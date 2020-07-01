package com.prose.crhen.SSServer.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.prose.crhen.SSServer.model.Server;

@Repository
public interface ServerRepository extends CrudRepository<Server, Long> {

}
