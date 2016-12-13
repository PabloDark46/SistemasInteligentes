package Problema;

import java.util.ArrayList;


public class Algoritmos {

	public static ArrayList<Nodo> Busqueda(Problema p, String estrategia, int limiteProfundidad, int profundidadMaxima) {
		ArrayList<Nodo> solucion = new ArrayList<Nodo>();
		int prof_costo = 0;
		while (prof_costo < limiteProfundidad && solucion.size()==0) {
			solucion = BusquedaSolucion(p, estrategia, prof_costo, profundidadMaxima);
			prof_costo++;
		}
		return solucion;
	}

	public static ArrayList<Nodo> BusquedaSolucion(Problema p, String estrategia, int costo_prof,int profundidadMaxima) {
		ArrayList<Nodo> lista_solucion = new ArrayList<Nodo>();
		boolean solucion = false;
		Nodo actual=p.getEstadoInicial();
		Frontera f = new Frontera(actual);
		ArrayList<Nodo>listaSucesores;
		ArrayList<Nodo>listaNodos=new ArrayList<Nodo>();
		while (!solucion && !f.esVacia()) {
			actual = f.seleccionar();
			listaNodos.add(actual);
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
				for(int a=0; a<listaSucesores.size(); a++){
					for(int b=0; b<listaNodos.size(); b++){
						if (listaSucesores.get(a)!=null&& listaSucesores.get(a).getEstado().equals(listaNodos.get(b).getEstado())){
							listaSucesores.set(a,null);
						}
					}
				}
				while(listaSucesores.remove(null));
				CrearListaNodosArbol(listaSucesores, actual, costo_prof++, estrategia, profundidadMaxima,f);				
				f.insertar(listaSucesores);
			}	
		}
		return lista_solucion;
	}

	public static void CrearListaNodosArbol(ArrayList<Nodo> listaSucesores, Nodo actual, int costo_prof,String estrategia, int profundidadMaxima,Frontera frontera){
		switch (estrategia) {
			case "ANCHURA":
				anchura(listaSucesores, actual);
				break;
			case "PROFUNDIDAD":
				profundidad(listaSucesores, actual);
				break;
			case "PROFUNDIDADACOTADA":
				acotada(listaSucesores, actual, costo_prof, profundidadMaxima);
				break;
			case "PROFUNDIDADITERATIVA":
				iterativa(listaSucesores, actual, costo_prof, profundidadMaxima);
				break;
			case "COSTOUNIFORME":
				costouniforme(listaSucesores, actual);
				break;
			case "A*":
				asterisco(listaSucesores,actual,frontera);
				break;
		}
	}

	public static void anchura(ArrayList<Nodo> listaSucesores, Nodo actual) {
		for (int i = 0; i < listaSucesores.size(); i++) {
			listaSucesores.get(i).setValor(actual.getValor()+1);
		}
	}

	public static void profundidad(ArrayList<Nodo> listaSucesores, Nodo actual) {
		for (int i = 1; i < listaSucesores.size(); i++) {
			listaSucesores.get(i).setValor(actual.getValor()+i);
		}
	}

	public static void acotada(ArrayList<Nodo> listaSucesores, Nodo actual, int prof_actual, int profundidadMaxima) {
		if (prof_actual < profundidadMaxima) {
			for (int i = 1; i < listaSucesores.size(); i++) {
				listaSucesores.get(i).setValor(i);
			}
		}
	}

	public static void iterativa(ArrayList<Nodo> listaSucesores, Nodo actual, int prof_actual, int profundidadMaxima) {
		for (int i = 1; i < listaSucesores.size(); i++) {
			listaSucesores.get(i).setValor(i);
		}
		profundidadMaxima = prof_actual;
	}

	public static void costouniforme(ArrayList<Nodo> listaSucesores, Nodo actual) {
		for (int i = 0; i < listaSucesores.size(); i++) {
			listaSucesores.get(i).setValor(actual.getCosto() + 1);
		}

	}
	public static void asterisco(ArrayList<Nodo> listaSucesores, Nodo actual, Frontera f){
		for (int i = 0; i < listaSucesores.size(); i++) {
			listaSucesores.get(i).setH();
			listaSucesores.get(i).setValor(actual.getCosto()+1+listaSucesores.get(i).getH());	
		}

	}
}
