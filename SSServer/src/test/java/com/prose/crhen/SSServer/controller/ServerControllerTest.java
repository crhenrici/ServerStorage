package com.prose.crhen.SSServer.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prose.crhen.SSServer.db.ServerHistoryRepository;
import com.prose.crhen.SSServer.db.ServerRepository;
import com.prose.crhen.SSServer.db.VolumeHistoryRepository;
import com.prose.crhen.SSServer.db.VolumeRepository;
import com.prose.crhen.SSServer.dto.ServerUpdateDTO;
import com.prose.crhen.SSServer.dto.VolumesUpdateDTO;
import com.prose.crhen.SSServer.model.Server;
import com.prose.crhen.SSServer.model.ServerHistory;
import com.prose.crhen.SSServer.model.Volume;
import com.prose.crhen.SSServer.model.VolumeHistory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    private VolumeRepository volumeRepository;

    @Autowired
    private ServerHistoryRepository serverHistoryRepository;

    @Autowired
    private VolumeHistoryRepository volumeHistoryRepository;

    @BeforeEach
    void setUp() {
        serverRepository.deleteAll();
        volumeRepository.deleteAll();
    }

    @Test
    void saveVolumeTest() throws Exception {
        FileSystemResourceLoader rl = new FileSystemResourceLoader();
        Resource resource = rl.getResource("classpath:volumeProcess.txt");
        ObjectMapper mapper = new ObjectMapper();
        List<VolumesUpdateDTO> result = mapper.readValue(resource.getFile(), new TypeReference<List<VolumesUpdateDTO>>() {});
        System.out.println(result.toString());
        assertEquals(5, result.size());
        VolumesUpdateDTO o = result.get(0);
        o.getSystemName().equals("CHWIWS08");

        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort + "/service/save/volume";
        restTemplate.postForLocation(baseUrl, result);

        Server resultServer = serverRepository.findByName("CHWIWS08");
        Volume resultVolume = volumeRepository.findByName(o.getName());

        assertEquals(o.getSystemName(), resultServer.getName());
        assertEquals(o.getName(), resultVolume.getName());
        assertEquals(Double.parseDouble(o.getCapacityGB()), resultVolume.getFullCapacity());
        assertEquals(Double.parseDouble(o.getFreeSpaceGB()), resultVolume.getLatestStorageFree());
        assertEquals(Double.parseDouble(o.getFreeSpacePercent()), resultVolume.getLatestStorageRatio());
    }

    @Test
    void saveVolumeTestWithHistory() throws Exception {
        FileSystemResourceLoader rl = new FileSystemResourceLoader();
        Resource resource = rl.getResource("classpath:volumeProcess.txt");
        ObjectMapper mapper = new ObjectMapper();
        List<VolumesUpdateDTO> result = mapper.readValue(resource.getFile(), new TypeReference<List<VolumesUpdateDTO>>() {});
        System.out.println(result.toString());
        assertEquals(5, result.size());
        VolumesUpdateDTO o = result.get(0);
        o.getSystemName().equals("CHWIWS08");

        resource = rl.getResource("classpath:volumeProcess1.txt");
        List<VolumesUpdateDTO> result2 = mapper.readValue(resource.getFile(), new TypeReference<List<VolumesUpdateDTO>>() {});
        assertEquals(5, result2.size());
        VolumesUpdateDTO d = result2.get(3);
        d.getSystemName().equals("CHWIWS09");

        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort + "/service/save/volume";
        restTemplate.postForLocation(baseUrl, result);
        restTemplate.postForLocation(baseUrl, result2);

        Server resultServer = serverRepository.findByName("CHWIWS09");
        Volume resultVolume = volumeRepository.findByName(d.getName());

        System.out.println(d.toString());
        assertEquals(d.getSystemName(), resultServer.getName());
        assertEquals(d.getName(), resultVolume.getName());
        assertEquals(Double.parseDouble(d.getCapacityGB()), resultVolume.getFullCapacity());
        assertEquals(Double.parseDouble(d.getFreeSpaceGB()), resultVolume.getLatestStorageFree());
        assertEquals(Double.parseDouble(d.getFreeSpacePercent()), resultVolume.getLatestStorageRatio());

        List<VolumeHistory> volumeHistories = volumeHistoryRepository.findAll();

        assertEquals(3, volumeHistories.size());
        VolumeHistory volumeHistory = volumeHistories.get(0);
        assertEquals(Double.parseDouble(o.getFreeSpaceGB()), volumeHistory.getStorageFree());
        assertEquals(Double.parseDouble(o.getFreeSpacePercent()), volumeHistory.getStorageRatio());
    }

    @Test
    void saveServerTest() throws Exception {
        FileSystemResourceLoader rl = new FileSystemResourceLoader();
        Resource resource = rl.getResource("classpath:serverProcess.txt");
        ObjectMapper mapper = new ObjectMapper();
        ServerUpdateDTO result = mapper.readValue(resource.getFile(), new TypeReference<ServerUpdateDTO>() {});
        System.out.println(result.toString());
        assertEquals(8, result.getRam().getCapacity());
        assertEquals(0.067164858498602875, result.getCpuUsage().getCookedValue());

        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort + "/service/save/server";
        restTemplate.postForLocation(baseUrl, result);

        Server resultServer = serverRepository.findByName("NUCWIN");

        assertEquals(result.getSystemName(), resultServer.getName());
        assertEquals(result.getRam().getCapacity(), resultServer.getRam());
        assertEquals(result.getRamUsage(), resultServer.getRamUsage());
        assertEquals(result.getCpuUsage().getCookedValue(), resultServer.getCpuUsage());
    }

    @Test
    void saveServerWithHistory() throws Exception {
        FileSystemResourceLoader rl = new FileSystemResourceLoader();
        Resource resource = rl.getResource("classpath:serverProcess.txt");
        ObjectMapper mapper = new ObjectMapper();
        ServerUpdateDTO result = mapper.readValue(resource.getFile(), new TypeReference<ServerUpdateDTO>() {});
        System.out.println(result.toString());
        assertEquals(8, result.getRam().getCapacity());
        assertEquals(0.067164858498602875, result.getCpuUsage().getCookedValue());

        resource = rl.getResource("classpath:serverProcess1.txt");
        ServerUpdateDTO result2 = mapper.readValue(resource.getFile(), new TypeReference<ServerUpdateDTO>() {});
        assertEquals(9, result2.getRam().getCapacity());
        assertEquals(0.167164858498602875, result2.getCpuUsage().getCookedValue());

        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort + "/service/save/server";
        restTemplate.postForLocation(baseUrl, result);
        restTemplate.postForLocation(baseUrl, result2);

        Server resultServer = serverRepository.findByName("NUCWIN");

        assertEquals(result2.getSystemName(), resultServer.getName());
        assertEquals(result2.getCpuUsage().getCookedValue(), resultServer.getCpuUsage());
        assertEquals(result2.getRam().getCapacity(), resultServer.getRam());

        List<ServerHistory> serverHistories = serverHistoryRepository.findAll();

        assertEquals(1, serverHistories.size());

        ServerHistory serverHistory = serverHistories.get(0);
        System.out.println(serverHistory.getCpuUsage());
        System.out.println(serverHistory.getRam());
        assertEquals(result.getRam().getCapacity(), serverHistory.getRam());
        assertEquals(result.getRamUsage(), serverHistory.getRamUsage());
    }

    @AfterEach
    void tearDown() {
        serverRepository.deleteAll();
        volumeRepository.deleteAll();
    }

}