package com.software.upbeat.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cliente")
public class Cliente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod_cliente;
	private String nombre;
	private String apellidos;
	private String contrasenya;
	private String correo;
	private String username;
	private String pais;
	
	public Cliente() {	
	}
	
	public Cliente(Long cod_cliente, String nombre, String apellidos, String contrasenya, String correo,
			String username, String pais) {
		super();
		this.cod_cliente = cod_cliente;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.contrasenya = contrasenya;
		this.correo = correo;
		this.username = username;
		this.pais = pais;
	}

	@Column(name = "cod_cliente", nullable = false)
	public Long getCod_cliente() {
		return cod_cliente;
	}

	public void setCod_cliente(Long cod_cliente) {
		this.cod_cliente = cod_cliente;
	}
	
	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "apellidos", nullable = false)
	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@Column(name = "password", nullable = false)
	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	@Column(name = "correo", nullable = false)
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Column(name = "username", nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "pais", nullable = false)
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "Cliente [cod_cliente=" + cod_cliente + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", contrasenya=" + contrasenya + ", correo=" + correo + ", username=" + username + ", pais=" + pais
				+ "]";
	}

}
