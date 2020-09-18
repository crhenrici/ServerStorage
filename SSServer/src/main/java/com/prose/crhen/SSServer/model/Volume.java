package com.prose.crhen.SSServer.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "volume")
public class Volume {
	
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
	@Column(name = "desc")
	private String desc;
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
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "server_id", nullable = false)
	private Server server;
	@Getter
	@Setter
	@OneToMany(mappedBy = "volume", fetch = FetchType.EAGER,
				cascade = CascadeType.ALL)
	private Set<VolumeHistory> volumeHistories;

	public Volume() {}
	
	public Volume(String name, String desc, double fullCapacity, double latestStorageReserved, double latestStorageFree,
			double latestStorageRatio, Server server) {
		super();
		this.name = name;
		this.desc = desc;
		this.fullCapacity = fullCapacity;
		this.latestStorageReserved = latestStorageReserved;
		this.latestStorageFree = latestStorageFree;
		this.latestStorageRatio = latestStorageRatio;
		this.server = server;
	}

	@Override
	public String toString() {
		return "Volume [id=" + id + ", name=" + name + ", desc=" + desc + ", fullCapacity=" + fullCapacity
				+ ", latestStorageReserved=" + latestStorageReserved + ", latestStorageFree=" + latestStorageFree
				+ ", latestStorageRatio=" + latestStorageRatio + ", server=" + server + "]";
	}
	
	


}
