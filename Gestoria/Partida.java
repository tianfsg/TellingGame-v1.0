package Gestoria;

import Entidades.*;

public class Partida extends Admin{

	static int personajes_completados = 0;
	static int ronda = 1;
	
	private boolean done = false;
	private Instanciador launch = new Instanciador();
	
	public Partida(String path_inicio, String path_objetivos) {
		LaunchGame(path_inicio, path_objetivos);
//		ComprobarPersonajes();
		Start();
	}
	
	public void Start() {
		try{
			do {
				
				System.out.println("----  RONDA "+ronda+" ----");
				log.add("ronda " + ronda);
				

				for(Personaje x : launch.getPersonajes()) {
	
					if(!x.getTurno()) {//No ha completado su turno.
						
//						System.out.println("TURNO DE " + x.getName().toUpperCase());
						if(!x.getIA()) {
							mostrarObjetivos(x);
						}
						Round(x);
						//PersonajeRonda(x);
					}
				
					if(x.checkPlayer()) {
						personajes_completados++;
					}
				}
				
				if(personajes_completados != launch.getPersonajes().size()) {
					personajes_completados = 0;
				}
				
				newRound(launch.getPersonajes());
	
				ronda++;
				
				if(personajes_completados == launch.getPersonajes().size()) {
					done = true;
				}
				
			}while(!done);
		}catch(Exception e) {System.out.println("Algo ha fallado.");
		}finally {
			ComprobarPersonajes();
			System.out.println("LA PARTIDA HA TERMINADO EN " + ronda + " RONDAS");
		}
		
	}

	public void LaunchGame(String path_inicio, String path_final) {
	
		//Rooms, Personajes, Objetos.
		launch.setStrategy(new InstanciarRooms(path_inicio));
		launch.setRooms(launch.getRooms());
	
		launch.setStrategy(new InstanciarPers(path_inicio, launch.Rooms()));
		launch.setPersonajes(launch.getPersonajes());
		
		launch.setStrategy(new InstanciarObj(path_inicio, launch.Rooms(), launch.Personajes()));
		launch.setObjetos(launch.getObjetos());
		
		//Objetivos Finales : Rooms y Objetos.
		launch.setStrategy(new InstanciarFinalRoom(path_final, launch.Personajes(), launch.Rooms()));
		launch.setStrategy(new InstanciarFinalObj(path_final, launch.Personajes(), launch.Objetos()));
	
//		Launch.imprimir();
	}
	
	public void ComprobarPersonajes() {
		int i = 1;
		for(Personaje x : launch.Personajes()) {
			//Info
			System.out.println("       PERSONAJE " + i);
			System.out.println("IA:"+x.getIA());
			System.out.println("NOMBRE: " + x.getName());
			System.out.print("OBJETO: ");
			try{System.out.println(x.getObj().getName());}catch(Exception e) {System.out.println("no tiene objeto.");}
			System.out.println("HAB: " + x.getRoom().getName());
			
			//Objetivos
			System.out.print("FINAL OBJ: ");
			try{System.out.println(x.getFinalObj().getName());}catch(Exception e) {System.out.println("no tiene objeto final.");}
			System.out.println("FINAL ROOM: " + x.getFinalRoom().getName());
			
			i++;
		}
	}
	
	public void PersonajeRonda(Personaje x) {
		System.out.println("NAME:" + x.getName());
		System.out.println("ROOM: "+ x.getRoom().getName());
		System.out.print("OBJ:"); try {System.out.println(x.getObj().getName());}catch(Exception e) {System.out.println("no posee.");}
	}
	
	public void mostrarObjetivos(Personaje x) {
		System.out.println("");
		System.out.println("Objeto final>> "+x.getFinalObj().getName());
		System.out.println("Habitacion final>> "+ x.getFinalRoom().getName());
		System.out.println("");
	}
	
}
