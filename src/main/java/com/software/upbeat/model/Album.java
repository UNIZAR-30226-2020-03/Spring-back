package com.software.upbeat.model;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name = "album", uniqueConstraints= {@UniqueConstraint(columnNames= {"nombre", "autor"})})
public class Album implements Serializable{
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
	
	@Column(name = "duracion")
	private Float duracion;
    
    @Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "numCanciones")
	private int numCanciones;
	
	//////// CANCIONES /////////////////////
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Cancion> canciones; //= new HashSet<Cancion>();
	
	/*
	 * https://stackoverflow.com/questions/20119142/jackson-multiple-back-reference-properties-with-name-defaultreference
	 */
	@JsonBackReference(value = "canciones_album")
	public Set<Cancion> getCanciones() {
		return canciones;
	}

	public void setCanciones(Set<Cancion> canciones) {
		this.canciones = canciones;
	}
	

	public void addCancion(Cancion cancion) {
		if(cancion.getCreador() == this.autor) {
			System.out.println("Es mismo autor");
			if (!containsCancion(cancion)) {
				System.out.println("No estaba la canción");
				canciones.add(cancion);
				numCanciones++;
				this.duracion = this.duracion + cancion.getDuracion();
				System.out.println("Cancion añadida al album");
			}
		}
	}

	public void removeCancion(Cancion cancion) {
		canciones.remove(cancion);
		
		//Para evitar posibles errores
		if(numCanciones<=0) {
			numCanciones=0;
		}
		else {
			numCanciones--;
		}
		// Para evitar posibles errores
		if(this.duracion - cancion.getDuracion() <= 0) {
			this.duracion = (float) 0;
		}
		else {
			this.duracion = this.duracion - cancion.getDuracion();
		}
	}
	
	public Album(Long id, String nombre, String descripcion, Date fecha) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = (float) 0;
		this.fecha = fecha;
		this.numCanciones = 0;
	}

	public boolean containsCancion(Cancion cancion) {
		return canciones.contains(cancion);
	}
	//////// FIN CANCIONES /////////////////////
	
	////////AUTOR /////////////////////
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="autor")
	private Artista autor;
	
	/*
	 * https://stackoverflow.com/questions/20119142/jackson-multiple-back-reference-properties-with-name-defaultreference
	 */
	@JsonBackReference(value = "autor-album")
	public Artista getAutor() {
		return autor;
	}
	
	public void setAutor(Artista autor) {
		this.autor = autor;
	}
	
	public boolean isAutor(Artista autor) {
		return this.autor==autor;
	}
	//////// FIN AUTOR /////////////////////
	
	public Album() {this.numCanciones = 0; this.duracion = (float) 0;}

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getNumCanciones() {
		return numCanciones;
	}

	public void setNumCanciones(int numCanciones) {
		this.numCanciones = numCanciones;
	}
	
	@Override
	public String toString() {
		return "Album [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", duracion=" + duracion
				+ ", fecha=" + fecha + ", numCanciones=" + numCanciones + ", canciones=" + canciones + ", autor="
				+ autor + "]";
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
		Album other = (Album) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Album(Long id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.numCanciones = 0;
		this.duracion = (float) 0;
	}
		

}