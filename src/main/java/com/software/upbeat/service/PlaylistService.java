package com.software.upbeat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.software.upbeat.dao.PlaylistRepository;
import com.software.upbeat.model.Playlist;

@Component
public class PlaylistService {
	
	@Autowired
	PlaylistRepository dao;
	
	public Playlist save(Playlist playlist){
		return dao.save(playlist);
	}
	
	public void delete(Playlist playlist) {
		dao.delete(playlist);
	}
	
	public List<Playlist> getAllPlaylists() {
		return this.dao.findAll();
	}
	
	public List<Playlist> getCreatorPlaylists() {
		return this.dao.findCreatorPlaylists();
	}

	public Optional<Playlist> getPlaylistById(Long playlistId) {
		return dao.findById(playlistId);
	}
	
	
	public List<Playlist> getPlaylistByName(String nombre){
		return dao.findByName(nombre);
	}

}
