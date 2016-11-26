package Problema;
import java.util.ArrayList;

public class EspacioEstados {
	
	private int costoGlobal;
	Nodo actual, objetivo;
	public EspacioEstados(Estado estadoInicial,Estado estadoObjetivo){
		
		actual=new Nodo(estadoInicial);
		objetivo=new Nodo(estadoObjetivo);
		
	}
	
	public ArrayList<Nodo> sucesores(Nodo e){
		ArrayList<String> acciones=e.getEstado().acciones();
		switch (e.accion){
			case "ARRIBA":
				acciones.remove("ABAJO");
				break;
			case "ABAJO":
				acciones.remove("ARRIBA");
				break;
			case "IZQUIERDA":
				acciones.remove("DERECHA");
				break;
			case "DERECHA":
				acciones.remove("IZQUIERDA");
				break;
		}
		ArrayList<Nodo> sucesores=new ArrayList<Nodo>();
		this.costoGlobal++;
		for(int i=0;i<acciones.size();i++){
			
			sucesores.add(new Nodo(e.getEstado().clone(),costoGlobal,acciones.get(i),e));
			sucesores.get(sucesores.size()-1).getEstado().mover(acciones.get(i));
			
		}
		
		return sucesores;
			
	}
	
	//OPERACIONES ESTADOS

	public boolean esObjetivo(Estado actual, Estado objetivo) {

		boolean ordenado = false, error = false;
		
		if (actual.getFilaHueco() == objetivo.getFilaHueco()&& actual.getColumnaHueco() == objetivo.getColumnaHueco()) {
			for (int i = 0; i < actual.getEstado().length && !error; i++) {
				for (int j = 0; j < actual.getEstado()[0].length && !error; j++) {
					if (actual.getEstado()[i][j] != objetivo.getEstado()[i][j])
						error = true;
				}

			}
		}
		if (!error) {
			ordenado = true;
		}
		return ordenado;
	} 
	
	public boolean esObjetivo(Estado actual){
		boolean objetivo;
		
		objetivo=esObjetivo(actual,this.objetivo.getEstado());
	
		
		return objetivo;
	}
	//OPERACIONES
}
