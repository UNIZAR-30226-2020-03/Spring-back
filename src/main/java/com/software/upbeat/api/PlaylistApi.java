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

import com.software.upbeat.model.Cancion;
import com.software.upbeat.model.Playlist;
import com.software.upbeat.service.CancionService;
import com.software.upbeat.service.PlaylistService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/playlist/")
public class PlaylistApi {

	private static final int CORRECT = 0;
	private static final int ERROR = 1;
	private static final int WRONG_RESULT = 2;
	
	@Autowired
	PlaylistService playlistService;
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	CancionService cancionService;
	
	/*
	 * ¿¿¿ getPlaylistByNameAndAuthor ???
	 */
	
	//////////////////////////////////////////////
	// OBTENER PLAYLIST POR ID					//
	//////////////////////////////////////////////
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET)
    public PlaylistResponse getPlaylistById(@PathVariable(value = "id") Long playlistId) {
		// Invoca lógica de negocio
		Optional<Playlist> playlistById = playlistService.getPlaylistById(playlistId);
		
		// Mapeo entity
		PlaylistResponse playlistResponse = mapper.map(playlistById.get(), PlaylistResponse.class);
		
		return playlistResponse;
    }
	
	//////////////////////////////////////////////
	// OBTENER PLAYLIST/S POR NOMBRE			//
	//////////////////////////////////////////////
	@RequestMapping(value="/getByName/{name}", method=RequestMethod.GET)
	public List<Playlist> getPlaylistByName(@PathVariable(value = "name") String nombre) {
		return playlistService.getPlaylistByName(nombre);
	}
	
	//////////////////////////////////////////////
	// OBTENER TODOS LOS PLAYLISTS				//
	//////////////////////////////////////////////
	
	@RequestMapping(value="/allPlaylists", method=RequestMethod.GET)
	public List<Playlist> getAllPlaylists() {
		return playlistService.getAllPlaylists();
	}
	
	//////////////////////////////////////////////
	// AÑADIR PLAYLIST							//
	//////////////////////////////////////////////
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public PlaylistResponse savePlaylist(@RequestBody PlaylistRequest playlistRequest) {
		
		// Mapeo request dto
		Playlist playlist = mapper.map(playlistRequest, Playlist.class);
		
		// Invoca lógica de negocio
		Playlist newPlaylist = playlistService.save(playlist);
		
		// Mapeo entity
		PlaylistResponse playlistResponse = mapper.map(newPlaylist, PlaylistResponse.class);
		
		return playlistResponse;
		
	}

	
	//////////////////////////////////////////////
	// ACTUALIZAR PLAYLIST POR EL CORREO 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT)
	public PlaylistResponse update(@PathVariable(value = "id") Long idPlaylist,
	@Valid @RequestBody PlaylistRequest datosPlaylist) {
		
		// Mapeo request dto
		Playlist playlist = mapper.map(datosPlaylist, Playlist.class);
		
		// Invoca lógica de negocio
		Optional<Playlist> playlistById = playlistService.getPlaylistById(idPlaylist);
		
		Playlist updatePlaylist = playlistById.get();
		
		updatePlaylist.setNombre(playlist.getNombre());
		updatePlaylist.setDescripcion(playlist.getDescripcion());
		updatePlaylist.setPathImg(playlist.getPathImg());
		updatePlaylist = playlistService.save(updatePlaylist);
		
		
		// Mapeo entity
		PlaylistResponse playlistResponse = mapper.map(updatePlaylist, PlaylistResponse.class);
		
		return playlistResponse;
		
	}
	
	//////////////////////////////////////////////
	// ELIMINAR PLAYLIST				 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public Map<String, Boolean> delete(@PathVariable(value = "id") Long idPlaylist) {
		
		// Invoca lógica de negocio
		Optional<Playlist> playlistById = playlistService.getPlaylistById(idPlaylist);
		
		Playlist deletePlaylist = playlistById.get();
		
		playlistService.delete(deletePlaylist);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("ELIMINADO", Boolean.TRUE);
		
		
		// Mapeo entity
		// PlaylistResponse playlistResponse = mapper.map(deletePlaylist, PlaylistResponse.class);
		
		return response;
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	// CANCIONES																		//
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////
	// AÑADIR CANCIÓN A UNA PLAYLIST		 	//
	//////////////////////////////////////////////
	@RequestMapping(value="/addSong/{idPlaylist}/{idSong}", method=RequestMethod.PUT)
	public int addSong(@PathVariable(value = "idPlaylist") Long idPlaylist,
			@PathVariable(value = "idSong") Long idSong) {
		
		int resul;
		
		try {
		// Invoca lógica de negocio
			Optional<Playlist> queryPlaylist = playlistService.getPlaylistById(idPlaylist);
			ResponseEntity<Cancion> querySong = cancionService.getSongByID(idSong);
			
			Playlist playlist = queryPlaylist.get();
			Cancion song = querySong.getBody();
			
			if(playlist.containsCancion(song)) {
				System.out.println("YA TENÍA ESTA CANCIÓN");
				resul = WRONG_RESULT;
			}
			else {
				playlist.addCancion(song);
				playlist = playlistService.save(playlist);
				resul = CORRECT;
			}
			
		}
		catch(Exception e) {
			resul = ERROR;
		}
		return resul;
		
	}
	
	//////////////////////////////////////////////
	// VER SI UNA CANCIÓN ESTÁ EN LA PLAYLIST	//
	//////////////////////////////////////////////
	@RequestMapping(value="/isInPlaylist/{idPlaylist}/{idSong}", method=RequestMethod.GET)
	public int isInPlaylist(@PathVariable(value = "idPlaylist") Long idPlaylist,
	@PathVariable(value = "idSong") Long idSong) {
		
		int resul;
		
		try {
			// Invoca lógica de negocio
			Optional<Playlist> queryPlaylist = playlistService.getPlaylistById(idPlaylist);
			ResponseEntity<Cancion> querySong = cancionService.getSongByID(idSong);
			
			Playlist playlist = queryPlaylist.get();
			Cancion song = querySong.getBody();
			
			if(playlist.containsCancion(song)) {
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
	// QUITAR CANCIÓN DE LA PLAYLIST	 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/takeOut/{idPlaylist}/{idSong}", method=RequestMethod.PUT)
	public int takeOut(@PathVariable(value = "idPlaylist") Long idPlaylist,
	@PathVariable(value = "idSong") Long idSong) {
		
		int resul;
		
		try{
			// Invoca lógica de negocio
			Optional<Playlist> queryPlaylist = playlistService.getPlaylistById(idPlaylist);
			ResponseEntity<Cancion> querySong = cancionService.getSongByID(idSong);
			
			Playlist playlist = queryPlaylist.get();
			Cancion song = querySong.getBody();
			
			playlist.removeCancion(song);
			playlist = playlistService.save(playlist);
			
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
		Optional<Playlist> queryPlaylist = playlistService.getPlaylistById(id);
		
		Playlist playlist = queryPlaylist.get();
		
		// Mapeo entity
		PlaylistResponse playlistResponse = mapper.map(playlist, PlaylistResponse.class);
		
		return playlistResponse.getCanciones();
		
	}
}
