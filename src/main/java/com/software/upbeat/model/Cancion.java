package com.software.upbeat.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;

public class Cancion {
	public Cancion(String string) {
		this.nombre=string;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String nombre;

	@ContentId
	private String contentId;

	@ContentLength
	private Long contentLength;
}