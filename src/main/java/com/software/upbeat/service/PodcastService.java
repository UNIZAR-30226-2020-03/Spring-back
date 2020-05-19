package com.software.upbeat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.software.upbeat.dao.PodcastRepository;
import com.software.upbeat.model.Podcast;

@Component
public class PodcastService {
	@Autowired
	PodcastRepository dao;
	
	public Podcast save(Podcast podcast){
		return dao.save(podcast);
	}
	
	public List<Podcast> getAllPodcast() {
		return this.dao.findAll();
	}
	
	public List<Podcast> getPodcastByName(String nombre){
		return dao.findPodcastByName(nombre);
	}
	
	public List<Podcast> getPodcastByNameAndTemporada(String nombre,int temporada){
		return dao.findPodcastByNameAndTemporada(nombre,temporada);
	}
	
	public ResponseEntity<Podcast> getPodcastByNameAndTemporadaAndEpisodio(String nombre,int temporada,int episodio){
		 Podcast podcast= dao.findPodcastByNameAndTemporadaAndEpisodio(nombre,temporada,episodio);
		return ResponseEntity.ok().body(podcast);
	}
	
	public String getPodcastMp3URLByNameAndTemporadaAndEpisodio(String nombre,int temporada,int episodio){ //con actualización reproducciones
		Podcast podcast= dao.findPodcastByNameAndTemporadaAndEpisodio(nombre,temporada,episodio);
		podcast.setReproducciones((podcast.getReproducciones()+1));
		dao.save(podcast);
		return podcast.getPathMp3();
	}
	public String getPodcastImgURLByNameAndTemporadaAndEpisodio(String nombre,int temporada,int episodio){ //con actualización reproducciones
		Podcast podcast= dao.findPodcastByNameAndTemporadaAndEpisodio(nombre,temporada,episodio);
		return podcast.getPathImg();
	}
	
	public ResponseEntity<Podcast> getPodcastById(Long id){
		Podcast podcast = dao.findPodcastByID(id);
		return ResponseEntity.ok().body(podcast);
	}
	
	public List<Podcast> findPodcastByPopularity(){
		return dao.findPodcastByPopularity();
	}
	
	public List<Podcast> findPodcastByAutor(String autor){
		return dao.findPodcastByAutor(autor);
	}
	
	public void delete(Podcast podcast) {
		// TODO Auto-generated method stub
		dao.delete(podcast);
	}
}
