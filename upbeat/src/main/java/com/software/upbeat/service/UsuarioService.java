package com.software.upbeat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.software.upbeat.dao.UsuarioRepository;
import com.software.upbeat.model.Usuario;

@Component
public class UsuarioService {

	@Autowired
	UsuarioRepository dao;
	
	public Usuario save(Usuario usuario){
		return dao.saveAndFlush(usuario);
	}
	
}
