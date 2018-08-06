package Ag_geradores;

import java.util.ArrayList;
import java.util.Collections;


public class Problema {
	ArrayList<Integer> DEMANDAS = new ArrayList<Integer>(){
		{
			add(150);
			add(20);
			add(40);
			add(220);
			add(60);
			add(85);
			add(92);
			add(12);
			add(25);
			add(110);
			add(155);
			add(23);
			add(42);
			add(226);
			add(62);
			add(91);
			add(87);
			add(13);
			add(125);
			add(112);
			
		}
	};
    
    public void calcularFuncaoObjetivo(Individuo individuo) {
        
    
    	int index = 0;
    	double peso = 0.0;
    	
    	Collections.shuffle(DEMANDAS);
    	
    	while(individuo.getPeso() < individuo.getCapacidade()){
    	
    		peso += DEMANDAS.get(index);
    		individuo.getCargas().add(DEMANDAS.get(index));
    		individuo.setPeso(peso);
    		
    		index++;
    	 }
       	
    	
    	
        individuo.setFuncaoObjetivo(individuo.getPeso());
        
    }
    
   
    
}
