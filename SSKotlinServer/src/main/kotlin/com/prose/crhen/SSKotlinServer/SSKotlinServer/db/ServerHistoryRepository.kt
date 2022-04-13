package com.prose.crhen.SSKotlinServer.SSKotlinServer.db

import com.prose.crhen.SSKotlinServer.SSKotlinServer.model.Server
import com.prose.crhen.SSKotlinServer.SSKotlinServer.model.ServerHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ServerHistoryRepository: JpaRepository<ServerHistory, Long> {
    fun findByServer(server: Server): List<ServerHistory>
}
