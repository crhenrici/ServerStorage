package com.prose.crhen.SSServer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prose.crhen.SSServer.business.api.ServerService;
import com.prose.crhen.SSServer.dto.ServerUpdateDTO;
import com.prose.crhen.SSServer.dto.VolumesUpdateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${test.data.location}")
    private String testDataLocation;

    @Autowired
    private ServerService service;

    public MockDataInitializer() {
    }

    public void run(String... args) throws Exception {
        service.deleteAll();
        logger.info( String.format("Loading Testdata from: %s", testDataLocation));
        loadServerIntoDB(testDataLocation+"serverProcess.txt");
        loadVolumesIntoDB(testDataLocation+"volumeProcess.txt");
        loadServerIntoDB(testDataLocation+"serverProcess1.txt");
        loadServerIntoDB(testDataLocation+"serverProcess2.txt");
        loadVolumesIntoDB(testDataLocation+"volumeProcess1.txt");
        loadServerIntoDB(testDataLocation+"serverProcess3.txt");
        loadVolumesIntoDB(testDataLocation+"volumeProcess2.txt");
        loadVolumesIntoDB(testDataLocation+"volumeProcess3.txt");
        loadVolumesIntoDB(testDataLocation+"volumeProcess4.txt");
        loadVolumesIntoDB(testDataLocation+"volumeProcess5.txt");
        logger.info( String.format("Loading Testdata from: %s done", testDataLocation));
    }

    private void loadServerIntoDB(String classpath) throws Exception{
        FileSystemResourceLoader rl = new FileSystemResourceLoader();
        Resource resource = rl.getResource(classpath);
        ObjectMapper mapper = new ObjectMapper();
        ServerUpdateDTO serverResult = mapper.readValue(resource.getFile(), new TypeReference<ServerUpdateDTO>() {});
        service.saveServerDTO(serverResult);
    }

    private void loadVolumesIntoDB(String classpath) throws Exception {
        FileSystemResourceLoader rl = new FileSystemResourceLoader();
        Resource resource = rl.getResource(classpath);
        ObjectMapper mapper = new ObjectMapper();
        List<VolumesUpdateDTO> result = mapper.readValue(resource.getFile(), new TypeReference<List<VolumesUpdateDTO>>() {});
        for (VolumesUpdateDTO volumesUpdateDTO : result) {
            service.saveVolumeDTO(volumesUpdateDTO);
        }
    }
}
