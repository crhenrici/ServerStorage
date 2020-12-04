package com.prose.crhen.SSServer.dto;

import com.prose.crhen.SSServer.model.Server;
import com.prose.crhen.SSServer.model.VolumeHistory;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Objects;
import java.util.Set;

public class VolumeQueryDTO {

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String desc;
    @Getter
    @Setter
    private Date date;
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
    private Server server;
    @Getter
    @Setter
    private Set<VolumeHistory> volumeHistories;

    public VolumeQueryDTO(String name, String desc, double fullCapacity, double latestStorageReserved, double latestStorageFree, double latestStorageRatio, Server server, Set<VolumeHistory> volumeHistories) {
        this.name = name;
        this.desc = desc;
        this.fullCapacity = fullCapacity;
        this.latestStorageReserved = latestStorageReserved;
        this.latestStorageFree = latestStorageFree;
        this.latestStorageRatio = latestStorageRatio;
        this.server = server;
        this.volumeHistories = volumeHistories;
    }

    public VolumeQueryDTO(String name, String desc, Date date, double fullCapacity, double latestStorageReserved, double latestStorageFree, double latestStorageRatio, Server server, Set<VolumeHistory> volumeHistories) {
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.fullCapacity = fullCapacity;
        this.latestStorageReserved = latestStorageReserved;
        this.latestStorageFree = latestStorageFree;
        this.latestStorageRatio = latestStorageRatio;
        this.server = server;
        this.volumeHistories = volumeHistories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolumeQueryDTO that = (VolumeQueryDTO) o;
        return Double.compare(that.fullCapacity, fullCapacity) == 0 &&
                Double.compare(that.latestStorageReserved, latestStorageReserved) == 0 &&
                Double.compare(that.latestStorageFree, latestStorageFree) == 0 &&
                Double.compare(that.latestStorageRatio, latestStorageRatio) == 0 &&
                Objects.equals(name, that.name) &&
                Objects.equals(desc, that.desc) &&
                Objects.equals(date, that.date) &&
                Objects.equals(server, that.server) &&
                Objects.equals(volumeHistories, that.volumeHistories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, desc, date, fullCapacity, latestStorageReserved, latestStorageFree, latestStorageRatio, server, volumeHistories);
    }

    @Override
    public String toString() {
        return "VolumeQueryDTO{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", date=" + date +
                ", fullCapacity=" + fullCapacity +
                ", latestStorageReserved=" + latestStorageReserved +
                ", latestStorageFree=" + latestStorageFree +
                ", latestStorageRatio=" + latestStorageRatio +
                ", server=" + server +
                ", volumeHistories=" + volumeHistories +
                '}';
    }
}
