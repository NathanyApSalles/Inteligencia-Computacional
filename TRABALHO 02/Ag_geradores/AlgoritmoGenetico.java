package Ag_geradores;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class AlgoritmoGenetico {

    // Tamanho da população
    Integer tamanho;
    // Taxa de crossover - operador principal
    Double pCrossover;
    // Taxa de mutação - operador secundário
    Double pMutacao;
    // Critério de parada - número de gerações
    Integer geracoes;

    // Dados do problema
    Problema problema;
    // Mínimo
    Double minimo;
    // Máximo
    Double maximo;
    ArrayList<Integer> MOCHILA = new ArrayList<Integer>(){
		{
			add(0);
			add(1);
			add(2);
			add(3);
		}
	};
	ArrayList<Integer> CAPACIDADE = new ArrayList<Integer>(){
		{
			add(500);
			add(600);
			add(200);
			add(300);
		}
	};

    public AlgoritmoGenetico(Integer tamanho, Double pCrossover, Double pMutacao, Integer geracoes, Problema problema) {
        this.tamanho = tamanho;
        this.pCrossover = pCrossover;
        this.pMutacao = pMutacao;
        this.geracoes = geracoes;
        this.problema = problema;
      
    }

    Populacao populacao;
    Populacao novaPopulacao;
    Individuo melhorSolucao;
    ArrayList<Individuo> individuos = new ArrayList<>();
    
    public Individuo getMelhorSolucao() {
        return melhorSolucao;
    }

    public Double executar(int tipo) {
    	
    	

        populacao = new Populacao( tamanho, problema, MOCHILA.get(0),500);
        novaPopulacao = new Populacao( tamanho, problema,MOCHILA.get(0),500);
       
        // Criar a população
        populacao.criar();
        
        // Avaliar
        populacao.avaliar();

        Random rnd = new Random();
        

        Collections.shuffle(populacao.getIndividuos());
        

        for (int g = 1; g <= geracoes; g++) {
        	
        	Individuo pai1 = new Individuo(500);
            Individuo pai2 = new Individuo(500);
            int ind1, ind2, ind3, ind4;
            
        	ind1 = rnd.nextInt(this.tamanho);
            
            do {
                ind2 = rnd.nextInt(this.tamanho);
            } while (ind1 == ind2);
            
            do{
                ind3 = rnd.nextInt(this.tamanho);
            }while(ind1 == ind3 && ind2 == ind3);
            
            do {
                ind4 = rnd.nextInt(this.tamanho);
            } while (ind3 == ind4 && ind1 == ind4 && ind2 == ind4);

            
            for (int i = 0; i < this.tamanho; i++) {
            	
                // Crossover
                if (rnd.nextDouble() <= this.pCrossover) {
                    // Realizar a operação

                    Individuo desc1 = new Individuo(500);
                    Individuo desc2 = new Individuo(500);   
                    
                    switch (tipo) {
						case 1:
							pai1 = selecaoTorneio(problema, populacao, ind1, ind2);  
				            pai2 = selecaoTorneio(problema, populacao, ind3, ind4);			            
				            					
							break;
							
						case 2: 
							pai1 = selecaoUniforme(populacao,ind1);
							pai2 = selecaoUniforme(populacao, ind2);
							
				            break;
							
						case 3: 
							
							pai1 = selecaoRoleta(populacao);
							pai2 = selecaoRoleta(populacao);
							
				            break;
				            
						case 4:
							pai1 = selecaoUniforme(populacao,ind3);
							pai2 = selecaoUniforme(populacao, ind4);
							
							break;
					
						default:
							pai1 = selecaoTorneio(problema, populacao, ind1, ind2);  
				            pai2 = selecaoTorneio(problema, populacao, ind3, ind4);
				            
							break;
					}
                    
                    
                    int corte = rnd.nextInt(pai1.getCargas().size());
                    System.out.println("corte: " + corte + " tam: " + pai1.getCargas().size());
                    //Crossover
                    crossoverAritmetico(pai1, pai2, desc1,corte);
		            crossoverAritmetico(pai2, pai1, desc2,corte);	
                    
                    // Mutação
//                    mutacaoPorVariavel(desc1);
//                    mutacaoPorVariavel(desc2);

                    // Avaliar as novas soluções
                    problema.calcularFuncaoObjetivo(desc1);
                    problema.calcularFuncaoObjetivo(desc2);

                    // Inserir na nova população
                    novaPopulacao.getIndividuos().add(desc1);
                    novaPopulacao.getIndividuos().add(desc2);
                }
            }
             
            
            switch (tipo) {
	            case 6: 
	            	// elitismo
		            populacao.getIndividuos().addAll(novaPopulacao.getIndividuos());
		            
		            Collections.sort(populacao.getIndividuos());
	
		            populacao.getIndividuos().subList(this.tamanho,populacao.getIndividuos().size()).clear();
					break;
					
				case 4:
					// seleção uniforme
		            populacao.getIndividuos().addAll(novaPopulacao.getIndividuos());
		            
		            for (int i = 0; i < this.tamanho; i++) {
		            	individuos.add(selecaoUniforme(novaPopulacao,ind4));
						
					}            
		            
		            populacao.individuos.clear();
		            
		            populacao.getIndividuos().addAll(individuos);
					break;
				case 5:
					//seleção torneio
		            populacao.getIndividuos().addAll(novaPopulacao.getIndividuos());
		            
		            for (int i = 0; i < this.tamanho; i++) {
		            	individuos.add(selecaoTorneio(problema, novaPopulacao, ind3, ind4));
						
					}            
		            
		            populacao.getIndividuos().clear();
		            
		            populacao.getIndividuos().addAll(individuos);
					break;
	
				default: //case 1, 2 e 3 
					//seleção Roleta
		            populacao.getIndividuos().addAll(novaPopulacao.getIndividuos());
		            
		            for (int i = 0; i < this.tamanho; i++) {
		            	individuos.add(selecaoRoleta(novaPopulacao));
						
					}            
		            
		            populacao.getIndividuos().clear();
		            
		            populacao.getIndividuos().addAll(individuos);
					
					break;
			    
			}
     
            // Limpa a nova população para a geração seguinte
            novaPopulacao.getIndividuos().clear();

            // Imprimir a situacao atual
//            System.out.println("Gen = " + g + "\tCusto = " + populacao.getIndividuos().get(0).getFuncaoObjetivo());
//            gerarCsv(populacao, tipo, g);
            
           
        }

        return populacao.getIndividuos().get(0).getFuncaoObjetivo();

    }
    private void crossoverAritmetico(Individuo ind1, Individuo ind2, Individuo descendente, int corte) {
    	

        Random rnd = new Random();
        Double alpha = rnd.nextDouble();

        for (int i = 0; i < corte; i++) {
            Integer valor = (int) (alpha * ind1.getCargas().get(i));
            descendente.getCargas().add(valor);
        }
        
        if(corte < ind2.getCargas().size()){
        	for (int i = corte; i < ind2.getCargas().size(); i++) {
                Integer valor = (int) ((1.0 - alpha) * ind2.getCargas().get(i));
                descendente.getCargas().add(valor);
            } 
        }

              
   

    }
    
    private Individuo selecaoUniforme(Populacao populacao, int ind1) {
		return populacao.getIndividuos().get(ind1);
	}
    
    private Individuo selecaoTorneio(Problema problema, Populacao populacao, int ind1, int ind2){
    	
    	Random rnd = new Random();
    	Individuo pai1 = new Individuo(500);
    	
        // Progenitores
        Individuo p1 = populacao.getIndividuos().get(ind1);
        Individuo p2 = populacao.getIndividuos().get(ind2);
       
        problema.calcularFuncaoObjetivo(p1);
        problema.calcularFuncaoObjetivo(p2);                
          
        
        if (p1.getFuncaoObjetivo() < p2.getFuncaoObjetivo() ){
        	
        	pai1 = p1;
        	
        	
        }else{
        	pai1 = p2;
        }
        
        problema.calcularFuncaoObjetivo(pai1);
      
        return pai1;
    }
    
    public Individuo selecaoRoleta(Populacao populacao){
    	//função de aptidão será f(xi)/somatório(fxk) com k de 0 a tamanho da população
    	ArrayList<Double> aptidao = new ArrayList<>();
    	Double somatorio = 0.0;
    	
    	for (int i = 0; i < populacao.getTamanho(); i++) {
    		somatorio += populacao.getIndividuos().get(i).getFuncaoObjetivo();
			
    	}
    	
    	for (int i = 0; i < populacao.getTamanho(); i++) {
			aptidao.add((populacao.getIndividuos().get(i).getFuncaoObjetivo())/somatorio);
		}
    	
    	//soma dos valores de aptidão de todos os indivíduos da população    	
    	Double T = 0.0;
    	//soma das aptidões até o índice corrente
    	Double S = 0.0;
    	//valor aleatório entre 0 e T
    	Double r = 0.0;
    	//posição do indivíduo selecionado
    	int pos = 0; 
    	
    	Random random = new Random();
    	//numero de vezes para fazer a repetição
    	int N = random.nextInt(50);
    		
    	for (int i = 0; i < aptidao.size(); i++) {
			T += aptidao.get(i);
		}
    	
    	r = random.nextDouble()*T;
    	
    	for (int i = 0; i < N; i++) {
    		
    		for (int j = 0; j < aptidao.size(); j++) {
    			S += aptidao.get(j);
    			if(S >= r){
    				pos = j;
    			} 
    						
			}
			
		} 	
    	
    	
    	
    	return populacao.getIndividuos().get(pos);
    }
        

//    private void mutacaoPorVariavel(Individuo individuo) {
//
//        Random rnd = new Random();
//        
//        if (rnd.nextDouble() <= this.pMutacao) {
//
//            // Mutacao aritmetica
//            // Multiplicar rnd e inverter ou nao o sinal
//            Double valorX = individuo.getX();
//            Double valorY = individuo.getY();
//            // Multiplica por rnd
//            valorX *= rnd.nextDouble();
//            valorY *= rnd.nextDouble();
//
//            // Inverter o sinal
//            if (!rnd.nextBoolean()) {
//                valorX = -valorX;
//                valorY = -valorY;
//            }
//
//            if (valorX >= this.minimo
//                    && valorX <= this.maximo) {
//                individuo.setX(valorX);
//
//            }
//            
//            if (valorY >= this.minimo
//                    && valorY <= this.maximo) {
//                individuo.setY(valorY);
//
//            }
//        }
//    }

}
