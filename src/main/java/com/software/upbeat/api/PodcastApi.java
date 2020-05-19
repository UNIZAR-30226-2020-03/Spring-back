package com.software.upbeat.api;

import java.io.IOException;
import java.util.Date;
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
import com.software.upbeat.model.Podcast;
import com.software.upbeat.service.PodcastService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/podcast/")
public class PodcastApi {
	@Autowired
	PodcastService podcastService;
	
	@Autowired
	Mapper mapper;
	
	//////////////////////////////////////////////////////////////////
	// STREAMING  URL PODCAST POR NOMBRE TEMPORADA Y EPISODIO      	//
	/////////////////////////////////////////////////////////////////
	@GetMapping(value="/getStreamMp3Url/{nombre}/{temp}/{ep}")
	public String  getUrlMp3ByNameAndTemporadaAndEpisodio(@PathVariable(value = "nombre") String nombre,@PathVariable(value = "temp") int temporada,@PathVariable(value = "ep") int episodio) throws IOException{
		return podcastService.getPodcastMp3URLByNameAndTemporadaAndEpisodio(nombre,temporada,episodio);
	}
	
	//////////////////////////////////////////////////////////////////
	// IMAGE URL PODCAST POR NOMBRE TEMPORADA Y EPISODIO      	//
	/////////////////////////////////////////////////////////////////
	@GetMapping(value="/getStreamImgUrl/{nombre}/{temp}/{ep}")
	public String  getUrlImgByNameAndTemporadaAndEpisodio(@PathVariable(value = "nombre") String nombre,@PathVariable(value = "temp") int temporada,@PathVariable(value = "ep") int episodio) throws IOException{
	return podcastService.getPodcastImgURLByNameAndTemporadaAndEpisodio(nombre,temporada,episodio);
	}

	//////////////////////////////////////////////
	// OBTENER PODCAST POR NOMBRE           	//
	//////////////////////////////////////////////
	@RequestMapping(value="/get/{nombre}", method=RequestMethod.GET)
	public List<Podcast> getByName(@PathVariable(value = "nombre") String nombre) {
	return podcastService.getPodcastByName(nombre);
	}

	//////////////////////////////////////////////
	// OBTENER PODCAST POR NOMBRE Y TEMPORADA 	//
	//////////////////////////////////////////////
	@RequestMapping(value="/get/{nombre}/{temporada}", method=RequestMethod.GET)
	public List<Podcast> getByEmailAndPassword(@PathVariable(value = "nombre") String nombre,@PathVariable(value = "temp") int temporada) {
		return podcastService.getPodcastByNameAndTemporada(nombre,temporada);
	}
	
	//////////////////////////////////////////////
	// OBTENER TODOS LOS PODCAST     			//
	//////////////////////////////////////////////
	@RequestMapping(value="/allPodcasts", method=RequestMethod.GET)
	public List<Podcast> getAllPodcast() {
		return podcastService.getAllPodcast();
	}
	
	//////////////////////////////////////////////////////////////////////
	// OBTENER TODOS LAS PODCAST ORDENADOS POR POPULARIDAD			    //
	//////////////////////////////////////////////////////////////////////
	@RequestMapping(value="/allPodcastOrderByPopularity", method=RequestMethod.GET)
	public List<Podcast> findPodcastByPopularity() {
	return podcastService.findPodcastByPopularity();
	}
	

	//////////////////////////////////////////////////////
	// ACTUALIZAR PODCAST POR EL NOMBRE TEMP Y EP 		//
	/////////////////////////////////////////////////////
	@RequestMapping(value="/update/{nombre}/{temp}/{ep}", method=RequestMethod.PUT)
	public PodcastResponse update(@PathVariable(value = "nombre") String nombre,@PathVariable(value = "temp") int temporada,@PathVariable(value = "ep") int episodio,
			@Valid @RequestBody PodcastRequest datosPodcast) {
		
		// Mapeo request dto
		Podcast podcast = mapper.map(datosPodcast, Podcast.class);
		
		// Invoca lógica de negocio
		ResponseEntity<Podcast>  podcastByNameTempEp = podcastService.getPodcastByNameAndTemporadaAndEpisodio(nombre,temporada,episodio);
		
		Podcast updatePodcast = podcastByNameTempEp.getBody();
		
		updatePodcast.setNombre(podcast.getNombre());
		updatePodcast.setEpisodio(podcast.getEpisodio());
		updatePodcast.setTemporada(podcast.getTemporada());
		updatePodcast.setDescripcion(podcast.getDescripcion());
		updatePodcast.setCreador(podcast.getCreador());
		updatePodcast.setPathMp3(podcast.getPathMp3());
		updatePodcast.setPathImg(podcast.getPathImg());
		updatePodcast.setDuracion(podcast.getDuracion());
		updatePodcast.setFecha(podcast.getFecha());
		
		updatePodcast = podcastService.save(updatePodcast);
		
		// Mapeo entity
		PodcastResponse podcastResponse = mapper.map(updatePodcast, PodcastResponse.class);
		return podcastResponse;
	}
	
	//////////////////////////////////////////////////
	// ELIMINAR PODCAST POR NOMBRE TEMP Y EPISODIO	//
	/////////////////////////////////////////////////
	@RequestMapping(value="/delete/{nombre}/{temp}/{ep}", method=RequestMethod.DELETE)
	public Map<String, Boolean> delete(@PathVariable(value = "nombre") String nombre,@PathVariable(value = "temp") int temporada,@PathVariable(value = "ep") int episodio) {
		
		// Invoca lógica de negocio
		ResponseEntity<Podcast>  podcastByNameTempEp = podcastService.getPodcastByNameAndTemporadaAndEpisodio(nombre,temporada,episodio);
		
		
		Podcast deletePodcast = podcastByNameTempEp.getBody();
		
		podcastService.delete(deletePodcast);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("ELIMINADO", Boolean.TRUE);
		return response;
		
	}
//////////////////////////////////////////////
// AÑADIR PODCAST       				    //
//////////////////////////////////////////////

@RequestMapping(value="/save", method=RequestMethod.POST)
public PodcastResponse savePodcast(@RequestBody PodcastRequest podcastRequest) throws IOException {

// Mapeo request dto
Podcast podcast = mapper.map(podcastRequest, Podcast.class);
podcast.setReproducciones((long) 0);
if(podcast.getDuracion()==null) podcast.setDuracion((float) 0);
Date fecha=new Date();
if(podcast.getFecha()==null) podcast.setFecha(convertUtilToSql(fecha));
// Invoca lógica de negocio
Podcast newPodcast = podcastService.save(podcast);

// Mapeo entity
PodcastResponse podcastResponse = mapper.map(newPodcast, PodcastResponse.class);

return podcastResponse;

// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
}
	
	//////////////////////////////////////////////
	// ELIMINAR PODCAST		ID			 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/deleteid/{id}", method=RequestMethod.DELETE)
	public Map<String, Boolean> delete(@PathVariable(value = "id") Long id) {
	
	// Invoca lógica de negocio
	ResponseEntity<Podcast> podcastByName = podcastService.getPodcastById(id);
	
	Podcast deletePodcast = podcastByName.getBody();
	
	podcastService.delete(deletePodcast);
	
	Map<String, Boolean> response = new HashMap<>();
	response.put("ELIMINADO", Boolean.TRUE);
	
	return response;
	
	}
	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

}
