package tpFinal;

public class Pasaje {

	private String codigoVuelo;
	private int numAsiento;
	private String estado;
	private String fecha;
	
	public Pasaje(String codigoVuelo, int numAsiento, String estado, String fecha) {
		this.codigoVuelo = codigoVuelo;
		this.numAsiento = numAsiento;
		this.estado = estado;
		this.fecha = fecha;
	}
	
	public Pasaje(String codVuelo,String fecha) {
		this.codigoVuelo = codVuelo;
		this.fecha = fecha;
	}

	public String getCodigoVuelo() {
		return codigoVuelo;
	}
	
	public void setCodigoVuelo(String vuelo) {
		this.codigoVuelo = vuelo;
	}
	
	public int getNumAsiento() {
		return numAsiento;
	}
	
	public void setNumAsiento(int numAsiento) {
		this.numAsiento = numAsiento;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Pasaje [codigoVuelo=" + codigoVuelo + ", numAsiento=" + numAsiento + ", estado=" + estado + ", fecha="
				+ fecha + "]";
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoVuelo == null) ? 0 : codigoVuelo.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pasaje other = (Pasaje) obj;
		if (codigoVuelo == null) {
			if (other.codigoVuelo != null)
				return false;
		} else if (!codigoVuelo.equals(other.codigoVuelo))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		return true;
	}

	
	
	
}
