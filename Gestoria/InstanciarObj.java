package Gestoria;

import Entidades.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class InstanciarObj extends StrategyInstanciador {

	private String[] lineas = new String[100];                  	//aqui se almacenan las lineas del archivo sin modificar

	private String[] Objetos1D = new String[100];				 	//aqui se almacenan los Objetos sin modificar (Objetos de la partida, no Objetos de java)
	private String[][] Objetos2D = new String[100][100];         	//aqui se almacenan los Objetos ya separados (0: nombre obj.  1: localizacion o personaje inicial)

	private Objeto[] Objetos = new Objeto[100];
	
	ArrayList<Personaje> Personajes; //Personajes[]
	ArrayList<Room> Rooms;			//Rooms[]
	private ArrayList<Objeto> listObj = new ArrayList<Objeto>();

	public InstanciarObj(String path, ArrayList<Room> allRooms, ArrayList<Personaje> allPersonajes) {
		setPersonaje(allPersonajes);
		setRooms(allRooms);
		extractString(path);
		executeInst();
	}

	public void setRooms(ArrayList<Room> rooms) {
		if(rooms != null) {
			this.Rooms = rooms;	
		}
	}
	
	public void setPersonaje(ArrayList<Personaje> personajes) {
		if(personajes != null) {
			this.Personajes = personajes;	
		}
	}
	
	//InstanciarObjMethods()
	
	public void executeInst() {
		asigEntidades();
		instEntidades();
	}

	//untouched
	public void extractString(String path){
		/*
		- metodo que abre el archivo y vuelca sus contenidos en el array de
	 	  strings llamado "lineas".

		- este metodo es necesario para la posterior instanciacion de los Objetos,
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
	
	public void asigEntidades()
	{
		/*
		  - este metodo analiza el array de strings en el que se ha vertido
		    el contenido del archivo, localiza la seccion de Objetos, y
		    los almacena COMO STRINGS justo a su localizacion o personaje
		    inciales.

		  - dicho almacenamiento se realiza por pasos:
		      - primero, se pasa cada objeto con su personaje o localizacion
		        "tal cual" al array Objetos1D.
		      - segundo, se analiza el array Objetos1D linea a linea, separando
		        objeto de personaje o localizacion, y almacenandolo en otro array
		        (Objetos2D). La primera dimension es el nombre del objeto, la segunda
		        es su personaje o localizacion inicial.

		    este modelo de estructura de datos facilita la posterior instanciacion
		    como Objetos (notese que hasta ahora, solo se ha trabajado con strings)
		 */
		// almacenamiento en array 1D ------------------------------------------------------

		for(int i = 0; lineas[i] != null; i++)
		{
			if(lineas[i].equals("<Objetos>")) {
				i++;
				for(int j = 0; lineas[i].equals("<Jugador>") == false; j++)
				{
					Objetos1D[j] = lineas[i];
					i++;
				}
			}
		}

		// almacenamiento en array 2D ----------------------------------------------------------------

		String[] splits = new String[100]; //array "temporal" que almacenara las adyacencias por separado.

		for(int i = 0; Objetos1D[i] != null; i++)
		{
			Objetos1D[i] = Objetos1D[i].replace(")", "");
			Objetos1D[i] = Objetos1D[i].replace("(", ",");
		}


		for(int i = 0; Objetos1D[i] != null; i++)
		{
			Arrays.fill(splits, null); //rellena el array temporal con null en cada iteracion, para evitar datos residuales
			splits = Objetos1D[i].split(","); //realiza el split separando por ","

			for(int j = 0; j < splits.length; j++)
			{
				Objetos2D[i][j] = splits[j]; //asignas las localizaciones o personajes a la segunda dimension
			}
		}
	}

	public void instEntidades(){
		// LOCALIZACIONES ------------------------------------------

//		System.out.println(" ");
//		System.out.println("INSTANCIANDO Objetos ASOCIADOS A LOCALIZACIONES");

		for(int i = 0; Objetos2D[i][0] != null; i++)
		{	
			for(int j = 0; j < Rooms.size() ; j++)
			{	
				if(Rooms.get(j).getName().equals(Objetos2D[i][1])) { //si el nombre de la habitacion (objeto) y el de la habitacion inicial del objeto (string) son iguales, se instancia
//					System.out.println(i + " --- " + Objetos2D[i][0] + " --> " + Rooms.get(j).getName());
					Objetos[i] = new Objeto(Objetos2D[i][0], Rooms.get(j));
				}
			}
		}

		// PERSONAJES ----------------------------------------------

		//esta parte del codigo en especifico es mas rentable
		int i = 0;
		while(Objetos[i] != null) {
			i++;
		}
		//int j = 0;
//		System.out.println(" ");
//		System.out.println("INSTANCIANDO Objetos ASOCIADOS A PERSONAJES");
		while (Objetos2D[i][0] != null) { //recorre el contenido del array de Objetos (indice 0 = nombre) (STRING)
			for(int j = 0; j < Personajes.size(); j++)
			{
				if(Personajes.get(j).getName().equals(Objetos2D[i][1])) { //si el nombre de la habitacion (objeto) y el del personaje (string) son iguales, se instancia
//					System.out.println(i + " --- " + Objetos2D[i][0] + " --> " + Personajes.get(j).getName());
					Objetos[i] = new Objeto(Objetos2D[i][0], Personajes.get(j));
				}
			}
			i++;
		}

		// ARRAYLIST
//		System.out.println("\nANADIENDO Objetos A ARRAYLIST DE Objetos: ");

		//se recorre el array de Objetos ("Objetos"), y se aNade cada objeto objeto al arraylist correspondiente.

		for(int k = 0; Objetos[k] != null; k++)
		{
			listObj.add(Objetos[k]);
//			System.out.println("ANADIDO: " + listObj.get(k).getName());
		}
		
		//comprobar();
	}
	
	@Override
	public ArrayList<Room> getRooms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Personaje> getPersonajes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Objeto> getObjetos(){
		return listObj != null ? listObj : null;	
	}

	@Override
	public void comprobar() {
		System.out.println("\n\nOBJETOS");
		for(Objeto x : listObj) {
			System.out.println("NAME:"+x.getName());
			try {System.out.println("PERS:"+x.getPersonaje().getName());}catch(Exception e) {System.out.println("HAB:"+x.getRoom().getName());}
		}
	}


}
