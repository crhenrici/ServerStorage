package com.prose.crhen.SSServer.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prose.crhen.SSServer.model.Server;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {

}
