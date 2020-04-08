package com.proyecto.upbeat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.upbeat.dto.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
