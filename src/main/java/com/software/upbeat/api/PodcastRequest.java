package com.software.upbeat.api;

import java.sql.Date;

import javax.validation.constraints.NotNull;

public class PodcastRequest {
	private Long id;
	@NotNull(message="El nombre es requerido")
	private String nombre;
	@NotNull(message="El episodio es requerido")
	private int episodio;
	@NotNull(message="La temporada es requerida")
	private int temporada;
	@NotNull(message="El artista es requerido")
	private String autor;
	@NotNull(message="La url es requerida")
	private String path;
	private String descripcion;
	private Float duracion;
	private Date fecha;
	private Long reproducciones;
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
	public int getEpisodio() {
		return episodio;
	}
	public void setEpisodio(int episodio) {
		this.episodio = episodio;
	}
	public int getTemporada() {
		return temporada;
	}
	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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
	public Long getReproducciones() {
		return reproducciones;
	}
	public void setReproducciones(Long reproducciones) {
		this.reproducciones = reproducciones;
	}
}
