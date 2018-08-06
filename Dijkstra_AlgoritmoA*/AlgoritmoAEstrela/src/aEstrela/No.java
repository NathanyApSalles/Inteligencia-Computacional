package aEstrela;

public class No {
	private int x, y;
	double custoF;
	double custoG;
	public No(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
	}
	 
	public double getCustoF() {
		return custoF;
	}
	
	public double getCustoG() {
		return custoG;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setCustoF(double d) {
		this.custoF = d;
	}
	public void setCustoG(double d) {
		this.custoG = d;
	}
	@Override
	public boolean equals(Object object){
		boolean isEqual = false;
		if(object != null && object instanceof No){
			isEqual = (this.x == ((No) object).x) && (this.y == ((No) object).y);
		}
		return isEqual;
	}
}
