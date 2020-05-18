package com.software.upbeat.api;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.software.upbeat.model.Cancion;
import com.software.upbeat.model.Cliente;
import com.software.upbeat.model.Playlist;

public class ClienteRequest {
	
	private Long cod_cliente;
	
	@NotNull(message="El nombre es requerido")
	private String nombre;
	private String apellidos;
	
	@NotNull(message="La contraseña es requerida")
	@Size(min=6, max=30, message="La contraseña debe tener entre {min} y {max} caracteres")
	private String contrasenya;
	
	@NotNull(message="El correo es requerido")
	private String correo;
	
	@NotNull(message="El nombre de usuario es requerido")
	@Size(min=6, max=30, message="El nombre de usuario debe tener entre {min} y {max} caracteres")
	private String username;
	
	private String pais;
	
	private Set<Cliente> amigos = new HashSet<Cliente>();
	
	private Set<Playlist> playlists = new HashSet<Playlist>();
	
	private Set<Playlist> favPlaylists = new HashSet<Playlist>();
	
	private Set<Cancion> favSongs;
	
	public Long getCod_cliente() {
		return cod_cliente;
	}
	public void setCod_cliente(Long cod_cliente) {
		this.cod_cliente = cod_cliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getContrasenya() {
		return contrasenya;
	}
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public Set<Cliente> getAmigos() {
		return amigos;
	}
	public void setAmigos(Set<Cliente> amigos) {
		this.amigos = amigos;
	}
	public Set<Playlist> getPlaylists() {
		return playlists;
	}
	public void setPlaylists(Set<Playlist> playlists) {
		this.playlists = playlists;
	}
	public Set<Playlist> getFavPlaylists() {
		return favPlaylists;
	}
	public void setFavPlaylists(Set<Playlist> favPlaylists) {
		this.favPlaylists = favPlaylists;
	}
	public Set<Cancion> getFavSongs() {
		return favSongs;
	}
	public void setFavSongs(Set<Cancion> favSongs) {
		this.favSongs = favSongs;
	}
	
}
