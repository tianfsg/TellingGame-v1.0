package Entidades;

import java.util.ArrayList;

public abstract class Personaje{

	
	protected String name = null;	
	protected Objeto obj = null, final_Obj = null;
	protected Room room = null, final_Room = null;

	protected boolean estadoTurno = false;
	protected boolean IA = true;
	
	protected ArrayList<String> believes;
	
	//Constructor
	public Personaje(String name, Room room) {
		setName(name);
		setRoom(room);
		believes = new ArrayList<String>();
	}
	
	//Default getters
	public String getName() {
		return name != null ? name : null;
	}
	
	public Room getRoom() {
		return room != null ? room : null;
	}
	
	public Objeto getObj() {
		return obj != null ? obj : null;
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
	
	public ArrayList<String> getBelieves(){
		return believes;
	}
	
	public void lastBelieves() {
		int num_believes = believes.size();
		if(num_believes > 0) {
			System.out.println(believes.get(num_believes));
		}
	}
	
	public boolean finalRoom() {
		return room.getName() == final_Room.getName() ? true : false;
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
		this.name = name;
	}
	
	public void setRoom(Room room) {
		if(this.room != null) {
			this.room.removePersonaje(this);
			this.room = room;
			this.room.addPersonaje(this);	
		}else {
			this.room = room;
			this.room.addPersonaje(this);
		}
	}
	
	public void setObj(Objeto obj) {
		if(!hasObj()) {
			this.obj = obj;
		}else {
			this.dropObj();
			obj.setPersonaje(this);
		}
	}
	
	public void removeObj() {
		this.obj = null;
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
	
	public void setPBelieve(Room room, Personaje x) {
		believes.add("Creo que " + x.getName() + " en " + room.getName());
	}
	
	public void setOBelive(Room room, Objeto o) {
		believes.add("Creo que " + o.getName() + " en " + room.getName());
	}
	
	//PersonajeMethods()
	
	public boolean hasObj() {
		return obj != null ? true : false;
	}
	
	public void dropObj() {
		if(hasObj()) {
			obj.setRoom(room);	
		}
	}

	public void giveObj(Personaje personaje) {
		if(hasObj()){
			getObj().setPersonaje(personaje);
		}
	}
	
	public void moveRoom(Room room) {
		room.removePersonaje(this);
		setRoom(room);
	}
	
	public abstract int chooseIA(int options);
	
}
