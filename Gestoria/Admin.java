package Gestoria;

import java.util.ArrayList;
import java.util.Scanner;
import Entidades.*;

public abstract class Admin {

	Scanner input = new Scanner(System.in);

    public abstract void Start();
    
    //Basic algorithm methods  
    public int showOptions(Personaje Jugador) {
        
        if(!Jugador.getIA()) {//-------------- MECANISMO PLAYER.
            
            if(Jugador.hasObj()) {//Tiene objeto equipado
                
                if(Jugador.getRoom().Personajes().size() > 1){//Hay mas Personajes que el Jugador que esta de turno.
                    System.out.println(Jugador.getName() + " que accion deseas realizar?\n");
                    System.out.println("1. Moverse  2. Soltar Objeto  3. Preguntar 4. Dar/Pedir Objeto 5. Nada\n");
                    return 1;
                    
                }else{//Esta solo en la habitacion.
                    System.out.println(Jugador.getName() + " que accion deseas realizar?\n");
                    System.out.println("1. Moverse  2. Soltar Objeto 3. Nada\n");
                    return 2;
                }
                
            } else {//No tiene objeto
             
                if(Jugador.getRoom().Objetos().size() > 0 ) {//Hay Objeto en la sala.
                    
                	if(Jugador.getRoom().Personajes().size() > 1) {//Hay Personaje en la sala.
                        System.out.println(Jugador.getName() + " que accion deseas realizar?\n");
                        System.out.println("1. Moverse  2. Tomar Objeto  3. Preguntar 4. Dar/Pedir Objeto 5. Nada\n");
                        return 3;
                        
                    }else{//Esta solo en la sala.
                        System.out.println(Jugador.getName() + " que accion deseas realizar?\n");
                        System.out.println("1. Moverse  2. Tomar Objeto 3. Nada\n");
                        return 4;
                    }
                    
                }else {//No hay Objeto en la sala.
                    
                	if(Jugador.getRoom().Personajes().size() > 1) {//Hay mas Personajes en la sala.
                        System.out.println(Jugador.getName() + " que accion deseas realizar?\n");
                        System.out.println("1. Moverse  2. Preguntar 3. Dar/Pedir Objeto 4. Nada\n");
                        return 5;
                   
                	}else{//Esta solo en la sala.
                    	System.out.println(Jugador.getName() + " que accion deseas realizar?\n");
                        System.out.println("1. Moverse 2. Nada\n");
                        return 6;
                    }
                }
            }
        }else {//----------------- MECANISMO IA.
        	
            if(Jugador.hasObj()) {//Tiene objeto equipado
                
                if(Jugador.getRoom().Personajes().size() > 1 ) {//Hay mas Personajes que el Jugador que esta de turno.
                    return 1;
                }else {//Esta solo en la habitacion.
                    return 2;
                }
                
            }else {//No tiene objeto
                
                if(Jugador.getRoom().Objetos().size() > 0 ) {//Hay Objeto en la sala.
                	
                    if(Jugador.getRoom().Personajes().size() > 1) {//Hay Personaje en la sala.
                        return 3;
                    }else {//Esta solo en la sala.
                        return 4;
                    }
                    
                }else {//No hay Objeto en la sala.
                    
                	if(Jugador.getRoom().Personajes().size() > 1) {
                    //Hay mas Personajes en la sala.
                        return 5;
                    }else {
                    //Esta solo en la sala.
                        return 6;
                    }
                }
            }
        }
    }
    
    public void takeOption(Personaje Jugador) {
        int decision = 0;
        int rama = 0;
        
        if(!Jugador.getIA()){//Es el jugador fisico.         
        	rama = showOptions(Jugador);    
            decision = input.nextInt();         
            doOption(Jugador, decision, rama);
        }else {
            //Es el jugador IA.
            rama = showOptions(Jugador);
            //si escogio una rama par, entonces solo hay 2 opciones, si le toca impar tiene siempre 3 opciones.
            switch(rama) {
            case 1:
            	decision = Jugador.chooseIA(4); 
            	doOption(Jugador, decision, rama);
                break;
            case 2:
            	decision = Jugador.chooseIA(3); 
            	doOption(Jugador, decision, rama);
            	break;
            case 3:
            	decision = Jugador.chooseIA(5);
            	doOption(Jugador, decision, rama);
            	break;
            case 4:
            	decision = Jugador.chooseIA(3);
            	doOption(Jugador, decision, rama);
            	break;
            case 5:
            	decision = Jugador.chooseIA(3);
            	doOption(Jugador, decision, rama);
            	break;
            case 6:
            	decision = Jugador.chooseIA(2);
            	doOption(Jugador, decision, rama);
            	break;
            default:System.err.println("Esa decision no es valida, intente de nuevo. linea 111 Admin.java\n");
                break;
            }
        }
    }
    
    public void doOption(Personaje Jugador, int decision, int rama) {
        switch(rama) {
        case 1:
            //Tiene objeto y hay mas jugadores en la sala.
            switch(decision) {
            case 1: 
                //Moverse de habitacion. 
            	System.out.println("MOVE :1");
            	move(Jugador);
            	break;
            case 2: 
                //Soltar Objeto.
            	System.out.println("DROP :1");
            	drop(Jugador);
            	break;
            case 3: 
                //Preguntar a otro Jugador.
            	//ask(Jugador);
            	break;
            case 4:
            	give(Jugador);
            	break;
            case 5:
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
            	System.out.println("MOVE :2");
            	move(Jugador);
            	break;
            case 2:
                //Soltar Objeto.
            	System.out.println("DROP :2");
            	drop(Jugador);
                break;
            case 3:System.out.println("No hiciste nada.");
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
            	System.out.println("MOVE :3");
            	move(Jugador);
            	break;
            case 2:
                //Tomar Objeto.
            	System.out.println("TAKE : 3");
            	take(Jugador);
            	break;
            case 3:
                //Preguntar a otro Jugador.
            	//ask(Jugador);
                break;
            case 4:
            	System.out.println("GIVE : 3");
            	give(Jugador);
            	break;
            case 5:
                //no hacer nada
            	System.out.println("No hiciste nada.");
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
            	System.out.println("MOVE :4");
            	move(Jugador);
            	break;
            case 2:
                //Tomar Objeto.
            	System.out.println("TAKE :4");
            	take(Jugador);
            	break;
            case 3:
            	System.out.println("No hiciste nada.");
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
            	System.out.println("MOVE :5");
            	move(Jugador);
            	break;
            case 2:
                //Preguntar a otro Personaje.
            	//ask(Jugador);
                break;
            case 3:
            	System.out.println("GIVE :5");
            	give(Jugador);
                break;
            case 4: 
            	System.out.println("No hiciste nada.");
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
            	System.out.println("MOVE : 6");
            	move(Jugador);
            	break;
            case 2:
            	System.out.println("No hiciste nada.");
                break;
            default: System.err.print("ERROR: opcion no valida N251\n");
                break;
            }
            break;
        default: System.err.print("ERROR: opcion no valida N255\n");
            break;//Fin del switch rama;
        }
    }
    
    public void Round(Personaje Jugador) {
    	takeOption(Jugador);
    	Jugador.setTurno(true);
    }
    
    public void newRound(ArrayList<Personaje> personajes) {
    	for(Personaje x : personajes) {
    		x.setTurno(false);
    	}
    }
    
    //AdminMethods();
    public void move(Personaje Jugador){
       
    	Room next = null;
    	int opciones;
    	
    	if(Jugador.getIA()) {//------------------- Mecanismo IA
    		
    		if(Jugador.finalObj()) {//tiene el objeto final.
    			
    			if(Jugador.getRoom().Anexos().contains(Jugador.getFinalRoom())){//Esta la habitacion final anexa a la habitacion actual.
    				
    				next = Jugador.getRoom().Anexos().get(Jugador.getRoom().Anexos().indexOf(Jugador.getFinalRoom())); //Asigna la habitacion final.
    				Jugador.setRoom(next);
    				//Debug
    	    		System.out.println(Jugador.getName() + " se movio a " + Jugador.getRoom().getName());
    			
    			}else {//Se mueve aleatoriamente hasta acercarse a su habitacion final.
    	    		
    				opciones = Jugador.getRoom().Anexos().size();
    	    		next = Jugador.getRoom().Anexos().get(Jugador.chooseIA(opciones)-1);
    	    		Jugador.setRoom(next);
    	    		//Debug
    	    		System.out.println(Jugador.getName() + " se movio a " + Jugador.getRoom().getName());
    			}
    		}else {//no tiene el objeto final.
    			
    			if(Jugador.getRoom().Objetos().contains(Jugador.getFinalObj())) {//No hace nada, se queda hasta tomar el Objeto.
    			}else {															 //No esta el objeto, entonces se mueve para buscarlo.
    				opciones = Jugador.getRoom().Anexos().size();
    	    		next = Jugador.getRoom().Anexos().get(Jugador.chooseIA(opciones)-1);
    	    		Jugador.setRoom(next);
    	    		//Debug
    	    		System.out.println(Jugador.getName() + " se movio a " + Jugador.getRoom().getName());
    			}
    		}
    		
    	}else {//------------------ Mecanismo Player

    		System.out.println("Ir a:\n");
    		do {
        		Jugador.getRoom().listAnexos();
    			opciones = input.nextInt()-1;
    		}while(opciones > Jugador.getRoom().Anexos().size()-1);
    		
			next = Jugador.getRoom().Anexos().get(opciones);
    		Jugador.setRoom(next);  		
    	}
    	
    }
    
    public void take(Personaje Jugador) {
    	
    	Objeto take;
    	int decision, opciones;
    	
    	if(Jugador.getIA()){//------------- Mecanismo IA.
    		
    		if(Jugador.getRoom().Objetos().contains(Jugador.getFinalObj())){//esta el Objeto final.
    		
    			take = Jugador.getRoom().Objetos().get(Jugador.getRoom().Objetos().indexOf(Jugador.getFinalObj())); //asigna el objeto final.
    			Jugador.setObj(take);
	    		//Debug
	    		System.out.println(Jugador.getName() + " tomo " + Jugador.getObj().getName());
    			
    		}else{//no esta el Objeto final.
    			
    			decision = Jugador.chooseIA(1);				//Se pregunta si quiere tomar algun objeto o no.
    			if(decision == 1) {
    				opciones = Jugador.getRoom().Objetos().size();
    				take = Jugador.getRoom().Objetos().get(Jugador.chooseIA(opciones)-1);
    				Jugador.setObj(take);
    				//Debug
    	    		System.out.println(Jugador.getName() + " tomo " + Jugador.getObj().getName());
        		}
    		}
    		
    	}else {//------------ Mecanismo Player.

    		System.out.println("Cual quieres tomar?");
			do {
				Jugador.getRoom().listObj();
        		opciones = input.nextInt()-1;
    		}while(opciones > Jugador.getRoom().Objetos().size()-1);

    		take = Jugador.getRoom().Objetos().get(opciones);

	    	if(Jugador.hasObj()) {
	    		
	    		System.out.println("Parece que ya tienes un objeto ("+Jugador.getObj().getName()+"), deseas tirarlo por este?  --> " + take.getName());
	    		do {
	    			System.out.println("1. Si || 2. No");
		    		decision = input.nextInt();
	    		}while(decision > 2);
	    		
	    		if(decision == 1) {
	    			Jugador.setObj(take);
			    	System.out.println("Has adquirido " + Jugador.getObj().getName());
	    		}else {
	    			System.out.println("Decidiste no hacer nada.");
	    		}
	    		
	    	}else{
	    		Jugador.setObj(take);
		    	System.out.println("Has adquirido " + Jugador.getObj().getName());
	    	}
	    }
    }
       
    public void drop(Personaje Jugador) {
    	
    	if(Jugador.getIA() && Jugador.hasObj()) {//IA y tiene objeto.
    		if(Jugador.finalObj() && Jugador.getFinalObj().getName() != Jugador.getObj().getName()) {//Si no es el Objeto final.
    			//Debug
    			String dropped = Jugador.getObj().getName();
    			System.out.println("Has soltado " + dropped);	
    		
    			Jugador.getObj().setRoom(Jugador.getRoom());
    		}
   
    	}else if(Jugador.getIA() == false && Jugador.hasObj()){//Player y tiene objeto.
    		String dropped = Jugador.getObj().getName();
    		Jugador.getObj().setRoom(Jugador.getRoom());
        	System.out.println("Has soltado " + dropped);	
    	}
    }
    
    public void give(Personaje Jugador) {

    	Personaje player;
    	int rand, opciones, decision;
    	
    	if(Jugador.getIA() && Jugador.hasObj()) {//-------- Mecanismo IA.
    		
    		if(!Jugador.finalObj()) {//no tiene el objeto final.
    			if(Jugador.getRoom().Personajes().size() > 0) {//hay mas personajes en la room.

    				opciones = Jugador.getRoom().Personajes().size();
    				player = Jugador.getRoom().Personajes().get(Jugador.chooseIA(opciones)-1);
    				
    				if(player.getIA()){//es la IA.
    				
    					rand = (int) (Math.random() * 1) + 1;
    					if(rand == 1){
    						Jugador.giveObj(player);
    						System.out.println(Jugador.getName() + " le dio " + player.getObj().getName() + " a " + player.getName());
    	    			}
    				
    				}else if(!player.getIA()){//es el Player.
    					
    					System.out.println(Jugador.getName() + " quiere darte su objeto, que dices? ---> (" +Jugador.getObj().getName()+")");
    					do {
        					System.out.println("1. Si acentuado || 2. No");
    						decision = input.nextInt();
    					}while (decision > 2);
    					
    					if(decision == 1){
    						Jugador.giveObj(player);
    					}
    				}			
    			}
    		}
    		
    	}else if(!Jugador.getIA() && Jugador.hasObj()) {//------- Mecanismo Player.
    		
    		System.out.println("A quien le quieres dar tu objeto?");
    		do {
    			Jugador.getRoom().listPersonajes();
        		opciones = input.nextInt()-1;
    		}while(opciones > Jugador.getRoom().Personajes().size()-1);
    		
    		player = Jugador.getRoom().Personajes().get(opciones);

    		rand = (int) (Math.random() * 1) + 1;
    		if(rand == 1) {
    			Jugador.giveObj(player);
    			System.out.println("Le has dado "+ player.getObj().getName()+" a "+player.getName());
    		}else {
    			System.out.println(player.getName() + "no quiso tu Objeto.");
    		}
    	}
    }

    public void demand(Personaje Jugador){
    	
    	Personaje player;
    	ArrayList<Personaje> personajes = Jugador.getRoom().Personajes();
    	
    	int decision, rand;
    	boolean done = false;
    	
    	if(Jugador.getIA()) {//------------ Mecanismo IA.	
    		if(!Jugador.finalObj()) {//no tiene el Objeto Final.
    		
    			for(Personaje Player : personajes) {
    				
    				if(Player.hasObj() && !done) {
        				if(Player.getObj().getName() == Jugador.getFinalRoom().getName()){//Tiene el Objeto Final.
    						if(Player.getIA()) {//es la IA.
    							
        						rand = (int) (Math.random() * 1) + 1;
        						System.out.println("Valores del rand:"+rand);
        						if(rand == 1) {
        							Player.giveObj(Jugador);
            						System.out.println(Player.getName() + " le dio ("+Jugador.getObj().getName()+") a " + Jugador.getName());
        							done = true;
        						}
        					
    						}else{//es el Player.
        						System.out.println(Jugador.getName() + " te esta pidiendo tu objeto ("+Player.getObj().getName()+"), que dices?");
        						do {
            						System.out.println("1. Si acentuado || 2. No");
        							decision = input.nextInt();
        						}while(decision > 2);
        						
        						if(decision == 1) {
        							Player.giveObj(Jugador);
            						System.out.println(Player.getName() + " le diste ("+Jugador.getObj().getName()+") a " + Jugador.getName());
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
        		Jugador.getRoom().listPersonajes();
    			decision = input.nextInt()-1;
    		}while(decision > Jugador.getRoom().Personajes().size()-1);
    		
			player = Jugador.getRoom().Personajes().get(decision);
    		
    		if(player.hasObj()) {
    			rand = (int) (Math.random() * 1) + 1;
        		if(rand == 1) {
        			player.giveObj(Jugador);
        			System.out.println(player.getName() + " te ha dado " + Jugador.getObj().getName());
        		}else {
        			System.out.println(player.getName() + " prefirio no darte su Objeto.");
        		}
    		}else {
    			System.out.println(player.getName() + " no tiene Objeto.");
    		}
    		
    	}
    }
    
    public void ask(Personaje Jugador) {
    	
    }

}
