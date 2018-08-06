package Ag_geradores;


import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;


public class AGReal {
	
	

    public static void main(String[] args) {
    	
    	
    	ArrayList<Double> result1 = new ArrayList<>();
    	
    	
        Problema problema = new Problema();

        Integer tamanho = 50;
        Double pCrossover = 0.8;
        Double pMutacao = 0.05;
        Integer geracoes = 50;
        
      

        AlgoritmoGenetico ag = new AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problema);
      
        
       
		result1.add(ag.executar(6));

        System.out.println("torneio binário | elitismo");
        System.out.println("Melhor custo: " + result1.toString());
        
        
        
        
    }
    
}
