package Gestoria;

import java.util.ArrayList;
import java.util.Scanner;
import Entidades.*;

public abstract class Admin {

	Scanner input = new Scanner(System.in);
	protected static ArrayList<Object> log = null;
	
	public Admin() {
		if(log == null) {
			log = new ArrayList<Object>();
		}
	}
	
	
    public abstract void Start();
    
    //Basic algorithm methods  
    public int showOptions(Personaje jugador) {
        
        if(!jugador.getIA()) {//-------------- MECANISMO PLAYER.
       
        	mostrarEntorno(jugador);
        	
            if(jugador.hasObj()) {//Tiene objeto equipado
                if(!jugador.getRoom().Objetos().isEmpty()) {//Hay mas Objetos en la Habitacion.
            		
            		if(jugador.getRoom().Personajes().size() > 1) {//Hay mas Personajes en la Habitacion.
            			System.out.println(jugador.getName() + " que accion deseas realizar?");
            			System.out.println("1. Moverse  2. Soltar/Tomar Objeto 3. Dar 4. Nada");
            			return 1;
            		}else {//Solo y hay Objetos.
            			System.out.println(jugador.getName() + " que accion deseas realizar?");
                      	System.out.println("1. Moverse  2. Soltar/Tomar Objeto 3. Nada");
                      	return 2;
            		}
            	}else {//No hay Objetos en la Habitacion. -- tiene objeto.
            		
               		if(jugador.getRoom().Personajes().size() > 1) {//Hay mas Personajes en la Habitacion.
            			System.out.println(jugador.getName() + " que accion deseas realizar?");
            			System.out.println("1. Moverse  2. Soltar Objeto 3. Dar 4. Nada");
            			return 3;
            		}else {//Solo y no hay Objetos.
            			System.out.println(jugador.getName() + " que accion deseas realizar?");
            			System.out.println("1. Moverse  2. Soltar Objeto 3. Nada");
            			return 4;
            		}
            	}
 
            } else {//No tiene objeto
             	
            	if(!jugador.getRoom().Objetos().isEmpty()) {//Hay Objetos en la Habitacion.
            		
            		if(jugador.getRoom().Personajes().size() > 1) {//Hay Personajes en la Habitacion.
            			boolean demand = false;
            			
            			//crear mecanismo que guarde los personajes que hay y registre el cambio.
            			
            			
            			for(Personaje x : jugador.getRoom().Personajes()) {
            				if(x.hasObj()) {
            					demand = true;
            				}
            			}
            			if(demand) {
            				System.out.println(jugador.getName() + " que accion deseas realizar?");
                            System.out.println("1. Moverse  2. Tomar Objeto 3. Pedir Objeto 4. Nada");
                            return 5;
                		}else {
                			System.out.println(jugador.getName() + " que accion deseas realizar?");
                            System.out.println("1. Moverse  2. Tomar Objeto 3. Nada");
                            return 6;
                		}
            		
            		
            		}else {//No hay Personajes en la Habitacion.
            			System.out.println(jugador.getName() + " que accion deseas realizar?");
                        System.out.println("1. Moverse  2. Tomar Objeto 3. Nada");
                        return 7;
            		}
            		
            	}else{//No hay objetos.
            		
            		if(jugador.getRoom().Personajes().size() > 1) {//Hay Personajes en la Habitacion.
            			boolean demand = false;
            			for(Personaje x : jugador.getRoom().Personajes()) {
            				if(x.hasObj()) {
            					demand = true;
            				}
            			}
            			if(demand) {
            				System.out.println(jugador.getName() + " que accion deseas realizar?");
                            System.out.println("1. Moverse 2. Pedir Objeto 3. Nada");
                            return 8;
                		}else {
                			System.out.println(jugador.getName() + " que accion deseas realizar?");
                            System.out.println("1. Moverse 2. Nada");
                            return 9;
                		}
            			
            		}else {//No hay Personajes en la Habitacion.
            			System.out.println(jugador.getName() + " que accion deseas realizar?");
                        System.out.println("1. Moverse 2. Nada");
                        return 10;
            		}
            	}
            }
            
        }else {//----------------- MECANISMO IA.
        	
            if(jugador.hasObj()) {//Tiene objeto equipado
            	
            	  if(!jugador.getRoom().Objetos().isEmpty()) {//Objetos en la hab.
            		  if(jugador.getRoom().Personajes().size() > 1) {//Personajes en la hab.
            			  return 1;
            		  }else {
            			  return 2;
            		  }
            		  
            	  }else{//sin Objetos en la hab.
            		  if(jugador.getRoom().Personajes().size() > 1) {//sin Personajes en la hab.
            			  return 3;
            		  }else {
            			  return 4;
            		  }
            	  }
         
            }else {//No tiene objeto
                
	            if(!jugador.getRoom().Objetos().isEmpty()) {//Objetos en la hab.
	            	if(jugador.getRoom().Personajes().size() > 1) {//Personajes en la hab.
	            		return 5;
	        		}else {
	        			return 6;
	        		}
	        		  
	            }else{//sin Objetos en la hab.
	        		
	            	if(jugador.getRoom().Personajes().size() > 1) {//sin Personajes en la hab.
	            		return 7;
	            	}else {
	            		return 8;
	            	}
	        	}
            }
        }
    }
    
    public void takeOption(Personaje jugador) {
        int decision = 0;
        int rama = 0;
    	boolean valido = false;

        if(!jugador.getIA()){//Es el jugador fisico. 
        
        	rama = showOptions(jugador);    
           	decision = input.nextInt();    
            System.out.println("DECISIONL: " + decision);
            	
            doOption(jugador, decision, rama);
            attachCreencias(jugador);
        }else {
            //Es el jugador IA.
            rama = showOptions(jugador);
            switch(rama) {//Le disminuimos el numero de decisiones para que siempre se mantenga buscando los objetos y despues las habitaciones.
            case 1:
            	decision = jugador.chooseIA(3); 
            	doOption(jugador, decision, rama);
                break;
            case 2:
            	decision = jugador.chooseIA(2); 
            	doOption(jugador, decision, rama);
            	break;
            case 3:
            	decision = jugador.chooseIA(3);
            	doOption(jugador, decision, rama);
            	break;
            case 4:
            	decision = jugador.chooseIA(2);
            	doOption(jugador, decision, rama);
            	break;
            case 5:
            	decision = jugador.chooseIA(3);
            	doOption(jugador, decision, rama);
            	break;
            case 6:
            	decision = jugador.chooseIA(2);
            	doOption(jugador, decision, rama);
            	break;
            case 7:
            	decision = jugador.chooseIA(2);
            	doOption(jugador, decision, rama);
            	break;
            case 8:
            	decision = jugador.chooseIA(2);
            	doOption(jugador, decision, rama);
            	break;
            case 9:
            	decision = jugador.chooseIA(1);
            	doOption(jugador, decision, rama);
            	break;
            case 10:
            	decision = jugador.chooseIA(1);
            	doOption(jugador, decision, rama);
            	break;default:System.err.println("Esa decision no es valida, intente de nuevo. linea 204 Admin.java\n");
                break;
            }
        }
    }
    
    public void doOption(Personaje jugador, int decision, int rama) {

    	attachLog(jugador, decision, rama);
    	
        switch(rama) {
        case 1:
            //Tiene objeto y hay mas jugadores en la sala.
            switch(decision) {
            case 1: 
                //Moverse de habitacion. 
            	move(jugador);
            	break;
            case 2: 
                //Soltar/Tomar Objeto.
            	take(jugador);
            	break;
            case 3:
            	give(jugador);
            	break;
            case 4:
                //no hacer nada
                break;
            default: System.err.println("ERROR: opcion no valida N161\n");
                break;
            }//Fin del switch;
            break;//Fin del case 1:
        case 2:
            //Tiene objeto pero esta solo.
            switch(decision) {
            case 1:
                //Moverse de habitacion.
            	move(jugador);
            	break;
            case 2:
                //Soltar/Tomar Objeto.
            	take(jugador);
                break;
            case 3:
            	if(!jugador.getIA()) {
            		System.out.println("No hiciste nada.");
             	}
            	break;
            default: System.err.println("ERROR: opcion no valida N178\n");
                break;
            }//Fin del switch;
            break;//Fin del case 2:
        case 3:
            //Hay Objeto en la sala y hay mas Personajes en la sala.
            switch(decision) {
            case 1:
                //Moverse de habitacion.
            	move(jugador);
            	break;
            case 2:
                //Soltar Objeto.
            	drop(jugador);
            	break;
            case 3:
            	give(jugador);
            	break;
            case 4:
            	if(!jugador.getIA()) {
            		System.out.println("No hiciste nada.");
             	}
            	break;
            default: System.err.print("ERROR: opcion no valida N204\n");
                break;
            }//Fin del switch;
            break;//Fin del case 3:
        case 4:
            //Hay Objeto pero esta solo en la sala.
            switch(decision) {
            case 1:
                //Moverse de habitacion.
            	move(jugador);
            	break;
            case 2:
                //Soltar Objeto.
            	drop(jugador);
            	break;
            case 3:
            	if(!jugador.getIA()) {
            		System.out.println("No hiciste nada.");
             	}
            	break;
            default: System.err.print("ERROR: opcion no valida N221\n");
                break;
            }//Fin del switch;
            break;//Fin del case 4:
        case 5: 
            //No hay Objeto pero hay mas Personajes en la sala.
            switch(decision) {
            case 1:
                //Moverse a otra Habitacion.
            	move(jugador);
            	break;
            case 2:
                //Tomar Objeto.
            	take(jugador);
                break;
            case 3:
            	demand(jugador);
            	break;
            case 4: 
            	if(!jugador.getIA()) {
            		System.out.println("No hiciste nada.");
             	}
            	break;
            default: System.err.print("ERROR: opcion no valida N238\n");
                break;
            }//fin de la decision de la rama 5.
            break;
        case 6:
            //No hay Objeto y esta solo en la sala.
            switch(decision) {
            case 1:
                //Moverse a otra Habitacion.
            	move(jugador);
            	break;
            case 2:
            	//Tomar Objeto.
            	take(jugador);
            	break;
            case 3:
            	if(!jugador.getIA()) {
            		System.out.println("No hiciste nada.");
             	}
            	break;
            default: System.err.print("ERROR: opcion no valida N251\n");
                break;
            }
            break;
        case 7:
        	switch(decision) {
        	case 1:
        		//Mover a otra hab.
        		move(jugador);
        		break;
        	case 2:
        		//Tomar
        		take(jugador);
        		break;
        	case 3:
            	if(!jugador.getIA()) {
            		System.out.println("No hiciste nada.");
             	}
            	break;
            default: System.err.print("ERROR: opcion no valida N314\n");
        		break;
        	}
        	break;
        case 8:
        	switch(decision) {
        	case 1:
        		//Mover
        		move(jugador);
        		break;
        	case 2:
        		//Pedir
        		demand(jugador);
        		break;
        	case 3:
            	if(!jugador.getIA()) {
            		System.out.println("No hiciste nada.");
             	}
            	break;
        	 default: System.err.print("ERROR: opcion no valida N326\n");
              	break;
        	}
        	break;
        case 9:
        	switch(decision) {
        	case 1:
        		//Mover a otra hab.
        		move(jugador);
        		break;
        	case 2:
            	if(!jugador.getIA()) {
            		System.out.println("No hiciste nada.");
             	}
            	break;
            default: System.err.print("ERROR: opcion no valida N314\n");
        		break;
        	}
        	break;
        case 10:
        	switch(decision) {
        	case 1:
        		move(jugador);
        		break;
        	case 2:
            	if(!jugador.getIA()) {
            		System.out.println("No hiciste nada.");
             	}
            	break;
        	 default: System.err.print("ERROR: opcion no valida N326\n");
              	break;
        	}
        	break;
        default: System.err.print("ERROR: opcion no valida N331\n");
            break;//Fin del switch rama;
        }
    }
    
    public void Round(Personaje jugador) {
    	takeOption(jugador);
    	jugador.setTurno(true);
    }
    
    public void newRound(ArrayList<Personaje> personajes) {
    	for(Personaje x : personajes) {
    		x.setTurno(false);
    	}
    }
    
    //AdminMethods();
    public void move(Personaje jugador){
       
    	Room next = null;
    	int opciones = 0;
    	
    	if(jugador.getIA()) {//------------------- Mecanismo IA
    		
    		if(jugador.finalObj()) {//tiene el objeto final.
    			
    			if(jugador.getRoom().Anexos().contains(jugador.getFinalRoom())){//Esta la habitacion final anexa a la habitacion actual.
    				
    				next = jugador.getRoom().Anexos().get(jugador.getRoom().Anexos().indexOf(jugador.getFinalRoom())); //Asigna la habitacion final.
    				jugador.setRoom(next);
    				//Debug
//    	    		System.out.println(jugador.getName() + " se movio a " + jugador.getRoom().getName());
    			
    			}
    			
    			if(jugador.getRoom().getName() != jugador.getFinalRoom().getName()) {//Se mueve aleatoriamente hasta acercarse a su habitacion final.
    	    		opciones = jugador.getRoom().Anexos().size();
    	    		next = jugador.getRoom().Anexos().get(jugador.chooseIA(opciones)-1);
    	    		jugador.setRoom(next);
    	    		//Debug
//    	    		System.out.println(jugador.getName() + " se movio a " + jugador.getRoom().getName());
    			}
    			
    		}else{//no tiene el objeto final.
    			
    			if(!jugador.getRoom().Objetos().contains(jugador.getFinalObj())) {//No esta el Objeto, intenta moverse para buscarlo.
    			
    				opciones = jugador.getRoom().Anexos().size();
    	    		next = jugador.getRoom().Anexos().get(jugador.chooseIA(opciones)-1);
    	    		jugador.setRoom(next);
    	    		//Debug
//    	    		System.out.println(jugador.getName() + " se movio a " + jugador.getRoom().getName());
    			}
    		}
    		
    	}else {//------------------ Mecanismo Player

    		System.out.println("Ir a:");
    		do {
        		jugador.getRoom().listAnexos();
    			opciones = input.nextInt()-1;
    		}while(opciones > jugador.getRoom().Anexos().size()-1);
    		
			next = jugador.getRoom().Anexos().get(opciones);
    		jugador.setRoom(next);  		
    	}
    	
    }
    
    public void take(Personaje jugador) {
    	
    	Objeto take;
    	int decision, opciones;
    	
    	if(jugador.getIA()){//------------- Mecanismo IA.
    		
    		if(jugador.getRoom().Objetos().contains(jugador.getFinalObj())){//esta el Objeto final.
    		
    			take = jugador.getRoom().Objetos().get(jugador.getRoom().Objetos().indexOf(jugador.getFinalObj())); //asigna el objeto final.
    			take.setPersonaje(jugador);
	    		//Debug
//	    		System.out.println(jugador.getName() + " tomo " + jugador.getObj().getName());
    			
    		}else{//no esta el Objeto final.
    			if(!jugador.getRoom().Objetos().isEmpty()) {
	    			
    				decision = jugador.chooseIA(2);				//Se pregunta si quiere tomar algun objeto o no.
	    			if(decision == 1) {
	    				opciones = jugador.getRoom().Objetos().size();
	    				take = jugador.getRoom().Objetos().get(jugador.chooseIA(opciones)-1);
	    				take.setPersonaje(jugador);
	    				//Debug
//	    	    		System.out.println(jugador.getName() + " tomo " + jugador.getObj().getName());
	        		}
    			
	    			if(jugador.hasObj() && decision != 1) {
	    				drop(jugador);
	    			}
    			}
    		}
    		
    	}else {//------------ Mecanismo Player.

    		if(jugador.hasObj()) {
	    		
	    		System.out.println("Parece que ya tienes un objeto ("+jugador.getObj().getName()+"), deseas tirarlo por otro?");
	    		do {
	    			System.out.println("1. Si || 2. No");
		    		decision = input.nextInt();
	    		}while(decision > 2);
	    		
	    		if(decision == 1) {

	        		System.out.println("Cual quieres tomar?");
	    			do {
	    				jugador.getRoom().listObj();
	            		opciones = input.nextInt()-1;
	        		}while(opciones > jugador.getRoom().Objetos().size()-1);

	        		take = jugador.getRoom().Objetos().get(opciones);
	    			
	    			take.setPersonaje(jugador);
	    			System.out.println("Has adquirido " + jugador.getObj().getName());
	    		}else {
	    			drop(jugador);
	    		}
	    		
	    	}else{
	    		
	    		System.out.println("Cual quieres tomar?");
				do {
					jugador.getRoom().listObj();
	        		opciones = input.nextInt()-1;
	    		}while(opciones > jugador.getRoom().Objetos().size()-1);

	    		take = jugador.getRoom().Objetos().get(opciones);
	    		take.setPersonaje(jugador);
	    		System.out.println("Has adquirido " + jugador.getObj().getName());
	    	}
	    }
    	
    	for(Objeto x : jugador.getRoom().Objetos()) {
//    		System.out.println(x.getName());
    	}
    	
    }
       
    public void drop(Personaje jugador) {
    	
    	
    	if(!jugador.getIA()) {
    		
    		if(jugador.hasObj()) {
				System.out.println("Has soltado " + jugador.getObj().getName());
        		jugador.dropObj();
        	}
    		
    	}else{
    		
    		if(jugador.getFinalObj() != null) {
    			if(jugador.hasObj() && jugador.getFinalObj().getName() != jugador.getObj().getName()) {
    				jugador.dropObj();
        		}
    			
    		}else {
    			if(jugador.hasObj()) {
    				jugador.dropObj();
    			}
    		}
    	}
    }
    
    public void give(Personaje jugador) {

    	Personaje player;
    	int rand, opciones, decision;
    	
    	if(jugador.getIA() && jugador.hasObj()) {//-------- Mecanismo IA.
    		
    		if(!jugador.finalObj()) {//no tiene el objeto final.
    			if(jugador.getRoom().Personajes().size() > 0) {//hay mas personajes en la room.

    				opciones = jugador.getRoom().Personajes().size();
    				player = jugador.getRoom().Personajes().get(jugador.chooseIA(opciones)-1);
    				
    				if(player.getIA()){//es la IA.
    				
    					rand = (int) (Math.random() * 2) + 1;
    					if(rand == 1){
    						jugador.giveObj(player);
//    						System.out.println(jugador.getName() + " le dio " + player.getObj().getName() + " a " + player.getName());
    	    			}
    				
    				}else if(!player.getIA()){//es el Player.
    					
    					System.out.println(jugador.getName() + " quiere darte su objeto, que dices? ---> (" +jugador.getObj().getName()+")");
    					do {
        					System.out.println("1. Si acentuado || 2. No");
    						decision = input.nextInt();
    					}while (decision > 2);
    					
    					if(decision == 1){
    						jugador.giveObj(player);
    					}
    				}			
    			}
    		}
    		
    	}else if(!jugador.getIA() && jugador.hasObj()) {//------- Mecanismo Player.
    		
    		System.out.println("A quien le quieres dar tu objeto?");
    		do {
    			jugador.getRoom().listarPreguntados(jugador);
        		opciones = input.nextInt()-1;
    		}while(opciones > jugador.getRoom().Personajes().size()-1);
    		
    		player = jugador.getRoom().Personajes().get(opciones);

    		rand = (int) (Math.random() * 2) + 1;
    		if(rand == 1) {
    			jugador.giveObj(player);
    			System.out.println("Le has dado "+ player.getObj().getName()+" a "+player.getName());
    		}else {
    			System.out.println(player.getName() + " no quiso tu Objeto.");
    		}
    	}
    }

    public void demand(Personaje jugador){
    	
    	ArrayList<Personaje> personajes = jugador.getRoom().Personajes();
    	
    	int decision, rand;
    	boolean done = false;
    	
    	if(jugador.getIA()) {//------------ Mecanismo IA.	
    		if(!jugador.finalObj()) {//no tiene el Objeto Final.
    		
    			for(Personaje player : personajes) {
    				
    				if(player.hasObj() && !done) {
        				if(player.getObj().getName() == jugador.getFinalRoom().getName()){//Tiene el Objeto Final.
    						if(player.getIA()) {//es la IA.
    							
        						rand = (int) (Math.random() * 2) + 1;
//        						System.out.println("Valores del rand:"+rand);
        						if(rand == 1) {
        							player.giveObj(jugador);
//            						System.out.println(Player.getName() + " le dio ("+jugador.getObj().getName()+") a " + jugador.getName());
        							done = true;
        						}
        					
    						}else{//es el Player.
        						System.out.println(jugador.getName() + " te esta pidiendo tu objeto ("+player.getObj().getName()+"), que dices?");
        						do {
            						System.out.println("1. Si acentuado || 2. No");
        							decision = input.nextInt();
        						}while(decision > 2);
        						
        						if(decision == 1) {
        							player.giveObj(jugador);
            						System.out.println(player.getName() + " le diste ("+jugador.getObj().getName()+") a " + jugador.getName());
        							done = true;
        						}else {
        							System.out.println("No le diste tu Objeto.");
        						}
        					}
        				}
        			}
        		}
    		}    		
    		
    	}else {//-------------- Mecanismo Player.
    		
    		System.out.println("A quien le quieres pedir su Objeto?");
    		do {
        		jugador.getRoom().listarPreguntados(jugador);
    			decision = input.nextInt()-1;
    		}while(decision > jugador.getRoom().Personajes().size()-1);
    		
        	Personaje player;
			player = jugador.getRoom().Personajes().get(decision);
    		
    		if(player.hasObj()) {
    			rand = (int) (Math.random() * 2) + 1;
        		if(rand == 1) {
        			player.giveObj(jugador);
        			System.out.println(player.getName() + " te ha dado " + jugador.getObj().getName());
        		}else {
        			System.out.println(player.getName() + " prefirio no darte su Objeto.");
        		}
    		}else {
    			System.out.println(player.getName() + " no tiene Objeto.");
    		}
    		
    	}
    }
    
    public void ask(Personaje jugador) {
    	
    	int decision;
    	Personaje player;
    	if(!jugador.getIA()) {//	MECANISMO PARA PLAYER
    		
    		//Quien quieres que te diga sus creencias?
    		do {
    			jugador.getRoom().listarPreguntados(jugador);
    			decision = input.nextInt() - 1;
    		}while(decision < 0 && decision > jugador.getRoom().Personajes().size() - 1);
    	
    		player = jugador.getRoom().Personajes().get(decision);
    	
    		switch(player.chooseIA(2)) {
    		case 1:
    			player.lastBelieves();
    			break;
    		case 2:
    			System.out.println(player.getName() + " no quiso darte informacion.");
    			break;
    		}
   
    	}else {//	MECANISMO PARA LA IA	
    		
    		decision = jugador.getRoom().Personajes().size();
    		player = jugador.getRoom().Personajes().get(jugador.chooseIA(decision)-1);
    		
    		if(!player.getIA()) {//Es el player
    			
    			do {
	    			System.out.println(jugador.getName()+" quiere algo de informacion, deseas darle la info?");
	    			System.out.println(" 1. SI | 2. NO");
	    
	    			decision = input.nextInt();
    			}while(decision != 1 && decision != 2);
    			
//    			System.out.println("DECISION (ask.Admin): " + decision);
    			switch(decision) {
    			case 1:
    				player.lastBelieves();
    				break;
    			case 2: 
    				System.out.println("Decidiste no decir nada.");
    				break;
    			}
    		}
    	}
    }
    
    
    public void attachCreencias(Personaje jugador) {
    	
    	if(jugador.getRoom().Personajes().size() > 1 ) {//Hay alguien mas	
    		for(Personaje x : jugador.getRoom().Personajes()) {
    			if(!( x.getName().equals(jugador.getName()) )) {//Guardamos todos los personajes en sus creencias.
    				jugador.setPBelieve(jugador.getRoom(), x);
    			}
    		}
    	}
    	
    	if(jugador.getRoom().Objetos().size() > 0) {
    		for(Objeto o : jugador.getRoom().Objetos()) {
    			jugador.setOBelive(jugador.getRoom(), o);
    		}
    	}
    	
    }
    
    public void attachLog(Personaje jugador, int decision, int rama) {
    	log.add(jugador + " decision " + decision + " rama " + rama);
    }

    public ArrayList<Object> getLog(){
    	return log;
    }
    
    public void muestraLog() {
		for(int i = 0; i < log.size(); i++) {
			System.out.println(log.get(i));
		}
    }
    
    public void mostrarEntorno(Personaje jugador) {
    	
    	Room hab = jugador.getRoom();
    	System.out.println("ANEXOS:");
    	hab.listAnexos();
    	System.out.println();
    	System.out.println("PERSONAJES:");
    	if(hab.Personajes().size() > 1){
    		hab.listarPreguntados(jugador);
        	System.out.println();
    	}else {
    		System.out.println("No hay.\n");
    	}
    	System.out.println("OBJETOS:");
    	if(hab.Objetos().size() > 0) {
        	hab.listObj();	
        	System.out.println();
    	}else {
    		System.out.println("No hay.\n");
    	}
    }
    
}
