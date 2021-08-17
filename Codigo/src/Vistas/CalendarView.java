package Vistas;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;


import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Herramientas.CalendarGestor;
import Principal.Evento;
import Principal.Usuario;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CalendarView extends JPanel {
	//View Fields
	private JPanel CalendarPanel;
	private JTextField txtNombre;
	private JTable DaysTable;
	private JTextField scndFilter;
	
	
	//ActiveUser
	Usuario activeUser;
	private JList<Evento> eventList;
	private JComboBox<String> comboBox;
	
	public CalendarView() {
		setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setBorder(null);
		txtNombre.setEditable(false);
		txtNombre.setBackground(SystemColor.window);
		txtNombre.setBounds(339, 6, 153, 26);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JButton prevMonth = new JButton("<<");
		prevMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalendarGestor.previousMonthLabel(txtNombre);
			}
		});
		prevMonth.setBounds(281, 6, 46, 29);
		add(prevMonth);
		
		JButton NextMonth = new JButton(">>");
		NextMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalendarGestor.nextMonthLabel(txtNombre);
			}
		});
		NextMonth.setBounds(504, 6, 46, 29);
		add(NextMonth);
		
		DaysTable = new JTable();
		DaysTable.setToolTipText("Press a Cell to View The events Related to it");
		DaysTable.setBackground(SystemColor.window);
		DaysTable.setCellSelectionEnabled(true);
		DaysTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DaysTable.setBorder(null);
		DaysTable.setBounds(6, 47, 841, 208);
		setTableModel();
		add(DaysTable);
		
		eventList = new JList<>();
		eventList.setBorder(new TitledBorder(null, "Selected Day Events", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		eventList.setBounds(135, 310, 415, 208);
		add(eventList);
		
		JButton btnAddEvent = new JButton("Add Event");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewEventPopUp nepu = new NewEventPopUp(activeUser);
				nepu.setLocationRelativeTo(getThis());
				nepu.setVisible(true);
				
			}
		});
		btnAddEvent.setBounds(6, 310, 117, 29);
		add(btnAddEvent);
		
		JButton btnDeleteEvent = new JButton("Delete Event");
		btnDeleteEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Evento ev =(Evento) eventList.getSelectedValue();
				
				borrar(ev);
			}
		});
		btnDeleteEvent.setBounds(6, 351, 117, 29);
		add(btnDeleteEvent);
		
		JButton btnEditEvent = new JButton("Edit Event");
		btnEditEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditEventPopUp evpu = new EditEventPopUp(eventList.getSelectedValue(),activeUser);
				evpu.setLocationRelativeTo(getThis());
				evpu.setVisible(true);
				
			}
		});
		btnEditEvent.setBounds(6, 389, 117, 29);
		add(btnEditEvent);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(608, 304, 61, 16);
		add(lblSearch);
		
		scndFilter = new JTextField();
		scndFilter.setBounds(608, 364, 239, 26);
		add(scndFilter);
		scndFilter.setColumns(10);
		
		comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Show Everything", "Date", "Description", "Related Contact"}));
		comboBox.setBounds(608, 324, 239, 27);
		add(comboBox);
		
		JButton btnGo = new JButton("GO");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mainFilter = (String) comboBox.getSelectedItem();
				String subFilter = scndFilter.getText();
				eventList.setModel(activeUser.filterEventsby(mainFilter,subFilter));
				comboBox.setSelectedIndex(0);
				scndFilter.setText("");
			}
		});
		btnGo.setBounds(608, 404, 117, 29);
		add(btnGo);
		CalendarPanel = new JPanel();
		CalendarPanel.setBackground(new Color(240, 240, 240));
		CalendarPanel.setLayout(null);
		
		
				
	}

	protected void borrar(Evento ev) {
		activeUser.deleteEvent(ev);
		this.setEventList(activeUser.getEventsByDate(CalendarGestor.getFecha()));
	}

	protected Component getThis() {
		return this;
	}

	public void startView(Usuario activeUser) {
		this.activeUser = activeUser;
		
		CalendarGestor gc = new CalendarGestor(activeUser,this);
		DaysTable.addMouseListener(gc);
		
		gc.setName(txtNombre);
		
		
	}

	private void setTableModel() {
		TableModel m = new DefaultTableModel(5,7);
		DaysTable.setModel(m);
		DaysTable.setGridColor(Color.GRAY);
		DaysTable.setBorder(BorderFactory.createLineBorder(Color.black));
		DaysTable.setRowHeight(42);
		
		
		int k = 1;
		
		for(int i = 0;i<5 && k<32;i++) {
			for(int j = 0;j<7 && k<32;j++) {
				m.setValueAt(k++, i, j);
			}
		}
		
	}

	public JTable getTable() {
		return DaysTable;
	}
	
	public String getNombre() {
		return txtNombre.getText();
	}

	public void setEventList(DefaultListModel<Evento> list) {
		eventList.setModel(list);
	}

		
}
