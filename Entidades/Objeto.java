package Entidades;

public class Objeto {
	
	private String oName;
	private Personaje Personaje;
	private Room Room;

	public Objeto(String name, Personaje personaje) {
		setName(name);
		setPersonaje(personaje);
		this.Room = null;
	}
	
	public Objeto(String name, Room room) {
		setName(name);
		setRoom(room);
		this.Personaje = null;
	}
	
	//Default getters
	public String getName() {
		return oName;
	}

	public Room getRoom() {
		if(Room != null) {
			return Room;
		}else {return null;}
	}
	
	public Personaje getPersonaje() {
		if(Personaje != null) {
			return Personaje;
		}else {return null;}
	}
	
	//Default setters
	public void setName(String name) {
		this.oName = name;
	}
	
	public void setPersonaje(Personaje personaje) {
		if(Room != null) {
			this.Room.removeObj(this);
			this.Room = null;
		}
		this.Personaje = personaje;
		this.Personaje.setObj(this);
	}
	
	public void setRoom(Room room) {
		if(Personaje != null) {
			this.Personaje.removeObj();
			this.Personaje = null;	
		}
		this.Room = room;
		this.Room.addObj(this);
	}
	
}
