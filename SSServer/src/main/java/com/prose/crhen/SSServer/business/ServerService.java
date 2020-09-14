package com.prose.crhen.SSServer.business;

import com.prose.crhen.SSServer.dto.VolumesUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public void save(VolumesUpdateDTO newServer) {
		Optional<Server> serverFound = Optional.ofNullable(this.serverRepository.findByName(newServer.getSystemName()));
		if (serverFound.isPresent()) {
			updateServer(serverFound.get(), newServer);
			} else {
			insertServer(newServer);
		}
	}

	private void insertServer(VolumesUpdateDTO newVolume) {
		double reserved = Double.parseDouble(newVolume.getCapacityGB()) - Double.parseDouble(newVolume.getFreeSpaceGB());
		Server insertedServer = new Server(newVolume.getSystemName(), Double.parseDouble(newVolume.getCapacityGB()), reserved, Double.parseDouble(newVolume.getFreeSpaceGB()), Double.parseDouble(newVolume.getFreeSpacePercent()), Integer.parseInt(newVolume.getRam()), Double.parseDouble(newVolume.getCpuUsage()));
		Volume insertedVolume = new Volume(newVolume.getName(),"", Double.parseDouble(newVolume.getCapacityGB()) , reserved, Double.parseDouble(newVolume.getFreeSpaceGB()), Double.parseDouble(newVolume.getFreeSpacePercent()),insertedServer);

		serverRepository.save(insertedServer);
		volumeRepository.save(insertedVolume);
	}

	private void updateServer(Server server, VolumesUpdateDTO newVolume) {
		Optional<Volume> volumeOptional = Optional.ofNullable(volumeRepository.findByName(newVolume.getName()));
		Volume insertedVolume = null;
		if (volumeOptional.isPresent()) {
			insertedVolume = volumeOptional.get();
			Set<Volume> volumes = updateVolume(insertedVolume, newVolume);
			server.setVolumes(volumes);
		} else {
			insertVolume(server, newVolume);
		}
		Server newServer = calcServerDetails(server);
		ServerHistory serverHistory = new ServerHistory(server.getLatestStorageReserved(), server.getLatestStorageFree(), server.getLatestStorageRatio(), server);
		serverRepository.save(newServer);
		serverHistoryRepo.save(serverHistory);
	}
	
	private Set<Volume> updateVolume(Volume updatedVolume, VolumesUpdateDTO newVolume) {
		double reserved = Double.parseDouble(newVolume.getCapacityGB()) - Double.parseDouble(newVolume.getFreeSpaceGB());

		Volume latestVolume = volumeRepository.findByName(newVolume.getName());
		VolumeHistory volumeHistory = new VolumeHistory(latestVolume.getLatestStorageReserved(), latestVolume.getLatestStorageFree(), latestVolume.getLatestStorageRatio(), latestVolume);
		volumeHistoryRepo.save(volumeHistory);

		Volume insertedVolume = updatedVolume;
		insertedVolume.setLatestStorageFree(Double.parseDouble(newVolume.getFreeSpaceGB()));
		insertedVolume.setLatestStorageRatio(Double.parseDouble(newVolume.getFreeSpacePercent()));
		insertedVolume.setLatestStorageReserved(reserved);

		Set<Volume> volumes = insertedVolume.getServer().getVolumes();
		volumeRepository.save(insertedVolume);
		return volumes;
	}

	private void insertVolume(Server server,VolumesUpdateDTO newVolume) {
		double reserved = Double.parseDouble(newVolume.getCapacityGB()) - Double.parseDouble(newVolume.getFreeSpaceGB());
		Volume insertedVolume = new Volume(newVolume.getName(),"", Double.parseDouble(newVolume.getCapacityGB()) , reserved, Double.parseDouble(newVolume.getFreeSpaceGB()), Double.parseDouble(newVolume.getFreeSpacePercent()),server);

		volumeRepository.save(insertedVolume);
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
