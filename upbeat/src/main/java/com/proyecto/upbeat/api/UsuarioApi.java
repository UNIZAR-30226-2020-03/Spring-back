package com.proyecto.upbeat.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.upbeat.dto.Usuario;
import com.proyecto.upbeat.service.UsuarioService;

@RestController
public class UsuarioApi {
	
	@RequestMapping(value="/usuario", method=RequestMethod.GET)
	public Usuario getById() {
		return new Usuario(1L, "Pepe", "Ruiz", "pepecontra", "pepito@gmail.com", "pepitomusic", "España", 1L);
	}
	
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(value="/saveUsuario", method=RequestMethod.POST)
	public Usuario updateOrSave(@RequestBody Usuario usuario) {
		return usuarioService.save(usuario);
	}

}
