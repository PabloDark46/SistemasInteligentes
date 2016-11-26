package Problema;

import java.util.ArrayList;

public class Problema {


	private Nodo estadoInicial;
	private Nodo estadoObjetivo;
	private EspacioEstados espacio;
	
	public Problema(Estado estadoInicial, Estado estadoObjetivo){
		this.estadoInicial=new Nodo(estadoInicial);
		this.estadoObjetivo=new Nodo(estadoObjetivo);
		this.espacio=new EspacioEstados(this.estadoInicial.getEstado(),this.estadoObjetivo.getEstado());
		
	}
	

	public Nodo getEstadoInicial() {
		return estadoInicial;
	}


	public Nodo getEstadoObjetivo() {
		return estadoObjetivo;
	}	
}




	

