package com.proyecto.upbeat.api;

import javax.validation.constraints.*;

public class ArtistaRequest extends ClienteRequest{
	
	private Long cod_usuario;
	
	@NotNull(message="El nombre de artista es requerido")
	private String nombre_artista;
	private String descripcion;
	
	public Long getCod_usuario() {
		return cod_usuario;
	}
	public void setCod_usuario(Long cod_usuario) {
		this.cod_usuario = cod_usuario;
	}
	public String getNombre_artista() {
		return nombre_artista;
	}
	public void setNombre_artista(String nombre_artista) {
		this.nombre_artista = nombre_artista;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}