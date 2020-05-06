package com.software.upbeat.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.software.upbeat.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	@Query(value = "SELECT * FROM cliente u WHERE u.correo = ?1", nativeQuery = true)
	Cliente findByEmail(String correo);
	
	@Query(value = "SELECT * FROM cliente u WHERE u.contrasenya = ?1 AND u.correo = ?2", nativeQuery = true)
	Cliente findByEmailAndPassword(String contrasenya, String correo);
	
	@Query(value = "SELECT * FROM cliente u WHERE u.correo = ?1", nativeQuery = true)
	Optional<Cliente> findPhotoByEmail(String correo);
}
