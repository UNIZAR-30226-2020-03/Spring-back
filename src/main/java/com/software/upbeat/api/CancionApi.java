package com.software.upbeat.api;


import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.software.upbeat.model.Cliente;
import com.software.upbeat.service.ArtistaService;
import com.software.upbeat.service.CancionService;
import com.software.upbeat.service.ClienteService;
/* al final no uso bytes
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.io.ByteArrayOutputStream;*/
import com.software.upbeat.service.PlaylistService;
import com.software.upbeat.service.UsuarioService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/cancion/")
public class CancionApi {
	
	@Autowired
	CancionService cancionService;
	
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	ArtistaService artistaService;
	
	//////////////////////////////////////////////
	// AÑADIR CANCION        				    //
	//////////////////////////////////////////////

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public CancionResponse saveCancion(@RequestBody CancionRequest cancionRequest) throws IOException {
		
		// Mapeo request dto
		Cancion cancion = mapper.map(cancionRequest, Cancion.class);
		cancion.setReproducciones((long) 0);
		if(cancion.getDuracion()==null) cancion.setDuracion((float) 0);
		Date fecha=new Date();
		if(cancion.getFecha()==null) cancion.setFecha(convertUtilToSql(fecha));
		// Invoca lógica de negocio
		Cancion newCancion = cancionService.save(cancion);
		
		// Mapeo entity
		CancionResponse cancionResponse = mapper.map(newCancion, CancionResponse.class);
		
		return cancionResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
	
	//////////////////////////////////////////////////////
	// STREAMING CANCION POR NOMBRE URL      	        //
	/////////////////////////////////////////////////////
	@GetMapping(value="/getStreamUrlMp3/{nombre}")
	public String  getUrlMp3ByName(@PathVariable(value = "nombre") String nombre) throws IOException{
		/*Cliente clienteReproduce= clienteService.getClienteByEmail(correo).getBody();
		clienteReproduce.addLastSongs(cancionService.getSongByName(nombre).getBody().getId());
		clienteReproduce = clienteService.save(clienteReproduce);
		
		
		// Mapeo entity
		ClienteResponse clienteResponse = mapper.map(clienteReproduce, ClienteResponse.class);*/
		return cancionService.getSongURLByName(nombre);
	}
	
	//////////////////////////////////////////////////////
	// STREAMING IMAGEN POR NOMBRE URL      	        //
	/////////////////////////////////////////////////////
	@GetMapping(value="/getStreamUrlImg/{nombre}")
	public String  getUrlImgByName(@PathVariable(value = "nombre") String nombre) throws IOException{
		return cancionService.getImgURLByName(nombre);
	}
	
	//////////////////////////////////////////////
	// OBTENER CANCION POR NOMBRE           	//
	//////////////////////////////////////////////
	@RequestMapping(value="/get/{nombre}", method=RequestMethod.GET)
	public CancionResponse getByName(@PathVariable(value = "nombre") String name) {
	ResponseEntity<Cancion> songByName = cancionService.getSongByName(name);
	CancionResponse cancionResponse = mapper.map(songByName.getBody(), CancionResponse.class);
	return cancionResponse;
	}

	//////////////////////////////////////////////////////
	// ACTUALIZAR REPRODUCCIONES CANCION POR ID 		//
	/////////////////////////////////////////////////////
	@RequestMapping(value="/updateReprod/{id}", method=RequestMethod.POST)
	public CancionResponse updateReprod(@PathVariable(value = "id") Long id) throws IOException{
		// Mapeo request dto
		ResponseEntity<Cancion> songById = cancionService.getSongByID(id);
		Cancion cancion=songById.getBody();	
		cancion.setReproducciones((cancion.getReproducciones()+1));
		cancion=cancionService.save(cancion);
		CancionResponse cancionResponse = mapper.map(cancion, CancionResponse.class);
		return cancionResponse;
	}
	
	//////////////////////////////////////////////////////
	// STREAMING IMAGEN POR ID URL      	           //
	/////////////////////////////////////////////////////
	@GetMapping(value="/getStreamUrlImgbyId/{id}")
	public String  getUrlImgById(@PathVariable(value = "id") Long id) throws IOException{
	return cancionService.getImgURLById(id);
	}
	
	//////////////////////////////////////////////
	// OBTENER CANCION POR ID               	//
	//////////////////////////////////////////////
	@RequestMapping(value="/getbyId/{id}", method=RequestMethod.GET)
	public CancionResponse getById(@PathVariable(value = "id") Long id) {
	ResponseEntity<Cancion> songById = cancionService.getSongByID(id);
	CancionResponse cancionResponse = mapper.map(songById.getBody(), CancionResponse.class);
	return cancionResponse;
	}
	
	//////////////////////////////////////////////
	// OBTENER TODAS LAS CANCIONES			//
	//////////////////////////////////////////////
	@RequestMapping(value="/allSongs", method=RequestMethod.GET)
	public List<Cancion> getAllSongs() {
		return cancionService.getAllSongs();
	}
	
	//////////////////////////////////////////////////////////////////////
	// OBTENER TODAS LAS CANCIONES ORDENADAS POR POPULARIDAD			//
	//////////////////////////////////////////////////////////////////////
	@RequestMapping(value="/allSongsOrderByPopularity", method=RequestMethod.GET)
	public List<Cancion> findSongsByPopularity() {
	return cancionService.findSongsByPopularity();
	}
	
	/*///////////////////////////////////////////////////////////////////////
	// OBTENER 10 ULTIMAS CANCIONES REPRODUCIDAS USUARIO            			//
	//////////////////////////////////////////////////////////////////////
	@RequestMapping(value="/getLast10/{correo}", method=RequestMethod.GET)
	public List<Cancion> find10LastSongsByClient(@PathVariable(value = "correo") String correo) {
		Cliente clienteReproduce= clienteService.getClienteByEmail(correo).getBody();
		List<Cancion> aux=new ArrayList<Cancion>();
		int i=0;
		while(i<clienteReproduce.getUltimaCancion()) {
			aux.add(cancionService.getSongByID(clienteReproduce.getIdCancion(i)).getBody());
		}
		return aux;
	}*/

	//////////////////////////////////////////////////////////
	// ACTUALIZAR CANCION POR EL NOMBRE 		           //
	/////////////////////////////////////////////////////////
	@RequestMapping(value="/update/{nombre}", method=RequestMethod.PUT)
	public CancionResponse update(@PathVariable(value = "nombre") String nombre,
			@Valid @RequestBody CancionRequest datosCancion) {
		
		// Mapeo request dto
		Cancion cancion = mapper.map(datosCancion, Cancion.class);
		
		// Invoca lógica de negocio
		ResponseEntity<Cancion> cancionByNombreArtista = cancionService.getSongByName(nombre);
		
		Cancion updateCancion = cancionByNombreArtista.getBody();
		updateCancion.setNombre(cancion.getNombre());
		updateCancion.setPathMp3(cancion.getPathMp3());
		updateCancion.setPathImg(cancion.getPathImg());
		updateCancion.setDuracion(cancion.getDuracion());
		updateCancion.setCreador(cancion.getCreador());
		updateCancion.setFecha(cancion.getFecha());
		//updateCancion.setSong(compressBytes(cancion.getSong()));
		
		updateCancion = cancionService.save(updateCancion);
		
		// Mapeo entity
		CancionResponse cancionResponse = mapper.map(updateCancion, CancionResponse.class);
		return cancionResponse;
	}
	
	//////////////////////////////////////////////////////////
	// ACTUALIZAR CANCION POR EL ID							//
	/////////////////////////////////////////////////////////
	@RequestMapping(value="/updateid/{id}", method=RequestMethod.PUT)
	public CancionResponse updateid(@PathVariable(value = "id") Long id,
	@Valid @RequestBody CancionRequest datosCancion) {
	
	// Mapeo request dto
	Cancion cancion = mapper.map(datosCancion, Cancion.class);
	
	// Invoca lógica de negocio
	ResponseEntity<Cancion> cancionByNombreArtista = cancionService.getSongByID(id);
	
	Cancion updateCancion = cancionByNombreArtista.getBody();
	updateCancion.setNombre(cancion.getNombre());
	updateCancion.setPathMp3(cancion.getPathMp3());
	updateCancion.setPathImg(cancion.getPathImg());
	updateCancion.setDuracion(cancion.getDuracion());
	updateCancion.setCreador(cancion.getCreador());
	updateCancion.setFecha(cancion.getFecha());
	//updateCancion.setSong(compressBytes(cancion.getSong()));
	
	updateCancion = cancionService.save(updateCancion);
	
	// Mapeo entity
	CancionResponse cancionResponse = mapper.map(updateCancion, CancionResponse.class);
	return cancionResponse;
	}
	
	//////////////////////////////////////////////////
	// ELIMINAR CANCION	POR EL NOMBRE 	           //
	/////////////////////////////////////////////////
	@RequestMapping(value="/delete/{nombre}", method=RequestMethod.DELETE)
	public Map<String, Boolean> delete(@PathVariable(value = "nombre") String nombreCancion) {
		
		// Invoca lógica de negocio
		ResponseEntity<Cancion> songByNameAndArtist = cancionService.getSongByName(nombreCancion);
		
		Cancion deleteCancion = songByNameAndArtist.getBody();
		
		cancionService.delete(deleteCancion);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("ELIMINADO", Boolean.TRUE);
		return response;
		
	}
	
	//////////////////////////////////////////////
	// ELIMINAR CANCION		ID			 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/deleteid/{id}", method=RequestMethod.DELETE)
	public Map<String, Boolean> deleteid(@PathVariable(value = "id") Long id) {
	
	// Invoca lógica de negocio
	ResponseEntity<Cancion> songByName = cancionService.getSongByID(id);
	
	Cancion deleteCancion = songByName.getBody();
	
	cancionService.delete(deleteCancion);
	
	Map<String, Boolean> response = new HashMap<>();
	response.put("ELIMINADO", Boolean.TRUE);
	
	return response;
	
	}
	/*	
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
	/*

	
	//////////////////////////////////////////////
	// STREAMING CANCION POR NOMBRE				//
	//////////////////////////////////////////////
	@GetMapping(value="/getStream/{nombre}")
	public byte[]  getStreamByName(@PathVariable(value = "nombre") String nombreCancion) throws IOException{
		byte[] song=cancionService.getSongStreamByName(nombreCancion);
		return decompressBytes(song);
	}
	*/
	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

}