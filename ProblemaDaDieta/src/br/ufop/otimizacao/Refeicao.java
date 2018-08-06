package br.ufop.otimizacao;

import java.util.ArrayList;
import java.util.Random;


public class Refeicao implements Cloneable{

	
	public int tipo; //café da manhã, almoço ou lanche
	public ArrayList<Double> qtdAlimento; // Xa->  quanto de cada alimento devemos comprar
	public ArrayList<Double> minNutrientes; // Mn-> qtd de nutrientes a ser ingerido de cada nutriente[ 0.2, 0.5, 1.5 ] -> [vitaminaA, calcio, proteina] 
	public ArrayList<ArrayList<Double>> qtdNutri; // Qn,a -> qtd de nutriente de cada alimento
	public ArrayList<Double> preco; // Pa->  preço de cada alimento
	public double custo;
	
	
	public Refeicao(int tipo, ArrayList<Double> minNutrientes, ArrayList<ArrayList<Double>> qtdNutri, ArrayList<Double> preco) {
		super();
		this.tipo = tipo;
		this.minNutrientes = minNutrientes;
		this.qtdNutri = qtdNutri;
		this.preco = preco;
	}


	public int getTipo() {
		return tipo;
	}


	public void setTipo(int tipo) {
		this.tipo = tipo;
	}


	public ArrayList<Double> getQtdAlimento() {
		return qtdAlimento;
	}


	public void setQtdAlimento(ArrayList<Double> qtdAlimento) {
		this.qtdAlimento = qtdAlimento;
	}


	public ArrayList<Double> getMaxNutrientes() {
		return minNutrientes;
	}


	public void setMaxNutrientes(ArrayList<Double> maxNutrientes) {
		this.minNutrientes = maxNutrientes;
	}


	public ArrayList<ArrayList<Double>> getQtdNutri() {
		return qtdNutri;
	}


	public void setQtdNutri(ArrayList<ArrayList<Double>> qtdNutri) {
		this.qtdNutri = qtdNutri;
	}


	public ArrayList<Double> getPreco() {
		return preco;
	}


	public void setPreco(ArrayList<Double> preco) {
		this.preco = preco;
	}
	
	
	public double getCusto() {
		return custo;
	}


	public void setCusto(double custo) {
		this.custo = custo;
	}
	
	public double minCusto (){
		double custo = 0.0;
		
		for (int i = 0; i < this.getQtdAlimento().size(); i++) {	
						
			custo += this.getPreco().get(i) * this.getQtdAlimento().get(i);
			
		}
				
		setCusto(custo);
		return custo;
	}
	

	public ArrayList<Double> minNutrientes() {
		
		if(tipo == 0) {
			double [] min = new double[this.getQtdNutri().size()];
			System.out.print("[Calcio, VitaminaA, Proteína] -> ");		
			System.out.print(minNutrientes.toString());
			
			Random random = new Random();
			do{
				this.qtdAlimento = new ArrayList<>();
				for (int k = 0; k < this.getPreco().size(); k++) {
					this.qtdAlimento.add(random.nextDouble());
					
				}			
				
				for (int i = 0; i < this.getQtdNutri().size(); i++) {
					for (int j = 0; j < this.getQtdNutri().get(i).size(); j++) {
						
						min[i] += this.getQtdNutri().get(i).get(j) * this.getQtdAlimento().get(j);
						
					}		
					
				}
				
				
			}while(min[0]<= this.minNutrientes.get(0) || min[1]<= this.minNutrientes.get(1) || min[2]<= this.minNutrientes.get(2));
			
			System.out.print("\nNutrientes da refeição: ");
			for (int i = 0; i < min.length; i++) {
				System.out.print(" " + min[i]);
			}
			System.out.println("\nCardápio: Pão Integral, Suco Natural, Biscoito Integral, Geleia de Frutas");
			System.out.println("Preço de cada alimento: " + this.getPreco().toString());
			System.out.println("\n*********** SOLUÇÃO INICIAL ***********");
			System.out.println("Quantidade de cada alimento a ser comprado:" + getQtdAlimento().toString());
			System.out.println("Custo da compra: " + minCusto());
			System.out.println("****************************************\n");
		}
		
		else if(tipo == 1) {
			double [] min = new double[this.getQtdNutri().size()];
			System.out.print("[Calcio, VitaminaA, Proteína] -> ");		
			System.out.print(minNutrientes.toString());
			
			Random random = new Random();
			do{
				this.qtdAlimento = new ArrayList<>();
				for (int k = 0; k < this.getPreco().size(); k++) {
					this.qtdAlimento.add(random.nextDouble());
					
				}			
				
				for (int i = 0; i < this.getQtdNutri().size(); i++) {
					for (int j = 0; j < this.getQtdNutri().get(i).size(); j++) {
						
						min[i] += this.getQtdNutri().get(i).get(j) * this.getQtdAlimento().get(j);
						
					}		
					
				}
				
				
			}while(min[0]<= this.minNutrientes.get(0) || min[1]<= this.minNutrientes.get(1) || min[2]<= this.minNutrientes.get(2));
			
			System.out.print("\nNutrientes da refeição: ");
			for (int i = 0; i < min.length; i++) {
				System.out.print(" " + min[i]);
			}
			System.out.println("\nCardápio: Brócolis, Cenoura, Peixe, Carne");
			System.out.println("Preço de cada alimento: " + this.getPreco().toString());
			System.out.println("\n*********** SOLUÇÃO INICIAL ***********");
			System.out.println("Quantidade de cada alimento a ser comprado:" + getQtdAlimento().toString());
			System.out.println("Custo da compra: " + minCusto());
			System.out.println("****************************************\n");
		}
		
		else {
			double [] min = new double[this.getQtdNutri().size()];
			System.out.print("[Calcio, VitaminaA, Proteína] -> ");		
			System.out.print(minNutrientes.toString());
			
			Random random = new Random();
			do{
				this.qtdAlimento = new ArrayList<>();
				for (int k = 0; k < this.getPreco().size(); k++) {
					this.qtdAlimento.add(random.nextDouble());
					
				}			
				
				for (int i = 0; i < this.getQtdNutri().size(); i++) {
					for (int j = 0; j < this.getQtdNutri().get(i).size(); j++) {
						
						min[i] += this.getQtdNutri().get(i).get(j) * this.getQtdAlimento().get(j);
						
					}		
					
				}
				
				
			}while(min[0]<= this.minNutrientes.get(0) || min[1]<= this.minNutrientes.get(1) || min[2]<= this.minNutrientes.get(2));
			
			System.out.print("\nNutrientes da refeição: ");
			for (int i = 0; i < min.length; i++) {
				System.out.print(" " + min[i]);
			}
			System.out.println("\nCardápio: Patê de Atum, Biscoito, Chá, Fruta");
			System.out.println("Preço de cada alimento: " + this.getPreco().toString());
			System.out.println("\n*********** SOLUÇÃO INICIAL ***********");
			System.out.println("Quantidade de cada alimento a ser comprado:" + getQtdAlimento().toString());
			System.out.println("Custo da compra: " + minCusto());
			System.out.println("****************************************\n");
			
		}
		
		return qtdAlimento;
	}

	
	public Object clone1(){
		Refeicao ref = new Refeicao(tipo, minNutrientes, qtdNutri, preco);
		ref.setQtdAlimento(qtdAlimento);
		return ref;
		
	}
	
	
	
	

}
