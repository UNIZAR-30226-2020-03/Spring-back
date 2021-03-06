package com.software.upbeat.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="cancion", uniqueConstraints= {@UniqueConstraint(columnNames= {"nombre", "creador"}), @UniqueConstraint(columnNames= "pathMp3")})
public class Cancion {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@Column(name = "nombre")
	private String nombre;
    
    @Column(name = "pathMp3")
	private String pathMp3;
    
    @Column(name = "pathImg")
	private String pathImg;
    
    @Column(name = "duracion")
	private Float duracion;
    
    @Column(name = "fecha")
	private Date fecha;
    
    @Column(name = "reproducciones")
    private Long reproducciones;
    
    /*
	@Lob
	@Type(type="org.hibernate.type.BinaryType")
    @Column(name="fichero")
    private byte[] song;
    */
	////////CREADOR /////////////////////
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="creador")
	private Artista creador;
	
	/*
	 * https://stackoverflow.com/questions/20119142/jackson-multiple-back-reference-properties-with-name-defaultreference
	 */
	@JsonBackReference(value = "artista-canciones")
	public Artista getCreador() {
		return creador;
	}
	
	public void setCreador(Artista creador) {
		this.creador = creador;
	}
	
	public boolean isCreador(Artista creador) {
		return this.creador==creador;
	}
	//////// FIN CREADOR /////////////////////

	public Cancion(){this.duracion = (float) 0; this.reproducciones = (long) 0;}


	public String getPathMp3() {
		return pathMp3;
	}

	public void setPathMp3(String pathMp3) {
		this.pathMp3 = pathMp3;
	}
	
	public String getPathImg() {
		return pathImg;
	}

	public void setPathImg(String pathImg) {
		this.pathImg = pathImg;
	}


	public Cancion(Long id, String nombre, String pathMp3, String pathImg, Float duracion, Date fecha) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pathMp3 = pathMp3;
		this.pathImg = pathImg;
		this.duracion = duracion;
		this.fecha = fecha;
		this.reproducciones = (long) 0;
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
	
	public void addReproduccion() {
		this.reproducciones++;
	}

	public Float getDuracion() {
		return duracion;
	}

	public void setDuracion(Float duracion) {
		this.duracion = duracion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date string) {
		this.fecha = string;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cancion [id=" + id + ", nombre=" + nombre + ", pathMp3=" + pathMp3 + ", pathImg=" + pathImg
				+ ", duracion=" + duracion + ", fecha=" + fecha + ", reproducciones=" + reproducciones + ", creador="
				+ creador + "]";
	}
	
}