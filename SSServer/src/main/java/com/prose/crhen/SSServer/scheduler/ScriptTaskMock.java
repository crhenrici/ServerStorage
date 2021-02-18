package com.prose.crhen.SSServer.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("scriptMock")
public class ScriptTaskMock implements ScriptTask {
    private static final Logger logger = LoggerFactory.getLogger(ScriptTaskMock.class);

    @Override
    public void run(String target) {
      logger.info("Task running for : " + target);
    }
}
