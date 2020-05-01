package com.software.upbeat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="cancion")
public class Cancion {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	

	@Column(name = "nombre")
	private String nombre;
    
    @Column(name = "artista")
	private String autor;
	
	@Lob
	@Type(type="org.hibernate.type.BinaryType")
    @Column(name="fichero")
    private byte[] song;
	
	public Cancion(){}
	
	public Cancion(long id, String nombre, Artista autor, byte[] song){
		this.id = id;
		this.nombre = nombre;
		this.autor = autor.getNombre_artista();
		this.song = song;
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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public byte[] getSong() {
		return song;
	}

	public void setSong(byte[] song) {
		this.song = song;
	}
	
	
}