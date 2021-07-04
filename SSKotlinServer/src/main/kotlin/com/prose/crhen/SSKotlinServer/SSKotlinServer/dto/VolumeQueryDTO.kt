package com.prose.crhen.SSKotlinServer.SSKotlinServer.dto

import com.prose.crhen.SSKotlinServer.SSKotlinServer.model.Server
import com.prose.crhen.SSKotlinServer.SSKotlinServer.model.VolumeHistory
import java.util.*

data class VolumeQueryDTO(
        val name: String,
        val desc: String,
        val date: Date,
        val fullCapacity: Double,
        val latestStorageReserved: Double,
        val latestStorageFree: Double,
        val latestStorageRatio: Double,
        val mappingLabel: String,
        val server: Server,
        val volumeHistories: Set<VolumeHistory>
)
