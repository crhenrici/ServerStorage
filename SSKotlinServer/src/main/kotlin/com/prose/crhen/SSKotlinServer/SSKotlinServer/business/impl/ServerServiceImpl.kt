package com.prose.crhen.SSKotlinServer.SSKotlinServer.business.impl

import com.prose.crhen.SSKotlinServer.SSKotlinServer.business.api.ServerService
import com.prose.crhen.SSKotlinServer.SSKotlinServer.db.ServerHistoryRepository
import com.prose.crhen.SSKotlinServer.SSKotlinServer.db.ServerRepository
import com.prose.crhen.SSKotlinServer.SSKotlinServer.db.VolumeHistoryRepository
import com.prose.crhen.SSKotlinServer.SSKotlinServer.db.VolumeRepository
import com.prose.crhen.SSKotlinServer.SSKotlinServer.dto.*
import com.prose.crhen.SSKotlinServer.SSKotlinServer.model.Server
import com.prose.crhen.SSKotlinServer.SSKotlinServer.model.ServerHistory
import com.prose.crhen.SSKotlinServer.SSKotlinServer.model.Volume
import com.prose.crhen.SSKotlinServer.SSKotlinServer.model.VolumeHistory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.Date

@Service
class ServerServiceImpl @Autowired constructor(
        private val serverRepository: ServerRepository,
        private val serverHistoryRepository: ServerHistoryRepository,
        private val volumeRepository: VolumeRepository,
        private val volumeHistoryRepository: VolumeHistoryRepository
): ServerService{


    override fun deleteAll() {
        serverRepository.deleteAll()
        volumeRepository.deleteAll()
        volumeHistoryRepository.deleteAll()
        serverHistoryRepository.deleteAll()
    }

    override fun getServerOverviewDTO(): ServerOverviewDTO {
        val servers: List<Server> = serverRepository.findAll()
        var volumes: List<Volume>?
        var totalCapacity = 0.0
        var totalCapacityUsed = 0.0
        var totalCapacityFree = 0.0

        for (server in servers) {
            volumes = volumeRepository.findByServer(server)
            for (volume: Volume in volumes) {
                totalCapacity += volume.fullCapacity
                totalCapacityUsed += volume.latestStorageReserved
                totalCapacityFree += volume.latestStorageFree
            }
        }
        val totalUsedCapacityRatio: Double = (totalCapacityUsed / totalCapacity) * 100
        return ServerOverviewDTO(
            numberOfServer = servers.size,
            totalCapacity = totalCapacity,
            totalCapacityFree = totalCapacityFree,
            totalCapacityUsed = totalCapacityUsed,
            totalUsedCapacityRatio = totalUsedCapacityRatio
        )
    }

    override fun getServers(): List<ServerQueryDTO> {
        val servers: List<Server> = serverRepository.findAll()
        val serverQueryDTOs = arrayListOf<ServerQueryDTO>()

        for (server: Server in servers) {
            val createdServerQuery = createServerQuery(server)
            serverQueryDTOs.add(createdServerQuery)
        }
        return serverQueryDTOs
    }

    override fun updateVolume(volumeQueryDTO: VolumeQueryDTO) {
        val server = volumeQueryDTO.server
        val updatedVolume = volumeRepository.findVolumeByNameAndServer(volumeQueryDTO.name, server)
        updatedVolume.description = volumeQueryDTO.desc
        updatedVolume.mappingLabel = volumeQueryDTO.mappingLabel
        updatedVolume.server.networkDesc = volumeQueryDTO.server.networkDesc

        val volumes = server.volumes
        server.volumes = volumes
        volumeRepository.save(updatedVolume)
    }

    override fun updateServer(serverQueryDTO: ServerQueryDTO) {
        val server = serverRepository.findByName(serverQueryDTO.name)
        server.networkDesc = serverQueryDTO.networkDesc
        serverRepository.save(server)
    }

    override fun getVolumesFromServer(serverName: String): List<VolumeQueryDTO> {
        val server = serverRepository.findByName(serverName)
        val volumes = volumeRepository.findByServer(server)
        val volumeQueryDTOs = arrayListOf<VolumeQueryDTO>()
        for (volume in volumes) {
            val createdVolumeQuery = createVolumeQuery(volume)
            if (createdVolumeQuery != null) {
                volumeQueryDTOs.add(createdVolumeQuery)
            }
        }
        return volumeQueryDTOs
    }

    override fun saveServerDTO(server: ServerUpdateDTO) {
        var newServer: Server? = serverRepository.findByName(server.systemName.SystemName)
        if (newServer != null) {
            newServer.ram = server.ram.Capacity
            newServer.ramUsage = server.ramUsage
            newServer.cpuUsage = server.cpuUsage.cookedValue
        } else {
            newServer = Server(
                    name = server.systemName.SystemName,
                    ram = server.ram.Capacity,
                    ramUsage = server.ramUsage,
                    cpuUsage = server.cpuUsage.cookedValue)
        }
        serverRepository.save(newServer)
        saveServerHistory(newServer)
    }

    private fun saveServerHistory(newServer: Server) {
        val serverHistory = ServerHistory(ram = newServer.ram!!, ramUsage = newServer.ramUsage!!, cpuUsage = newServer.cpuUsage!!, server = newServer)
        serverHistoryRepository.save(serverHistory)
    }

    override fun saveVolumeDTO(newVolume: VolumesUpdateDTO) {
        var serverFound: Server? = serverRepository.findByName(newVolume.systemName)
        if (serverFound != null) {
            checkForVolume(serverFound, newVolume)
        } else {
            serverFound = Server(name = newVolume.systemName)
            val createdVolume = createVolumeToBeInserted(newVolume, serverFound)
            insertServerAndVolume(serverFound, createdVolume)
        }
        saveVolumeHistory(serverFound, newVolume)
    }

    private fun insertServerAndVolume(serverFound: Server, createdVolume: Volume) {
        serverRepository.save(serverFound)
        volumeRepository.save(createdVolume)
    }

    private fun createVolumeToBeInserted(newVolume: VolumesUpdateDTO, serverFound: Server): Volume {
        val freeSpacePercentage = newVolume.freeSpacePercent ?: "0"
        val reserved = newVolume.capacityGB.toDouble().minus(newVolume.freeSpaceGB.toDouble())
        val sqlDate = getSqlDate()
        return Volume(name = newVolume.name,
            description = "", date = sqlDate,
            fullCapacity = newVolume.capacityGB.toDouble(),
            latestStorageReserved = reserved,
            latestStorageRatio = freeSpacePercentage.toDouble(),
            server = serverFound, latestStorageFree = newVolume.freeSpaceGB.toDouble())
    }

    private fun saveVolumeHistory(serverFound: Server?, newVolume: VolumesUpdateDTO) {
        val latestVolume = serverFound?.let { volumeRepository.findVolumeByNameAndServer(newVolume.name, it)}
        val volumeHistory = latestVolume?.let { VolumeHistory(date = it.date, storageReserved = it.latestStorageReserved, storageFree = it.latestStorageFree, storageRatio = it.latestStorageRatio, volume = it)}
        volumeHistory?.let { volumeHistoryRepository.save(it) }
    }

    private fun checkForVolume(serverFound: Server, newVolume: VolumesUpdateDTO) {
        val volume: Volume = volumeRepository.findVolumeByNameAndServer(server = serverFound, name = serverFound.name)

        if (volume != null) {
            val volumes = updateVolumeOffNewVolume(volume, newVolume)
            serverFound.volumes = volumes
        } else {
            volumeRepository.save(createVolumeToBeInserted(newVolume, serverFound))
        }

    }

    private fun updateVolumeOffNewVolume(volume: Volume, newVolume: VolumesUpdateDTO): Set<Volume>? {
        val volumeInserted = getVolumeToBeInserted(volume, newVolume)

        val volumes: Set<Volume>? = volumeInserted.server.volumes
        volumeRepository.save(volumeInserted)
        return volumes
    }

    private fun getVolumeToBeInserted(volume: Volume, newVolume: VolumesUpdateDTO?): Volume {
        val freeSpacePercentage = newVolume?.freeSpacePercent ?: "0"
        val reserved = newVolume?.capacityGB?.toDouble()?.minus(newVolume.freeSpaceGB.toDouble())
        volume.latestStorageFree = newVolume?.freeSpaceGB?.toDouble()!!
        volume.latestStorageRatio = freeSpacePercentage.toDouble()
        if (reserved != null) {
            volume.latestStorageReserved = reserved
        }
        volume.date = getSqlDate()
        return volume
    }

    private fun getSqlDate(): Date {
        val currentTime: java.util.Date = java.util.Date()
        return Date(currentTime.time)
    }

    override fun createServerQuery(server: Server): ServerQueryDTO {
        val volumes: List<Volume> = volumeRepository.findByServer(server)
        return ServerQueryDTO(
            fullCapacity = addServerCapacityFromVolumes(volumes),
            latestStorageFree = addServerStorageFromVolumes(volumes),
            latestStorageRatio = calculateStorageRatio(addServerCapacityFromVolumes(volumes), addServerStorageFromVolumes(volumes)),
            latestStorageReserved = addServerReservedFromVolumes(volumes),
            name = server.name,
            ram = server.ram!!,
            ramUsage = server.ramUsage!!,
            volumes = server.volumes,
            serverHistories = server.serverHistories,
            networkDesc = server.networkDesc,
            cpuUsage = server.cpuUsage!!
        )
    }

    private fun addServerReservedFromVolumes(volumes: List<Volume>): Double {
        return volumes.sumOf { it.latestStorageReserved }
    }

    private fun calculateStorageRatio(fullCapacity: Double, latestStorageFree: Double): Double {
        return (latestStorageFree/fullCapacity) * 100
    }

    private fun addServerStorageFromVolumes(volumes: List<Volume>): Double {
        return volumes.sumOf { it.latestStorageFree }
    }

    private fun addServerCapacityFromVolumes(volumes: List<Volume>): Double {
        return volumes.sumOf { it.fullCapacity }
    }

    override fun getVolumes(): List<VolumeQueryDTO?> {
        val volumes = volumeRepository.findAll()
        val volumeQueryDTOs = arrayListOf<VolumeQueryDTO?>()
        for (volume in volumes) {
            val createdVolumeQuery = createVolumeQuery(volume)
            volumeQueryDTOs.add(createdVolumeQuery)
        }
        return volumeQueryDTOs
    }

    private fun createVolumeQuery(volume: Volume?): VolumeQueryDTO? {
        return volume?.let {
            VolumeQueryDTO(
                    name = it.name,
                    desc = it.description,
                    date = it.date,
                    fullCapacity = it.fullCapacity,
                    latestStorageReserved = it.latestStorageReserved,
                    latestStorageRatio = it.latestStorageRatio,
                    latestStorageFree = it.latestStorageFree,
                    mappingLabel = it.mappingLabel!!,
                    server = it.server,
                    volumeHistories = it.volumeHistories!!

            )
        }
    }
}
