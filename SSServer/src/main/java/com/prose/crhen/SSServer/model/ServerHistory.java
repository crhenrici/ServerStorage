package com.prose.crhen.SSServer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "ram")
    private int ram;
    @Getter
    @Setter
    @Column(name = "ram_usage")
    private double ramUsage;
    @Getter
    @Setter
    @Column(name = "cpu_usage")
    private double cpuUsage;
    @JsonIgnore
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "server_id", nullable = false)
    private Server server;

    public ServerHistory() {
    }

    public ServerHistory(int ram, double ramUsage , double cpuUsage,Server server) {
        this.ram = ram;
        this.ramUsage = ramUsage;
        this.cpuUsage = cpuUsage;
        this.server = server;
    }

    @Override
    public String toString() {
        return "ServerHistory{" +
                "id=" + id +
                ", ram=" + ram +
                ", ramUsage=" + ramUsage +
                ", cpuUsage=" + cpuUsage +
                ", server=" + server +
                '}';
    }
}
