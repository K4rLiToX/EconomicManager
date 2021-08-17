/**
 * @Author Pedro
 */

package ED;

import java.util.StringTokenizer;

public class Date {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dia;
		result = prime * result + mes;
		result = prime * result + year;
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
		Date other = (Date) obj;
		if (dia != other.dia)
			return false;
		if (mes != other.mes)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	public int compareTo(Object d) {
		Date d2 = (Date) d;
		
		if(this.year > d2.getYear()) {
			return 1;
		}else if (this.year == d2.getYear()) {
			if(this.mes > d2.getMonth()) {
				return 1;
			}else if(this.mes == d2.getMonth()) {
				if(this.dia > d2.getDay()) {
					return 1;
				}else if (this.dia == d2.getDay()) {
					return 1;
				}else {
					return -1;
				}
			}else {
				return -1;
			}
			
		}else {
			return -1;
		}
	}
	
	private int dia;
	private int mes;
	private int year;
	
	public Date(int d,int m,int y) {
		dia = d;
		mes = m;
		year = y;
	}

	public Date(String date) {
		StringTokenizer st = new StringTokenizer(date,"/");
		
		dia = Integer.parseInt(st.nextToken());
		mes = Integer.parseInt(st.nextToken());
		year = Integer.parseInt(st.nextToken());
	}

	public static java.util.Date getSysDate() {
		return new java.util.Date();
	}

	public int getDay() {
		return dia;
	}

	public void setDay(int dia) {
		this.dia = dia;
	}

	public int getMonth() {
		return mes;
	}

	public void setMonth(int mes) {
		this.mes = mes;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public String toString() {
		return dia+"/"+mes+"/"+year;
	}

	
	@SuppressWarnings("rawtypes")
	public static Comparable showGraph(double f, double g) {
		String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		
		int m = (int) f;
		int y = (int) g;
		
		return months[m-1]+"/"+y;
	}
}
