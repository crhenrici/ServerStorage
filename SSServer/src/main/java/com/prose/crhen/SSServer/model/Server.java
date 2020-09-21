package com.prose.crhen.SSServer.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "server")
public class Server {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Id
	@Column(name = "id")
	private long id;
	@Getter
	@Setter
	@Column(name = "name")
	private String name;
	@Getter
	@Setter
	@Column(name = "full_capacity")
	private double fullCapacity;
	@Getter
	@Setter
	@Column(name = "latest_storage_reserved")
	private double latestStorageReserved;
	@Getter
	@Setter
	@Column(name = "latest_storage_free")
	private double latestStorageFree;
	@Getter
	@Setter
	@Column(name = "latest_storage_ratio")
	private double latestStorageRatio;
	@Getter
	@Setter
	@Column(name = "ram", nullable = true)
	private int ram;
	@Getter
	@Setter
	@Column(name = "cpu_usage", nullable = true)
	private double cpuUsage;
	@Getter
	@Setter
	@OneToMany(mappedBy = "server", fetch = FetchType.EAGER,
				cascade = CascadeType.ALL)
	private Set<Volume> volumes;
	@Getter
	@Setter
	@OneToMany(mappedBy = "server", fetch = FetchType.EAGER,
				cascade = CascadeType.ALL)
	private Set<ServerHistory> serverHistories;

	public Server() {

	}

	public Server(String name, double fullCapacity, double latestStorageReserved, double latestStorageFree, double latestStorageRatio) {
		this.name = name;
		this.fullCapacity = fullCapacity;
		this.latestStorageReserved = latestStorageReserved;
		this.latestStorageFree = latestStorageFree;
		this.latestStorageRatio = latestStorageRatio;
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
