package com.software.upbeat.api;

import java.util.HashSet;
import java.util.Set;

import com.software.upbeat.model.Cancion;
import com.software.upbeat.model.Cliente;

public class PlaylistResponse {

	private Long id;
	private String nombre;
	private String descripcion;
	private int numCanciones;
	private Set<Cancion> canciones = new HashSet<Cancion>();
	private Cliente creador;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getNumCanciones() {
		return numCanciones;
	}

	public void setNumCanciones(int numCanciones) {
		this.numCanciones = numCanciones;
	}

	public Set<Cancion> getCanciones() {
		return canciones;
	}

	public void setCanciones(Set<Cancion> canciones) {
		this.canciones = canciones;
	}

	public Cliente getCreador() {
		return creador;
	}

	public void setCreador(Cliente creador) {
		this.creador = creador;
	}
}