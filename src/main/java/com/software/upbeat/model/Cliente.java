package com.software.upbeat.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

// DOC FetchGroups -> http://openjpa.apache.org/builds/2.4.3/apache-openjpa/docs/ref_guide_fetch.html

@Entity
@Table(name="cliente", uniqueConstraints= {@UniqueConstraint(columnNames= "correo"), @UniqueConstraint(columnNames= "username")})
public class Cliente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod_cliente;
	private String nombre;
	private String pathImg;
	private String apellidos;
	private String contrasenya;
	private String correo;
	private String username;
	private String pais;
	/*
	private ArrayList<Long> lista10Ultimas;
	private int ultimaCancion;
	
	
	
	
	/*
	 * AMIGOS -> CLIENTES = USUARIO | ARTISTA
	 * https://stackoverflow.com/questions/3393515/jpa-how-to-have-one-to-many-relation-of-the-same-entity-type
	 */
	/*
	public ArrayList<Long> getLista10Ultimas() {
		return lista10Ultimas;
	}
	public Long getIdCancion(int numero) {
		return lista10Ultimas.get(numero);
	}
	public void setLista10Ultimas(ArrayList<Long> lista10Ultimas) {
		this.lista10Ultimas = lista10Ultimas;
	}
	
	public int getUltimaCancion() {
		return ultimaCancion;
	}
	public void setUltimaCancion(int ultimaCancion) {
		this.ultimaCancion = ultimaCancion;
	}
	public void addLastSongs(Long id) {
		lista10Ultimas.set(ultimaCancion,id);
		ultimaCancion=(ultimaCancion+1)%10;
	}
	*/
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Cliente> amigos; //= new HashSet<Cliente>();
	// private List<Cliente> amigos = new ArrayList<Cliente>();
	
	/*
	 * IMPORTANTE!!!!!
	 * EVITAR RECURSIVIDAD INFINITA
	 * 
	 * https://www.youtube.com/watch?v=6cW4z3DwG4E
	 */
	
	@JsonBackReference(value = "amigos")
	public Set<Cliente> getAmigos() {
		return amigos;
	}

	public void setAmigos(Set<Cliente> amigos) {
		this.amigos = amigos;
	}
	

	public void addAmigo(Cliente amigo) {
		amigos.add(amigo);
	}
	
	public void removeAmigo(Cliente amigo) {
		amigos.remove(amigo);
	}
	
	public boolean containsAmigo(Cliente amigo) {
		return amigos.contains(amigo);
	}
	
	// PLAYLISTS
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="creador")
	private Set<Playlist> playlists; //= new HashSet<Playlist>();
	
	@JsonManagedReference(value = "cliente_playlists")
	public Set<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(Set<Playlist> playlists) {
		this.playlists = playlists;
	}
	

	public void addPlaylist(Playlist playlist) {
		playlists.add(playlist);
		playlist.setCreador(this);
		System.out.println("------------------");
		System.out.println(playlists);
	}
	
	public void removePlaylist(Playlist playlist) {
		playlists.remove(playlist);
		playlist.setCreador(null);
	}
	
	public boolean containsPlaylist(Playlist playlist) {
		return playlists.contains(playlist);
	}
	// FIN PLAYLISTS
	
	//FAVORITOS PLAYLIST
	
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Playlist> favPlaylists;
	
	@JsonBackReference(value = "cliente_fav_playlists")
	public Set<Playlist> getFavPlaylists() {
		return favPlaylists;
	}

	public void setFavPlaylists(Set<Playlist> playlists) {
		this.favPlaylists = playlists;
	}
	

	public void addFavPlaylist(Playlist playlist) {
		favPlaylists.add(playlist);
	}
	
	public void removeFavPlaylist(Playlist playlist) {
		favPlaylists.remove(playlist);
	}
	
	public boolean containsFavPlaylist(Playlist playlist) {
		return favPlaylists.contains(playlist);
	}
	
	//FIN FAVORITOS PLAYLIST
	
	//FAVORITOS CANCIONES
	
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Cancion> favSongs;
	
	@JsonBackReference(value = "cliente_fav_songs")
	public Set<Cancion> getFavSongs() {
		return favSongs;
	}

	public void setFavSongs(Set<Cancion> song) {
		this.favSongs = song;
	}
	

	public void addFavSongs(Cancion song) {
		favSongs.add(song);
	}
	
	public void removeFavSongs(Cancion song) {
		favSongs.remove(song);
	}
	
	public boolean containsFavSongs(Cancion song) {
		return favSongs.contains(song);
	}
	
	//FIN FAVORITOS CANCIONES
	
	public Cliente() {	
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amigos == null) ? 0 : amigos.hashCode());
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((cod_cliente == null) ? 0 : cod_cliente.hashCode());
		result = prime * result + ((contrasenya == null) ? 0 : contrasenya.hashCode());
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		result = prime * result + ((playlists == null) ? 0 : playlists.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Cliente other = (Cliente) obj;
		if (amigos == null) {
			if (other.amigos != null)
				return false;
		} else if (!amigos.equals(other.amigos))
			return false;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (cod_cliente == null) {
			if (other.cod_cliente != null)
				return false;
		} else if (!cod_cliente.equals(other.cod_cliente))
			return false;
		if (contrasenya == null) {
			if (other.contrasenya != null)
				return false;
		} else if (!contrasenya.equals(other.contrasenya))
			return false;
		if (correo == null) {
			if (other.correo != null)
				return false;
		} else if (!correo.equals(other.correo))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (playlists == null) {
			if (other.playlists != null)
				return false;
		} else if (!playlists.equals(other.playlists))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public Cliente(Long cod_cliente, String nombre, String apellidos,String pathImg, String contrasenya, String correo,
			String username, String pais) {
		super();
		this.cod_cliente = cod_cliente;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.contrasenya = contrasenya;
		this.correo = correo;
		this.pathImg = pathImg;
		this.username = username;
		this.pais = pais;
	}

	@Column(name = "cod_cliente", nullable = false)
	public Long getCod_cliente() {
		return cod_cliente;
	}

	public String getPathImg() {
		return pathImg;
	}

	public void setPathImg(String pathImg) {
		this.pathImg = pathImg;
	}

	public void setCod_cliente(Long cod_cliente) {
		this.cod_cliente = cod_cliente;
	}
	
	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "apellidos", nullable = false)
	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@Column(name = "password", nullable = false)
	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	// unique = true
	@Column(name = "correo", nullable = false, unique = true)
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	// unique = true
	@Column(name = "username", nullable = false, unique = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "pais", nullable = false)
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "Cliente [cod_cliente=" + cod_cliente + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", contrasenya=" + contrasenya + ", correo=" + correo + ", username=" + username + ", pais=" + pais
				+ ", amigos=" + amigos + ", playlists=" + playlists + "]";
	}
	
}
