package com.software.upbeat.api;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
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
	@GetMapping(value="/getStream/{nombre}")
	public byte[]  getStreamByName(@PathVariable(value = "nombre") String nombreCancion) throws IOException{
		byte[] song=cancionService.getSongStreamByName(nombreCancion);
		return decompressBytes(song);
	}
	
	//////////////////////////////////////////////
	// STREAMING CANCION POR NOMBRE URL      	//
	//////////////////////////////////////////////
	@GetMapping(value="/getStreamUrl/{nombre}")
	public String  getUrlByName(@PathVariable(value = "nombre") String nombreCancion) throws IOException{
		return cancionService.getSongURLByName(nombreCancion);
		
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
	// AÑADIR CANCION BYTES					    //
	//////////////////////////////////////////////

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public CancionResponse saveCancion(@RequestBody CancionRequest cancionRequest) throws IOException {
		
		// Mapeo request dto
		Cancion cancion = mapper.map(cancionRequest, Cancion.class);
		
		// Invoca lógica de negocio
		cancion.setSong(compressBytes(cancion.getSong()));
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
		updateCancion.setPath(cancion.getPath());
		updateCancion.setSong(compressBytes(cancion.getSong()));
		
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
//////////////////////////////////////////////
// ELIMINAR CANCION		ID			 		//
//////////////////////////////////////////////
@RequestMapping(value="/deleteid/{id}", method=RequestMethod.DELETE)
public Map<String, Boolean> delete(@PathVariable(value = "id") Long id) {

// Invoca lógica de negocio
ResponseEntity<Cancion> songByName = cancionService.getSongByID(id);

Cancion deleteCancion = songByName.getBody();

cancionService.delete(deleteCancion);

Map<String, Boolean> response = new HashMap<>();
response.put("ELIMINADO", Boolean.TRUE);


// Mapeo entity
// ClienteResponse clienteResponse = mapper.map(deleteCliente, ClienteResponse.class);

return response;

}
	
// compress the image bytes before inserting it to the database
public static byte[] compressBytes(byte[] data) {
	Deflater deflater = new Deflater();
	deflater.setInput(data);
	deflater.finish();

	ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
	byte[] buffer = new byte[1024];
	while (!deflater.finished()) {
		int count = deflater.deflate(buffer);
		outputStream.write(buffer, 0, count);
	}
	try {
		outputStream.close();
	} catch (IOException e) {
	}
	System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

	return outputStream.toByteArray();
}

// uncompress the image bytes before returning it to the angular application
public static byte[] decompressBytes(byte[] data) {
	Inflater inflater = new Inflater();
	inflater.setInput(data);
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
	byte[] buffer = new byte[1024];
	try {
		while (!inflater.finished()) {
			int count = inflater.inflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		outputStream.close();
	} catch (IOException ioe) {
	} catch (DataFormatException e) {
	}
	return outputStream.toByteArray();
}
}