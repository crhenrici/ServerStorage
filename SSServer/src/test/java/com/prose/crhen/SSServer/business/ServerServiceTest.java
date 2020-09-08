package com.prose.crhen.SSServer.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.prose.crhen.SSServer.dto.VolumesUpdateDTO;

public class ServerServiceTest {
	
	@Autowired
	ServerService service;
	
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
	}

}
