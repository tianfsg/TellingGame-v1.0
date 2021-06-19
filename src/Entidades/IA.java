package Entidades;


public class IA extends Personaje {

	public IA(String name, Room room) {
		super(name, room);
		IA = true;
	}

	public int chooseIA(int options){
		return (int) (Math.random() * options) + 1;
	}

	@Override
	public boolean getIA() {
		return true;
	}
}