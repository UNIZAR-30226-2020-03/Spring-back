package com.proyecto.upbeat.api;

import org.dozer.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.proyecto.upbeat.dto.Cliente;
import com.proyecto.upbeat.service.ClienteService;

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
