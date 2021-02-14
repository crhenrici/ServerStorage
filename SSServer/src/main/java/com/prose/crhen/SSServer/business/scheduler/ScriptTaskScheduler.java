package com.prose.crhen.SSServer.business.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ScriptTaskScheduler {

    ProcessBuilder processBuilder = new ProcessBuilder();
    @Value("${scripts.location}:classpath:scripts/")
    String scriptLocation;
    @Value("${targets}: ")
    String computerNames;

    @Scheduled(cron = "${cron.expression}")
    public void scheduleScriptTask() throws IOException {
        String[] targetList = computerNames.split(":");
        Process process;
        for (String target : targetList) {
           processBuilder.command("powershell.exe", "-Command", scriptLocation + "\\serverquery.ps1","-computerName", target);
           process = processBuilder.start();
           processBuilder.command("powershell.exe", "-Command", scriptLocation + "\\volumequery.ps1", "-computerName", target);
           process = processBuilder.start();
       }
    }
}
