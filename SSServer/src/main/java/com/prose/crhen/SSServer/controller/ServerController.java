package com.prose.crhen.SSServer.controller;

import java.util.List;
import java.util.Optional;

import com.prose.crhen.SSServer.business.ServerService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.google.common.base.Preconditions;
import com.prose.crhen.SSServer.db.ServerRepository;
import com.prose.crhen.SSServer.model.Server;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ServerController implements InitializingBean {

	@Autowired
	ServerRepository repository;

	@Autowired
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
	@ResponseBody
	public Server saveServer(@RequestBody Server server) {
		Preconditions.checkNotNull(server, "value can't be null");
		double storageRatio = service.calculateStorageRatio(server.getFullCapacity(), server.getStorageReserved());
		server.setStorageRatio(storageRatio);
		Server newServer = repository.save(server);
		return newServer;
	}
	
	@PutMapping("update")
	@ResponseBody
	public Optional<Server> updateServer(@RequestBody Server newServer) {
		long id = Preconditions.checkNotNull(newServer.getId(), "no valid server");
		double storageRatio = service.calculateStorageRatio(newServer.getFullCapacity(), newServer.getStorageReserved());
		newServer.setStorageRatio(storageRatio);
		return repository.findById(id).map(server -> {
			server.setName(newServer.getName());
			server.setStorageReserved(newServer.getStorageReserved());
			server.setStorageFree(newServer.getStorageFree());
			server.setStorageRatio(newServer.getStorageRatio());
			server.setRam(newServer.getRam());
			server.setCpuUsage(newServer.getCpuUsage());
			return repository.save(server);
		});
	}

	//a temporary solution
	@Override
	public void afterPropertiesSet() throws Exception {
		this.repository = Preconditions.checkNotNull(repository, "no valid repository");
		Server server = new Server("CHWISRV01", 100, 50, 50, 50.00, 4, 28.25);
		Server server2 = new Server("CHWISRV02", 200, 150, 50, 75.00, 8, 15.00);
		Server server3 = new Server("CHWISRV03", 300, 100, 200, 33.33, 16, 12.66);
		repository.save(server);
		repository.save(server2);
		repository.save(server3);
	}
}
