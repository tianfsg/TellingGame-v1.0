package Entidades;

public class IA extends Personaje {

	public IA(String name, Room room) {
		super(name, room);
		IA = true;
	}

	public int chooseIA(int options){
		int option = (int) (Math.random() * options) + 1;
		return option;
	}

	@Override
	public boolean getIA() {
		return true;
	}
	
}
