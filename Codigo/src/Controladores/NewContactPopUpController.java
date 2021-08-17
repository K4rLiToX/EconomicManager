package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Principal.Contacto;
import Principal.Usuario;
import Vistas.NewContactPopUp;
import Vistas.VistaAcciones;

public class NewContactPopUpController implements ActionListener {
	//Vistas
	NewContactPopUp ncpu;
	VistaAcciones va;
	
	//Usuario
	Usuario activeUser;
	
	 public NewContactPopUpController(VistaAcciones v1,NewContactPopUp v2,Usuario au) {
		va = v1;
		ncpu = v2;
		activeUser = au;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		switch(command) {
		
		case "NewOk":
			String ID = ncpu.getID();
			String Name = ncpu.getName();
			String Relation = ncpu.getRelation();
			String Phone = ncpu.getPhone();
			
			Contacto contacto = new Contacto(ID, Name, Relation, Phone);
			activeUser.agenda.insertarContacto(contacto);
			
			
			ncpu.setVisible(false);
			ncpu.Clean();
			va.setContacts(activeUser.getContactList());
		break;
		
		case "NewCancel":
			ncpu.Clean();
			ncpu.setVisible(false);
		break;
		}

	}

}
