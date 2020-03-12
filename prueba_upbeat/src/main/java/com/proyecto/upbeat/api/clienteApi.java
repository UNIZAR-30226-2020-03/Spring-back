package com.proyecto.upbeat.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.upbeat.model.clienteModel;

@RestController
public class clienteApi {
	
	@RequestMapping(value="/cliente", method=RequestMethod.GET)
	public clienteModel getById() {
		return new clienteModel(1L,"a","b","c","d");
	}
	
	
}
