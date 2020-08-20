package com.prose.crhen.SSServer.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
public class VolumeHistory {
	
	@Getter
	private long volumeId;
	@Getter
	@Setter
	private int storageReserved;
	@Getter
	@Setter
	private int storageFree;
	@Getter
	@Setter
	private int storageRatio;
	
	public VolumeHistory(long volumeId, int storageReserved, int storageFree, int storageRatio) {
		super();
		this.volumeId = volumeId;
		this.storageReserved = storageReserved;
		this.storageFree = storageFree;
		this.storageRatio = storageRatio;
	}

	@Override
	public String toString() {
		return "VolumeHistory [volumeId=" + volumeId + ", storageReserved=" + storageReserved + ", storageFree="
				+ storageFree + ", storageRatio=" + storageRatio + "]";
	}
}
