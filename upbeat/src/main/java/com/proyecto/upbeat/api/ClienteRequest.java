package com.proyecto.upbeat.api;

import javax.validation.constraints.*;

public class ClienteRequest {
	
	private Long cod_cliente;
	
	@NotNull(message="El nombre es requerido")
	private String nombre;
	private String apellidos;
	
	@NotNull(message="La contraseña es requerida")
	@Size(min=6, max=30, message="La canción debe tener entre {min} y {max} caracteres")
	private String contrasenya;
	
	private String correo;
	
	@NotNull(message="El nombre de usuario es requerido")
	@Size(min=6, max=30, message="El nombre de usuario debe tener entre {min} y {max} caracteres")
	private String username;
	
	private String pais;
	
	public Long getCod_cliente() {
		return cod_cliente;
	}
	public void setCod_cliente(Long cod_cliente) {
		this.cod_cliente = cod_cliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getContrasenya() {
		return contrasenya;
	}
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}

}