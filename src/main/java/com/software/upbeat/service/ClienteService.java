package com.software.upbeat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	public List<Cliente> getAllClientes() {
		return this.dao.findAll();
	}
	
	public ResponseEntity<Cliente> getClienteByEmail(String correo){
		Cliente cliente = dao.findByEmail(correo);
		return ResponseEntity.ok().body(cliente);
	}
}
