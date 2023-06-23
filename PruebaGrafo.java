// PruebaGrafo.java

// taller #2 grafo malla mejorado version 1.2.3

// realizado por:
// Nombre: Jesus morales
// C.I : 22.376.391

import java.util.Scanner;

public class PruebaGrafo{

	private static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("\n -> Programa para Ilustrar un grafoMalla y generar un laberinto\n");

		System.out.print(" Introduzca el numero de filas: ");
		int filas = entrada.nextInt();

		System.out.print(" Introduzca el numero de columnas: ");
		int columnas = entrada.nextInt();
		
		// crea el grafo malla con sus aristas y pesos ramdons
		GrafoMalla grafo = new GrafoMalla( filas , columnas );

		//System.out.println("       -> GrafoMalla\n");

		//grafo.imprimir(); // muestra el grafomalla completo

		System.out.println();

		System.out.println("\n       -> Laberinto\n");

		// crea un grafoMalla con las mismas dimensiones pero sin aristas
		GrafoMalla nuevo = new GrafoMalla( grafo );

		// ejecuta el metodo de kruskal sobre el grafo anterior y agrega el AEM
		// a este nuevo grafomalla
		grafo.kruskal( nuevo ); 

		// determinar la entrada y salida del laberinto
		nuevo.establecerEyS();

		System.out.println( "   entrada -> vertice #" + nuevo.getEntrada() +
			"\n   salida -> vertice #" + nuevo.getSalida() + "\n" );

		nuevo.imprimir(); // muestra el laberinto

		nuevo.ejecutarDijkstra();
		nuevo.ejecutarBestFirstSearch();
		nuevo.ejecutarAEstrella();

	}
}