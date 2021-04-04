package Entidades;

public class Player extends Personaje {

	public Player(String name, Room room) {
		super(name, room);
		IA = false;
	}

	public int chooseIA(int options) {
		return 0;
	}

	public boolean getIA() {
		return false;
	}

}
