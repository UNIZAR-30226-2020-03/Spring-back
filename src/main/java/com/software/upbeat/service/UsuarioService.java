package com.software.upbeat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.software.upbeat.dao.UsuarioRepository;
import com.software.upbeat.model.Usuario;

@Component
public class UsuarioService extends ClienteService{

	@Autowired
	UsuarioRepository dao;
	
	public Usuario save(Usuario usuario){
		return dao.save(usuario);
	}
	
	public List<Usuario> getAllUsuarios() {
		return this.dao.findAll();
	}
	
	public ResponseEntity<Usuario> getUsuarioByEmail(String correo){
		Usuario usuario = dao.findByEmail(correo);
		return ResponseEntity.ok().body(usuario);
	}
	
	public ResponseEntity<Usuario> getUsuarioByEmailAndPassword(String password, String correo){
		Usuario usuario = dao.findByEmailAndPassword(password, correo);
		return ResponseEntity.ok().body(usuario);
	}

	public void delete(Usuario usuario) {
		// TODO Auto-generated method stub
		dao.delete(usuario);
	}

	public ResponseEntity<Usuario> getUsuarioByUsername(String username) {
		Usuario usuario = dao.findByUsername(username);
		return ResponseEntity.ok().body(usuario);
	}
}
