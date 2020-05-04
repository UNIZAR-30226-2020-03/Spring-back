package com.software.upbeat.api;

import java.util.HashSet;
import java.util.Set;

import com.software.upbeat.model.Usuario;

public class UsuarioResponse extends ClienteResponse{

	private Long cod_usuario;

	private Set<Usuario> amigos = new HashSet<Usuario>();
	

	public void setAmigos(Set<Usuario> amigos) {
		this.amigos = amigos;
	}

	public Long getCod_usuario() {
		return cod_usuario;
	}

	public void setCod_usuario(Long cod_usuario) {
		this.cod_usuario = cod_usuario;
	}

	public Set<Usuario> getAmigos() {
		return amigos;
	}
	
}
