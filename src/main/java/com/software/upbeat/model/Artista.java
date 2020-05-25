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
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Artista extends Cliente{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)  // GenerationType.IDENTITY??????
	@Column(name = "cod_artista")
	private Long cod_artista;
	
	@Column(name = "nombre_artista")
	private String nombre_artista;
	/*
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((albumes == null) ? 0 : albumes.hashCode());
		result = prime * result + ((canciones == null) ? 0 : canciones.hashCode());
		result = prime * result + ((cod_artista == null) ? 0 : cod_artista.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((nombre_artista == null) ? 0 : nombre_artista.hashCode());
		result = prime * result + numCanciones;
		result = prime * result + numPodcast;
		result = prime * result + ((podcasts == null) ? 0 : podcasts.hashCode());
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
		Artista other = (Artista) obj;
		if (albumes == null) {
			if (other.albumes != null)
				return false;
		} else if (!albumes.equals(other.albumes))
			return false;
		if (canciones == null) {
			if (other.canciones != null)
				return false;
		} else if (!canciones.equals(other.canciones))
			return false;
		if (cod_artista == null) {
			if (other.cod_artista != null)
				return false;
		} else if (!cod_artista.equals(other.cod_artista))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (nombre_artista == null) {
			if (other.nombre_artista != null)
				return false;
		} else if (!nombre_artista.equals(other.nombre_artista))
			return false;
		if (numCanciones != other.numCanciones)
			return false;
		if (numPodcast != other.numPodcast)
			return false;
		if (podcasts == null) {
			if (other.podcasts != null)
				return false;
		} else if (!podcasts.equals(other.podcasts))
			return false;
		return true;
	}*/
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "numCanciones")
	private int numCanciones;
	
	//////// CANCIONES /////////////////////
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="creador")
	private Set<Cancion> canciones; //= new HashSet<Cancion>();
	
	/*
	 * https://stackoverflow.com/questions/20119142/jackson-multiple-back-reference-properties-with-name-defaultreference
	 */
	@JsonBackReference(value = "artista-canciones")
	public Set<Cancion> getCanciones() {
		return canciones;
	}

	public void setCanciones(Set<Cancion> canciones) {
		this.canciones = canciones;
	}
	

	public void addCancion(Cancion cancion) {
		canciones.add(cancion);
		cancion.setCreador(this);
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
		System.out.println("Llego");
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
		podcast.setCreador(this);
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
	
	// ÁLBUMES

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="autor")
	private Set<Album> albumes; //= new HashSet<Album>();
	
	@JsonManagedReference(value = "autor-album")
	public Set<Album> getAlbumes() {
		return albumes;
	}

	public void setAlbumes(Set<Album> albumes) {
		this.albumes = albumes;
	}

	public void addAlbum(Album album) {
		albumes.add(album);
		album.setAutor(this);
		System.out.println("------------------");
	}

	public void removeAlbum(Album album) {
		albumes.remove(album);
		album.setAutor(null);
	}
	
	public boolean containsAlbum(Album album) {
		return albumes.contains(album);
	}
	// FIN ÁLBUMES
	
	public Artista() {	
	}
	
	public Artista(Long cod_cliente, String nombre, String apellidos, String contrasenya, String correo,String pathImg,
			String username, String pais, Long cod_artista, String nombre_artista, String descripcion) {
		super(cod_cliente, nombre, apellidos,pathImg, contrasenya, correo, username, pais);
		// TODO Auto-generated constructor stub
		this.cod_artista = cod_artista;
		this.nombre_artista = nombre_artista;
		this.descripcion = descripcion;
	}
	
	public Long getCod_artista() {
		return cod_artista;
	}

	public void setCod_artista(Long cod_artista) {
		this.cod_artista = cod_artista;
	}
	
	public String getNombre_artista() {
		return nombre_artista;
	}

	public void setNombre_artista(String nombre_artista) {
		this.nombre_artista = nombre_artista;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/*@Override
	public String toString() {
		return "Artista [cod_artista=" + cod_artista + ", nombre_artista=" + nombre_artista + ", descripcion="
				+ descripcion + ", numCanciones=" + numCanciones + ", canciones=" + canciones + ", numPodcast="
				+ numPodcast + ", podcasts=" + podcasts + ", albumes=" + albumes + "]";
	}*/
	
	
}
