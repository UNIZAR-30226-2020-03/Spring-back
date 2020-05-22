package com.software.upbeat.model;

import java.util.Queue;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="listaReproduccion")
public class ListaReproduccion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	private int num_canciones;
	
	private int indice;
		
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNum_canciones() {
		return num_canciones;
	}

	public void setNum_canciones(int num_canciones) {
		this.num_canciones = num_canciones;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	//////// CLIENTE /////////////////////
	/*
	 * @OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente")
	private Cliente cliente;*/
	
	/*
	 * https://stackoverflow.com/questions/20119142/jackson-multiple-back-reference-properties-with-name-defaultreference
	 */
	/*@JsonBackReference(value = "listaRep-cliente")
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public boolean isCliente(Cliente cliente) {
		return this.cliente==cliente;
	}*/
	//////// FIN CLIENTE /////////////////////
	/*
	////////CANCIONES /////////////////////
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Cancion> canciones; //= new HashSet<Cancion>();
	
	int segundoReproduccion;
	/*
	* https://stackoverflow.com/questions/20119142/jackson-multiple-back-reference-properties-with-name-defaultreference
	*/
	/*
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
	
	*/
	//////////CANCIONES /////////////////////

}
