package com.prose.crhen.SSServer.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class ServerHistory {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Id
    private long id;
    @Getter
    @Setter
    private double storageFree;
    @Getter
    @Setter
    private double storageReserved;
    @Getter
    @Setter
    private double storageRatio;
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private Server server;

    public ServerHistory() {
    }

    public ServerHistory(double storageFree, double storageReserved, double storageRatio, Server server) {
        this.storageFree = storageFree;
        this.storageReserved = storageReserved;
        this.storageRatio = storageRatio;
        this.server = server;
    }

    @Override
    public String toString() {
        return "ServerHistory{" +
                "id=" + id +
                ", storageFree=" + storageFree +
                ", storageReserved=" + storageReserved +
                ", storageRatio=" + storageRatio +
                ", server=" + server +
                '}';
    }
}
