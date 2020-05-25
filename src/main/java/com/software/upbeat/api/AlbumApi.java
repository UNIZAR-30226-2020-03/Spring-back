package com.software.upbeat.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

import com.software.upbeat.model.Album;
import com.software.upbeat.model.Artista;
import com.software.upbeat.model.Cancion;
import com.software.upbeat.service.AlbumService;
import com.software.upbeat.service.ArtistaService;
import com.software.upbeat.service.CancionService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/album/")
public class AlbumApi {

	private static final int CORRECT = 0;
	private static final int ERROR = 1;
	private static final int WRONG_RESULT = 2;
	
	@Autowired
	AlbumService albumService;
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	CancionService cancionService;
	
	@Autowired
	ArtistaService artistaService;
	
	/*
	 * ¿¿¿ getAlbumByNameAndAuthor ???
	 */
	
	//////////////////////////////////////////////
	// OBTENER ALBUM POR ID				//
	//////////////////////////////////////////////
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET)
    public AlbumResponse getAlbumById(@PathVariable(value = "id") Long albumId) {
		// Invoca lógica de negocio
		Optional<Album> albumById = albumService.getAlbumById(albumId);
		
		// Mapeo entity
		AlbumResponse albumResponse = mapper.map(albumById.get(), AlbumResponse.class);
		
		return albumResponse;
    }
	
	//////////////////////////////////////////////
	// OBTENER ALBUM/S POR NOMBRE				//
	//////////////////////////////////////////////
	@RequestMapping(value="/getByName/{name}", method=RequestMethod.GET)
	public List<Album> getAlbumByName(@PathVariable(value = "name") String nombre) {
		return albumService.getAlbumByName(nombre);
	}
	
	//////////////////////////////////////////////
	// OBTENER TODOS LOS ALBUMS				//
	//////////////////////////////////////////////
	@RequestMapping(value="/allAlbums", method=RequestMethod.GET)
	public List<Album> getAllAlbums() {
		return albumService.getAllAlbums();
	}
	
	//////////////////////////////////////////////
	// AÑADIR ALBUM							//
	//////////////////////////////////////////////
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public AlbumResponse saveAlbum(@RequestBody AlbumRequest albumRequest) {
		
		// Mapeo request dto
		Album album = mapper.map(albumRequest, Album.class);
		if(album.getDuracion()==null) album.setDuracion((float) 0);
		Date fecha=new Date();
		if(album.getFecha()==null) album.setFecha(convertUtilToSql(fecha));
		// Invoca lógica de negocio
		Album newAlbum = albumService.save(album);
		
		// Mapeo entity
		AlbumResponse albumResponse = mapper.map(newAlbum, AlbumResponse.class);
		
		return albumResponse;
		
	}
	
	//////////////////////////////////////////////
	// ACTUALIZAR ALBUM POR EL CORREO 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT)
	public AlbumResponse update(@PathVariable(value = "id") Long idAlbum,
	@Valid @RequestBody AlbumRequest datosAlbum) {
		
		// Mapeo request dto
		Album album = mapper.map(datosAlbum, Album.class);
		
		// Invoca lógica de negocio
		Optional<Album> albumById = albumService.getAlbumById(idAlbum);
		
		Album updateAlbum = albumById.get();
		
		updateAlbum.setNombre(album.getNombre());
		updateAlbum.setDescripcion(album.getDescripcion());
		updateAlbum.setFecha(album.getFecha());
		
		updateAlbum = albumService.save(updateAlbum);
		
		
		// Mapeo entity
		AlbumResponse albumResponse = mapper.map(updateAlbum, AlbumResponse.class);
		
		return albumResponse;
		
	}
	
	//////////////////////////////////////////////
	// ELIMINAR ALBUM				 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public Map<String, Boolean> delete(@PathVariable(value = "id") Long idAlbum) {
		
		Map<String, Boolean> response = new HashMap<>();
		
		try {
			// Invoca lógica de negocio
			Optional<Album> albumById = albumService.getAlbumById(idAlbum);
			
			Album deleteAlbum = albumById.get();
			Artista artista = deleteAlbum.getAutor();
			artista.removeAlbum(deleteAlbum);
			artistaService.save(artista);
			
			albumService.delete(deleteAlbum);
			
			
			response.put("ELIMINADO", Boolean.TRUE);
		}
		catch(Exception e) {
			System.out.println(e);
			response.put("EXCEPCIÓN", Boolean.FALSE);
		}
		
		// Mapeo entity
		// AlbumResponse albumResponse = mapper.map(deleteAlbum, AlbumResponse.class);
		
		return response;
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	// CANCIONES																		//
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////
	// AÑADIR CANCIÓN A UN ÁLBUM			 	//
	//////////////////////////////////////////////
	@RequestMapping(value="/addSong/{idAlbum}/{idSong}", method=RequestMethod.PUT)
	public int addSong(@PathVariable(value = "idAlbum") Long idAlbum,
			@PathVariable(value = "idSong") Long idSong) {
		
		int resul;
		
		try {
		// Invoca lógica de negocio
			Optional<Album> queryAlbum = albumService.getAlbumById(idAlbum);
			ResponseEntity<Cancion> querySong = cancionService.getSongByID(idSong);
			
			Album album = queryAlbum.get();
			Cancion song = querySong.getBody();
			
			if(album.containsCancion(song)) {
				System.out.println("YA TENIA ESTA CANCION");
				resul = WRONG_RESULT;
			}
			else {
				album.addCancion(song);
				album = albumService.save(album);
				resul = CORRECT;
			}
			
		}
		catch(Exception e) {
			resul = ERROR;
		}
		return resul;
		
	}
	
	//////////////////////////////////////////////
	// VER SI UNA CANCIÓN ESTÁ EN LA ALBUM	//
	//////////////////////////////////////////////
	@RequestMapping(value="/isInAlbum/{idAlbum}/{idSong}", method=RequestMethod.GET)
	public int isInAlbum(@PathVariable(value = "idAlbum") Long idAlbum,
	@PathVariable(value = "idSong") Long idSong) {
		
		int resul;
		
		try {
			// Invoca lógica de negocio
			Optional<Album> queryAlbum = albumService.getAlbumById(idAlbum);
			ResponseEntity<Cancion> querySong = cancionService.getSongByID(idSong);
			
			Album album = queryAlbum.get();
			Cancion song = querySong.getBody();
			
			if(album.containsCancion(song)) {
				resul = CORRECT;
			}
			else {
				resul = WRONG_RESULT;
			}
		}
		catch(Exception e) {
			resul = ERROR;
		}
		
		return resul;
		
	}
	
	//////////////////////////////////////////////
	// QUITAR CANCIÓN DE LA ALBUM	 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/takeOut/{idAlbum}/{idSong}", method=RequestMethod.PUT)
	public int takeOut(@PathVariable(value = "idAlbum") Long idAlbum,
	@PathVariable(value = "idSong") Long idSong) {
		
		int resul;
		
		try{
			// Invoca lógica de negocio
			Optional<Album> queryAlbum = albumService.getAlbumById(idAlbum);
			ResponseEntity<Cancion> querySong = cancionService.getSongByID(idSong);
			
			Album album = queryAlbum.get();
			Cancion song = querySong.getBody();
			
			album.removeCancion(song);
			album = albumService.save(album);
			
			resul = CORRECT;
			
		}
		catch(Exception e) {
			resul = ERROR;
		}
		
		return resul;
		
	}
	
	//////////////////////////////////////////////
	// LISTA CANCIONES					 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/songList/{id}", method=RequestMethod.GET)
	public Set<Cancion> songList(@PathVariable(value = "id") Long id) {
		
		// Invoca lógica de negocio
		Optional<Album> queryAlbum = albumService.getAlbumById(id);
		
		Album album = queryAlbum.get();
		
		// Mapeo entity
		AlbumResponse albumResponse = mapper.map(album, AlbumResponse.class);
		
		return albumResponse.getCanciones();
		
	}
	
	//////////////////////////////////////////////
	// AUTOR DEL ÁLBUM					 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/getAutor/{idAlbum}", method=RequestMethod.GET)
	public Artista autor(@PathVariable(value = "idAlbum") Long idAlbum) {
		
		// Invoca lógica de negocio
		Optional<Album> queryAlbum = albumService.getAlbumById(idAlbum);
		
		Album album = queryAlbum.get();
		
		// Mapeo entity
		AlbumResponse albumResponse = mapper.map(album, AlbumResponse.class);
		
		return albumResponse.getAutor();
		
	}
	
	//////////////////////////////////////////////
	// FECHAS							 		//
	//////////////////////////////////////////////
	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
}