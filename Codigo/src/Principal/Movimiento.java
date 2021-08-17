/**
 * @author RUBEN
 *
 **/
package Principal;

import ED.Date;

public class Movimiento implements Comparable<Movimiento> {
	//Atributos
	protected double cantidad;
	protected Date Fecha;
	protected String concepto,Contacto;
	protected Integer identificador;
	protected String Category;
	
	//Constructor - Alfa
	public Movimiento(double p_cantidad, Date p_fecha, String p_concepto, Integer p_identificador,String p_Contacto,String category) {
		cantidad = p_cantidad;
		Fecha = p_fecha;
		concepto = p_concepto;
		identificador = p_identificador;
		Contacto = p_Contacto;
		Category = category;
	}
	
	//Getters Setter - Alfa
	public double getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(double p_cantidad) {
		cantidad = p_cantidad;
	}
	
	public Date getFecha() {
		return Fecha;
	}
	
	public void setFecha(Date p_fecha) {
		Fecha = p_fecha;
	}
	
	public String getConcepto() {
		return concepto;
	}
	
	public void setConcepto(String p_concepto) {
		concepto = p_concepto;
	}
	
	public Integer getIdentificador() {
		return identificador;
	}
	
	public void setIdentificador(Integer p_identificador) {
		identificador = p_identificador;
	}
	
	public String getContacto() {
		return Contacto;
	}
	
	public void setContacto(String p_Contacto) {
		Contacto = p_Contacto;
	}
	
	public boolean isExpenditure() {
		return cantidad <= 0;
	}
	
	public boolean isEntry() {
		return cantidad > 0;
	}
	
	public String getCategory() {
		return Category;
	}
	
	public void setCategory(String categoria) {
		Category = categoria;
	}
	
	//Equals - Hashcode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
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
		Movimiento other = (Movimiento) obj;
		if (identificador == null) {
			if (other.identificador != null)
				return false;
		} else if (!identificador.equals(other.identificador))
			return false;
		return true;
	}
	
	
	//ToString
	public String toString() {
		if(cantidad > 0) {
			return this.concepto+"\t\t- Ingreso";
		}else {
			return this.concepto+"\t\t- Gasto";
		}
		
	}

	@Override
	public int compareTo(Movimiento o) {
		return this.Fecha.compareTo(o.getFecha());
	}
}
