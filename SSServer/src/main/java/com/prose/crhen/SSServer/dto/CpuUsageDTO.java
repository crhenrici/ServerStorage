package com.prose.crhen.SSServer.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;


@JsonDeserialize(builder = CpuUsageDTO.CpuUsageDTOBuilder.class)
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@Value
@Builder
public class CpuUsageDTO {
    String path;
    String instanceName;
    Double cookedValue;
    Long rawValue;
    Long secondValue;
    int multipleCount;
    Long counterType;
    String timestamp;
    Long timestamp100NSec;
    int status;
    int defaultScale;
    Long timeBase;

    @JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
    @JsonPOJOBuilder(withPrefix = "")
    public static class CpuUsageDTOBuilder {
    }

}
