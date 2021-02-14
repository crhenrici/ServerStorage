package com.prose.crhen.SSServer.business;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;

@Configuration
@ComponentScan(basePackages = "com.prose.crhen.SSServer")
@TestPropertySource(locations = "classpath:application-dev.properties")
public class TestConfig {
}
