package Entidades;

import java.util.ArrayList;

public class Room {

	private String rName;
	private ArrayList <Personaje> listPersonajes = new ArrayList<Personaje>(); 
	private ArrayList <Room> listAnexos = new ArrayList<Room>();
	private ArrayList <Objeto> listObj= new ArrayList<Objeto>();	
    
    //Constructor  
    public Room(String name) {
    	rName = name;
    }
    
    //Default getters
    
    public String getName() {
    	return rName;
    }
    
    public ArrayList<Personaje> Personajes(){
    	return listPersonajes;
    }

    public ArrayList<Objeto> Objetos(){
    	return listObj;
    }
    
    public ArrayList<Room> Anexos(){
    	return listAnexos;
    }
    
    //Adding besides
    
    public void addBesides(Room beside) {
    	listAnexos.add(beside);
    }
    
    //Adding or removing Caracter
    
    public void addPersonaje(Personaje personaje) {
    	listPersonajes.add(personaje);
    }
    
    public void removePersonaje(Personaje personaje) {
    	if(listPersonajes.size() > 0) {
    		listPersonajes.remove(listPersonajes.indexOf(personaje));
    	}
    }
	
    //Adding or removing Obj
 
    public void addObj(Objeto obj) {
    	if(listObj.contains(obj) == false) {
    		listObj.add(obj);
    	}
    }
    
    public void removeObj(Objeto obj) {
    	if( listObj.contains(obj) ) {
    		listObj.remove( listObj.indexOf(obj) );
    	}
    }
    
   //Room PrintMethods
 
    public void listAnexos() {
    	for(int i = 0; i < listAnexos.size(); i++) {
    		if(listAnexos.get(i) != null) {
    			System.out.println((i+1) + ". " + listAnexos.get(i).getName() + "\n");		
    		}
    	}
    }
    
    public void listObj() {
    	for(int i = 0; i < listObj.size(); i++) {
    		if(listObj.get(i) != null) {
    			System.out.println((i+1) + ". " + listObj.get(i).getName() + "\n");		
    		}
    	}
    }
    
    public void listPersonajes() {
    	for(int i = 0; i < listPersonajes.size(); i++) {
    		if(listPersonajes.get(i) != null) {
    			System.out.println((i+1) + ". " + listPersonajes.get(i).getName() + "\n");
    		}
    	}
    }
    
    public int listarPreguntados(Personaje Jugador) {
        int indiceJugador = listPersonajes.indexOf(Jugador);
        
        if(indiceJugador == 0) {
            for(int i = 1; i < listPersonajes.size(); i++) {
                   System.out.println((i) + ". " + listPersonajes.get(i).getName() + " \n");
            }
            return 0;
        }else if(indiceJugador == 1) {
            for(int i = 0; i < listPersonajes.size(); i++) {
                if(Jugador.getName() != listPersonajes.get(i).getName()) {
                   if(i > indiceJugador) {
                        System.out.println((i) + ". " + listPersonajes.get(i).getName() + "\n ");    
                    }else {
                        System.out.println((i+1) + ". " + listPersonajes.get(i).getName() + "\n ");
                    }
                }
            }
            return 1;
        }else if(indiceJugador > 1) {
            for(int i = 0; i < listPersonajes.size(); i++) {
                if(Jugador.getName() != listPersonajes.get(i).getName()) {
                	if(i > indiceJugador) {
                        System.out.println((i) + ". " + listPersonajes.get(i).getName() + "\n ");    
                    }else {
                        System.out.println((i+1) + ". " + listPersonajes.get(i).getName() + "\n ");
                    }    
                }
            }
            return 2;
        }
        return indiceJugador;
    }
    
    
    
}
