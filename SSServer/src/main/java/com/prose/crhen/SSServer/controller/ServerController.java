package com.prose.crhen.SSServer.controller;

import com.google.common.base.Preconditions;
import com.prose.crhen.SSServer.business.ServerService;
import com.prose.crhen.SSServer.dto.VolumesUpdateDTO;
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

	@GetMapping("/servers")
	@ResponseBody
	public List<Server> getAllServer() {
		List<Server> serverList = service.getServers();
		return serverList;
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> saveServer(@RequestBody List<VolumesUpdateDTO> volumes) {
		Preconditions.checkNotNull(volumes, "value can't be null");
		System.out.println(volumes.toString());
        for (VolumesUpdateDTO volume : volumes) {
        	service.save(volume);
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
