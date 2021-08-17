/**
 * @Author Pedro
 * @Author Isma
 */

package Vistas;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import Principal.Contacto;
import Principal.Movimiento;
import Principal.Usuario;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class VistaAcciones extends JFrame {
	//Contenedor Exterior
	private JTabbedPane contentPane;
	
	//Pestaña Usuario - Alfa
	private UserView UserPane;		//Contenedor
	
	//Pestaña Movimientos - Alfa
	private TransactionView TransactionsPane;	//Contenedor
	
	//Pestaña cambio de moneda
	private ConcurrencyView Divisas;
	
	//Pestaña para los contactos
	private ContactsView Contactos;
	
	//Pestaña para el calendario
	private CalendarView CalendarPane;
	
	//Constructor
	public VistaAcciones() {
		//Alfa
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 700);
		
		contentPane = new JTabbedPane();
		setContentPane(contentPane);
		
		//Usuario
		UserPane = new UserView();
		contentPane.addTab("User", UserPane);
		
		
		//Movimientos
		TransactionsPane = new TransactionView();
		contentPane.addTab("Transactions", TransactionsPane);
		
		//Contactos
		Contactos = new ContactsView();
		contentPane.addTab("Contacts", Contactos);
		
		//Calendario
		CalendarPane = new CalendarView();
		contentPane.addTab("Calendar", CalendarPane);
		
		
		//Divisas
		Divisas = new ConcurrencyView();
		contentPane.addTab("Badge Exchange", Divisas);
		
	}
	
	//Registro de Controladores
	public void RegistrarControladorUsuario(ActionListener ctr) {
		UserPane.RegistrarControladorUsuario(ctr);
	}
	
	public void RegistrarControladorMovimientos(ActionListener ctr) {	
		TransactionsPane.RegistrarControladorMovimientos(ctr);
	}

	public void registrarControladorDivisas(ActionListener ctr) {
		Divisas.registrarControladorDivisas(ctr);
	}
	
	public void registrarControladorContactos(ActionListener ctr) {
		Contactos.registrarControladorContactos(ctr);
	}
	
	//Servicios - Usuario
	public void SetView(Usuario activeUser) {
		UserPane.SetView(activeUser);
	}
	
	public void setUserImage(Usuario activeUser){
		UserPane.SetUserImage(activeUser);
	}
	
	public void setFieldsEditable() {
		UserPane.SetFieldsEditable();
	}
	
	public void ReadAndSetFields(Usuario activeUser) {
		UserPane.ReadAndSetFields(activeUser);
	}
	
	public void setFieldsUnEditable() {
		UserPane.setFieldsUnEditable();
		
	}

	//Servicios - Movimientos - Alfa
	public void SetMovements(DefaultListModel<Movimiento> lista) {
		TransactionsPane.SetMovements(lista);
	}

	public Movimiento getSelectedMovement() {
		return TransactionsPane.getSelectedMovement();
	}
	
	public String getMainFilter() {
		return TransactionsPane.getMainFilter();
	}

	public String getSubFilter() {
		return TransactionsPane.getSubFilter();
	}

	public void clearFilters() {
		TransactionsPane.clearFilters();
	}
	
	public Object[] getExcelInfo() {
		return TransactionsPane.getExcelInfo();
	}

	//Servicios - Calculo de Divisas
	public String divisaOrigen() {
		return Divisas.divisaOrigen();
	}
	
	public String divisaDestino() {
		return Divisas.divisaDestino();
	}
	
	public double divisaCantidad() {
		return Divisas.divisaCantidad();
	}
	
	public void divisaDeSalida(double cant) {
		Divisas.divisaDeSalida(cant);
	}

	public void setRates() {
		Divisas.SetRates();
	}
	
	//Servicios - Contactos
	public void setContacts(DefaultComboBoxModel<Contacto> lista) {
		Contactos.setContacts(lista);
	}
	
	public Contacto getSelectedContact() {
		return Contactos.getSelectedContact();
	}
	
	//Servicios Calendario
	public void SetCalendarView(Usuario activeUser) {
		CalendarPane.startView(activeUser);
	}
	

	

	

	
}
