package com.prose.crhen.SSKotlinServer.SSKotlinServer.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "volume")
data class Volume(
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    val name: String, var description: String, var date: Date,
    val fullCapacity: Double, var latestStorageReserved: Double,
    var latestStorageFree: Double, var latestStorageRatio: Double,
    var mappingLabel: String? = null,
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        @JoinColumn(name = "server_id", nullable = false)
        val server: Server,
    @OneToMany(mappedBy = "volume", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        val volumeHistories: Set<VolumeHistory>? = null
)
