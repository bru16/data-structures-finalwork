package lineales.dinamicas;

	public class Nodo {

	
	private Object elem;
	private Nodo enlace;
	
	public Nodo(Object elem,Nodo enlace) {
		
	this.elem = elem;
	this.enlace = enlace;
	}
	
	public Object getElem() {
		
		return this.elem;
	}
	
	public void setElem(Object elems) {
		this.elem = elems;
	}
	
	public Nodo getEnlace() {
		return this.enlace;
	}
	
	public void setEnlace(Nodo enlaced){
		this.enlace = enlaced;
	}
	
	
	
}
