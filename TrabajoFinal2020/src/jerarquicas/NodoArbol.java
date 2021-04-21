package jerarquicas;

public class NodoArbol {

	private Object elem;
	private NodoArbol izquierdo;
	private NodoArbol derecho;
	
	
	public NodoArbol(Object elem, NodoArbol izquierdo, NodoArbol derecho) {
		
		this.elem = elem;
		this.izquierdo = izquierdo;
		this.derecho = derecho;
	}
	
	public Object getElem() {
		
		
		return this.elem;
		
	}
	
	public NodoArbol getIzquierdo() {
		
		return this.izquierdo;
	}
	
	public NodoArbol getDerecho() {
		
		return this.derecho;
	}
	
	public void setElem(Object x) {
		
		this.elem = x;
		
	}
	
	public void setIzquierdo(NodoArbol x) {
		
		this.izquierdo = x;
		
	}
	
	public void setDerecho(NodoArbol x) {
		
		this.derecho = x;
		
	}
}
