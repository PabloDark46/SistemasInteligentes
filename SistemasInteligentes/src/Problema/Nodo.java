package Problema;
public class Nodo implements Comparable<Nodo>{
	
	Nodo padre;
	Estado e;
	int costo;
	String accion;
	int valor;
	int profundidad;
	int h;
	
	public Nodo(Estado e, int costo, String accion, int valor,Nodo padre){
		this.e = e;
		this.costo = costo;
		this.accion = accion;
		this.valor = valor;
		this.padre=padre;
	}
	
	public Nodo(Estado e, int costo, String accion,Nodo padre){
		this.e = e;
		this.costo = costo;
		this.accion = accion;
		this.padre=padre;
	}
	
	public Nodo(Estado e){
		this.e = e;
		this.accion = "NADA";
	}
	
	public Nodo getPadre() {
		return padre;
	}
	public void setH(){
		int posEsperada=0;
		for (int i = 0; i < e.getEstado().length; i++) {
			for (int j = 0; j < e.getEstado()[0].length; j++) {
				if(e.getEstado()[i][j]!=posEsperada){
					this.h+=1;
				}
			posEsperada+=1;		
			}
		}
	}
	
	public int getH() {
		return h;
	}

	public Estado getEstado() {
		return e;
	}
	public void setEstado(Estado e) {
		this.e = e;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public int compareTo(Nodo nodo) {
	    int valor=0;  
		if (valor > nodo.valor) {
			valor=-1;
		}else if (valor < nodo.valor) {
			valor=1;
		}
		return valor;
		
	}
	public String toString(){
		return String.valueOf(this.valor);
	}
}
