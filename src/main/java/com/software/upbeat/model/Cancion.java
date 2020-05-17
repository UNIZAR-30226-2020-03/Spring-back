package com.software.upbeat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

@Entity
@Table(name="cancion", uniqueConstraints= {@UniqueConstraint(columnNames= "nombre"), @UniqueConstraint(columnNames= "artrista")})
public class Cancion {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@Column(name = "nombre")
	private String nombre;
    
    @Column(name = "artista")
	private Artista autor;
	
    @Column(name = "path")
	private String path;
    
    @Column(name = "reproducciones")
    private Long reproducciones;
    
    /*
	@Lob
	@Type(type="org.hibernate.type.BinaryType")
    @Column(name="fichero")
    private byte[] song;
    */
	
	public Cancion(){}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((reproducciones == null) ? 0 : reproducciones.hashCode());
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
		Cancion other = (Cancion) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
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
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (reproducciones == null) {
			if (other.reproducciones != null)
				return false;
		} else if (!reproducciones.equals(other.reproducciones))
			return false;
		return true;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Cancion(long id, String nombre, Artista autor, byte[] song){
		this.id = id;
		this.nombre = nombre;
		this.autor = autor;
		this.reproducciones = (long) 0;
		//this.song = song;
	}

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

	public Artista getAutor() {
		return autor;
	}

	public void setAutor(Artista autor) {
		this.autor = autor;
	}
	
	
	/*
	public byte[] getSong() {
		return song;
	}

	public void setSong(byte[] song) {
		this.song = song;
	}
	*/
	
	public Long getReproducciones() {
		return reproducciones;
	}

	public void setReproducciones(Long reproducciones) {
		this.reproducciones = reproducciones;
	}
	
	
	
}