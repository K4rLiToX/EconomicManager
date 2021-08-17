package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Principal.Contacto;
import Principal.Usuario;
import Vistas.EditContactPopUp;
import Vistas.VistaAcciones;

public class EditContactPopUpController implements ActionListener {
	//Vistas
	EditContactPopUp ecpu;
	VistaAcciones va;
	
	//Usuario
	Usuario activeUser;
	Contacto original;
	
	 public EditContactPopUpController(VistaAcciones v1,EditContactPopUp v2,Usuario au) {
		va = v1;
		ecpu = v2;
		activeUser = au;
	}
	 
	 public void setContactoToEdit(Contacto c) {
		 original = c;
		 
		 ecpu.setName(c.getNombreCompleto());
		 ecpu.setID(c.getDNI());
		 ecpu.setPhone(c.getTelefono());
		 ecpu.setRelation(c.getRelacion());
	 }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		switch(command) {
		
		case "NewOk":
			String ID = ecpu.getID();
			String Name = ecpu.getName();
			String Relation = ecpu.getRelation();
			String Phone = ecpu.getPhone();
			
			Contacto contacto = new Contacto(ID, Name, Relation, Phone);
			activeUser.agenda.borrarContacto(original);
			activeUser.agenda.insertarContacto(contacto);
			
			
			ecpu.setVisible(false);
			ecpu.Clean();
			va.setContacts(activeUser.getContactList());
		break;
		
		case "NewCancel":
			ecpu.Clean();
			ecpu.setVisible(false);
		break;
		}

	}

}
