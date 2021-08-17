/**
 * @Author Pedro
 */
package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ED.Date;
import Principal.Usuario;
import Vistas.NewMovementPopUp;
import Vistas.VistaAcciones;

public class NewMovementPopUpControler implements ActionListener {
	//Vistas
	NewMovementPopUp nmpu;
	VistaAcciones va;
	
	//Usuario
	Usuario activeUser;
	
	 public NewMovementPopUpControler(VistaAcciones v1,NewMovementPopUp v2,Usuario au) {
		va = v1;
		nmpu = v2;
		activeUser = au;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		switch(command) {

		case "Ingreso":
			nmpu.setSelected(command);
		break;
		
		case "Gasto":
			nmpu.setSelected(command);
		break;
		
		case "NewOk":
			double amount = nmpu.getAmount();
			
			Date date =  nmpu.getDate();
			String Description = nmpu.getDescription();
			String Destination = nmpu.getDestination().getNombreCompleto();
			String Category    = nmpu.getCategory();
			
			activeUser.insertarMovimiento(amount, date, Description, Destination,Category);
			
			
			nmpu.setVisible(false);
			nmpu.Clean();
			va.SetMovements(activeUser.getMovementsList());
		break;
		
		case "NewCancel":
			nmpu.Clean();
			nmpu.setVisible(false);
		break;
		}

	}

}
