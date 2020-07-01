package com.prose.crhen.SSServer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Server {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Id
	private long id;
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private int fullCapacity;
	@Getter
	@Setter
	private int storageReserved;
	@Getter
	@Setter
	private int storageFree;
	@Getter
	@Setter
	private double storageRatio;
	
	public Server(long id, String name, int fullCapacity, int storageReserved, int storageFree, double storageRatio) {
		super();
		this.id = id;
		this.name = name;
		this.fullCapacity = fullCapacity;
		this.storageReserved = storageReserved;
		this.storageFree = storageFree;
		this.storageRatio = storageRatio;
	}

	@Override
	public String toString() {
		return "Server [id=" + id + ", name=" + name + ", fullCapacity=" + fullCapacity + ", storageReserved="
				+ storageReserved + ", storageFree=" + storageFree + ", storageRatio=" + storageRatio + "]";
	}
	
	

}
