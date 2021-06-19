package Gestoria;

import Entidades.*;

import java.util.ArrayList;

//EL CONTEXTO
public class Instanciador{

	private StrategyInstanciador instanciador;
	
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private ArrayList<Personaje> personajes = new ArrayList<Personaje>();
	private ArrayList<Objeto> objetos =  new ArrayList<Objeto>();
	
	public void setPersonajes(ArrayList<Personaje> personajes) {
		if(personajes != null) {
			this.personajes = personajes;
		}
	}
	
	public void setObjetos(ArrayList<Objeto> objetos) {
		if(objetos != null) {
			this.objetos = objetos;
		}
	}
	
	public void setRooms(ArrayList<Room> room) {
		if(room != null) {
			this.rooms = room;
		}
	}

	public void setStrategy(StrategyInstanciador instanciador) {
		this.instanciador = instanciador;
	}
	
	
	//Getters del instanciador
	public ArrayList<Room> Rooms() {
		return rooms != null ? rooms : null;
	}

	public ArrayList<Personaje> Personajes(){
		return personajes != null ? personajes : null;
	}
	
	public ArrayList<Objeto> Objetos(){
		return objetos != null ? objetos : null;
	}
	
	//Metodos Instanciador.
	
	public ArrayList<Room> getRooms() {
		return instanciador.getRooms();
	}

	public ArrayList<Personaje> getPersonajes(){
		return instanciador.getPersonajes();
	}
	
	public ArrayList<Objeto> getObjetos(){
		return instanciador.getObjetos();
	}
	
	public void imprimir() {
		
		System.out.println("\n\nROOMS ");
		for(Room x: rooms) {
			
			System.out.println("NOMBRE:"+x.getName());
		}
		
		
		System.out.println("\n\nPERSONAJES ");
		for(Personaje x: personajes) {
			System.out.println("NOMBRE:"+x.getName());
			System.out.println("HAB:"+x.getRoom().getName());
			System.out.print("OBJ:");
			try{System.out.println(x.getObj().getName());}catch(Exception e) {System.out.println("no tiene.");}
		}
		
		
		
		System.out.println("\n\nOBJETOS ");
		for(Objeto x : objetos) {
			System.out.println("NOMBRE:"+x.getName());
			try {System.out.println("HAB:"+x.getRoom().getName());}catch(Exception e) {System.out.println("PERS:"+x.getPersonaje().getName());}
		}
	}
	
	//Metodos Jorge.
	 public void toArrayListObjeto(Objeto[] objetoArray)
	    {
	        for(int i = 0; objetoArray[i] != null; i++)
	        {
	            objetos.add(objetoArray[i]);
	        }
	    }

	 public void toArrayListRoom(Room[] roomArray)
	    {
	        for(int i = 0; roomArray[i] != null; i++)
	        {
	            rooms.add(roomArray[i]);
	        }
	    }

	 public void toArrayListPersonaje(Personaje[] personajeArray)
	    {
	        for(int i = 0; personajeArray[i] != null; i++)
	        {
	            personajes.add(personajeArray[i]);
	        }
	    }

	
}