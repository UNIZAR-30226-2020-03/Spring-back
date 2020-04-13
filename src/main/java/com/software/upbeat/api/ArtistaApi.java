package com.software.upbeat.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.software.upbeat.model.Artista;
import com.software.upbeat.service.ArtistaService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/artista/")
public class ArtistaApi {
	

	@Autowired
	ArtistaService artistaService;
	
	@Autowired
	Mapper mapper;
	
	//////////////////////////////////////////////
	// OBTENER USUARIO POR EMAIL				//
	//////////////////////////////////////////////
	@RequestMapping(value="/get/{correo}", method=RequestMethod.GET)
	public ArtistaResponse getByEmail(@PathVariable(value = "correo") String correoArtista) {
		
		// Invoca lógica de negocio
		ResponseEntity<Artista> artistaByEmail = artistaService.getArtistaByEmail(correoArtista);
		
		// Mapeo entity
		ArtistaResponse artistaResponse = mapper.map(artistaByEmail.getBody(), ArtistaResponse.class);
		
		return artistaResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
	
	//////////////////////////////////////////////
	// OBTENER USUARIO POR PASSWORD Y EMAIL 	//
	//////////////////////////////////////////////
	@RequestMapping(value="/get/{contrasenya}/{correo}", method=RequestMethod.GET)
	public ArtistaResponse getByEmailAndPassword(@PathVariable(value = "contrasenya") String password, @PathVariable(value = "correo") String correoArtista) {
		// Invoca lógica de negocio
		ResponseEntity<Artista> artistaByEmailAndPassword = artistaService.getArtistaByEmailAndPassword(password, correoArtista);
		
		// Mapeo entity
		ArtistaResponse artistaResponse = mapper.map(artistaByEmailAndPassword.getBody(), ArtistaResponse.class);
		
		return artistaResponse;
	}
	
	//////////////////////////////////////////////
	// OBTENER TODOS LOS USUARIOS				//
	//////////////////////////////////////////////
	
	@RequestMapping(value="/allArtistas", method=RequestMethod.GET)
	public List<Artista> getAllArtistas() {
		return artistaService.getAllArtistas();
	}
	
	//////////////////////////////////////////////
	// AÑADIR USUARIO							//
	//////////////////////////////////////////////
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ArtistaResponse saveArtista(@RequestBody ArtistaRequest artistaRequest) {
		
		// Mapeo request dto
		Artista artista = mapper.map(artistaRequest, Artista.class);
		
		// Invoca lógica de negocio
		Artista newArtista = artistaService.save(artista);
		
		// Mapeo entity
		ArtistaResponse artistaResponse = mapper.map(newArtista, ArtistaResponse.class);
		
		return artistaResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
	
	//////////////////////////////////////////////
	// ACTUALIZAR USUARIO POR EL CORREO 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/update/{correo}", method=RequestMethod.PUT)
	public ArtistaResponse update(@PathVariable(value = "correo") String correoArtista,
			@Valid @RequestBody ArtistaRequest datosArtista) {
		
		// Mapeo request dto
		Artista artista = mapper.map(datosArtista, Artista.class);
		
		// Invoca lógica de negocio
		ResponseEntity<Artista> artistaByEmail = artistaService.getArtistaByEmail(correoArtista);
		
		Artista updateArtista = artistaByEmail.getBody();
		
		updateArtista.setNombre(artista.getNombre());
		updateArtista.setApellidos(artista.getApellidos());
		updateArtista.setContrasenya(artista.getContrasenya());
		updateArtista.setCorreo(artista.getCorreo());
		updateArtista.setUsername(artista.getUsername());
		updateArtista.setPais(artista.getPais());
		
		updateArtista = artistaService.save(updateArtista);
		
		
		// Mapeo entity
		ArtistaResponse artistaResponse = mapper.map(updateArtista, ArtistaResponse.class);
		
		return artistaResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
	
	//////////////////////////////////////////////
	// ELIMINAR USUARIO					 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/delete/{correo}", method=RequestMethod.DELETE)
	public Map<String, Boolean> delete(@PathVariable(value = "correo") String correoArtista) {
		
		// Invoca lógica de negocio
		ResponseEntity<Artista> artistaByEmail = artistaService.getArtistaByEmail(correoArtista);
		
		Artista deleteArtista = artistaByEmail.getBody();
		
		artistaService.delete(deleteArtista);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("ELIMINADO", Boolean.TRUE);
		
		
		// Mapeo entity
		// ArtistaResponse artistaResponse = mapper.map(deleteArtista, ArtistaResponse.class);
		
		return response;
		
	}

}
