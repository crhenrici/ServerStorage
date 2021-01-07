package com.prose.crhen.SSServer.business;

import com.prose.crhen.SSServer.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prose.crhen.SSServer.db.ServerHistoryRepository;
import com.prose.crhen.SSServer.db.ServerRepository;
import com.prose.crhen.SSServer.db.VolumeHistoryRepository;
import com.prose.crhen.SSServer.db.VolumeRepository;
import com.prose.crhen.SSServer.model.Server;
import com.prose.crhen.SSServer.model.ServerHistory;
import com.prose.crhen.SSServer.model.Volume;
import com.prose.crhen.SSServer.model.VolumeHistory;

import java.util.*;

@Service
public class ServerService {
	
	@Autowired
	private ServerRepository serverRepository;

	@Autowired
	private VolumeRepository volumeRepository;
	
	@Autowired
	private VolumeHistoryRepository volumeHistoryRepo;
	
	@Autowired
	private ServerHistoryRepository serverHistoryRepo;

	//ONLY FOR DEMONSTRATION
	public void deleteAll() {
		serverRepository.deleteAll();
		volumeHistoryRepo.deleteAll();
		volumeHistoryRepo.deleteAll();
		serverHistoryRepo.deleteAll();
	}

	public ServerOverviewDTO getServerOverviewDTO() {
		List<Server> servers = serverRepository.findAll();
		List<Volume> volumes = null;
		double totalCapacity = 0;
		double totalCapacityUsed = 0;
		double totalCapacityFree = 0;
		for (Server server : servers) {
			volumes = volumeRepository.findByServer(server);
		}
		for (Volume volume : volumes) {
			totalCapacity += volume.getFullCapacity();
			totalCapacityUsed += volume.getLatestStorageReserved();
			totalCapacityFree += volume.getLatestStorageFree();
		}
		double totalUsedCapacityRatio = (totalCapacityUsed/totalCapacity) * 100;
		ServerOverviewDTO serverOverviewDTO = new ServerOverviewDTO(servers.size(), totalCapacity, totalCapacityUsed, totalCapacityFree, totalUsedCapacityRatio);
		return  serverOverviewDTO;
	}

	public List<ServerQueryDTO> getServers() {
		List<Server> servers = serverRepository.findAll();
		List<ServerQueryDTO> serverQueryDTOS = new ArrayList<>();
		for (Server server : servers) {
			ServerQueryDTO createdServerQuery = createServerQuery(server);
			serverQueryDTOS.add(createdServerQuery);
		}
		return serverQueryDTOS;
	}

	private ServerQueryDTO createServerQuery(Server server) {
		ServerQueryDTO.ServerQueryDTOBuilder serverQueryDTOBuilder = calcServerDetails(server);
		serverQueryDTOBuilder.name(server.getName())
				.ram(server.getRam())
				.ramUsage(server.getRamUsage())
				.cpuUsage(server.getCpuUsage())
				.volumes(server.getVolumes())
				.serverHistories(server.getServerHistories());
		return serverQueryDTOBuilder.build();
	}

	public List<VolumeQueryDTO> getVolumes() {
		List<Volume> volumes = volumeRepository.findAll();
		List<VolumeQueryDTO> volumeQueryDTOS = new ArrayList<>();
		for (Volume volume : volumes) {
			VolumeQueryDTO createdVolumeQuery = createVolumeQuery(volume);
			volumeQueryDTOS.add(createdVolumeQuery);
		}
		return volumeQueryDTOS;
	}

	public List<VolumeQueryDTO> getVolumesFromServer(String serverName) {
		Server server = serverRepository.findByName(serverName);
		List<Volume> volumes = volumeRepository.findByServer(server);
		List<VolumeQueryDTO> volumeQueryDTOS = new ArrayList<>();
		for (Volume volume : volumes) {
			VolumeQueryDTO createdVolumeQuery = createVolumeQuery(volume);
			volumeQueryDTOS.add(createdVolumeQuery);
		}
		return volumeQueryDTOS;
	}

	private VolumeQueryDTO createVolumeQuery(Volume volume) {
		VolumeQueryDTO volumeQueryDTO = VolumeQueryDTO
				.builder()
				.name(volume.getName())
				.desc(volume.getDesc())
				.date(volume.getDate())
				.fullCapacity(volume.getFullCapacity())
				.latestStorageReserved(volume.getLatestStorageReserved())
				.latestStorageFree(volume.getLatestStorageFree())
				.latestStorageRatio(volume.getLatestStorageRatio())
				.server(volume.getServer())
				.volumeHistories(volume.getVolumeHistories())
				.build();
		return volumeQueryDTO;
	}

	public void saveServerDTO(ServerUpdateDTO server) {
		Optional<Server> serverOptional = Optional.ofNullable(this.serverRepository.findByName(server.getSystemName()));
		if (serverOptional.isPresent()) {
			saveServerHistory(serverOptional.get());
			Server updatedServer = serverOptional.get();
			updatedServer.setRam(server.getRam().getCapacity());
			updatedServer.setRamUsage(server.getRamUsage());
			updatedServer.setCpuUsage(server.getCpuUsage().getCookedValue());
			serverRepository.save(updatedServer);
		} else {
			Server insertedServer = new Server(server.getSystemName(), server.getRam().getCapacity(), server.getRamUsage() , server.getCpuUsage().getCookedValue());
			serverRepository.save(insertedServer);
		}
	}

	public void saveVolumeDTO(VolumesUpdateDTO newServer) {
		Optional<Server> serverFound = Optional.ofNullable(this.serverRepository.findByName(newServer.getSystemName()));
		if (serverFound.isPresent()) {
			checkForVolume(serverFound.get(), newServer);
			} else {
			Server createdServer = createServerToBeInsertedOffVolume(newServer);
			Volume createdVolume = createVolumeToBeInserted(newServer, createdServer);
			insertServerAndVolume(createdServer, createdVolume);
		}
	}

	private Server createServerToBeInsertedOffVolume(VolumesUpdateDTO newVolume) {
		Server insertedServer = new Server(newVolume.getSystemName());
		return insertedServer;
	}

	private Volume createVolumeToBeInserted(VolumesUpdateDTO newVolume, Server insertedServer) {
		double reserved = Double.parseDouble(newVolume.getCapacityGB()) - Double.parseDouble(newVolume.getFreeSpaceGB());
		java.sql.Date sqlDate = getSqlDate();
		Volume insertedVolume = new Volume(newVolume.getName(),"" , sqlDate , Double.parseDouble(newVolume.getCapacityGB()) , reserved, Double.parseDouble(newVolume.getFreeSpaceGB()), Double.parseDouble(newVolume.getFreeSpacePercent()),insertedServer);
		return insertedVolume;
	}

	private java.sql.Date getSqlDate() {
		Date currentDate = new Date();
		return new java.sql.Date(currentDate.getTime());
	}

	private void insertServerAndVolume(Server insertedServer, Volume insertedVolume) {
		serverRepository.save(insertedServer);
		volumeRepository.save(insertedVolume);
	}

	private void checkForVolume(Server server, VolumesUpdateDTO newVolume) {
		Optional<Volume> volumeOptional = Optional.ofNullable(volumeRepository.findByName(newVolume.getName()));
		Volume insertedVolume = null;
		if (volumeOptional.isPresent()) {
			insertedVolume = volumeOptional.get();
			Set<Volume> volumes = updateVolume(insertedVolume, newVolume);
			server.setVolumes(volumes);
		} else {
			insertVolume(server, newVolume);
		}
	}

	private void saveServerHistory(Server server) {
		ServerHistory serverHistory = new ServerHistory(server.getRam(), server.getRamUsage(), server.getCpuUsage(), server);
		serverHistoryRepo.save(serverHistory);
	}

	private Set<Volume> updateVolume(Volume updatedVolume, VolumesUpdateDTO newVolume) {
		saveVolumeHistory(newVolume);

		Volume insertedVolume = getVolumeToBeInserted(updatedVolume, newVolume);

		Set<Volume> volumes = insertedVolume.getServer().getVolumes();
		volumeRepository.save(insertedVolume);
		return volumes;
	}

	private Volume getVolumeToBeInserted(Volume updatedVolume, VolumesUpdateDTO newVolume) {
		Volume insertedVolume = updatedVolume;
		java.sql.Date sqlDate = getSqlDate();
		double reserved = Double.parseDouble(newVolume.getCapacityGB()) - Double.parseDouble(newVolume.getFreeSpaceGB());
		insertedVolume.setLatestStorageFree(Double.parseDouble(newVolume.getFreeSpaceGB()));
		insertedVolume.setLatestStorageRatio(Double.parseDouble(newVolume.getFreeSpacePercent()));
		insertedVolume.setLatestStorageReserved(reserved);
		insertedVolume.setDate(sqlDate);
		return insertedVolume;
	}

	private void saveVolumeHistory(VolumesUpdateDTO newVolume) {
		Volume latestVolume = volumeRepository.findByName(newVolume.getName());
		VolumeHistory volumeHistory = new VolumeHistory(latestVolume.getDate(), latestVolume.getLatestStorageReserved(), latestVolume.getLatestStorageFree(), latestVolume.getLatestStorageRatio(), latestVolume);
		volumeHistoryRepo.save(volumeHistory);
	}

	private void insertVolume(Server server,VolumesUpdateDTO newVolume) {
		double reserved = Double.parseDouble(newVolume.getCapacityGB()) - Double.parseDouble(newVolume.getFreeSpaceGB());
		java.sql.Date sqlDate = getSqlDate();
		Volume insertedVolume = new Volume(newVolume.getName(),"", sqlDate , Double.parseDouble(newVolume.getCapacityGB()) , reserved, Double.parseDouble(newVolume.getFreeSpaceGB()), Double.parseDouble(newVolume.getFreeSpacePercent()),server);

		volumeRepository.save(insertedVolume);
	}

	public ServerQueryDTO.ServerQueryDTOBuilder calcServerDetails(Server server) {
		 List<Volume> volumes = volumeRepository.findByServer(server);
		double fullCapacity = addServerCapacityFromVolumes(volumes);
		double latestStorageFree = addServerStorageFromVolumes(volumes);
		double latestStorageRatio = calculateStorageRatio(fullCapacity, latestStorageFree);
		double latestStorageReserved = addServerReservedFromVolumes(volumes);
		ServerQueryDTO.ServerQueryDTOBuilder  serverQueryDTOBuilder = ServerQueryDTO.builder()
				 .latestStorageFree(latestStorageFree)
				 .latestStorageReserved(latestStorageReserved)
				 .fullCapacity(fullCapacity)
				 .latestStorageRatio(latestStorageRatio);
		 return serverQueryDTOBuilder;
	}

	private double addServerStorageFromVolumes(List<Volume> volumes) {
		double storageFree = 0;
		for (Volume volume : volumes) {
			storageFree += volume.getLatestStorageFree();
		}
		return storageFree;
	}

	private double addServerReservedFromVolumes(List<Volume> volumes) {
		double storageReserved = 0;
		for (Volume volume : volumes) {
			storageReserved += volume.getLatestStorageReserved();
		}
		return storageReserved;
	}

	private double addServerCapacityFromVolumes(List<Volume> volumes) {
		double fullCapacity = 0;
		for (Volume volume : volumes) {
			fullCapacity += volume.getFullCapacity();
		}
		return fullCapacity;
	}

	private double calculateStorageRatio(double fullCapacity, double latestStorageFree) {
		double storageRatio = (latestStorageFree/fullCapacity) * 100;
		return storageRatio;
	}


}
