package com.prose.crhen.SSKotlinServer.SSKotlinServer.model

import javax.persistence.*

@Entity
@Table(name = "server_history")
data class ServerHistory(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val ram: Int,
        val ramUsage: Double,
        val cpuUsage: Double,
        @ManyToOne(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
        @JoinColumn(name = "server_id", nullable = false)
        val server: Server
)
