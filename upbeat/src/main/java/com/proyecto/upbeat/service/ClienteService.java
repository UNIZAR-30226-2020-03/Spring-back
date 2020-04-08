package com.proyecto.upbeat.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.proyecto.upbeat.dao.ClienteRepository;
import com.proyecto.upbeat.dto.Cliente;

public class ClienteService {
	
	@Autowired
	ClienteRepository dao;
	
	public Cliente save(Cliente cliente){
		return dao.saveAndFlush(cliente);
	}

}
