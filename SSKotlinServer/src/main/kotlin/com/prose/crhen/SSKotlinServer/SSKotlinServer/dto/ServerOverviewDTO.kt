package com.prose.crhen.SSKotlinServer.SSKotlinServer.dto

data class ServerOverviewDTO(
        val numberOfServer: Int,
        val totalCapacity: Double,
        val totalCapacityUsed: Double,
        val totalCapacityFree: Double,
        val totalUsedCapacityRatio: Double
)
