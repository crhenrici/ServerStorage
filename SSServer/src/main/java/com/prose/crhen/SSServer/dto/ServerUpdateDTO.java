package com.prose.crhen.SSServer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ServerUpdateDTO {

    private CpuUsageDTO cpuUsage;

    private RamDTO ram;

    private String systemName;

    @JsonProperty("cpuusage")
    public CpuUsageDTO getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(CpuUsageDTO cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    @JsonProperty("ram")
    public RamDTO getRam() {
        return ram;
    }

    public void setRam(RamDTO ram) {
        this.ram = ram;
    }
    @JsonProperty("systemname")
    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerUpdateDTO that = (ServerUpdateDTO) o;
        return Objects.equals(cpuUsage, that.cpuUsage) &&
                Objects.equals(ram, that.ram) &&
                Objects.equals(systemName, that.systemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpuUsage, ram, systemName);
    }

    @Override
    public String toString() {
        return "ServerUpdateDTO{" +
                "cpuUsage=" + cpuUsage +
                ", ram=" + ram +
                ", systemName='" + systemName + '\'' +
                '}';
    }
}
