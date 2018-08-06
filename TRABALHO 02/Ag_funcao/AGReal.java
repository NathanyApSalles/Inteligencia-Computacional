package Ag_funcao;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;


public class AGReal {
	
	

    public static void main(String[] args) {
    	
    	ArrayList<Integer> REPETICOES = new ArrayList<>();
    	ArrayList<Double> result1 = new ArrayList<>();
    	
    	ArrayList<Double> result2 = new ArrayList<>();
    	
    	ArrayList<Double> result3 = new ArrayList<>();
    	
    	ArrayList<Double> result4 = new ArrayList<>();
    	
    	ArrayList<Double> result5 = new ArrayList<>();
    	
    	ArrayList<Double> result6 = new ArrayList<>();
    	
        Problema problema = new Problema();

        Integer tamanho = 50;
        Double pCrossover = 0.8;
        Double pMutacao = 0.05;
        Integer geracoes = 50;
        
        Double minimo = 0.0;
        Double maximo = 10.0;

        AlgoritmoGenetico ag = new AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problema, minimo, maximo);
      
        
        for (int i = 0; i < 60; i++) {
        	REPETICOES.add(i);
			
		}
        
        Collections.shuffle(REPETICOES);
        System.out.println(REPETICOES.toString());
        for (int i = 0; i < REPETICOES.size(); i++) {
        	System.out.println("i: " + i + "repetição: " + REPETICOES.get(i));
			if(REPETICOES.get(i) < 10){
				//torneio binário | roleta
				
//				for (int j = 0; j < ag.executar(1).size(); j++) {
					result1.add(ag.executar(1));
//				}
//				Collections.sort(result1);
//				max1.add(result1.get(0));
//				min1.add(result1.get(result1.size()-1));
			}
			if(REPETICOES.get(i) > 10 && REPETICOES.get(i) < 20){
				 //uniforme | roleta
				result2.add(ag.executar(2));
//				Collections.sort(result2);
//				max2.add(result2.get(0));
//				min2.add(result2.get(result2.size()-1));
				
			} 
			if(REPETICOES.get(i) > 20 && REPETICOES.get(i) < 30){
				result3.add(ag.executar(3)); //roleta | roleta
//				Collections.sort(result3);
//				max3.add(result3.get(0));
//				min3.add(result3.get(result3.size()-1));
			}
			if(REPETICOES.get(i) > 30 && REPETICOES.get(i) < 40){
				result4.add(ag.executar(4)); //uniforme | uniforme
//				Collections.sort(result4);
//				max4.add(result4.get(0));
//				min4.add(result4.get(result4.size()-1));
			}
			if(REPETICOES.get(i) > 40 && REPETICOES.get(i) < 50){
				result5.add(ag.executar(5)); //torneio binário | torneio binário
//				Collections.sort(result5);
//				max5.add(result5.get(0));
//				min5.add(result5.get(result5.size()-1));
			}
			if(REPETICOES.get(i) > 50 && REPETICOES.get(i) < 60){
				result6.add(ag.executar(6)); //torneio binário | elitismo
//				Collections.sort(result6);
//				max6.add(result6.get(0));
//				min6.add(result6.get(result6.size()-1));
			}
		}
        
        Collections.sort(result1);
        Collections.sort(result2);
        Collections.sort(result3);
        Collections.sort(result4);
        Collections.sort(result5);
        Collections.sort(result6);
        
        System.out.println("torneio binário | roleta");
        System.out.println("Melhor custo: " + result1.get(0));
        System.out.println("Pior custo: " + result1.get(result1.size()-1));
        System.out.println("uniforme | roleta");
        System.out.println("Melhor custo: " + result2.get(0));
        System.out.println("Pior custo: " + result2.get(result2.size()-1));
        
        System.out.println("roleta | roleta");
        System.out.println("Melhor custo: " + result3.get(0));
        System.out.println("Pior custo: " + result3.get(result3.size()-1));
        
        System.out.println("uniforme | uniforme");
        System.out.println("Melhor custo: " + result4.get(0));
        System.out.println("Pior custo: " + result4.get(result4.size()-1));
        
        System.out.println("torneio binário | torneio binário");
        System.out.println("Melhor custo: " + result5.get(0));
        System.out.println("Pior custo: " + result5.get(result5.size()-1));
        
        System.out.println("torneio binário | elitismo");
        System.out.println("Melhor custo: " + result6.get(0));
        System.out.println("Pior custo: " + result6.get(result6.size()-1));
        
        
        
        
    }
    
}
