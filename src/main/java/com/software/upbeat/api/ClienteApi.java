package com.software.upbeat.api;

import java.io.IOException;
import java.util.ArrayList;
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
import com.software.upbeat.model.Cliente;
import com.software.upbeat.model.ListaReproduccion;
import com.software.upbeat.model.Playlist;
import com.software.upbeat.service.CancionService;
import com.software.upbeat.service.ClienteService;
import com.software.upbeat.service.ListaReproduccionService;
import com.software.upbeat.service.PlaylistService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/cliente/")
public class ClienteApi {
	
	private static final int CORRECT = 0;
	private static final int ERROR = 1;
	private static final int WRONG_RESULT = 2;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	PlaylistService playlistService;
	
	@Autowired
	CancionService cancionService;
	
	@Autowired
	ListaReproduccionService listaReproduccionService;;
	
	//////////////////////////////////////////////
	// OBTENER CLIENTE POR EMAIL				//
	//////////////////////////////////////////////
	@RequestMapping(value="/get/{correo}", method=RequestMethod.GET)
	public ClienteResponse getByEmail(@PathVariable(value = "correo") String correoCliente) {
	
		// Mapeo request dto
		// Cliente cliente = mapper.map(clienteRequest, Cliente.class);
		
		// Invoca lógica de negocio
		ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
		
		// Mapeo entity
		ClienteResponse clienteResponse = mapper.map(clienteByEmail.getBody(), ClienteResponse.class);
		
		return clienteResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
	
	//////////////////////////////////////////////
	// OBTENER IMAGEN POR EMAIL				//
	//////////////////////////////////////////////
	@GetMapping(value="/getImgUrl/{correo}")
	public String  getUrlImgByEmail(@PathVariable(value = "correo") String correo) throws IOException{
		return clienteService.getImgByEmail(correo);
	}
	
	//////////////////////////////////////////////
	// OBTENER CLIENTE POR PASSWORD Y EMAIL 	//
	//////////////////////////////////////////////
	@RequestMapping(value="/get/{contrasenya}/{correo}", method=RequestMethod.GET)
	public ClienteResponse getByEmailAndPassword(@PathVariable(value = "contrasenya") String password, @PathVariable(value = "correo") String correoCliente) {
		// Invoca lógica de negocio
		ResponseEntity<Cliente> clienteByEmailAndPassword = clienteService.getClienteByEmailAndPassword(password, correoCliente);
		
		// Mapeo entity
		ClienteResponse clienteResponse = mapper.map(clienteByEmailAndPassword.getBody(), ClienteResponse.class);
		
		return clienteResponse;
	}
	//////////////////////////////////////////////
	// OBTENER TODOS LOS CLIENTES				//
	//////////////////////////////////////////////
	
	@RequestMapping(value="/allClientes", method=RequestMethod.GET)
	public List<Cliente> getAllClientes() {
		return clienteService.getAllClientes();
	}
	
	//////////////////////////////////////////////
	// AÑADIR CLIENTE							//
	//////////////////////////////////////////////

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ClienteResponse saveCliente(@RequestBody ClienteRequest clienteRequest) {
		
		// Mapeo request dto
		Cliente cliente = mapper.map(clienteRequest, Cliente.class);
		/*
		ArrayList<Long> aux =new ArrayList<Long>();
		cliente.setLista10Ultimas(aux);
		cliente.setUltimaCancion(0);
		*/
		// Invoca lógica de negocio
		Cliente newCliente = clienteService.save(cliente);
		
		// Mapeo entity
		ClienteResponse clienteResponse = mapper.map(newCliente, ClienteResponse.class);
		
		return clienteResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
	
	//////////////////////////////////////////////
	// ACTUALIZAR CLIENTE POR EL CORREO 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/update/{correo}", method=RequestMethod.PUT)
	public ClienteResponse update(@PathVariable(value = "correo") String correoCliente,
			@Valid @RequestBody ClienteRequest datosCliente) {
		
		// Mapeo request dto
		Cliente cliente = mapper.map(datosCliente, Cliente.class);
		
		// Invoca lógica de negocio
		ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
		
		Cliente updateCliente = clienteByEmail.getBody();
		
		updateCliente.setNombre(cliente.getNombre());
		updateCliente.setApellidos(cliente.getApellidos());
		updateCliente.setContrasenya(cliente.getContrasenya());
		updateCliente.setCorreo(cliente.getCorreo());
		updateCliente.setPathImg(cliente.getPathImg());
		updateCliente.setUsername(cliente.getUsername());
		updateCliente.setPais(cliente.getPais());
		
		updateCliente = clienteService.save(updateCliente);
		
		
		// Mapeo entity
		ClienteResponse clienteResponse = mapper.map(updateCliente, ClienteResponse.class);
		
		return clienteResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
	
	//////////////////////////////////////////////
	// ELIMINAR CLIENTE					 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/delete/{correo}", method=RequestMethod.DELETE)
	public Map<String, Boolean> delete(@PathVariable(value = "correo") String correoCliente) {
		
		// Invoca lógica de negocio
		ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
		
		Cliente deleteCliente = clienteByEmail.getBody();
		
		clienteService.delete(deleteCliente);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("ELIMINADO", Boolean.TRUE);
		
		
		// Mapeo entity
		// ClienteResponse clienteResponse = mapper.map(deleteCliente, ClienteResponse.class);
		
		return response;
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	// AMIGOS																		//
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////
	// SEGUIR A UN CLIENTE				 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/follow/{miCorreo}/{suCorreo}", method=RequestMethod.PUT)
	public int follow(@PathVariable(value = "miCorreo") String correoCliente,
	@PathVariable(value = "suCorreo") String correoAmigo) {
		
		int resul;
		
		try {
			// Invoca lógica de negocio
			ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
			ResponseEntity<Cliente> amigoByEmail = clienteService.getClienteByEmail(correoAmigo);
			
			Cliente cliente = clienteByEmail.getBody();
			Cliente amigo = amigoByEmail.getBody();
			
			if(cliente.containsAmigo(amigo)) {
				System.out.println("YA SE SEGUÍA");
				resul = WRONG_RESULT;
			}
			else {
				cliente.addAmigo(amigo);
				cliente = clienteService.save(cliente);
				resul = CORRECT;
			}
		
		}
		catch(Exception e) {
			resul = ERROR;
		}
		return resul;
		
	}
	
	//////////////////////////////////////////////
	// VER SI UN CLIENTE ES AMIGO		 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/following/{miCorreo}/{suCorreo}", method=RequestMethod.GET)
	public int following(@PathVariable(value = "miCorreo") String correoCliente,
	@PathVariable(value = "suCorreo") String correoAmigo) {
		
		int resul;
		
		try {
			// Invoca lógica de negocio
			ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
			ResponseEntity<Cliente> amigoByEmail = clienteService.getClienteByEmail(correoAmigo);
			
			Cliente cliente = clienteByEmail.getBody();
			Cliente amigo = amigoByEmail.getBody();
			
			if(cliente.containsAmigo(amigo)) {
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
	// DEJAR DE SEGUIR					 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/unfollow/{miCorreo}/{suCorreo}", method=RequestMethod.PUT)
	public int unfollow(@PathVariable(value = "miCorreo") String correoCliente,
	@PathVariable(value = "suCorreo") String correoAmigo) {
		
		int resul;
		
		try{
			// Invoca lógica de negocio
			ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
			ResponseEntity<Cliente> amigoByEmail = clienteService.getClienteByEmail(correoAmigo);
			
			Cliente cliente = clienteByEmail.getBody();
			Cliente amigo = amigoByEmail.getBody();
			
			cliente.removeAmigo(amigo);
			cliente = clienteService.save(cliente);
			
			resul = CORRECT;
			
		}
		catch(Exception e) {
			resul = ERROR;
		}
		
		return resul;
	
	}
	
	//////////////////////////////////////////////
	// LISTA SIGUIENDO					 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/followingList/{miCorreo}", method=RequestMethod.GET)
	public Set<Cliente> followingList(@PathVariable(value = "miCorreo") String correoCliente) {
	
		// Invoca lógica de negocio
		ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
		
		Cliente cliente = clienteByEmail.getBody();
		
		// Mapeo entity
		ClienteResponse clienteResponse = mapper.map(cliente, ClienteResponse.class);
		
		return clienteResponse.getAmigos();
	
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	// PLAYLISTS																	//
	//////////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////
	// CREAR UNA PLAYLIST				 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/createPlaylist/{miCorreo}/{playlistId}", method=RequestMethod.PUT)
	public int createPlaylist(@PathVariable(value = "miCorreo") String correoCliente,
			@PathVariable(value = "playlistId") Long playlistId) {
		
		int resul;
		
		try {
			// Invoca lógica de negocio
			ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
			Optional<Playlist> newPlaylist = playlistService.getPlaylistById(playlistId);
			
			Cliente cliente = clienteByEmail.getBody();
			Playlist playlist = newPlaylist.get();
			
			if(cliente.containsPlaylist(playlist)) {
				System.out.println("YA TENÍA ESA PLAYLIST");
				resul = WRONG_RESULT;
			}
			else {
				System.out.println("AÑADO PLAYLIST");
				cliente.addPlaylist(playlist);
				System.out.println("AÑADIDA");
				cliente = clienteService.save(cliente);
				resul = CORRECT;
			}
		
		}
		catch(Exception e) {
			resul = ERROR;
		}
		return resul;
	
	}
	
	//////////////////////////////////////////////
	// ELIMINAR PLAYLIST				 		//
	//////////////////////////////////////////////
	/*@RequestMapping(value="/unfollow/{miCorreo}/{suCorreo}", method=RequestMethod.PUT)
	public int deletePlaylist(@PathVariable(value = "miCorreo") String correoCliente,
	@PathVariable(value = "suCorreo") String correoAmigo) {
		
		int resul;
		
		try{
			// Invoca lógica de negocio
			ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
			ResponseEntity<Cliente> amigoByEmail = clienteService.getClienteByEmail(correoAmigo);
			
			Cliente cliente = clienteByEmail.getBody();
			Cliente amigo = amigoByEmail.getBody();
			
			cliente.removeAmigo(amigo);
			cliente = clienteService.save(cliente);
			
			resul = CORRECT;
			
		}
		catch(Exception e) {
			resul = ERROR;
		}
		
		return resul;
		
	}*/
	
	//////////////////////////////////////////////
	// LISTA PLAYLISTS					 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/myPlaylists/{miCorreo}", method=RequestMethod.GET)
	public Set<Playlist> myPlaylists(@PathVariable(value = "miCorreo") String correoCliente) {
	
		// Invoca lógica de negocio
		ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
		
		Cliente cliente = clienteByEmail.getBody();
		
		// Mapeo entity
		ClienteResponse clienteResponse = mapper.map(cliente, ClienteResponse.class);
		return cliente.getPlaylists();
	
	}
	
	//////////////////////////////////////////////
	// VER SI UNA PLAYLIST ES DE UN CLIENTE		//
	//////////////////////////////////////////////
	@RequestMapping(value="/isMyPlaylist/{miCorreo}/{playlistId}", method=RequestMethod.GET)
	public int isMyPlaylist(@PathVariable(value = "miCorreo") String correoCliente,
	@PathVariable(value = "playlistId") Long playlistId) {
		
		int resul;
		
		try {
			// Invoca lógica de negocio
			ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
			Optional<Playlist> newPlaylist = playlistService.getPlaylistById(playlistId);
			
			Cliente cliente = clienteByEmail.getBody();
			Playlist playlist = newPlaylist.get();
			
			if(cliente.containsPlaylist(playlist)) {
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
	
	
	//////////////////////////////////////////////
	// MARCAR PLAYLIST FAVORITOS		 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/favPlaylist/{miCorreo}/{playlistId}", method=RequestMethod.PUT)
	public int favPlaylist(@PathVariable(value = "miCorreo") String correoCliente, 
			@PathVariable(value = "playlistId") Long playlistId) {
		
		int resul;
		
		try {
			// Invoca lógica de negocio
			ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
			Optional<Playlist> newPlaylist = playlistService.getPlaylistById(playlistId);
			
			Cliente cliente = clienteByEmail.getBody();
			Playlist playlist = newPlaylist.get();
			
			if(cliente.containsFavPlaylist(playlist)) {
				System.out.println("YA TENÍA ESA PLAYLIST COMO FAVORITA");
				resul = WRONG_RESULT;
			}
			else {
				System.out.println("AÑADO PLAYLIST FAVORITA");
				cliente.addFavPlaylist(playlist);
				System.out.println("AÑADIDA COMO FAVORITA");
				cliente = clienteService.save(cliente);
				resul = CORRECT;
			}
		
		}
		catch(Exception e) {
			resul = ERROR;
		}
		return resul;
		
	}
	
	//////////////////////////////////////////////
	// VER SI UNA PLAYLIST ES FAVORITA	 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/markFavPlaylist/{miCorreo}/{playlistId}", method=RequestMethod.GET)
	public int markFavPlaylist(@PathVariable(value = "miCorreo") String correoCliente, 
			@PathVariable(value = "playlistId") Long playlistId) {
	
	int resul;
	
	try {
		// Invoca lógica de negocio
		ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
		Optional<Playlist> newPlaylist = playlistService.getPlaylistById(playlistId);
		
		Cliente cliente = clienteByEmail.getBody();
		Playlist playlist = newPlaylist.get();
					
		if(cliente.containsFavPlaylist(playlist)) {
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
	// LISTA PLAYLISTS FAVORITOS		 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/listFavPlaylist/{miCorreo}", method=RequestMethod.GET)
	public Set<Playlist> myFavPlaylists(@PathVariable(value = "miCorreo") String correoCliente) {
	
		// Invoca lógica de negocio
		ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
		
		Cliente cliente = clienteByEmail.getBody();
		
		return cliente.getFavPlaylists();
	
	}
	//////////////////////////////////////////////
	// DESMARCAR PLAYLIST FAVORITA		 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/eliminateFavPlaylist/{miCorreo}/{playlistId}", method=RequestMethod.PUT)
	public int eliminateFavPlaylist(@PathVariable(value = "miCorreo") String correoCliente, 
			@PathVariable(value = "playlistId") Long playlistId)  {
	
	int resul;
	
	try{
		// Invoca lógica de negocio
		ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
		Optional<Playlist> newPlaylist = playlistService.getPlaylistById(playlistId);
		
		Cliente cliente = clienteByEmail.getBody();
		Playlist playlist = newPlaylist.get();
		
		cliente.removeFavPlaylist(playlist);
		cliente = clienteService.save(cliente);
		
		resul = CORRECT;
	
	}catch(Exception e) {
		resul = ERROR;
	}
		return resul;
	}
	
	//////////////////////////////////////////////
	// MARCAR CANCION FAVORITOS	 		 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/favSong/{miCorreo}/{songId}", method=RequestMethod.PUT)
	public int favSong(@PathVariable(value = "miCorreo") String correoCliente, 
			@PathVariable(value = "songId") Long songId) {
	
	int resul;
	
	try {
	// Invoca lógica de negocio
	ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
	ResponseEntity<Cancion> newSong = cancionService.getSongByID(songId);
	
	Cliente cliente = clienteByEmail.getBody();
	Cancion song = newSong.getBody();
	
		if(cliente.containsFavSongs(song)) {
			System.out.println("YA TENÍA ESA CANCION COMO FAVORITA");
			resul = WRONG_RESULT;
		}
		else {
			System.out.println("AÑADO CANCION FAVORITA");
			cliente.addFavSongs(song);
			System.out.println("AÑADIDA COMO FAVORITA");
			cliente = clienteService.save(cliente);
			resul = CORRECT;
		}
	}
	catch(Exception e) {
		resul = ERROR;
	}
	return resul;
	
	}
	
	//////////////////////////////////////////////
	// VER SI UNA CANCION ES FAVORITA	 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/markFavSong/{miCorreo}/{songId}", method=RequestMethod.GET)
	public int markFavSong(@PathVariable(value = "miCorreo") String correoCliente, 
			@PathVariable(value = "songId") Long songId) {
	
	int resul;
	
	try {
		// Invoca lógica de negocio
		ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
		ResponseEntity<Cancion> newSong = cancionService.getSongByID(songId);
		
		Cliente cliente = clienteByEmail.getBody();
		Cancion song = newSong.getBody();
	
		if(cliente.containsFavSongs(song)) {
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
	// LISTA CANCIONES FAVORITOS		 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/songsFavPlaylist/{miCorreo}", method=RequestMethod.GET)
	public Set<Cancion> myFavSongs(@PathVariable(value = "miCorreo") String correoCliente) {
	
		// Invoca lógica de negocio
		ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
		
		Cliente cliente = clienteByEmail.getBody();
		
		return cliente.getFavSongs();
	
	}
	
	//////////////////////////////////////////////
	// DESMARCAR CANCION FAVORITA		 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/eliminateFavSong/{miCorreo}/{songId}", method=RequestMethod.PUT)
	public int eliminateFavSong(@PathVariable(value = "miCorreo") String correoCliente, 
			@PathVariable(value = "songId") Long songId)  {
	
	int resul;
	
	try{
		// Invoca lógica de negocio
		ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
		ResponseEntity<Cancion> newSong = cancionService.getSongByID(songId);
		
		Cliente cliente = clienteByEmail.getBody();
		Cancion song = newSong.getBody();
		
		cliente.removeFavSongs(song);
		cliente = clienteService.save(cliente);
		
		resul = CORRECT;
	
	}catch(Exception e) {
		resul = ERROR;
	}
	return resul;
	}
	
////////////////////////////////// LISTA DE REPRODUCCION /////////////////////////////////
	//////////////////////////////////////////////
	// CREAR MI LISTA DE REPRODUCCION			//
	//////////////////////////////////////////////
	@RequestMapping(value="/createMiListaReprod/{miCorreo}/", method=RequestMethod.PUT)
	public int createListaReproduccion(@PathVariable(value = "miCorreo") String correoCliente) {
		
		int resul;
		
		try {
			// Obtiene cliente
			ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
			Cliente cliente = clienteByEmail.getBody();
			//Crea la lista de reproduccion vacia
			ListaReproduccion listaReproduccion  = new ListaReproduccion();
			listaReproduccion.setCliente(cliente);
		    listaReproduccion  = listaReproduccionService.save(listaReproduccion);
			//relaciona las entidades
			cliente.setListaReproduccion(listaReproduccion);
			System.out.println("AÑADIDA");
			cliente = clienteService.save(cliente);
			resul = CORRECT;
		}
		catch(Exception e) {
			resul = ERROR;
		}
		return resul;
	}
	
	//////////////////////////////////////////////
	// DEVOLVER CANCION  COLA    		 		//
	//////////////////////////////////////////////
	/*
	@RequestMapping(value="/nextSongCola/{miCorreo}", method=RequestMethod.GET)
	public String nextSongCola(@PathVariable(value = "miCorreo") String correoCliente) {
	
	// Invoca lógica de negocio
	ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
	Cliente cliente = clienteByEmail.getBody();
	ListaReproduccion cola = cliente.getListaReproduccion();
	Long id = cola.devolverCancion().getId();
	String url = cancionService.getSongURLById(id);
	cola = listaReproduccionService.save(cola);
	cliente = clienteService.save(cliente);
	return url;
	}
	
	//////////////////////////////////////////////
	//DEVOLVER SEGUNDO CANCION  COLA    		//
	//////////////////////////////////////////////
	@RequestMapping(value="/nextSecondSongCola/{miCorreo}", method=RequestMethod.GET)
	public int nextSecondSongCola(@PathVariable(value = "miCorreo") String correoCliente) {
	
	//Invoca lógica de negocio
	ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
	Cliente cliente = clienteByEmail.getBody();
	ListaReproduccion cola = cliente.getListaReproduccion();
	return cola.getSegundoReproduccion();
	}
	
	//////////////////////////////////////////////
	//ACTUALIZAR SEGUNDO CANCION  COLA    		//
	//////////////////////////////////////////////
	@RequestMapping(value="/updateSecondSongCola/{miCorreo}/{number}", method=RequestMethod.GET)
	public void updateSecondSongCola(@PathVariable(value = "miCorreo") String correoCliente,@PathVariable(value = "number") int number) {
	
	//Invoca lógica de negocio
	ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
	Cliente cliente = clienteByEmail.getBody();
	ListaReproduccion cola = cliente.getListaReproduccion();
	cola.setSegundoReproduccion(number);
	cola = listaReproduccionService.save(cola);
	cliente = clienteService.save(cliente);
	}
	
	//////////////////////////////////////////////
	// AÑADIR CANCION  COLA    		 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/addSongCola/{miCorreo}/{id}", method=RequestMethod.GET)
	public void addSongCola(@PathVariable(value = "miCorreo") String correoCliente,@PathVariable(value = "id") Long id) {
	
	// Invoca lógica de negocio
	ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
	Cliente cliente = clienteByEmail.getBody();
	ListaReproduccion cola = cliente.getListaReproduccion();
	ResponseEntity<Cancion> cancionById = cancionService.getSongByID(id);
	Cancion cancion = cancionById.getBody();
	cola.addCancion(cancion);
	cola = listaReproduccionService.save(cola);
	cliente = clienteService.save(cliente);
	}
	
	*/
//////////////////////////////// FIN LISTA DE REPRODUCCION /////////////////////////////////
	
}
