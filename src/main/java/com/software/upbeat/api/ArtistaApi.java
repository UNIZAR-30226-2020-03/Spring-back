package com.software.upbeat.api;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.software.upbeat.model.Artista;
import com.software.upbeat.service.ArtistaService;

@RestController
public class ArtistaApi {
	
	@Autowired
	ArtistaService artistaService;
	
	@Autowired
	Mapper mapper;
	
	@RequestMapping(value="/saveArtista", method=RequestMethod.POST)
	public ArtistaResponse updateOrSave(@RequestBody ArtistaRequest artistaRequest) {
		
		// Mapeo request dto
		Artista artista = mapper.map(artistaRequest, Artista.class);
		
		// Invoca lógica de negocio
		Artista updatedArtista = artistaService.save(artista);
		
		// Mapeo entity
		ArtistaResponse artistaResponse = mapper.map(updatedArtista, ArtistaResponse.class);
		
		return artistaResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}

}
