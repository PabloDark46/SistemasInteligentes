package Problema;
import java.util.ArrayList;

public class EspacioEstados {
	
	private int limiteProfundidad;
	Nodo actual, objetivo;
	
	public EspacioEstados(Estado estadoInicial,Estado estadoObjetivo){
		actual=new Nodo(estadoInicial);
		objetivo=new Nodo(estadoObjetivo);
	}
	
	public int getLimiteProfundidad(){
		return limiteProfundidad;
	}
	
	public void setLimiteProfundidad(int limiteProfundidad){
		this.limiteProfundidad=limiteProfundidad;
	}
	
	public ArrayList<Nodo> sucesores(Nodo e){
		ArrayList<String> acciones=e.getEstado().acciones();
		ArrayList<Nodo> sucesores=new ArrayList<Nodo>();

		for(int i=0;i<acciones.size();i++){
			sucesores.add(new Nodo(e.getEstado().clone(),e.getCosto()+1,acciones.get(i),e));
			System.out.println(sucesores.get(sucesores.size()-1).getEstado());
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
		}else{
			error=true;
		}
		if (!error) {
			ordenado = true;
		}
		
		return ordenado;
	} 
	
	public boolean esObjetivo(Estado actual){
				
		boolean objetivo=esObjetivo(actual,this.objetivo.getEstado());
		return objetivo;
	}
	//OPERACIONES
}
