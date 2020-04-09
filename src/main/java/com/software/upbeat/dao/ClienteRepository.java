package com.software.upbeat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.software.upbeat.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
