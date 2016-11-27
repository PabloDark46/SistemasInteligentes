package Problema;

import java.util.ArrayList;

public class Algoritmos {
	private static Frontera f;
	private static EspacioEstados e;
	
	public static ArrayList<Nodo> Busqueda(Problema p, String estrategia, int limiteProfundidad){
		ArrayList<Nodo> solucion = new ArrayList<Nodo>();
		solucion = BusquedaSolucion(p,estrategia,0,limiteProfundidad);	
		return solucion;
	}
	
	public static ArrayList<Nodo> BusquedaSolucion(Problema p, String estrategia, int costo_prof, int limiteProfundidad){
		ArrayList<Nodo> lista_solucion = new ArrayList<Nodo>();
		boolean essolucion = false;
		e = new EspacioEstados(p.getEstadoInicial().getEstado(),p.getEstadoObjetivo().getEstado());
		Nodo n_inicial = new Nodo(p.getEstadoInicial().getEstado(),0,p.getEstadoInicial().getAccion(),0,null);
		f = new Frontera(n_inicial);
		
		do{
		Nodo actual = f.seleccionar();
			if (e.esObjetivo(actual.getEstado())){
				essolucion = true;
			}else{
				lista_solucion = e.sucesores(actual);
				CrearListaNodosArbol(lista_solucion, actual, costo_prof++, estrategia,limiteProfundidad);
				f.insertar(lista_solucion);
			}	
		}while(!essolucion && !f.esVacia());
		return lista_solucion;
	}
	
	
	public static void CrearListaNodosArbol(ArrayList<Nodo> lista_solucion, Nodo actual, int costo_prof,  String estrategia, int limiteProfundidad) {
		switch(estrategia){
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
			iterativa(lista_solucion, actual, costo_prof,limiteProfundidad);
			break;
		case "COSTOUNIFORME":
			costouniforme(lista_solucion, actual);
			break;	
	}
	}

	public static void anchura(ArrayList<Nodo> lista_solucion, Nodo actual) {
		for (int i=0; i<lista_solucion.size();i++){
			lista_solucion.get(i).setValor(actual.getValor()+1);
		}
	}

	
	public static void profundidad(ArrayList<Nodo> lista_solucion, Nodo actual) {
		for (int i=0; i<lista_solucion.size();i++){
			lista_solucion.get(i).setValor(i);
		}
	}
	
	public static void acotada(ArrayList<Nodo> lista_solucion, Nodo actual, int prof_actual, int limiteProfundidad) {
		if (prof_actual<limiteProfundidad){
		for (int i=0; i<lista_solucion.size();i++){
			lista_solucion.get(i).setValor(i);
		}
		}
	}

	public static void iterativa(ArrayList<Nodo> lista_solucion, Nodo actual, int prof_actual, int limiteProfundidad) {
		for (int i=0; i<lista_solucion.size();i++){
			lista_solucion.get(i).setValor(i);
		}
		limiteProfundidad=prof_actual;
		
	}

	public static void costouniforme(ArrayList<Nodo> lista_solucion, Nodo actual) {
		for (int i=0; i<lista_solucion.size();i++){
			lista_solucion.get(i).setValor(actual.getCosto()+1);
		}
		
	}

	


	
}



