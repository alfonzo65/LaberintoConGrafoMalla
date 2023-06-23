// VerticeConDistancia.java

// realizado por:
// Nombre: Jesus morales
// C.I : 22.376.391

public class VerticeConDistancia{

	private int vertice;
	private double distancia;
	VerticeConDistancia sig;

	public VerticeConDistancia( int v , double distancia ){
		vertice = v;
		this.distancia = distancia;
		sig = null;
	}

	public int getVertice(){
		return vertice;
	}

	public double getDistancia(){
		return distancia;
	}

	public void setDistancia( double d ){
		distancia = d;
	}
}