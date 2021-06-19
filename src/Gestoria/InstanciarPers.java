package Gestoria;

import Entidades.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class InstanciarPers implements StrategyInstanciador {

	private String[] lineas = new String[100];

	private String[] personajes1D = new String[100];              //aqui se almacenan los personajes sin modificar
	private String[][] personajes2D = new String[100][100];
	private String jugador_string = new String();

	private ArrayList<Room> rooms;
	private Personaje[] arrPersonajes = new Personaje[100];

	private ArrayList<Personaje> listPersonajes = new ArrayList<Personaje>();

	// constructor (NECESARIO PASAR ARRAY DE HABITACIONES; SI NO, FALLA!!!
	public InstanciarPers(String path, ArrayList<Room> allRooms){	
		setRoom(allRooms);
		extractString(path);
		executeInst();
	}

	public void setRoom(ArrayList<Room> rooms) {
		if(rooms != null) {
			this.rooms = rooms;		
		}
	}
	
	//InstanciarPersMethods()
	
	public void executeInst() {
		asigEntidades();
		instEntidades();
	}

	//untouched
	public void extractString(String path)
	{
		/*
		- metodo que abre el archivo y vuelca sus contenidos en el array de
	 	  strings llamado "lineas".

		- este metodo es necesario para la posterior instanciacion de los objetos,
	  	  por esta razon se llama en el constructor de la clase.
	 	*/
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		//APERTURA DE ARCHIVO

		try {
			//se abre el archivo con el path especificado
			archivo = new File (path);
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);

			//se leen las lineas del archivo y se asignan al array de lineas
			String linea_fich;
			int i = 0;
			while((linea_fich = br.readLine()) != null) {
				this.lineas[i] = linea_fich;
				i++;
			}

		} catch(Exception e) {

			System.err.println("[ERROR] > Apertura de fichero fallida.");
			return;

		} finally {
			try {
				if(null != archivo) {         //se intenta cerrar el archivo; si falla, se lanza una excepción
					fr.close();

				}} catch(Exception e2) {
				System.err.println("[ERROR] > Cierre de fichero fallido.");
				e2.printStackTrace();
				return;
			}
		}
	}

	public void asigEntidades(){

		// 1D -------------------------------------------------

		for(int i = 0; lineas[i] != null; i++)
		{
			if(lineas[i].equals("<Personajes>"))
			{
				i++;
				for(int j = 0; lineas[i].equals("<Objetos>") == false; j++)
				{
					personajes1D[j] = lineas[i];
					i++;
				}
			}
		}

		// 2D -------------------------------------------------

		String[] splits = new String[100]; //array temporal que almacena las adyacencias por separado.

		for(int i = 0; personajes1D[i] != null; i++)
		{
			personajes1D[i] = personajes1D[i].replace(")", "");
			personajes1D[i] = personajes1D[i].replace("(", ",");
		}

		for(int i = 0; personajes1D[i] != null; i++)
		{
			Arrays.fill(splits, null); //rellena el array temporal con null en cada iteracion, para evitar datos residuales
			splits = personajes1D[i].split(","); //realiza el split separando por ","
			//longitud = splits.length; //mira cuantas palabras por separado hay para poder hacer el bucle siguiente bien:
			//j = 0;
			for(int j = 0; j < splits.length; j++)
			{
				personajes2D[i][j] = splits[j]; //asignas las localizaciones a la segunda dimension
			}
		}

		// JUGADOR

		for(int i = 0; lineas[i] != null; i++)
		{
			if(lineas[i].equals("<Jugador>")) {
				i++;
				String[] separaciones = new String[100];
				lineas[i] = lineas[i].replace(")", "");
				lineas[i] = lineas[i].replace("(", ",");
				lineas[i] = lineas[i].replace(" ", "");
				separaciones = lineas[i].split(",");
				jugador_string = separaciones[0];
				break;
			}
		}
	}
	
	public void instEntidades()
	{
//		System.out.println(" ");
//		System.out.println("INSTANCIANDO PERSONAJES");
		for(int i = 0; personajes2D[i][0] != null; i++)
		{
			for(int j = 0; j < rooms.size() ; j++)
			{
				if(rooms.get(j).getName().equals(personajes2D[i][1])) { //cuando los strings coinciden...
					
					if(personajes2D[i][0].equals(jugador_string)){ // SI ES EL JUGADOR, INSTANCIA JUGADOR; SI NO, INSTANCIA IA.
						
//						System.out.println("JUGADOR: " + personajes2D[i][0]);
						arrPersonajes[i] = new Player(personajes2D[i][0], rooms.get(j)); //... pilla el objeto cuya string coincida y lo pasa al constructor de personajes.
					}else{
						
//						System.out.println("IA: " + personajes2D[i][0]);
						arrPersonajes[i] = new IA(personajes2D[i][0], rooms.get(j)); //... pilla el objeto cuya string coincida y lo pasa al constructor de personajes.
//						System.out.println(arrPersonajes[i].getName() + " --> " + Rooms.get(j).getName());
					}
				}
			}
		}

//		System.out.println("\nANADIENDO PERSONAJES A ARRAYLIST DE PERSONAJES: ");
		//se recorre el array de personajes (objetos), y se aNade cada objeto personaje al arraylist correspondiente.
		for(int i = 0; arrPersonajes[i] != null; i++)
		{
			listPersonajes.add(arrPersonajes[i]);
//			System.out.println("ANADIDO: " + listPersonajes.get(i).getName());
		}

		//comprobar();
	}

	@Override
	public ArrayList<Room> getRooms() {
		return null;
	}

	@Override
	public ArrayList<Personaje> getPersonajes(){
		return listPersonajes != null ? listPersonajes : null;
	}

	@Override
	public ArrayList<Objeto> getObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void comprobar() {
		System.out.println("\n\nPERSONAJES");
		for(Personaje x : listPersonajes) {
			System.out.println("NAME:" + x.getName());
			System.out.println("HAB:" + x.getRoom().getName());
			System.out.print("OBJ:");
			try {System.out.println(x.getObj().getName());}catch(Exception e) {System.out.println("no tiene obj.");}
		}
	}
	
}
