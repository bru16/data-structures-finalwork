package lineales.estaticas;

public class Cola {

	private Object[] array;
	private int frente;
	private int fin;
	private static final int TAMANIO = 10;

	public Cola() {

		this.array = new Object[TAMANIO];
		this.frente = 0;
		this.fin = 0;
	}

	public boolean poner(Object elemento) {

		boolean exito;

		if (this.fin + 1 == this.frente) {
			exito = false;	// Cola llena.
		}
		else if (this.fin != 0 && (this.fin + 1) % Cola.TAMANIO == this.frente) {
			exito = false;
		}
		else {
			this.array[fin] = elemento;	// Se pone el elemento en la posicion fin y se incrementa "fin"
			this.fin = (this.fin + 1) % Cola.TAMANIO;
			exito = true;
		}

		return exito;
	}

	public boolean sacar() {

		boolean exito;

		if(this.fin == this.frente) {
			exito = false;
		}
		else {
			this.array[frente] = null;
			this.frente = (this.frente + 1) % Cola.TAMANIO;
			exito = true;

		}

		return exito;
	}

	public Object obtenerFrente() {

		Object elemento;

		if(this.frente == this.fin) {
			elemento = null;
		}
		else {
			elemento = this.array[frente];
		}

		return elemento;
	}

	public boolean esVacia() {

		boolean exito;

		if(this.frente == this.fin) {
			exito = true;
		}
		else {
			exito = false;
		}

		return exito;
	}

	public void vaciar() {

		for(int i = 0; i < Cola.TAMANIO; i++) {
			this.array[i] = null;
		}
		this.frente = 0;
		this.fin = 0;

	}

	public Cola clone() {

		Cola nueva = new Cola();

		nueva.fin = this.fin;
		nueva.frente = this.frente;

		for(int i = 0; i < Cola.TAMANIO; i++) {
			nueva.array[i] = this.array[i];
		}

		return nueva;

	}

	public String toString() {


		String s="[";
		int i=this.frente;
		while(i!=this.fin){
			s = s + this.array[i] + "-";
			i++;
			i = i % Cola.TAMANIO;
		}
		s+= "]";
		return s;

	}	

	
}
