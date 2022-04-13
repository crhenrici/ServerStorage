package com.prose.crhen.SSKotlinServer.SSKotlinServer.db

import com.prose.crhen.SSKotlinServer.SSKotlinServer.model.Server
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ServerRepository: JpaRepository<Server, Long> {

    fun findByName(name: String): Server
}
