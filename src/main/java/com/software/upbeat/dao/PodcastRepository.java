package com.software.upbeat.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.software.upbeat.model.Podcast;

public interface PodcastRepository  extends JpaRepository<Podcast, Long> {
	@Query(value = "SELECT * FROM podcast c WHERE c.nombre = ?1", nativeQuery = true)
	List<Podcast> findPodcastByName(String nombre);
	
	@Query(value = "SELECT * FROM podcast c WHERE c.nombre = ?1 and c.temporada=?2", nativeQuery = true)
	List<Podcast> findPodcastByNameAndTemporada(String nombre, int temporada);
	
	@Query(value = "SELECT * FROM podcast c WHERE c.nombre = ?1 and c.temporada=?2 and c.episodio=?3", nativeQuery = true)
	Podcast findPodcastByNameAndTemporadaAndEpisodio(String nombre, int temporada, int episodio);
	
	@Query(value = "SELECT * FROM podcast c WHERE c.id = ?1", nativeQuery = true)
	Podcast findPodcastByID(Long id);
	
	
	@Query(value = "SELECT * FROM podcast c ORDER BY reproducciones DESC", nativeQuery = true)
	List<Podcast> findPodcastByPopularity();
	
	@Query(value = "SELECT * FROM podcast c WHERE c.autor=?1", nativeQuery = true)
	List<Podcast> findPodcastByAutor(String autor);
	
}
