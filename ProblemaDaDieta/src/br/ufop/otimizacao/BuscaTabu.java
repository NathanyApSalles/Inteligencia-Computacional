package br.ufop.otimizacao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BuscaTabu {
	
	public BuscaTabu() {}
	
	public ArrayList<Double> buscaTabu(Refeicao refeicao, int tamTabu) {
	    
        //inicializando a lista tabu
		int numIter = 5;
		ArrayList<Double> s0 = new ArrayList<>();
		s0 = (ArrayList<Double>) refeicao.getQtdAlimento().clone();
        ArrayList<Integer> tabu = new ArrayList<>(Collections.nCopies(s0.size(), 0));
        ArrayList<Double> f_opt_iter = new ArrayList<>(Collections.nCopies(numIter, 0.0));
        
        //inicializa o ótimo inicial
        Double f_opt = refeicao.getCusto();
        ArrayList<Double> s_opt = (ArrayList<Double>) s0.clone();
        ArrayList<Double> s = (ArrayList<Double>) s0.clone();
        
        for(int i = 0; i < numIter; i++){
            double maxf = Double.POSITIVE_INFINITY;
            int maxk = 0;
            ArrayList<Double> maxs = (ArrayList<Double>) s.clone();
                     
            for(int k = 0; k < s0.size(); k++){
            	int numTabu = 0;
                ArrayList<Double> sk = sortearAlimento(refeicao);
            
                double fk = calculaCusto(refeicao,sk);
                for(int j = 0; j<sk.size(); j++) {
                	if(sk.get(j)!= s.get(j)) {
                		numTabu = j;
                	}
                }
                if(tabu.get(numTabu) <= 0){
                    if(fk < maxf){
                        maxf = fk;
                        maxs = (ArrayList<Double>) sk.clone();
                        maxk = numTabu;
                        if(fk < f_opt){
                            f_opt = fk;
                            s_opt = (ArrayList<Double>) sk.clone();
                        }
                    }
                    else if(fk < f_opt){
                        maxf = fk;
                        maxs = (ArrayList<Double>) sk.clone();
                        maxk = numTabu;
                        f_opt = fk;
                        s_opt = sk;
                    }
                }
            }
            for(int j = 0; j < tabu.size(); j++){
                if(tabu.get(j) > 0)
                    tabu.set(j, tabu.get(j) - 1);
            }
            tabu.set(maxk, tamTabu);
            s = (ArrayList<Double>) maxs.clone();
            f_opt_iter.set(i, f_opt);
        }
        
        return s_opt;
			
	}
	
	

	public ArrayList<Double> sortearAlimento (Refeicao refeicao){
		ArrayList<Double> Xa = new ArrayList<>();
		double min = 0.0;
		
		Random random = new Random();
		// sortear um nutriente e um alimento 
		
		int nutriente = random.nextInt(refeicao.getQtdNutri().size());
		int alimento = random.nextInt(refeicao.getQtdAlimento().size());
		

		do{
			double qtd = random.nextDouble();

			//sortear a quantidade do alimento 
			refeicao.getQtdAlimento().set(alimento, qtd);
			
			//faz somatorio do mínimo de nutrientes necessários
			for (int j = 0; j < refeicao.getQtdNutri().get(nutriente).size(); j++) {
				
				min += refeicao.getQtdNutri().get(nutriente).get(j) * refeicao.getQtdAlimento().get(nutriente);
				
			}	
			
			
			
		}while(min <= refeicao.getQtdNutri().get(nutriente).get(alimento));//gera o sorteio enquanto a qtd mínima de nutrientes não for atingida
		
		
		Xa = (ArrayList<Double>) refeicao.getQtdAlimento().clone();
		
		return Xa;
	}
	
	public double calculaCusto(Refeicao refeicao, ArrayList<Double> Xa) {
		
		double custo = 0.0;
		
		for (int i = 0; i < refeicao.getQtdAlimento().size(); i++) {	
			
			custo += refeicao.getPreco().get(i) * Xa.get(i);
			
		}
			
		
		refeicao.setCusto(custo);
		return custo;
		
		
	}
		
		

}
