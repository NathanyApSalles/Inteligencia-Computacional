package Ag_geradores;


import java.util.ArrayList;


public class Populacao {
    
    
    // Tamanho da população
    Integer tamanho;
    
    // Conjunto de indivíduos
    ArrayList<Individuo> individuos;

    // Problema
    Problema problema;
    
    int mochila, capacidade;

    public Populacao(Integer tamanho,Problema problema, int mochila, int capacidade) {
        this.tamanho = tamanho;
        this.problema = problema;
        this.individuos = new ArrayList<>();
        this.mochila = mochila;
        this.capacidade = capacidade;
    }
    
     

    public Integer getTamanho() {
        return tamanho;
    }

    public void setTamanho(Integer tamanho) {
        this.tamanho = tamanho;
    }

    public ArrayList<Individuo> getIndividuos() {
        return individuos;
    }

    public void setIndividuos(ArrayList<Individuo> individuos) {
        this.individuos = individuos;
    }
    
    // Criar a população
    public void criar() {
        
        this.individuos = new ArrayList<>();
        
        
        for(int i = 0; i < this.getTamanho(); i++) {
            
            Individuo individuo = new Individuo(500);
            
            individuo.criar(mochila,500);
            
            this.getIndividuos().add(individuo);
            
        }
        
    }
    
    // Avaliar a população
    public void avaliar() {
        
        for(Individuo individuo : this.getIndividuos()) {
            problema.calcularFuncaoObjetivo(individuo);
        }
        
    }
    
    
}
