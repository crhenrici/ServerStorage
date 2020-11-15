package com.prose.crhen.SSServer.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prose.crhen.SSServer.db.ServerRepository;
import com.prose.crhen.SSServer.dto.ServerUpdateDTO;
import com.prose.crhen.SSServer.dto.VolumesUpdateDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class ServerControllerTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private ServerRepository serverRepository;

    @Test
    void saveVolumeTest() throws Exception {
        FileSystemResourceLoader rl = new FileSystemResourceLoader();
        Resource resource = rl.getResource("classpath:process.txt");
        ObjectMapper mapper = new ObjectMapper();
        List<VolumesUpdateDTO> result = mapper.readValue(resource.getFile(), new TypeReference<List<VolumesUpdateDTO>>() {});
        System.out.println(result.toString());
        assertEquals(5, result.size());
        VolumesUpdateDTO o = result.get(0);
        o.getSystemName().equals("CHWIWS08");

        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort + "/service/save/volume";
        restTemplate.postForLocation(baseUrl, result);
    }

    void saveServerTest() throws Exception {
        FileSystemResourceLoader rl = new FileSystemResourceLoader();
        Resource resource = rl.getResource("classpath:serverProcess.txt");
        ObjectMapper mapper = new ObjectMapper();
        ServerUpdateDTO result = mapper.readValue(resource.getFile(), new TypeReference<ServerUpdateDTO>() {});
        System.out.println(result.toString());
        assertEquals(8, result.getRam().getCapacity());
        assertEquals(1.4561652284152626, result.getCpuUsage().getCookedValue());

        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort + "/service/save/server";
        restTemplate.postForLocation(baseUrl, result);
    }

    @AfterEach
    void tearDown() {
        serverRepository.deleteAll();
    }

}