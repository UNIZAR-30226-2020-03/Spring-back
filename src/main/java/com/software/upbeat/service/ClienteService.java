package com.software.upbeat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.software.upbeat.dao.ClienteRepository;
import com.software.upbeat.exception.ResourceNotFoundException;
import com.software.upbeat.model.Cliente;

@Component
public class ClienteService {

	@Autowired
	ClienteRepository dao;
	
	public Cliente save(Cliente cliente){
		return dao.saveAndFlush(cliente);
	}
	
	// OBTENER TODOS LOS CLIENTES
	public List<Cliente> getAllClientes() {
		return this.dao.findAll();
	}
	
	// OBTENER CLIENTE POR ID
	public ResponseEntity<Cliente> getClienteById(Long id) throws ResourceNotFoundException{
		Cliente cliente = dao.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe cliente con este id: " + id));
		return ResponseEntity.ok().body(cliente);
	}
	
}
