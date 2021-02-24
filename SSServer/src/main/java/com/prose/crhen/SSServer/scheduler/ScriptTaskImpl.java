package com.prose.crhen.SSServer.scheduler;

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

    @Value("${scripts.location}")
    String scriptLocation;

    @Override
    public void run(String target)  {
        ProcessBuilderCmdRunner cmdRunner = new ProcessBuilderCmdRunner();
        logger.info("Task running for : " + target);
        cmdRunner.run(new PowerShellScriptCmd(scriptLocation,"serverquery.ps1", target));
        cmdRunner.run(new PowerShellScriptCmd(scriptLocation,"volumequery.ps1", target));
    }
}
