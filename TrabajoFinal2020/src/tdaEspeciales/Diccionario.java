package tdaEspeciales;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Pila;

public class Diccionario {

	private NodoAVLDicc raiz;

    public Diccionario() {
        this.raiz = null;
    }
	
	public boolean insertar(Comparable clave,Object dato) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoAVLDicc(clave,dato);
        } else {
            exito = insertarAux(this.raiz,null, clave,dato);
        }
        return exito;
    }

    private boolean insertarAux(NodoAVLDicc nodo, NodoAVLDicc padre, Comparable elemento, Object dato) {
    	boolean exito = false;
    	Comparable contenido = nodo.getClave();
    	if (!elemento.equals(contenido)) {
    		exito = true;
    		if (elemento.compareTo(contenido) < 0) {
    			// elemento es menor que contenido
    			NodoAVLDicc izquierdo = nodo.getIzquierdo();
    			if (izquierdo == null) {
    				nodo.setIzquierdo(new NodoAVLDicc(elemento,dato));
    			} else {
    				exito = insertarAux(izquierdo, nodo, elemento,dato);
    			}
    		} else {
    			// elemento es mayor que contenido. Si tiene HD baja a la derecha, sino agrega elemento
    			NodoAVLDicc derecho = nodo.getDerecho();
    			if (derecho == null) {
    				nodo.setDerecho(new NodoAVLDicc(elemento,dato));
    			} else {
    				exito = insertarAux(derecho, nodo, elemento,dato);
    			}
    		}
    		if (exito)
    			balancear(nodo, padre);
    	}
    	return exito;
    }
	
    private NodoAVLDicc balancear(NodoAVLDicc nodo) {
        int balancePadre = nodo.calcularBalance();
        int balanceHijo;
        NodoAVLDicc aux = nodo;
        if (balancePadre > 1) {
            // desbalanceado a la izquierda
            balanceHijo = nodo.getIzquierdo().calcularBalance();
            // determino si aplico rotacion simple o doble
            if (balanceHijo >= 0) {
                aux = rotarDerecha(nodo);
            } else {
                aux = rotarIzquierdaDerecha(nodo);
            }
        } else if (balancePadre < -1) {
            // desbalanceado a la derecha
            balanceHijo = nodo.getDerecho().calcularBalance();
            // determino si aplico rotacion simple o doble
            if (balanceHijo <= 0) {
               aux = rotarIzquierda(nodo);
            } else {
                aux = rotarDerechaIzquierda(nodo);
            }
        }
        return aux;
    }
    
    private void balancear(NodoAVLDicc nodo, NodoAVLDicc padre) {
        nodo.recalcularAltura();
        NodoAVLDicc aux = balancear(nodo);
        if (nodo == this.raiz) {
            this.raiz = aux;
        } else if (padre.getClave().compareTo(aux.getClave()) > 0) {
            padre.setIzquierdo(aux);
        } else {
            padre.setDerecho(aux);
        }
    }
    
    private NodoAVLDicc rotarIzquierda(NodoAVLDicc nodo) {
        // pivot
        NodoAVLDicc hijoDerecho = nodo.getDerecho();
        // temporal
        NodoAVLDicc hijoIzquierdo = hijoDerecho.getIzquierdo();
        hijoDerecho.setIzquierdo(nodo);
        nodo.setDerecho(hijoIzquierdo);
        // recalculo altura de nodo y su "hijo"
        nodo.recalcularAltura();
        hijoDerecho.recalcularAltura();
        return hijoDerecho;
    }

    private NodoAVLDicc rotarDerecha(NodoAVLDicc nodo) {
        // pivot
        NodoAVLDicc hijoIzquierdo = nodo.getIzquierdo();
        // temporal
        NodoAVLDicc hijoDerecho = hijoIzquierdo.getDerecho();
        hijoIzquierdo.setDerecho(nodo);
        nodo.setIzquierdo(hijoDerecho);
        // recalculo altura del nodo y su "hijo"
        nodo.recalcularAltura();
        hijoIzquierdo.recalcularAltura();
        return hijoIzquierdo;
    }

    private NodoAVLDicc rotarIzquierdaDerecha(NodoAVLDicc nodo) {
    	nodo.setIzquierdo(rotarIzquierda(nodo.getIzquierdo()));
    	return rotarDerecha(nodo);
    }

    private NodoAVLDicc rotarDerechaIzquierda(NodoAVLDicc nodo) {
    	nodo.setDerecho(rotarDerecha(nodo.getDerecho()));
    	return rotarIzquierda(nodo);
    }

    public boolean eliminar(Comparable clave) {
    	return eliminarAux(this.raiz, null, clave);
    }

    // baja hasta encontrar el nodo
    private boolean eliminarAux(NodoAVLDicc nodo, NodoAVLDicc padre, Comparable x) {
    	boolean exito = false;
    	if (nodo != null) {
    		Comparable elemento = nodo.getClave();
    		if (elemento.equals(x)) {
    			// elimina el nodo
    			exito = eliminarNodo(nodo, padre);
    		} else {
    			if (elemento.compareTo(x) > 0) {
    				exito = eliminarAux(nodo.getIzquierdo(), nodo, x);
    			} else {
    				exito = eliminarAux(nodo.getDerecho(), nodo, x);
    			}
    			if (exito)
    				balancear(nodo, padre);
    		}

    	}
    	return exito;
    }

    // determina el tipo de eliminacion
    private boolean eliminarNodo(NodoAVLDicc nodo, NodoAVLDicc padre) {
    	NodoAVLDicc izquierdo = nodo.getIzquierdo();
    	NodoAVLDicc derecho = nodo.getDerecho();
    	if (izquierdo == null && derecho == null) {
    		// elimino un nodo hoja (sin hijos)
    		eliminarHoja(nodo, padre);
    	} else if (izquierdo != null && derecho != null) {
    		// elimino un nodo con dos hijos
    		eliminarConDosHijos(nodo);
    	} else {
    		// elimino un nodo con un hijo, uno izquierdo o derecho
    		eliminarConUnHijo(nodo, padre);
    	}
    	return true;
    }

    // caso 1
    private void eliminarHoja(NodoAVLDicc hijo, NodoAVLDicc padre) {
    	if (padre == null) {
    		// caso especial un unico elemento
    		this.raiz = null;
    	} else if (padre.getIzquierdo() == hijo) {
    		padre.setIzquierdo(null);
    	} else {
    		padre.setDerecho(null);
    	}
    }

    // caso 2
    private void eliminarConUnHijo(NodoAVLDicc hijo, NodoAVLDicc padre) {
    	NodoAVLDicc izquierdo = hijo.getIzquierdo();
    	NodoAVLDicc derecho = hijo.getDerecho();

    	if (padre == null) {
    		// caso especial de la raiz con un hijo
    		this.raiz = (izquierdo != null) ? izquierdo : derecho;
    	} else if (izquierdo != null) {
    		if(padre.getDerecho().equals(hijo)) {
    			padre.setDerecho(izquierdo);
    		}
    		else
    			padre.setIzquierdo(izquierdo);
    	} else {
    		if(padre.getDerecho().equals(hijo))
    			padre.setDerecho(derecho);
    		else 
    			padre.setIzquierdo(derecho);
    		//if-elses en donde se inserta el hijo del que elimino, si a la izq o der.
    	}
    }

    // caso 3
    private void eliminarConDosHijos(NodoAVLDicc nodo) {
        NodoAVLDicc candidato = nodo.getDerecho();
        NodoAVLDicc padreCandidato = nodo;
        Pila ancestros = new Pila();
        ancestros.apilar(nodo);
        // obtengo el menor de los mayores (candidato)
        while (candidato.getIzquierdo() != null) {
            ancestros.apilar(candidato);
            padreCandidato = candidato;
            candidato = candidato.getIzquierdo();
        }
        // remplazo el valor del nodo a eliminar por el valor del candidato
        nodo.setClave(candidato.getClave());
        // hijo pude ser null o no
        NodoAVLDicc hijoCandidato = candidato.getDerecho();
        if (padreCandidato == nodo) {
            // el candidato es el hijo
            padreCandidato.setDerecho(hijoCandidato);
        } else {
            // caso comun, el candidato no es hijo del nodo
            padreCandidato.setIzquierdo(hijoCandidato);
            // se balancean todos los nodos por los que se baja para buscar al candidato
            NodoAVLDicc hijo, padre;
            while (!ancestros.esVacia()) {
                hijo = (NodoAVLDicc) ancestros.obtenerTope();
                ancestros.desapilar();
                if (ancestros.obtenerTope() != null) {
                    padre = (NodoAVLDicc) ancestros.obtenerTope();
                    balancear(hijo, padre);
                }
            }
        }
    }

    public Object obtenerDato(Comparable clave) {

    	Object dato = null;
    	boolean pertenece = false;
    	NodoAVLDicc nodo = this.raiz;
    	Comparable claveAux;
    	while (nodo != null && !pertenece) {
    		claveAux = nodo.getClave();
    		if (claveAux.equals(clave)) {
    			pertenece = true;
    			dato = nodo.getDato();
    		} else if (claveAux.compareTo(clave) > 0) {
    			nodo = nodo.getIzquierdo();
    		} else if (claveAux.compareTo(clave) < 0) {
    			nodo = nodo.getDerecho();
    		}
    	}
    	return dato;
    }

    public boolean existeClave(Comparable clave) {

    	boolean pertenece = false;
    	NodoAVLDicc nodo = this.raiz;
    	Comparable elemento;
    	while (nodo != null && !pertenece) {
    		elemento = nodo.getClave();
    		if(elemento.equals(clave)) {
    			pertenece = true;
    		} else if (elemento.compareTo(clave) > 0) {
    			nodo = nodo.getIzquierdo();
    		} else if (elemento.compareTo(clave) < 0) {
    			nodo = nodo.getDerecho();
    		}
    	}
    	return pertenece;

    }

    public Lista listarClaves() {
    	Lista lista = new Lista();
    	listarAuxClave(this.raiz, lista);
    	return lista;
    }

    private void listarAuxClave(NodoAVLDicc nodo, Lista lista) {
    	if (nodo != null) {
    		listarAuxClave(nodo.getDerecho(), lista);
    		lista.insertar(nodo.getClave(), 1);
    		listarAuxClave(nodo.getIzquierdo(), lista);
    	}
    }

    public Lista listarDatos() {
    	Lista lista = new Lista();
    	listarAuxDato(this.raiz, lista);
    	return lista;

    }

    private void listarAuxDato(NodoAVLDicc nodo, Lista lista) {
    	if (nodo != null) {
    		listarAuxDato(nodo.getDerecho(), lista);
    		lista.insertar(nodo.getDato()+"\n", 1);
    		listarAuxDato(nodo.getIzquierdo(), lista);
    	}
    }

    public Lista listarRango(String codigo1, String codigo2) {
    	Lista lista = new Lista();
    	listarRangoAux(this.raiz, lista, codigo1, codigo2);
    	return lista;
    }

    private void listarRangoAux(NodoAVLDicc nodo, Lista lista, String codigo1, String codigo2) {
    	if (nodo != null) {
    		Comparable elemento = nodo.getClave();
    		if (elemento.compareTo(codigo2) < 0)
    			listarRangoAux(nodo.getDerecho(), lista, codigo1, codigo2);
    		if (elemento.compareTo(codigo1) >= 0 && elemento.compareTo(codigo2) <= 0)
    			lista.insertar(elemento, 1);
    		if (elemento.compareTo(codigo1) > 0)
    			listarRangoAux(nodo.getIzquierdo(), lista, codigo1, codigo2);
    	}
    }
    
    public String toString() {
    	
    	if(this.raiz != null)
    		return toStringAux(this.raiz,"");
    	else
    		return "Arbol Vacio";
    }

    // copiado de arbol binario
    private String toStringAux(NodoAVLDicc nodo, String s) {
    	if (nodo != null) {
    		s += "\n" + nodo.getClave() + "\t";
    		NodoAVLDicc izquierdo = nodo.getIzquierdo();
    		NodoAVLDicc derecho = nodo.getDerecho();
    		if(izquierdo != null) 
    			s += "HI: " + izquierdo.getClave();
    		else
    			s += "-";
    		s += "\t";
    		if(derecho != null) 
    			s += "HD: " + derecho.getClave();
    		else
    			s += "-";
    		s = toStringAux(nodo.getIzquierdo(), s);
    		s = toStringAux(nodo.getDerecho(), s);
    	}
    	return s;
    }

}
