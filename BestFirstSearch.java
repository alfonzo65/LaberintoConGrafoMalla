// BestFirstSearch.java

// realizado por:
// Nombre: Jesus morales
// C.I : 22.376.391

public class BestFirstSearch{

	private Arista[] aristaA;
	private ColaConPrioridadDistancia cola;
	private int cuenta = 0;

	public void bestFirstSearch( Grafo g , int entrada , int salida , int filas , int columnas ){

		aristaA = new Arista[ g.obtenerCantidadDeVertices() ];

		// inicializar cola con prioridad
		cola = new ColaConPrioridadDistancia();

		aristaA[ entrada ] = null;
		cola.insertar( entrada , 0 );

		while( !cola.estaVacia() ){

			int vActual = cola.extraer();

			if( vActual == salida )
				break;

			relajar( g ,  vActual , salida , filas , columnas );

		}// fin while

	}// fin metodo aEstrella

	private void relajar( Grafo g , int vActual , int exit , int f , int c ){

		cuenta++;

		Arista arista = g.obtenerLista( vActual );

		while( arista != null ){

			// obtener destino o vertice adyacente
			int w = arista.otroExtremo( vActual );

			if( aristaA[w] == null ){ // duda en esta condicion
			
				int manhattan = distanciaManHattan( w , exit , f , c );
				aristaA[ w ] = arista;
				cola.insertar( w , manhattan );
					
			}
			
			arista = arista.sig;
		}
	}


	private int distanciaManHattan( int vActual , int vDestino , int filas , int columnas ){ 

		int actual_X =  obtenerCoordenadaX( vActual , filas , columnas ); 
		int actual_Y = obtenerCoordenadaY( vActual , filas , columnas );

		int destino_X = obtenerCoordenadaX( vDestino , filas , columnas );
		int destino_Y = obtenerCoordenadaY( vDestino , filas , columnas );

		int dx = Math.abs( actual_X - destino_X );

		int dy = Math.abs( actual_Y - destino_Y );

    	return (dx + dy);
    }

    private int obtenerCoordenadaX( int vertice , int filas , int columnas ){

		int conteo = 0;
		int temp = 0;
		int i = 0;

		for( ; i < filas ; i++ ){
			for( int j = 0 ; j < columnas ; j++ ){
				if( conteo == vertice ){
					temp = i;
				}
				
				conteo++;
			}

			if( conteo > vertice )
				break;
		}

		return temp;

	}// fin del metodo obtenerCoordenadaX

	private int obtenerCoordenadaY( int vertice , int filas , int columnas ){

		int conteo = 0;
		int temp = 0;

		for( int i = 0 ; i < filas ; i++ ){
			for( int j = 0 ; j < columnas ; j++ ){
				if( conteo == vertice ){
					temp = j;
				}
				
				conteo++;
			}

			if( conteo > vertice )
				break;
		}
		

		return temp;

	}// fin del metodo obtenerCoordenadaX

	public int obtenerCuenta(){
		return cuenta;
	}

}