package com.prose.crhen.SSServer.business;

import com.prose.crhen.SSServer.dto.ServerQueryDTO;
import com.prose.crhen.SSServer.dto.ServerUpdateDTO;
import com.prose.crhen.SSServer.dto.VolumesUpdateDTO;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
	
	public List<ServerQueryDTO> getServers() {
		List<Server> servers = serverRepository.findAll();
		List<ServerQueryDTO> serverQueryDTOS = new ArrayList<>();
		for (Server server : servers) {
			ServerQueryDTO serverQueryDTO = createServerQuery(server);
			serverQueryDTOS.add(serverQueryDTO);
		}
		return serverQueryDTOS;
	}

	private ServerQueryDTO createServerQuery(Server server) {
		ServerQueryDTO serverQueryDTO = new ServerQueryDTO();
		serverQueryDTO.setName(server.getName());
		serverQueryDTO.setRam(server.getRam());
		serverQueryDTO.setCpuUsage(server.getCpuUsage());
		serverQueryDTO.setVolumes(server.getVolumes());
		serverQueryDTO.setServerHistories(server.getServerHistories());
		return serverQueryDTO;
	}
	
	public List<Volume> getVolumes() {
		List<Volume> volumes = volumeRepository.findAll();
		return volumes;
	}

	public void saveServerDTO(ServerUpdateDTO server) {
		Optional<Server> serverOptional = Optional.ofNullable(this.serverRepository.findByName(server.getSystemName()));
		if (serverOptional.isPresent()) {
			Server updatedServer = serverOptional.get();
			updatedServer.setRam(server.getRam());
			updatedServer.setCpuUsage(server.getCpuUsage());
			saveServerWithHistory(serverOptional.get(), updatedServer);
		}
	}

	public void saveVolumeDTO(VolumesUpdateDTO newServer) {
		Optional<Server> serverFound = Optional.ofNullable(this.serverRepository.findByName(newServer.getSystemName()));
		if (serverFound.isPresent()) {
			checkForVolume(serverFound.get(), newServer);
			} else {
			Server createdServer = createServerToBeInserted(newServer);
			Volume createdVolume = createVolumeToBeInserted(newServer, createdServer);
			insertServerAndVolume(createdServer, createdVolume);
		}
	}

	private Server createServerToBeInserted(VolumesUpdateDTO newVolume) {
		Server insertedServer = new Server(newVolume.getSystemName());
		return insertedServer;
	}

	private Volume createVolumeToBeInserted(VolumesUpdateDTO newVolume, Server insertedServer) {
		double reserved = Double.parseDouble(newVolume.getCapacityGB()) - Double.parseDouble(newVolume.getFreeSpaceGB());
		Volume insertedVolume = new Volume(newVolume.getName(),"", Double.parseDouble(newVolume.getCapacityGB()) , reserved, Double.parseDouble(newVolume.getFreeSpaceGB()), Double.parseDouble(newVolume.getFreeSpacePercent()),insertedServer);
		return insertedVolume;
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

	private void saveServerWithHistory(Server server, Server newServer) {
		ServerHistory serverHistory = new ServerHistory(server.getRam(),server.getCpuUsage(), server);
		serverRepository.save(newServer);
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
		double reserved = Double.parseDouble(newVolume.getCapacityGB()) - Double.parseDouble(newVolume.getFreeSpaceGB());
		insertedVolume.setLatestStorageFree(Double.parseDouble(newVolume.getFreeSpaceGB()));
		insertedVolume.setLatestStorageRatio(Double.parseDouble(newVolume.getFreeSpacePercent()));
		insertedVolume.setLatestStorageReserved(reserved);
		return insertedVolume;
	}

	private void saveVolumeHistory(VolumesUpdateDTO newVolume) {
		Volume latestVolume = volumeRepository.findByName(newVolume.getName());
		VolumeHistory volumeHistory = new VolumeHistory(latestVolume.getLatestStorageReserved(), latestVolume.getLatestStorageFree(), latestVolume.getLatestStorageRatio(), latestVolume);
		volumeHistoryRepo.save(volumeHistory);
	}

	private void insertVolume(Server server,VolumesUpdateDTO newVolume) {
		double reserved = Double.parseDouble(newVolume.getCapacityGB()) - Double.parseDouble(newVolume.getFreeSpaceGB());
		Volume insertedVolume = new Volume(newVolume.getName(),"", Double.parseDouble(newVolume.getCapacityGB()) , reserved, Double.parseDouble(newVolume.getFreeSpaceGB()), Double.parseDouble(newVolume.getFreeSpacePercent()),server);

		volumeRepository.save(insertedVolume);
	}

	public ServerQueryDTO calcServerDetails(Server server) {
		 List<Volume> volumes = volumeRepository.findByServer(server);
		 ServerQueryDTO serverQueryDTO = createServerQuery(server);
		 serverQueryDTO.setLatestStorageFree(addServerStorageFromVolumes(volumes));
		 serverQueryDTO.setLatestStorageReserved(addServerReservedFromVolumes(volumes));
		 serverQueryDTO.setFullCapacity(addServerCapacityFromVolumes(volumes));
		 serverQueryDTO.setLatestStorageRatio(calculateStorageRatio(serverQueryDTO));
		 
		 return serverQueryDTO;
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

	private double calculateStorageRatio(ServerQueryDTO server) {
		double storageRatio = (server.getLatestStorageReserved()/server.getFullCapacity()) * 100;
		return storageRatio;
	}


}
