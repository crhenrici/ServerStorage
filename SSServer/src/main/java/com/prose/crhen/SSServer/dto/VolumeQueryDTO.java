package com.prose.crhen.SSServer.dto;

import com.prose.crhen.SSServer.model.Server;
import com.prose.crhen.SSServer.model.VolumeHistory;
import lombok.Builder;
import lombok.Value;

import java.sql.Date;
import java.util.Set;

@Value
@Builder
public class VolumeQueryDTO {
    
    String name;
    String desc;
    Date date;
    double fullCapacity;
    double latestStorageReserved;
    double latestStorageFree;
    double latestStorageRatio;
    String mappingLabel;
    Server server;
    Set<VolumeHistory> volumeHistories;

    public static class VolumeQueryDTOBuilder {
    }
    
}
