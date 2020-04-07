package com.proyecto.upbeat.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.proyecto.upbeat.dao.ArtistaRepository;
import com.proyecto.upbeat.dto.Artista;

public class ArtistaService {
	
	@Autowired
	ArtistaRepository dao;
	
	public Artista save (Artista artista) {
		return dao.saveAndFlush(artista);
	}

}
