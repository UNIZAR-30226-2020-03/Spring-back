package com.software.upbeat.api;

import java.sql.Date;

import com.software.upbeat.model.Artista;
import com.software.upbeat.model.Cliente;




public class CancionResponse {
	private Long id;
	private String nombre;
	private String pathMp3;
	private String pathImg;
	private Float duracion;
	private Date fecha;
	private Long reproducciones;
	private Artista creador;
	//private byte[] song;


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
		this.reproducciones = reproducciones;
	}
	
	public Artista getCreador() {
		return creador;
	}

	public void setCreador(Artista creador) {
		this.creador = creador;
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
