package tdaEspeciales;

public class NodoAVLDicc {

	private int altura;
	private Comparable clave;
	private Object dato;
	private NodoAVLDicc izquierdo;
	private NodoAVLDicc derecho;

	public NodoAVLDicc(Comparable clave,Object dato, NodoAVLDicc izquierdo, NodoAVLDicc derecho) {
		this.dato = dato;
		this.clave = clave;
		this.izquierdo = izquierdo;
		this.derecho = derecho;
		recalcularAltura();
	}

	public NodoAVLDicc(Comparable clave,Object dato) {
		this.dato = dato;
		this.clave = clave;
		recalcularAltura();
	}

	public int getAltura() {
		return this.altura;
	}

	public void recalcularAltura() {
		int altD = -1, altI = -1;
		if (this.derecho != null) {
			altD = this.derecho.getAltura();
		}
		if (this.izquierdo != null) {
			altI = this.izquierdo.getAltura();
		}
		this.altura = Math.max(altI, altD) + 1;
	}

	public Comparable getClave() {
		return this.clave;
	}

	public void setClave(Comparable clave) {
		this.clave = clave;
	}

	public Object getDato() {
		return this.dato;
	}
	
	public void setDato(Object dato) {
		this.dato = dato;
	}

	public NodoAVLDicc getIzquierdo() {
		return this.izquierdo;
	}

	public void setIzquierdo(NodoAVLDicc izquierdo) {
		this.izquierdo = izquierdo;
	}

	public NodoAVLDicc getDerecho() {
		return this.derecho;
	}

	public void setDerecho(NodoAVLDicc derecho) {
		this.derecho = derecho;
	}

	public int calcularBalance() {
		return ((this.izquierdo == null) ? -1 : this.izquierdo.altura) - ((this.derecho == null) ? -1 : this.derecho.altura);	//PROBANDO IF linea
	}
}
