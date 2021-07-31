package com.prose.crhen.SSKotlinServer.SSKotlinServer.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "volume_history")
data class VolumeHistory(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val date: Date,
        val storageReserved: Double,
        val storageFree: Double,
        val storageRatio: Double,
        @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        @JoinColumn(name = "volume_id", nullable = false)
        val volume: Volume
)
