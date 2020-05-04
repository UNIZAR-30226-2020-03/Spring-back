package com.software.upbeat.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.software.upbeat.model.Cliente;
import com.software.upbeat.model.Usuario;
import com.software.upbeat.service.UsuarioService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/usuario/")
public class UsuarioApi extends ClienteApi{
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	Mapper mapper;
	
	//////////////////////////////////////////////
	// OBTENER USUARIO POR EMAIL				//
	//////////////////////////////////////////////
	@RequestMapping(value="/get/{correo}", method=RequestMethod.GET)
	public UsuarioResponse getByEmail(@PathVariable(value = "correo") String correoUsuario) {
		
		// Invoca lógica de negocio
		ResponseEntity<Usuario> usuarioByEmail = usuarioService.getUsuarioByEmail(correoUsuario);
		
		// Mapeo entity
		UsuarioResponse usuarioResponse = mapper.map(usuarioByEmail.getBody(), UsuarioResponse.class);
		
		return usuarioResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
	
	//////////////////////////////////////////////
	// OBTENER USUARIO POR PASSWORD Y EMAIL 	//
	//////////////////////////////////////////////
	@RequestMapping(value="/get/{contrasenya}/{correo}", method=RequestMethod.GET)
	public UsuarioResponse getByEmailAndPassword(@PathVariable(value = "contrasenya") String password, @PathVariable(value = "correo") String correoUsuario) {
		// Invoca lógica de negocio
		ResponseEntity<Usuario> usuarioByEmailAndPassword = usuarioService.getUsuarioByEmailAndPassword(password, correoUsuario);
		
		// Mapeo entity
		UsuarioResponse usuarioResponse = mapper.map(usuarioByEmailAndPassword.getBody(), UsuarioResponse.class);
		
		return usuarioResponse;
	}
	
	//////////////////////////////////////////////
	// OBTENER TODOS LOS USUARIOS				//
	//////////////////////////////////////////////
	
	@RequestMapping(value="/allUsuarios", method=RequestMethod.GET)
	public List<Usuario> getAllUsuarios() {
		return usuarioService.getAllUsuarios();
	}
	
	//////////////////////////////////////////////
	// AÑADIR USUARIO							//
	//////////////////////////////////////////////
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public UsuarioResponse saveUsuario(@RequestBody UsuarioRequest usuarioRequest) {
		
		// Mapeo request dto
		Usuario usuario = mapper.map(usuarioRequest, Usuario.class);
		
		// Invoca lógica de negocio
		Usuario newUsuario = usuarioService.save(usuario);
		
		// Mapeo entity
		UsuarioResponse usuarioResponse = mapper.map(newUsuario, UsuarioResponse.class);
		
		return usuarioResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
	
	//////////////////////////////////////////////
	// ACTUALIZAR USUARIO POR EL CORREO 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/update/{correo}", method=RequestMethod.PUT)
	public UsuarioResponse update(@PathVariable(value = "correo") String correoUsuario,
			@Valid @RequestBody UsuarioRequest datosUsuario) {
		
		// Mapeo request dto
		Usuario usuario = mapper.map(datosUsuario, Usuario.class);
		
		// Invoca lógica de negocio
		ResponseEntity<Usuario> usuarioByEmail = usuarioService.getUsuarioByEmail(correoUsuario);
		
		Usuario updateUsuario = usuarioByEmail.getBody();
		
		updateUsuario.setNombre(usuario.getNombre());
		updateUsuario.setApellidos(usuario.getApellidos());
		updateUsuario.setContrasenya(usuario.getContrasenya());
		updateUsuario.setCorreo(usuario.getCorreo());
		updateUsuario.setUsername(usuario.getUsername());
		updateUsuario.setPais(usuario.getPais());
		updateUsuario.setAmigos(usuario.getAmigos());
		
		updateUsuario = usuarioService.save(updateUsuario);
		
		
		// Mapeo entity
		UsuarioResponse usuarioResponse = mapper.map(updateUsuario, UsuarioResponse.class);
		
		return usuarioResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
	
	//////////////////////////////////////////////
	// ELIMINAR USUARIO					 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/delete/{correo}", method=RequestMethod.DELETE)
	public Map<String, Boolean> delete(@PathVariable(value = "correo") String correoUsuario) {
		
		// Invoca lógica de negocio
		ResponseEntity<Usuario> usuarioByEmail = usuarioService.getUsuarioByEmail(correoUsuario);
		
		Usuario deleteUsuario = usuarioByEmail.getBody();
		
		usuarioService.delete(deleteUsuario);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("ELIMINADO", Boolean.TRUE);
		
		
		// Mapeo entity
		// UsuarioResponse usuarioResponse = mapper.map(deleteUsuario, UsuarioResponse.class);
		
		return response;
		
	}
	
	//////////////////////////////////////////////
	// SEGUIR A UN CLIENTE				 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/follow/{miCorreo}/{suCorreo}", method=RequestMethod.PUT)
	public UsuarioResponse follow(@PathVariable(value = "miCorreo") String correoUsuario,
	@PathVariable(value = "suCorreo") String correoAmigo) {
		
		// Invoca lógica de negocio
		ResponseEntity<Usuario> usuarioByEmail = usuarioService.getUsuarioByEmail(correoUsuario);
		ResponseEntity<Cliente> amigoByEmail = clienteService.getClienteByEmail(correoAmigo);
		
		Usuario usuario = usuarioByEmail.getBody();
		Cliente amigo = amigoByEmail.getBody();
		
		usuario.addAmigo(amigo);
		usuario = usuarioService.save(usuario);
		
		// Mapeo entity
		UsuarioResponse usuarioResponse = mapper.map(usuario, UsuarioResponse.class);
		
		return usuarioResponse;
	
	}
	
	//////////////////////////////////////////////
	// VER SI UN CLIENTE ES AMIGO		 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/following/{miCorreo}/{suCorreo}", method=RequestMethod.GET)
	public boolean following(@PathVariable(value = "miCorreo") String correoUsuario,
	@PathVariable(value = "suCorreo") String correoAmigo) {
		
		// Invoca lógica de negocio
		ResponseEntity<Usuario> usuarioByEmail = usuarioService.getUsuarioByEmail(correoUsuario);
		ResponseEntity<Cliente> amigoByEmail = clienteService.getClienteByEmail(correoAmigo);
		
		Usuario usuario = usuarioByEmail.getBody();
		Cliente amigo = amigoByEmail.getBody();
		
		return usuario.containsAmigo(amigo);
		
	}
	
	//////////////////////////////////////////////
	// ACTUALIZAR USUARIO POR EL CORREO 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/unfollow/{miCorreo}/{suCorreo}", method=RequestMethod.PUT)
	public UsuarioResponse unfollow(@PathVariable(value = "miCorreo") String correoUsuario,
	@PathVariable(value = "suCorreo") String correoAmigo) {
		
		// Invoca lógica de negocio
		ResponseEntity<Usuario> usuarioByEmail = usuarioService.getUsuarioByEmail(correoUsuario);
		ResponseEntity<Cliente> amigoByEmail = clienteService.getClienteByEmail(correoAmigo);
		
		Usuario usuario = usuarioByEmail.getBody();
		Cliente amigo = amigoByEmail.getBody();
		
		usuario.removeAmigo(amigo);
		usuario = usuarioService.save(usuario);
		
		// Mapeo entity
		UsuarioResponse usuarioResponse = mapper.map(usuario, UsuarioResponse.class);
		
		return usuarioResponse;
		
	}
		
}
