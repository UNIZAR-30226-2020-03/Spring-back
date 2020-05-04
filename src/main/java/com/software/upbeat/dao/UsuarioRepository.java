package com.software.upbeat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.software.upbeat.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query(value = "SELECT * FROM cliente u WHERE u.correo = ?1", nativeQuery = true)
	Usuario findByEmail(String correo);
	
	@Query(value = "SELECT * FROM cliente u WHERE u.contrasenya = ?1 AND u.correo = ?2", nativeQuery = true)
	Usuario findByEmailAndPassword(String contrasenya, String correo);
	
	@Query(value = "SELECT * FROM cliente u WHERE u.username = ?1", nativeQuery = true)
	Usuario findByUsername(String username);
}
