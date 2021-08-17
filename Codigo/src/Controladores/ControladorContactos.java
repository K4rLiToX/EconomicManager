package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Principal.Contacto;
import Principal.Usuario;
import Vistas.EditContactPopUp;
import Vistas.NewContactPopUp;
import Vistas.ViewContactPopUp;
import Vistas.VistaAcciones;

public class ControladorContactos implements ActionListener {
	
	//Vistas
	VistaAcciones vista;
	NewContactPopUp ncpu = new NewContactPopUp();
	ViewContactPopUp vcpu = new ViewContactPopUp();
	EditContactPopUp ecpu = new EditContactPopUp();
	
	//Controladores
	NewContactPopUpController ncpuc;
	ViewContactPopUpController vcpuc;
	EditContactPopUpController ecpuc;
		
	//Usuario
	Usuario activeUser;
	
	public ControladorContactos(VistaAcciones v, Usuario au) {
		activeUser = au;
		vista = v;
		
		ncpuc = new NewContactPopUpController(vista, ncpu, activeUser);
		vcpuc = new ViewContactPopUpController(vista, vcpu, activeUser);
		ecpuc = new EditContactPopUpController(vista, ecpu, activeUser);
		
		ncpu.registraControlador(ncpuc);
		vcpu.registraControlador(vcpuc);
		ecpu.registraControlador(ecpuc);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		switch (command) {
			case "Add Contact":
				ncpu.setVisible(true);
				
			break;
			
			case "Delete Contact":
				Contacto c = vista.getSelectedContact();
				
				activeUser.agenda.borrarContacto(c);
				
				vista.setContacts(activeUser.getContactList());
			break;
			
			case "Edit Contact":
				ecpuc.setContactoToEdit(vista.getSelectedContact());
				ecpu.setVisible(true);
			break;
			
			case "View Contact":
				vcpuc.setContactToDisplay(vista.getSelectedContact());
				vcpu.setVisible(true);
			break;
				
			
				
		}

	}

}
