package jerarquicas;

public class NodoGen {
	

	private Object elem;
	private NodoGen hijoIzquierdo;
	private NodoGen hermanoDerecho;


	public NodoGen(Object elem, NodoGen izquierdo, NodoGen derecho) {

		this.elem = elem;
		this.hijoIzquierdo = izquierdo;
		this.hermanoDerecho = derecho;
	}

	public Object getElem() {


		return this.elem;

	}

	public NodoGen getHijoIzquierdo() {

		return this.hijoIzquierdo;
	}

	public NodoGen getHermanoDerecho() {

		return this.hermanoDerecho;
	}

	public void setElem(Object x) {

		this.elem = x;

	}

	public void setHijoIzquierdo(NodoGen x) {

		this.hijoIzquierdo = x;

	}

	public void setHermanoDerecho(NodoGen x) {

		this.hermanoDerecho = x;

	}
}

