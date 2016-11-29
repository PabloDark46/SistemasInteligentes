package Problema;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Frontera{
	//PriorityQueue<Nodo> frontera;
	ArrayList<Nodo> frontera;
	
	public Frontera(Nodo estadoInicial) {
		frontera=new ArrayList<Nodo>();
		insertar(estadoInicial);
	}	
	
	
	//OPERACIONES

	public ArrayList<Nodo> getFrontera() {
		return frontera;
	}

	public void insertar(Nodo nodo){
		frontera.add(nodo);
		Comparator<Nodo>c=Comparator.naturalOrder();
		frontera.sort(c);
	}
	public void insertar(ArrayList<Nodo> suc){
		
		for (int i = 0; i < suc.size(); i++)
			insertar(suc.get(i));
		
	}
	
	public Nodo seleccionar(){
		return frontera.remove(0);
	}
	
	public boolean esVacia(){
		return frontera.isEmpty();
	}
	
	public String toString(){
	   
	   return frontera.toString();
	}
	//OPERACIONES
	
	/*public Frontera(Nodo estadoInicial) {
	frontera=new PriorityQueue<Nodo>();
	insertar(estadoInicial);
	}
	
	public PriorityQueue<Nodo> getFrontera() {
	return frontera;
	}
	
	public void insertar(Nodo nodo){
		List b=new List();
		b.so
		frontera.add(nodo);
		ArrayList<Nodo>a=new ArrayList<Nodo>();
		Comparator<?super Nodo>c=new Comparator<?super Nodo>();
		a.sort(c);
	}
	
	public void insertar(ArrayList<Nodo> suc){
		
		for (int i = 0; i < suc.size(); i++)
			insertar(suc.get(i));
		
	}
	
	public Nodo seleccionar(){
		return frontera.remove();
	}*/
}

