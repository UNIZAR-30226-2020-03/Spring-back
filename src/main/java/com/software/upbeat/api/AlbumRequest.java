package com.software.upbeat.api;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.software.upbeat.model.Artista;
import com.software.upbeat.model.Cancion;

public class AlbumRequest {
	
	private Long id;
	
	@NotNull(message="El nombre es requerido")
	private String nombre;
	
	@Size(min=0, max=1000, message="La descripcion debe tener entre {min} y {max} caracteres")
	private String descripcion;
	
	private Float duracion;
    
	private Date fecha;
	
	private int numCanciones;
	
	private Set<Cancion> canciones = new HashSet<Cancion>();
	
	private Artista autor;
	
	private String pathImg;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPathImg() {
		return pathImg;
	}

	public void setPathImg(String pathImg) {
		this.pathImg = pathImg;
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

	public Float getDuracion() {
		return duracion;
	}

	public void setDuracion(Float duracion) {
		this.duracion = duracion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public Artista getAutor() {
		return autor;
	}

	public void setAutor(Artista autor) {
		this.autor = autor;
	}
		

}
