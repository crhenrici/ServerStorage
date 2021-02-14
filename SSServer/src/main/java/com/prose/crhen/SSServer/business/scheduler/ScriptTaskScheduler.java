package com.prose.crhen.SSServer.business.scheduler;

import org.apache.tomcat.jni.Proc;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component
public class ScriptTaskScheduler {

    ProcessBuilder processBuilder = new ProcessBuilder();
    List<String> computerNames = new LinkedList<>(Arrays.asList("chwisrv03", "chwisrv04", "chwisrv07", "chwisrv11"));

    @Scheduled(cron = "${cron.expression}")
    public void scheduleScriptTask() throws IOException {
        Process process;
        for (int i = 0; i < computerNames.size(); i++) {
           processBuilder.command("powershell.exe", "-Command", "\\script\\serverquery.ps1","-computerName", computerNames.get(i));
           process = processBuilder.start();
           processBuilder.command("powershell.exe", "-Command", "\\script\\volumequery.ps1", "-computerName", computerNames.get(i));
           process = processBuilder.start();
       }
    }
}
