package com.prose.crhen.SSServer.controller;

import java.util.List;

import com.prose.crhen.SSServer.business.ServerService;
import com.prose.crhen.SSServer.dto.VolumesUpdateDTO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.google.common.base.Preconditions;
import com.prose.crhen.SSServer.db.ServerRepository;
import com.prose.crhen.SSServer.model.Server;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "service")
public class ServerController {

	@Autowired(required = false)
	ServerRepository repository;

	@Autowired(required = false)
	ServerService service;
	
	public ServerController() {

	}
	
	@GetMapping("/servers")
	@ResponseBody
	public List<Server> getAllServer() {
		List<Server> serverList = (List<Server>) repository.findAll();
		return serverList;
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> saveServer(@RequestBody List<VolumesUpdateDTO> volumes) {
		Preconditions.checkNotNull(volumes, "value can't be null");
		System.out.println(volumes.toString());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
