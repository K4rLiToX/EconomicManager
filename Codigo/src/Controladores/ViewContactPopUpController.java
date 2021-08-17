package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Principal.Contacto;
import Principal.Usuario;
import Vistas.ViewContactPopUp;
import Vistas.VistaAcciones;

public class ViewContactPopUpController implements ActionListener {
	//Vistas
	ViewContactPopUp vcpu;
	VistaAcciones va;
	
	//Usuario
	Usuario activeUser;
	
	//Contacto seleccionado
	Contacto c;
	
	 public ViewContactPopUpController(VistaAcciones v1,ViewContactPopUp v2,Usuario au) {
		va = v1;
		vcpu = v2;
		activeUser = au;
	}
	 
	 public void setContactToDisplay(Contacto c) {
		 this.c = c;
		 
		 vcpu.setName(c.getNombreCompleto());
		 vcpu.setPhone(c.getTelefono());
		 vcpu.setRelation(c.getRelacion());
		 vcpu.setID(c.getDNI());
	 }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		switch(command) {
		
		case "Close":
			vcpu.setVisible(false);
		break;
		
		}

	}

}
