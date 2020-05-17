package com.software.upbeat.api;

import java.io.IOException;

import javax.validation.constraints.NotNull;

import org.springframework.core.io.ClassPathResource;

public class CancionResponse {

	private Long id;

	@NotNull(message="El nombre es requerido")
	private String nombre;
	private String autor;
	/*
	 * PATH REQUERIDO -> SI NO ES ASÍ Y SE CREA PRIMERO SIN PATH: ELIMINAR CONSTRAINT
	 */
	@NotNull(message="La ruta a la canción es requerida")
	private String path;

	@NotNull(message="La url es requerida")
	private String path;


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


	/*
	public byte[] getSong() {
		return song;
	}
	public void setSong(String path) throws IOException {
		ClassPathResource song = new ClassPathResource(path);
		byte[] arraySong = new byte[(int) song.contentLength()];
		song.getInputStream().read(arraySong);
		this.song = arraySong;
	}
	*/
}
