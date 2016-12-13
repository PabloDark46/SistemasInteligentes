package Problema;

import java.util.ArrayList;
import java.util.Comparator;

public class Frontera {
	// PriorityQueue<Nodo> frontera;
	ArrayList<Nodo> frontera;
	
	public Frontera(Nodo estadoInicial) {
		frontera = new ArrayList<Nodo>();
		insertar(estadoInicial);
	}

	
	public ArrayList<Nodo> getFrontera() {
		return frontera;
	}


	// OPERACIONES
	public void insertar(ArrayList<Nodo> suc) {

		for (int i = 0; i < suc.size(); i++){
			
			insertar(suc.get(i));
		}

	}

	public void insertar(Nodo nodo) {
		frontera.add(nodo);
		Comparator<Nodo> c = Comparator.naturalOrder();
		frontera.sort(c);

	}

	public Nodo seleccionar() {
		return frontera.remove(0);
	}
	
	public boolean esVacia() {
		return frontera.isEmpty();
	}

	public String toString() {

		return frontera.toString();
	}
	// OPERACIONES

}
