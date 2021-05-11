package com.prose.crhen.SSServer.business.api;

import com.lowagie.text.DocumentException;
import com.prose.crhen.SSServer.dto.PdfDTO;


import java.io.IOException;

public interface PDFGenerator {

    PdfDTO generatePDF(String outputFolder) throws IOException, DocumentException;
}
