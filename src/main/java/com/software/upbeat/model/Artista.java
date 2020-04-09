package com.software.upbeat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Artista extends Cliente{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cod_artista;
	private String nombre_artista;
	private String descripcion;
	
	public Artista() {	
	}
	
	public Artista(Long cod_cliente, String nombre, String apellidos, String contrasenya, String correo,
			String username, String pais, Long cod_artista, String nombre_artista, String descripcion) {
		super(cod_cliente, nombre, apellidos, contrasenya, correo, username, pais);
		// TODO Auto-generated constructor stub
		this.cod_artista = cod_artista;
		this.nombre_artista = nombre_artista;
		this.descripcion = descripcion;
	}

    @Column(name = "cod_artista")
	public Long getCod_artista() {
		return cod_artista;
	}

	public void setCod_artista(Long cod_artista) {
		this.cod_artista = cod_artista;
	}
	
	@Column(name = "artistname", nullable = false)
	public String getNombre_artista() {
		return nombre_artista;
	}

	public void setNombre_artista(String nombre_artista) {
		this.nombre_artista = nombre_artista;
	}

	@Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}