package Principal;

import ED.Date;

public class Evento {
	 private Integer id;
	 private Date fecha;
	 private String descripcion;
	 private String contacto;
	 
	 
	public Evento(Integer id, Date fecha, String descripcion, String contactoRelacionado) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.contacto = contactoRelacionado;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Evento other = (Evento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getContactoRelacionado() {
		return contacto;
	}

	public void setContactoRelacionado(String contactoRelacionado) {
		this.contacto = contactoRelacionado;
	}

	public String toString() {
		return descripcion +"|" + contacto;
				
	}

}
