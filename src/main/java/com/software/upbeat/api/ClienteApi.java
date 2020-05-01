package com.software.upbeat.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.software.upbeat.model.Cliente;
import com.software.upbeat.service.ClienteService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/cliente/")
public class ClienteApi {
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	Mapper mapper;
	
	
	//////////////////////////////////////////////
	// OBTENER CLIENTE POR EMAIL				//
	//////////////////////////////////////////////
	@RequestMapping(value="/get/{correo}", method=RequestMethod.GET)
	public ClienteResponse getByEmail(@PathVariable(value = "correo") String correoCliente) {
	
		// Mapeo request dto
		// Cliente cliente = mapper.map(clienteRequest, Cliente.class);
		
		// Invoca lógica de negocio
		ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
		
		// Mapeo entity
		ClienteResponse clienteResponse = mapper.map(clienteByEmail.getBody(), ClienteResponse.class);
		
		return clienteResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
	
	//////////////////////////////////////////////
	// OBTENER CLIENTE POR PASSWORD Y EMAIL 	//
	//////////////////////////////////////////////
	@RequestMapping(value="/get/{contrasenya}/{correo}", method=RequestMethod.GET)
	public ClienteResponse getByEmailAndPassword(@PathVariable(value = "contrasenya") String password, @PathVariable(value = "correo") String correoCliente) {
		// Invoca lógica de negocio
		ResponseEntity<Cliente> clienteByEmailAndPassword = clienteService.getClienteByEmailAndPassword(password, correoCliente);
		
		// Mapeo entity
		ClienteResponse clienteResponse = mapper.map(clienteByEmailAndPassword.getBody(), ClienteResponse.class);
		
		return clienteResponse;
		//Jejjejejeherokuuu
	}
	//////////////////////////////////////////////
	// OBTENER TODOS LOS CLIENTES				//
	//////////////////////////////////////////////
	
	@RequestMapping(value="/allClientes", method=RequestMethod.GET)
	public List<Cliente> getAllClientes() {
		return clienteService.getAllClientes();
	}
	
	//////////////////////////////////////////////
	// AÑADIR CLIENTE							//
	//////////////////////////////////////////////

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ClienteResponse saveCliente(@RequestBody ClienteRequest clienteRequest) {
		
		// Mapeo request dto
		Cliente cliente = mapper.map(clienteRequest, Cliente.class);
		
		// Invoca lógica de negocio
		Cliente newCliente = clienteService.save(cliente);
		
		// Mapeo entity
		ClienteResponse clienteResponse = mapper.map(newCliente, ClienteResponse.class);
		
		return clienteResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
	
	//////////////////////////////////////////////
	// ACTUALIZAR CLIENTE POR EL CORREO 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/update/{correo}", method=RequestMethod.PUT)
	public ClienteResponse update(@PathVariable(value = "correo") String correoCliente,
			@Valid @RequestBody ClienteRequest datosCliente) {
		
		// Mapeo request dto
		Cliente cliente = mapper.map(datosCliente, Cliente.class);
		
		// Invoca lógica de negocio
		ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
		
		Cliente updateCliente = clienteByEmail.getBody();
		
		updateCliente.setNombre(cliente.getNombre());
		updateCliente.setApellidos(cliente.getApellidos());
		updateCliente.setContrasenya(cliente.getContrasenya());
		updateCliente.setCorreo(cliente.getCorreo());
		updateCliente.setUsername(cliente.getUsername());
		updateCliente.setPais(cliente.getPais());
		
		updateCliente = clienteService.save(updateCliente);
		
		
		// Mapeo entity
		ClienteResponse clienteResponse = mapper.map(updateCliente, ClienteResponse.class);
		
		return clienteResponse;
		
		// SE PODRÍA HACER DE FORMA MÁS BREVE PERO ASÍ SE RESALTA CADA PASO DE FORMA INDEPENDIENTE
	}
	
	//////////////////////////////////////////////
	// ELIMINAR CLIENTE					 		//
	//////////////////////////////////////////////
	@RequestMapping(value="/delete/{correo}", method=RequestMethod.DELETE)
	public Map<String, Boolean> delete(@PathVariable(value = "correo") String correoCliente) {
		
		// Invoca lógica de negocio
		ResponseEntity<Cliente> clienteByEmail = clienteService.getClienteByEmail(correoCliente);
		
		Cliente deleteCliente = clienteByEmail.getBody();
		
		clienteService.delete(deleteCliente);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("ELIMINADO", Boolean.TRUE);
		
		
		// Mapeo entity
		// ClienteResponse clienteResponse = mapper.map(deleteCliente, ClienteResponse.class);
		
		return response;
		
	}
	
}
