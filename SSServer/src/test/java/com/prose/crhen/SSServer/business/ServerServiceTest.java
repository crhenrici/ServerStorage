package com.prose.crhen.SSServer.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.prose.crhen.SSServer.dto.ServerQueryDTO;
import com.prose.crhen.SSServer.dto.VolumeQueryDTO;
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

	Volume volume;

	Volume volume2;

	Volume volume3;

	Volume volume4;

	Volume volume5;

	Server server;

	Server server2;

	ServerHistory serverHistory;
	

	void setUpNewServerTest() {
		newVolume = VolumesUpdateDTO.builder()
		.capacityGB("100")
		.date("10-8-2020")
		.driveLetter("D")
		.freeSpaceGB("50")
		.freeSpacePercent("50")
		.name("Test")
		.systemName("TestServer")
		.build();
	}

	void setUpExistingServerTest() {
		newVolume = VolumesUpdateDTO.builder()
				.capacityGB("100")
				.date("10-8-2020")
				.driveLetter("D")
				.freeSpaceGB("50")
				.freeSpacePercent("50")
				.name("Test")
				.systemName("TestServer")
				.build();
		service.saveVolumeDTO(newVolume);

		secondVolume = VolumesUpdateDTO.builder()
		.capacityGB("200")
		.date("12-08-2020")
		.driveLetter("E")
		.freeSpaceGB("50")
		.freeSpacePercent("25")
		.name("Testing")
		.systemName("TestServer")
		.build();
	}

	void setUpNewServerAndNewVolumeTest() {
		newVolume = VolumesUpdateDTO.builder()
				.capacityGB("100")
				.date("10-8-2020")
				.driveLetter("D")
				.freeSpaceGB("50")
				.freeSpacePercent("50")
				.name("Test")
				.systemName("TestServer")
				.build();
		service.saveVolumeDTO(newVolume);

		secondVolume = VolumesUpdateDTO.builder()
				.capacityGB("200")
				.date("12-08-2020")
				.driveLetter("E")
				.freeSpaceGB("50")
				.freeSpacePercent("25")
				.name("Testing")
				.systemName("TestServer")
				.build();
		service.saveVolumeDTO(secondVolume);

		lastVolume = VolumesUpdateDTO.builder()
		.capacityGB("150")
		.date("14-08-2020")
		.driveLetter("f")
		.freeSpaceGB("50")
		.freeSpacePercent("33.33")
		.name("TestingVolume")
		.systemName("TestingServer")
		.build();
	}

	void setUpExistingServerAndExistingVolumeTest() {
		newVolume = VolumesUpdateDTO.builder()
				.capacityGB("100")
				.date("10-8-2020")
				.driveLetter("D")
				.freeSpaceGB("50")
				.freeSpacePercent("50")
				.name("Test")
				.systemName("TestServer")
				.build();
		service.saveVolumeDTO(newVolume);

		secondVolume = VolumesUpdateDTO.builder()
				.capacityGB("200")
				.date("12-08-2020")
				.driveLetter("E")
				.freeSpaceGB("50")
				.freeSpacePercent("25")
				.name("Testing")
				.systemName("TestServer")
				.build();
		service.saveVolumeDTO(secondVolume);

		lastVolume = VolumesUpdateDTO.builder()
				.capacityGB("150")
				.date("14-08-2020")
				.driveLetter("f")
				.freeSpaceGB("50")
				.freeSpacePercent("33.33")
				.name("TestingVolume")
				.systemName("TestingServer")
				.build();
		service.saveVolumeDTO(lastVolume);

		updatedVolume = VolumesUpdateDTO.builder()
				.capacityGB("100")
				.date("2020-08-20")
				.driveLetter("D")
				.freeSpaceGB("25")
				.freeSpacePercent("25")
				.name("Test")
				.systemName("TestServer")
				.build();
	}

	void setUpCalcServerDetailsTest() {
		server = new Server();
		server.setRam(4);
		server.setCpuUsage(8.00);
		server.setName("ServerTest");

		volume = new Volume();
		volume.setName("VolumeTest");
		volume.setDesc("Some Volume");
		volume.setFullCapacity(50);
		volume.setLatestStorageFree(10);
		volume.setLatestStorageReserved(40);
		volume.setLatestStorageRatio(80);
		volume.setServer(server);

		volume2 = new Volume();
		volume2.setName("Volume2Test");
		volume2.setDesc("Some Volume2");
		volume2.setFullCapacity(100);
		volume2.setLatestStorageFree(50);
		volume2.setLatestStorageReserved(50);
		volume2.setLatestStorageRatio(50);
		volume2.setServer(server);

		volume3 = new Volume();
		volume3.setName("Volume3Test");
		volume3.setDesc("Some Volume3");
		volume3.setFullCapacity(150);
		volume3.setLatestStorageFree(50);
		volume3.setLatestStorageReserved(100);
		volume3.setLatestStorageRatio(66.67);
		volume3.setServer(server);

		serverRepository.save(server);
		volumeRepository.save(volume);
		volumeRepository.save(volume2);
		volumeRepository.save(volume3);

		Set<Volume> volumeList = new LinkedHashSet<>();
		volumeList.add(volume);
		volumeList.add(volume2);
		volumeList.add(volume3);
		server.setVolumes(volumeList);
	}

	void setUpGetServersTest() {
		server = new Server();
		server.setRam(4);
		server.setRamUsage(3.45);
		server.setCpuUsage(8.00);
		server.setName("ServerTest");

		serverHistory = new ServerHistory();
		serverHistory.setServer(server);
		serverHistory.setRam(server.getRam());
		serverHistory.setRamUsage(server.getRamUsage());
		serverHistory.setCpuUsage(server.getCpuUsage());

		Set<ServerHistory> serverHistories = new LinkedHashSet<>();
		serverHistories.add(serverHistory);
		server.setServerHistories(serverHistories);

		volume = new Volume();
		volume.setName("VolumeTest");
		volume.setDesc("Some Volume");
		volume.setFullCapacity(50);
		volume.setLatestStorageFree(10);
		volume.setLatestStorageReserved(40);
		volume.setLatestStorageRatio(80);
		volume.setServer(server);

		volume2 = new Volume();
		volume2.setName("Volume2Test");
		volume2.setDesc("Some Volume2");
		volume2.setFullCapacity(100);
		volume2.setLatestStorageFree(50);
		volume2.setLatestStorageReserved(50);
		volume2.setLatestStorageRatio(50);
		volume2.setServer(server);

		volume3 = new Volume();
		volume3.setName("Volume3Test");
		volume3.setDesc("Some Volume3");
		volume3.setFullCapacity(150);
		volume3.setLatestStorageFree(50);
		volume3.setLatestStorageReserved(100);
		volume3.setLatestStorageRatio(66.67);
		volume3.setServer(server);

		Set<Volume> volumeList = new LinkedHashSet<>();
		volumeList.add(volume);
		volumeList.add(volume2);
		volumeList.add(volume3);
		server.setVolumes(volumeList);

		serverRepository.save(server);
		volumeRepository.save(volume);
		volumeRepository.save(volume2);
		volumeRepository.save(volume3);

		server2 = new Server();
		server2.setName("TestServer2");
		server2.setCpuUsage(16.00);
		server2.setRam(4);
		server2.setRamUsage(3.78);

		ServerHistory serverHistory2 = new ServerHistory();
		serverHistory2.setServer(server2);
		serverHistory2.setRam(server2.getRam());
		serverHistory2.setRamUsage(server2.getRamUsage());
		serverHistory2.setCpuUsage(server2.getCpuUsage());

		Set<ServerHistory> serverHistories2 = new LinkedHashSet<>();
		server2.setServerHistories(serverHistories2);

		volume4 = new Volume();
		volume4.setName("Volume4Test");
		volume4.setDesc("Some Volume4");
		volume4.setFullCapacity(200);
		volume4.setLatestStorageFree(50);
		volume4.setLatestStorageReserved(150);
		volume4.setLatestStorageRatio(75);
		volume4.setServer(server2);

		volume5 = new Volume();
		volume5.setName("Volume5Test");
		volume5.setDesc("Some Volume5");
		volume5.setFullCapacity(150);
		volume5.setLatestStorageFree(25);
		volume5.setLatestStorageReserved(125);
		volume5.setLatestStorageRatio(83.33);
		volume5.setServer(server2);

		Set<Volume> volumeList2 = new LinkedHashSet<>();
		volumeList.add(volume4);
		volumeList.add(volume5);
		server2.setVolumes(volumeList2);

		serverRepository.save(server2);
		volumeRepository.save(volume4);
		volumeRepository.save(volume5);
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
		assertEquals(2, server.getVolumes().size());
		assertEquals(0, server.getServerHistories().size());
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
		assertEquals(0, serverHistoryRepository.count());
		assertEquals(0, serverHistories.size());
	}

	@Test
	void saveExistingServerAndExistingVolume() {
		setUpExistingServerAndExistingVolumeTest();
		service.saveVolumeDTO(updatedVolume);
		List<VolumeQueryDTO> volumes = service.getVolumes();
		VolumeQueryDTO volume = volumes.get(0);
		assertEquals(25, volume.getLatestStorageFree());
		assertEquals(75, volume.getLatestStorageReserved());
		assertEquals(3, volumeRepository.count());
		assertEquals(1, volumeHistoryRepository.count());
	}

	@Test
	void calcServerDetailsTest() {
		setUpCalcServerDetailsTest();
		ServerQueryDTO testServer = service.calcServerDetails(server).build();
		assertEquals(300, testServer.getFullCapacity());
		assertEquals(110, testServer.getLatestStorageFree());
		assertEquals(190, testServer.getLatestStorageReserved());
		assertEquals(36.666666666666664, testServer.getLatestStorageRatio());
	}

	@Test
	void getServersTest() {
		setUpGetServersTest();
		List<ServerQueryDTO> serverQueryDTO = service.getServers();
		assertEquals(2, serverQueryDTO.size());
		assertEquals(300, serverQueryDTO.get(0).getFullCapacity());
		assertEquals(server.getName(), serverQueryDTO.get(0).getName());
		assertEquals(server.getRam(), serverQueryDTO.get(0).getRam());
		assertEquals(server.getRamUsage(), serverQueryDTO.get(0).getRamUsage());
		assertEquals(server2.getName(), serverQueryDTO.get(1).getName());
		assertEquals(350, serverQueryDTO.get(1).getFullCapacity());
		assertEquals(275, serverQueryDTO.get(1).getLatestStorageReserved());
	}

	@Test
	void getVolumesTest() {
		setUpGetServersTest();
		List<VolumeQueryDTO> volumeList = service.getVolumes();
		assertEquals(5, volumeList.size());
		assertEquals("Volume5Test", volumeList.get(4).getName());
		assertEquals("VolumeTest", volumeList.get(0).getName());
	}

	@Test
	void getVolumesFromServerTest() {
		setUpGetServersTest();
		List<VolumeQueryDTO> volumeQueryDTOS = service.getVolumesFromServer("ServerTest");
		assertEquals(3, volumeQueryDTOS.size());
		assertEquals("VolumeTest", volumeQueryDTOS.get(0).getName());
		assertEquals("Volume3Test", volumeQueryDTOS.get(2).getName());
	}

	@AfterEach
	void tearDown() {
		serverRepository.deleteAll();
		volumeRepository.deleteAll();
		serverHistoryRepository.deleteAll();
		volumeHistoryRepository.deleteAll();
	}

}
