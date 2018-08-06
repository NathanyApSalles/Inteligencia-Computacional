package aEstrela;

import java.util.ArrayList;

public class ResultadoBusca {
	private double custoTotal;
	private double setCustoMostar;
	ArrayList<No> caminhoPercorrido = new ArrayList<No>();
	ArrayList<No> caminhoVisitado = new ArrayList<No>();
	public ResultadoBusca() {
		this.custoTotal = 0;
		// TODO Auto-generated constructor stub
	}
	public double getCustoTotal() {
		return custoTotal;
	}
	public void setCustoTotal(double d) {
		this.custoTotal = d;
	}
	public ArrayList<No> getCaminhoPercorrido() {
		return caminhoPercorrido;
	}
	public void setCaminhoPercorrido(ArrayList<No> caminhoPercorrido) {
		this.caminhoPercorrido = caminhoPercorrido;
	}
	public ArrayList<No> getCaminhoVisitado() {
		return caminhoVisitado;
	}
	public void setCaminhoVisitado(ArrayList<No> caminhoVisitado) {
		this.caminhoVisitado = caminhoVisitado;
	}
	public double getSetCustoMostar() {
		return setCustoMostar;
	}
	public void setSetCustoMostar(double setCustoMostar) {
		this.setCustoMostar = setCustoMostar;
	}
}
