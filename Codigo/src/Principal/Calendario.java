package Principal;

import ED.HashTable;
import ED.SeparateChainingHashTable;

public class Calendario {
	//Fields
	HashTable<Integer,Evento> eventos;
	
	public Calendario() {
		eventos = new SeparateChainingHashTable<Integer,Evento>(100, 75);
	}
	
	public void insertarEvento (Evento e) {
		eventos.insert(e.getId(), e);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(Evento e :eventos.values()) {
			sb.append("EVNT>>"+e.getId()+">>"+e.getFecha()+">>"+e.getDescripcion()+">>"+e.getContactoRelacionado()+"\n");
		}
		
		
		return sb.toString();
	}

	public Iterable<Evento> getIteradorEventos(){
		return eventos.values();
	}

	public  void borrarEvento(Evento ev) {
		eventos.delete(ev.getId());
	}

	
}
