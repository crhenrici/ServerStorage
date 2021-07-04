package com.prose.crhen.SSKotlinServer.SSKotlinServer.dto

data class ServerUpdateDTO(
        val cpuUsage: CpuUsageDTO,
        val ram: RamDTO,
        val ramUsage: Double,
        val systemName: SystemNameDTO
)
