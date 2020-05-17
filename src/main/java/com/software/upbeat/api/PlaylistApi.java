package com.software.upbeat.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.software.upbeat.model.Playlist;
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
	
	/*
	 * TODO: getPlaylistByName(String nombre) <- List
	 * 
	 * ¿¿¿ getPlaylistByNameAndAuthor ???
	 */
	
	//////////////////////////////////////////////
	// OBTENER PLAYLIST POR EMAIL				//
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
		
		updatePlaylist = playlistService.save(updatePlaylist);
		
		
		// Mapeo entity
		PlaylistResponse playlistResponse = mapper.map(updatePlaylist, PlaylistResponse.class);
		
		return playlistResponse;
		
	}
	
	//////////////////////////////////////////////
	// ELIMINAR PLAYLIST					 		//
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
}
