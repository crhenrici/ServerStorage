package com.prose.crhen.SSKotlinServer.SSKotlinServer.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "volume")
data class Volume(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
        val name: String, val description: String, val date: Date,
        val fullCapacity: Double, val latestStorageReserved: Double,
        val latestStorageFree: Double, val latestStorageRatio: Double,
        val mappingLabel: String,
        @ManyToOne(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
        @JoinColumn(name = "server_id", nullable = false)
        val server: Server,
        @OneToMany(mappedBy = "volume", fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
        val volumeHistories: Set<VolumeHistory>
)
