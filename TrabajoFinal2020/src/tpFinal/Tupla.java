package tpFinal;

public class Tupla implements Comparable {

	private ClaveCliente cliente;
	private Comparable cantPasajes;
	
	public Tupla(ClaveCliente x, Comparable y) {
		this.cliente = x;
		this.cantPasajes = y;
	}

	@Override
	public int compareTo(Object o) {
		
		Tupla tupla = (Tupla) o;
		int condicion = this.cantPasajes.compareTo(tupla.cantPasajes);
		return condicion;
	}
	
	public ClaveCliente getCliente() {
		return this.cliente;
	}
	
	public int getCantPasajes() {
		return (int)this.cantPasajes;
	}
}
