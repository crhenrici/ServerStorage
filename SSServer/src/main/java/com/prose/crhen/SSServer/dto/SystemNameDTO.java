package com.prose.crhen.SSServer.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@JsonDeserialize(builder = SystemNameDTO.SystemNameDTOBuilder.class)
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@Value
@Builder
public class SystemNameDTO {

    String SystemName;

    @JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
    @JsonPOJOBuilder(withPrefix = "")
    public static class SystemNameDTOBuilder {
    }
}