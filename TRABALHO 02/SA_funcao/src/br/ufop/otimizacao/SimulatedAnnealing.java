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

public class SimulatedAnnealing {

    
    static FileWriter writer;
    
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ArrayList<Double> resp;
        ArrayList<Double> solucaoini = new ArrayList();
        solucaoini.add(0, 0.5);
        solucaoini.add(1, 0.5);
        
        writer = new FileWriter("arq.csv");
        for(int i=0; i<100; i++){
            System.out.println("ITERAÇÃO " + i + ": ");
            resp = SimulatedAnnealing(0.5, solucaoini, 0.7, 10, 0.2, 100);
            writer.append("\n");
        }
        
        writer.flush();
        writer.close();
    }

    //s é a solução inicial
    //alpha é a taxa de redução da temperatura
    //SAmax é o numero máximo de iterações na temperatura t
    //beta é a taxa de reaquecimento
    //t0 é a temperatura inicial
    
    public static ArrayList SimulatedAnnealing(double n, ArrayList<Double> s, double alpha, int SAmax, double beta, int t0) throws IOException{
        ArrayList<Double> s_star;
        s_star =  (ArrayList<Double>) s.clone();
        double delta = 0.0;
        int interT = 0; //Numero de iterações na temperatura T
        double i, j;
        double t = TempInicial(n, alpha, beta, SAmax, t0, s);
        while(t > Math.pow(10, -3)){
            while(interT < SAmax){
                interT = interT + 1;
                Random rand = new Random();
                i = (-0.5 + rand.nextDouble())*n;
                do{
                    j =(-0.5 + rand.nextDouble())*n;
                }while(j == i);
                ArrayList<Double> s2 = new ArrayList();
                s2.add(0, i);
                s2.add(1, j);
                double delta1 = calcula_delta(s);
                double delta2 = calcula_delta(s2);
                delta = delta2 - delta1;
                if(delta < 0){
                    s.clear();
                    s.add(0, s2.get(0));
                    s.add(1, s2.get(1));
                    if(delta2 < calcula_delta(s_star)){
                        s_star.clear();
                        s_star.add(0, s2.get(0));
                        s_star.add(1, s2.get(1));
                    }
                }
                else{
                    float x = rand.nextFloat();
                    if(x < Math.exp(-delta/t)){
                        s.clear();
                        s.add(0, s2.get(0));
                        s.add(1, s2.get(1));
                    }
                }
                System.out.println("DELTA PARA TEMPERATURA " + t + ": " + calcula_delta(s_star));
                writer.append("" + t);
                writer.append(",");
                writer.append("" + calcula_delta(s_star));
                writer.append("\n");
            }
            t = alpha * t;
            interT = 0;
        }
        //System.out.println("\nDELTA: " + delta);
        s.clear();
        s.add(0, s_star.get(0));
        s.add(1, s_star.get(1));
        return s;
    }
    
    public static double calcula_delta(ArrayList<Double> s){
        double delta = (((1 + sinc(s.get(0)))/2)*((1 + (sinc(s.get(1))))/2));
        return delta; 
    }

    public static double TempInicial(double n, double alpha, double beta, int SAmax, float t0, ArrayList s){
         double t = t0;
         boolean continua = true;
         double aceitos, i, j;
         while(continua){
             aceitos = 0;
             for(int interT = 1; interT <= SAmax; interT++){
                 //Gera um vizinho qualquer
                Random rand = new Random();
                i = (-0.5 + rand.nextDouble())*n;
                do{
                    j = (-0.5 + rand.nextDouble())*n;
                }while(j == i);
                ArrayList<Double> s2 = new ArrayList();
                s2.add(0, i);
                s2.add(1, j);
                double delta1 = calcula_delta(s);
                double delta2 = calcula_delta(s2);
                double delta = delta2 - delta1;
                if(delta < 0)
                    aceitos++;
                else{
                    float x = rand.nextFloat();
                    if(x < Math.exp(-delta/t))
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
    
    public static double sinc(Double val){
        Double sinc;
        sinc = Math.sin(Math.PI * (val - 5))/(Math.PI * (val - 5));
        return sinc;
    }
}