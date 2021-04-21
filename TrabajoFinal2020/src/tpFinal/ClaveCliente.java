package tpFinal;

public class ClaveCliente implements Comparable {

	private String tipoDni;
	private String numDni;
	
	public ClaveCliente(String numDni, String tipoDni) {
		this.tipoDni = tipoDni;
		this.numDni = numDni;
	}
	
	public String getTipoDni() {
		return tipoDni;
	}
	
	public String getNumDni() {
		return numDni;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numDni == null) ? 0 : numDni.hashCode());
		result = prime * result + ((tipoDni == null) ? 0 : tipoDni.hashCode());
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
		ClaveCliente other = (ClaveCliente) obj;
		if (numDni == null) {
			if (other.numDni != null)
				return false;
		} else if (!numDni.equals(other.numDni))
			return false;
		if (tipoDni == null) {
			if (other.tipoDni != null)
				return false;
		} else if (!tipoDni.equals(other.tipoDni))
			return false;
		return true;
	}

	@Override
	public int compareTo(Object claveCliente) {

		ClaveCliente x = (ClaveCliente) claveCliente;
		int condicion = this.tipoDni.compareTo(x.tipoDni);

		if(condicion == 0)
			condicion = this.numDni.compareTo(x.numDni);

		return condicion;
	}

	@Override
	public String toString() {
		return "tipoDni=" + tipoDni + ", numDni=" + numDni;
	}


	
}
