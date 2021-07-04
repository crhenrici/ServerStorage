package com.prose.crhen.SSKotlinServer.SSKotlinServer.model

import javax.persistence.*

@Entity
@Table(name = "server")
data class Server(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
        val name: String, val networkDesc: String, val ram: Int,
        val ramUsage: Double, val cpuUsage: Double,
        @OneToMany(mappedBy = "server", fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
        val volumes: Set<Volume>,
        @OneToMany(mappedBy = "server", fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
        val serverHistories: Set<ServerHistory>
)
