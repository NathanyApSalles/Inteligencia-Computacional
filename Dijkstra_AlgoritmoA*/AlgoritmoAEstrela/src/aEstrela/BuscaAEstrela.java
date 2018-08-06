package aEstrela;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class BuscaAEstrela {

	public static double [][]MAPA = new double [100][100];
	
	/*
	 * Retorna uma matriz de inteiros com as dist�ncias que correspondem ao mapa lido.
	 * */
	public static double[][] leMapa(){
		double [][]mapa = new double [100][100];
		try {
			Scanner scanner = new Scanner(new FileReader("relevo.txt")).useDelimiter("\\t|\\n");
			int i = 0;
			double valor;
				while(scanner.hasNext()){
				for(int j = 0; j < 100; j++){
					String aux = scanner.next();
					valor = Double.parseDouble(aux);
						mapa[i][j] = valor;
					}			
				
				scanner.next();
				i++;
			
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Arquivo n�o encontrado.");
			e.printStackTrace();
		}
		
		
		return mapa;	
	}
	/*
	 * Imprime a matriz mapa.
	 * */
	public static void imprimeMapa(){
		for(int i = 0; i < 100; i++){
			for(int j = 0; j < 100; j++){
				System.out.print("	"+MAPA[i][j]);
			}
			System.out.println("");
		}
	}
	
	// Heur�stica utilizada: distancia Manhattan.
	/*
	 * Retorna a dist�ncia Manhattan entre dois n�s.
	 * */
	public static double distanciaManhattan(No n0, No n1){
		return Math.abs(n1.getX()-n0.getX()) + Math.abs(n1.getY()-n0.getY());
	}
	
	/*
	 * Retorna a lista de vizinhos de um n�.
	 * */
	public static ArrayList<No> listaVizinhos(No n){
		ArrayList<No> lista = new ArrayList<No>();
		int x = n.getX();
		int y = n.getY();
		if(x+1 < 100){
			n = new No(x+1, y);
			lista.add(n);
		}
		if(x-1 >= 0){
			n = new No(x-1, y);
			lista.add(n);
		}
		if(y+1 < 100){
			n = new No(x, y+1);
			lista.add(n);
		}
		if(y-1 >= 0){
			n = new No(x, y-1);
			lista.add(n);
		}
		if(y-1 >= 0 && x-1 >= 0){
			n = new No(x-1, y-1);
			lista.add(n);
		}
		if(y+1 < 100 && x+1 < 100){
			n = new No(x+1, y+1);
			lista.add(n);
		}
		if(x-1 >= 0 && y+1 < 100){
			n = new No(x-1, y+1);
			lista.add(n);
		}
		if(x+1 < 100 && y-1 >= 0){
			n = new No(x+1, y-1);
			lista.add(n);
		}
		return lista;
	}
	
	/*
	 * Retorna distancia entre dois n�s.
	 * */
	public static double distancia(No n0, No n1){
		return Math.sqrt(Math.pow(0.1*(n0.getX()-n1.getX()),2)+Math.pow(0.1*(n0.getY()-n1.getY()),2)+Math.pow((MAPA[n1.getX()][n1.getY()] - MAPA[n0.getX()][n0.getY()]),2));
	}
	
	/*
	 * Retorna o resultado da busca A* de um n� inicial n0 at� um n� final n1.
	 * */
	public static ResultadoBusca buscaAEstrela(No n0, No n1){
		ArrayList<No> nosVisitados = new ArrayList<No>();
		ArrayList<No> nosExpandidos = new ArrayList<No>();
		nosExpandidos.add(n0); // inicialmente, no inicial � o unico expandido
		
		No vizinho;
		ArrayList<No> vizinhos;
		No [][]caminho = new No [100][100];
		
		
		n0.setCustoF(distanciaManhattan(n0, n1)); // Custo do ponto inicial � o custo heur�stico
		n0.setCustoG(0); // Custo para chegar no n� inicial � zero
		ResultadoBusca resultado = new ResultadoBusca();
				
		while(!nosExpandidos.isEmpty()){
			No atual = nosExpandidos.get(0); // Pego o no com menor custo f(n)
			nosExpandidos.remove(0);
			nosVisitados.add(atual);
		
			if(atual.getX() == n1.getX() && atual.getY() == n1.getY()){ // no atual � o destino
				resultado.setCustoTotal(atual.getCustoG());
				resultado.setCaminhoPercorrido(calculaCaminho(n0, n1, caminho));
				resultado.setCaminhoVisitado(nosVisitados);
				return resultado;
			}
			vizinhos = listaVizinhos(atual);
		
			while(!vizinhos.isEmpty()){
				vizinho = vizinhos.get(0);
				vizinhos.remove(0);
				if(nosVisitados.contains(vizinho)) // J� existe um caminho at� vizinho (que � menor)
					continue;
				if(!nosExpandidos.contains(vizinho)){
					vizinho.setCustoG(Integer.MAX_VALUE); // Ainda nao tinha sido encontrado, custo infinito
					nosExpandidos.add(vizinho);
				}
				double custoGVizinho = atual.getCustoG() + MAPA[vizinho.getX()][vizinho.getY()];
				if(custoGVizinho >= vizinho.getCustoG())
					continue; // J� existe outro caminho que � melhor
				
				// Este caminho � o melhor encontrado at� agora
				caminho[vizinho.getX()][vizinho.getY()] = atual; // Salvo de onde eu vim para chegar nele
				vizinho.setCustoG(atual.getCustoG() + distancia(atual, vizinho));
				vizinho.setCustoF(atual.getCustoG() + distancia(atual, vizinho) + distanciaManhattan(vizinho, n1));
				Collections.sort(nosExpandidos, new ComparaNo());
			}
		}
		System.out.println("Ocorreu um erro!");
		return null;
	}
	
	/*
	 * Retorna um ArrayList de N� contendo apenas os n�s que fazem parte do percurso
	 * que possui o menor custo. 
	 * */
	public static ArrayList<No> calculaCaminho(No inicio, No fim, No[][] caminho){
		int x = fim.getX();
		int y = fim.getY();
		No seguinte = caminho[x][y];
		ArrayList<No> caminhoPercorrido = new ArrayList<No>();
		caminhoPercorrido.add(fim);
		while((caminho[x][y]!=null)){
			x = seguinte.getX();
			y = seguinte.getY();
			caminhoPercorrido.add(0,seguinte); // adiciona sempre no inicio, para ficar na ordem inicio -> final
			System.out.println("("+x+","+y+") -> "+  MAPA[x][y]);
			if(caminho[x][y]!=null) // Evitar null pointer
				seguinte = caminho[x][y];
		}
		return caminhoPercorrido;
	}
	
	/*
	 * Imprime os n�s na ordem em que s�o visitados.
	 * */
	public static void imprimeVisitado(ResultadoBusca menor){
		while(!menor.getCaminhoVisitado().isEmpty()){
			No aux = menor.getCaminhoVisitado().get(0);
			menor.getCaminhoVisitado().remove(0);
			if(aux.getX() == -1 && aux.getY() == -1){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String []args){
		
		try {
			MAPA = leMapa();
			
			No inicio = new No(0,0); // 
			No fim = new No(99,99); // 
			
			// Caminho 
			
			ResultadoBusca c1r1 = buscaAEstrela(inicio, fim);			
			
			ResultadoBusca c1Final = new ResultadoBusca();
			c1Final.setCustoTotal(c1r1.getCustoTotal());
			
			ArrayList<No> caminhoTotal = new ArrayList<No>();

			caminhoTotal.addAll(c1r1.getCaminhoPercorrido());
			caminhoTotal.remove(caminhoTotal.size()-1); // Retirar posi��o repetida
			c1Final.setCaminhoPercorrido(caminhoTotal);
			
			caminhoTotal = new ArrayList<No>();
			No n = new No(-1,-1); // Apenas para marcar fim de um caminho e definir a��es espec�ficas na hora de imprimir a solu��o
			caminhoTotal.addAll(c1r1.getCaminhoVisitado());
			caminhoTotal.add(n);
					
			System.out.println("Resultados:\n ");
			System.out.println("Custo: " + c1Final.getCustoTotal());
			

			ResultadoBusca []resultados = new ResultadoBusca[6];
			resultados[0] = c1Final;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


