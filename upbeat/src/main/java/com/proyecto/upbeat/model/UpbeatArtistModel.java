package com.proyecto.upbeat.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Artista")
public class UpbeatArtistModel extends UpbeatClientModel{
	
	private String nom_artista;
	private String descripcion;
	
	public UpbeatArtistModel(Long cod_cliente, String client_name, String nombre, String apellidos, String password,
			String correo, String nombre_artista, String descripcion_artista) {
		super(cod_cliente, client_name, nombre, apellidos, password, correo);
		this.nom_artista = nombre_artista;
		this.descripcion = descripcion_artista;
	}

	public String getNom_artista() {
		return nom_artista;
	}

	public void setNom_artista(String nom_artista) {
		this.nom_artista = nom_artista;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((nom_artista == null) ? 0 : nom_artista.hashCode());
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
		UpbeatArtistModel other = (UpbeatArtistModel) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (nom_artista == null) {
			if (other.nom_artista != null)
				return false;
		} else if (!nom_artista.equals(other.nom_artista))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UpbeatArtistModel [nom_artista=" + nom_artista + ", descripcion=" + descripcion + "]";
	}

	
	
}
