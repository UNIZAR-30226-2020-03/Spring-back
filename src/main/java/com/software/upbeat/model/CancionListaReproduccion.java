package com.software.upbeat.model;

public class CancionListaReproduccion {
	
	private Cancion cancion;
	private int segundos;
	
	public Cancion getCancion() {
		return cancion;
	}
	public void setCancion(Cancion cancion) {
		this.cancion = cancion;
	}
	public int getSegundos() {
		return segundos;
	}
	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cancion == null) ? 0 : cancion.hashCode());
		result = prime * result + segundos;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CancionListaReproduccion other = (CancionListaReproduccion) obj;
		if (cancion == null) {
			if (other.cancion != null)
				return false;
		} else if (!cancion.equals(other.cancion))
			return false;
		if (segundos != other.segundos)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "CancionListaReproduccion [cancion=" + cancion + ", segundos=" + segundos + "]";
	}
	
	public CancionListaReproduccion(Cancion cancion, int segundos) {
		super();
		this.cancion = cancion;
		this.segundos = segundos;
	}
	
	

}
