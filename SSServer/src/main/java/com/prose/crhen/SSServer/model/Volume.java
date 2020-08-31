package com.prose.crhen.SSServer.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Volume {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Id
	private long id;
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private String desc;
	@Getter
	@Setter
	private int latestStorageReserved;
	@Getter
	@Setter
	private int latestStorageFree;
	@Getter
	@Setter
	private double latestStorageRatio;
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(nullable = false)
	public Server server;

	public Volume() {}
	
	public Volume(String name, String desc, int latestStorageReserved, int latestStorageFree,
			double latestStorageRatio, Server server) {
		super();
		this.name = name;
		this.desc = desc;
		this.latestStorageReserved = latestStorageReserved;
		this.latestStorageFree = latestStorageFree;
		this.latestStorageRatio = latestStorageRatio;
		this.server = server;
	}

	@Override
	public String toString() {
		return "Volume{" +
				"id=" + id +
				", name='" + name + '\'' +
				", desc='" + desc + '\'' +
				", latestStorageReserved=" + latestStorageReserved +
				", latestStorageFree=" + latestStorageFree +
				", latestStorageRatio=" + latestStorageRatio +
				", server=" + server +
				'}';
	}
}
