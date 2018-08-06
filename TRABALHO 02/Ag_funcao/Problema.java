package Ag_funcao;
public class Problema {
    
    public void calcularFuncaoObjetivo(Individuo individuo) {
        
    	Double result = 0.0;
    	
    	Double sincX = 0.0;
    	
    	Double sincY = 0.0;
    	
    	result = ((1 + sinc(individuo.getX()))/2)*((1 + sinc(individuo.getY()))/2);   	
    	
    	
        individuo.setFuncaoObjetivo(result);
        
    }
    
    public Double sinc(Double val){
    	
    	Double sinc = 0.0;
    	sinc = Math.sin(Math.PI * (val - 5))/(Math.PI * (val - 5));
    	
    	return sinc;
    	
    } 
    
}
