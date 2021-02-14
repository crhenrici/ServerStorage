package com.prose.crhen.SSServer.business;

import com.prose.crhen.SSServer.SsServerApplication;
import com.prose.crhen.SSServer.business.scheduler.ScriptTaskScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.IOException;

@SpringJUnitConfig(TestConfig.class)
public class ScriptTaskSchedulerTest {

    @Autowired
    ScriptTaskScheduler scheduler;

    @Test
    void scheduleScriptTaskTest() throws IOException {
        scheduler.scheduleScriptTask();
    }
}
