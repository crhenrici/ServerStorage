package com.prose.crhen.SSServer.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
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

	public Server(String name) {
		this.name = name;
	}

	public Server(String name, int ram, double cpuUsage) {
		super();
		this.name = name;
		this.ram = ram;
		this.cpuUsage = cpuUsage;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Server server = (Server) o;
		return id == server.id &&
				ram == server.ram &&
				Double.compare(server.cpuUsage, cpuUsage) == 0 &&
				Objects.equals(name, server.name) &&
				Objects.equals(volumes, server.volumes) &&
				Objects.equals(serverHistories, server.serverHistories);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, ram, cpuUsage, volumes, serverHistories);
	}

	@Override
	public String toString() {
		return "Server{" +
				"id=" + id +
				", name='" + name + '\'' +
				", ram=" + ram +
				", cpuUsage=" + cpuUsage +
				", volumes=" + volumes +
				", serverHistories=" + serverHistories +
				'}';
	}
}
