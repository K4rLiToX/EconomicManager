package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Herramientas.DataBaseManager;
import Principal.Usuario;
import Vistas.DeleteAccountPopUp;
import Vistas.VistaAcciones;
import Vistas.VistaLogIn;

public class DeleteAccountPopUpControler implements ActionListener {
	//Usuario
	Usuario ActiveUser;
	
	//Vistas
	DeleteAccountPopUp dapu;
	VistaAcciones va;
	VistaLogIn vli;
	
	public DeleteAccountPopUpControler(Usuario au,DeleteAccountPopUp v, VistaAcciones v1, VistaLogIn v2) {
		ActiveUser = au;
		dapu = v;
		va = v1;
		vli = v2;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String Command = e.getActionCommand();
		
		switch(Command) {
			case "OK":
				DataBaseManager.BorrarUsuario(ActiveUser.getUserName());
				ActiveUser.clear();
				
				// Cambiar Vista
				dapu.setVisible(false);
				va.setVisible(false);
				vli.printSomething("Account Succesfully Deleted");
				vli.setVisible(true);
				break;
			
			case "Cancel":
				dapu.setVisible(false);
				break;
		}
	}

}
