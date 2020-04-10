package com.software.upbeat.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;

@Entity
public class SopCanciones {
	private @Id @GeneratedValue Long id;
	private String title;
	private String author;
	public void ponerTitulo(String titulo) {
		this.title=titulo;
	}
	public void ponerArtista(String artista) {
		this.author=artista;
	}
	// Spring Content managed attribute
	private @ContentId UUID contentId;
	private @ContentLength Long contentLen;
}
