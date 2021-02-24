package com.prose.crhen.SSServer.scheduler;

public class PowerShellScriptCmd implements Command {

    private String scriptLocation;
    private String scriptName;
    private String target;

    public PowerShellScriptCmd(String scriptLocation, String scriptName, String target) {
        this.scriptLocation = scriptLocation;
        this.scriptName = scriptName;
        this.target = target;
    }

    @Override
    public String[] getCommand() {
        return new String[]{"C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\powershell.exe", "-Command", String.format("%s\\%s", scriptLocation, scriptName), "-computerName", target};
    }
}
