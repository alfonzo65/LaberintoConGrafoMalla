// Arista.java

// realizado por:
// Nombre: Jesus morales
// C.I : 22.376.391

import java.util.Random;

public class Arista{

	private Random numero = new Random(); 
	private double peso;
	private int v1;
	private int v2;
	Arista sig;

	public Arista( int vOrigen , int vDestino ){
		v1 = vOrigen;
		v2 = vDestino;
		peso = generarNumero(); 
		sig = null;
	}

	public Arista( int vOrigen , int vDestino , double peso ){
		v1 = vOrigen;
		v2 = vDestino;
		this.peso = peso;
		sig = null;
	}

	private double generarNumero(){
		//return  1 + Math.random()*30;
		return numero.nextInt(2);
	}

	public int extremo(){
		return getOrigen();
	}

	public int otroExtremo( int v ){
		if( v == getOrigen() )
			return getDestino();

		return getOrigen();
	}

	public int getOrigen(){
		return v1;
	}

	public int getDestino(){
		return v2;
	}

	public double obtenerPeso(){
		return peso;
	}
}