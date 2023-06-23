// Aestrella.java

// realizado por:
// Nombre: Jesus morales
// C.I : 22.376.391

public class Aestrella{

	private double[] distA;
	private Arista[] aristaA;
	private boolean[] visitado;
	private ColaConPrioridadDistancia cola;
	private int cuenta = 0;
	private static final double INFINITO = Double.POSITIVE_INFINITY;

	public void aEstrella( Grafo g , int entrada , int salida , int filas , int columnas ){

		distA = new double[ g.obtenerCantidadDeVertices() ];
		aristaA = new Arista[ g.obtenerCantidadDeVertices() ];
		visitado = new boolean[ g.obtenerCantidadDeVertices() ];

		// inicializar todo el arreglo distA con infinito positivo
		for( int i = 0 ; i < distA.length ; i++ )
			distA[ i ] = INFINITO;

		// inicializar cola con prioridad
		cola = new ColaConPrioridadDistancia();

		distA[ entrada ] = 0;
		aristaA[ entrada ] = null;

		cola.insertar( entrada , 0 );

		while( !cola.estaVacia() ){

			int vActual = cola.extraer();

			visitado[ vActual ] = true;

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

			if( visitado[w] != true ){

				if( distA[ w ] > distA[ vActual ] + arista.obtenerPeso() ){

				int manhattan = distanciaManHattan( w , exit , f , c );
				distA[ w ] = distA[ vActual ] + arista.obtenerPeso();
				aristaA[ w ] = arista;

				if( cola.estaEnLaCola( w ) )
					cola.actualizarVertice( w , distA[w] + manhattan ); 
				else
					cola.insertar( w , distA[w] + manhattan ); 

				}
			}

			arista = arista.sig;
		}

		//cola.imprimirCola();
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

}// fin de la clase Aestrella