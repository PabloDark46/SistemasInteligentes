package Problema;

import java.util.ArrayList;

public class Algoritmos {

	public static ArrayList<Nodo> Busqueda(Problema p, String estrategia, int limiteProfundidad) {
		ArrayList<Nodo> solucion = new ArrayList<Nodo>();
		int prof_costo = 0;
		while (prof_costo < limiteProfundidad) {
			solucion = BusquedaSolucion(p, estrategia, prof_costo, limiteProfundidad);
			prof_costo++;
		}
		return solucion;
	}

	public static ArrayList<Nodo> BusquedaSolucion(Problema p, String estrategia, int costo_prof,int limiteProfundidad) {
		ArrayList<Nodo> lista_solucion = new ArrayList<Nodo>();
		boolean solucion = false;
		Nodo actual=p.getEstadoInicial();
		Frontera f = new Frontera(actual);
		ArrayList<Nodo>listaSucesores;
		
		while (!solucion && !f.esVacia()) {
			actual = f.seleccionar();
			
			if (p.getEspacio().esObjetivo(actual.getEstado())) {
				solucion = true;
				Nodo recorrido = actual;
				lista_solucion.add(recorrido);
				
				while (recorrido.getPadre() != null) {
					lista_solucion.add(recorrido.getPadre());
					recorrido = recorrido.getPadre();
					
				}
				
			} else {
				listaSucesores=p.getEspacio().sucesores(actual);
				CrearListaNodosArbol(listaSucesores, actual, costo_prof++, estrategia, limiteProfundidad);
				f.insertar(listaSucesores);
				
			}	
		}
		return lista_solucion;
	}

	public static void CrearListaNodosArbol(ArrayList<Nodo> lista_solucion, Nodo actual, int costo_prof,String estrategia, int limiteProfundidad) {
		switch (estrategia) {
			case "ANCHURA":
				anchura(lista_solucion, actual);
				break;
			case "PROFUNDIDAD":
				profundidad(lista_solucion, actual);
				break;
			case "PROFUNDIDADACOTADA":
				acotada(lista_solucion, actual, costo_prof, limiteProfundidad);
				break;
			case "PROFUNDIDADITERATIVA":
				iterativa(lista_solucion, actual, costo_prof, limiteProfundidad);
				break;
			case "COSTOUNIFORME":
				costouniforme(lista_solucion, actual);
				break;
		}
	}

	public static void anchura(ArrayList<Nodo> lista_solucion, Nodo actual) {
		for (int i = 0; i < lista_solucion.size(); i++) {
			lista_solucion.get(i).setValor(lista_solucion.get(i).getCosto());
		}
	}

	public static void profundidad(ArrayList<Nodo> lista_solucion, Nodo actual) {
		for (int i = 0; i < lista_solucion.size(); i++) {
			lista_solucion.get(i).setValor(i);
		}
	}

	public static void acotada(ArrayList<Nodo> lista_solucion, Nodo actual, int prof_actual, int limiteProfundidad) {
		if (prof_actual < limiteProfundidad) {
			for (int i = 0; i < lista_solucion.size(); i++) {
				lista_solucion.get(i).setValor(i);
			}
		}
	}

	public static void iterativa(ArrayList<Nodo> lista_solucion, Nodo actual, int prof_actual, int limiteProfundidad) {
		for (int i = 0; i < lista_solucion.size(); i++) {
			lista_solucion.get(i).setValor(i);
		}
		limiteProfundidad = prof_actual;

	}

	public static void costouniforme(ArrayList<Nodo> lista_solucion, Nodo actual) {
		for (int i = 0; i < lista_solucion.size(); i++) {
			lista_solucion.get(i).setValor(actual.getCosto() + 1);
		}

	}
}
