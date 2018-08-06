package br.ufop.otimizacao;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static Scanner in;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean sair = false;
		
		while(!sair){
			System.out.println("----------------- DIETA ------------------\n");
			System.out.println("Escolha sua refeição: \n");
			System.out.println("1 - Café da Manhã");
			System.out.println("2 - Almoço");
			System.out.println("3 - Lanche");
			System.out.println("4 - Sair");
			
			in = new Scanner(System.in);
	        int opcao = in.nextInt();
	        
	        switch(opcao) {
	        	case 1:{
	        		ArrayList<Double> preco = new ArrayList<>();
	        		
	        		preco.add(5.0); 	
	        		preco.add(3.0); 	
	        		preco.add(5.50);	
	        		preco.add(15.20);
	        		
	        		ArrayList<ArrayList<Double>> qtdNutri = new ArrayList<ArrayList<Double>>();
	        		ArrayList<Double> nutri1 = new ArrayList<>();
	        		nutri1.add(4.0);
	        		nutri1.add(2.0);
	        		nutri1.add(3.0);
	        		nutri1.add(10.0);
	        		
	        		ArrayList<Double> nutri2 = new ArrayList<>();
	        		nutri2.add(20.0);
	        		nutri2.add(10.0);
	        		nutri2.add(5.0);
	        		nutri2.add(22.0);
	        		
	        		ArrayList<Double> nutri3 = new ArrayList<>();
	        		nutri3.add(40.0);
	        		nutri3.add(35.0);
	        		nutri3.add(5.0);
	        		nutri3.add(40.0);
	        		
	        		qtdNutri.add(nutri1);
	        		qtdNutri.add(nutri2);
	        		qtdNutri.add(nutri3);
	        		
	        		
	        		ArrayList<Double> minNutri = new ArrayList<>();
	        		minNutri.add(8.0);
	        		minNutri.add(10.0);
	        		minNutri.add(50.0);
	        		
	        		Refeicao refeicao = new Refeicao(0, minNutri , qtdNutri, preco);
	        				
	        		refeicao.minNutrientes();
	        		
	        		ILS ils = new ILS();
	        		int numIter = 100;
	        		ils.ils(refeicao, numIter);
	        		
	        	}
	        	break;
	        	
	        	case 2: {
	        		ArrayList<Double> preco = new ArrayList<>();
	        		
	        		preco.add(4.0);		
	        		preco.add(2.0);		
	        		preco.add(25.50);	
	        		preco.add(19.75);	
	        		
	        		ArrayList<ArrayList<Double>> qtdNutri = new ArrayList<ArrayList<Double>>();
	        		ArrayList<Double> nutri1 = new ArrayList<>();
	        		nutri1.add(2.0);
	        		nutri1.add(2.0);
	        		nutri1.add(10.0);
	        		nutri1.add(20.0);
	        		
	        		ArrayList<Double> nutri2 = new ArrayList<>();
	        		nutri2.add(50.0);
	        		nutri2.add(20.0);
	        		nutri2.add(10.0);
	        		nutri2.add(30.0);
	        		
	        		ArrayList<Double> nutri3 = new ArrayList<>();
	        		nutri3.add(80.0);
	        		nutri3.add(70.0);
	        		nutri3.add(10.0);
	        		nutri3.add(80.0);
	        		
	        		qtdNutri.add(nutri1);
	        		qtdNutri.add(nutri2);
	        		qtdNutri.add(nutri3);
	        		
	        		
	        		ArrayList<Double> minNutri = new ArrayList<>();
	        		minNutri.add(11.0);
	        		minNutri.add(70.0);
	        		minNutri.add(250.0);
	        		
	        		Refeicao refeicao = new Refeicao(1, minNutri , qtdNutri, preco);
	        				
	        		refeicao.minNutrientes();
	        		
	        		ILS ils = new ILS();
	        		int numIter = 100;
	        		ils.ils(refeicao, numIter);
	        		
	        	}
	        	break;
	        	
	        	case 3: {
	        		ArrayList<Double> preco = new ArrayList<>();
	        		
	        		preco.add(6.0);
	        		preco.add(5.0);
	        		preco.add(10.00);
	        		preco.add(1.75);
	        		
	        		ArrayList<ArrayList<Double>> qtdNutri = new ArrayList<ArrayList<Double>>();
	        		ArrayList<Double> nutri1 = new ArrayList<>();
	        		nutri1.add(5.0);
	        		nutri1.add(6.0);
	        		nutri1.add(10.0);
	        		nutri1.add(20.0);
	        		
	        		ArrayList<Double> nutri2 = new ArrayList<>();
	        		nutri2.add(26.0);
	        		nutri2.add(30.0);
	        		nutri2.add(20.0);
	        		nutri2.add(15.0);
	        		
	        		ArrayList<Double> nutri3 = new ArrayList<>();
	        		nutri3.add(40.0);
	        		nutri3.add(35.0);
	        		nutri3.add(20.0);
	        		nutri3.add(40.0);
	        		
	        		qtdNutri.add(nutri1);
	        		qtdNutri.add(nutri2);
	        		qtdNutri.add(nutri3);
	        		
	        		
	        		ArrayList<Double> minNutri = new ArrayList<>();
	        		minNutri.add(20.0);
	        		minNutri.add(30.0);
	        		minNutri.add(25.0);
	        		
	        		Refeicao refeicao = new Refeicao(2, minNutri , qtdNutri, preco);
	        				
	        		refeicao.minNutrientes();
	        		
	        		ILS ils = new ILS();
	        		int numIter = 100;
	        		ils.ils(refeicao, numIter);
	        		
	        	}
	        	break;
	        	
	        	case 4 : 
	        		sair = true;
	        		break;
	        	
	        	default:
	        		System.out.println("Refeição Inválida!!!");
	        }
				

		}
		
		
		
		

	}

}
