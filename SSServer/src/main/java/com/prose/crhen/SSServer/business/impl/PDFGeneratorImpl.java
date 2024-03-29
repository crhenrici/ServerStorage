package com.prose.crhen.SSServer.business.impl;

import com.lowagie.text.DocumentException;
import com.prose.crhen.SSServer.business.api.PDFGenerator;
import com.prose.crhen.SSServer.dto.PdfDTO;
import com.prose.crhen.SSServer.dto.ServerQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class PDFGeneratorImpl implements PDFGenerator {

    @Autowired
    ServerServiceImpl service;

    String currentDate;

    private String parseThymeleafTemplate() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();

        List<ServerQueryDTO> serverList = service.getServers();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate timeNow = LocalDate.now();
        currentDate = df.format(timeNow).toString();

        context.setVariable("serverList", serverList);
        context.setVariable("currentDate", currentDate);
        return templateEngine.process("pdf_template", context);
    }

    public PdfDTO generatePDF(String fileName) throws IOException, DocumentException {
        String html = parseThymeleafTemplate();
        OutputStream outputStream = new FileOutputStream(fileName);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();

        PdfDTO pdf = PdfDTO.builder()
                .fileName(fileName)
                .encodedFile(encodeFile(fileName))
                .build();
        return pdf;
    }

    private byte[] encodeFile(String fileName) throws IOException {
        byte[] inFileBytes = Files.readAllBytes(Paths.get(fileName));
        deleteFile(fileName);
        byte[] encodedFile = Base64.getEncoder().encode(inFileBytes);
        return encodedFile;
    }

    private void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists())
            file.delete();
    }
}
