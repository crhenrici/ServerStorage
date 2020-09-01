package com.prose.crhen.SSServer.db;

import com.prose.crhen.SSServer.model.Server;
import com.prose.crhen.SSServer.model.ServerHistory;
import org.junit.jupiter.api.AfterEach;
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
class ServerHistoryRepositoryTest {

    @Autowired
    TestEntityManager manager;

    @Autowired
    ServerHistoryRepository repository;

    @Autowired
    ServerRepository serverRepository;

    @BeforeEach
    void setUp() {
        Server server = new Server("CHWISRV01", 100, 50, 50, 50.00, 4, 28.25);
        serverRepository.save(server);
    }

    @Test
    void testPersistServerHistory() {
        Server result = serverRepository.findByName("CHWISRV01");
        assertNotNull(result);
        ServerHistory history = new ServerHistory(50, 50, 50, result);
        manager.persist(history);
        manager.flush();
        List<ServerHistory> historyList = repository.findByServer(result);
        Server expected = historyList.get(0).getServer();
        assertEquals(expected, result);

    }

    @AfterEach
    void tearDown() {
        manager.clear();
    }
}