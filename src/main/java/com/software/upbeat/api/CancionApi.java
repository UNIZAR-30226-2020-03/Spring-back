package com.software.upbeat.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.software.upbeat.model.Cancion;
import com.software.upbeat.service.CancionService;


@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/cancion/")
public class CancionApi {
	
	@Autowired
	CancionService cancionService;
	
	@Autowired
	Mapper mapper;
	
	
	//////////////////////////////////////////////
	// STREAMING CANCION POR NOMBRE				//
	//////////////////////////////////////////////
	@RequestMapping(value="/getStream/{nombre}", method=RequestMethod.GET)
	public void getStreamByName(HttpServletResponse response,@PathVariable(value = "nombre") String nombreCancion) throws IOException{
		byte[] song=cancionService.getSongStreamByName(nombreCancion);
		InputStream myStream =new ByteArrayInputStream(song);
		// Set the content type and attachment header.
		response.addHeader("Content-disposition", "attachment;filename="+nombreCancion);
		response.setContentType("mp3");

		// Copy the stream to the response's output stream.
		IOUtils.copy(myStream, response.getOutputStream());
		response.flushBuffer();
	}
	
	//////////////////////////////////////////////
	// STREAMING CANCION POR NOMBRE Y AUTOR  	//
	//////////////////////////////////////////////
	@RequestMapping(value="/getStream/{nombre}/{autor}", method=RequestMethod.GET)
	public void getStreamByNameAndArtist(HttpServletResponse response,@PathVariable(value = "nombre") String nombreCancion, @PathVariable(value = "autor") String autorCancion) throws IOException {

		InputStream myStream =new ByteArrayInputStream(cancionService.getSongStreamByNameAndArtist(nombreCancion,autorCancion));
		// Set the content type and attachment header.
		response.addHeader("Content-disposition", "attachment;filename="+nombreCancion);
		response.setContentType("mp3");

		// Copy the stream to the response's output stream.
		IOUtils.copy(myStream, response.getOutputStream());
		response.flushBuffer();
	}
	//////////////////////////////////////////////
	// OBTENER CANCION POR NOMBRE           	//
	//////////////////////////////////////////////
	@RequestMapping(value="/get/{nombre}", method=RequestMethod.GET)
	public CancionResponse getByNameAndArtist(@PathVariable(value = "nombre") String name) {
	ResponseEntity<Cancion> songByName = cancionService.getSongByName(name);
	
	// Mapeo entity
	CancionResponse cancionResponse = mapper.map(songByName.getBody(), CancionResponse.class);
	
	return cancionResponse;
	//Jejjejejeherokuuu
	}

	//////////////////////////////////////////////
	// OBTENER CANCION POR NOMBRE Y AUTOR    	//
	//////////////////////////////////////////////
	@RequestMapping(value="/get/{nombre}/{autor}", method=RequestMethod.GET)
	public CancionResponse getByEmailAndPassword(@PathVariable(value = "nombre") String name, @PathVariable(value = "autor") String autor) {
		ResponseEntity<Cancion> songByNameAndArtist = cancionService.getSongByNameAndArtist(name, autor);
		
		// Mapeo entity
		CancionResponse cancionResponse = mapper.map(songByNameAndArtist.getBody(), CancionResponse.class);
		
		return cancionResponse;
		//Jejjejejeherokuuu
		

	}
	//////////////////////////////////////////////
	// OBTENER TODAS LAS CANCIONES			//
	//////////////////////////////////////////////
	
	@RequestMapping(value="/allSongs", method=RequestMethod.GET)
	public List<Cancion> getAllSongs() {
		return cancionService.getAllSongs();
	}
	
	//////////////////////////////////////////////
	// AÑADIR CANCION						//
	//////////////////////////////////////////////

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public CancionResponse saveCancion(@RequestBody CancionRequest cancionRequest) throws IOException {
		
		// Mapeo request dto
		Cancion cancion = mapper.map(cancionRequest, Cancion.class);
		
		// Invoca lógica de negocio
		Cancion newCancion = cancionService.save(cancion);
		
		// Mapeo entity
		CancionResponse cancionResponse = mapper.map(newCancion, CancionResponse.class);
		
		return cancionResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
	
	//////////////////////////////////////////////
	// ACTUALIZAR CANCION POR EL NOMBRE 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/update/{nombre}", method=RequestMethod.PUT)
	public CancionResponse update(@PathVariable(value = "nombre") String nombre,
			@Valid @RequestBody CancionRequest datosCancion) {
		
		// Mapeo request dto
		Cancion cancion = mapper.map(datosCancion, Cancion.class);
		
		// Invoca lógica de negocio
		ResponseEntity<Cancion> cancionByEmail = cancionService.getSongByName(nombre);
		
		Cancion updateCancion = cancionByEmail.getBody();
		
		updateCancion.setNombre(cancion.getNombre());
		updateCancion.setAutor(cancion.getAutor());
		updateCancion.setSong(cancion.getSong());
		
		updateCancion = cancionService.save(updateCancion);
		
		
		// Mapeo entity
		CancionResponse cancionResponse = mapper.map(updateCancion, CancionResponse.class);
		
		return cancionResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
	
	//////////////////////////////////////////////
	// ELIMINAR CANCION					 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/delete/{nombre}", method=RequestMethod.DELETE)
	public Map<String, Boolean> delete(@PathVariable(value = "nombre") String nombreCancion) {
		
		// Invoca lógica de negocio
		ResponseEntity<Cancion> songByName = cancionService.getSongByName(nombreCancion);
		
		Cancion deleteCancion = songByName.getBody();
		
		cancionService.delete(deleteCancion);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("ELIMINADO", Boolean.TRUE);
		
		
		// Mapeo entity
		// ClienteResponse clienteResponse = mapper.map(deleteCliente, ClienteResponse.class);
		
		return response;
		
	}
}