package tdaGrafo;

public class NodoAdy {

	//atributos
	
	private NodoVert vertice;
	private NodoAdy sigAdyacente;
	private int etiqueta;		// etiqueta int para grafo etiquetado con minutos entre los aereopuertos.


	// metodos propios del TDA

	public NodoAdy(NodoVert vert, int etiq) {
		vertice = vert;
		this.etiqueta = etiq;
		sigAdyacente = null;
	}

	public NodoAdy() {
		
		sigAdyacente = null;
	}
	//getters

	public NodoVert getVertice() {
		return this.vertice;
	}

	public NodoAdy getSiguienteAdy() {
		return this.sigAdyacente;
	}

	public int getEtiqueta() {
		return this.etiqueta;
	}
	
	//setters
	
	public void setVertice(NodoVert x) {
		this.vertice = x;
	}
	
	public void setSiguienteAdy(NodoAdy x) {
		this.sigAdyacente = x;
	}
	
	public void setEtiqueta (int x) {
		this.etiqueta = x;
	}
}
