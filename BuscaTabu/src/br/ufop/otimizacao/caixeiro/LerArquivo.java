package br.ufop.otimizacao.caixeiro;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LerArquivo {


	double matriz[][] = new double[10][10];
	
	
	public double[][] criaMatriz() throws IOException{
		
		FileInputStream in = new FileInputStream("/home/nathany/workspace/BuscaTabu/src/matrizDist.csv");
		InputStreamReader inputStreamReader = new InputStreamReader(in);
		BufferedReader reader = new BufferedReader(inputStreamReader);
		
		String linha = null;
		int cont = 0;
		
		while ((linha = reader.readLine()) != null) {
			String[] cidades = linha.split(",");
			for (int i = 0; i < cidades.length; i++) {
				matriz[cont][i] = Double.parseDouble(cidades[i]);
			}
			
			cont++;			
			
			
		}
		reader.close();
		
		lerMatriz(matriz);
		
		return matriz;
		
	}	
	
	public static void lerMatriz(double matriz[][]){
		System.out.println("********************************** CAIXEIRO VIAJANTE **********************************\n " + "  A\t" + "B\t" + "C\t" + "D\t" + "E\t" + "F\t" + "G\t" +"H\t" + "I\t" + "J\t" );
		for (int i = 0; i < matriz.length; i++) {
			switch (i) {
			case 0:
				System.out.print("A ");
				break;

			case 1:
				System.out.print("B ");
				break;

			case 2:
				System.out.print("C ");
				break;

			case 3:
				System.out.print("D ");
				break;

			case 4:
				System.out.print("E ");
				break;

			case 5:
				System.out.print("F ");
				break;

			case 6:
				System.out.print("G ");
				break;

			case 7:
				System.out.print("H ");
				break;

			case 8:
				System.out.print("I ");
				break;

			default:
				System.out.print("J ");
				break;
			}
			for (int j = 0; j < matriz.length; j++) {
				System.out.print(matriz[i][j] + "\t");
			}
			System.out.print("\n");
		}
		
	}
	
	

}
