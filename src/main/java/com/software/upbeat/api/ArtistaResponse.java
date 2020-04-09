package com.software.upbeat.api;

public class ArtistaResponse extends ClienteResponse{
	
	private Long cod_artista;
	private String nombre_artista;
	private String descripcion;
	
	
	public Long getCod_artista() {
		return cod_artista;
	}
	public void setCod_artista(Long cod_artista) {
		this.cod_artista = cod_artista;
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
