package tpFinal;

import conjuntistas.ArbolAVL;

public class Viaje {

	private int cantVendidos;
	private int cantTotales;
	private String fecha;
	private ArbolAVL asientos;
	
	public Viaje(String fecha,int cantTotales, ArbolAVL asientos) {
		
		this.fecha = fecha;
		this.cantTotales = cantTotales;
		this.asientos = asientos;
	}

	public int getCantVendidos() {
		return cantVendidos;
	}

	public void setCantVendidos(int cantVendidos) {
		this.cantVendidos = cantVendidos;
	}

	public int getCantTotales() {
		return cantTotales;
	}

	public void setCantTotales(int cantTotales) {
		this.cantTotales = cantTotales;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public ArbolAVL getAsientos() {
		return this.asientos;
	}

	@Override
	public String toString() {
		return "cantidad asientos vendidos =" + cantVendidos + ", cantidad asientos totales =" + cantTotales + ", fecha =" + fecha + "] \n";
	}

}
