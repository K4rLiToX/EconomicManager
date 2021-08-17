package Principal;




import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

import ED.Date;
import ED.HashTable;
import ED.SeparateChainingHashTable;

public class Usuario {
	//Variables de Instancia - Version Alfa
	private String userName;		//Nombre de Usuario
	private String userPassword;	//Contraseña
	private Integer movId;			//Asignador de identificaciones para los movimientos
	private Integer evntId;
	private HashTable<Integer,Movimiento> movimientos = new SeparateChainingHashTable<>(100, 75);	//Lista de movimientos del usuario
	public Agenda agenda = new Agenda(); 			//Agenda de Contactos
	private Company company; //Informacion acerca de la empresa del Usuario
	private Calendario calendario = new Calendario();
	
	
	//Constructor - Alfa - Por Defecto vacío

	//Getter and Setters - Alfa
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public Integer getmovID() {
		return movId;
	}
	
	public void setmovId(Integer num) {
		movId = num;
	}
		
	//Métodos - Alfa
	public void clear() { 
		//Limpia los datos tras un cierre de sesión
		userName = null;
		userPassword = null;
		movId = null;
		movimientos = new SeparateChainingHashTable<>(100, 75);
		agenda = new Agenda();
		company = null;
	}
	
	public void insertarMovimiento(double cantidad,Date Fecha, String concepto, String Destinatario,String Category) {
		//Diseñado para GUI
		
		//Crear Movimiento
		Movimiento m = new Movimiento(cantidad,Fecha,concepto,movId,Destinatario,Category);
		
		//Añadir Movimiento a la Tabla
		movimientos.insert(movId, m);
		
		//Modificar Id
		movId++;
	}

	public void insertarMovimiento(Movimiento M) {
		// Diseñado para Cargar los datos de la bd
	
		Integer id = M.getIdentificador();
		movimientos.insert(id, M);
	}
	
	public void DeleteTransaction(Integer key) {
		movimientos.delete(key);
	}
	
	public Iterable<Movimiento> getIteradorMovimientos() {
		return movimientos.values();
	}
	
	public DefaultListModel<Movimiento> getMovementsList(){
		//Necesario para la JList de la GUI
		
		DefaultListModel<Movimiento> lista = new DefaultListModel<>();
		
		for(Movimiento m : this.getIteradorMovimientos()) {
			lista.addElement(m);
		}
		
		return lista;
	}

	//Métodos - Beta
	public void addContact(Contacto c) {
		agenda.insertarContacto(c);
	}
	
	public void addContact(String dni, String name, String Relationship,String tlfn) {
		Contacto c = new Contacto(dni,name,Relationship,tlfn);
		agenda.insertarContacto(c);
	}
	
	public String dumpContacts() {
		return agenda.toString();
	}
	
	public DefaultComboBoxModel<Contacto> getContactList() {
		DefaultComboBoxModel<Contacto> list = new DefaultComboBoxModel<>();
		
		for(Contacto c :agenda.getContactsIterator()) {
			list.addElement(c);
		}
		
		return list;
	}

	public DefaultListModel<Movimiento> filterby(String mainFilter, String subFilter) {
		DefaultListModel<Movimiento> list = new DefaultListModel<>();
		
		switch(mainFilter) {
			case "Show Everything":
				list = getMovementsList();
			break;	
		
			case "Date": 
				Date d = new Date(subFilter);
				
				for(Movimiento m: getIteradorMovimientos()) {
					if(m.getFecha().equals(d)) list.addElement(m);
				}
			break;
			
			case "Description":
				for(Movimiento m: getIteradorMovimientos()) {
					if(m.getConcepto().contains(subFilter)) list.addElement(m);
				}
			break;
			
			case "Related Contact":
				for(Movimiento m: getIteradorMovimientos()) {
					if(m.getContacto().contains(subFilter)) list.addElement(m);
				}
			break;
			
			case "Category":
				for(Movimiento m: getIteradorMovimientos()) {
					if(m.getCategory().contains(subFilter)) list.addElement(m);
				}
			break;
			
		}
		
		return list;
	}
	
	//Métodos - Undefined
	public void setCompany(Company c) {
		company = c;
	}
	
	public Company getCompany(){
		return company;
	}
	
	public Contacto getContactByName(String name) {
		if(name.equals("Otro...")) {
			return new Contacto("-","Not Defined Role","Other","-");
		}else {
			Contacto res = null;
		
		for(Contacto c : agenda.getContactsIterator()) {
			if(c.getNombreCompleto().equals(name)) {
				res = c;
				break;
			}
		}
		
		return res;
		}
		
	}

	
	//Metodos Calendario
	public void setevntId(Integer evntID) {
		this.evntId = evntID;
	}

	
	public Integer getevntID() {
		return this.evntId;
	}

	public void addEvent(Date fecha,String Descripcion,String Contacto) {
		Evento e = new Evento(evntId++,fecha,Descripcion,Contacto);
		
		this.addEvent(e);
	}
	
	public void addEvent(Evento e) {
		calendario.insertarEvento(e);
	}

	
	public String dumpEvents() {
		return calendario.toString();
	}

	public DefaultListModel<Evento> getEventsByDate(Date fecha) {
		DefaultListModel<Evento> list = new DefaultListModel<>();
		
		for(Evento e: calendario.getIteradorEventos()) {
			if(e.getFecha().equals(fecha)) {
				list.addElement(e);
			}
		}
		
		return list;
	}

	public void deleteEvent(Evento ev) {
		calendario.borrarEvento(ev);
	}

	public ListModel<Evento> filterEventsby(String mainFilter, String subFilter) {
		DefaultListModel<Evento> list = new DefaultListModel<>();
		
		switch(mainFilter) {
			case "Show Everything":
				list = getEventList();
			break;	
		
			case "Date": 
				Date d = new Date(subFilter);
				
				for(Evento m: calendario.getIteradorEventos()) {
					if(m.getFecha().equals(d)) list.addElement(m);
				}
			break;
			
			case "Description":
				for(Evento m: calendario.getIteradorEventos()) {
					if(m.getDescripcion().contains(subFilter)) list.addElement(m);
				}
			break;
			
			case "Related Contact":
				for(Evento m: calendario.getIteradorEventos()) {
					if(m.getContactoRelacionado().contains(subFilter)) list.addElement(m);
				}
			break;
			
		}
		
		return list;
	}

	public DefaultListModel<Evento> getEventList() {
		DefaultListModel<Evento> list = new DefaultListModel<>();
		for(Evento e :calendario.getIteradorEventos()) {
			list.addElement(e);
		}
		return list;
	}

}
