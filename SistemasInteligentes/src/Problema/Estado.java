package Problema;

import java.util.ArrayList;


public class Estado {

	private int[][] estado;
	private int filaHueco;
	private int columnaHueco;
	

	public Estado(int[][]estado,int filaHueco,int columnaHueco){
		this.estado=estado;
		this.filaHueco=filaHueco;
		this.columnaHueco=columnaHueco;
	}

	public int[][] getEstado() {
		return estado;
	}


	public int getFilaHueco() {
		return filaHueco;
	}

	public void setFilaHueco(int filaHueco) {
		this.filaHueco = filaHueco;
	}

	public int getColumnaHueco() {
		return columnaHueco;
	}	
	
	public void setColumnaHueco(int columnaHueco) {
		this.columnaHueco = columnaHueco;
	}


	public Estado clone(){
        
        int[][]eClon=new int[estado.length][estado[0].length];
    	
        for(int i=0;i<estado.length;i++)
    		for(int j=0;j<estado[0].length;j++)
    			eClon[i][j]=estado[i][j];
        
        
        return new Estado(eClon,this.filaHueco,this.columnaHueco);
    }
	
	//MOVIMIENTOS	

	public ArrayList<String> acciones(){
		ArrayList<String> movimientos = new ArrayList<String>();
		
		if (moverAbajo() == 0){
			movimientos.add("ABAJO");
			moverArriba();
			
		}
		if (moverArriba() == 0){
			movimientos.add("ARRIBA");
			moverAbajo();
		}
		
		if (moverDer() == 0){
			movimientos.add("DERECHA");
			moverIzq();
		}
		
		if (moverIzq() == 0){
			movimientos.add("IZQUIERDA");
			moverDer();
		}
	
		return movimientos;
	}	

	public int mover(String movimiento){
		int movido=1;
		
		switch(movimiento){
			case "ARRIBA":
				movido=moverArriba();
				break;
			case "ABAJO":
				movido=moverAbajo();
				break;
			case "IZQUIERDA":
				movido=moverIzq();
				break;
			case "DERECHA":
				movido=moverDer();
				break;

		}
		return movido;
	}

	public int moverIzq(){
		int correcto=1;
		if(getColumnaHueco()>0){
			intercambia(getFilaHueco(),getColumnaHueco(),getFilaHueco(),getColumnaHueco()-1);
			setColumnaHueco(getColumnaHueco()-1);
			correcto=0;
		}
			
		return correcto;
	}

	public int moverDer(){
		int correcto=1;
		if(getColumnaHueco()<getEstado().length-1){
			intercambia(getFilaHueco(),getColumnaHueco(),getFilaHueco(),getColumnaHueco()+1);
			setColumnaHueco(getColumnaHueco()+1);
			correcto=0;
		}
		
		return correcto;
	}

	public int moverArriba(){
		int correcto=1;
		if(getFilaHueco()>0){
			intercambia(getFilaHueco(),getColumnaHueco(),getFilaHueco()-1,getColumnaHueco());
			setFilaHueco(getFilaHueco()-1);
			correcto=0;
		}
		
		return correcto;
	}

	public int moverAbajo(){
		int correcto=1;
		if(getFilaHueco()<getEstado()[0].length-1){
			intercambia(getFilaHueco(),getColumnaHueco(),getFilaHueco()+1,getColumnaHueco());
			setFilaHueco(getFilaHueco()+1);
			correcto=0;
		}
		
		return correcto;
	}	
	public void intercambia(int filaOrigen,int columnaOrigen,int filaDestino,int columnaDestino){
		int aux=this.getEstado()[filaOrigen][columnaOrigen];
		int destino=this.getEstado()[filaDestino][columnaDestino];
		this.getEstado()[filaOrigen][columnaOrigen]=destino;
		this.getEstado()[filaDestino][columnaDestino]=aux;
		
	}
	//MOVIMIENTOS
	public String toString(){
		String s="";
		for (int i = 0; i < estado.length; i++) {
			for (int j = 0; j < estado.length; j++) {
				s+=estado[i][j]+" ";
			}
		s+="\n";	
			
		}
		return s;
	}
	
}