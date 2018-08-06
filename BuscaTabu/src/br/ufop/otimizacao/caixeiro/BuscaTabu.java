package br.ufop.otimizacao.caixeiro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BuscaTabu {

	public double buscaTabu(ArrayList<Integer> caminho, double matriz[][]){
		
		Caminho c = new Caminho();
		double solucaoAntiga =  c.calculaCaminho(caminho, matriz);
		double solucaoStar = solucaoAntiga; //melhor solução encontrada
		double solucaoNova = 0.0;
		
		
		int iter = 0; //contador de número de iterações
		
		int melhorIter = 0; //iteração que forneceu a melhor solução		
		
		int BTmax = 10; //número máximo de iterações sem melhora na solução
		
		ArrayList<Integer> T = new ArrayList(); //lista tabu
		ArrayList<Integer> caminhoNovo = new ArrayList<>(); //caminho depois do movimento
		ArrayList<Double> aspiracao = new ArrayList<>();
		ArrayList<Integer> melhorCaminho = new ArrayList<>();
		melhorCaminho = (ArrayList<Integer>) caminho.clone();
			
		Random random = new Random();
		//posições de troca
		int pos1 = 0;
		int pos2 = 0;
		
		aspiracao.add(solucaoStar); // iniciando a função de aspiração: salva a melhor solução encontrada até o momento
		
		while(iter - melhorIter <= BTmax){
			
			iter = iter + 1;
			pos1 = random.nextInt(caminho.size());
			do{
				pos2 = random.nextInt(caminho.size());
			}while(pos1 == pos2);
			
			
			String tabu = pos1 + "" + pos2;
			
			int movimento = Integer.parseInt(tabu);
			
			// se o movimento não está no tabu 
			if(!T.contains(movimento)){
				caminhoNovo = troca(pos1,pos2,caminho);
				
				solucaoNova = c.calculaCaminho(caminhoNovo, matriz);
					
				T.add(movimento);//atualiza lista tabu
				
				caminho = (ArrayList<Integer>) caminhoNovo.clone();
				
				solucaoAntiga = solucaoNova;				
				
				
			}else{
				
				caminhoNovo = troca(pos1,pos2,caminho);
				
				solucaoNova = c.calculaCaminho(caminhoNovo, matriz);
				//ou se atende a função de aspiração
				//a função de aspiração será: a nova solução deverá ser melhor que a melhor solução do array de aspiração
				if(solucaoNova < Collections.min(aspiracao)){					
					
					caminho = (ArrayList<Integer>) caminhoNovo.clone();
					
					solucaoAntiga = solucaoNova;
					
					//atualiza função de aspiração
					aspiracao.add(solucaoAntiga);
					
				}
			}
			
			if(solucaoAntiga < solucaoStar){
				solucaoStar = solucaoAntiga;
				melhorIter = iter;	
				melhorCaminho = (ArrayList<Integer>) caminho.clone();
			}
			
		}
		
		ArrayList<String> caminhoFinal = new ArrayList<>();
		
		c.tradutorIntString(melhorCaminho, caminhoFinal);
		
		System.out.println("\nCAMINHO FINAL: " + melhorCaminho.toString() + " -> " + caminhoFinal.toString() + " custo: " + solucaoStar);
				
		
		return solucaoStar;
		
	
	}
	public ArrayList<Integer> troca(int pos1, int pos2, ArrayList<Integer> caminho){
				
		int ind1 = caminho.get(pos1);
		int ind2 = caminho.get(pos2);
		
		caminho.set(pos1, ind2);
		caminho.set(pos2, ind1);
				
		return caminho;			
		
		
	}
	
}
