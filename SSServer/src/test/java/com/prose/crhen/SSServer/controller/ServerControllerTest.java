package com.prose.crhen.SSServer.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.prose.crhen.SSServer.SsServerApplication;
import com.prose.crhen.SSServer.dto.VolumesUpdateDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@WebMvcTest(ServerController.class)
class ServerControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(result);

        mockMvc.perform(post("/service/save").contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)).andExpect(MockMvcResultMatchers.status().isOk());
    }

}