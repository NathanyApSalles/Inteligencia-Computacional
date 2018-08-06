
package Ag_geradores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Individuo implements Comparable<Individuo>{
    
	
	ArrayList<Integer> DEMANDAS = new ArrayList<Integer>(){
		{
			add(150);
			add(20);
			add(40);
			add(220);
			add(60);
			add(85);
			add(92);
			add(12);
			add(25);
			add(110);
			add(155);
			add(23);
			add(42);
			add(226);
			add(62);
			add(91);
			add(87);
			add(13);
			add(125);
			add(112);
			
		}
	};
    private int capacidade;
    private ArrayList<Integer> cargas = new ArrayList<>();
	private double peso;
    
    // Custo da função objetivo
    Double funcaoObjetivo;
    
    
    public Individuo( int capacidade) {
       
        this.capacidade = capacidade;
    }

    
    public Double getFuncaoObjetivo() {
        return funcaoObjetivo;
    }

    public void setFuncaoObjetivo(Double funcaoObjetivo) {
        this.funcaoObjetivo = funcaoObjetivo;
    }

    public int getCapacidade() {
		return capacidade;
	}


	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}


	public double getPeso() {
		return peso;
	}


	public void setPeso(double peso) {
		this.peso = peso;
	}
    

    public ArrayList<Integer> getCargas() {
		return cargas;
	}


	public void setCargas(ArrayList<Integer> cargas) {
		this.cargas = cargas;
	}
    
    // Gerar o genótipo
    public void criar(int gerador, int capacidade) {        
           
    	int index = 0;   	
    	
    	
    	while(this.peso < this.capacidade){
    		this.peso += DEMANDAS.get(index);
    		
    		cargas.add(DEMANDAS.get(index));
    		
    		
    		index ++;
    	 }
      
    	       
            
                
    }
     
    @Override
    public int compareTo(Individuo o) {
        return this.getFuncaoObjetivo()
                .compareTo(o.getFuncaoObjetivo());
    }
}
