package com.prose.crhen.SSServer.dto;

import lombok.Getter;
import lombok.Setter;

public class ServerOverviewDTO {

    @Getter
    @Setter
    private int numberOfServers;
    @Getter
    @Setter
    private double totalCapacity;
    @Getter
    @Setter
    private double totalCapacityUsed;
    @Getter
    @Setter
    private double totalCapacityFree;
    @Getter
    @Setter
    private double totalUsedCapacityRatio;

    public ServerOverviewDTO() {

    }

    public ServerOverviewDTO(int numberOfServers, double totalCapacity, double totalCapacityUsed, double totalCapacityFree, double totalFreeCapacityRatio) {
        this.numberOfServers = numberOfServers;
        this.totalCapacity = totalCapacity;
        this.totalCapacityUsed = totalCapacityUsed;
        this.totalCapacityFree = totalCapacityFree;
        this.totalUsedCapacityRatio = totalFreeCapacityRatio;
    }

    @Override
    public String toString() {
        return "ServerOverviewDTO{" +
                "numberOfServers=" + numberOfServers +
                ", totalCapacity=" + totalCapacity +
                ", totalCapacityUsed=" + totalCapacityUsed +
                ", totalCapacityFree=" + totalCapacityFree +
                ", totalUsedCapacityRatio=" + totalUsedCapacityRatio +
                '}';
    }
}
