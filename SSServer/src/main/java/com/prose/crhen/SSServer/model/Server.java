package com.prose.crhen.SSServer.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
public class Server {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Id
	private long id;
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
	@OneToMany(mappedBy = "server", fetch = FetchType.LAZY,
				cascade = CascadeType.ALL)
	private Set<Volume> volumes;
	@Getter
	@Setter
	@OneToMany(mappedBy = "server", fetch = FetchType.LAZY,
				cascade = CascadeType.ALL)
	private Set<ServerHistory> serverHistories;

	public Server() {

	}
	
	public Server(String name, double fullCapacity, double latestStorageReserved, double latestStorageFree, double latestStorageRatio, int ram, double cpuUsage) {
		super();
		this.name = name;
		this.fullCapacity = fullCapacity;
		this.latestStorageReserved = latestStorageReserved;
		this.latestStorageFree = latestStorageFree;
		this.latestStorageRatio = latestStorageRatio;
		this.ram = ram;
		this.cpuUsage = cpuUsage;
	}

	@Override
	public String toString() {
		return "Server{" +
				"id=" + id +
				", name='" + name + '\'' +
				", fullCapacity=" + fullCapacity +
				", storageReserved=" + latestStorageReserved +
				", storageFree=" + latestStorageFree +
				", storageRatio=" + latestStorageRatio +
				", ram=" + ram +
				", cpuUsage=" + cpuUsage +
				'}';
	}
}
