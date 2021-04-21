package lineales.estaticas;

public class Pila {

	
	private static final int TAMANIO = 10;
	private int tope;
	private Object[] array;
	
	public Pila() {
		
		this.array = new Object[TAMANIO];
		this.tope = -1;

	}
	
	public boolean apilar(Object nuevoEle) {
		
		boolean exito;
		
		if(this.tope + 1 >= Pila.TAMANIO) {
			
			exito = false;
		}
		else {
			this.tope++;
			this.array[tope] = nuevoEle;
			exito = true;
		}
		return exito;
	}
	
	public boolean desapilar() {
		
		boolean exito;
		
		if(tope >= 0) {
			
			this.array[tope] = null;
			tope--;
			exito = true;
		}
		else {
			exito = false;
		}

		return exito;

	}

	public Object obtenerTope() {

		if(this.tope < 0) {
			return null;
		}
		Object x;

		x = this.array[tope];

		return x;

	}

	public boolean esVacia() {

		boolean exito;

		if(tope >= 0) {
			exito = false;
		}
		else {
			exito = true;
		}

		return exito;
	}

	public void vaciar() {

		while(tope != -1) {

			this.array[tope] = null;

			tope--;

		}

	}

	public Pila clone() {

		Pila nueva = new Pila();

		nueva.tope = this.tope;
		nueva.array = this.array.clone();

		return nueva;
	}

	public String toString() {

		String cadena = "[";


		for(int i = 0; i <= this.tope; i++) {


			cadena += this.array[i]+"-";

		}

		cadena += "]";
		return cadena;
	}

	public boolean equals(Pila x) {

		boolean iguales = false;
		
		for(int i = 0; i <= tope; i++) {
			
			if(this.array[i] == x.array[i]) {
				iguales = true;
			}
			else {
				iguales = false;
			}
		}
		
		return iguales;
			
	}

}