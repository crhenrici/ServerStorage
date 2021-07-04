package com.prose.crhen.SSKotlinServer.SSKotlinServer.business.api

import com.prose.crhen.SSKotlinServer.SSKotlinServer.dto.*
import com.prose.crhen.SSKotlinServer.SSKotlinServer.model.Server

interface ServerService {
    fun deleteAll();

    fun getServerOverviewDTO(): ServerOverviewDTO

    fun getServers(): List<ServerQueryDTO>

    fun updateVolume(volumeQueryDTO: VolumeQueryDTO)

    fun updateServer(serverQueryDTO: ServerQueryDTO)

    fun getVolumesFromServer(serverName: String): List<VolumeQueryDTO>

    fun saveServerDTO(server: ServerUpdateDTO)

    fun saveVolumeDTO(newServer: VolumesUpdateDTO)

    fun calcServerDetails(server: Server): ServerQueryDTO

    fun getVolumes(): List<VolumeQueryDTO>


}
