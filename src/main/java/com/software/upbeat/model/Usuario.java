package com.software.upbeat.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

@Entity
public class Usuario extends Cliente{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.AUTO) // GenerationType.IDENTITY??????
	private Long cod_usuario;
	
	/*
	 * AMIGOS -> CLIENTES = USUARIO | ARTISTA
	 * https://stackoverflow.com/questions/3393515/jpa-how-to-have-one-to-many-relation-of-the-same-entity-type
	 */
	Set<Cliente> amigos;

	@OneToMany(mappedBy = "cod_cliente", cascade = CascadeType.ALL)
	public Set<Cliente> getAmigos() {
		return amigos;
	}

	public void setAmigos(Set<Cliente> amigos) {
		this.amigos = amigos;
	}
	
	public void addAmigo(Cliente amigo) {
		amigos.add(amigo);
	}

	public Usuario() {	
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cod_usuario == null) ? 0 : cod_usuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (cod_usuario == null) {
			if (other.cod_usuario != null)
				return false;
		} else if (!cod_usuario.equals(other.cod_usuario))
			return false;
		return true;
	}

	public Usuario(Long cod_cliente, String nombre, String apellidos, String contrasenya, String correo,
			String username, String pais, Long codigo_usuario) {
		super(cod_cliente, nombre, apellidos, contrasenya, correo, username, pais);
		// TODO Auto-generated constructor stub
		this.cod_usuario = codigo_usuario;
		this.amigos = new HashSet<Cliente>();
	}

	@Column(name = "cod_usuario")
	public Long getCod_usuario() {
		return cod_usuario;
	}

	public void setCod_usuario(Long cod_usuario) {
		this.cod_usuario = cod_usuario;
	}
	
}
