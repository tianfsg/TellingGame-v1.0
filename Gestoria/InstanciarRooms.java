package Gestoria;

import Entidades.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;


//LAS ESTRATEGIAS
public class InstanciarRooms extends StrategyInstanciador{

	private String[] lineas = new String[100];

	private String[] localizaciones1D = new String[100];          //aqui se almacenan las localizaciones sin modificar
	private String[][] localizaciones2D = new String[100][100];   //aqui se almacenan las localizaciones ya separadas (0: nombre hab. >0: adyacencias)

	private Room[] Rooms = new Room[100];
	private ArrayList<Room> listRoom = new ArrayList<Room>();

	public InstanciarRooms(String path){
		extractString(path);
		executeInst();
	}
	
	//InstanciarRoomsMethod()
	
	public void executeInst() {
		asigEntidades();
		instEntidades();
	}

	//untouched
	public void extractString(String path){
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
		
		// 1D -----------------------------------------
		int j = 0;

		//desde que ve "<Localizaciones>" hasta que llega a "<Personajes>", anade todas las lineas en el array localizaciones1D
		for(int i = 0; lineas[i] != null; i++)
		{
			if(lineas[i].equals("<Localizaciones>"))
			{
				i++;
				while(lineas[i].equals("<Personajes>") == false) {
					this.localizaciones1D[j] = lineas[i];
					i++;
					j++;
				}
			}
		}

		// 2D -----------------------------------------------

		String[] splits = new String[100]; //array temporal que almacena las adyacencias por separado.

		//este bucle transforma las localizaciones de la siguiente forma, usando String.replace():
		//Jardin(Comedor, Dormitorio) >> Jardin(Comedor, Dormitorio >> Jardin,Comedor, Dormitorio >> Jardin,Comedor,Dormitorio
		for(int i = 0; localizaciones1D[i] != null; i++)
		{
			localizaciones1D[i] = localizaciones1D[i].replace(")", "");
			localizaciones1D[i] = localizaciones1D[i].replace("(", ",");
			localizaciones1D[i] = localizaciones1D[i].replace(" ", "");
		}

		for(int i = 0; localizaciones1D[i] != null; i++)
		{
			Arrays.fill(splits, null); //rellena el array temporal con null en cada iteracion, para evitar datos residuales
			splits = localizaciones1D[i].split(","); //realiza el split separando por ","
			for(int k = 0; k < splits.length; k++)
			{
				localizaciones2D[i][k] = splits[k]; //asignas las adyacencias a la segunda dimension
			}
		}
	}
	
	public void instEntidades(){

		// INSTANCIACION DE HABITACIONES ----------------------

//		System.out.println("INSTANCIANDO HABITACIONES");
		//instancia todas las habitaciones en el array de habitaciones (recorre todo el array de strings y las instancia)

		for(int i = 0; localizaciones2D[i][0] != null; i++)
		{
			Rooms[i] = new Room(localizaciones2D[i][0]);
//			System.out.println(Rooms[i].getName() + " --> " + i);
		}

		// INSTANCIACION DE ADYACENCIAS ---------------------------------------------------------------------------

		//recorre todo el array de localizaciones 2D, y si los nombres coinciden, instancia las adyacencias
		// este bucle lo dejo con while, porque en algunas ocasiones hay que incrementar un contador y en otro otras, y
		// la modularidad del while me lo pone mas facil.
		
		int i = 0, l = 0, j = 1;

		while(localizaciones2D[i][j] != null) {
			if(localizaciones2D[i][j].equals(Rooms[l].getName())) {
				Rooms[i].addBesides(Rooms[l]);
				j++;
				l = 0;
			} else {
				l++;
			}
			if(localizaciones2D[i][j] == null) {
				j = 1;
				i++;
			}
		}

		// TRANSFORMACION EN ARRAYLIST
		//se recorre el array de habitaciones (objetos), y se añade cada objeto habitación al arraylist correspondiente.

		for(int k = 0; Rooms[k] != null; k++){
			listRoom.add(Rooms[k]);
		}
		//comprobar();
	}

	public void comprobar(){
		System.out.println("\n\nROOMS");
		for(Room x : listRoom) {
			System.out.println("NAME: " + x.getName());
		}
	}

	@Override
	public ArrayList<Room> getRooms(){
		return listRoom != null ? listRoom : null;
	}

	@Override
	public ArrayList<Personaje> getPersonajes(){
		return null;
	}

	@Override
	public ArrayList<Objeto> getObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

}
