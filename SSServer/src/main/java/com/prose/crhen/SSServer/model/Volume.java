package com.prose.crhen.SSServer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Volume {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private long id;
	@Getter
	@Setter
	private long serverId;
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
	private int latestStorageRatio;
	
	public Volume(long serverId, String name, String desc, int latestStorageReserved, int latestStorageFree,
			int latestStorageRatio) {
		super();
		this.serverId = serverId;
		this.name = name;
		this.desc = desc;
		this.latestStorageReserved = latestStorageReserved;
		this.latestStorageFree = latestStorageFree;
		this.latestStorageRatio = latestStorageRatio;
	}

	@Override
	public String toString() {
		return "Volume [id=" + id + ", serverId=" + serverId + ", name=" + name + ", desc=" + desc
				+ ", latestStorageReserved=" + latestStorageReserved + ", latestStorageFree=" + latestStorageFree
				+ ", latestStorageRatio=" + latestStorageRatio + "]";
	}
}
