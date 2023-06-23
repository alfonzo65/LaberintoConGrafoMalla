// ColaConPrioridadDistancia.java

// realizado por:
// Nombre: Jesus morales
// C.I : 22.376.391

public class ColaConPrioridadDistancia{

	private VerticeConDistancia primero;
	private VerticeConDistancia ultimo;

	public ColaConPrioridadDistancia(){
		primero = null;
		ultimo = null;
	}

	public void insertar( int v , double distancia ){

		VerticeConDistancia nuevo = new VerticeConDistancia( v , distancia );

		if( estaVacia() ){

			primero = nuevo;
			ultimo = nuevo;

		}else{

			if( distancia <= primero.getDistancia() ){
				nuevo.sig = primero;
				primero = nuevo;
			}else{
				VerticeConDistancia actual = primero;
				VerticeConDistancia anterior = null;

				while( actual != null && distancia > actual.getDistancia() ){
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

			}// fin else interno

		}// fin else externo

	}// fin del metodo insertar

	public int extraer(){

		if( !estaVacia() ){

			if( primero == ultimo ){
				int temp = primero.getVertice();
				primero = null;
				ultimo = null;
				return temp;
			}

			int temp = primero.getVertice();
			primero = primero.sig;
			return temp;

		}

		return -1; // vacia
	}

	private void extraer( int v ){

		VerticeConDistancia actual = primero;
		VerticeConDistancia anterior = null;
		VerticeConDistancia temp = null;

		while( actual.getVertice() != v ){
			anterior = actual;
			actual = actual.sig;
		}

		if( anterior == null ){
			temp = primero;
			primero = primero.sig;
		}else{

			if( actual == ultimo ){
				temp = ultimo;
				ultimo = anterior;
				ultimo.sig = null;
			}else{
				temp = actual;
				actual = actual.sig;
				anterior.sig = actual;
			}

		}// fin else

		temp = null; // libera la memoria
	}

	public boolean estaEnLaCola( int v ){

		if( !estaVacia() ){

			VerticeConDistancia actual = primero;

			while( actual != null ){

				if( v == actual.getVertice() )
					return true;

				actual = actual.sig;
			}

		}

		return false;
	}

	public void actualizarVertice( int v , double distancia ){

		// busca el vertice y lo extrae 
		extraer( v );

		// insertar el vertice en la cola con su nueva distancia
		insertar( v , distancia );

	}

	public boolean estaVacia(){
		return primero == null;
	}

	public void imprimirCola(){

		VerticeConDistancia temp = primero;

		while( temp != null ){
			System.out.printf( "(%d|%.3f) > ", temp.getVertice() , temp.getDistancia() );
			temp = temp.sig;
		}

		System.out.println( " NUll ");
	}
}