package com.prose.crhen.SSServer.business;

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

import java.util.LinkedHashSet;
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
	
	public List<Server> getServers() {
		List<Server> servers = serverRepository.findAll();
		return servers;
	}
	
	public List<Volume> getVolumes() {
		List<Volume> volumes = volumeRepository.findAll();
		return volumes;
	}
	
	//update or save server
	public void saveServer(VolumesUpdateDTO newServer) {
//		 Optional<Server> serverFound = this.serverRepository.findById(newServer.getId());
//
//		 //calculate details of server
//		 Server updatedServer = calcServerDetails(newServer);
//		 //save data to serverHistory
//		 ServerHistory serverHistory = new ServerHistory(updatedServer.getLatestStorageFree(),updatedServer.getLatestStorageReserved(), updatedServer.getLatestStorageRatio(),
//				 updatedServer);
//		 serverHistoryRepo.save(serverHistory);
//		 newServer.getServerHistories().add(serverHistory);
//
//		 if (serverFound.get().getId() == updatedServer.getId()) {
//			 //update the server
//			serverFound.map(server -> {
//				server.setLatestStorageFree(updatedServer.getLatestStorageFree());
//				server.setLatestStorageReserved(updatedServer.getLatestStorageReserved());
//				server.setLatestStorageRatio(updatedServer.getLatestStorageRatio());
//				server.setVolumes(updatedServer.getVolumes());
//				server.setCpuUsage(updatedServer.getCpuUsage());
//				server.setServerHistories(updatedServer.getServerHistories());
//				return serverRepository.save(server);
//			});
//		 } else {
//			 //save new server
//			 serverRepository.save(newServer);
//		 }
		Optional<Server> serverFound = Optional.ofNullable(this.serverRepository.findByName(newServer.getSystemName()));
		if (serverFound.isPresent()) {
			updateServer(serverFound.get(), newServer);
			System.out.println(serverFound.get().getServerHistories().size());
		} else {
			insertServer(newServer);
		}
	}
	
	//update or save volume
	//this is the same code as saveServer - should be deleted
	public void saveVolume(VolumesUpdateDTO volumeData) {
//		Optional<Volume> volumeFound = this.volumeRepository.findByName(newVolume.getId());
//        if (volumeFound.isPresent()) {
//			updateVolume(volumeData);
//		} else {
//			insertVolume(volumeData);
//		}
//		//save data to volumeHistory
//		VolumeHistory volumeHistory = new VolumeHistory(newVolume.getLatestStorageReserved(), newVolume.getLatestStorageFree(), newVolume.getLatestStorageRatio(), newVolume);
//		volumeHistoryRepo.save(volumeHistory);
//		newVolume.getVolumeHistories().add(volumeHistory);
//
//		if (volumeFound.get().getId() == newVolume.getId()) {
//			//update the volume
//			volumeFound.map(volume -> {
//				volume.setDesc(newVolume.getDesc());
//				volume.setLatestStorageFree(newVolume.getLatestStorageFree());
//				volume.setLatestStorageReserved(newVolume.getLatestStorageReserved());
//				volume.setLatestStorageRatio(newVolume.getLatestStorageRatio());
//				volume.setVolumeHistories(newVolume.getVolumeHistories());
//				return volumeRepository.save(volume);
//			});
//		} else {
//			//save new volume
//			volumeRepository.save(newVolume);
//		}
		Optional<Server> serverOptional = Optional.ofNullable(serverRepository.findByName(volumeData.getSystemName()));
		if (serverOptional.isPresent()) {
			updateServer(serverOptional.get(),volumeData);
		} else {
			insertServer(volumeData);
		}
	}

	private void insertServer(VolumesUpdateDTO newVolume) {
		double reserved = Double.parseDouble(newVolume.getCapacityGB()) - Double.parseDouble(newVolume.getFreeSpaceGB());
		//creating the new entities
		Server insertedServer = new Server(newVolume.getSystemName(), Double.parseDouble(newVolume.getCapacityGB()), reserved, Double.parseDouble(newVolume.getFreeSpaceGB()), Double.parseDouble(newVolume.getFreeSpacePercent()), Integer.parseInt(newVolume.getRam()), Double.parseDouble(newVolume.getCpuUsage()));
		Volume insertedVolume = new Volume(newVolume.getName(),"", Double.parseDouble(newVolume.getCapacityGB()) , reserved, Double.parseDouble(newVolume.getFreeSpaceGB()), Double.parseDouble(newVolume.getFreeSpacePercent()),insertedServer);
		Set<Volume> volumes = new LinkedHashSet<>();
		VolumeHistory volumeHistory = new VolumeHistory(insertedVolume.getLatestStorageReserved(), insertedVolume.getLatestStorageFree(), insertedVolume.getLatestStorageRatio(), insertedVolume);
		Set<VolumeHistory> volumeHistories = new LinkedHashSet<>();
		ServerHistory serverHistory = new ServerHistory(insertedServer.getLatestStorageFree(), insertedServer.getLatestStorageReserved(), insertedServer.getLatestStorageRatio(),insertedServer);
		Set<ServerHistory> serverHistories = new LinkedHashSet<>();

		//linking the entities
		volumes.add(insertedVolume);
		serverHistories.add(serverHistory);
		insertedServer.setVolumes(volumes);
		insertedVolume.setVolumeHistories(volumeHistories);
		insertedServer.setServerHistories(serverHistories);
		
		//save in repository
		serverRepository.save(insertedServer);
		volumeRepository.save(insertedVolume);
		volumeHistoryRepo.save(volumeHistory);
		serverHistoryRepo.save(serverHistory);
	}

	private void updateServer(Server server, VolumesUpdateDTO newVolume) {
		Optional<Volume> volumeOptional = Optional.ofNullable(volumeRepository.findByName(newVolume.getName()));
		Volume insertedVolume = null;
		if (volumeOptional.isPresent()) {
			insertedVolume = volumeOptional.get();
			Volume updatedVolume = updateVolume(insertedVolume, newVolume);
			Set<Volume> volumes = server.getVolumes();
			volumes.add(updatedVolume);
		} else {
			insertedVolume = insertVolume(server, newVolume);
		}
		Server newServer = calcServerDetails(server);
		newServer.getVolumes().add(insertedVolume);
		ServerHistory serverHistory = new ServerHistory(newServer.getLatestStorageReserved(), newServer.getLatestStorageFree(), newServer.getLatestStorageRatio(), newServer);
		System.out.println(newServer.getServerHistories().size());
		newServer.getServerHistories().add(serverHistory);
		System.out.println(newServer.getServerHistories().size());
		serverRepository.save(newServer);
		serverHistoryRepo.save(serverHistory);
		System.out.println(newServer.getServerHistories().size());
	}
	
	private Volume updateVolume(Volume updatedVolume, VolumesUpdateDTO newVolume) {
		double reserved = Double.parseDouble(newVolume.getCapacityGB()) - Double.parseDouble(newVolume.getFreeSpaceGB());
		Volume insertedVolume = updatedVolume;
		insertedVolume.setLatestStorageFree(Double.parseDouble(newVolume.getFreeSpaceGB()));
		insertedVolume.setLatestStorageRatio(Double.parseDouble(newVolume.getFreeSpacePercent()));
		insertedVolume.setLatestStorageReserved(reserved);
		VolumeHistory volumeHistory = new VolumeHistory(insertedVolume.getLatestStorageReserved(), insertedVolume.getLatestStorageFree(), insertedVolume.getLatestStorageRatio(), insertedVolume);
		Set<VolumeHistory> volumeHistories = updatedVolume.getVolumeHistories();
		volumeHistories.add(volumeHistory);
		insertedVolume.setVolumeHistories(volumeHistories);

		volumeRepository.save(insertedVolume);
		volumeHistoryRepo.save(volumeHistory);
		return insertedVolume;
	}

	private Volume insertVolume(Server server,VolumesUpdateDTO newVolume) {
		double reserved = Double.parseDouble(newVolume.getCapacityGB()) - Double.parseDouble(newVolume.getFreeSpaceGB());
		Volume insertedVolume = new Volume(newVolume.getName(),"", Double.parseDouble(newVolume.getCapacityGB()) , reserved, Double.parseDouble(newVolume.getFreeSpaceGB()), Double.parseDouble(newVolume.getFreeSpacePercent()),server);
		Set<Volume> volumes = new LinkedHashSet<>();
		VolumeHistory volumeHistory = new VolumeHistory(insertedVolume.getLatestStorageReserved(), insertedVolume.getLatestStorageFree(), insertedVolume.getLatestStorageRatio(), insertedVolume);
		Set<VolumeHistory> volumeHistories = new LinkedHashSet<>();

		server.setVolumes(volumes);
		volumeHistories.add(volumeHistory);
		insertedVolume.setVolumeHistories(volumeHistories);

		volumeRepository.save(insertedVolume);
		volumeHistoryRepo.save(volumeHistory);

		return insertedVolume;
	}


	//calculate details of the server
	public Server calcServerDetails(Server server) {
		 List<Volume> volumes = volumeRepository.findByServer(server);
		 double storageFree = 0;
		 double storageReserved = 0;
		 double fullCapacity = 0;
		 for (Volume volume : volumes) {
			 storageFree += volume.getLatestStorageFree();
			 storageReserved += volume.getLatestStorageReserved();
			 fullCapacity += volume.getFullCapacity();
		 }
		
		 double storageRatio = (storageReserved/fullCapacity) * 100;

		 server.setLatestStorageFree(storageFree);
		 server.setLatestStorageReserved(storageReserved);
		 server.setLatestStorageRatio(storageRatio);
		 server.setFullCapacity(fullCapacity);
		 
		 return server;
	}
}
