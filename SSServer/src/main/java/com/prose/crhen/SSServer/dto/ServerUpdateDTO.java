package com.prose.crhen.SSServer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.util.Objects;

@JsonDeserialize(builder = ServerUpdateDTO.ServerUpdateDTOBuilder.class)
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@Value
@Builder
public class ServerUpdateDTO {

    CpuUsageDTO cpuUsage;

    RamDTO ram;

    double ramUsage;

    String systemName;

    @JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
    @JsonPOJOBuilder(withPrefix = "")
    public static class ServerUpdateDTOBuilder {
    }
}
