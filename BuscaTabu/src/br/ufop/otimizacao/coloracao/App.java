package br.ufop.otimizacao.coloracao;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    /**
     * @param args the command line arguments
     */

    static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        try {
            Leitor reader = new Leitor();
            reader.lerArquivo();
            Grafo grafo = reader.grafo;
            ArrayList<Aresta> arestas = reader.arestas;

                    Coloracao c = new Coloracao(grafo);
                    ArrayList<Integer> solucao = c.buscaTabu(c.coloracaoInicial(), 100, 10);
                    System.out.println(" TAMANHO COLORA��O FINAL: " + solucao.size());
                    System.out.println("\n SOLU��O INICIAL: " + c.coloracaoInicial());
                    System.out.println(" CUSTO INICIAL: " + c.funcao(c.coloracaoInicial()));
                    System.out.println(" TAMANHO COLORA��O INICIAL: " + c.listaCores(c.coloracaoInicial()).size());//              
        } 
        catch (Exception e){
            e.printStackTrace();
        }
    }
}