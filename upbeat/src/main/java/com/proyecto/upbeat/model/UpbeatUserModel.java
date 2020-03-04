package com.proyecto.upbeat.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Usuario")
public class UpbeatUserModel extends UpbeatClientModel{
	
	private String nom_user;
	
	public UpbeatUserModel(Long cod_cliente, String client_name, String nombre, String apellidos, String password,
			String correo, String nombre_usuario) {
		super(cod_cliente, client_name, nombre, apellidos, password, correo);
		this.nom_user = nombre_usuario;
	}

	public String getNom_user() {
		return nom_user;
	}

	public void setNom_user(String nom_user) {
		this.nom_user = nom_user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nom_user == null) ? 0 : nom_user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpbeatUserModel other = (UpbeatUserModel) obj;
		if (nom_user == null) {
			if (other.nom_user != null)
				return false;
		} else if (!nom_user.equals(other.nom_user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UpbeatUserModel [nom_user=" + nom_user + "]";
	}
	
}
