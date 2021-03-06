package com.software.upbeat.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.software.upbeat.model.Cliente;
import com.software.upbeat.model.ListaReproduccion;
import com.software.upbeat.model.Usuario;
import com.software.upbeat.service.ListaReproduccionService;
import com.software.upbeat.service.UsuarioService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/usuario")
public class UsuarioApi{
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	ListaReproduccionService listaReproduccionService;
	
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
	// OBTENER USUARIO POR USERNAME				//
	//////////////////////////////////////////////
	@RequestMapping(value="/username/{username}", method=RequestMethod.GET)
	public UsuarioResponse getByUsername(@PathVariable(value = "username") String username) {
		
		// Invoca lógica de negocio
		ResponseEntity<Usuario> usuarioByUsername = usuarioService.getUsuarioByUsername(username);
		
		// Mapeo entity
		UsuarioResponse usuarioResponse = mapper.map(usuarioByUsername.getBody(), UsuarioResponse.class);
		
		return usuarioResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
	
	//////////////////////////////////////////////
	// OBTENER TODOS LOS USUARIOS				//
	//////////////////////////////////////////////
	
	@RequestMapping(value="/allUsuarios", method=RequestMethod.GET)
	public List<Usuario> getAllUsuarios() {
		return usuarioService.getAllUsuarios();
	}
	//////////////////////////////////////////////
	// OBTENER IMAGEN POR EMAIL				//
	//////////////////////////////////////////////
	@GetMapping(value="/getImgUrl/{correo}")
	public String  getUrlImgByEmail(@PathVariable(value = "correo") String correo) throws IOException{
		return usuarioService.getImgByEmail(correo);
	}
	//////////////////////////////////////////////
	// AÑADIR USUARIO							//
	//////////////////////////////////////////////
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public UsuarioResponse saveUsuario(@RequestBody UsuarioRequest usuarioRequest) {
		
		// Mapeo request dto
		Usuario usuario = mapper.map(usuarioRequest, Usuario.class);
		
		// Invoca lógica de negocio
		try {
			
			ListaReproduccion lr = new ListaReproduccion();
			listaReproduccionService.save(lr);
			usuario.setListaRep(lr);
			
			Usuario newUsuario = usuarioService.save(usuario);
			
			// Mapeo entity
			UsuarioResponse usuarioResponse = mapper.map(newUsuario, UsuarioResponse.class);
			
			return usuarioResponse;
		}
		catch(Exception e) {
			System.out.println("ERROR AL CREAR USUARIO");
			usuario.setCod_cliente((long) -1);
			// Mapeo entity
			UsuarioResponse usuarioResponse = mapper.map(usuario, UsuarioResponse.class);
			
			return usuarioResponse;
		}
		
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
	
	//////////////////////////////////////////////
	// ACTUALIZAR USUARIO POR EL CORREO 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/updateUser/{correo}", method=RequestMethod.PUT)
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
		updateUsuario.setPathImg(usuario.getPathImg());
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
		
}
