package Vistas;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Principal.Contacto;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ContactsView extends JPanel {
	
	private JList<Contacto> ContactsList;
	private JButton btnAddContact;
	private JButton btnEditContact;
	private JButton btnDeleteContact;
	private JButton btnViewContact;
	
	public ContactsView() {
		//Ajustes de la ventana
				this.setFocusTraversalKeysEnabled(false);
				this.setFocusable(false);
				this.setBackground(SystemColor.window);
				this.setLayout(null);
				
				//Lista de Movimientos
				ContactsList = new JList<>();
				ContactsList.setVisibleRowCount(300);
				ContactsList.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Contacts", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
				ContactsList.setBounds(22, 24, 410, 577);
				this.add(ContactsList);
				
				btnAddContact = new JButton("Add Contact");
				btnAddContact.setBounds(466, 24, 172, 29);
				add(btnAddContact);
				
				btnEditContact = new JButton("Edit Contact");
				btnEditContact.setBounds(650, 24, 172, 29);
				add(btnEditContact);
				
				btnDeleteContact = new JButton("Delete Contact");
				btnDeleteContact.setBounds(650, 65, 172, 29);
				add(btnDeleteContact);
				
				btnViewContact = new JButton("View Contact");
				btnViewContact.setBounds(466, 65, 172, 29);
				add(btnViewContact);
	}
	
	public void registrarControladorContactos(ActionListener ctr) {
		btnAddContact.addActionListener(ctr);
		btnAddContact.setActionCommand("Add Contact");
		
		btnEditContact.addActionListener(ctr);
		btnEditContact.setActionCommand("Edit Contact");
		
		btnDeleteContact.addActionListener(ctr);
		btnDeleteContact.setActionCommand("Delete Contact");
		
		btnViewContact.addActionListener(ctr);
		btnViewContact.setActionCommand("View Contact");
	}
	
	public void setContacts(DefaultComboBoxModel<Contacto> lista) {
		ContactsList.setModel(lista);
	}
	
	public Contacto getSelectedContact() {
		return ContactsList.getSelectedValue();
	}
	
}
