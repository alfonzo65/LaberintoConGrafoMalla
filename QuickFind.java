// QuickFind.java

// realizado por:
// Nombre: Jesus morales
// C.I : 22.376.391

public class QuickFind{

	private int[] conjuntos;
	private int numConjuntos;

	public QuickFind( int cantidadDeVertices ){

		conjuntos = new int[ cantidadDeVertices ];
		numConjuntos = cantidadDeVertices;
		// inicializar conjuntos
		for( int i = 0 ; i < cantidadDeVertices ; i++ )
			conjuntos[ i ] = i;
	}

	public void union( int v1 , int v2 ){

		int subConjunto = conjuntos[ v2 ];

		for( int i = 0 ; i < conjuntos.length ; i++ ){
			if( conjuntos[ i ] == subConjunto )
				conjuntos[ i ] = conjuntos[ v1 ];
		}

		numConjuntos--;
	}

	public boolean conectados( int v1 , int v2 ){

		if( conjuntos[ v1 ] == conjuntos[ v2 ] )
			return true;
		
		return false;
	}

	public int getConjuntos(){
		return numConjuntos;
	}
}