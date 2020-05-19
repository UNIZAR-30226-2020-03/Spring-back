package com.software.upbeat.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.software.upbeat.model.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long>{

	@Query(value = "SELECT * FROM playlist p WHERE p.nombre = ?1", nativeQuery = true)
	List<Playlist> findByName(String nombre);
	
	@Query(value = "SELECT * FROM playlist p WHERE p.id = ?1", nativeQuery = true)
	Optional<Playlist> findById(Long id);
	
	/*
	 * https://www.objectdb.com/java/jpa/query/jpql/from
	 */
	@Query(value = "SELECT * FROM playlist p WHERE p.nombre = ?1", nativeQuery = true)
	List<Playlist> findCreatorPlaylists();
}
