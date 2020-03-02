package com.proyecto.upbeat.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cliente")
public class UpbeatClientModel {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cod_cliente;
	
	private String client_name;
	private String nombre;
	private String apellidos;
	private String password;
	private String correo;
	
	public UpbeatClientModel(Long cod_cliente, String client_name, String nombre, String apellidos, String password,
			String correo) {
		super();
		this.cod_cliente = cod_cliente;
		this.client_name = client_name;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.password = password;
		this.correo = correo;
	}

	public Long getCod_cliente() {
		return cod_cliente;
	}

	public void setCod_cliente(Long cod_cliente) {
		this.cod_cliente = cod_cliente;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((client_name == null) ? 0 : client_name.hashCode());
		result = prime * result + ((cod_cliente == null) ? 0 : cod_cliente.hashCode());
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpbeatClientModel other = (UpbeatClientModel) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (client_name == null) {
			if (other.client_name != null)
				return false;
		} else if (!client_name.equals(other.client_name))
			return false;
		if (cod_cliente == null) {
			if (other.cod_cliente != null)
				return false;
		} else if (!cod_cliente.equals(other.cod_cliente))
			return false;
		if (correo == null) {
			if (other.correo != null)
				return false;
		} else if (!correo.equals(other.correo))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UpbeatClientModel [cod_cliente=" + cod_cliente + ", client_name=" + client_name + ", nombre=" + nombre
				+ ", apellidos=" + apellidos + ", password=" + password + ", correo=" + correo + "]";
	}
	
	
}
