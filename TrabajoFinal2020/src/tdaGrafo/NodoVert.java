package tdaGrafo;

public class NodoVert {

	//atributos
	
	private Object elem;
	private NodoVert sigVertice;
	private NodoAdy primerAdy;
	
	//constructor
	
	public NodoVert(Object elemx, NodoVert x) {
		
		this.elem = elemx;
		this.sigVertice = x;
		this.primerAdy = null;
	}
	
	
	// metodos del TDA NodoVert
	
	//getters
	
	public Object getElem() {
		return this.elem;
	}
	
	public NodoVert getSigVertice() {
		return this.sigVertice;
	}
	
	public NodoAdy getPrimerAdy() {
		return this.primerAdy;
	}
	
	//setters
	
	public void setElem(Object x) {
		this.elem = x;
	}
	
	public void setSigVertice(NodoVert x) {
		this.sigVertice = x;
	}
	
	public void setPrimerAdy(NodoAdy x) {
		this.primerAdy = x;
	}
	
}
