package com.prose.crhen.SSKotlinServer.SSKotlinServer.dto

import com.prose.crhen.SSKotlinServer.SSKotlinServer.model.ServerHistory
import com.prose.crhen.SSKotlinServer.SSKotlinServer.model.Volume

data class ServerQueryDTO(
        val name: String,
        val networkDesc: String?,
        val fullCapacity: Double,
        val latestStorageReserved: Double,
        val latestStorageFree: Double,
        val latestStorageRatio: Double,
        val ram: Int,
        val ramUsage: Double,
        val cpuUsage: Double,
        val volumes: Set<Volume>?,
        val serverHistories: Set<ServerHistory>?
)
