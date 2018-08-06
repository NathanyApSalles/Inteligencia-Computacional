
package Ag_funcao;

import java.util.ArrayList;
import java.util.Random;

public class Individuo implements Comparable<Individuo>{
    
    // Genótipo+Fenotipo
    private Double x;
    private Double y;
    
    // Custo da função objetivo
    Double funcaoObjetivo;
    
    // Valor mínimo
    Double minimo;
    // Valor máximo
    Double maximo;
    


    public Individuo(Double minimo, Double maximo) {
        this.minimo = minimo;
        this.maximo = maximo;
        
    }

    
    public Double getFuncaoObjetivo() {
        return funcaoObjetivo;
    }

    public void setFuncaoObjetivo(Double funcaoObjetivo) {
        this.funcaoObjetivo = funcaoObjetivo;
    }

    public Double getX() {
		return x;
	}


	public void setX(Double x) {
		this.x = x;
	}


	public Double getY() {
		return y;
	}


	public void setY(Double y) {
		this.y = y;
	}
    public Double getMinimo() {
        return minimo;
    }

    public void setMinimo(Double minimo) {
        this.minimo = minimo;
    }

    public Double getMaximo() {
        return maximo;
    }

    public void setMaximo(Double maximo) {
        this.maximo = maximo;
    }

    
    // Gerar o genótipo
    public void criar() {        
                
        Random rnd = new Random();
        Double valorX;
        Double valorY;     
        
       
	    valorX = this.minimo + 
	            ( this.maximo - this.minimo )
	            * rnd.nextDouble();
	    this.x = valorX;
	    
	    valorY = this.minimo + 
	            ( this.maximo - this.minimo )
	            * rnd.nextDouble();
	    this.y = valorY;
            
            
                
    }
     
    @Override
    public int compareTo(Individuo o) {
        return this.getFuncaoObjetivo()
                .compareTo(o.getFuncaoObjetivo());
    }
}
