package Entidades;

public class Objeto {
	
	private String oName;
	private Personaje jugador;
	private Room room;

	public Objeto(String name, Personaje jugador) {
		setName(name);
		setPersonaje(jugador);
		this.room = null;
	}
	
	public Objeto(String name, Room room) {
		setName(name);
		setRoom(room);
		this.jugador = null;
	}
	
	//Default getters
	public String getName() {
		return oName;
	}

	public Room getRoom() {
		if(room != null) {
			return room;
		}else {return null;}
	}
	
	public Personaje getPersonaje() {
		if(jugador != null) {
			return jugador;
		}else {return null;}
	}
	
	//Default setters
	public void setName(String name) {
		this.oName = name;
	}
	
	public void setPersonaje(Personaje personaje) {
		
		if(hasRoom()) {
			this.room.removeObj(this);
			removeRoom();
		}
		
		if(hasPers()) {
			
			jugador.removeObj();
			removePersonaje();
			setPersonaje(personaje);
			
		}else {

			this.jugador = personaje;
			this.jugador.setObj(this);
		}
		
		
	}
	
	public void setRoom(Room room) {
		
		if(hasPers()) {
			this.jugador.removeObj();
			removePersonaje();
		}
		this.room = room;
		this.room.addObj(this);
		
//		try {System.out.println("PERS:" + Personaje.getName());}catch(Exception e) {System.out.println("ROOM:" + Room.getName());}
		
	}

	public boolean hasPers() {
		return jugador != null ? true : false;
	}
	
	public boolean hasRoom() {
		return room != null ? true : false;
	}
	
	public void removeRoom() {
		room = null;
	}
	
	public void removePersonaje() {
		jugador = null;
	}
		
}

