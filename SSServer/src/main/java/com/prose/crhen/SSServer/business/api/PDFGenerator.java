package com.prose.crhen.SSServer.business.api;

import com.lowagie.text.DocumentException;
<<<<<<< Updated upstream
import com.prose.crhen.SSServer.dto.PdfDTO;
=======
>>>>>>> Stashed changes

import java.io.IOException;

public interface PDFGenerator {

    PdfDTO generatePDF(String outputFolder) throws IOException, DocumentException;
}
