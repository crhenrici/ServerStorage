package com.prose.crhen.SSServer.db;

import com.prose.crhen.SSServer.model.Server;
import com.prose.crhen.SSServer.model.Volume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class VolumeRepositoryTest {

    @Autowired
    TestEntityManager manager;

    @Autowired
    ServerRepository serverRepository;

    @Autowired
    VolumeRepository volumeRepository;

    @BeforeEach
    void setUp() {
        Server server = new Server("CHWISRV01", 100, 50, 50, 50.00, 4, 28.25);
        serverRepository.save(server);
    }

    @Test
    void testPersistVolume() {
        Server result = serverRepository.findByName("CHWISRV01");
        assertNotNull(result);
        Volume volume = new Volume("Test", "Some test", 50, 50, 50, result);
        manager.persist(volume);
        manager.flush();
        List<Volume> volumeList = volumeRepository.findByServer(result);
        Volume expected = volumeList.get(0);
        assertEquals(expected, volume);
        Server expectedServer = expected.getServer();
        assertEquals(expectedServer,result);

    }
}