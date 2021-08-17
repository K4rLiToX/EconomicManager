/**
 * @Author Pedro
 */

package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ED.Date;
import Principal.Usuario;
import Vistas.EditMovementPopUp;
import Vistas.VistaAcciones;

public class EditMovementPopUpControler implements ActionListener {
	//Vistas 
	VistaAcciones va;
	EditMovementPopUp empu;
	
	//Usuario
	Usuario activeUser;
	
	public EditMovementPopUpControler(VistaAcciones v1, EditMovementPopUp v2, Usuario au) {
		va = v1;
		empu = v2;
		activeUser = au;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		switch(command) {
		
		case "EditOk":
			double amount = empu.getAmount();
			Date date =  empu.getDate();
			String Description = empu.getDescription();
			String Destination = empu.getDestination();
			String Category    = empu.getCategory();
			
			Integer id = empu.getMovementID();
			activeUser.DeleteTransaction(id);
			activeUser.insertarMovimiento(amount, date, Description, Destination,Category);
			
			empu.setVisible(false);
			va.SetMovements(activeUser.getMovementsList());
		break;
		
		case "EditCancel":
			empu.setVisible(false);
		break;
		}
	}

}
