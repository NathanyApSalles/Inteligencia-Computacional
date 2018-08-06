package br.ufop.otimizacao.caixeiro;

import java.security.Principal;
import java.util.ArrayList;

public class Caminho {
	
    public static double calculaCaminho(ArrayList<Integer> caminho, double matriz[][]){
        double custo = 0.0;
        for (int i = 0; i < caminho.size()-1 ; i++ ){
                custo += matriz[caminho.get(i)][caminho.get(i+1)];
        } 
                
        custo = custo + matriz[caminho.get(caminho.size()-1)][caminho.get(0)];
        
        return custo;
    }
    
public static ArrayList<Integer> tradutorStringInt(ArrayList<String> caminho, ArrayList<Integer> pos ){      
       
        
        for (int i = 0;  i < caminho.size() ; i++ ){
            switch (caminho.get(i)) {
                case "A":
                    pos.add(i,0);
                    break;
                case "B":
                    pos.add(i,1);
                    break;
                case "C":
                    pos.add(i,2);
                    break;
                case "D":
                    pos.add(i,3);
                    break;
                case "E":
                    pos.add(i,4);
                    break;
                case "F":
                    pos.add(i,5);
                    break;
                case "G":
                    pos.add(i,6);
                    break;
                case "H":
                    pos.add(i,7);
                    break;
                case "I":
                    pos.add(i,8);
                    break;
                case "J":
                    pos.add(i,9);
                    break;
                
            }
        }
        
        return pos;
        
    }
    
    public static ArrayList<String> tradutorIntString(ArrayList<Integer> pos, ArrayList<String> caminho){
        
        for (int i = 0; i < pos.size() ; i++ ){
            switch (pos.get(i)) {
                case 0:
                    caminho.add(i,"A");
                    break;
                case 1:
                    caminho.add(i,"B");
                    break;
                case 2:
                    caminho.add(i,"C");
                    break;
                case 3:
                    caminho.add(i,"D");
                    break;
                case 4:
                    caminho.add(i,"E");
                    break;
                case 5:
                    caminho.add(i,"F");
                    break;
                case 6:
                    caminho.add(i,"G");
                    break;
                case 7:
                    caminho.add(i,"H");
                    break;
                case 8:
                    caminho.add(i,"I");
                    break;
                case 9:
                    caminho.add(i,"J");
                    break;
                
            }
        } 
        return caminho;
        
    }



}
