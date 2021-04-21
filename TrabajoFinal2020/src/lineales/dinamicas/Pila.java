package lineales.dinamicas;

public class Pila {

	
	private Nodo tope;
	
	public Pila() {
		
		this.tope = null;
		
	}
	
	public boolean apilar(Object nuevoElem) {
		
		Nodo nuevo = new Nodo(nuevoElem,this.tope);
		
		this.tope = nuevo;
		
		return true;
	}
	
	public boolean desapilar() {
		
		boolean exito;
		
		
		if(this.tope != null) {
			
			this.tope = this.tope.getEnlace();
			
			exito = true;
		}
		else {
			this.tope = null;
			exito = false;
		}
		
		return exito;
	}
	
	public Object obtenerTope() {
		
		Object x;
		
		if(this.tope == null) {
			x = null;
		}
		else {
			x = this.tope.getElem();
		}
		
		
		return x;
	}
	
	public boolean esVacia() {
		
		boolean exito;
		
		if(this.tope != null) {
			exito = false;
		}
		else {
			exito = true;
		}
		
		return exito;
	}
	
	public void vaciar() {
		
		while(this.tope != null) {
			this.tope = null;
		}
		
	}
	
	public Pila clone() {
		
		Pila nueva = new Pila();
		Nodo auxiliar;
		auxiliar = this.tope;
		nueva.tope = this.tope;
		
		while(nueva.tope.getEnlace() != null) {
			
			nueva.tope.setElem(this.obtenerTope());
			nueva.tope = this.tope.getEnlace();
			this.tope = nueva.tope;
		}
		nueva.tope.setElem(this.obtenerTope());
		nueva.tope = auxiliar;
		this.tope = auxiliar;
		
		return nueva;
	}
	
	public String toString() {
		
		String cadena = "";
		
		if(this.tope == null) {
			cadena = " La PILA esta vacia. ";
		}
		else {
			Nodo aux = this.tope;
			cadena = "]";
			
			while(aux != null) {
				cadena = aux.getElem().toString() +cadena ;
				aux = aux.getEnlace();
				if(aux != null) 
					cadena = "-" + cadena;	
			}
			cadena = "["+cadena;
		}
		return cadena;
	}
	

}
