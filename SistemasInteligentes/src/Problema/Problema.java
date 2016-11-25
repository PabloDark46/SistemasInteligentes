package Problema;

import java.util.ArrayList;

public class Problema {


	private Estado estadoInicial;
	private Estado estadoObjetivo;
	private EspacioEstados espacio;
	public Problema(Estado estadoInicial, Estado estadoObjetivo){
		this.estadoInicial=new Nodo(estadoInicial);
		this.estadoObjetivo=new Nodo(estadoObjetivo);
		
	}
	

	public Nodo getEstadoInicial() {
		return estadoInicial;
	}


	public Nodo getEstadoObjetivo() {
		return estadoObjetivo;
	}




	
}
