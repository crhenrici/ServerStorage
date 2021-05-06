package com.prose.crhen.SSServer.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PdfDTO {

    String fileName;
    byte[] encodedFile;

    public static class PdfDTOBuilder {

    }
}
