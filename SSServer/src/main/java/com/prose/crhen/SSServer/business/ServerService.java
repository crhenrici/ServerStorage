package com.prose.crhen.SSServer.business;

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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class ServerService {
	
	@Autowired(required = false)
	ServerRepository serverRepository;

	@Autowired
	VolumeRepository volumeRepository;
	
	@Autowired
	VolumeHistoryRepository volumeHistoryRepo;
	
	@Autowired
	ServerHistoryRepository serverHistoryRepo;
	
	public List<Server> getServers() {
		List<Server> servers = serverRepository.findAll();
		return servers;
	}
	
	public List<Volume> getVolumes() {
		List<Volume> volumes = volumeRepository.findAll();
		return volumes;
	}
	
	//update or save server
	public void saveServer(Server newServer) {
		 Optional<Server> serverFound = this.serverRepository.findById(newServer.getId());
		 
		 //calculate details of server
		 Server updatedServer = calcServerDetails(newServer);
		 ServerHistory serverHistory = new ServerHistory();
		 serverHistory.setStorageFree(updatedServer.getLatestStorageFree());
		 serverHistory.setStorageReserved(updatedServer.getLatestStorageReserved());
		 serverHistory.setStorageRatio(updatedServer.getLatestStorageRatio());
		 serverHistory.setServer(updatedServer);
		 serverHistoryRepo.save(serverHistory);
		 
		 if (serverFound.get().getId() == updatedServer.getId()) {			 
			 //update the server
			serverFound.map(server -> {
				server.setLatestStorageFree(updatedServer.getLatestStorageFree());
				server.setLatestStorageReserved(updatedServer.getLatestStorageReserved());
				server.setLatestStorageRatio(updatedServer.getLatestStorageRatio());
				server.setVolumes(updatedServer.getVolumes());
				server.setCpuUsage(updatedServer.getCpuUsage());
				server.setServerHistories(updatedServer.getServerHistories());
				return serverRepository.save(server);
			});
		 } else {
			 //save new server
			 serverRepository.save(newServer);
		 }
	}
	
	//update or save volume
	public void saveVolume(Volume newVolume) {
		Optional<Volume> volumeFound = this.volumeRepository.findById(newVolume.getId());
		VolumeHistory volumeHistory = new VolumeHistory();
		volumeHistory.setStorageFree(newVolume.getLatestStorageFree());
		volumeHistory.setStorageReserved(newVolume.getLatestStorageReserved());
		volumeHistory.setStorageRatio(newVolume.getLatestStorageRatio());
		volumeHistory.setVolume(newVolume);
		volumeHistoryRepo.save(volumeHistory);
		
		if (volumeFound.get().getId() == newVolume.getId()) {	
			//update the volume
			volumeFound.map(volume -> {
				volume.setDesc(newVolume.getDesc());
				volume.setLatestStorageFree(newVolume.getLatestStorageFree());
				volume.setLatestStorageReserved(newVolume.getLatestStorageReserved());
				volume.setLatestStorageRatio(newVolume.getLatestStorageRatio());
				volume.setVolumeHistories(newVolume.getVolumeHistories());
				return volumeRepository.save(volume);
			});
		} else {
			//save new volume
			volumeRepository.save(newVolume);
		}
	}
	
	//calculate details of the server
	public Server calcServerDetails(Server server) {
		 List<Volume> volumes = volumeRepository.findByServer(server);
		 double storageFree = 0;
		 double storageReserved = 0;
		 for (Volume volume : volumes) {
			 storageFree += volume.getLatestStorageFree();
			 storageReserved += volume.getLatestStorageReserved();
		 }
		
		 double storageRatio = (storageReserved/server.getFullCapacity()) * 100;
		 
		 server.setLatestStorageFree(storageFree);
		 server.setLatestStorageReserved(storageReserved);
		 server.setLatestStorageRatio(storageRatio);
		 
		 return server;
	}
	
    //calculate the storaga ratio of the server
    public double calculateStorageRatio(double storageCapacity, double storageUsed) {
        double storageRatio = (Double.valueOf(storageUsed)/Double.valueOf(storageCapacity))*100;
        storageRatio = round(storageRatio);
        return storageRatio;
    }

    //round the number to two decimals
    private double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
