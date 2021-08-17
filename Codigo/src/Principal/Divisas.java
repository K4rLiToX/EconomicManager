package Principal;

import java.util.ArrayList;
import java.util.List;

public abstract class Divisas {
	private static final double EUR = 1;
	private static final double USD = 1.1159;
	private static final double GBP = 0.8773;
	private static final double CAD = 1.5014;
	
	public static double toEUR(String divi, double cant) {
		double c=0;
		switch(divi) {
		case "EUR":
			c = cant;
			break;
		case "USD":
			c = cant/USD;
			break;
		case "GBP":
			c = cant/GBP;
			break;
		case "CAD":
			c = cant/CAD;
			break;
		}
		return c;
	}
	
	public static double fromEUR(String divi, double cant) {
		double c=0;
		switch(divi) {
		case "EUR":
			c = cant;
			break;
		case "USD":
			c = cant*USD;
			break;
		case "GBP":
			c = cant*GBP;
			break;
		case "CAD":
			c = cant*CAD;
			break;
		}
		return c;
	}
	
	public static double fromTo(String origen, String destino, double cant) {
		return fromEUR(destino, toEUR(origen, cant));
	}

	public static List<String> getRates(){
		List<String> rates= new ArrayList<>();
		
		rates.add("EUR\t- \t"+EUR);
		rates.add("USD\t- \t"+USD);
		rates.add("GBP\t- \t"+GBP);
		rates.add("CAD\t- \t"+CAD);

		
		return rates;
	}
}
