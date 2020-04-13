package com.software.upbeat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.software.upbeat.model.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Long>{
	@Query(value = "SELECT * FROM cliente a WHERE a.correo = ?1", nativeQuery = true)
	Artista findByEmail(String correo);
	
	@Query(value = "SELECT * FROM cliente a WHERE a.contrasenya = ?1 AND a.correo = ?2", nativeQuery = true)
	Artista findByEmailAndPassword(String contrasenya, String correo);
}
