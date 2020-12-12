package com.prose.crhen.SSServer.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@JsonDeserialize(builder = VolumesUpdateDTO.VolumesUpdateDTOBuilder.class)
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@Value
@Builder
public class VolumesUpdateDTO {

    String systemName;
    String name;
    String driveLetter;
    String capacityGB;
    String freeSpaceGB;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Builder.Default
    String freeSpacePercent = "0";
    String date;

    @JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
    @JsonPOJOBuilder(withPrefix = "")
    public static class VolumesUpdateDTOBuilder {
    }
}
