package com.prose.crhen.SSServer.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.prose.crhen.SSServer.SsServerApplication;
import com.prose.crhen.SSServer.db.ServerRepository;
import com.prose.crhen.SSServer.dto.VolumesUpdateDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class ServerControllerTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private ServerRepository serverRepository;

    @Test
    void saveTest() throws Exception {
        FileSystemResourceLoader rl = new FileSystemResourceLoader();
        Resource resource = rl.getResource("classpath:process.txt");
        ObjectMapper mapper = new ObjectMapper();
        List<VolumesUpdateDTO> result = mapper.readValue(resource.getFile(), new TypeReference<List<VolumesUpdateDTO>>() {});
        System.out.println(result.toString());
        assertEquals(5, result.size());
        VolumesUpdateDTO o = result.get(0);
        o.getSystemName().equals("CHWIWS08");

        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort + "/service/save";
        restTemplate.postForLocation(baseUrl, result);
    }

}