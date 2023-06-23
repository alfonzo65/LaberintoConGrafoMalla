// Grafo.java

// realizado por:
// Nombre: Jesus morales
// C.I : 22.376.391

public class Grafo{

	private Lista[] listaAdy;
	private Kruskal kruskal;
	private int cantidadVertices;

	public Grafo( int vertices ){

		listaAdy = new Lista[vertices];
		cantidadVertices = vertices;

		for( int i = 0 ; i < listaAdy.length ; i++ )
			listaAdy[i] = new Lista();

	}

	public int obtenerCantidadDeVertices(){
		return listaAdy.length;
	}

	public void crearArista( int v1 , int v2 ){

		Arista n = new Arista( v1 , v2 );

		listaAdy[v1].meterEnLista( n.getOrigen() , n.getDestino() , n.obtenerPeso() );
		listaAdy[v2].meterEnLista(  n.getOrigen() , n.getDestino() , n.obtenerPeso() );
	}

	public void agregarArista( Arista arista ){
		listaAdy[arista.getOrigen()].meterEnLista( arista.getOrigen() , arista.getDestino() , 
			arista.obtenerPeso() );
		listaAdy[arista.getDestino()].meterEnLista( arista.getOrigen() , arista.getDestino() , 
			arista.obtenerPeso() );
	}

	public int obtenerGradoDeVertice( int indice ){
		return listaAdy[indice].getGrado();
	}

	public void kruskal( GrafoMalla temp ){

		ColaConPrioridad cola = new ColaConPrioridad();

		cola = crearColaConPrioridad( cola );

		kruskal = new Kruskal( listaAdy , cola );

		kruskal.ejecutarMetodoKruskal( listaAdy , temp );

	}// fin del metodo kruskal

	private ColaConPrioridad crearColaConPrioridad( ColaConPrioridad cola ){

		Lista listaTemp = new Lista();

		for( int i = 0 ; i < listaAdy.length ; i++ ){

			Arista arista = listaAdy[i].obtenerLista();

			while( arista != null ){

				if( !listaTemp.estaEnLaLista( arista ) ){
					listaTemp.meterEnLista( arista.getOrigen() , arista.getDestino() ,
						arista.obtenerPeso() );
					cola.insertar( arista.getOrigen() , arista.getDestino() ,
						arista.obtenerPeso() );
				}

				arista = arista.sig;
			}
		}

		return cola;
	}

	public Arista obtenerLista( int v ){
		return listaAdy[v].obtenerLista();
	}

	public void imprimirGrafo(){

		for( int i = 0 ; i < listaAdy.length ; i++ ){

			Arista temp = listaAdy[i].obtenerLista();

			System.out.print( i + "|" + "(g:" + listaAdy[i].getGrado() + ")" + "| ");

			while( temp != null ){
				System.out.print( "("+temp.getOrigen() + "," + temp.getDestino() +") -> ");
				temp = temp.sig;
			}

			System.out.println();
		}
	}

}// fin de la clase grafo