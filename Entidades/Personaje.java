package Entidades;

public abstract class Personaje {

	protected String Name = null;	
	protected Objeto Obj = null, final_Obj = null;
	protected Room Room = null, final_Room = null;

	protected boolean estadoTurno = false;
	protected boolean IA = true;
	
	//Constructor
	public Personaje(String name, Room room) {
		setName(name);
		setRoom(room);
	}
	
	//Default getters
	public String getName() {
		return Name != null ? Name : null;
	}
	
	public Room getRoom() {
		return Room != null ? Room : null;
	}
	
	public Objeto getObj() {
		return Obj != null ? Obj : null;
	}
	
	public Room getFinalRoom() {
		return final_Room != null ? final_Room : null;
	}
	
	public Objeto getFinalObj() {
		return final_Obj != null ? final_Obj : null;
	}
	
	public boolean getTurno() {
		return estadoTurno;
	}
	
	public boolean finalRoom() {
		return Room.getName() == final_Room.getName() ? true : false;
	}
	
	public boolean finalObj() {
		if(getFinalObj() != null && getObj() != null) {
			return getObj().getName() == getFinalObj().getName() ? true : false;
		}else if (getFinalObj() != null && getObj() == null){
			return false;
		}else if (getFinalObj() == null){
			return true;
		}
		return false;
	}
	
	public boolean checkPlayer() {
		return finalObj() && finalRoom() ? true : false;
	}
	
	public abstract boolean getIA();
	
	//Default setters
	
	public void setName(String name) {
		this.Name = name;
	}
	
	public void setRoom(Room room) {
		if(Room != null) {
			Room.removePersonaje(this);
			this.Room = room;
			Room.addPersonaje(this);	
		}else {
			this.Room = room;
			Room.addPersonaje(this);
		}
	}
	
	public void setObj(Objeto obj) {
		if(hasObj()) {
			System.out.println("Has soltado " + Obj.getName() + ".");
			Obj.setRoom(Room);
			setObj(obj);
		}else {
			Obj = obj;	
		}
	}
	
	public void removeObj() {
		if(hasObj()) {
			this.Obj = null;
		}else {
			System.out.println("No tienes un objeto...");
		}
	}
	
	public void setFinalRoom(Room room) {
		this.final_Room = room;
	}
	
	public void setFinalObj(Objeto obj) {
		this.final_Obj = obj;
	}
	
	public boolean setTurno(boolean state) {
		this.estadoTurno = state;
		return estadoTurno;
	}
	
	//PersonajeMethods()
	
	public abstract int chooseIA(int options);
	
	public boolean hasObj() {
		return Obj != null ? true : false;
	}
	
	public void dropObj() {
		if(hasObj()) {
			Obj.setRoom(Room);	
		}
	}

	public void giveObj(Personaje personaje) {
		if(hasObj()) {
			if(personaje.hasObj()) {
				personaje.getObj().setRoom(personaje.getRoom());
				this.Obj.setPersonaje(personaje);
				this.removeObj();
			}else {
				this.Obj.setPersonaje(personaje);
				this.removeObj();
			}
		}
	}
	
	public void moveRoom(Room room) {
		Room.removePersonaje(this);
		setRoom(room);
	}
	
}
