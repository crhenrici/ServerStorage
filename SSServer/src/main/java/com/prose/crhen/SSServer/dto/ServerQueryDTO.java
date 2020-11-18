package com.prose.crhen.SSServer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prose.crhen.SSServer.model.ServerHistory;
import com.prose.crhen.SSServer.model.Volume;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

public class ServerQueryDTO {

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private double fullCapacity;
    @Getter
    @Setter
    private double latestStorageReserved;
    @Getter
    @Setter
    private double latestStorageFree;
    @Getter
    @Setter
    private double latestStorageRatio;
    @Getter
    @Setter
    private int ram;
    @Getter
    @Setter
    private double cpuUsage;
    @Getter
    @Setter
    private Set<Volume> volumes;
    @Getter
    @Setter
    private Set<ServerHistory> serverHistories;

    public ServerQueryDTO() {

    }

    public ServerQueryDTO(String name, double fullCapacity, double latestStorageReserved, double latestStorageFree, double latestStorageRatio, int ram, double cpuUsage, Set<Volume> volumes, Set<ServerHistory> serverHistories) {
        this.name = name;
        this.fullCapacity = fullCapacity;
        this.latestStorageReserved = latestStorageReserved;
        this.latestStorageFree = latestStorageFree;
        this.latestStorageRatio = latestStorageRatio;
        this.ram = ram;
        this.cpuUsage = cpuUsage;
        this.volumes = volumes;
        this.serverHistories = serverHistories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerQueryDTO that = (ServerQueryDTO) o;
        return Double.compare(that.fullCapacity, fullCapacity) == 0 &&
                Double.compare(that.latestStorageReserved, latestStorageReserved) == 0 &&
                Double.compare(that.latestStorageFree, latestStorageFree) == 0 &&
                Double.compare(that.latestStorageRatio, latestStorageRatio) == 0 &&
                ram == that.ram &&
                Double.compare(that.cpuUsage, cpuUsage) == 0 &&
                Objects.equals(name, that.name) &&
                Objects.equals(volumes, that.volumes) &&
                Objects.equals(serverHistories, that.serverHistories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fullCapacity, latestStorageReserved, latestStorageFree, latestStorageRatio, ram, cpuUsage, volumes, serverHistories);
    }

    @Override
    public String toString() {
        return "ServerQueryDTO{" +
                "name='" + name + '\'' +
                ", fullCapacity=" + fullCapacity +
                ", latestStorageReserved=" + latestStorageReserved +
                ", latestStorageFree=" + latestStorageFree +
                ", latestStorageRatio=" + latestStorageRatio +
                ", ram=" + ram +
                ", cpuUsage=" + cpuUsage +
                ", volumes=" + volumes +
                ", serverHistories=" + serverHistories +
                '}';
    }
}
