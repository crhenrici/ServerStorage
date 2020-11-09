package com.prose.crhen.SSServer.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prose.crhen.SSServer.dto.ServerUpdateDTO;
import com.prose.crhen.SSServer.dto.VolumesUpdateDTO;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class JSONTest {

    @Test
    void testProcessData() throws IOException {
        FileSystemResourceLoader rl = new FileSystemResourceLoader();
        Resource resource = rl.getResource("classpath:process.txt");
        ObjectMapper mapper = new ObjectMapper();
        List result = mapper.readValue(resource.getFile(), List.class);
        System.out.println(result.toString());
        assertEquals(5, result.size());
        Map<String, Object> o = (Map<String, Object>) result.get(0);
        o.get("SystemName").equals("CHWIWS08");
    }

    @Test
    void testProcessDataWithObject() throws IOException {
        FileSystemResourceLoader rl = new FileSystemResourceLoader();
        Resource resource = rl.getResource("classpath:process.txt");
        ObjectMapper mapper = new ObjectMapper();
        List<VolumesUpdateDTO> result = mapper.readValue(resource.getFile(), new TypeReference<List<VolumesUpdateDTO>>() {});
        System.out.println(result.toString());
        assertEquals(5, result.size());
        VolumesUpdateDTO o = result.get(0);
        o.getSystemName().equals("CHWIWS08");
    }

    @Test
    void testProcessDataWithObjectServer() throws IOException {
        FileSystemResourceLoader rl = new FileSystemResourceLoader();
        Resource resource = rl.getResource("classpath:serverProcess.txt");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(resource.getFile());
        ServerUpdateDTO result = new ServerUpdateDTO();
        result.setRam(node.get("ram").get("Capacity").asInt());
        System.out.println(result.toString());
        assertEquals(8, result.getRam());
    }
}
