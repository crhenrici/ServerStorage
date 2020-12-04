package com.prose.crhen.SSServer.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

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
	@Column(name = "date")
	private Date date;
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
	@JsonIgnore
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

	public VolumeHistory(Date date, double storageReserved, double storageFree, double storageRatio, Volume volume) {
		this.date = date;
		this.storageReserved = storageReserved;
		this.storageFree = storageFree;
		this.storageRatio = storageRatio;
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "VolumeHistory{" +
				"id=" + id +
				", date=" + date +
				", storageReserved=" + storageReserved +
				", storageFree=" + storageFree +
				", storageRatio=" + storageRatio +
				", volume=" + volume +
				'}';
	}
}
