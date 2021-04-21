package jerarquicas;

import conjuntistas.NodoABB;
import lineales.dinamicas.Lista;
import lineales.estaticas.Cola;

public class ArbolBin {

	private NodoArbol raiz;


	public ArbolBin() {

		this.raiz = null;
	}

	private NodoArbol obtenerNodo(NodoArbol n, Object elem) {

		NodoArbol nuevo = null;

		if(n != null) {
			if(n.getElem().equals(elem))
				nuevo = n;
			else {
				nuevo = obtenerNodo(n.getIzquierdo(), elem);

				if(nuevo == null) 
					nuevo = obtenerNodo(n.getDerecho(), elem);
			}
		}

		return nuevo;
	}

	public boolean insertar(Object elemNuevo, Object elemPadre, char lugar) {

		boolean exito = true;

		if(this.raiz == null) {

			this.raiz = new NodoArbol(elemNuevo, null, null);
		}
		else {
			NodoArbol nodoPadre = obtenerNodo(this.raiz,elemPadre);
			if(nodoPadre != null) {
				if(lugar == 'I' && nodoPadre.getIzquierdo() == null) {
					nodoPadre.setIzquierdo(new NodoArbol(elemNuevo,null,null));
				}
				else if (lugar == 'D' && nodoPadre.getDerecho() == null) {
					nodoPadre.setDerecho(new NodoArbol(elemNuevo,null,null));
				}
				else {
					exito = false;
				}
			}
			else
				exito = false;
		}

		return exito;	
	}

	public boolean esVacio() {

		boolean exito;

		if(this.raiz == null) 
			exito = true;
		else
			exito = false;

		return exito;

	}
	
	public void vaciar() {
		
		this.raiz = null;
	}

	public int altura() {

		int altura = alturaAux(this.raiz) - 1;
		
		return altura;
	}

	private int alturaAux(NodoArbol n) {

		int alturaD = 0;
		int alturaI = 0;

		if(n != null) {

			alturaD += alturaAux(n.getDerecho()) + 1;

			alturaI += alturaAux(n.getIzquierdo()) + 1;
		}

		if (alturaI >= alturaD) {
			alturaD = alturaI;
		}

		return alturaD;
	}

	public Lista listarPreorden() {

		Lista aux = new Lista();
		aux = preOrdenAux(this.raiz,aux);
		return aux;

	}

	private Lista preOrdenAux(NodoArbol n, Lista aux) {

		if(n != null) {

			preOrdenAux(n.getDerecho(),aux);
			preOrdenAux(n.getIzquierdo(),aux);
			aux.insertar(n.getElem(),1);
		}
		return aux;
	}

	public Lista listarInorden() {

		Lista aux = new Lista();
		aux = inOrdenAux(this.raiz,aux);
		return aux;
	}

	private Lista inOrdenAux(NodoArbol n, Lista aux) {

		if(n != null) {

			inOrdenAux(n.getDerecho(),aux);
			aux.insertar(n.getElem(),1);
			inOrdenAux(n.getIzquierdo(),aux);
		}
		return aux;
	}

	public Lista listarPosorden() {

		Lista aux = new Lista();
		aux = posOrdenAux(this.raiz,aux);
		return aux;
		
	}

	private Lista posOrdenAux(NodoArbol n, Lista aux) {

		if(n != null) {

			aux.insertar(n.getElem(),1);
			posOrdenAux(n.getDerecho(),aux);
			posOrdenAux(n.getIzquierdo(),aux);
		}
		return aux;
	}

	public Lista listarPorNivel() {

		int pos = 1;
		Lista auxLista = new Lista();
		Cola auxCola = new Cola();

		auxCola.poner(this.raiz);

		while(auxCola.esVacia() != true) {

			NodoArbol auxNodo = (NodoArbol) auxCola.obtenerFrente();
			auxCola.sacar();
			auxLista.insertar(auxNodo.getElem(), pos);
			if(auxNodo.getIzquierdo() != null) {
				auxCola.poner(auxNodo.getIzquierdo());
			}
			if(auxNodo.getDerecho() != null) {
				auxCola.poner(auxNodo.getDerecho());
			}
			pos+= 1;
		}
		return auxLista;
	}

	public Object padre(Object elem) {

		Object x = null;

		if(this.raiz != null && this.raiz.getElem() != elem) {
			x = padreAux(this.raiz, elem, null);
		}

		return x;
	}
	
	private Object padreAux(NodoArbol n, Object elemPadre, NodoArbol aux) {
		
		Object valor = null;
		
		if(n != null) {
			if(n.getElem().equals(elemPadre)) {
				valor = aux.getElem();
			}
			else {
				aux = n;
				valor = padreAux(n.getIzquierdo(),elemPadre,aux);
				if(valor == null) {
					aux = n;
					valor = padreAux(n.getDerecho(), elemPadre,aux);
				}
			}
		}

		return valor;
	}

	public int nivel(Object x) {

		int nivel;
		nivel = nivelAux(this.raiz, x, 0);

		return nivel;
	}

	private int nivelAux(NodoArbol n, Object elem, int level){

		int res = -1;
		if (n != null){
			if (n.getElem().equals(elem)){
				res = level;
			}
			else{
				res = nivelAux(n.getIzquierdo(),elem,level+1);
				if(level > 0) {
					res = nivelAux(n.getDerecho(),elem,level+1);
				}
				else if(level >= 0 && res == -1)
					res = nivelAux(n.getDerecho(),elem,level+1);
			}
		}
		return res;
	}

	public ArbolBin clone() {

		ArbolBin aux = new ArbolBin();

		aux.raiz = clonarAux(this.raiz);

		return aux;
	}

	private NodoArbol clonarAux(NodoArbol aux) {
		NodoArbol hijo = null;
		if (aux != null) {
			hijo = new NodoArbol(aux.getElem(), clonarAux(aux.getIzquierdo()), clonarAux(aux.getDerecho()));
		}
		return hijo;
	}

	
	// toString mejorado
	
	public String toString() {

		String cad = "";
		if(this.raiz != null)
			cad = toStringAux(this.raiz, cad);
		else
			cad = "Arbol vacio";

		return cad;
	}

	public String toStringAux(NodoArbol nodo, String cad) {
		if (nodo != null) {
			cad += "\n" + nodo.getElem() + "\t";
			NodoArbol izq = nodo.getIzquierdo();
			NodoArbol der = nodo.getDerecho();

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
	
	// Ejercicio adicional de reentrega obtenerAncestros()
	
	public Lista obtenerAncestros(Object elemento) {
		Lista ls = new Lista();
		obtenerAncestrosAux(this.raiz, ls, elemento);
		return ls;
	}

	private boolean obtenerAncestrosAux(NodoArbol n, Lista ls, Object elemento) {

		boolean encontrado = false;
		if (n != null){
			if (n.getElem().equals(elemento)) {
				encontrado = true;
			} else {
				encontrado = obtenerAncestrosAux(n.getIzquierdo(),ls,elemento);
				if(!encontrado)
					encontrado = obtenerAncestrosAux(n.getDerecho(),ls,elemento);
				
				if (encontrado)
					ls.insertar(n.getElem(),ls.longitud()+1); // Si inserto en posicion 1 queda al reves los ancestros.
			}
		}
		return encontrado;
	}

}
