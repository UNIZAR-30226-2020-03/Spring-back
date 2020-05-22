package com.software.upbeat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.software.upbeat.dao.ListaReproduccionRepository;
import com.software.upbeat.model.ListaReproduccion;

@Component
public class ListaReproduccionService {
	
	@Autowired
	ListaReproduccionRepository dao;
	
	public ListaReproduccion save(ListaReproduccion listaReproduccion){
		return dao.save(listaReproduccion);
	}
	
	public void delete(ListaReproduccion listaReproduccion) {
		dao.delete(listaReproduccion);
	}
}
