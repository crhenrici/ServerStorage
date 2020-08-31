package com.prose.crhen.SSServer.db;

import com.prose.crhen.SSServer.model.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ServerRepositoryTest {

    @Autowired
    TestEntityManager manager;

    @Autowired
    ServerRepository repository;

    @BeforeEach
    void setUp() {
        Server server = new Server("CHWISRV01", 100, 50, 50, 50.00, 4, 28.25);
        Server server2 = new Server("CHWISRV02", 200, 150, 50, 75.00, 8, 15.00);
        Server server3 = new Server("CHWISRV03", 300, 100, 200, 33.33, 16, 12.66);
        repository.save(server);
        repository.save(server2);
        repository.save(server3);
    }

    @Test
    void testQueryServer() {
        List<Server> serverList = (List<Server>) repository.findAll();
        assertEquals(serverList.size(), 3);
        Optional<Server> found = repository.findById(serverList.get(0).getId());
        assertEquals(serverList.get(0), found.get());
    }

    @Test
    void testPersistServer() {
        Server server = new Server("CHWISRV04", 150, 70, 80, 50.00, 12, 28.25);
        manager.persist(server);
        manager.flush();
        Server found = repository.findByName(server.getName());
        assertSame(found, server);
        List<Server> serverList = (List<Server>) repository.findAll();
        assertEquals(serverList.size(), 4);
    }

    @AfterEach
    void tearDown() {
    }
}