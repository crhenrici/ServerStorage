package com.prose.crhen.SSServer.business.impl;

import com.lowagie.text.DocumentException;
import com.prose.crhen.SSServer.business.api.PDFGenerator;
import com.prose.crhen.SSServer.dto.ServerQueryDTO;
import com.prose.crhen.SSServer.dto.VolumeQueryDTO;
import com.prose.crhen.SSServer.model.Volume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;

@Component
public class PDFGeneratorImpl implements PDFGenerator {

    @Autowired
    ServerServiceImpl service;

    private String parseThymeleafTemplate() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("web/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();

        List<ServerQueryDTO> serverList = service.getServers();

        context.setVariable("serverList", serverList);

        return templateEngine.process("pdf_template", context);
    }

    public void generatePDF(String outputFolder) throws IOException, DocumentException {
        String html = parseThymeleafTemplate();
        OutputStream outputStream = new FileOutputStream(outputFolder);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }

    private double roundOff(double num) {
        return Math.round(num*100)/100;
    }
}
