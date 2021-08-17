/**
 * @Author Pedro
 */

package Controladores;

import Vistas.*;
import Principal.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import Herramientas.DataBaseManager;

public class ControladorUsuario implements ActionListener {
	// Vistas
	VistaLogIn vli;
	VistaAcciones va;
	//Imagen imagen;
	
	//Vistas Internas
	DeleteAccountPopUp dapu = new DeleteAccountPopUp();
	
	//Controladores Internos
	DeleteAccountPopUpControler dapuc;
	
	// Usuario Activo
	Usuario ActiveUser;

	//Variables  Aux
	boolean firstTime;

	
	// Constructor
	public ControladorUsuario(VistaLogIn v1, VistaAcciones v2, Usuario au) {
		vli = v1;
		va = v2;
		ActiveUser = au;
		
		dapuc = new DeleteAccountPopUpControler(ActiveUser,dapu,va,vli);
		
		dapu.registrarControlador(dapuc);
		
		firstTime = true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String Command = e.getActionCommand();

		switch (Command) {
		case "Cerrar Sesion":
			// Guardar Cambios en la BD
			DataBaseManager.VolcarDatos(ActiveUser);

			// Cerrar Sesion
			ActiveUser.clear();

			// Volver a vista LogIN
			va.setVisible(false);
			vli.setVisible(true);
		break;

		case "Borrar Usuario":
			//Throw Confirmation Request
			dapu.setVisible(true);
		break;
			
		case "Change Image":
			String Usuario = ActiveUser.getUserName();
			
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(null);

			if(returnVal == JFileChooser.APPROVE_OPTION) {
				DataBaseManager.myCreateImage(chooser.getSelectedFile(),Usuario,"Change");
				va.setUserImage(ActiveUser);	
			}
		break;
		
		case "Edit Info":
			if(firstTime) {
				va.setFieldsEditable();
				firstTime = false;
			}else {
				va.ReadAndSetFields(ActiveUser);
				va.setFieldsUnEditable();
				va.SetView(ActiveUser);
				firstTime = true;
			}
		break;	
			
		}
	}

}
