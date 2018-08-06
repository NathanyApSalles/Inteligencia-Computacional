package br.ufop.otimizacao.mochila;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int capacidade1 = 500;
        int capacidade2 = 600;
        int capacidade3 = 200;
        int capacidade4 = 300;
        
        ArrayList<Objeto> listaDeObjetos = new ArrayList<>();
        listaDeObjetos.add(new Objeto(150, 150));
        listaDeObjetos.add(new Objeto(20, 20));
        listaDeObjetos.add(new Objeto(40, 40));
        listaDeObjetos.add(new Objeto(220, 220));
        listaDeObjetos.add(new Objeto(60, 60));
        listaDeObjetos.add(new Objeto(85, 85));
        listaDeObjetos.add(new Objeto(92, 92));
        listaDeObjetos.add(new Objeto(12, 12));
        listaDeObjetos.add(new Objeto(25, 25));
        listaDeObjetos.add(new Objeto(110, 110));
        listaDeObjetos.add(new Objeto(155, 155));
        listaDeObjetos.add(new Objeto(23, 23));
        listaDeObjetos.add(new Objeto(42, 42));
        listaDeObjetos.add(new Objeto(226, 226));
        listaDeObjetos.add(new Objeto(62, 62));
        listaDeObjetos.add(new Objeto(91, 91));
        listaDeObjetos.add(new Objeto(87, 87));
        listaDeObjetos.add(new Objeto(13, 13));
        listaDeObjetos.add(new Objeto(125, 125));
        listaDeObjetos.add(new Objeto(112, 112));
        
        Mochila gerador1 = new Mochila(listaDeObjetos, capacidade1, 1);
        Mochila gerador2 = new Mochila(listaDeObjetos, capacidade2, 2);
        Mochila gerador3 = new Mochila(listaDeObjetos, capacidade3, 3);
        Mochila gerador4 = new Mochila(listaDeObjetos, capacidade4, 4);
        
        Random rand = new Random();
        
        Solucao s = new Solucao(listaDeObjetos);
        ArrayList<Integer> resp;
        for(int i=0; i<100; i++){
            resp = s.solucaoInicial(gerador1, gerador2, gerador3, gerador4);
            System.out.println(resp);
            int numIter = rand.nextInt(10000);
            int numTabu = rand.nextInt(50);
            resp = s.buscaTabu(resp, numIter, numTabu, listaDeObjetos, gerador1, gerador2, gerador3, gerador4);
    }
    
    }
}