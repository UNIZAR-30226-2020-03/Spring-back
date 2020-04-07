package com.proyecto.upbeat.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario extends Cliente{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3424972757253234619L;
	
	@Id
	@GeneratedValue
	Long cod_usuario;
	
	public Usuario(Long cod_cliente, String nombre, String apellidos, String contrasenya, String correo,
			String username, String pais, Long cod_usuario) {
		super(cod_cliente, nombre, apellidos, contrasenya, correo, username, pais);
		// TODO Auto-generated constructor stub
		this.cod_usuario = cod_usuario;
	}

	public Long getCod_usuario() {
		return cod_usuario;
	}

	@Override
	public String toString() {
		return "Usuario [cod_usuario=" + cod_usuario + ", getCod_usuario()=" + getCod_usuario() + ", getUsername()="
				+ getUsername() + ", getPais()=" + getPais() + ", getCod_cliente()=" + getCod_cliente()
				+ ", getNombre()=" + getNombre() + ", getApellidos()=" + getApellidos() + ", getContrasenya()="
				+ getContrasenya() + ", getCorreo()=" + getCorreo() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + "]";
	}

	public void setCod_usuario(Long cod_usuario) {
		this.cod_usuario = cod_usuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
