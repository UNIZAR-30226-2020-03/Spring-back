package com.proyecto.upbeat.api;

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
	
	@RequestMapping(value="/saveCliente", method=RequestMethod.POST)
	public Cliente updateOrSave(@RequestBody Cliente cliente) {
		return clienteService.save(cliente);
	}
}
