package com.proyecto.upbeat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.upbeat.dto.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
