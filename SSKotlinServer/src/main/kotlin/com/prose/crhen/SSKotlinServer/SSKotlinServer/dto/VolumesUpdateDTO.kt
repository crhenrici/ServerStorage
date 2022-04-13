package com.prose.crhen.SSKotlinServer.SSKotlinServer.dto

data class VolumesUpdateDTO(
        val systemName: String,
        val name: String,
        val driveLetter: String,
        val capacityGB: String,
        val freeSpaceGB: String,
        val freeSpacePercent: String = "0",
        val date: String
)
