/**
 * @Author Pedro
 */

package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vistas.ViewMovementPopUp;

public class ViewMovementPopUpControler implements ActionListener {
	//Vistas
	ViewMovementPopUp vmpu;
	
	
	public ViewMovementPopUpControler(ViewMovementPopUp vmpu2) {
		vmpu = vmpu2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		switch(command) {
			case "Done":
				vmpu.setVisible(false);
			break;
		}
	}

}
