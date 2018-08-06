package br.ufop.otimizacao.mochila;

import java.util.ArrayList;

public class Mochila {
    private ArrayList<Objeto> listaObjetos;
    private int pesoMaximo;
    private int num;

    public Mochila() {
        this.listaObjetos = new ArrayList<>();
        this.pesoMaximo = 0;
    }
    
    public Mochila(ArrayList<Objeto> listaObjetos, int pesoMáximo, int num){ 
        this.listaObjetos = new ArrayList<>(listaObjetos);  
        this.pesoMaximo = pesoMáximo; 
        this.num = num;
    } 

    public int getPesoMaximo() {
        return pesoMaximo;
    }

    public void setPesoMaximo(int pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
       
    @Override
    public String toString(){ 
        String res = "Peso máximo: " + pesoMaximo + "\nLista de objetos: "; 
        res += listaObjetos.toString(); 
        return res; 

    } 
}