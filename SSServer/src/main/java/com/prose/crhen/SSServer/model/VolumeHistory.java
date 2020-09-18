package com.prose.crhen.SSServer.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "volume_history")
public class VolumeHistory {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Id
	@Column(name = "id")
	private long id;
	@Getter
	@Setter
	@Column(name = "storage_reserved")
	private double storageReserved;
	@Getter
	@Setter
	@Column(name = "storage_free")
	private double storageFree;
	@Getter
	@Setter
	@Column(name = "storage_ratio")
	private double storageRatio;
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "volume_id", nullable = false)
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
