package com.proyecto.upbeat.model;

import java.sql.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cancion")
public class UpbeatSongModel {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cod_cancion;
	
	private java.sql.Date fecha_cancion;
	private String nombre;
	private boolean tipo;
	private int duracion;
	
	public UpbeatSongModel(Date fecha_cancion, String nombre, boolean tipo, int duracion) {
		super();
		this.fecha_cancion = fecha_cancion;
		this.nombre = nombre;
		this.tipo = tipo;
		this.duracion = duracion;
	}

	public java.sql.Date getFecha_cancion() {
		return fecha_cancion;
	}

	public void setFecha_cancion(java.sql.Date fecha_cancion) {
		this.fecha_cancion = fecha_cancion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isTipo() {
		return tipo;
	}

	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public Long getCod_cancion() {
		return cod_cancion;
	}

	public void setCod_cancion(Long cod_cancion) {
		this.cod_cancion = cod_cancion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cod_cancion == null) ? 0 : cod_cancion.hashCode());
		result = prime * result + duracion;
		result = prime * result + ((fecha_cancion == null) ? 0 : fecha_cancion.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + (tipo ? 1231 : 1237);
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
		UpbeatSongModel other = (UpbeatSongModel) obj;
		if (cod_cancion == null) {
			if (other.cod_cancion != null)
				return false;
		} else if (!cod_cancion.equals(other.cod_cancion))
			return false;
		if (duracion != other.duracion)
			return false;
		if (fecha_cancion == null) {
			if (other.fecha_cancion != null)
				return false;
		} else if (!fecha_cancion.equals(other.fecha_cancion))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UpbeatSongModel [cod_cancion=" + cod_cancion + ", fecha_cancion=" + fecha_cancion + ", nombre=" + nombre
				+ ", tipo=" + tipo + ", duracion=" + duracion + "]";
	}
	
	
	
	
}
