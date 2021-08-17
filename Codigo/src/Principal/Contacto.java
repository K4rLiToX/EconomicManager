package Principal;

public class Contacto {
	private String DNI;
	private String NombreCompleto;
	private String Relacion;
	private String telefono;

	public Contacto(String id, String nom, String rel, String tlf) {
		DNI = id;
		NombreCompleto = nom;
		Relacion = rel;
		telefono = tlf;
	}

	public String getNombreCompleto() {
		return NombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		NombreCompleto = nombreCompleto;
	}

	public String getRelacion() {
		return Relacion;
	}

	public void setRelacion(String relacion) {
		Relacion = relacion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	@Override

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DNI == null) ? 0 : DNI.hashCode());
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
		Contacto other = (Contacto) obj;
		if (DNI == null) {
			if (other.DNI != null)
				return false;
		} else if (!DNI.equals(other.getDNI()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return (NombreCompleto + "\t|\t" + Relacion);
	}
}
