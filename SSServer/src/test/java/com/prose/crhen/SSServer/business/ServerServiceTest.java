package com.prose.crhen.SSServer.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.prose.crhen.SSServer.controller.TestConfig;
import com.prose.crhen.SSServer.db.ServerHistoryRepository;
import com.prose.crhen.SSServer.db.ServerRepository;
import com.prose.crhen.SSServer.db.VolumeHistoryRepository;
import com.prose.crhen.SSServer.db.VolumeRepository;
import com.prose.crhen.SSServer.dto.VolumesUpdateDTO;
import com.prose.crhen.SSServer.model.Server;
import com.prose.crhen.SSServer.model.ServerHistory;
import com.prose.crhen.SSServer.model.Volume;
import com.prose.crhen.SSServer.model.VolumeHistory;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class ServerServiceTest {
	
	@Autowired
	ServerService service;
	
	@Autowired
	ServerRepository serverRepository;
	
	@Autowired
	VolumeRepository volumeRepository;
	
	@Autowired
	ServerHistoryRepository serverHistoryRepository;
	
	@Autowired
	VolumeHistoryRepository volumeHistoryRepository;
	
	VolumesUpdateDTO newVolume;
	
	
	@BeforeEach
	void setUp() {
		newVolume = new VolumesUpdateDTO();
		newVolume.setCapacityGB("100");
		newVolume.setCpuUsage("28.75");
		newVolume.setDate("10-08-2020");
		newVolume.setDriveLetter("D");
		newVolume.setFreeSpaceGB("50");
		newVolume.setFreeSpacePercent("50");
		newVolume.setName("Test");
		newVolume.setRam("8");
		newVolume.setSystemName("TestServer");
	}
	
	@Test
	void saveServerTest() {
		service.saveServer(newVolume);
		assertEquals(1, serverRepository.count());
		assertEquals(1, volumeRepository.count());
		VolumesUpdateDTO someVolume = new VolumesUpdateDTO();
		someVolume.setCapacityGB("200");
		someVolume.setCpuUsage("15.75");
		someVolume.setDate("12-08-2020");
		someVolume.setDriveLetter("E");
		someVolume.setFreeSpaceGB("50");
		someVolume.setFreeSpacePercent("25");
		someVolume.setName("Testing");
		someVolume.setRam("16");
		someVolume.setSystemName("TestServer");
		service.saveServer(someVolume);
		assertEquals(2, volumeRepository.count());
		double fullCapacity = Double.parseDouble(newVolume.getCapacityGB()) + Double.parseDouble(someVolume.getCapacityGB());
		Server server = serverRepository.findByName("TestServer");
		assertEquals(fullCapacity, server.getFullCapacity());
		assertEquals(2, server.getVolumes().size());
		assertEquals(2, server.getServerHistories().size());
		for (ServerHistory serverHistory : server.getServerHistories()) {
			System.out.println("ServerHistory: " + serverHistory.toString());
		}

		VolumesUpdateDTO lastVolume = new VolumesUpdateDTO();
		lastVolume.setCapacityGB("150");
		lastVolume.setCpuUsage("12.65");
		lastVolume.setDate("14-08-2020");
		lastVolume.setDriveLetter("f");
		lastVolume.setFreeSpaceGB("50");
		lastVolume.setFreeSpacePercent("33.33");
		lastVolume.setName("TestingVolume");
		lastVolume.setRam("4");
		lastVolume.setSystemName("TestingServer");
		service.saveServer(lastVolume);
		assertEquals(2, serverRepository.count());
		assertEquals(3, volumeRepository.count());
		Server testingServer = serverRepository.findByName("TestingServer");
		assertEquals(1, testingServer.getServerHistories().size());
		List<ServerHistory> serverHistories = serverHistoryRepository.findAll();
		assertEquals(3, serverHistoryRepository.count());
		assertEquals(3, serverHistories.size());
	}
	
	@Test
	void saveServerUpdateVolumeTest() {
		VolumesUpdateDTO updatedVolume = newVolume;
		updatedVolume.setCpuUsage("32.43");
		updatedVolume.setDate("2020-08-20");
		updatedVolume.setFreeSpaceGB("25");
		updatedVolume.setFreeSpacePercent("25");
		service.saveServer(updatedVolume);
		List<Volume> volumes = service.getVolumes();
		Volume volume = volumes.get(0);
		System.out.println("volume: " + volume.toString());
		assertEquals(25, volume.getLatestStorageFree());
		assertEquals(75, volume.getLatestStorageReserved());
		assertEquals(3, volumeRepository.count());
		assertEquals(4, volumeHistoryRepository.count());
	}

}
