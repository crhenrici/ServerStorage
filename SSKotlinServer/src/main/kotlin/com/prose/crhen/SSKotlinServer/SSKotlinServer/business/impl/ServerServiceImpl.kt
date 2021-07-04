package com.prose.crhen.SSKotlinServer.SSKotlinServer.business.impl

import com.prose.crhen.SSKotlinServer.SSKotlinServer.business.api.ServerService
import com.prose.crhen.SSKotlinServer.SSKotlinServer.dto.*
import com.prose.crhen.SSKotlinServer.SSKotlinServer.model.Server
import org.springframework.stereotype.Service

@Service
class ServerServiceImpl: ServerService {
    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override fun getServerOverviewDTO(): ServerOverviewDTO {
        TODO("Not yet implemented")
    }

    override fun getServers(): List<ServerQueryDTO> {
        TODO("Not yet implemented")
    }

    override fun updateVolume(volumeQueryDTO: VolumeQueryDTO) {
        TODO("Not yet implemented")
    }

    override fun updateServer(serverQueryDTO: ServerQueryDTO) {
        TODO("Not yet implemented")
    }

    override fun getVolumesFromServer(serverName: String): List<VolumeQueryDTO> {
        TODO("Not yet implemented")
    }

    override fun saveServerDTO(server: ServerUpdateDTO) {
        TODO("Not yet implemented")
    }

    override fun saveVolumeDTO(newServer: VolumesUpdateDTO) {
        TODO("Not yet implemented")
    }

    override fun calcServerDetails(server: Server): ServerQueryDTO {
        TODO("Not yet implemented")
    }

    override fun getVolumes(): List<VolumeQueryDTO> {
        TODO("Not yet implemented")
    }
}
