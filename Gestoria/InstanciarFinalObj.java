package Gestoria;

import Entidades.Objeto;
import Entidades.Personaje;
import Entidades.Room;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class InstanciarFinalObj extends StrategyInstanciador{

    private String[] lineas = new String[100];                    //aqui se almacenan las lineas del archivo sin modificar

    private String[] ObjetosFinal1D = new String[100];           //aqui se almacenan los Objetos objetivo sin modificar
    private String[][] ObjetosFinal2D = new String[100][100];
//
//    private Objeto[] Objetos;
//    private Personaje[] Personajes;

    private ArrayList<Personaje> Personajes;
    private ArrayList<Objeto> Objetos;

    public InstanciarFinalObj(String path, ArrayList<Personaje> personajes, ArrayList<Objeto> objetos) {

    	setPersonajes(personajes);
    	setObjetos(objetos);
        extractString(path);
        executeInst();
    }

    public void executeInst() {
        //Instanciar las habitaciones.
        //Todo el mecanismo necesario siendo modular y creando las funciones necesarias.
        //si se repite algo, mejor crear un metodo que lo haga.
        asigEntidades();
        instEntidades();
    }

    
    
    
	  public void setPersonajes(ArrayList<Personaje> personajes) {
		this.Personajes = personajes;
	}
	
	public void setObjetos(ArrayList<Objeto> Objetos) {
		this.Objetos = Objetos;
	}
	
	public ArrayList<Objeto>  getObjetos() {
		return Objetos;
	}
	
	public ArrayList<Personaje> getPersonaje() {
		return Personajes;
	}

    
    //Metodos.
    
    public void extractString(String path) {
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
        //System.out.println("asignaObjetosFinal");
        // 1D --------------------------------------------------------------
        int j = 0;
        for (int i = 0; lineas[i] != null; i++) {
            if (lineas[i].equals("<Posesion Objetos>")) {
                i++;
                while (lineas[i] != null) {
                    ObjetosFinal1D[j] = lineas[i]; //asigna las lineas enteras de Objetos finales en el array ObjetosFinal1D
                    i++; j++;
                }
            }
        }

        // 2D -------------------------------------------------------

        String[] splits = new String[100]; //array "temporal" que almacenara las adyacencias por separado.
        for (int i = 0; ObjetosFinal1D[i] != null; i++) {
            ObjetosFinal1D[i] = ObjetosFinal1D[i].replace(")", "");
            ObjetosFinal1D[i] = ObjetosFinal1D[i].replace("(", ",");
        }

        for (int i = 0; ObjetosFinal1D[i] != null; i++) {
            Arrays.fill(splits, null); //rellena el array temporal con null en cada iteracion, para evitar datos residuales
            splits = ObjetosFinal1D[i].split(","); //realiza el split separando por ","

            for (int k = 0; k < splits.length; k++) {
                ObjetosFinal2D[i][k] = splits[k]; //asignas los personaejs a la segunda dimension
            }
        }
    }

    public void instEntidades(){
        //System.out.println("instanciaObjetosFinal");
        //se recorre el array entero de ObjetosFinal2D (strings)

        for (int i = 0; ObjetosFinal2D[i][1] != null; i++) {
        	
            for (int j = 0; j < Personajes.size(); j++) {
                if (Personajes.get(j).getName().equals(ObjetosFinal2D[i][1]) && Personajes.get(j) != null) {
                	for (int k = 0; k < Objetos.size() ; k++) {
                        if (Objetos.get(k).getName().equals(ObjetosFinal2D[i][0]) && Objetos.get(k) != null) {
                            Personajes.get(j).setFinalObj(Objetos.get(k));
//                            System.out.println(Personajes.get(j).getName() + " tiene de objeto final: " + Objetos.get(k).getName());
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
	public void comprobar() {
		// TODO Auto-generated method stub
		
	}

}
