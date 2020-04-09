package com.software.upbeat.api;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.software.upbeat.model.Cliente;
import com.software.upbeat.service.ClienteService;

@RestController
public class ClienteApi {
	
	@RequestMapping(value="/cliente", method=RequestMethod.GET)
	public Cliente getById() {
		return new Cliente(1L, "Pepe", "Ruiz", "pepecontra", "pepito@gmail.com", "pepitomusic", "España");
	}
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	Mapper mapper;
	
	//////////////////////////////////////////////
	// OBTENER TODOS LOS CLIENTES				//
	//////////////////////////////////////////////
	
	@RequestMapping(value="/allClientes", method=RequestMethod.GET)
	public List<Cliente> getAllClientes() {
		return clienteService.getAllClientes();
	}	
	
	@RequestMapping(value="/cliente", method=RequestMethod.POST)
	public ClienteResponse updateOrSave(@RequestBody ClienteRequest clienteRequest) {
		
		// Mapeo request dto
		Cliente cliente = mapper.map(clienteRequest, Cliente.class);
		
		// Invoca lógica de negocio
		Cliente updatedCliente = clienteService.save(cliente);
		
		// Mapeo entity
		ClienteResponse clienteResponse = mapper.map(updatedCliente, ClienteResponse.class);
		
		return clienteResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
}
