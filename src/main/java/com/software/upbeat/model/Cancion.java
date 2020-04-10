package com.software.upbeat.model;

import java.io.ByteArrayInputStream;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.software.upbeat.UpbeatApplication.SopCancionesContentStore;
import com.software.upbeat.UpbeatApplication.SopCancionesRepository;


@Component
public class Cancion {
	@Autowired private SopCancionesRepository repo;
  	@Autowired private SopCancionesContentStore contentStore;

	public void doSomething() {

		SopCanciones doc = new SopCanciones();
		doc.ponerTitulo("Bella ciao");
		doc.ponerArtista("Talco");
		contentStore.setContent(doc, new ByteArrayInputStream("C:\\Users\\victo\\Music\\Talco\\10 Years\\16 Bella ciao.mp3".getBytes())); 
	}

}
