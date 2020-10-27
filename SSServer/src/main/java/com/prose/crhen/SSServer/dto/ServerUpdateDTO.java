package com.prose.crhen.SSServer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ServerUpdateDTO {

    private int ram;
    private double cpuUsage;
    private String systemName;

    @JsonProperty("ram.Capacity")
    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    @JsonProperty("cpuusage.CookedValue")
    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    @JsonProperty("volumes.SystemName")
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
        return ram == that.ram &&
                Double.compare(that.cpuUsage, cpuUsage) == 0 &&
                Objects.equals(systemName, that.systemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ram, cpuUsage, systemName);
    }

    @Override
    public String toString() {
        return "ServerUpdateDTO{" +
                "ram=" + ram +
                ", cpuUsage=" + cpuUsage +
                ", systemName='" + systemName + '\'' +
                '}';
    }
}
