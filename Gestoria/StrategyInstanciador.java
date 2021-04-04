package Gestoria;

import java.util.ArrayList;

import Entidades.*;

public abstract class StrategyInstanciador {

	//Atributos privados pertinentes del padre.
	//getter y setters con cada atributo.
	

	
	
	//modificar este metodo para que reciba y devuelva lo que consideres.
	public abstract void executeInst();
	public abstract void extractString(String path);
	public abstract void instEntidades();
	public abstract void asigEntidades();
	public abstract void comprobar();
	
	//getters de los arraylist.
	
	public abstract ArrayList<Room> getRooms();
	
	public abstract ArrayList<Personaje> getPersonajes();
	
	public abstract ArrayList<Objeto> getObjetos();

	
	


	
}
