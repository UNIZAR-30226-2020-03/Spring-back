package com.software.upbeat.api;

import java.util.Set;

import com.software.upbeat.model.Album;
import com.software.upbeat.model.Cancion;
import com.software.upbeat.model.Podcast;

public class ArtistaResponse extends ClienteResponse{
	
	private Long cod_artista;
	private String nombre_artista;
	private String descripcion;
	private int numCanciones;
	private Set<Cancion> artistSongs;
	private Set<Podcast> artistPodcast;
	private Set<Album> albumes;
	
	
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
	public int getNumCanciones() {
		return numCanciones;
	}
	public void setNumCanciones(int numCanciones) {
		this.numCanciones = numCanciones;
	}
	public Set<Cancion> getArtistSongs() {
		return artistSongs;
	}
	public void setArtistSongs(Set<Cancion> artistSongs) {
		this.artistSongs = artistSongs;
	}
	public Set<Podcast> getArtistPodcast() {
		return artistPodcast;
	}
	public void setArtistPodcast(Set<Podcast> artistPodcast) {
		this.artistPodcast = artistPodcast;
	}
	public Set<Album> getAlbumes() {
		return albumes;
	}
	public void setAlbumes(Set<Album> albumes) {
		this.albumes = albumes;
	}
	

}
