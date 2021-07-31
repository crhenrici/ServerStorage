package com.prose.crhen.SSKotlinServer.SSKotlinServer.db

import com.prose.crhen.SSKotlinServer.SSKotlinServer.model.Server
import com.prose.crhen.SSKotlinServer.SSKotlinServer.model.Volume
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VolumeRepository: JpaRepository<Volume, Long> {
    fun findByName(name: String): Volume

    fun findByServer(server: Server): List<Volume>

    fun findVolumeByNameAndServer(name: String, server: Server): Volume
}
