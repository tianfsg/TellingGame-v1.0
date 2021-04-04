package Gestoria;

import Entidades.*;
import java.util.ArrayList;

//EL CONTEXTO
public class Instanciador {

	private StrategyInstanciador Instanciador;
	
	
	private ArrayList<Room> Rooms = new ArrayList<Room>();
	private ArrayList<Personaje> Personajes = new ArrayList<Personaje>();
	private ArrayList<Objeto> Objetos =  new ArrayList<Objeto>();
	
	
	public void setPersonajes(ArrayList<Personaje> personajes) {
		if(personajes != null) {
			this.Personajes = personajes;
		}
	}
	
	public void setObjetos(ArrayList<Objeto> objetos) {
		if(objetos != null) {
			this.Objetos = objetos;
		}
	}
	
	public void setRooms(ArrayList<Room> room) {
		if(room != null) {
			this.Rooms = room;
		}
	}

	public void setStrategy(StrategyInstanciador Inst) {
		Instanciador = Inst;
	}
	
	
	public ArrayList<Room> Rooms() {
		return Rooms != null ? Rooms : null;
	}

	public ArrayList<Personaje> Personajes(){
		return Personajes != null ? Personajes : null;
	}
	
	public ArrayList<Objeto> Objetos(){
		return Objetos != null ? Objetos : null;
	}
	
	
	
	//Metodos Instanciador.
	
	public ArrayList<Room> getRooms() {
		return Instanciador.getRooms();
	}

	public ArrayList<Personaje> getPersonajes(){
		return Instanciador.getPersonajes();
	}
	
	public ArrayList<Objeto> getObjetos(){
		return Instanciador.getObjetos();
	}
	
	public void roomsimpi() {

	}
	
	public void imprimir() {
		
		System.out.println("\n\nROOMS ");
		for(Room x: Rooms) {
			
			System.out.println("NOMBRE:"+x.getName());
		}
		
		
		System.out.println("\n\nPERSONAJES ");
		for(Personaje x: Personajes) {
			System.out.println("NOMBRE:"+x.getName());
			System.out.println("HAB:"+x.getRoom().getName());
			System.out.print("OBJ:");
			try{System.out.println(x.getObj().getName());}catch(Exception e) {System.out.println("no tiene.");}
		}
		
		
		
		System.out.println("\n\nOBJETOS ");
		for(Objeto x : Objetos) {
			System.out.println("NOMBRE:"+x.getName());
			try {System.out.println("HAB:"+x.getRoom().getName());}catch(Exception e) {System.out.println("PERS:"+x.getPersonaje().getName());}
		}
	}
	
	//Metodos Jorge.
	 public void toArrayListObjeto(Objeto[] objetoArray)
	    {
	        for(int i = 0; objetoArray[i] != null; i++)
	        {
	            Objetos.add(objetoArray[i]);
	        }
	    }

	    public void toArrayListRoom(Room[] roomArray)
	    {
	        for(int i = 0; roomArray[i] != null; i++)
	        {
	            Rooms.add(roomArray[i]);
	        }
	    }

	    public void toArrayListPersonaje(Personaje[] personajeArray)
	    {
	        for(int i = 0; personajeArray[i] != null; i++)
	        {
	            Personajes.add(personajeArray[i]);
	        }
	    }
	
}