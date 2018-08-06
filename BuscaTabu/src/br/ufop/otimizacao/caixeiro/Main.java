package br.ufop.otimizacao.caixeiro;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<Integer> caminho = new ArrayList();
		Caminho c = new Caminho();
		
		double matriz [][] = new double [10][10];
		LerArquivo arq = new LerArquivo();
		
	     
        matriz = arq.criaMatriz();
       
//        caminho = geraSolucaoInicial(matriz); // solução gerada aleatoriamente 
        
        
        for (int i = 0; i < 100; i++) {
        	
        	
        	System.out.println("\n************************************************** " + i + "° EXECUÇÃO **************************************************");
        	caminho = vizinhoMaisProximo(matriz);
            
            
            double custo = c.calculaCaminho(caminho, matriz);
            
            BuscaTabu bt = new BuscaTabu();
            
            bt.buscaTabu(caminho, matriz);
			
		}
        
        
        
        
	}
	
	public static ArrayList<Integer> geraSolucaoInicial(double [][] matriz){
		ArrayList<Integer> caminho = new ArrayList<>();
		
		Random rand = new Random();
		
		int v0 = 0;
		
		for (int i = 0; i < 10; i++) {
			v0 = rand.nextInt(10);
			while(caminho.contains(v0)){
				v0 = rand.nextInt(10);
			}
			caminho.add(v0);
			
		}
		
		System.out.println("\nCaminho gerado: " + caminho.toString());
		

		return caminho;			
		
	}
	
	

	public static int menor(double [][] matriz, ArrayList<Integer> caminho,int v){
		double custo = 99;
		int menor = 0;
		for (int i = 0; i < 10; i++) {
			if(i!= v && !caminho.contains(i)){
				if(matriz[v][i] < custo){
					custo = matriz[v][i];
					menor = i;	
					
				}
				
			}
		}
		
		return menor;
		
	}
	public static ArrayList<Integer> vizinhoMaisProximo(double [][] matriz){
		ArrayList<Integer> caminho = new ArrayList<>();
		
		Random rand = new Random();
		
		int v0 = rand.nextInt(10);
		
		caminho.add(v0);
		
		int v = 0;
		int u = 0;
		double custo = 0.0;
		
		v = v0;
		
		while(caminho.size()<10){
			u = menor(matriz, caminho, v);
			caminho.add(u);
			custo = custo + matriz[v][u];			
			v = u;		
			
		}
		custo = custo + matriz[caminho.get(caminho.size()-1)][caminho.get(0)];
		Caminho c = new Caminho();
		ArrayList<String> caminhoFinal = new ArrayList<>();
		c.tradutorIntString(caminho, caminhoFinal);
		
		System.out.println("\nCaminho inicial: " + caminho.toString() + " -> " + caminhoFinal.toString() + " custo: " + custo);
		
		
		return caminho;
		
	}

}
