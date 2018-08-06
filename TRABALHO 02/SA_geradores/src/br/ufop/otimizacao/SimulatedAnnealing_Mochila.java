/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufop.otimizacao;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SimulatedAnnealing_Mochila {

    
    static FileWriter writer;
    
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ArrayList<Integer> resp;
        ArrayList<Integer> solucaoini = new ArrayList();
        ArrayList<Integer> pesos = new ArrayList();
        
        writer = new FileWriter("arq.csv");
       
        for(int i = 0; i < 20; i++){
            Random rand = new Random();
            solucaoini.add(i, 0);
        }
        
        //Atribuição dos pesos passados
        pesos.add(0, 150);
        pesos.add(1, 20);
        pesos.add(2, 40);
        pesos.add(3, 220);
        pesos.add(4, 60);
        pesos.add(5, 85);
        pesos.add(6, 92);
        pesos.add(7, 12);
        pesos.add(8, 25);
        pesos.add(9, 110);
        pesos.add(10, 155);
        pesos.add(11, 23);
        pesos.add(12, 42);
        pesos.add(13, 226);
        pesos.add(14, 62);
        pesos.add(15, 91);
        pesos.add(16, 87);
        pesos.add(17, 13);
        pesos.add(18, 125);
        pesos.add(19, 112);
              
        //System.out.println(solucaoini.toString());
        for(int i=0; i<100; i++){
            System.out.println("ITERAÇÃO " + i + ": ");
            resp = SimulatedAnnealing(solucaoini, pesos, 0.7, 10, 0.2, 100);
            System.out.println(resp.toString());
        }
        
        writer.flush();
        writer.close();
    }

    public static ArrayList SimulatedAnnealing(ArrayList<Integer> s, ArrayList<Integer> pesos, double alpha, int SAmax, double beta, int t0) throws IOException{
        ArrayList<Integer> s_star;
        s_star =  (ArrayList<Integer>) s.clone();
        double delta;
        int interT = 0; //Numero de iterações na temperatura T
        double t = TempInicial(alpha, beta, SAmax, t0, s, pesos);
        while(t > Math.pow(10, -3)){
            while(interT < SAmax){
                interT = interT + 1;
                ArrayList<Integer> s2;
                s2 = calcula_vizinho(s);
                double delta1 = calcula_delta(s , pesos);
                double delta2 = calcula_delta(s2, pesos);
                delta = delta2 - delta1;
                if(delta > 0){
                    s = (ArrayList<Integer>) s2.clone();
                    if(delta2 > calcula_delta(s_star, pesos)){
                        s_star.clear();
                        s_star = (ArrayList<Integer>) s2.clone();
                        //s_star = s2;
                    }
                }
                else{
                    Random rand = new Random();
                    float x = rand.nextFloat();
                    if(x < Math.exp(delta/t)){
                        //s = s2;
                        s = (ArrayList<Integer>) s2.clone();
                    }
                }
                System.out.println("DELTA PARA TEMPERATURA " + t + ": " + calcula_delta(s_star, pesos));
                writer.append("" + t);
                writer.append(",");
                writer.append("" + calcula_delta(s_star, pesos));
                writer.append("\n");
            }
            t = alpha * t;
            interT = 0;
        }
        s = (ArrayList<Integer>) s_star.clone();
        //s = s_star;
        return s;
    }
    
    public static ArrayList<Integer> calcula_vizinho(ArrayList<Integer> s){
        ArrayList<Integer> sNovo = (ArrayList<Integer>) s.clone();
        Random rand = new Random();
        int pos = rand.nextInt(s.size());
        int moch = rand.nextInt(5);
        sNovo.set(pos, moch);
        //System.out.println(s.toString());
        return sNovo;
    }
    
    public static double calcula_delta(ArrayList<Integer> s, ArrayList<Integer> pesos){
        double c1 = 0.0;
        double c2 = 0.0;
        double c3 = 0.0;
        double c4 = 0.0;
        for(int i = 0; i < s.size(); i++){
            if(s.get(i) == 1)
                c1 = c1 + pesos.get(i);
            else if(s.get(i) == 2)
                c2 = c2 + pesos.get(i);
            else if(s.get(i) == 3)
                c3 = c3 + pesos.get(i);
            else if(s.get(i) == 4)
                c4 = c4 + pesos.get(i);
        }
        
        double cons = c1 + c2 + c3 + c4;
        
        if(c1 > 500)
            cons = cons - 10000 * (c1 - 500);
        if(c2 > 600)
            cons = cons - 10000 * (c2 - 600);
        if(c3 > 200)
            cons = cons - 10000 * (c3 - 200);
        if(c4 > 300)
            cons = cons - 10000 * (c4 - 300);
            
        return cons;
    }

    public static double TempInicial(double alpha, double beta, int SAmax, float t0, ArrayList<Integer> s, ArrayList<Integer> pesos){
         double t = t0;
         boolean continua = true;
         double aceitos;
         while(continua){
             aceitos = 0;
             for(int interT = 1; interT <= SAmax; interT++){
                 //Gera um vizinho qualquer
                ArrayList<Integer> s2;
                s2 = calcula_vizinho(s);
                double delta1 = calcula_delta(s, pesos);
                double delta2 = calcula_delta(s2, pesos);
                double delta = delta2 - delta1;
                if(delta > 0)
                    aceitos++;
                else{
                    Random rand = new Random();
                    float x = rand.nextFloat();
                    if(x < Math.exp(delta/t))
                        aceitos++;
                }
            }  
            if(aceitos >= alpha * SAmax)
                continua = false;
            else
                t = beta * t;
        }
        return t; 
    }
}