package com.prose.crhen.SSServer.controller;

import com.google.common.base.Preconditions;
import com.prose.crhen.SSServer.business.ServerService;
import com.prose.crhen.SSServer.dto.*;
import com.prose.crhen.SSServer.model.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "service")
public class ServerController {


	@Autowired
	private ServerService service;

	@GetMapping("/overview")
	@ResponseBody
	public ServerOverviewDTO getServerOverview() {
		ServerOverviewDTO serverOverviewDTO = service.getServerOverviewDTO();
		return  serverOverviewDTO;
	}

	@GetMapping("/servers")
	@ResponseBody
	public List<ServerQueryDTO> getAllVolumes() {
		List<ServerQueryDTO> serverList = service.getServers();
		return serverList;
	}
	
	@PostMapping("/save/volume")
	public ResponseEntity<String> saveVolumes(@RequestBody List<VolumesUpdateDTO> volumes) {
		Preconditions.checkNotNull(volumes, "value can't be null");
		System.out.println(volumes.toString());
        for (VolumesUpdateDTO volume : volumes) {
        	service.saveVolumeDTO(volume);
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping("/save/server")
	public ResponseEntity<String> saveServers(@RequestBody ServerUpdateDTO server) {
		Preconditions.checkNotNull(server, "value can't be null");
		service.saveServerDTO(server);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
