package com.prose.crhen.SSServer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;
import com.prose.crhen.SSServer.db.ServerRepository;
import com.prose.crhen.SSServer.model.Server;

@RestController
public class ServerController {
	
	ServerRepository repository;
	
	public ServerController(ServerRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping()
	public List<Server> getAllServer() {
		List<Server> serverList = repository.findAll();
		return serverList;
	}
	
	@PostMapping()
	public Server saveServer(@RequestBody Server server) {
		Server newServer = Preconditions.checkNotNull(repository.save(server));
		return newServer;
	}
	
	public Optional<Server> updateServer(@RequestBody Server newServer) {
		long id = Preconditions.checkNotNull(newServer.getId());
		return repository.findById(id).map(server -> {
			server.setName(newServer.getName());
			server.setStorageReserved(newServer.getStorageReserved());
			server.setStorageFree(newServer.getStorageFree());
			server.setStorageRatio(newServer.getStorageRatio());
			return repository.save(server);
		});
	}

}
