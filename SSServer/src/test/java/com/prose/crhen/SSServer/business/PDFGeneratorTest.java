package com.prose.crhen.SSServer.business;

import com.lowagie.text.DocumentException;
import com.prose.crhen.SSServer.business.api.PDFGenerator;
import com.prose.crhen.SSServer.dto.PdfDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles("scriptMock")
@TestPropertySource(locations = "classpath:test.properties")
class PDFGeneratorTest {

    @Autowired
    PDFGenerator pdfGenerator;

    @Test
    void generatePDF() throws DocumentException, IOException {
        String fileName = "test";
        PdfDTO pdf = pdfGenerator.generatePDF(fileName);
        assertEquals(pdf.getFileName(), fileName);
    }

}
