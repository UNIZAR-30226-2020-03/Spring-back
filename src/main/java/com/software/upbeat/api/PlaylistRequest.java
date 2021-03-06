package com.software.upbeat.api;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.*;

import com.software.upbeat.model.Cancion;
import com.software.upbeat.model.Cliente;

public class PlaylistRequest {

	private Long id;
	
	@NotNull(message="El nombre es requerido")
	private String nombre;
	private String pathImg;
	public Long getId() {
		return id;
	}

	public String getPathImg() {
		return pathImg;
	}

	public void setPathImg(String pathImg) {
		this.pathImg = pathImg;
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

	@Size(min=0, max=1000, message="La descripcion debe tener entre {min} y {max} caracteres")
	private String descripcion;
	
	private int numCanciones;
	
	private Set<Cancion> canciones = new HashSet<Cancion>();
	
	private Cliente creador;
}
