package br.ufop.otimizacao;

import java.util.ArrayList;

public class ILS {

	public ILS(){}
	
	public void ils(Refeicao refeicao, int numIter){
				
		Refeicao s0 = (Refeicao) refeicao.clone1(); // solução inicial 
		
		BuscaTabu bt = new BuscaTabu();
		ArrayList<Double> buscaTabu = new ArrayList<>();
		
		buscaTabu = bt.buscaTabu(s0, 10); // busca local
			    
		Refeicao s1 = s0;
		s1.setQtdAlimento(buscaTabu);
		s1.minCusto();
		
		int i = 0;
		
		while(i < numIter) { //enquanto não satisfeito o critério de parada 
			
			//pertubação
			ArrayList<Double> perturbacao = bt.sortearAlimento(s1);
			
			Refeicao s2 = (Refeicao) refeicao.clone1();
			
			s2.setQtdAlimento(perturbacao);
			
			//busca local
			s2.setQtdAlimento(bt.buscaTabu(s2, 10));
			
			s2.minCusto();
			
			//criteirio de aceitação 
			if(s2.custo < s1.custo) {
				s1 = (Refeicao) s2.clone1();
				s1.minCusto();
			}
			
			i++;
		}
		
        System.out.println("********** SOLUÇÃO ILS **********");
        System.out.println("Quantidade de cada alimento a ser comprado: " + s1.getQtdAlimento());
        System.out.println("Custo da compra: " + s1.getCusto());
        System.out.println("*****************************************\n");					
	}

}
