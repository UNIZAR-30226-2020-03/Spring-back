package com.software.upbeat.api;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.software.upbeat.exception.ResourceNotFoundException;
import com.software.upbeat.model.Cliente;
import com.software.upbeat.service.ClienteService;

@RestController
public class ClienteApi {
	
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
	
	//////////////////////////////////////////////
	// OBTENER CLIENTE POR ID					//
	//////////////////////////////////////////////
	@RequestMapping(value="/cliente/{cod_cliente}", method=RequestMethod.GET)
	public ClienteResponse getById(@PathVariable(value = "cod_cliente") Long clienteId) throws ResourceNotFoundException {
		
		// Mapeo request dto
		// Cliente cliente = mapper.map(clienteRequest, Cliente.class);
		
		// Invoca lógica de negocio
		ResponseEntity<Cliente> clienteById = clienteService.getClienteById(clienteId);
				
		// Mapeo entity
		ClienteResponse clienteResponse = mapper.map(clienteById, ClienteResponse.class);
				
		return clienteResponse;
				
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
	
	//////////////////////////////////////////////
	// ACTUALIZAR O GUARDAR CLIENTE				//
	//////////////////////////////////////////////	
	@RequestMapping(value="/saveCliente", method=RequestMethod.POST)
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

