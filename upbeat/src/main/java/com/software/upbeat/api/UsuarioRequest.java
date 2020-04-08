package com.software.upbeat.api;

import javax.validation.constraints.*;

public class UsuarioRequest extends ClienteRequest{

	@NotNull(message="El codigo de usuario es requerido")
	private Long cod_usuario;

	public Long getCod_usuario() {
		return cod_usuario;
	}

	public void setCod_usuario(Long cod_usuario) {
		this.cod_usuario = cod_usuario;
	}
	
}
