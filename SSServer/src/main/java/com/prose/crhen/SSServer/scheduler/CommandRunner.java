package com.prose.crhen.SSServer.scheduler;

import java.util.List;

public interface CommandRunner {

    void preProcess();
    List<String> run(Command cmd);
    void postProcess();
}
