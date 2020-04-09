package com.software.upbeat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.software.upbeat.dao.ArtistaRepository;
import com.software.upbeat.model.Artista;

@Component
public class ArtistaService {
	
	@Autowired
	ArtistaRepository dao;
	
	public Artista save(Artista artista){
		return dao.saveAndFlush(artista);
	}
	
}
