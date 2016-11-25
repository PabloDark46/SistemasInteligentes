package Problema;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Frontera{
	PriorityQueue<Nodo> frontera = new PriorityQueue <Nodo>();
	
	public Frontera(Nodo estadoInicial) {
		frontera=new PriorityQueue<Nodo>();
		insertar(estadoInicial);
	}
		
	public PriorityQueue<Nodo> getFrontera() {
		return frontera;
	}
	
	//OPERACIONES

	
	public void insertar(Nodo nodo){
		frontera.add(nodo);
	}
	public void insertar(ArrayList<Nodo> suc){
		
		for (int i = 0; i < suc.size(); i++)
			insertar(suc.get(i));
		
	}
	public Nodo seleccionar(){
		return frontera.remove();
	}
	
	public boolean esVacia(){
		return frontera.isEmpty();
	}
	
	public String toString(){
	   
	   return frontera.toString();
	}
	//OPERACIONES
}

