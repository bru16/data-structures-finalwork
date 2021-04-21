package lineales.dinamicas;
import lineales.dinamicas.Nodo;

public class Cola {

	private Nodo frente;
	private Nodo fin;
	
	public Cola() {
		
		frente = null;
		fin = null;
		
	}
	
	public boolean poner(Object elemento) {
		
		Nodo nuevo = new Nodo(elemento,null);

		boolean res = true;
		
		if(this.frente == null && this.fin == null) {
			this.fin = nuevo;
			this.frente = nuevo;
		}
		else {
			this.fin.setEnlace(nuevo);
			this.fin = nuevo;
		}
		return res;

	}

	public boolean sacar() {

		boolean exito;

		if(this.frente == null) {
			exito = false;
		}
		else {
			this.frente = this.frente.getEnlace();
			if(this.frente == null) {
				this.fin = null;
			}
			exito = true;
		}
		return exito;
	}

	public Object obtenerFrente() {

		Object elem;
		
		if(this.frente == null) 
			elem = null;
		else 
			elem = this.frente.getElem();

		return elem;
	}

	public boolean esVacia() {

		boolean exito;

		if(this.frente == null) 
			exito = true;
		else
			exito = false;

		return exito;
	}
	
	public void vaciar() {
		
		while(this.frente != null) {
			this.frente = this.frente.getEnlace();
		}
		
	}

	public Cola clone() {

		Cola nueva = new Cola();
		Nodo aux = this.frente;
		nueva.frente = new Nodo(aux.getElem(),null);
		aux = aux.getEnlace();
		Nodo aux2 = nueva.frente;
		
		while(aux != null) {
			aux2.setEnlace(new Nodo(aux.getElem(),null));
			aux2 = aux2.getEnlace();
			aux = aux.getEnlace();
		}
		nueva.fin = aux2;
		return nueva;
	}


	public String toString() {

		String cad = "[";
		Nodo aux = this.frente;

		if(this.frente == null) {
			return "La COLA esta vacia";
		}
		else {
			while(aux.getEnlace() != null) {
				cad = cad + aux.getElem() + "-";
				aux = aux.getEnlace();
			}
			cad = cad + aux.getElem();
		}
		cad += "]";
		return cad;
	}

}
