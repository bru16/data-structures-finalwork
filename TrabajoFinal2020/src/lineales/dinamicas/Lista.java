package lineales.dinamicas;

public class Lista {

	private Nodo cabecera;
	private int longitud;
	
	public Lista() {
		
		this.cabecera = null;
		this.longitud = 0;
	}

	public boolean insertar(Object elem, int pos) {

		boolean exito;

		if(pos < 1 || pos > this.longitud + 1) {
			exito = false;
		}
		else if(pos == 1) {
			this.cabecera = new Nodo(elem,this.cabecera);
			exito = true;
			this.longitud++;
		}
		else {
			int i = 1;
			Nodo aux = this.cabecera;
			while(i < pos-1) {
				aux = aux.getEnlace();
				i++;
			}
			Nodo nuevo = new Nodo(elem,aux.getEnlace());
			aux.setEnlace(nuevo);
			exito = true;
			this.longitud++;
		}
		return exito;
	}

	public boolean eliminar(int pos) {

		boolean exito;

		if(pos < 1 || pos > this.longitud) {
			exito = false;
		}
		else if(pos == 1) {
			this.cabecera = this.cabecera.getEnlace();
			this.longitud--;
			exito = true;
		}
		else {
			Nodo aux = this.cabecera;
			int i = 1;
			while(i < pos-1) {
				aux = aux.getEnlace();
				i++;
			}
			aux.setEnlace(aux.getEnlace().getEnlace());
			this.longitud--;
			exito = true;
		}
		return exito;
	}

	public Object recuperar(int pos) {

		Object x;
		
		if(pos < 1 || pos > this.longitud) {
			x = null;
		}
		else if(pos == 1) {
			x = this.cabecera.getElem();
		}
		else {
			Nodo aux = this.cabecera;
			for(int i = 1; i < pos-1; i++) {
				aux = aux.getEnlace();
			}
			x = aux.getEnlace().getElem();
		}

		return x;
	}

	public int localizar(Object elem) {

		int posicion = -1;
		boolean encontrado = false;
		Nodo aux = this.cabecera;
		int i = 1;

		if(aux == null)
			posicion = -1;
		else if(aux.getElem().equals(elem)) {
			posicion = 1;
		}
		else {
			aux = aux.getEnlace();
			i++;
			while(encontrado != true && i != this.longitud + 1) {

				if (aux.getElem().equals(elem)){
					encontrado = true;
					posicion = i;
				}
				else {
					aux = aux.getEnlace();
					i++;
				}
			}
		}
		return posicion;
	}

	public void vaciar() {
		
		this.cabecera = null;
		longitud = 0;
	}
	
	public boolean esVacia() {
		
		boolean exito = false;
		
		if(this.cabecera == null) {
			exito = true;
		}
		
		return exito;
	}

	public int longitud() {

		int x = this.longitud;

		return x;
	}

	public Lista clone() {
		
		Lista l1 = new Lista();
		if(this.longitud != 0) 
			l1 = this.cloneAux();
			
		return l1;
	}

	private Lista cloneAux() {

		Lista nueva = new Lista();
		Nodo aux = this.cabecera;
		Nodo aux2;
		nueva.cabecera = new Nodo(aux.getElem(), null);
		aux2 = nueva.cabecera;
		
		while(aux.getEnlace() != null) {
			
			aux = aux.getEnlace();
			aux2.setEnlace(new Nodo(aux.getElem(),null));
			aux2 = aux2.getEnlace();
		}
		nueva.longitud = this.longitud;
		
		return nueva;
	}
	
	public String toString() {
		
		if(this.longitud == 0) 
			return "Lista vacia";
		
		String cad = "[";
		Nodo aux = this.cabecera;
		
		for(int i = 1; i <= this.longitud; i++) {
			
			cad += aux.getElem() + "-";
			aux = aux.getEnlace();
		}
		
		cad += "]";
		
		
		return cad;
	}

	public static Lista concatenar(Lista l1, Lista l2) {

		Lista nueva = new Lista();
		int longL1 = l1.longitud();
		int longL2 = l2.longitud();
		int i,j,contador;
		contador = 0;

		for(i = 1; i <= longL1; i++) {

			nueva.insertar(l1.recuperar(i), i);
			contador++;
		}

		for(j = 1; j <= longL2; j++) {

			nueva.insertar(l2.recuperar(j), contador+j);
		}

		return nueva;

	}

	public Lista obtenerMultiplos(int x) {

		Lista nueva = new Lista();
		Nodo aux = null;
		Nodo aux2 = this.cabecera;

		for(int i = 1; i <= this.longitud; i++) {


			if((i % x) == 0) {

				Nodo nuevo = new Nodo(aux2.getElem(),null);
				
				if(nueva.cabecera == null) {

					nueva.cabecera = nuevo;
					aux = nueva.cabecera;	
				}
				else {
					aux.setEnlace(nuevo);
					aux = aux.getEnlace();
				}
						
				nueva.longitud++;
			}
			aux2 = aux2.getEnlace();
		}

		return nueva;

	}

	public Lista eliminarApariciones(Object x) {

		boolean cambio = true;
		Nodo aux = this.cabecera;
		Nodo aux2 = aux;
		int pos = 1;

		while(aux != null) {

			if(cambio) 
				aux2 = aux;
			
			if(aux.getElem().equals(x)) {

				if(this.longitud == 1) 
					this.cabecera = null;

				else if(pos == 1 && this.longitud > 1) 
					this.cabecera = aux.getEnlace();

				else if(pos == 2 && this.longitud > 2)
					this.cabecera.setEnlace(aux.getEnlace());

				else if(aux.getEnlace() != null) {
					aux2.setEnlace(aux.getEnlace());
					cambio = false;
				}
				else
					aux2.setEnlace(null);

				this.longitud--;
			}

			aux = aux.getEnlace();
			pos++;
		}
		return this;


	}

	public void invertir() {
		invertirAux(this.cabecera, this);
	}

	private void invertirAux(Nodo nodo, Lista lista) {
		if (nodo != null) {
			this.cabecera = nodo;
			invertirAux(nodo.getEnlace(), lista);
			if (nodo.getEnlace() != null)
				nodo.getEnlace().setEnlace(nodo);
	    }
	}
}
