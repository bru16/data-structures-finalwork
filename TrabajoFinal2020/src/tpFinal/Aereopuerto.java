package tpFinal;

public class Aereopuerto {
	
	private String nombreAereonautico;
	private String ciudad;
	private String telefono;

	public Aereopuerto(String nombre, String ciu, String tel) {

		this.nombreAereonautico = nombre;
		this.ciudad = ciu;
		this.telefono = tel;
	}

	public Aereopuerto(String nombre) {

		this.nombreAereonautico = nombre;
	}


	@Override
	public boolean equals(Object obj) {
		
		boolean res;
		if(obj == null)
			res = false;
		else {
			Aereopuerto aux = (Aereopuerto) obj;
			res = (this.nombreAereonautico.equals(aux.getNombreAereonautico()));
		}
		return res;
	}
	
	public String getNombreAereonautico() {
		return this.nombreAereonautico;
	}
	
	public void setTelefono(String tel) {
		this.telefono = tel;
	}
	
	public String toString() {
		return this.nombreAereonautico+" " + this.ciudad +" "+ this.telefono;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setNombreAereonautico(String nombreAereonautico) {
		this.nombreAereonautico = nombreAereonautico;
	}
	
}
