package com.prose.crhen.SSServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableScheduling
public class SsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsServerApplication.class, args);
	}

}
