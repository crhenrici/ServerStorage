package com.prose.crhen.SSServer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prose.crhen.SSServer.business.ServerService;
import com.prose.crhen.SSServer.dto.ServerUpdateDTO;
import com.prose.crhen.SSServer.dto.VolumesUpdateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("mockdata")
public class MockDataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MockDataInitializer.class);

    @Autowired
    private ServerService service;

    public MockDataInitializer() {
    }

    public void run(String... args) throws Exception {
        service.deleteAll();
        logger.info("I am here");
        FileSystemResourceLoader rl = new FileSystemResourceLoader();

        Resource resource = rl.getResource("classpath:testdata/serverProcess.txt");
        ObjectMapper mapper = new ObjectMapper();
        ServerUpdateDTO serverResult = mapper.readValue(resource.getFile(), new TypeReference<ServerUpdateDTO>() {});
        service.saveServerDTO(serverResult);

        resource = rl.getResource("classpath:testdata/volumeProcess.txt");
        List<VolumesUpdateDTO> result = mapper.readValue(resource.getFile(), new TypeReference<List<VolumesUpdateDTO>>() {});
        for (VolumesUpdateDTO volumesUpdateDTO : result) {
            service.saveVolumeDTO(volumesUpdateDTO);
        }

        resource = rl.getResource("classpath:testdata/serverProcess1.txt");
        ServerUpdateDTO serverResult2 =  mapper.readValue(resource.getFile(), new TypeReference<ServerUpdateDTO>() {});
        service.saveServerDTO(serverResult2);

        resource = rl.getResource("classpath:testdata/serverProcess2.txt");
        ServerUpdateDTO serverResult3 = mapper.readValue(resource.getFile(), new TypeReference<ServerUpdateDTO>() {});
        service.saveServerDTO(serverResult3);

        resource = rl.getResource("classpath:testdata/volumeProcess1.txt");
        List<VolumesUpdateDTO> volumeResults = mapper.readValue(resource.getFile(), new TypeReference<List<VolumesUpdateDTO>>() {});
        for (VolumesUpdateDTO volumesUpdateDTO : volumeResults) {
            service.saveVolumeDTO(volumesUpdateDTO);
        }
    }
}
