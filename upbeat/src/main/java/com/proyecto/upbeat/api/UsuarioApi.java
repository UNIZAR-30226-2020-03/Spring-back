package com.proyecto.upbeat.api;

import org.dozer.Mapper;
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
	
	@Autowired
	Mapper mapper;
	
	@RequestMapping(value="/saveUsuario", method=RequestMethod.POST)
	public UsuarioResponse updateOrSave(@RequestBody UsuarioRequest usuarioRequest) {
		
		// Mapeo request dto
		Usuario usuario = mapper.map(usuarioRequest, Usuario.class);
		
		// Invoca lógica de negocio
		Usuario updatedUsuario = usuarioService.save(usuario);
		
		// Mapeo entity
		UsuarioResponse usuarioResponse = mapper.map(updatedUsuario, UsuarioResponse.class);
		
		return usuarioResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}

}
