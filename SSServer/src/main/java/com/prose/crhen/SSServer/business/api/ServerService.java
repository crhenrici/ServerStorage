package com.prose.crhen.SSServer.business.api;

import com.prose.crhen.SSServer.dto.*;
import com.prose.crhen.SSServer.model.Server;

import java.util.List;

public interface ServerService {
    //ONLY FOR DEMONSTRATION
    void deleteAll();

    ServerOverviewDTO getServerOverviewDTO();

    List<ServerQueryDTO> getServers();

    void updateVolume(VolumeQueryDTO volumeQueryDTO);

    void updateServer(ServerQueryDTO serverQueryDTO);

    List<VolumeQueryDTO> getVolumesFromServer(String serverName);

    void saveServerDTO(ServerUpdateDTO server);

    void saveVolumeDTO(VolumesUpdateDTO newServer);

    ServerQueryDTO.ServerQueryDTOBuilder calcServerDetails(Server server);

    List<VolumeQueryDTO> getVolumes();
}
