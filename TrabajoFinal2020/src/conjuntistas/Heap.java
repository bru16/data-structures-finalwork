package conjuntistas;

public class Heap {


	private static final int TAMANIO = 30;
	@SuppressWarnings("rawtypes")
	private Comparable[] heap;
	private int ultimo;


	public Heap() {

		ultimo = 0;
		this.heap = new Comparable[TAMANIO];
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	public boolean insertar(Comparable elem) {

		boolean exito = (this.ultimo < this.TAMANIO-1);

		if(exito) {
			this.ultimo++;
			this.heap[ultimo] = elem;
			if(this.ultimo != 1) {
				int pos = this.ultimo;	// posicion del ultimo elem, (pos / 2) = al padre.
				while(pos > 1 && (this.heap[pos].compareTo(this.heap[(int)pos/2]) < 0)) {
					Comparable aux = this.heap[pos];
					this.heap[pos] = this.heap[(int) pos/2];
					this.heap[(int) pos/2] = aux;
					pos = (int) pos / 2;


				}
			}
		}

		return exito;
	}

	public boolean eliminarCima() {

		boolean exito;

		if (this.ultimo == 0) {
			exito = false;
		}
		else {
			this.heap[1] = this.heap[ultimo];
			this.ultimo--;
			hacerBajar(1);
			exito = true;
		}
		return exito;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void hacerBajar(int posPadre) {

		int posH;
		Comparable temp = this.heap[posPadre];
		boolean salir = false;

		while(!salir) {
			posH = posPadre * 2;
			if(posH <= this.ultimo) {
				if(posH < this.ultimo) {
					if(this.heap[posH + 1].compareTo(this.heap[posH]) < 0) {
						posH++;
					}
				}
				if(this.heap[posH].compareTo(temp) < 0) {
					this.heap[posPadre] = this.heap[posH];
					this.heap[posH] = temp;
					posPadre = posH;
				}
				else 
					salir = true;
			}
			else 
				salir = true;
			
		}
	}

	@SuppressWarnings("rawtypes")
	public Comparable recuperarCima() {

		Comparable cima = null;

		if(this.ultimo != 0)
			cima = this.heap[1];

		return cima;
	}

	public boolean esVacio() {

		boolean exito = true;

		if(this.ultimo != 0)
			exito = false;
		
		return exito;
	}

	public Heap clone() {

		Heap clone = new Heap();

		for(int i = 1; i <= this.ultimo; i++) {
			clone.heap[i] = this.heap[i];
			clone.ultimo++;
		}

		return clone;
	}

	public String toString() {

		String cad = "";

		cad = cad + "Raiz: "+this.heap[1]+ "\n";
		for(int i = 1; i <= this.ultimo; i++) {
			if((i * 2) <= this.ultimo && this.heap[(i*2)] != null) 
				cad += "| HI de "+this.heap[i]+" es "+this.heap[i*2] + " - ";

			if((i * 2) + 1 <= this.ultimo && this.heap[(i*2) + 1] != null) 
				cad += "HD de "+this.heap[i]+" es "+this.heap[(i*2) + 1] + " | \n";

		}	
		return cad;
	}
}
