package com.software.upbeat.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="podcast", uniqueConstraints= {@UniqueConstraint(columnNames= "nombre"), @UniqueConstraint(columnNames= "temporada"), @UniqueConstraint(columnNames= "episodio")})
public class Podcast {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
	
	@Column(name = "nombre")
	private String nombre;
    
	@Column(name = "episodio")
	private int episodio;
	
	@Column(name = "temporada")
	private int temporada;
	
    @Column(name = "artista")
	private String autor;
	
    @Column(name = "descripcion")
	private String descripcion;
    
    @Column(name = "path")
	private String path;
    
    @Column(name = "duracion")
	private Float duracion;
    
    @Column(name = "fecha")
	private Date fecha;
    
    @Column(name = "reproducciones")
    private Long reproducciones;

	public Podcast() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Podcast(Long id, String nombre, int episodio, int temporada, String autor, String descripcion, String path,
			Float duracion, Date fecha, Long reproducciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.episodio = episodio;
		this.temporada = temporada;
		this.autor = autor;
		this.descripcion = descripcion;
		this.path = path;
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

	public int getEpisodio() {
		return episodio;
	}

	public void setEpisodio(int episodio) {
		this.episodio = episodio;
	}

	public int getTemporada() {
		return temporada;
	}

	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public Long getReproducciones() {
		return reproducciones;
	}

	public void setReproducciones(Long reproducciones) {
		this.reproducciones = reproducciones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((duracion == null) ? 0 : duracion.hashCode());
		result = prime * result + episodio;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((reproducciones == null) ? 0 : reproducciones.hashCode());
		result = prime * result + temporada;
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
		Podcast other = (Podcast) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (duracion == null) {
			if (other.duracion != null)
				return false;
		} else if (!duracion.equals(other.duracion))
			return false;
		if (episodio != other.episodio)
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
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
		if (temporada != other.temporada)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Podcast [id=" + id + ", nombre=" + nombre + ", episodio=" + episodio + ", temporada=" + temporada
				+ ", autor=" + autor + ", descripcion=" + descripcion + ", path=" + path + ", duracion=" + duracion
				+ ", fecha=" + fecha + ", reproducciones=" + reproducciones + "]";
	}
    
    
}
