package com.prose.crhen.SSServer.business.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ScriptTaskScheduler {

    @Autowired
    ScriptTask scriptTask;

    @Value("${computer.targets}")
    String computerNames;

    @Scheduled(cron = "${cron.expression}")
    public void scheduleScriptTask() throws IOException {
        String[] targetList = computerNames.split(":");
        Process process;
        for (String target : targetList) {
           scriptTask.run(target);
       }
    }
}
