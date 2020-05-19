package com.software.upbeat.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.software.upbeat.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Long>{
	
	@Query(value = "SELECT * FROM album a WHERE a.nombre = ?1", nativeQuery = true)
	List<Album> findByName(String nombre);

}
