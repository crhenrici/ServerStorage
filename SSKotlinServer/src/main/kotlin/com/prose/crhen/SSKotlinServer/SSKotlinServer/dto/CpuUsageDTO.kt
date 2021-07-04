package com.prose.crhen.SSKotlinServer.SSKotlinServer.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class CpuUsageDTO(
        val path: String,
        val instanceName: String,
        val cookedValue: Double,
        val rawValue: Long,
        val secondValue: Long,
        val multipleCount: Int,
        val counterType: Long,
        val timestamp: String,
        val timestamp100NSec: Long,
        val status: Int,
        val defaultScale: Int,
        val timeBase: Long
)
