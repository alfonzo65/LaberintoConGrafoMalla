// Kruskal.java

// realizado por:
// Nombre: Jesus morales
// C.I : 22.376.391

public class Kruskal{

	ColaConPrioridad cola;
	QuickFind quickFind;

	public Kruskal( Lista[] grafo , ColaConPrioridad cola ){
		quickFind = new QuickFind( grafo.length );
		this.cola = cola;
	}

	public void ejecutarMetodoKruskal( Lista[] grafo , GrafoMalla temp ){

		int i = 0;

		while( !cola.estaVacia() && ( i < grafo.length - 1 )  ){

			Arista arista = cola.sacarDeLaCola();

			if( !quickFind.conectados( arista.getOrigen() , arista.getDestino() ) ){
				
				// unir los vertices
				quickFind.union( arista.getOrigen() , arista.getDestino() );

				// -> *** añadir al grafo malla sin aristas
				temp.agregarAlGrafoMalla( arista );

				i++;
			}

		}// fin while

	}// fin del method ejercutarMetodoKruskal

}// fin de la class Kruskal