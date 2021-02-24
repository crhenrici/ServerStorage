package com.prose.crhen.SSServer.controller;

import com.google.common.base.Preconditions;
import com.prose.crhen.SSServer.business.api.ServerService;
import com.prose.crhen.SSServer.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ServerController {

	private static final Logger logger = LoggerFactory.getLogger(ServerController.class);

	@Autowired
	private ServerService service;

	@RequestMapping({"","/servers"})
	public String forwardToClient() {
		return "forward:/index.html";
	}

	@GetMapping("/service/overview")
	@ResponseBody
	public ServerOverviewDTO getServerOverview() {
		ServerOverviewDTO serverOverviewDTO = service.getServerOverviewDTO();
		return  serverOverviewDTO;
	}

	@GetMapping("/service/servers")
	@ResponseBody
	public List<ServerQueryDTO> getAllServers() {
		List<ServerQueryDTO> serverList = service.getServers();
		return serverList;
	}

	@GetMapping("/service/volumes")
	@ResponseBody
	public List<VolumeQueryDTO> getAllVolumesFromServer(@RequestParam String serverName) {
		List<VolumeQueryDTO> volumeList = service.getVolumesFromServer(serverName);
		return volumeList;
	}
	
	@PostMapping("/service/save/volume")
	public ResponseEntity<String> saveVolumes(@RequestBody List<VolumesUpdateDTO> volumes) {
		Preconditions.checkNotNull(volumes, "value can't be null");
		logger.info("Received volume data: " + volumes.toString());
        for (VolumesUpdateDTO volume : volumes) {
        	logger.info("Saving volume: " + volume.toString());
        	service.saveVolumeDTO(volume);
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping("/service/update/volume")
	public ResponseEntity<String> updateVolume(@RequestBody VolumeQueryDTO volume) {
		Preconditions.checkNotNull(volume, "value can't be null");
		logger.info("Request received");
		logger.info("Received volume data: " + volume.toString());
		service.updateVolumeQueryDTO(volume);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping("/service/save/server")
	public ResponseEntity<String> saveServers(@RequestBody ServerUpdateDTO server) {
		Preconditions.checkNotNull(server, "value can't be null");
		logger.info("Data received");
		logger.info("Received server data: " + server.toString());
		service.saveServerDTO(server);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
