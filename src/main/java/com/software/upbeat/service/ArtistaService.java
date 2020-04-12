package com.software.upbeat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.software.upbeat.dao.ArtistaRepository;
import com.software.upbeat.model.Artista;

@Component
public class ArtistaService extends ClienteService{
	
	@Autowired
	ArtistaRepository dao;
	
	public Artista save(Artista artista){
		return dao.save(artista);
	}
	
	public List<Artista> getAllArtistas() {
		return this.dao.findAll();
	}
	
	public ResponseEntity<Artista> getArtistaByEmail(String correo){
		Artista artista = dao.findByEmail(correo);
		return ResponseEntity.ok().body(artista);
	}
	
	public ResponseEntity<Artista> getArtistaByEmailAndPassword(String password, String correo){
		Artista artista = dao.findByEmailAndPassword(password, correo);
		return ResponseEntity.ok().body(artista);
	}

	public void delete(Artista artista) {
		// TODO Auto-generated method stub
		dao.delete(artista);
	}
	
}
