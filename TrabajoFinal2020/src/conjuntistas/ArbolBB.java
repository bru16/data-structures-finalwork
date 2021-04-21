package conjuntistas;
import conjuntistas.NodoABB;
import jerarquicas.ArbolBin;
import jerarquicas.NodoArbol;
import lineales.dinamicas.Lista;

public class ArbolBB {


	private NodoABB raiz;


	public ArbolBB() {

		this.raiz = null;

	}

	public boolean insertar(Comparable elem) {

		boolean exito = true;

		if(this.raiz == null) {
			this.raiz = new NodoABB(elem,null,null);
		}
		else
			exito = insertarAux(this.raiz,elem);

		return exito;
	}

	private boolean insertarAux(NodoABB n, Comparable elem) {

		boolean exito = true;

		if(elem.compareTo(n.getElem()) == 0){
			exito = false;
		}
		else if(elem.compareTo(n.getElem()) < 0) {

			if(n.getIzquierdo() != null) 
				exito = insertarAux(n.getIzquierdo(), elem);
			else 
				n.setIzquierdo(new NodoABB(elem,null,null));
		}
		else {

			if(n.getDerecho() != null) 
				exito = insertarAux(n.getDerecho(), elem);
			else
				n.setDerecho(new NodoABB(elem,null,null));
		}
		return exito;
	}

	public boolean pertenece(Comparable elem) {
		boolean exito = false;
		if(this.raiz != null)
			exito = perteneceAux(this.raiz, elem);
		
		return exito;
	}
	
	private boolean perteneceAux(NodoABB n, Comparable elem) {

		boolean exito;

		if(elem.compareTo(n.getElem()) == 0)
			exito = true;

		else if(elem.compareTo(n.getElem()) < 0) {

			if(n.getIzquierdo() != null)
				exito = perteneceAux(n.getIzquierdo(), elem);
			else
				exito = false;
		}
		else {

			if(n.getDerecho() != null)
				exito = perteneceAux(n.getDerecho(), elem);
			else
				exito = false;
		}

		return exito;
	}

	public boolean eliminar(Comparable elemEliminar) {
		return eliminarAux(this.raiz, null, elemEliminar);
	}

	private boolean eliminarAux(NodoABB n, NodoABB padre, Comparable elemEliminar) {
		
		boolean exito = false;
		if (n != null) {

			Comparable elem = n.getElem();

			if (elem.equals(elemEliminar))	// llegue a encontrar el elemento y procedo a eliminarlo
				exito = eliminarNodo(n, padre);

			else if (elem.compareTo(elemEliminar) > 0) 
				exito = eliminarAux(n.getIzquierdo(), n, elemEliminar);	// bajo por izquierda o derecha hasta encontrar el elem a eliminar
			
			else 
				exito = eliminarAux(n.getDerecho(), n, elemEliminar);
		}
		return exito;
	}

	private boolean eliminarNodo(NodoABB nodo, NodoABB padre) {
		
		NodoABB izquierdo = nodo.getIzquierdo();
		NodoABB derecho = nodo.getDerecho();
		// Analizo posibles casos a eliminar
		if (izquierdo == null && derecho == null) 
			eliminarHoja(nodo, padre);	// elimino nodo HOJA (caso 1)
		
		 else if (izquierdo != null && derecho != null) // elimino nodo con DOS hijos (caso2)
			eliminarConDosHijos(nodo);
		
		 else 
			eliminarConUnHijo(nodo, padre); // elimino nodo con al menos 1 hijo (caso 3)
		
		return true;
	}

	// caso 1
	private void eliminarHoja(NodoABB hijo, NodoABB padre) {
		if (padre == null) {
			// caso especial raiz
			this.raiz = null;
		} else if (padre.getDerecho() == hijo) {
			padre.setDerecho(null);
		} else {
			padre.setIzquierdo(null);
		}
	}

	// caso 2
	private void eliminarConUnHijo(NodoABB hijo, NodoABB padre) {
		
		NodoABB aux;
		
		if(hijo.getIzquierdo() != null)
			aux = hijo.getIzquierdo();
		else
			aux = hijo.getDerecho();
		
		if (padre == null) {
			// caso especial, raiz con un hijo
			this.raiz = aux;
		} else if (padre.getDerecho() == hijo) {
			padre.setDerecho(aux);
		} else {
			padre.setIzquierdo(aux);
		}
	}

	// caso 3
	private void eliminarConDosHijos(NodoABB nodo) {
		NodoABB candidato = nodo.getDerecho();
		NodoABB padreCandidato = nodo;
		// obtengo el menor de los mayores (candidato)
		while (candidato.getIzquierdo() != null) {
			padreCandidato = candidato;
			candidato = candidato.getIzquierdo();
		}
		// remplazo el valor del nodo a eliminar por el valor del candidato
		nodo.setElem(candidato.getElem());
		// hijo pude ser null o no
		NodoABB hijoCandidato = candidato.getDerecho();
		// elimina el nodo
		// el candidato es el hijo derecho del nodo a eliminar?
		if (nodo.getDerecho() == candidato) {
			// caso especial, el candidato es hijo del nodo
			nodo.setDerecho(hijoCandidato);
		} else {
			// caso comun, el candidato no es hijo del nodo
			padreCandidato.setIzquierdo(hijoCandidato);
		}
	}

	public boolean esVacio() {

		boolean exito = false;

		if(this.raiz == null)
			exito = true;

		return exito;
		
	}
	
	public void vaciar() {
		this.raiz = null;
	}

	public Lista listar() {

		Lista nueva = new Lista();
		listarAux(this.raiz, nueva);

		return nueva;
	}

	private Lista listarAux(NodoABB n, Lista aux) {

		if(n != null) {
			listarAux(n.getDerecho(),aux);
			aux.insertar(n.getElem(),1);
			listarAux(n.getIzquierdo(),aux);
		}

		return aux;
	}

	public Lista listarRango(int minimo, int maximo) {
		
		Lista nueva = new Lista();
		listarRangoAux(this.raiz, nueva, minimo, maximo);
		return nueva;
	}

	private void listarRangoAux(NodoABB n, Lista lista, int min, int max) {
		if (n != null) {
			Comparable elem = n.getElem();
			if (elem.compareTo(max) < 0)
				listarRangoAux(n.getDerecho(), lista, min, max);
			if (elem.compareTo(min) >= 0 && elem.compareTo(max) <= 0)
				lista.insertar(elem, 1);
			if (elem.compareTo(min) > 0)
				listarRangoAux(n.getIzquierdo(), lista, min, max);
		}
	}

	public Comparable minimo() {
		
		Comparable elem = null;
		NodoABB n = this.raiz;
		
		while (n != null) {	// baja por izquierda
			elem = n.getElem();
			n = n.getIzquierdo();
		}
		return elem;
	}

	public Comparable maximo() {
		Comparable elemento = null;
		NodoABB nodo = this.raiz;

		while (nodo != null) {	//baja por derecha
			elemento = nodo.getElem();
			nodo = nodo.getDerecho();
		}
		return elemento;
	}

	public boolean eliminarMin() {
		
		boolean exito = false;
		if (this.raiz != null) {
			NodoABB padre = null;
			NodoABB hijo = this.raiz;
			while (hijo.getIzquierdo() != null) {
				padre = hijo;
				hijo = hijo.getIzquierdo();
			}
			if (padre == null) {
				this.raiz = hijo.getDerecho();	//Si la raiz es el minimo elemento, la elimino seteando como nueva raiz el hijo derecho de raiz, el cual si es null entonces queda la raiz en null.
			} else {
				padre.setIzquierdo(hijo.getDerecho());
			}
			exito = true;
		}
		return exito;
	}

	public boolean eliminarMax() {	// Muy parecido al eliminarMin() pero bajo por derecha.
		
		boolean exito = false;
		if (this.raiz != null) {
			NodoABB padre = null;
			NodoABB hijo = this.raiz;
			while (hijo.getDerecho() != null) {
				padre = hijo;
				hijo = hijo.getDerecho();
			}
			if (padre == null) {
				this.raiz = hijo.getIzquierdo();	// Mismo que eliminarMin() pero al reves 
			} else {
				padre.setDerecho(hijo.getIzquierdo());
			}
			exito = true;
		}
		return exito;
	}

	public ArbolBB clone() {

		ArbolBB aux = new ArbolBB();
		aux.raiz = clonarAux(this.raiz);
		return aux;
	}

	private NodoABB clonarAux(NodoABB aux) {
		NodoABB hijo = null;
		if (aux != null)
			hijo = new NodoABB(aux.getElem(), clonarAux(aux.getIzquierdo()), clonarAux(aux.getDerecho()));
		
		return hijo;
	}

	public String toString() {

		String cad = "";	
		if(this.raiz != null)
			cad = toStringAux(this.raiz, cad);
		else
			cad = "Arbol vacio";

		return cad;
	}

	public String toStringAux(NodoABB nodo, String cad) {
		if (nodo != null) {
			cad += "\n" + nodo.getElem() + "\t";
			NodoABB izq = nodo.getIzquierdo();
			NodoABB der = nodo.getDerecho();

			if(izq != null)
				cad += "HI: " + izq.getElem() + "\t";
			else
				cad += "HI: -" + "\t";

			if(der != null)
				cad += "HD : " +der.getElem() + "\t";
			else
				cad += "HD: -" + "\t"; 

			cad = toStringAux(izq, cad);
			cad = toStringAux(der, cad);
		}
		return cad;
	}

	// ejercicios parcial
	
	public int masNodosEnRango(int min, int max) {
		int izq = contador(min, max, this.raiz.getIzquierdo());
		int der = contador(min, max, this.raiz.getDerecho());
		return Math.max(izq, der);
	}

	private int contador(int min, int max, NodoABB nodoABB) {
		int res = 0;
		if (nodoABB != null) {
			if (((Comparable) nodoABB.getElem()).compareTo(min) >= 0 && ((Comparable) nodoABB.getElem()).compareTo(max) <= 0)
				res = 1;

			if (nodoABB.getIzquierdo() != null && ((Comparable) nodoABB.getIzquierdo().getElem()).compareTo(min) >= 0) 
				res = contador(min, max, nodoABB.getIzquierdo()) + res;

			if (nodoABB.getDerecho() != null && ((Comparable) nodoABB.getDerecho().getElem()).compareTo(max) <= 0)
				res = contador(min, max, nodoABB.getDerecho()) + res;

		}

		return res;
	}


	public NodoABB obtenerNodo(Comparable elem) {

		NodoABB n = this.raiz;
		boolean exito = false;

		while(n != null && !exito) {
			if(n.getElem() == elem)
				exito = true;
			
			else if(n.getElem().compareTo(elem) > 0)
				n = n.getIzquierdo();
			else
				n = n.getDerecho();
		}
		
		if(!exito) {
			n = null;
		}
		
		return n;
	}

	public ArbolBB clonarParteInvertida(Comparable elem) {

		ArbolBB arb = new ArbolBB();

		if(this.raiz != null) {
			NodoABB aux = obtenerNodo(elem);
			if(aux != null) {
				arb.raiz = clonarParteInvertidaAux(aux,elem);
			}

		}

		return arb;
	}

	private NodoABB clonarParteInvertidaAux(NodoABB n, Comparable elem) {

			NodoABB izq = n.getIzquierdo();
			NodoABB der = n.getDerecho();

			if(izq != null && der != null) {
				n.setDerecho(izq);
				n.setIzquierdo(der);
				clonarParteInvertidaAux(n.getIzquierdo(),elem);
				clonarParteInvertidaAux(n.getDerecho(),elem);
			}
			else if(izq != null && der == null) {
				n.setDerecho(izq);
				n.setIzquierdo(null);
				clonarParteInvertidaAux(n.getDerecho(),elem);
			}

			else if(der != null && izq == null) {
				n.setIzquierdo(der);
				n.setDerecho(null);
				clonarParteInvertidaAux(n.getIzquierdo(),elem);
			}
		
		return n;
	}


	public Lista listarMayorIgual(Comparable elem) {
		
		Lista ls = new Lista();
		
		if(this.raiz != null)
			listarMayorIgualAux(this.raiz, elem, ls);
		
		return ls;
	}


	private void listarMayorIgualAux(NodoABB n, Comparable elem, Lista ls) {


		if(n != null) {
			if(n.getElem().compareTo(elem) >= 0) {

				listarMayorIgualAux(n.getDerecho(),elem,ls);
				ls.insertar(n.getElem(), ls.longitud()+1);
				listarMayorIgualAux(n.getIzquierdo(),elem,ls);

			}
		}
	}

	public int diferenciaCandidatos(Comparable elem) {

		int der = -2;
		if(this.raiz != null) {
			NodoABB aux = obtenerNodo(elem);

			if(aux != null && aux.getDerecho() != null && aux.getIzquierdo() != null) {

				Comparable auxElem = aux.getElem();

				der = diferenciaCandidatosAux(aux.getDerecho(), auxElem);
				int izq = diferenciaCandidatosAux(aux.getIzquierdo(), auxElem);
				der = der-izq;
			}
			if(aux == null)
				der = -1;
		}
		return der;
	}

	public int diferenciaCandidatosAux(NodoABB n, Comparable x) {

		int elem = -2;

		if(n.getElem().compareTo(x) > 0) {
			while(n != null) {
				elem = (int) n.getElem();
				n = n.getIzquierdo();
			}
		}
		else {
			while(n != null) {
				elem = (int) n.getElem();
				n = n.getDerecho();
			}

		}
		return elem;

	}



	
	
	
	



	
}
