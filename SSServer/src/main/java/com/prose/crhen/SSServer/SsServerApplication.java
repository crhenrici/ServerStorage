package com.prose.crhen.SSServer;

import com.prose.crhen.SSServer.db.ServerRepository;
import com.prose.crhen.SSServer.model.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
public class SsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsServerApplication.class, args);
	}

}
