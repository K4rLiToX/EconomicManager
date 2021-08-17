/**
 * @Author Pedro 
 */

package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import Excepciones.AlreadyInUseException;
import Herramientas.DataBaseManager;
import Principal.Company;
import Vistas.VistaLogIn;
import Vistas.VistaRegistro;

public class ControladorRegistro implements ActionListener {
	// Vistas
	VistaLogIn vli;
	VistaRegistro vr;

	public ControladorRegistro(VistaLogIn vli, VistaRegistro vr) {
		this.vli = vli;
		this.vr = vr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		String Usuario;
		
		switch (command) {
		case "Registro Terminado":
			try {
				 //Leer Datos de los campos - Usuario
				 Usuario = vr.getUsuario();
				 String Password = vr.getPassword();
				 
				 //Leer Datos de los Campos 
				 String CName = vr.getCName();
				 String CPhone = vr.getCPhone();
				 String CEMail = vr.getCEmail();
				 String CAddress =  vr.getCAddress();
				 
				 Company c = new Company(CName,CPhone,CEMail,CAddress);
				 

				// Registrar Usuario
				DataBaseManager.RegistrarUsuario(Usuario, Password,c);

				// Volver a menu de logIn
				vr.setVisible(false);
				vr.limpiarVista();
				vli.setVisible(true);

				// Emitir mensaje de Registro Con éxito
				vli.setColorNormal();
				vli.printSomething("Register Successfully done");

			} catch (AlreadyInUseException aiu) {// En caso de que el nombre de usuario ya exista
				// Mostrar Mensaje de Error y Reiniciar Proceso
				vr.limpiarVista();
				vr.printSomething(aiu.getMessage());
			}
		break;

		case "Volver LogIn":
			 Usuario = vr.getUsuario();
			File posibleImage = new File("BD/"+Usuario+".jpg");
			
			if(posibleImage.exists()) {
				posibleImage.delete();
			}
			
			vr.limpiarVista();
			vr.setVisible(false);
			vli.setVisible(true);
		break;
		
		case "Open File Chooser":
			 Usuario = vr.getUsuario();
			
			if(Usuario.equals("")) {
				vr.printSomething("First of all you have to choose a User ID");
			}else {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "JPG & PNG Images", "jpg", "png");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					DataBaseManager.myCreateImage(chooser.getSelectedFile(),Usuario,"Create");
				}
			}
			
			break;
		}
	}

}
