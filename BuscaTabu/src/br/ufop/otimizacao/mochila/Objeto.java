package br.ufop.otimizacao.mochila;

public class Objeto {
    
    public int peso;
    public int beneficio;

    public Objeto(int peso, int beneficio) {
        this.peso = peso;
        this.beneficio = beneficio;
    }

    @Override
    public String toString() {
        return "Objeto{" + "peso=" + peso + ", beneficio=" + beneficio + '}';
    }

    
}