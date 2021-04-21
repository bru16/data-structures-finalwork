package tpFinal;


public class Cliente {

	private String numeroDni;
	private String tipoDni;
	private String nombre;
	private String apellido;
	private String fechaNacimiento;
	private String domicilio;
	private String telefono;

	public Cliente(String nroDocumento, String tipoDocumento) {

		this.numeroDni = nroDocumento;
		this.tipoDni = tipoDocumento;
	}

	public Cliente(String nroDocumento, String tipoDocumento, String name, String lastName, String fechaNac, String dom, String tel) {

		this.numeroDni = nroDocumento;
		this.tipoDni = tipoDocumento;
		this.nombre = name;
		this.apellido = lastName;
		this.fechaNacimiento = fechaNac;
		this.domicilio = dom;
		this.telefono = tel;
	}

	public String getNumeroDni() {
		return this.numeroDni;
	}

	public String getTipoDni() {
		return this.tipoDni;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	public String getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public String getDomicilio() {
		return this.domicilio;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setNumeroDni(String x) {
		this.numeroDni = x;
	}

	public void setTipoDni(String x) {
		this.tipoDni = x;
	}

	public void setNombre(String name) {
		this.nombre = name;
	}

	public void setApellido(String lastName) {
		this.apellido = lastName;
	}

	public void setTelefono(String tel) {
		this.telefono = tel;
	}

	public void setDomicilio(String dom) {
		this.domicilio = dom;
	}

	public void setFechaNacimiento(String fecha) {
		this.fechaNacimiento = fecha;
	}
	
	public String toString() {
		return this.apellido + " " + this.nombre + " " + this.tipoDni + " " + this.numeroDni + " " + this.fechaNacimiento + " " +this.domicilio +" " + this.telefono;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((domicilio == null) ? 0 : domicilio.hashCode());
		result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((numeroDni == null) ? 0 : numeroDni.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
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
		Cliente other = (Cliente) obj;
		if (numeroDni == null) {
			if (other.numeroDni != null)
				return false;
		} else if (!numeroDni.equals(other.numeroDni))
			return false;
		if (tipoDni == null) {
			if (other.tipoDni != null)
				return false;
		} else if (!tipoDni.equals(other.tipoDni))
			return false;
		return true;
	}
	
	
}
