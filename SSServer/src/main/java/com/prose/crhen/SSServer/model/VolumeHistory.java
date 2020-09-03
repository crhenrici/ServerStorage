package com.prose.crhen.SSServer.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
public class VolumeHistory {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Id
	private long id;
	@Getter
	@Setter
	private double storageReserved;
	@Getter
	@Setter
	private double storageFree;
	@Getter
	@Setter
	private double storageRatio;
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(nullable = false)
	private Volume volume;

	public VolumeHistory() {}

	public VolumeHistory(double storageReserved, double storageFree, double storageRatio, Volume volume) {
		super();
		this.storageReserved = storageReserved;
		this.storageFree = storageFree;
		this.storageRatio = storageRatio;
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "VolumeHistory{" +
				"id=" + id +
				", storageReserved=" + storageReserved +
				", storageFree=" + storageFree +
				", storageRatio=" + storageRatio +
				", volume=" + volume +
				'}';
	}
}
