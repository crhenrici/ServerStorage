package com.prose.crhen.SSServer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.google.common.base.Preconditions;
import com.prose.crhen.SSServer.db.ServerRepository;
import com.prose.crhen.SSServer.model.Server;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ServerController {

	ServerRepository repository;
	
	public ServerController(ServerRepository repository) {
		this.repository = Preconditions.checkNotNull(repository, "no valid repository");
		Server server = new Server(1, "CHWISRV01", 100, 50, 50, 50.00);
		Server server2 = new Server(2, "CHWISRV02", 200, 150, 50, 75.00);
		Server server3 = new Server(3, "CHWISRV03", 300, 100, 200, 33.33);
		repository.save(server);
		repository.save(server2);
		repository.save(server3);
	}
	
	@GetMapping("/servers")
	@ResponseBody
	public List<Server> getAllServer() {
		List<Server> serverList = (List<Server>) repository.findAll();
		return serverList;
	}
	
	@PostMapping("/save")
	@ResponseBody
	public Server saveServer(@RequestBody Server server) {
		Server newServer = Preconditions.checkNotNull(repository.save(server), "no valid server");
		return newServer;
	}
	
	@PutMapping("update")
	@ResponseBody
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
