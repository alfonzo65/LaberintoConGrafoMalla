// GrafoMalla.java

// realizado por:
// Nombre: Jesus morales
// C.I : 22.376.391

// NOTA: Profesor queria poner el orden de como estan los caracteres en el arreglo,
// pero el compilador hasta con comentarios los toma como un caracter desconocido.

public class GrafoMalla{

	private final char[] CARACTERES = {
		0x250C , 0x2510 , 0x2514 , 0x2518 , 0x252C , 0x2534 , 0x251C ,
		0x2524 , 0x253C , ' ' , 0x2500 , 0x2502 , 
	};

	private Grafo grafo;
	private Dijkstra buscarCamino_1 = new Dijkstra();
	private BestFirstSearch buscarCamino_2 = new BestFirstSearch();
	private Aestrella buscarCamino_3 = new Aestrella();
	private int f;
	private int c;

	private int entrada;
	private int salida;

	public GrafoMalla( GrafoMalla malla ){

		f = malla.getFilas();
		c = malla.getColumnas();

		grafo = new Grafo( malla.getFilas() * malla.getColumnas() );
	}

	public GrafoMalla( int filas , int columnas ){

		f = filas;
		c = columnas;

		grafo = new Grafo( filas * columnas );

		// crea el grafo malla
		crearGrafoMalla( filas , columnas );

	}

	public void ejecutarDijkstra(){
		buscarCamino_1.dijkstra( getGrafo() , getEntrada() , getSalida() );
		System.out.println( "\n" + "     Dijkstra " + buscarCamino_1.obtenerCuenta() );
	}

	public void ejecutarBestFirstSearch(){
		buscarCamino_2.bestFirstSearch( getGrafo() , getEntrada() , getSalida() , getFilas() ,
			getColumnas() );
		System.out.println( "     Best-First Search "  + buscarCamino_2.obtenerCuenta() );
	}

	public void ejecutarAEstrella(){
		buscarCamino_3.aEstrella( getGrafo() , getEntrada() , getSalida() , getFilas() ,
			getColumnas() );
		System.out.println( "     A* "  + buscarCamino_3.obtenerCuenta() );
	}


	// este el metodo agregar arista requerido
	public void agregarAlGrafoMalla( Arista a ){
		grafo.agregarArista( a );
	}

	public void kruskal( GrafoMalla temp ){
		grafo.kruskal( temp );
	}

	private void crearGrafoMalla(int filas , int columnas ){

		for ( int i = 0 ; i < filas ; i++ ) {
			
			for ( int j = 0 ; j < columnas ; j++ ) {
				
				if( j < columnas - 1 )
					grafo.crearArista( (i*columnas) + j , (i*columnas) + j + 1 );

				if( i < filas - 1 )
					grafo.crearArista( (i*columnas) + j , ((i+1)*columnas) + j );

			}// fin for interno

			System.out.println();

		}// fin for externo

	}// fin del metodo crearGrafoMalla

	public void imprimir(){

		for ( int i = 0 ; i < f ; i++ ) {

			System.out.print("        ");
			
			for ( int j = 0 ; j < c ; j++ ) {
				
				// pide el grado del vertice actual
				int grado = grafo.obtenerGradoDeVertice( (i*c) + j );

				imprimirCaracter( grado , (i*c) + j );

			}// fin for interno

			System.out.println();

		}// fin for externo

	}// fin del metodo imprimir

	private void imprimirCaracter( int grado , int vActual ){

		Arista temp;

		int codigo;

		switch ( grado ) {

			case 0: 
				System.out.print( CARACTERES[9] );
				break;

			case 1:

				temp = grafo.obtenerLista( vActual );
				int extremo = temp.otroExtremo( vActual );

				direccionCaracterHyV( extremo , vActual );
				break;

			case 2:

				temp = grafo.obtenerLista( vActual );
				
				codigo = determinarCaracterDeGradoDos( temp , vActual );

				imprimirCaracterDeVerticeDeGradoDos( codigo );

				break;
			
			case 3:

				temp = grafo.obtenerLista( vActual );
				
				codigo = determinarCaracterDeGradoTres( temp , vActual );

				imprimirCaracterDeVerticeDeGradoTres( codigo );

				break;

			case 4:

				System.out.print( CARACTERES[8] );

				break;

		}// fin switch

	}// fin del metodo imprimirCaracter

	public Grafo getGrafo(){
		return grafo;
	}

	public int getFilas(){
		return f;
	}

	public int getColumnas(){
		return c;
	}

	private void direccionCaracterHyV( int n , int vActual ){

		int horizontalSentidoDerecha = vActual + 1;
		int horizontalSentidoIzquierda = vActual - 1;
		int verticalSentidoArriba, verticalSentidoAbajo;

		if( f == c ){
			verticalSentidoArriba = vActual - f;
			verticalSentidoAbajo = vActual + f; 
		}else{
			verticalSentidoArriba = vActual - c;
			verticalSentidoAbajo = vActual + c; 
		}
			
		if( n == verticalSentidoArriba || n == verticalSentidoAbajo ) 
			System.out.print( CARACTERES[11] );

		if( n == horizontalSentidoDerecha || n == horizontalSentidoIzquierda )
			System.out.print( CARACTERES[10] ); 
	
	}

	private int determinarCaracterDeGradoDos( Arista temp , int vActual ){

		int codigo = 0;

		int derecha = vActual + 1;
		int izquierda = vActual - 1;
		int arriba , abajo;

		if( c > f || f > c ){
			arriba = vActual - c;
			abajo = vActual + c;
		}else{
			arriba = vActual - f;
			abajo = vActual + f;
		}

		while( temp != null ){

			int direccion = temp.otroExtremo( vActual );

			if( direccion == izquierda )
				codigo+=2;

			if( direccion == derecha )
				codigo+=9;

			if( direccion == arriba )
				codigo+=3;

			if( direccion == abajo )
				codigo+=4;
			
			temp = temp.sig;

		}// fin while

		return codigo;
		
	}// fin del metodo direccionCaracterEsquinaHyV

	private int determinarCaracterDeGradoTres( Arista v , int vActual ){

		int codigo = 0;

		int derecha = vActual + 1;
		int izquierda = vActual - 1;
		int arriba , abajo;

		if( c > f || f > c ){
			arriba = vActual - c;
			abajo = vActual + c;
		}else{
			arriba = vActual - f;
			abajo = vActual + f;
		}
		
		while( v != null ){

			int direccion = v.otroExtremo( vActual );

			if( direccion == izquierda )
				codigo+=0;

			if( direccion == derecha )
				codigo+=2;

			if( direccion == arriba )
				codigo+=1;

			if( direccion == abajo )
				codigo+=3;

			v = v.sig;

		}// fin while

		return codigo;
	}

	private void imprimirCaracterDeVerticeDeGradoTres( int clave ){

		switch ( clave ) {
			case 3:
				System.out.print( CARACTERES[5] );
				break;
			case 4:
				System.out.print( CARACTERES[7] );
				break;
			case 5:
				System.out.print( CARACTERES[4] );
				break;
			case 6:
				System.out.print( CARACTERES[6] );
				break;
		}

	}// fin del metodo imprimirCaracterDeVerticeDeGradoTres

	private void imprimirCaracterDeVerticeDeGradoDos( int clave ){

		switch ( clave ) {
			case 5:
				System.out.print( CARACTERES[3] );
				break;
			case 6:
				System.out.print( CARACTERES[1] );
				break;
			case 7:
				System.out.print( CARACTERES[11] );
				break;
			case 12:
				System.out.print( CARACTERES[2] );
				break;
			case 13:
				System.out.print( CARACTERES[0] );
				break;
			case 11:
				System.out.print( CARACTERES[10] );
				break;
		}
	}

	public void establecerEyS(){

		int contador = 0;

		for ( int i = 0; i < f ; i++ ) {

			for ( int j = 0 ; j < c ; j++ ) {
				
				int vGrado = grafo.obtenerGradoDeVertice( (i*c) + j );

				if( vGrado == 1 && contador == 0 ){
					entrada = (i*c) + j;
					contador++;
				}else if( vGrado == 1 && contador > 0 ){
					salida = (i*c) + j;
				}
				
			}// fin for interno
			
		}// fin for alterno
 
	}//  fin del metodo establecerEyS

	public int getEntrada(){
		return entrada;
	}

	public int getSalida(){
		return salida;
	}

}// fin de la clase GrafoMalla