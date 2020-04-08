package com.proyecto.upbeat.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Artista extends Cliente{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4548058923899754311L;
	
	@Id
	@GeneratedValue
	Long cod_artista;
	String nombre_artista;
	String descripcion;
	
	public Artista(Long cod_cliente, String nombre, String apellidos, String contrasenya, String correo,
			String username, String pais, Long cod_artista, String nombre_artista, String descripcion) {
		super(cod_cliente, nombre, apellidos, contrasenya, correo, username, pais);
		// TODO Auto-generated constructor stub
		this.cod_artista = cod_artista;
		this.nombre_artista = nombre_artista;
		this.descripcion = descripcion;
	}

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Artista [cod_artista=" + cod_artista + ", nombre_artista=" + nombre_artista + ", descripcion="
				+ descripcion + ", getCod_artista()=" + getCod_artista() + ", getNombre_artista()="
				+ getNombre_artista() + ", getDescripcion()=" + getDescripcion() + ", getUsername()=" + getUsername()
				+ ", getPais()=" + getPais() + ", getCod_cliente()=" + getCod_cliente() + ", getNombre()=" + getNombre()
				+ ", getApellidos()=" + getApellidos() + ", getContrasenya()=" + getContrasenya() + ", getCorreo()="
				+ getCorreo() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + "]";
	}

}
