/**
 * 
 */
package pruebas;

import java.io.File;

import org.junit.*;

import Excepciones.*;
import Herramientas.*;
import Principal.Company;
import Principal.Usuario;

/**
 * @author RUBEN
 *
 */
public class TestGestorEconomico {
	DataBaseManager dt;
	ExcelMaker em;
	GraphicDisplayer gd;
	@Before
	public void init() {
		dt=new DataBaseManager();
		em = new ExcelMaker();
		gd = new GraphicDisplayer();
	}
	
	@After
	public void terminate() {
		dt = null;
		em = null;
		gd = null;
	}
	@Test (expected = NonExistingUserException.class)
	public void UsuarioNoExiste() throws NonExistingUserException, InvalidPasswordException {
		dt.LogIn(null , null, null);
	}
	@Test (expected = InvalidPasswordException.class)
	public void ContrasenaIncorrecta() throws NonExistingUserException, InvalidPasswordException, AlreadyInUseException {
		dt.BorrarUsuario("d");
		dt.RegistrarUsuario("d", "a", new Company(null, null, null, null));
		dt.LogIn("d", "sdf", new Usuario());
	}
	@Test (expected = AlreadyInUseException.class)
	public void UsuarioEnUso() throws AlreadyInUseException  {
		dt.RegistrarUsuario("", "", null);
		dt.RegistrarUsuario("", "", null);
	}
	@Test (expected = EmptyInfoException.class)
	public void generarPDFVacio() throws EmptyInfoException {
		em.generateExcelFile(new Object[0], new Usuario());
	}
	@Test (expected = EmptyInfoException.class)
	public void generarGraficaVacia() throws EmptyInfoException {
		gd.generateGraphicsPdf(new Object[0], new Usuario());
	}
}
