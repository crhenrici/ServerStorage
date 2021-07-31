package com.prose.crhen.SSKotlinServer.SSKotlinServer.controller

import com.prose.crhen.SSKotlinServer.SSKotlinServer.business.api.ServerService
import com.prose.crhen.SSKotlinServer.SSKotlinServer.dto.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@CrossOrigin("*")
class ServerController @Autowired constructor(private val service: ServerService){


    @GetMapping("/service/overview")
    @ResponseBody
    fun getServerOverview(): ServerOverviewDTO {
        return service.getServerOverviewDTO()
    }

    @GetMapping("/service/servers")
    @ResponseBody
    fun getAllServers(): List<ServerQueryDTO> {
        return service.getServers()
    }

    @GetMapping("/service/volumes")
    @ResponseBody
    fun getAllVolumesFromServer(@RequestParam serverName: String): List<VolumeQueryDTO> {
        return service.getVolumesFromServer(serverName)
    }

    @PostMapping("/service/save/volume")
    fun saveVolumes(@RequestBody volumes: List<VolumesUpdateDTO>?): ResponseEntity<String> {
        if (volumes != null) {
            for (volume in volumes) {
                service.saveVolumeDTO(volume)
            }
        }
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @PostMapping("/service/update/volume")
    fun updateVolume(@RequestBody volume: VolumeQueryDTO?): ResponseEntity<String> {
        volume?.let { service.updateVolume(it) }
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @PostMapping("/service/update/server")
    fun updateServerDesc(@RequestBody server: ServerQueryDTO?): ResponseEntity<String> {
        server?.let { service.updateServer(it) }
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @PostMapping("/service/save/server")
    fun saveServer(@RequestBody server: ServerUpdateDTO?): ResponseEntity<String> {
        server?.let { service.saveServerDTO(it) }
        return ResponseEntity.status(HttpStatus.OK).build()
    }

}
