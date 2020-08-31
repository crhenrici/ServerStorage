package com.prose.crhen.SSServer.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
public class VolumeHistory {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Id
	private long id;
	@Getter
	@Setter
	private int storageReserved;
	@Getter
	@Setter
	private int storageFree;
	@Getter
	@Setter
	private int storageRatio;

	public VolumeHistory() {}

	public VolumeHistory(long id, int storageReserved, int storageFree, int storageRatio) {
		super();
		this.id = id;
		this.storageReserved = storageReserved;
		this.storageFree = storageFree;
		this.storageRatio = storageRatio;
	}

	@Override
	public String toString() {
		return "VolumeHistory [id=" + id + ", storageReserved=" + storageReserved + ", storageFree="
				+ storageFree + ", storageRatio=" + storageRatio + "]";
	}
}
