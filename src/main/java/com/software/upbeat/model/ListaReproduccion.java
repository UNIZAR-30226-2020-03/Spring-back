package com.software.upbeat.model;

import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="listaReproduccion")
public class ListaReproduccion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "num_canciones")
	private int num_canciones;
	
	private int segundoReproduccion;
	
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
	
	public int getSegundoReproduccion() {
		return segundoReproduccion;
	}
	
	public void setSegundoReproduccion(int segundoReproduccion) {
		this.segundoReproduccion = segundoReproduccion;
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
	
	////////CANCIONES /////////////////////
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Cancion> canciones; //= new HashSet<Cancion>();
	
	Iterator<Cancion> iterador = null;
	/*
	* https://stackoverflow.com/questions/20119142/jackson-multiple-back-reference-properties-with-name-defaultreference
	*/
	@JsonBackReference(value = "cancionesCola")
	public List<Cancion> getCanciones() {
		return canciones;
	}
	
	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
	}
	
	public void actualizar_num_canciones() {
		this.num_canciones = canciones.size();
	}
	
	public void actualizar_segundoReproduccion(int segundo) {
		this.segundoReproduccion = segundo;
	}
	
	public void addCancion(Cancion cancion) {
		canciones.remove(cancion);
		canciones.add(cancion);
		actualizar_num_canciones();
	}
	
	public void removeCancion(Cancion cancion) {
		canciones.remove(cancion);
		actualizar_num_canciones();
	}
	
	public void clear() {
		canciones.clear();
		
	}
	
	/*
	 * REPRODUCIR UNA CANCIÓN
	 * 
	 * SI ESTÁ VACÍA LA LISTA -> cancion = null, segs = -1
	 * SI NO -> cancion = siguiente canción del iterador, segundos = segundos actuales
	 */
	public CancionListaReproduccion play(){
		Cancion cancion;
		int segs;
		if(iterador.hasNext()) {
			cancion = iterador.next();
			segs = this.segundoReproduccion;
		}
		else {
			cancion = null;
			segs = -1;
		}
		return new CancionListaReproduccion(cancion, segs);
	}
	
	/*
	 * PAUSAR UNA CANCIÓN
	 * 
	 * Se actualizan los segundos 
	 */
	public void parar(int segundoParar) {
		this.segundoReproduccion = segundoParar;
	}
	
	/*
	 * 
	 */
	public void reproducirCancion() {}
	
	/*
	 * 
	 */
	public void reproducirLista() {}
	
	/*
	 * 
	 */
	public void addCancion() {}
	
	/*
	 * 
	 */
	public void addLista() {}
	
	public ListaReproduccion() {
		super();
		this.segundoReproduccion = 0;
	}
	
	//////////CANCIONES /////////////////////

}
