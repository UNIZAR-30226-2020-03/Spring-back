package com.software.upbeat.api;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.software.upbeat.model.Artista;
import com.software.upbeat.model.Usuario;
import com.software.upbeat.service.UsuarioService;

@RestController
public class UsuarioApi {
	
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
