package jerarquicas;

import lineales.dinamicas.Lista;
import lineales.estaticas.Cola;

public class ArbolGen {

	private NodoGen raiz;


	public ArbolGen() {

		this.raiz = null;
	}

	public boolean insertar(Object elemNuevo, Object elemPadre) {

		boolean exito = true;

		if(this.raiz == null) {

			this.raiz = new NodoGen(elemNuevo, null, null);
		}
		else {
			NodoGen nodoPadre = obtenerNodo(this.raiz,elemPadre);
			if(nodoPadre != null) {
				if(nodoPadre.getHijoIzquierdo() == null) {
					nodoPadre.setHijoIzquierdo((new NodoGen(elemNuevo,null,null)));
				}
				else {
					nodoPadre = nodoPadre.getHijoIzquierdo();
					if(nodoPadre.getHermanoDerecho() != null) {
						NodoGen nodoAux = null;
						nodoPadre = nodoPadre.getHermanoDerecho();
						while(nodoPadre != null) {
							nodoAux = nodoPadre;
							nodoPadre = nodoPadre.getHermanoDerecho();
						}
						nodoAux.setHermanoDerecho(new NodoGen(elemNuevo,null,null));
					}
					else						
						nodoPadre.setHermanoDerecho((new NodoGen(elemNuevo,null,null)));
				}
			}
			else
				exito = false;
		}
		return exito;
	}

	private NodoGen obtenerNodo(NodoGen n, Object buscado) {

		NodoGen res = null;
		if(n != null){
			if(n.getElem().equals(buscado)){
				res = n;
			}
			else{
				res = obtenerNodo(n.getHijoIzquierdo(),buscado);
				if(res == null){
					res = obtenerNodo(n.getHermanoDerecho(),buscado);
				}
			}
		}
		return res;
	}

	public Lista listarPreorden() {
		Lista lista = new Lista();
		lista = listarPreordenAux(this.raiz,lista);
		return lista;
	}

	private Lista listarPreordenAux(NodoGen n, Lista lista) {

		
		
		if(n != null) {
			lista.insertar(n.getElem(), lista.longitud() + 1);
			if(n.getHijoIzquierdo() != null) {
				listarPreordenAux(n.getHijoIzquierdo(),lista);
			}
			if(n.getHermanoDerecho() != null)
			listarPreordenAux(n.getHermanoDerecho(),lista);
		}
		return lista;
	}
	
	public Lista listarPosorden() {
		
		Lista lista = new Lista();
		lista = listarPosordenAux(this.raiz, lista);
		return lista;
	}
	private Lista listarPosordenAux(NodoGen n, Lista lista) {

		if(n != null) {
			if(n.getHijoIzquierdo() != null) {
				listarPosordenAux(n.getHijoIzquierdo(),lista);
			}
			lista.insertar(n.getElem(), lista.longitud() + 1);
			if(n.getHermanoDerecho() != null)
			listarPosordenAux(n.getHermanoDerecho(),lista);
		}
		return lista;
	}
	
	public Lista listarInorden() {
		
		Lista lista = new Lista();
		lista = listarInordenAux(this.raiz, lista);
		return lista;
		
	}
	
	private Lista listarInordenAux(NodoGen n, Lista lista) {

		if(n != null) {
			if(n.getHijoIzquierdo() != null) {
				listarInordenAux(n.getHijoIzquierdo(),lista);
			}
			lista.insertar(n.getElem(), lista.longitud() + 1);
			if(n.getHijoIzquierdo() != null) {
				NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
				while(hijo != null) {
					listarInordenAux(hijo,lista);
					hijo = hijo.getHermanoDerecho();
				}
					
			}
		}
		return lista;
	}

	public Lista listarPorNivel() {

		int pos = 1;
		Lista auxLista = new Lista();
		Cola auxCola = new Cola();

		auxCola.poner(this.raiz);

		while(!auxCola.esVacia()) {

			NodoGen auxNodo = (NodoGen) auxCola.obtenerFrente();
			auxCola.sacar();
			auxLista.insertar(auxNodo.getElem(), pos);
			auxNodo = auxNodo.getHijoIzquierdo();
			while(auxNodo != null) {
				auxCola.poner(auxNodo);
				auxNodo = auxNodo.getHermanoDerecho();
			}
			pos += 1;
		}
		return auxLista;
	}
	
	
	public int altura() {
        return alturaAux(this.raiz, -1);
    }

    private int alturaAux(NodoGen nodo, int altura) {
        if (nodo != null) {
            int x = alturaAux(nodo.getHijoIzquierdo(), altura + 1);
            int y = alturaAux(nodo.getHermanoDerecho(), altura);
            if(x > y) 
            	altura = x;
            else
            	altura = y;
        }
        return altura;
    }
	
	public boolean pertenece(Object elem) {

		boolean exito = false;

		if(obtenerNodo(this.raiz,elem) != null)
			exito = true;
		

		return exito;
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

	public Lista ancestros(Object elemento) {
		Lista ls = new Lista();
		ancestrosAux(this.raiz, ls, elemento);
		return ls;
	}

	private boolean ancestrosAux(NodoGen n, Lista ls, Object elemento) {

		boolean encontrado = false;
		if (n != null){
			if (n.getElem().equals(elemento)) {
				encontrado = true;
			} else {
				NodoGen hijo = n.getHijoIzquierdo();
				while (hijo != null && !encontrado) {
					encontrado = ancestrosAux(hijo, ls, elemento);
					hijo = hijo.getHermanoDerecho();
				}
				if (encontrado) 
					ls.insertar(n.getElem(),ls.longitud()+1);	// Use ls.longitud para que se vaya insertando bien y para luego no usar el invertir() de lista, ya que este no existe y no es eficiente hacerlo.
			}
		}
		return encontrado;
	}


	public String toString() {
		return toStringAux(this.raiz);
	}

	private String toStringAux(NodoGen n) {

		String s = "\n";
		
		if(n != null) {
			
			s += n.getElem().toString() + " -> ";
			NodoGen hijo = n.getHijoIzquierdo();
			while(hijo != null) {
				s += hijo.getElem().toString() + ", ";
				hijo = hijo.getHermanoDerecho();
			}
			
			hijo = n.getHijoIzquierdo();
			while(hijo != null) {
				s += "\n" + toStringAux(hijo);
				hijo = hijo.getHermanoDerecho();
			}
		}
		return s;
	}

	public int nivel(Object elem) {

		int ret;

		ret = nivelAux(this.raiz, elem, -1);

		return ret;

	}

	private int nivelAux(NodoGen n, Object buscado, int nivel) {

		if(n != null){
			if(n.getElem().equals(buscado)){
				nivel++;
			}
			else{
				nivel = nivelAux(n.getHijoIzquierdo(),buscado, nivel);
				if(nivel == -1)
					nivel = nivelAux(n.getHermanoDerecho(),buscado, nivel);
				else
					nivel += 1;

			}
		}
		return nivel;
	}

	public ArbolGen clone() {
		ArbolGen clon = new ArbolGen();
		clon.raiz = cloneAux(this.raiz);
		return clon;
	}

	private NodoGen cloneAux(NodoGen n) {

		NodoGen nuevo = new NodoGen(null, null, null);

		if(n != null) 
			nuevo = new NodoGen(n.getElem(), cloneAux(n.getHijoIzquierdo()), cloneAux(n.getHermanoDerecho()));
		
		else 
			nuevo = null;
		
		return nuevo;
	}

	public Object padre(Object elemento) {
		Object x;
		
		if(this.raiz == null || this.raiz.getElem().equals(elemento))
			x = null;
		else
			x = padreAux(this.raiz, elemento);
		
		return x;
	}

	private Object padreAux(NodoGen nodo, Object elemento) {
    	
        Object respuesta = null;
        if (nodo != null) {
            NodoGen next = nodo.getHijoIzquierdo();

            while (next != null && !next.getElem().equals(elemento))
            	next = next.getHermanoDerecho();
            if (next != null) {
            	respuesta = nodo.getElem();
            } else {
            	respuesta = padreAux(nodo.getHijoIzquierdo(), elemento);
            	if (respuesta == null)
            		respuesta = padreAux(nodo.getHermanoDerecho(), elemento);
            }
        }
        return respuesta;
	}

	public Lista frontera() {
        Lista lista = new Lista();
        fronteraAux(this.raiz, lista);
        return lista;
    }

    private void fronteraAux(NodoGen nodo, Lista lista) {
        if (nodo != null) {
            if (nodo.getHijoIzquierdo() == null) {
                lista.insertar(nodo.getElem(), lista.longitud()+1);
            } else {
                fronteraAux(nodo.getHijoIzquierdo(), lista);
            }
            fronteraAux(nodo.getHermanoDerecho(), lista);
        }
    }

}
