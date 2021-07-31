package com.prose.crhen.SSKotlinServer.SSKotlinServer.model

import javax.persistence.*

@Entity
@Table(name = "server")
data class Server(
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    val name: String, var networkDesc: String? = null, var ram: Int? = null,
    var ramUsage: Double? = null, var cpuUsage: Double? = null,
    @OneToMany(mappedBy = "server", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    var volumes: Set<Volume>? = null,
    @OneToMany(mappedBy = "server", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        val serverHistories: Set<ServerHistory>? = null
)
