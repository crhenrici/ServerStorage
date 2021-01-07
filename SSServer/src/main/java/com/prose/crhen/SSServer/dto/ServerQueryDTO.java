package com.prose.crhen.SSServer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.prose.crhen.SSServer.model.ServerHistory;
import com.prose.crhen.SSServer.model.Volume;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.Objects;
import java.util.Set;

@Value
@Builder
public class ServerQueryDTO {
    
    String name;
    double fullCapacity;
    double latestStorageReserved;
    double latestStorageFree;
    double latestStorageRatio;
    int ram;
    double ramUsage;
    double cpuUsage;
    Set<Volume> volumes;
    Set<ServerHistory> serverHistories;

    public static class ServerQueryDTOBuilder {

    }
}
