package com.prose.crhen.SSServer.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "server_history")
public class ServerHistory {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Id
    @Column(name = "id")
    private long id;
    @Getter
    @Setter
    @Column(name = "storage_free")
    private double storageFree;
    @Getter
    @Setter
    @Column(name = "storage_reserved")
    private double storageReserved;
    @Getter
    @Setter
    @Column(name = "storage_ratio")
    private double storageRatio;
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "server_id", nullable = false)
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
