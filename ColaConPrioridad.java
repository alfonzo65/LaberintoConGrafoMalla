// ColaConPrioridad.java

// realizado por:
// Nombre: Jesus morales
// C.I : 22.376.391


public class ColaConPrioridad{

	private Arista primero;
	private Arista ultimo;

	public ColaConPrioridad(){
		primero = null;
		ultimo = null;
	}

	public void insertar( int vOrigen , int vDestino , double peso ){

		Arista nuevo = new Arista( vOrigen , vDestino , peso );

		if( estaVacia() ){
			primero = nuevo;
			ultimo = nuevo;
		}else{
			
			if( peso <= primero.obtenerPeso() ){
				nuevo.sig = primero;
				primero = nuevo;
			}else{

				Arista actual = primero;
				Arista anterior = null;

				while( actual != null && peso > actual.obtenerPeso() ){
					anterior = actual;
					actual = actual.sig;
				}

				if( actual == null ){
					ultimo.sig = nuevo;
					ultimo = nuevo;
				}else{
					anterior.sig = nuevo;
					nuevo.sig = actual;
				}

			}// fin else

		}// fin else externo

	}// fin metodo insertar en al cola

	public boolean estaVacia(){
		return primero == null;
	}

	public Arista sacarDeLaCola(){

		if( !estaVacia() ){

			if( primero == ultimo ){
				Arista aux = primero;
				primero = null;
				ultimo = null;
				return aux;
			}

			Arista temp = primero;
			primero = primero.sig;
			return temp;
		}

		return null;
	}

	public void imprimirCola(){

		Arista temp = primero;

		while( temp != null ){

			System.out.print( "(" + temp.getOrigen() +"|"+ temp.getDestino() +"|"+
			temp.obtenerPeso() + ")" );

			System.out.println();

			temp = temp.sig;

		}

		System.out.println("NUll");
	}
	
}// fin de cla clase colaconprioridad
