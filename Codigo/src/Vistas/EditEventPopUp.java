package Vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ED.Date;
import Principal.Contacto;
import Principal.Evento;
import Principal.Usuario;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class EditEventPopUp extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JComboBox<Contacto> ContactBox;
	private JComboBox<String> daybox;
	private JComboBox<String> monthBox;
	private JComboBox<String> yearBox;

	/**
	 * Create the dialog.
	 */
	public EditEventPopUp(Evento event,Usuario activeUser) {
		setBounds(100, 100, 450, 196);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
			JLabel lblDay = new JLabel("Day");
			lblDay.setBounds(6, 6, 61, 16);
			contentPanel.add(lblDay);
	
			JLabel lblMonth = new JLabel("Month");
			lblMonth.setBounds(119, 6, 61, 16);
			contentPanel.add(lblMonth);
		
			JLabel lblYear = new JLabel("Year");
			lblYear.setBounds(255, 6, 61, 16);
			contentPanel.add(lblYear);
		
			daybox = new JComboBox<>();
			daybox.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "", ""}));
			daybox.setMaximumRowCount(31);
			daybox.setBounds(37, 2, 70, 27);
			contentPanel.add(daybox);
		
			monthBox = new JComboBox<>();
			monthBox.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
			monthBox.setBounds(163, 2, 80, 27);
			contentPanel.add(monthBox);
	
			yearBox = new JComboBox<>();
			yearBox.setModel(new DefaultComboBoxModel<>(new String[] {"2019", "2020", "2021", "2022"}));
			yearBox.setBounds(292, 2, 107, 27);
			contentPanel.add(yearBox);
		
			JLabel lblDescription = new JLabel("Description");
			lblDescription.setBounds(6, 43, 80, 16);
			contentPanel.add(lblDescription);
		
			textField = new JTextField();
			textField.setBounds(98, 41, 346, 26);
			contentPanel.add(textField);
			textField.setColumns(10);
		
			JLabel lblContact = new JLabel("Contact");
			lblContact.setBounds(6, 88, 61, 16);
			contentPanel.add(lblContact);
		
		
			ContactBox = new JComboBox<>();
			ContactBox.setBounds(98, 84, 346, 27);
			contentPanel.add(ContactBox);
		
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int day = Integer.parseInt((String) daybox.getSelectedItem());
						int month = Integer.parseInt((String) monthBox.getSelectedItem());
						int year = Integer.parseInt((String) yearBox.getSelectedItem());
						Date fecha = new Date(day,month,year);
						String Description  = textField.getText();
						Contacto c = (Contacto) ContactBox.getSelectedItem();
						String contacto = c.getNombreCompleto();
						
						activeUser.deleteEvent(event);
						activeUser.addEvent(fecha, Description, contacto);
						close();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						close();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			
		
		
		this.startView(event,activeUser);
	}

	protected void close() {
		this.setVisible(false);
	}

	private void startView(Evento event,Usuario activeUser) {
		Date Fecha = event.getFecha();
		daybox.setSelectedIndex(Fecha.getDay()-1);
		monthBox.setSelectedIndex(Fecha.getMonth()-1);
		yearBox.setSelectedIndex(Fecha.getYear()%2019);
		this.textField.setText (event.getDescripcion());
		
		ContactBox.setModel(activeUser.getContactList());
		int idx = getContactIndex(event.getContactoRelacionado());
		ContactBox.setSelectedIndex(idx);
		
		
		
	}

	private int getContactIndex(String contactoRelacionado) {
			ComboBoxModel<Contacto> list = ContactBox.getModel();
					
			int i = 0;
			
			while(!list.getElementAt(i).getNombreCompleto().equals(contactoRelacionado)) {
				i++;
			}
			
			
			return i;
	}
	

}
