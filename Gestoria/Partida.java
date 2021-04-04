package Gestoria;

import Entidades.*;

public class Partida extends Admin{

	static int Personajes_completados = 0;
	static int Ronda = 1;
	
	private boolean done = false;
	
	private Instanciador Launch = new Instanciador();
	
	public Partida(String path_inicio, String path_objetivos) {
		LaunchGame(path_inicio, path_objetivos);
//		ComprobarPersonajes();
		Start();
	}
	
	public void Start() {
		do {

			for(Personaje x : Launch.getPersonajes()) {

				System.out.println("----  RONDA "+Ronda+" ----");

				System.out.println("\n\n");
				if(!x.getTurno()) {//No ha completado su turno.
					System.out.println("TURNO DE " + x.getName().toUpperCase());
					Round(x);
					PersonajeRonda(x);
					
				}
			
				if(x.checkPlayer()) {
					Personajes_completados++;
				}

			}
			
			newRound(Launch.getPersonajes());
			Ronda++;
			
			if(Personajes_completados == Launch.getPersonajes().size()) {
				done = true;
			}
			
		}while(!done);
		
		System.out.println("LA PARTIDA HA TERMINADO EN " + Ronda + " RONDAS");
	}

	public void LaunchGame(String path_inicio, String path_final) {
	
		//Rooms, Personajes, Objetos.
		Launch.setStrategy(new InstanciarRooms(path_inicio));
		Launch.setRooms(Launch.getRooms());
	
		Launch.setStrategy(new InstanciarPers(path_inicio, Launch.Rooms()));
		Launch.setPersonajes(Launch.getPersonajes());
		
		Launch.setStrategy(new InstanciarObj(path_inicio, Launch.Rooms(), Launch.Personajes()));
		Launch.setObjetos(Launch.getObjetos());
		
		//Objetivos Finales : Rooms y Objetos.
		Launch.setStrategy(new InstanciarFinalRoom(path_final, Launch.Personajes(), Launch.Rooms()));
		
		Launch.setStrategy(new InstanciarFinalObj(path_final, Launch.Personajes(), Launch.Objetos()));
	
		Launch.imprimir();
	}
	
	public void ComprobarPersonajes() {
		int i = 1;
		for(Personaje x : Launch.Personajes()) {
			System.out.println("       PERSONAJE " + i);
			System.out.println("IA:"+x.getIA());
			System.out.println("NOMBRE: " + x.getName());
			System.out.print("OBJETO: ");
			try{System.out.println(x.getObj().getName());}catch(Exception e) {System.out.println("no tiene objeto.");}
			System.out.println("HAB: " + x.getRoom().getName());
			
			
			//TOCA ARREGLAR.
			System.out.println("FINAL ROOM: " + x.getFinalRoom().getName());
			System.out.print("FINAL OBJ: ");
			try{System.out.println(x.getFinalObj().getName());}catch(Exception e) {System.out.println("no tiene objeto final.");}
			i++;
		}
	}
	
	public void PersonajeRonda(Personaje x) {
		System.out.println("NAME:" + x.getName());
		System.out.println("ROOM: "+ x.getRoom().getName());
		System.out.print("OBJ:"); try {System.out.println(x.getObj().getName());}catch(Exception e) {System.out.println("no posee.");}
	}
	
}
