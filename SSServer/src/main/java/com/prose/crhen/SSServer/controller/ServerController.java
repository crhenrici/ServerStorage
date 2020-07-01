package com.prose.crhen.SSServer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;
import com.prose.crhen.SSServer.db.ServerRepository;
import com.prose.crhen.SSServer.model.Server;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ServerController {
	
	ServerRepository repository;
	
	public ServerController(ServerRepository repository) {
		this.repository = Preconditions.checkNotNull(repository, "no valid repository");
		Server server = new Server(1, "CHWISRV1", 100, 50, 50, 50.00);
		repository.save(server);
	}
	
	@GetMapping("/servers")
	@ResponseBody
	public List<Server> getAllServer() {
		List<Server> serverList = (List<Server>) repository.findAll();
		return serverList;
	}
	
	@PostMapping()
	public Server saveServer(@RequestBody Server server) {
		Server newServer = Preconditions.checkNotNull(repository.save(server), "no valid server");
		return newServer;
	}
	
	@PutMapping()
	public Optional<Server> updateServer(@RequestBody Server newServer) {
		long id = Preconditions.checkNotNull(newServer.getId(), "no valid server");
		return repository.findById(id).map(server -> {
			server.setName(newServer.getName());
			server.setStorageReserved(newServer.getStorageReserved());
			server.setStorageFree(newServer.getStorageFree());
			server.setStorageRatio(newServer.getStorageRatio());
			return repository.save(server);
		});
	}

}
