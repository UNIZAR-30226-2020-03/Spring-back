package com.software.upbeat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.software.upbeat.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
