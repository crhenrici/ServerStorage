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
        this.freeSpacePercent = freeSpacePercent;
    }

    @JsonProperty("Date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolumesUpdateDTO serverDTO = (VolumesUpdateDTO) o;
        return Objects.equals(systemName, serverDTO.systemName) &&
                Objects.equals(name, serverDTO.name) &&
                Objects.equals(driveLetter, serverDTO.driveLetter) &&
                Objects.equals(capacityGB, serverDTO.capacityGB) &&
                Objects.equals(freeSpaceGB, serverDTO.freeSpaceGB) &&
                Objects.equals(freeSpacePercent, serverDTO.freeSpacePercent) &&
                Objects.equals(date, serverDTO.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(systemName, name, driveLetter, capacityGB, freeSpaceGB, freeSpacePercent, date);
    }

    @Override
    public String toString() {
        return "ServerDTO{" +
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
