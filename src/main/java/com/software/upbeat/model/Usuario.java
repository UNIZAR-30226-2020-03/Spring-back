package com.software.upbeat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Usuario extends Cliente{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cod_usuario;

	public Usuario() {	
	}

	public Usuario(Long cod_cliente, String nombre, String apellidos, String contrasenya, String correo,
			String username, String pais, Long codigo_usuario) {
		super(cod_cliente, nombre, apellidos, contrasenya, correo, username, pais);
		// TODO Auto-generated constructor stub
		this.cod_usuario = codigo_usuario;
	}

	@Column(name = "cod_usuario")
	public Long getCod_usuario() {
		return cod_usuario;
	}

	public void setCod_usuario(Long cod_usuario) {
		this.cod_usuario = cod_usuario;
	}
	
}
