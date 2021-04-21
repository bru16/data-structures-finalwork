package conjuntistas;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Nodo;

public class TablaHash {

	private static final int TAMANIO = 10;
	private Nodo hash[];
	private int cant;
	
	
	public TablaHash(){
		
		this.hash = new Nodo[10];
		this.cant = 0;
	}
	
	public boolean insertar(Object elem) {
		
		boolean encontrado = false;
		int pos = elem.hashCode() % TablaHash.TAMANIO;
		Nodo aux = this.hash[pos];
		
		while(!encontrado && aux != null) {
			
			encontrado = aux.getElem().equals(elem);
			aux = aux.getEnlace();
		}
		
		if(!encontrado) {
			this.hash[pos] = new Nodo(elem, this.hash[pos]);
			this.cant++;
		}
		
		return !encontrado;
	}

	public boolean pertenece(Object elem) {

		boolean encontrado = false;
		int pos = elem.hashCode() % TablaHash.TAMANIO;
		Nodo aux = this.hash[pos];

		while(!encontrado && aux != null) {

			encontrado = aux.getElem().equals(elem);
			aux = aux.getEnlace();
		}

		return encontrado;
	}
	
	public boolean eliminar(Object elem) {
		
		boolean encontrado = false;
		int pos = elem.hashCode() % TablaHash.TAMANIO;
		Nodo aux = this.hash[pos];
		Nodo aux2 = this.hash[pos];
		
		while(!encontrado && aux != null) {
			
			encontrado = aux.getElem().equals(elem);
			aux2 = aux;
			aux = aux.getEnlace();
		}
		
		if(encontrado) {
			aux2 = null;
			this.cant--;
		}
		
		return encontrado;
		
	}
	
	public boolean esVacia() {
		
		boolean vacia = false;
		
		if(this.cant == 0)
			vacia = true;
		
		return vacia;
	}
	
	public Lista listar() {
		
		Lista ls = new Lista();
		Nodo aux;
		int pos = 1;
		
		for(int i = 0; i < TablaHash.TAMANIO; i++) {
			
			aux = this.hash[i];
			
			while(aux != null) {
				
				ls.insertar(aux.getElem(), pos);
				pos++;
				aux = aux.getEnlace();
			}
		}
		
		return ls;
	}
}
