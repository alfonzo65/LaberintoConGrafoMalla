// Lista.java

// realizado por:
// Nombre: Jesus morales
// C.I : 22.376.391

public class Lista{

	Arista primero;
	private int gradoDeVertice;

	public Lista(){
		primero = null;
		gradoDeVertice = 0;
	}

	public void meterEnLista( int v1 , int v2 ){

		Arista nuevo = new Arista( v1 , v2 );

		if( estaVacia() ){
			primero = nuevo;
		}
		else{

			Arista actual = primero;
			Arista anterior = null;

			while( actual != null ){
				anterior = actual;
				actual = actual.sig;
			}

			anterior.sig = nuevo;
		}

		incrementarGrado();
	}

	public void meterEnLista( int v1 , int v2 , double peso ){

		Arista nuevo = new Arista( v1 , v2 , peso );

		if( estaVacia() ){
			primero = nuevo;
		}
		else{

			Arista actual = primero;
			Arista anterior = null;

			while( actual != null ){
				anterior = actual;
				actual = actual.sig;
			}

			anterior.sig = nuevo;
		}

		incrementarGrado();
	}

	private void incrementarGrado(){
		gradoDeVertice++;
	}

	public int getGrado(){
		return gradoDeVertice;
	}

	public boolean estaVacia(){
		return primero == null;
	}

	public Arista obtenerLista(){
		if( estaVacia() )
			return null;

		return primero;
	}

	public boolean estaEnLaLista( Arista a ){

		if( !estaVacia() ){
			Arista actual = primero;
			while( actual != null ){

				if( ( actual.getOrigen() == a.getOrigen() ) && 
					( actual.getDestino() == a.getDestino() ) )
					return true;

				actual = actual.sig;
			}
		}

		return false;
	}

}