package br.ufop.otimizacao.mochila;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Solucao {
    private ArrayList<Objeto> listaObjetos;

    public Solucao(ArrayList<Objeto> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }
        
    public ArrayList<Integer> solucaoInicial(Mochila mochila1, Mochila mochila2, Mochila mochila3, Mochila mochila4){ 
        ArrayList<Integer> res  = new ArrayList<>();
        Random rand = new Random();
        int i=0;
        while(i != listaObjetos.size()){ 
            int mochila = rand.nextInt(4);
            res.add(i, mochila+1); 
            i++;
        }
        return res; 
    }
    
    public ArrayList<Integer> buscaTabu(ArrayList<Integer> s0, int numIter, int tamTabu, ArrayList<Objeto> objetos, Mochila mochila1, Mochila mochila2, Mochila mochila3, Mochila mochila4){
        
        //inicializando a lista tabu
        ArrayList<Integer> tabu = new ArrayList<Integer>(Collections.nCopies(s0.size(), 0));
        ArrayList<Integer> f_opt_iter = new ArrayList<>(Collections.nCopies(numIter, 0));
        
        //inicializa o ótimo inicial
        int f_opt = funcao(s0, objetos, mochila1, mochila2, mochila3, mochila4);
        ArrayList<Integer> s_opt = (ArrayList<Integer>) s0.clone();
        ArrayList<Integer> s = (ArrayList<Integer>) s0.clone();
        
        for(int i = 0; i < numIter; i++){
            double maxf = Double.NEGATIVE_INFINITY;
            int maxk = 0;
            ArrayList<Integer> maxs = (ArrayList<Integer>) s.clone();
            //testa os vizinhos
            for(int k = 0; k < s0.size(); k++){
                ArrayList<Integer> sk = (ArrayList<Integer>) s.clone();
                if(sk.get(k) == 0){
                    sk.set(k, 1);
                    int fk = funcao(sk, objetos, mochila1, mochila2, mochila3, mochila4); 
                    if(tabu.get(k) <= 0){
                        if(fk > maxf){
                            maxf = fk;
                            maxs = (ArrayList<Integer>) sk.clone();
                            maxk = k;
                            if(fk > f_opt){
                                f_opt = fk;
                                s_opt = (ArrayList<Integer>) sk.clone();
                            }
                        }             
                    }
                    else if(fk > f_opt){
                        maxf = fk;
                        maxs = (ArrayList<Integer>) sk.clone();
                        maxk = k;
                        f_opt = fk;
                        s_opt = (ArrayList<Integer>) sk.clone();
                    }
                    sk.set(k, 2);
                    fk = funcao(sk, objetos, mochila1, mochila2, mochila3, mochila4);
                    if(tabu.get(k) <= 0){
                        if(fk > maxf){
                            maxf = fk;
                            maxs = (ArrayList<Integer>) sk.clone();
                            maxk = k;
                            if(fk > f_opt){
                                f_opt = fk;
                                s_opt = (ArrayList<Integer>) sk.clone();
                            }
                        }
                    }
                    else if(fk > f_opt){
                        maxf = fk;
                        maxs = (ArrayList<Integer>) sk.clone();
                        maxk = k;
                        f_opt = fk;
                        s_opt = sk;
                    }
                    
                    sk.set(k, 3);
                    fk = funcao(sk, objetos, mochila1, mochila2, mochila3, mochila4);
                    if(tabu.get(k) <= 0){
                        if(fk > maxf){
                            maxf = fk;
                            maxs = (ArrayList<Integer>) sk.clone();
                            maxk = k;
                            if(fk > f_opt){
                                f_opt = fk;
                                s_opt = (ArrayList<Integer>) sk.clone();
                            }
                        }
                    }
                    else if(fk > f_opt){
                        maxf = fk;
                        maxs = (ArrayList<Integer>) sk.clone();
                        maxk = k;
                        f_opt = fk;
                        s_opt = sk;
                    }
                    
                    sk.set(k, 4);
                    fk = funcao(sk, objetos, mochila1, mochila2, mochila3, mochila4);
                    if(tabu.get(k) <= 0){
                        if(fk > maxf){
                            maxf = fk;
                            maxs = (ArrayList<Integer>) sk.clone();
                            maxk = k;
                            if(fk > f_opt){
                                f_opt = fk;
                                s_opt = (ArrayList<Integer>) sk.clone();
                            }
                        }
                    }
                    else if(fk > f_opt){
                        maxf = fk;
                        maxs = (ArrayList<Integer>) sk.clone();
                        maxk = k;
                        f_opt = fk;
                        s_opt = sk;
                    }
                }
                if(sk.get(k) == 1){
                    
                    sk.set(k, 0);
                    int fk = funcao(sk, objetos, mochila1, mochila2, mochila3, mochila4); 
                    if(tabu.get(k) <= 0){
                        if(fk > maxf){
                            maxf = fk;
                            maxs = (ArrayList<Integer>) sk.clone();
                            maxk = k;
                            if(fk > f_opt){
                                f_opt = fk;
                                s_opt = (ArrayList<Integer>) sk.clone();
                            }
                        }             
                    }
                    else if(fk > f_opt){
                        maxf = fk;
                        maxs = (ArrayList<Integer>) sk.clone();
                        maxk = k;
                        f_opt = fk;
                        s_opt = (ArrayList<Integer>) sk.clone();
                    }
                    
                    sk.set(k, 2);
                    fk = funcao(sk, objetos, mochila1, mochila2, mochila3, mochila4);
                    if(tabu.get(k) <= 0){
                        if(fk > maxf){
                            maxf = fk;
                            maxs = (ArrayList<Integer>) sk.clone();
                            maxk = k;
                            if(fk > f_opt){
                                f_opt = fk;
                                s_opt = (ArrayList<Integer>) sk.clone();
                            }
                        }
                    }
                    else if(fk > f_opt){
                        maxf = fk;
                        maxs = (ArrayList<Integer>) sk.clone();
                        maxk = k;
                        f_opt = fk;
                        s_opt = sk;
                    }
                    
                    sk.set(k, 3);
                    fk = funcao(sk, objetos, mochila1, mochila2, mochila3, mochila4);
                    if(tabu.get(k) <= 0){
                        if(fk > maxf){
                            maxf = fk;
                            maxs = (ArrayList<Integer>) sk.clone();
                            maxk = k;
                            if(fk > f_opt){
                                f_opt = fk;
                                s_opt = (ArrayList<Integer>) sk.clone();
                            }
                        }
                    }
                    else if(fk > f_opt){
                        maxf = fk;
                        maxs = (ArrayList<Integer>) sk.clone();
                        maxk = k;
                        f_opt = fk;
                        s_opt = sk;
                    }
                    
                    sk.set(k, 4);
                    fk = funcao(sk, objetos, mochila1, mochila2, mochila3, mochila4);
                    if(tabu.get(k) <= 0){
                        if(fk > maxf){
                            maxf = fk;
                            maxs = (ArrayList<Integer>) sk.clone();
                            maxk = k;
                            if(fk > f_opt){
                                f_opt = fk;
                                s_opt = (ArrayList<Integer>) sk.clone();
                            }
                        }
                    }
                    else if(fk > f_opt){
                        maxf = fk;
                        maxs = (ArrayList<Integer>) sk.clone();
                        maxk = k;
                        f_opt = fk;
                        s_opt = sk;
                    }
                }
                if(sk.get(k) == 2){
                    
                    sk.set(k, 0);
                    int fk = funcao(sk, objetos, mochila1, mochila2, mochila3, mochila4); 
                    if(tabu.get(k) <= 0){
                        if(fk > maxf){
                            maxf = fk;
                            maxs = (ArrayList<Integer>) sk.clone();
                            maxk = k;
                            if(fk > f_opt){
                                f_opt = fk;
                                s_opt = (ArrayList<Integer>) sk.clone();
                            }
                        }             
                    }
                    else if(fk > f_opt){
                        maxf = fk;
                        maxs = (ArrayList<Integer>) sk.clone();
                        maxk = k;
                        f_opt = fk;
                        s_opt = (ArrayList<Integer>) sk.clone();
                    }
                    
                    sk.set(k, 1);
                    fk = funcao(sk, objetos, mochila1, mochila2, mochila3, mochila4);
                    if(tabu.get(k) <= 0){
                        if(fk > maxf){
                            maxf = fk;
                            maxs = (ArrayList<Integer>) sk.clone();
                            maxk = k;
                            if(fk > f_opt){
                                f_opt = fk;
                                s_opt = (ArrayList<Integer>) sk.clone();
                            }
                        }
                    }
                    else if(fk > f_opt){
                        maxf = fk;
                        maxs = (ArrayList<Integer>) sk.clone();
                        maxk = k;
                        f_opt = fk;
                        s_opt = sk;
                    }
                    
                    sk.set(k, 3);
                    fk = funcao(sk, objetos, mochila1, mochila2, mochila3, mochila4);
                    if(tabu.get(k) <= 0){
                        if(fk > maxf){
                            maxf = fk;
                            maxs = (ArrayList<Integer>) sk.clone();
                            maxk = k;
                            if(fk > f_opt){
                                f_opt = fk;
                                s_opt = (ArrayList<Integer>) sk.clone();
                            }
                        }
                    }
                    else if(fk > f_opt){
                        maxf = fk;
                        maxs = (ArrayList<Integer>) sk.clone();
                        maxk = k;
                        f_opt = fk;
                        s_opt = sk;
                    }
                    
                    sk.set(k, 4);
                    fk = funcao(sk, objetos, mochila1, mochila2, mochila3, mochila4);
                    if(tabu.get(k) <= 0){
                        if(fk > maxf){
                            maxf = fk;
                            maxs = (ArrayList<Integer>) sk.clone();
                            maxk = k;
                            if(fk > f_opt){
                                f_opt = fk;
                                s_opt = (ArrayList<Integer>) sk.clone();
                            }
                        }
                    }
                    else if(fk > f_opt){
                        maxf = fk;
                        maxs = (ArrayList<Integer>) sk.clone();
                        maxk = k;
                        f_opt = fk;
                        s_opt = sk;
                    }
                }
                if(sk.get(k) == 3){
                    
                    sk.set(k, 0);
                    int fk = funcao(sk, objetos, mochila1, mochila2, mochila3, mochila4); 
                    if(tabu.get(k) <= 0){
                        if(fk > maxf){
                            maxf = fk;
                            maxs = (ArrayList<Integer>) sk.clone();
                            maxk = k;
                            if(fk > f_opt){
                                f_opt = fk;
                                s_opt = (ArrayList<Integer>) sk.clone();
                            }
                        }             
                    }
                    else if(fk > f_opt){
                        maxf = fk;
                        maxs = (ArrayList<Integer>) sk.clone();
                        maxk = k;
                        f_opt = fk;
                        s_opt = (ArrayList<Integer>) sk.clone();
                    }
                    
                    sk.set(k, 1);
                    fk = funcao(sk, objetos, mochila1, mochila2, mochila3, mochila4);
                    if(tabu.get(k) <= 0){
                        if(fk > maxf){
                            maxf = fk;
                            maxs = (ArrayList<Integer>) sk.clone();
                            maxk = k;
                            if(fk > f_opt){
                                f_opt = fk;
                                s_opt = (ArrayList<Integer>) sk.clone();
                            }
                        }
                    }
                    else if(fk > f_opt){
                        maxf = fk;
                        maxs = (ArrayList<Integer>) sk.clone();
                        maxk = k;
                        f_opt = fk;
                        s_opt = sk;
                    }
                    
                    sk.set(k, 2);
                    fk = funcao(sk, objetos, mochila1, mochila2, mochila3, mochila4);
                    if(tabu.get(k) <= 0){
                        if(fk > maxf){
                            maxf = fk;
                            maxs = (ArrayList<Integer>) sk.clone();
                            maxk = k;
                            if(fk > f_opt){
                                f_opt = fk;
                                s_opt = (ArrayList<Integer>) sk.clone();
                            }
                        }
                    }
                    else if(fk > f_opt){
                        maxf = fk;
                        maxs = (ArrayList<Integer>) sk.clone();
                        maxk = k;
                        f_opt = fk;
                        s_opt = sk;
                    }
                    
                    sk.set(k, 4);
                    fk = funcao(sk, objetos, mochila1, mochila2, mochila3, mochila4);
                    if(tabu.get(k) <= 0){
                        if(fk > maxf){
                            maxf = fk;
                            maxs = (ArrayList<Integer>) sk.clone();
                            maxk = k;
                            if(fk > f_opt){
                                f_opt = fk;
                                s_opt = (ArrayList<Integer>) sk.clone();
                            }
                        }
                    }
                    else if(fk > f_opt){
                        maxf = fk;
                        maxs = (ArrayList<Integer>) sk.clone();
                        maxk = k;
                        f_opt = fk;
                        s_opt = sk;
                    }
                }
                else{
                    
                    sk.set(k, 0);
                    int fk = funcao(sk, objetos, mochila1, mochila2, mochila3, mochila4); 
                    if(tabu.get(k) <= 0){
                        if(fk > maxf){
                            maxf = fk;
                            maxs = (ArrayList<Integer>) sk.clone();
                            maxk = k;
                            if(fk > f_opt){
                                f_opt = fk;
                                s_opt = (ArrayList<Integer>) sk.clone();
                            }
                        }             
                    }
                    else if(fk > f_opt){
                        maxf = fk;
                        maxs = (ArrayList<Integer>) sk.clone();
                        maxk = k;
                        f_opt = fk;
                        s_opt = (ArrayList<Integer>) sk.clone();
                    }
                    
                    sk.set(k, 1);
                    fk = funcao(sk, objetos, mochila1, mochila2, mochila3, mochila4);
                    if(tabu.get(k) <= 0){
                        if(fk > maxf){
                            maxf = fk;
                            maxs = (ArrayList<Integer>) sk.clone();
                            maxk = k;
                            if(fk > f_opt){
                                f_opt = fk;
                                s_opt = (ArrayList<Integer>) sk.clone();
                            }
                        }
                    }
                    else if(fk > f_opt){
                        maxf = fk;
                        maxs = (ArrayList<Integer>) sk.clone();
                        maxk = k;
                        f_opt = fk;
                        s_opt = sk;
                    }
                    
                    sk.set(k, 2);
                    fk = funcao(sk, objetos, mochila1, mochila2, mochila3, mochila4);
                    if(tabu.get(k) <= 0){
                        if(fk > maxf){
                            maxf = fk;
                            maxs = (ArrayList<Integer>) sk.clone();
                            maxk = k;
                            if(fk > f_opt){
                                f_opt = fk;
                                s_opt = (ArrayList<Integer>) sk.clone();
                            }
                        }
                    }
                    else if(fk > f_opt){
                        maxf = fk;
                        maxs = (ArrayList<Integer>) sk.clone();
                        maxk = k;
                        f_opt = fk;
                        s_opt = sk;
                    }
                    
                    sk.set(k, 3);
                    fk = funcao(sk, objetos, mochila1, mochila2, mochila3, mochila4);
                    if(tabu.get(k) <= 0){
                        if(fk > maxf){
                            maxf = fk;
                            maxs = (ArrayList<Integer>) sk.clone();
                            maxk = k;
                            if(fk > f_opt){
                                f_opt = fk;
                                s_opt = (ArrayList<Integer>) sk.clone();
                            }
                        }
                    }
                    else if(fk > f_opt){
                        maxf = fk;
                        maxs = (ArrayList<Integer>) sk.clone();
                        maxk = k;
                        f_opt = fk;
                        s_opt = sk;
                    }
                }
            }

            for(int j = 0; j < tabu.size(); j++){
                if(tabu.get(j) > 0)
                    tabu.set(j, tabu.get(j) - 1);
            }
            tabu.set(maxk, tamTabu);
            //System.out.println("Tabu:             " + tabu.toString());
            s = (ArrayList<Integer>) maxs.clone();
            f_opt_iter.set(i, f_opt);
            //System.out.println("Custo Iteração: " + f_opt);
            //System.out.println("Solucao Iteração: " + s_opt.toString());

        }
        System.out.println("SOLUCAO FINAL: " + s_opt);
        System.out.println("CUSTO FINAL: " + f_opt);
        return s_opt;
    }
    
    public int funcao(ArrayList<Integer> s, ArrayList<Objeto> objetos, Mochila mochila1, Mochila mochila2, Mochila mochila3, Mochila mochila4){
        ArrayList<Integer> s1 = (ArrayList<Integer>) s.clone();
        for(int i = 0; i < s1.size(); i++){
            if(s1.get(i) == 2)
                s1.set(i, 0);
            else if(s1.get(i) == 3)
                s1.set(i, 0);
            else if(s1.get(i) == 4)
                s1.set(i, 0);
        }
        
        ArrayList<Integer> s2 = (ArrayList<Integer>) s.clone();
        for(int j = 0; j < s2.size(); j++){
            if(s2.get(j) == 1)
                s2.set(j, 0);
            else if(s2.get(j) == 2)
                s2.set(j, 1);
            else if(s2.get(j) == 3)
                s2.set(j, 0);
            else if(s2.get(j) == 4)
                s2.set(j, 0);
        }
        
        ArrayList<Integer> s3 = (ArrayList<Integer>) s.clone();
        for(int k = 0; k < s3.size(); k++){
            if(s3.get(k) == 1)
                s3.set(k, 0);
            else if(s3.get(k) == 2)
                s3.set(k, 0);
            else if(s3.get(k) == 3)
                s3.set(k, 1);
            else if(s3.get(k) == 4)
                s3.set(k, 0);
        }
        
        ArrayList<Integer> s4 = (ArrayList<Integer>) s.clone();
        for(int l = 0; l < s4.size(); l++){
            if(s4.get(l) == 1)
                s4.set(l, 0);
            else if(s4.get(l) == 2)
                s4.set(l, 0);
            else if(s4.get(l) == 3)
                s4.set(l, 0);
            else if(s4.get(l) == 4)
                s4.set(l, 1);
        }
        
        int beneficio = 0;  
        for(int i = 0; i < s1.size(); i++){
            beneficio = beneficio + s1.get(i)*objetos.get(i).beneficio;
        }
        for(int i = 0; i < s2.size(); i++){
            beneficio = beneficio + s2.get(i)*objetos.get(i).beneficio;
        }
        for(int i = 0; i < s3.size(); i++){
            beneficio = beneficio + s3.get(i)*objetos.get(i).beneficio;
        }
        for(int i = 0; i < s4.size(); i++){
            beneficio = beneficio + s4.get(i)*objetos.get(i).beneficio;
        }
        
        
        int peso1 = 0;
        for(int i = 0; i < s1.size(); i++){
            peso1 = peso1 + s1.get(i)*objetos.get(i).peso;
        }
        
        int peso2 = 0;
        for(int i = 0; i < s2.size(); i++){
            peso2 = peso2 + s2.get(i)*objetos.get(i).peso;
        }
        
        int peso3 = 0;
        for(int i = 0; i < s3.size(); i++){
            peso3 = peso3 + s3.get(i)*objetos.get(i).peso;
        }
        
        int peso4 = 0;
        for(int i = 0; i < s4.size(); i++){
            peso4 = peso4 + s4.get(i)*objetos.get(i).peso;
        }
          
        int f = beneficio;
        if(peso1 > mochila1.getPesoMaximo())
            f = f - 1000*(peso1 - mochila1.getPesoMaximo());
        if(peso2 > mochila2.getPesoMaximo())
            f = f - 1000*(peso2 - mochila2.getPesoMaximo());
        if(peso3 > mochila3.getPesoMaximo())
            f = f - 1000*(peso3 - mochila3.getPesoMaximo());
        if(peso4 > mochila4.getPesoMaximo())
            f = f - 1000*(peso4 - mochila4.getPesoMaximo());
        
        return f;
    }
    
}