package com.software.upbeat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.software.upbeat.dao.ClienteRepository;
import com.software.upbeat.model.Cliente;

@Component
public class ClienteService {

	@Autowired
	ClienteRepository dao;
	
	public Cliente save(Cliente cliente){
		return dao.saveAndFlush(cliente);
	}
	
}
