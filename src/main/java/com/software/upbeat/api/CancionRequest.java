package com.software.upbeat.api;



import javax.validation.constraints.NotNull;



public class CancionRequest {
private Long id;
	
	@NotNull(message="El nombre es requerido")
	private String nombre;
	private String autor;
	private String path;
	private byte[] song;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public byte[] getSong() {
		return song;
	}
	/*
	 * COMENTADA YA QUE SE TRATA EN FIREBASE -> SI SE NECESITARA: DESCOMENTAR
	 * 
	 * public void setSong(String media) throws IOException {
		ClassPathResource song = new ClassPathResource(path);
		byte[] arraySong = new byte[(int) song.contentLength()];
		song.getInputStream().read(arraySong);
		this.song = arraySong;
	}*/
	public void setSong(byte[] song){
		
		this.song = song;
	}
}
