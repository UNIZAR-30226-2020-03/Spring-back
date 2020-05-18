package com.software.upbeat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.software.upbeat.dao.AlbumRepository;
import com.software.upbeat.model.Album;

@Component
public class AlbumService {
	
	@Autowired
	AlbumRepository dao;
	
	public Album save(Album album){
		return dao.save(album);
	}
	
	public void delete(Album album) {
		dao.delete(album);
	}
	
	public List<Album> getAllAlbums() {
		return this.dao.findAll();
	}

	public Optional<Album> getAlbumById(Long albumId) {
		return dao.findById(albumId);
	}
	
	public List<Album> getAlbumByName(String nombre){
		return dao.findByName(nombre);
	}

}