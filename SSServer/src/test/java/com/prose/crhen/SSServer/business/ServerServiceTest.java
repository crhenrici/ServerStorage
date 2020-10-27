package com.prose.crhen.SSServer.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
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

	VolumesUpdateDTO secondVolume;

	VolumesUpdateDTO lastVolume;

	VolumesUpdateDTO updatedVolume;

	

	void setUpNewServerTest() {
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

	void setUpExistingServerTest() {
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
		service.saveVolumeDTO(newVolume);

		secondVolume = new VolumesUpdateDTO();
		secondVolume.setCapacityGB("200");
		secondVolume.setCpuUsage("15.75");
		secondVolume.setDate("12-08-2020");
		secondVolume.setDriveLetter("E");
		secondVolume.setFreeSpaceGB("50");
		secondVolume.setFreeSpacePercent("25");
		secondVolume.setName("Testing");
		secondVolume.setRam("16");
		secondVolume.setSystemName("TestServer");
	}

	void setUpNewServerAndNewVolumeTest() {
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
		service.saveVolumeDTO(newVolume);

		secondVolume = new VolumesUpdateDTO();
		secondVolume.setCapacityGB("200");
		secondVolume.setCpuUsage("15.75");
		secondVolume.setDate("12-08-2020");
		secondVolume.setDriveLetter("E");
		secondVolume.setFreeSpaceGB("50");
		secondVolume.setFreeSpacePercent("25");
		secondVolume.setName("Testing");
		secondVolume.setRam("16");
		secondVolume.setSystemName("TestServer");
		service.saveVolumeDTO(secondVolume);

		lastVolume = new VolumesUpdateDTO();
		lastVolume.setCapacityGB("150");
		lastVolume.setCpuUsage("12.65");
		lastVolume.setDate("14-08-2020");
		lastVolume.setDriveLetter("f");
		lastVolume.setFreeSpaceGB("50");
		lastVolume.setFreeSpacePercent("33.33");
		lastVolume.setName("TestingVolume");
		lastVolume.setRam("4");
		lastVolume.setSystemName("TestingServer");
	}

	void setUpExistingServerAndExistingVolumeTest() {
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
		service.saveVolumeDTO(newVolume);

		secondVolume = new VolumesUpdateDTO();
		secondVolume.setCapacityGB("200");
		secondVolume.setCpuUsage("15.75");
		secondVolume.setDate("12-08-2020");
		secondVolume.setDriveLetter("E");
		secondVolume.setFreeSpaceGB("50");
		secondVolume.setFreeSpacePercent("25");
		secondVolume.setName("Testing");
		secondVolume.setRam("16");
		secondVolume.setSystemName("TestServer");
		service.saveVolumeDTO(secondVolume);

		lastVolume = new VolumesUpdateDTO();
		lastVolume.setCapacityGB("150");
		lastVolume.setCpuUsage("12.65");
		lastVolume.setDate("14-08-2020");
		lastVolume.setDriveLetter("f");
		lastVolume.setFreeSpaceGB("50");
		lastVolume.setFreeSpacePercent("33.33");
		lastVolume.setName("TestingVolume");
		lastVolume.setRam("4");
		lastVolume.setSystemName("TestingServer");
		service.saveVolumeDTO(lastVolume);

		updatedVolume = newVolume;
		updatedVolume.setCpuUsage("32.43");
		updatedVolume.setDate("2020-08-20");
		updatedVolume.setFreeSpaceGB("25");
		updatedVolume.setFreeSpacePercent("25");
	}

	@Test
	void saveNewServerTest() {
		setUpNewServerTest();
		service.saveVolumeDTO(newVolume);
		assertEquals(1, serverRepository.count());
		assertEquals(1, volumeRepository.count());
	}

	@Test
	void saveExistingServerTest() {
		setUpExistingServerTest();
		service.saveVolumeDTO(secondVolume);
		assertEquals(2, volumeRepository.count());
		double fullCapacity = Double.parseDouble(newVolume.getCapacityGB()) + Double.parseDouble(secondVolume.getCapacityGB());
		Server server = serverRepository.findByName("TestServer");
		assertEquals(fullCapacity, server.getFullCapacity());
		assertEquals(2, server.getVolumes().size());
		assertEquals(1, server.getServerHistories().size());
	}

	@Test
	void saveNewServerAndNewVolume() {
		setUpNewServerAndNewVolumeTest();
		service.saveVolumeDTO(lastVolume);
		assertEquals(2, serverRepository.count());
		assertEquals(3, volumeRepository.count());
		Server testingServer = serverRepository.findByName("TestingServer");
		assertEquals(0, testingServer.getServerHistories().size());
		List<ServerHistory> serverHistories = serverHistoryRepository.findAll();
		assertEquals(1, serverHistoryRepository.count());
		assertEquals(1, serverHistories.size());
	}

	@Test
	void saveExistingServerAndExistingVolume() {
		setUpExistingServerAndExistingVolumeTest();
		service.saveVolumeDTO(updatedVolume);
		List<Volume> volumes = service.getVolumes();
		Volume volume = volumes.get(0);
		assertEquals(25, volume.getLatestStorageFree());
		assertEquals(75, volume.getLatestStorageReserved());
		assertEquals(3, volumeRepository.count());
		assertEquals(1, volumeHistoryRepository.count());
	}

	@AfterEach
	void tearDown() {
		serverRepository.deleteAll();
		volumeRepository.deleteAll();
		serverHistoryRepository.deleteAll();
		volumeHistoryRepository.deleteAll();
	}

}
