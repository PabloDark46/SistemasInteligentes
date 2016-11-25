package Problema;
public class Nodo implements Comparable<Nodo>{
	
	Nodo padre;
	Estado e;
	int costo;
	String accion;
	int valor;

	public Nodo(Estado e, int costo, String accion, int valor){
		
		this.e = e;
		this.costo = costo;
		this.accion = accion;
		this.valor = valor;
		this.padre=null;
	}
	
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
		if (valor < nodo.valor) {
			valor=-1;
		}
		if (valor > nodo.valor) {
			valor=1;
		}
		return valor;
	}
}
