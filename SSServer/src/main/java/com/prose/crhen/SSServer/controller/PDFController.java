package com.prose.crhen.SSServer.controller;

import com.google.common.base.Preconditions;
import com.lowagie.text.DocumentException;
import com.prose.crhen.SSServer.business.api.PDFGenerator;
import com.prose.crhen.SSServer.dto.PdfDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/service/pdf")
public class PDFController {

    private static Logger logger = LoggerFactory.getLogger(PDFController.class);

    @Autowired
    private PDFGenerator pdfGenerator;

    @GetMapping("/create")
    @ResponseBody
    public PdfDTO createPDF(@RequestParam String path) throws DocumentException, IOException {
        Preconditions.checkNotNull(path, "Path was not given");
        logger.info("PDFController: Request received");
        logger.info("PDFController: Data received: " + path);
        PdfDTO pdf = pdfGenerator.generatePDF(path);
        return pdf;
    }
}
