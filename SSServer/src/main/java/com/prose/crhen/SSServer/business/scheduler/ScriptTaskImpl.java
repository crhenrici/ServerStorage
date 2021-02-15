package com.prose.crhen.SSServer.business.scheduler;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Profile("scriptWindows")
public class ScriptTaskImpl implements ScriptTask {
    private static final Logger logger = LoggerFactory.getLogger(ScriptTaskImpl.class);
    ProcessBuilder processBuilder = new ProcessBuilder();

    @Value("${scripts.location}:classpath:scripts/")
    String scriptLocation;

    @Override
    public void run(String target)  {
        logger.info("Task running for : " + target);
        runProcess(target, "serverquery.ps1");
        runProcess(target, "volumequery.ps1");
    }

    private void runProcess(String target, String scriptName) {
        processBuilder.command("powershell.exe", "-Command", String.format("%s\\%s",scriptLocation,scriptName),"-computerName", target);
        try {
            processBuilder.start();
        } catch (IOException e) {
            logger.error(String.format("Script: %s error with exception: %s ",scriptName, e.getMessage()));
            ExceptionUtils.printRootCauseStackTrace(e);
        }
    }
}
