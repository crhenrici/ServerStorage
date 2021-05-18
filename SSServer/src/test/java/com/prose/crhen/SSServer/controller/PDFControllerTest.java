package com.prose.crhen.SSServer.controller;

import com.lowagie.text.DocumentException;
import com.prose.crhen.SSServer.dto.PdfDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"scriptMock"})
class PDFControllerTest {

    @LocalServerPort
    int randomServerPort;
    String path = "test";
    File file;

    @BeforeEach
    void setUp() {
        file = new File(path);

        if (file.exists())
            file.delete();
    }
    @Test
    void createPDFTest() {
        final String baseUrl = "http://localhost:" + randomServerPort + "/service/pdf/create";
        RestTemplate restTemplate = new RestTemplate();
        PdfDTO pdf = restTemplate.getForObject(baseUrl + "?path=" + path, PdfDTO.class);
        System.out.println(pdf.toString());
        assertEquals(pdf.getFileName(), path);
    }
}
