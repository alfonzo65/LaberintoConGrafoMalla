// Dijkstra.java

// realizado por:
// Nombre: Jesus morales
// C.I : 22.376.391

public class Dijkstra{

	private double[] distA;
	private Arista[] aristaA;
	private ColaConPrioridadDistancia cola;
	private int cuenta = 0;
	private static final double INFINITO = Double.POSITIVE_INFINITY;

	public void dijkstra( Grafo g , int entrada , int salida ){

		distA = new double[ g.obtenerCantidadDeVertices() ];
		aristaA = new Arista[ g.obtenerCantidadDeVertices() ];

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

			if( vActual == salida )
				break;

			relajar( g , vActual );

		}
	}

	private void relajar( Grafo g , int vActual ){

		cuenta++;

		Arista arista = g.obtenerLista( vActual );

		while( arista != null ){
			// obtener destino o vertice adyacente
			int w = arista.otroExtremo( vActual );

			if( distA[ w ] > distA[ vActual ] + arista.obtenerPeso() ){

				distA[ w ] = distA[ vActual ] + arista.obtenerPeso();
				aristaA[ w ] = arista;

				if( cola.estaEnLaCola( w ) )
					cola.actualizarVertice( w , distA[ w ] );
				else
					cola.insertar( w , distA[ w ] );
				
			}

			arista = arista.sig;
		}
	}

	public int obtenerCuenta(){
		return cuenta;
	}

}// fin de la clase Dijkstra