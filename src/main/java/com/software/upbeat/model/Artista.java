package com.software.upbeat.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Artista extends Cliente{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.AUTO)  // GenerationType.IDENTITY??????
	private Long cod_artista;
	private String nombre_artista;
	private String descripcion;
	@Column(name = "numCanciones")
	private int numCanciones;
	
	//////// CANCIONES /////////////////////
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
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
	@Column(name = "numPodcast")
	private int numPodcast;
	
	public int getNumCanciones() {
		return numCanciones;
	}

	public void setNumCanciones(int numCanciones) {
		this.numCanciones = numCanciones;
	}

	public int getNumPodcast() {
		return numPodcast;
	}

	public void setNumPodcast(int numPodcast) {
		this.numPodcast = numPodcast;
	}


	public void setPodcasts(Set<Podcast> podcasts) {
		this.podcasts = podcasts;
	}
	//////// PODCAST /////////////////////
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Podcast> podcasts; //= new HashSet<Cancion>();
	
	/*
	 * https://stackoverflow.com/questions/20119142/jackson-multiple-back-reference-properties-with-name-defaultreference
	 */
	@JsonBackReference(value = "podcast")
	public Set<Podcast> getPodcasts() {
		return podcasts;
	}

	
	

	public void addPodcast(Podcast podcast) {
		podcasts.add(podcast);
		numPodcast++;
	}

	public void removePodcast(Podcast podcast) {
		podcasts.remove(podcast);
		if(numPodcast<=0) {
			numPodcast=0;
		}
		else {
			numPodcast--;
		}
	}
	
	public boolean containsPodcast(Podcast podcast) {
		return podcasts.contains(podcast);
	}
	//////// FIN PODCAST /////////////////////
	public Artista() {	
	}
	
	public Artista(Long cod_cliente, String nombre, String apellidos, String contrasenya, String correo,
			String username, String pais, Long cod_artista, String nombre_artista, String descripcion) {
		super(cod_cliente, nombre, apellidos, contrasenya, correo, username, pais);
		// TODO Auto-generated constructor stub
		this.cod_artista = cod_artista;
		this.nombre_artista = nombre_artista;
		this.descripcion = descripcion;
	}

    @Column(name = "cod_artista")
	public Long getCod_artista() {
		return cod_artista;
	}

	public void setCod_artista(Long cod_artista) {
		this.cod_artista = cod_artista;
	}
	
	@Column(name = "artistname", nullable = false)
	public String getNombre_artista() {
		return nombre_artista;
	}

	public void setNombre_artista(String nombre_artista) {
		this.nombre_artista = nombre_artista;
	}

	@Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
