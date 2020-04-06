package com.proyecto.upbeat.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.proyecto.upbeat.dao.UsuarioRepository;
import com.proyecto.upbeat.dto.Usuario;

public class UsuarioService {
	
	@Autowired
	UsuarioRepository dao;
	
	public Usuario save (Usuario usuario) {
		return dao.saveAndFlush(usuario);
	}

}
