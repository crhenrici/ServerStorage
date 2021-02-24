package com.prose.crhen.SSServer.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ScriptTaskScheduler {

    private static final Logger logger = LoggerFactory.getLogger(ScriptTaskScheduler.class);

    @Autowired
    ScriptTask scriptTask;

    @Value("${computer.targets}")
    String computerNames;

    @Scheduled(cron = "${cron.expression}")
    public void scheduleScriptTask() throws IOException {
        String[] targetList = computerNames.split(":");
        logger.info("targetList: " + targetList.toString());
        for (String target : targetList) {
           scriptTask.run(target);
       }
    }
}
