package com.prose.crhen.SSKotlinServer.SSKotlinServer.db

import com.prose.crhen.SSKotlinServer.SSKotlinServer.model.Volume
import com.prose.crhen.SSKotlinServer.SSKotlinServer.model.VolumeHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VolumeHistoryRepository: JpaRepository<VolumeHistory, Long> {
    fun findByVolume(volume: Volume): List<VolumeHistory>
}
