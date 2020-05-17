package com.software.upbeat.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.software.upbeat.model.Cancion;


public interface CancionRepository extends JpaRepository<Cancion, Long>{
	@Query(value = "SELECT * FROM cancion c WHERE c.nombre = ?1", nativeQuery = true)
	Cancion findSongByName(String nombre);
	
	@Query(value = "SELECT * FROM cancion c WHERE c.id = ?1", nativeQuery = true)
	Cancion findSongByID(Long id);
	
	@Query(value = "SELECT song FROM cancion c WHERE c.nombre = ?1 AND c.artista = ?2", nativeQuery = true)
	Cancion findSongByNameAndArtist(String nombre, String autor);
	
	@Query(value = "SELECT * FROM cancion c ORDER BY reproducciones DESC", nativeQuery = true)
	List<Cancion> findSongsByPopularity();
	
	@Query(value = "SELECT * FROM cancion c WHERE c.autor=?1", nativeQuery = true)
	List<Cancion> findSongsByAutor(String autor);
	
}
