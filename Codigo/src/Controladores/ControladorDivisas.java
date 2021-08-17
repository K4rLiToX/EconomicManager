/**
 * @Author Isma
 */

package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Principal.Divisas;
import Vistas.VistaAcciones;

public class ControladorDivisas implements ActionListener{
	VistaAcciones va;
	
	public ControladorDivisas(VistaAcciones va) {
		this.va = va;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("Calcular")) {
			String origen = va.divisaOrigen();
		String destino = va.divisaDestino();
		double cant = va.divisaCantidad();
		
		double cantFinal;
		
		cantFinal = Divisas.fromTo(origen, destino, cant);
		
		va.divisaDeSalida(cantFinal);
		}
	}

}
