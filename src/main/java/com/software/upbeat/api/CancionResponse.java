package com.software.upbeat.api;

import java.io.IOException;

import javax.validation.constraints.NotNull;

import org.springframework.core.io.ClassPathResource;

public class CancionResponse {
private Long id;
	
	@NotNull(message="El nombre es requerido")
	private String nombre;
	private String autor;
	
	@NotNull(message="La cancion es requerida")
	private byte[] song;
	
	public Long getId_cancion() {
		return id;
	}
	public void setId_cancion(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public byte[] getSong() {
		return song;
	}
	public void setSong(String path) throws IOException {
		ClassPathResource song = new ClassPathResource(path);
		byte[] arraySong = new byte[(int) song.contentLength()];
		song.getInputStream().read(arraySong);
		this.song = arraySong;
	}
}
