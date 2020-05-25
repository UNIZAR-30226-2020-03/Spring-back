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
@Table(name="podcast", uniqueConstraints= {@UniqueConstraint(columnNames= {"nombre", "temporada", "episodio"}), @UniqueConstraint(columnNames= "pathMp3")})
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
	
    @Column(name = "descripcion")
	private String descripcion;
    
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
	////////CREADOR /////////////////////
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="creador")
	private Artista creador;
	
	/*
	 * https://stackoverflow.com/questions/20119142/jackson-multiple-back-reference-properties-with-name-defaultreference
	 */
	@JsonBackReference(value = "artista-podcast")
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
	public Podcast() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Podcast(Long id, String nombre, int episodio, int temporada, String descripcion, String pathMp3,String pathImg,
			Float duracion, Date fecha, Long reproducciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.episodio = episodio;
		this.temporada = temporada;
		this.descripcion = descripcion;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

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
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((duracion == null) ? 0 : duracion.hashCode());
		result = prime * result + episodio;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((pathMp3 == null) ? 0 : pathMp3.hashCode());
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
		if (pathMp3 == null) {
			if (other.pathMp3 != null)
				return false;
		} else if (!pathMp3.equals(other.pathMp3))
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
				+ ", descripcion=" + descripcion + ", pathMp3=" + pathMp3 + ", duracion=" + duracion
				+ ", fecha=" + fecha + ", reproducciones=" + reproducciones + "]";
	}
    
    
}
