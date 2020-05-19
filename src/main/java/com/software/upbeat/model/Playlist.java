package com.software.upbeat.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "playlist", uniqueConstraints= {@UniqueConstraint(columnNames= {"nombre", "creador"})})
public class Playlist implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "pathImg")
	private String pathImg;
	
	@Column(name = "numCanciones")
	private int numCanciones;
	
	//////// CANCIONES /////////////////////
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Cancion> canciones; //= new HashSet<Cancion>();
	
	/*
	 * https://stackoverflow.com/questions/20119142/jackson-multiple-back-reference-properties-with-name-defaultreference
	 */
	@JsonBackReference(value = "canciones")
	public Set<Cancion> getCanciones() {
		return canciones;
	}

	public void setCanciones(Set<Cancion> canciones) {
		this.canciones = canciones;
	}
	

	public void addCancion(Cancion cancion) {
		canciones.add(cancion);
		numCanciones++;
	}

	public void removeCancion(Cancion cancion) {
		canciones.remove(cancion);
		if(numCanciones<=0) {
			numCanciones=0;
		}
		else {
			numCanciones--;
		}
	}
	
	public boolean containsCancion(Cancion cancion) {
		return canciones.contains(cancion);
	}
	//////// FIN CANCIONES /////////////////////
	
	////////CREADOR /////////////////////
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="creador")
	private Cliente creador;
	
	/*
	 * https://stackoverflow.com/questions/20119142/jackson-multiple-back-reference-properties-with-name-defaultreference
	 */
	@JsonBackReference(value = "cliente-playlists")
	public Cliente getCreador() {
		return creador;
	}
	
	public void setCreador(Cliente creador) {
		this.creador = creador;
	}
	
	public boolean isCreador(Cliente creador) {
		return this.creador==creador;
	}
	//////// FIN CREADOR /////////////////////
	
	public Playlist() {this.numCanciones = 0;}

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

	public String getPathImg() {
		return pathImg;
	}

	public void setPathImg(String pathImg) {
		this.pathImg = pathImg;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getNumCanciones() {
		return numCanciones;
	}

	public void setNumCanciones(int numCanciones) {
		this.numCanciones = numCanciones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((canciones == null) ? 0 : canciones.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + numCanciones;
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
		Playlist other = (Playlist) obj;
		if (canciones == null) {
			if (other.canciones != null)
				return false;
		} else if (!canciones.equals(other.canciones))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (numCanciones != other.numCanciones)
			return false;
		return true;
	}

	public Playlist(Long id, String nombre, String descripcion,String pathImg) {
		super();
		this.id = id;
		this.pathImg= pathImg;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.numCanciones = 0;
	}
	
	
	

}
