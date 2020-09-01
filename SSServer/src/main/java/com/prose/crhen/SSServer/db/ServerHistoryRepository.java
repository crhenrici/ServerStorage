package com.prose.crhen.SSServer.db;

import com.prose.crhen.SSServer.model.Server;
import com.prose.crhen.SSServer.model.ServerHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerHistoryRepository extends JpaRepository<ServerHistory, Long> {

    public List<ServerHistory> findByServer(Server server);
}
