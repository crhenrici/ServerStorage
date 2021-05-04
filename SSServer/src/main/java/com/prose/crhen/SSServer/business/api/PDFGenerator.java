package com.prose.crhen.SSServer.business.api;

import com.lowagie.text.DocumentException;

import java.io.IOException;

public interface PDFGenerator {

    void generatePDF(String outputFolder) throws IOException, DocumentException;
}
