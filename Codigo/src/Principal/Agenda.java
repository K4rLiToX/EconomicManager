package Principal;


import javax.swing.DefaultListModel;

import ED.HashTable;
import ED.SeparateChainingHashTable;

//toString de agenda entera

public class Agenda {
	private HashTable<String,Contacto> contactList;
	
	public Agenda() {
		contactList = new SeparateChainingHashTable<>(100,75);
	}
	
	public void insertarContacto(Contacto contacto) {
		contactList.insert(contacto.getDNI(), contacto);
	}
	
	public void borrarContacto(Contacto contacto) {
		contactList.delete(contacto.getDNI());
	}
	
	public DefaultListModel<Contacto> getContactsList(){
		//Necesario para la JList de la GUI
		
		DefaultListModel<Contacto> lista = new DefaultListModel<>();
		
		for(Contacto c : contactList.values()) {
			lista.addElement(c);
		}
		
		return lista;
	}
	
	/*public String mostrarContacto(Contacto contacto) {
		Iterator<Contacto> it = contactList.iterator();
		boolean encontrado = false;
		Contacto contactoAux = null;
		while(!contactList.isEmpty() && !encontrado && it.hasNext()) {
			contactoAux = it.next();
			if(contacto.equals(contactoAux)) {
				encontrado = true;
			}
		}
		return contactoAux.toString();
	}*/
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Contacto c : contactList.values()) {
			sb.append("CT>>"+c.getDNI()+">>"+c.getNombreCompleto()+">>"+c.getRelacion()+">>"+c.getTelefono()+"\n");
		}
		return sb.toString();
	}
	
	public Iterable<Contacto> getContactsIterator(){
		return contactList.values();
	}
}
