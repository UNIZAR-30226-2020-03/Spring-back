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
	
	private static int ACTUAL = 0;
	private static int SIGUIENTE = 1;
	
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
	
	//Iterator<Cancion> iterador = null;
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
	
	
	//////////////////////////////////////////////////////////////
	// OPERACIONES COLA DE REPRODUCCIÓN							//
	//////////////////////////////////////////////////////////////
	
	public void actualizar_segundoReproduccion(int segundo) {
		this.segundoReproduccion = segundo;
	}
	
	public void actualizar_num_canciones() {
		this.num_canciones = canciones.size();
	}
	
	/*
	 * QUITAR UNA CANCIÓN DE LA LISTA
	 */
	public void removeCancion(Cancion cancion) {
		canciones.remove(cancion);
		actualizar_num_canciones();
	}
	
	/*
	 * VACIAR LA LISTA
	 */
	public void clear() {
		canciones.clear();
		actualizar_num_canciones();
		
	}
	
	/*
	 * REPRODUCIR UNA CANCIÓN
	 * 
	 * SI num_canciones <=0 -> cancion = null, segs = -1
	 * SI NO -> cancion = canción en posición 0, segundos = segundos actuales
	 */
	public CancionListaReproduccion play(){
		Cancion cancion;
		int segs;
		actualizar_num_canciones();
		if(num_canciones > ACTUAL) {
			cancion = canciones.get(ACTUAL);
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
	 * Se actualiza el segundo en el cual se ha parado la canción
	 */
	public void parar(int segundoParar) {
		this.segundoReproduccion = segundoParar;
	}
	
	/*
	 * PASAR UNA CANCIÓN
	 * 
	 * SI num_canciones <= 1 -> cancion = null, segs = -1
	 * SI NO -> cancion = canción en posición 1, segundos = segundos actuales, se quita de la lista la primera canción
	 * 
	 * DE ESTA FORMA, LA SIGUIENTE CANCIÓN SE CONVIERTE EN LA CABEZA DE LA COLA
	 */
	public CancionListaReproduccion next(){
		Cancion cancion;
		int segs;
		actualizar_num_canciones();
		if(num_canciones > SIGUIENTE) {
			cancion = canciones.get(SIGUIENTE);
			segs = this.segundoReproduccion;
			canciones.remove(ACTUAL);
		}
		else {
			cancion = null;
			segs = -1;
		}
		return new CancionListaReproduccion(cancion, segs);
	}
	
	/*
	 * AÑADE LA CANCIÓN QUE SE QUIERE REPRODUCIR AL PRINCIPIO DE LA COLA
	 */
	public CancionListaReproduccion reproducirCancion(Cancion cancion) {
		canciones.remove(cancion);
		canciones.add(ACTUAL, cancion);
		actualizar_num_canciones();
		int segs = 0;
		return new CancionListaReproduccion(cancion, segs);
	}
	
	/*
	 * AÑADE LA CANCIÓN QUE SE QUIERE REPRODUCIR AL PRINCIPIO DE LA COLA
	 * 
	 * AÑADE EL RESTO DE CANCIONES DE LA LISTA (ÁLBUM O PLAYLIST) A LA COLA,
	 * A CONTINUACIÓN DE ESTA PRIMERA CANCIÓN
	 */
	public CancionListaReproduccion reproducirLista(List<Cancion> cancionList) {
		canciones.removeAll(cancionList);
		canciones.addAll(ACTUAL, cancionList);
		actualizar_num_canciones();
		Cancion cancion = canciones.get(ACTUAL);
		int segs = 0;
		return new CancionListaReproduccion(cancion, segs);
	}
	
	/*
	 * AÑADE A LA COLA UNA CANCIÓN Y ACTUALIZA EL NÚMERO DE CANCIONES
	 */
	public void addCancion(Cancion cancion) {
		canciones.remove(cancion);
		canciones.add(cancion);
		actualizar_num_canciones();
	}
	
	/*
	 * AÑADE A LA COLA UNA LISTA DE CANCIONES Y ACTUALIZA EL NÚMERO DE CANCIONES
	 * 
	 * UTILIZADO PARA AÑADIR PLAYLISTS O ÁLBUMES A LA COLA
	 */
	public void addLista(List<Cancion> cancionList) {
		canciones.removeAll(cancionList);
		canciones.addAll(cancionList);
		actualizar_num_canciones();
	}
	//////////CANCIONES /////////////////////
	
	public ListaReproduccion() {
		super();
		this.segundoReproduccion = 0;
		this.num_canciones = 0;
	}
	
	

}
