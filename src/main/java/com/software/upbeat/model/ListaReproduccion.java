package com.software.upbeat.model;

import java.util.Queue;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class ListaReproduccion {
	
	
//////// CLIENTE /////////////////////
@OneToOne(fetch=FetchType.LAZY)
@JoinColumn(name="cliente")
private Cliente cliente;

/*
 * https://stackoverflow.com/questions/20119142/jackson-multiple-back-reference-properties-with-name-defaultreference
 */
@JsonBackReference(value = "listaRep-cliente")
public Cliente getCliente() {
	return cliente;
}

public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}

public boolean isCliente(Cliente cliente) {
	return this.cliente==cliente;
}
//////// FIN CLIENTE /////////////////////

////////CANCIONES /////////////////////
@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
private Queue<Cancion> canciones; //= new HashSet<Cancion>();

int segundoReproduccion;
/*
* https://stackoverflow.com/questions/20119142/jackson-multiple-back-reference-properties-with-name-defaultreference
*/
@JsonBackReference(value = "cancionesCola")
public Queue<Cancion> getCanciones() {
return canciones;
}

public void setCanciones(Queue<Cancion> canciones) {
this.canciones = canciones;
}


public void addCancion(Cancion cancion) {
canciones.add(cancion);
}

public Cancion devolverCancion() {
 return canciones.poll();
}

public int getSegundoReproduccion() {
	return segundoReproduccion;
}

public void setSegundoReproduccion(int segundoReproduccion) {
	this.segundoReproduccion = segundoReproduccion;
}

public ListaReproduccion() {
	super();
	this.segundoReproduccion = 0;
}


//////////CANCIONES /////////////////////

}
