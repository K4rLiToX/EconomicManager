/**
 * @Author Pedro
 * @Author Isma
 */

import Vistas.*;
import Controladores.*;
import Principal.*;

public class Main {

	public static void main(String[] args) {
		// Vistas
		VistaLogIn vli = new VistaLogIn();
		VistaRegistro vr = new VistaRegistro();
		VistaAcciones va = new VistaAcciones();
		

		// Usuario
		Usuario activeUser = new Usuario();

		// Controladores
		ControladorLogIn cli = new ControladorLogIn(vli, vr, va, activeUser);
		ControladorRegistro cr = new ControladorRegistro(vli, vr);
		
		ControladorUsuario ca = new ControladorUsuario(vli, va, activeUser);
		ControladorMovimientos cm = new ControladorMovimientos(va,activeUser);
		ControladorDivisas cd = new ControladorDivisas(va);
		
		ControladorContactos cc = new ControladorContactos(va, activeUser);

		// Registro de Controladores
		vli.RegistrarControladorUsuario(cli);
		vr.registrarControlador(cr);
		va.RegistrarControladorUsuario(ca);
		va.RegistrarControladorMovimientos(cm);
		va.registrarControladorDivisas(cd);
		va.registrarControladorContactos(cc);
		
		// Acciones
		vli.setLocationRelativeTo(null);
		vli.setVisible(true);
	}

}
