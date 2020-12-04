package com.prose.crhen.SSServer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class VolumesUpdateDTO {

    private String systemName;
    private String name;
    private String driveLetter;
    private String capacityGB;
    private String freeSpaceGB;
    private String freeSpacePercent;
    private String date;
//    private String ram;
//    private String cpuUsage;

    @JsonProperty("SystemName")
    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("DriveLetter")
    public String getDriveLetter() {
        return driveLetter;
    }

    public void setDriveLetter(String driveLetter) {
        this.driveLetter = driveLetter;
    }

    @JsonProperty("CapacityGB")
    public String getCapacityGB() {
        return capacityGB;
    }

    public void setCapacityGB(String capacityGB) {
        this.capacityGB = capacityGB;
    }

    @JsonProperty("FreeSpaceGB")
    public String getFreeSpaceGB() {
        return freeSpaceGB;
    }

    public void setFreeSpaceGB(String freeSpaceGB) {
        this.freeSpaceGB = freeSpaceGB;
    }

    @JsonProperty("FreeSpacePercent")
    public String getFreeSpacePercent() {
        return freeSpacePercent;
    }

    public void setFreeSpacePercent(String freeSpacePercent) {
      this.freeSpacePercent = freeSpacePercent != null ? freeSpacePercent : "0";
    }

    @JsonProperty("Date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

//    public String getRam() {
//        return ram;
//    }
//
//    public void setRam(String ram) {
//        this.ram = ram;
//    }
//
//    public String getCpuUsage() {
//        return cpuUsage;
//    }
//
//    public void setCpuUsage(String cpuUsage) {
//        this.cpuUsage = cpuUsage;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolumesUpdateDTO that = (VolumesUpdateDTO) o;
        return Objects.equals(systemName, that.systemName) &&
                Objects.equals(name, that.name) &&
                Objects.equals(driveLetter, that.driveLetter) &&
                Objects.equals(capacityGB, that.capacityGB) &&
                Objects.equals(freeSpaceGB, that.freeSpaceGB) &&
                Objects.equals(freeSpacePercent, that.freeSpacePercent) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(systemName, name, driveLetter, capacityGB, freeSpaceGB, freeSpacePercent, date);
    }

    @Override
    public String toString() {
        return "VolumesUpdateDTO{" +
                "systemName='" + systemName + '\'' +
                ", name='" + name + '\'' +
                ", driveLetter='" + driveLetter + '\'' +
                ", capacityGB='" + capacityGB + '\'' +
                ", freeSpaceGB='" + freeSpaceGB + '\'' +
                ", freeSpacePercent='" + freeSpacePercent + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
