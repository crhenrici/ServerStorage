package com.prose.crhen.SSServer.db;

import com.prose.crhen.SSServer.model.Server;
import com.prose.crhen.SSServer.model.Volume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolumeRepository extends JpaRepository<Volume, Long> {

    Volume findByName(String name);

    List<Volume> findByServer(Server server);
}
