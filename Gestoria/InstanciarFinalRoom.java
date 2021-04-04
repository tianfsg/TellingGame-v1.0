package Gestoria;

import Entidades.Objeto;
import Entidades.Personaje;
import Entidades.Room;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class InstanciarFinalRoom extends StrategyInstanciador{
	
    private String[] lineas = new String[100];                    //aqui se almacenan las lineas del archivo sin modificar

    private String[] localFinal1D = new String[100];             //aqui se almacenan las localizaciones objetivo sin modificar
    private String[][] localFinal2D = new String[100][100];      //aqui se almacenan las loc. obj. ya separadas (0: nombre personaje 1: nombre habitacion)

    private ArrayList<Room> habitaciones;
    private ArrayList<Personaje> Personajes;

    public InstanciarFinalRoom(String path, ArrayList<Personaje> arrayList, ArrayList<Room> arrayList2)
    {
    	setPersonajes(arrayList);
    	setRooms(arrayList2);
        extractString(path);
        executeInst();
    }

    public void setPersonajes(ArrayList<Personaje> personajes) {
    	this.Personajes = personajes;
    }
    
    public void setRooms(ArrayList<Room> rooms) {
    	this.habitaciones = rooms;
    }
    
    
    public ArrayList<Personaje> getPersonaje() {
    	return Personajes;
    }
    
    public ArrayList<Room>getRoom() {
    	return habitaciones;
    }
    
    
    //Metodos
    
    public void executeInst()
    {
        asigEntidades();
        instEntidades();
    }

    public void extractString(String path) {
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
            archivo = new File(path);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            //se leen las lineas del archivo y se asignan al array de lineas
            String linea_fich;
            int i = 0;
            while ((linea_fich = br.readLine()) != null) {
                this.lineas[i] = linea_fich;
                i++;
            }

        } catch (Exception e) {

            System.err.println("[ERROR] > Apertura de fichero fallida.");
            return;

        } finally {
            try {
                if (null != archivo) {         //se intenta cerrar el archivo; si falla, se lanza una excepción
                    fr.close();

                }
            } catch (Exception e2) {
                System.err.println("[ERROR] > Cierre de fichero fallido.");
                e2.printStackTrace();
                return;
            }
        }
    }

    public void asigEntidades()
    {
        //System.out.println("asignaLocalFinal");
        int i = 0;
        int j = 0;
        while (lineas[i] != null) { //recorre el array de lineas entero
            //pilla solamente las localizaciones:
            if (lineas[i].equals("<Localizacion Personajes>")) {
                i++;
                while (lineas[i].equals("<Posesion Objetos>") == false) {
                    this.localFinal1D[j] = lineas[i]; //asigna las lineas de localizaciones al array localFinal1D
                    i++;
                    j++;
                }
                break;
            }
            i++;

        }

        // 2D ------------------------------------------------

        i = 0; j = 0;
        
        String[] splits = new String[100]; //array "temporal" que almacenara las adyacencias por separado.

        while (localFinal1D[i] != null) {
            //este bucle transforma los personajes asi:
            //Jorge(Comedor) >> Jorge(Comedor >> Jorge,Comedor
            //para ello, usa el metodo String.replace()
            localFinal1D[i] = localFinal1D[i].replace(")", "");
            localFinal1D[i] = localFinal1D[i].replace("(", ",");
            localFinal1D[i] = localFinal1D[i].replace(" ", "");
            i++;
        }

        //una vez se ha transformado, se realiza la separacion para asignar a las dos dimensiones:
        i = 0;j = 0;
        
        while (localFinal1D[i] != null) {

            Arrays.fill(splits, null); //rellena el array temporal con null en cada iteracion, para evitar datos residuales
            splits = localFinal1D[i].split(","); //realiza el split separando por ","
            j = 0;
            while (j < splits.length) {
//				System.out.println(splits[j]);
                localFinal2D[i][j] = splits[j]; //asignas las localizaciones finales a la segunda dimension
                j++;
            }

            i++;
        }
    }

    public void instEntidades()
    {
        for (int i = 0; localFinal2D[i][1] != null; i++) {
        	for (int j = 0; j < Personajes.size(); j++) {
        		if (Personajes.get(j).getName().equals(localFinal2D[i][0]) && Personajes.get(j) != null) {
        			for (int k = 0; k < habitaciones.size(); k++) {
                    	if (habitaciones.get(k).getName().equals(localFinal2D[i][1]) && habitaciones.get(k) != null) {
                    		Personajes.get(j).setFinalRoom(habitaciones.get(k));
//                            System.out.println(Personajes.get(j).getName() + " esta en: " + habitaciones.get(k).getName());
                        }
                    }
                }
            }
        }
    }

	@Override
	public ArrayList<Room> getRooms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Personaje> getPersonajes() {
		// TODO Auto-generated method stub
		return Personajes != null ? Personajes : null;
	}

	@Override
	public ArrayList<Objeto> getObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void comprobar() {
		// TODO Auto-generated method stub
		
	}

}
