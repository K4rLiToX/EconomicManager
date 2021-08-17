/**
 * @Author Pedro
 **/

package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Excepciones.*;
import Principal.*;
import Vistas.*;
import Herramientas.*;

public class ControladorLogIn implements ActionListener {
	// Vistas
	VistaLogIn vli;
	VistaRegistro vr;
	VistaAcciones va;

	// Usuario Activo
	Usuario activeUser;

	// Constructor
	public ControladorLogIn(VistaLogIn vli, VistaRegistro vr, VistaAcciones va, Usuario aU) {
		this.vli = vli;
		this.vr = vr;
		this.va = va;
		activeUser = aU;
	}

	// Acciones
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		switch (command) {
		case "Iniciar Sesion":
			try {
				// Obtener Datos de los Campos de texto
				String Username = vli.getUserText();
				String Password = vli.getPassword();

				// Comprobar Contraseña
				DataBaseManager.LogIn(Username, Password, activeUser);

				// Leer Movimientos
				DataBaseManager.CargarDatos(activeUser);

				// Cambiar a menú Principal
				vli.LimpiarVista();
				vli.setVisible(false);
				
				// Inicializa Pestaña Usuario
				va.setUserImage(activeUser);
				va.SetView(activeUser);
				
				//Inicializa Pestaña Movimientos
				va.SetMovements(activeUser.getMovementsList()); 
				
				//Inicializa Pestaña Contactos
				va.setContacts(activeUser.getContactList());
				
				//Inicializa Pestaña Divisas
				va.setRates();
				
				//Inicializa Calendario
				va.SetCalendarView(activeUser);
				
				va.setLocationRelativeTo(vli);
				va.setVisible(true);
			} catch (NonExistingUserException neu) { // No existe el fichero de Usuario en la BD

				// Mostrar Mensaje de error y Reiniciar LogIn
				vli.LimpiarVista();
				vli.setColorRed();
				vli.printSomething(neu.getMessage());
			} catch (InvalidPasswordException ip) { // Contraseña Incorrecta

				// Mostrar Mensaje de error y Reiniciar LogIN
				vli.CleanPassword();
				vli.setColorRed();
				vli.printSomething(ip.getMessage());
			}

			break;

		case "Cambiar a Registro":
			vli.setVisible(false);
			vli.LimpiarVista();
			vr.setLocationRelativeTo(vli);
			vr.setVisible(true);
			break;

		}
	}

}
