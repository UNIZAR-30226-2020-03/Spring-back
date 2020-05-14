package com.software.upbeat.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.openjpa.persistence.FetchAttribute;
import org.apache.openjpa.persistence.FetchGroup;
import org.apache.openjpa.persistence.FetchGroups;
import org.apache.openjpa.persistence.LoadFetchGroup;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	private String apellidos;
	private String contrasenya;
	private String correo;
	private String username;
	private String pais;
	
	/*
	 * AMIGOS -> CLIENTES = USUARIO | ARTISTA
	 * https://stackoverflow.com/questions/3393515/jpa-how-to-have-one-to-many-relation-of-the-same-entity-type
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
	
	@JsonBackReference
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
	
	public Cliente() {	
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((cod_cliente == null) ? 0 : cod_cliente.hashCode());
		result = prime * result + ((contrasenya == null) ? 0 : contrasenya.hashCode());
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
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
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public Cliente(Long cod_cliente, String nombre, String apellidos, String contrasenya, String correo,
			String username, String pais) {
		super();
		this.cod_cliente = cod_cliente;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.contrasenya = contrasenya;
		this.correo = correo;
		this.username = username;
		this.pais = pais;
	}

	@Column(name = "cod_cliente", nullable = false)
	public Long getCod_cliente() {
		return cod_cliente;
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
				+ "]";
	}

}
