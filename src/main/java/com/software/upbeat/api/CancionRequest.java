package com.software.upbeat.api;



import java.sql.Date;

import javax.validation.constraints.NotNull;

import com.software.upbeat.model.Artista;



public class CancionRequest {
	private Long id;
	@NotNull(message="El nombre es requerido")
	private String nombre;
	@NotNull(message="El artista es requerido")
	private Artista creador;
	@NotNull(message="La url es requerida")
	private String pathMp3;
	@NotNull(message="La imagen es requerida")
	private String pathImg;
	private Float duracion;
	private Date fecha;
	private Long reproducciones;
	//private byte[] song;


	public Artista getCreador() {
		return creador;
	}
	public String getPathMp3() {
		return pathMp3;
	}
	public void setPathMp3(String pathMp3) {
		this.pathMp3 = pathMp3;
	}
	public String getPathImg() {
		return pathImg;
	}
	public void setPathImg(String pathImg) {
		this.pathImg = pathImg;
	}
	public void setCreador(Artista creador) {
		this.creador = creador;
	}

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
		this.reproducciones =reproducciones;
	}

	/*
	public byte[] getSong() {
		return song;
	}

	public void setSong(String media) throws IOException {
		ClassPathResource song = new ClassPathResource(path);
		byte[] arraySong = new byte[(int) song.contentLength()];
		song.getInputStream().read(arraySong);
		this.song = arraySong;
	}

	public void setSong(byte[] song){

		this.song = song;
	}
	*/
}
