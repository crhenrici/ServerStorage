package com.prose.crhen.SSServer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Value;

@Value
@JsonDeserialize(builder = PdfDTO.PdfDTOBuilder.class)
@Builder
public class PdfDTO {

    @JsonProperty("fileName")
    String fileName;
    @JsonProperty("encodedFile")
    byte[] encodedFile;

    public static class PdfDTOBuilder {

    }
}
