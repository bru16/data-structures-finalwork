package tpFinal;

import lineales.dinamicas.Lista;

public class Vuelo {

	public Vuelo(String claveVuelo) {
		this.clave = claveVuelo;
	}
	
	public Vuelo(String claveVuelo, String origen, String destino, int horaSalida, int horaLlegada) {
		this.clave = claveVuelo;
		this.origen = origen;
		this.destino = destino;
		this.horaLlegada = horaLlegada;
		this.horaSalida = horaSalida;
		this.viajes = new Lista();
	}
	
	private String clave;
	private String origen;
	private String destino;
	private int horaSalida;
	private int horaLlegada;
	private Lista viajes;

	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vuelo other = (Vuelo) obj;
		if (clave == null) {
			if (other.clave != null)
				return false;
		} else if (!clave.equals(other.clave))
			return false;
		if (destino == null) {
			if (other.destino != null)
				return false;
		} else if (!destino.equals(other.destino))
			return false;
		if (horaLlegada != other.horaLlegada)
			return false;
		if (horaSalida != other.horaSalida)
			return false;
		if (origen == null) {
			if (other.origen != null)
				return false;
		} else if (!origen.equals(other.origen))
			return false;
		if (viajes == null) {
			if (other.viajes != null)
				return false;
		} else if (!viajes.equals(other.viajes))
			return false;
		return true;
	}

	public int getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(int horaSalida) {
		this.horaSalida = horaSalida;
	}

	public int getHoraLlegada() {
		return horaLlegada;
	}

	public void setHoraLlegada(int horaLlegada) {
		this.horaLlegada = horaLlegada;
	}

	public String getClave() {
		return clave;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Lista getViajes() {
		return viajes;
	}

	public void setVuelos(Lista viajes) {
		this.viajes = viajes;
	}
	
	public boolean sinViajes() {
		return this.viajes.esVacia();
	}

	@Override
	public String toString() {
		return "[Vuelo =" + clave + ", origen =" + origen + ", destino =" + destino + ", hora de salida =" + horaSalida
				+ ", hora de llegada =" + horaLlegada + " ";
	}
	
}
