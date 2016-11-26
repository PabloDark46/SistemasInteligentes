package Problema;

import java.util.ArrayList;

public class Algoritmos {
	private static Frontera f;
	private static EspacioEstados e;
	
	public static ArrayList<Nodo> BusquedaAcotada(Problema p, String estrategia, int prof_max){
		ArrayList<Nodo> lista_solucion = new ArrayList<Nodo>();
		ArrayList<Nodo> lista_nodos= new ArrayList<Nodo>();
		boolean essolucion = false;
		int costo = 0;
		e = new EspacioEstados(p.getEstadoInicial().getEstado(),p.getEstadoObjetivo().getEstado());
		Nodo n_inicial = new Nodo(p.getEstadoInicial().getEstado(),0,p.getEstadoInicial().getAccion(),0);
		f = new Frontera(n_inicial);
		
		do{
			Nodo actual = f.seleccionar();
			if (e.esObjetivo(actual.getEstado())){
				essolucion = true;
			}else{
				lista_solucion = e.sucesores(actual);
				for (int i=0;i<lista_solucion.size();i++){
					lista_nodos.add(new Nodo(actual.getEstado().clone(),costo++,e.actual.getAccion(),prof_max));	
				}
				f.insertar(lista_nodos);
			}	
		}while(!essolucion && !f.esVacia());
	
		return lista_solucion;
	}
		
	public static ArrayList<Nodo> Busqueda(Problema p, String estrategia, int prof_max, int inc_prof){
		ArrayList<Nodo> solucion = new ArrayList<Nodo>();
		int prof_actual = inc_prof;
		
		while(solucion!=null && prof_actual<=prof_max){
			solucion = BusquedaAcotada(p,estrategia,prof_actual);
			prof_actual += inc_prof;	
		}
		return solucion;
	}
}



