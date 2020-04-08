package com.proyecto.upbeat.api;

public class UsuarioResponse extends ClienteResponse{
	
	public Long getCod_usuario() {
		return cod_usuario;
	}

	public void setCod_usuario(Long cod_usuario) {
		this.cod_usuario = cod_usuario;
	}

	private Long cod_usuario;

}
