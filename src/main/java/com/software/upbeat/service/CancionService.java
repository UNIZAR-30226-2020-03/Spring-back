package com.software.upbeat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.software.upbeat.dao.CancionRepository;
import com.software.upbeat.model.Cancion;



@Component
public class CancionService {

	@Autowired
	CancionRepository dao;
	
	public Cancion save(Cancion cancion){
		return dao.save(cancion);
	}
	
	public List<Cancion> getAllSongs() {
		return this.dao.findAll();
	}
	
	public ResponseEntity<Cancion> getSongByName(String nombre){
		Cancion cancion = dao.findSongByName(nombre);
		return ResponseEntity.ok().body(cancion);
	}
	
	public byte[] getSongStreamByName(String nombre){
		Cancion cancion = dao.findSongByName(nombre);
		return cancion.getSong();
	}
	
	public ResponseEntity<Cancion> getSongByNameAndArtist(String nombre, String autor){
		Cancion cancion = dao.findSongByNameAndArtist(nombre, autor);
		return ResponseEntity.ok().body(cancion);
	}
	
	public byte[] getSongStreamByNameAndArtist(String nombre, String autor){
		Cancion cancion = dao.findSongByNameAndArtist(nombre, autor);
		return cancion.getSong();
	}

	public void delete(Cancion cancion) {
		// TODO Auto-generated method stub
		dao.delete(cancion);
	}
}
