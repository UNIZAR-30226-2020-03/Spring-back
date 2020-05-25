package com.software.upbeat.api;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.software.upbeat.model.Album;
import com.software.upbeat.model.Artista;
import com.software.upbeat.model.Cancion;
import com.software.upbeat.model.ListaReproduccion;
import com.software.upbeat.model.Podcast;
import com.software.upbeat.service.AlbumService;
import com.software.upbeat.service.ArtistaService;
import com.software.upbeat.service.CancionService;
import com.software.upbeat.service.ListaReproduccionService;
import com.software.upbeat.service.PodcastService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/artista/")
public class ArtistaApi {
	

	private static final int WRONG_RESULT = 0;

	private static final int CORRECT = 0;

	private static final int ERROR = 0;

	@Autowired
	ArtistaService artistaService;
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	CancionService cancionService;
	
	@Autowired
	PodcastService podcastService;
	
	@Autowired
	AlbumService albumService;
	
	@Autowired
	ListaReproduccionService listaReproduccionService;
	
	//////////////////////////////////////////////
	// OBTENER ARTISTA POR EMAIL				//
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
	// OBTENER ARTISTA POR PASSWORD Y EMAIL 	//
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
	// OBTENER TODOS LOS ARTISTAS				//
	//////////////////////////////////////////////
	
	@RequestMapping(value="/allArtistas", method=RequestMethod.GET)
	public List<Artista> getAllArtistas() {
		return artistaService.getAllArtistas();
	}
	
	//////////////////////////////////////////////
	// AÑADIR ARTISTA							//
	//////////////////////////////////////////////
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ArtistaResponse saveArtista(@RequestBody ArtistaRequest artistaRequest) {
		
		// Mapeo request dto
		Artista artista = mapper.map(artistaRequest, Artista.class);
		artista.setNumCanciones(0);
		artista.setNumPodcast(0);
		// Invoca lógica de negocio
		ListaReproduccion lr = new ListaReproduccion();
		listaReproduccionService.save(lr);
		artista.setListaRep(lr);
		
		Artista newArtista = artistaService.save(artista);
		
		// Mapeo entity
		ArtistaResponse artistaResponse = mapper.map(newArtista, ArtistaResponse.class);
		
		return artistaResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
	//////////////////////////////////////////////
	// OBTENER IMAGEN POR EMAIL				//
	//////////////////////////////////////////////
	@GetMapping(value="/getImgUrl/{correo}")
	public String  getUrlImgByEmail(@PathVariable(value = "correo") String correo) throws IOException{
		return artistaService.getImgByEmail(correo);
	}
	
	//////////////////////////////////////////////
	// ACTUALIZAR ARTISTA POR EL CORREO 		//
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
		updateArtista.setPathImg(artista.getPathImg());
		updateArtista.setUsername(artista.getUsername());
		updateArtista.setPais(artista.getPais());
		
		updateArtista = artistaService.save(updateArtista);
		
		
		// Mapeo entity
		ArtistaResponse artistaResponse = mapper.map(updateArtista, ArtistaResponse.class);
		
		return artistaResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
	
	//////////////////////////////////////////////
	// CREAR CANCION			 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/createSong/{correoArtista}/{songId}", method=RequestMethod.PUT)
	public int createSong(@PathVariable(value = "correoArtista") String correoArtista,
	@PathVariable(value = "songId") Long songId) {
		
		int resul;
		
		System.out.println("CREATE SONG");
		
		try {
			// Invoca lógica de negocio
			ResponseEntity<Artista> artistaByEmail = artistaService.getArtistaByEmail(correoArtista);
			Artista artista = artistaByEmail.getBody();
			
			// System.out.println(artista);
			
			ResponseEntity<Cancion> newSong = cancionService.getSongByID(songId);
			
			
			Cancion cancion = newSong.getBody();
			
			System.out.println("---------");
			// System.out.println(artista + " -- " + cancion);
			
			if(artista.containsCancion(cancion)) {
				System.out.println("YA TENÍA ESA CANCION");
				resul = WRONG_RESULT;
			}
			else {
				System.out.println("AÑADO CANCION");
				artista.addCancion(cancion);
				cancion.setCreador(artista);
				System.out.println("AÑADIDA");
				cancion = cancionService.save(cancion);
				artista = artistaService.save(artista);
				resul = CORRECT;
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
			resul = ERROR;
		}
		return resul;
		
	}
	
	//////////////////////////////////////////////
	// CREAR PODCAST			 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/createPodcast/{miCorreo}/{podcastId}", method=RequestMethod.PUT)
	public int createPodcast(@PathVariable(value = "miCorreo") String correoArtista,
	@PathVariable(value = "podcastId") Long podcastId) {
	
	int resul;
	
	try {
	// Invoca lógica de negocio
	ResponseEntity<Artista> artistaByEmail = artistaService.getArtistaByEmail(correoArtista);
	ResponseEntity<Podcast> newPodcast = podcastService.getPodcastById(podcastId);
	
	Artista artista = artistaByEmail.getBody();
	Podcast podcast = newPodcast.getBody();
	
	if(artista.containsPodcast(podcast)) {
	System.out.println("YA TENÍA ESE PODCAST");
	resul = WRONG_RESULT;
	}
	else {
	System.out.println("AÑADO PODCAST");
	artista.addPodcast(podcast);
	System.out.println("AÑADIDO");
	artista = artistaService.save(artista);
	resul = CORRECT;
	}
	
	}
	catch(Exception e) {
	resul = ERROR;
	}
	return resul;
	
	}
	//////////////////////////////////////////////
	// ELIMINAR ARTISTA					 		//
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
	
	//////////////////////////////////////////////
	// LISTA CANCIONES					 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/mySongs/{miCorreo}", method=RequestMethod.GET)
	public Set<Cancion> mySongs(@PathVariable(value = "miCorreo") String correoArtista) {
		
		// Invoca lógica de negocio
		ResponseEntity<Artista> artistaByEmail = artistaService.getArtistaByEmail(correoArtista);
		
		Artista artista = artistaByEmail.getBody();
		
		// Mapeo entity
		ArtistaResponse artistaResponse = mapper.map(artista, ArtistaResponse.class);
		
		return artista.getCanciones();
		
	}
	
	//////////////////////////////////////////////
	// LISTA PODCAST					 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/myPodcast/{miCorreo}", method=RequestMethod.GET)
	public Set<Podcast> myPodcast(@PathVariable(value = "miCorreo") String correoArtista) {
		
		// Invoca lógica de negocio
		ResponseEntity<Artista> artistaByEmail = artistaService.getArtistaByEmail(correoArtista);
		
		Artista artista = artistaByEmail.getBody();
		
		// Mapeo entity
		ArtistaResponse artistaResponse = mapper.map(artista, ArtistaResponse.class);
		
		return artista.getPodcasts();
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	// ÁLBUMES																	//
	//////////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////
	// CREAR UN ÁLBUM					 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/createAlbum/{miCorreo}/{albumId}", method=RequestMethod.PUT)
	public int createAlbum(@PathVariable(value = "miCorreo") String correoArtista,
	@PathVariable(value = "albumId") Long albumId) {
		
		int resul;
		
		System.out.println("CREATE ALBUM");
		
		try {
			// Invoca lógica de negocio
			ResponseEntity<Artista> artistaByEmail = artistaService.getArtistaByEmail(correoArtista);
			Optional<Album> newAlbum = albumService.getAlbumById(albumId);
			
			Artista artista = artistaByEmail.getBody();
			Album album = newAlbum.get();
			
			System.out.println(artista + "    " + album);
			
			if(artista.containsAlbum(album)) {
				System.out.println("YA TENÍA ESE ÁLBUM");
				resul = WRONG_RESULT;
			}
			else {
				System.out.println("AÑADO ÁLBUM");
				artista.addAlbum(album);
				album.setAutor(artista);
				System.out.println("AÑADIDO");
				album = albumService.save(album);
				artista = artistaService.save(artista);
				resul = CORRECT;
			}
			
		}
		catch(Exception e) {
			resul = ERROR;
		}
		return resul;
		
	}
	
	//////////////////////////////////////////////
	// LISTA PLAYLISTS					 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/myAlbums/{miCorreo}", method=RequestMethod.GET)
	public Set<Album> myAlbums(@PathVariable(value = "miCorreo") String correoArtista) {
		
		// Invoca lógica de negocio
		ResponseEntity<Artista> artistaByEmail = artistaService.getArtistaByEmail(correoArtista);
		
		Artista artista = artistaByEmail.getBody();
		
		// Mapeo entity
		ArtistaResponse artistaResponse = mapper.map(artista, ArtistaResponse.class);
		
		return artistaResponse.getAlbumes();
		
	}
	
	//////////////////////////////////////////////
	// VER SI UNA PLAYLIST ES DE UN CLIENTE		//
	//////////////////////////////////////////////
	@RequestMapping(value="/isMyAlbum/{miCorreo}/{albumId}", method=RequestMethod.GET)
	public int isMyAlbum(@PathVariable(value = "miCorreo") String correoArtista,
	@PathVariable(value = "albumId") Long albumId) {
		
		int resul;
		
		try {
			// Invoca lógica de negocio
			ResponseEntity<Artista> artistaByEmail = artistaService.getArtistaByEmail(correoArtista);
			Optional<Album> newAlbum = albumService.getAlbumById(albumId);
			
			Artista artista = artistaByEmail.getBody();
			Album album = newAlbum.get();
			
			if(artista.containsAlbum(album)) {
				resul = CORRECT;
			}
			else {
				resul = WRONG_RESULT;
			}
		}
		catch(Exception e) {
			System.out.println(e);
			resul = ERROR;
		}
		
		return resul;
		
	}


}
