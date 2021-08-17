/**
 * @Author Pedro
 */

package Vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ED.Date;
import Principal.Contacto;
import Principal.Movimiento;
import Principal.Usuario;

@SuppressWarnings("serial")
public class EditMovementPopUp extends JDialog {
	
	//View Components
	private final JPanel contentPanel = new JPanel();
	private JTextField AmountTF;
	private JTextField DescriptionTF;
	private JComboBox<Contacto> DestinationTF;
	private JComboBox<Integer> DayCB;
	private JComboBox<Integer> MonthCB;
	private JLabel lblDestination;
	private JComboBox<Integer> YearCB;
	private JButton okButton;
	private JButton cancelButton;
	private JRadioButton rdbtnIngreso;
	private JRadioButton rdbtnGasto;
	private JLabel CategoryLabel;
	private JComboBox<String> Category;
	
	//Auxiliary Attributes
	Movimiento selected;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EditMovementPopUp() {
		setBounds(100, 100, 452, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel AmountTxt = new JLabel("Amount");
		AmountTxt.setBounds(24, 25, 61, 16);
		contentPanel.add(AmountTxt);
		
		AmountTF = new JTextField();
		AmountTF.setBounds(107, 20, 130, 26);
		contentPanel.add(AmountTF);
		AmountTF.setColumns(10);
		
		JLabel Datetxt = new JLabel("Date");
		Datetxt.setBounds(24, 77, 61, 16);
		contentPanel.add(Datetxt);
		
		DayCB = new JComboBox<Integer>();
		DayCB.setMaximumRowCount(31);
		DayCB.setBounds(107, 73, 73, 27);
		DayCB.setModel(new DefaultComboBoxModel<>(new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,25,27,28,29,30,31}));
		contentPanel.add(DayCB);
		
		MonthCB = new JComboBox<Integer>();
		MonthCB.setModel(new DefaultComboBoxModel(new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12}));
		MonthCB.setMaximumRowCount(12);
		MonthCB.setBounds(182, 73, 68, 27);
		contentPanel.add(MonthCB);
		
		YearCB = new JComboBox<Integer>();
		YearCB.setModel(new DefaultComboBoxModel(new Integer[] {2019,2020,2021,2022}));
		YearCB.setMaximumRowCount(12);
		YearCB.setBounds(262, 73, 93, 27);
		contentPanel.add(YearCB);
		
		JLabel Descriptiontxt = new JLabel("Description");
		Descriptiontxt.setBounds(24, 114, 78, 16);
		contentPanel.add(Descriptiontxt);
		
		DescriptionTF = new JTextField();
		DescriptionTF.setBounds(107, 112, 294, 26);
		contentPanel.add(DescriptionTF);
		DescriptionTF.setColumns(10);
		
		lblDestination = new JLabel("");
		lblDestination.setBounds(24, 157, 78, 16);
		contentPanel.add(lblDestination);
		
		DestinationTF = new JComboBox<Contacto>();
		DestinationTF.setBackground(SystemColor.text);
		DestinationTF.setBounds(107, 152, 294, 26);
		contentPanel.add(DestinationTF);
		
		rdbtnIngreso = new JRadioButton("Ingreso");
		rdbtnIngreso.setBounds(262, 21, 141, 23);
		contentPanel.add(rdbtnIngreso);
		
		rdbtnGasto = new JRadioButton("Gasto");
		rdbtnGasto.setBounds(262, 44, 141, 23);
		contentPanel.add(rdbtnGasto);
		
		CategoryLabel = new JLabel("Category");
		CategoryLabel.setBounds(24, 198, 61, 16);
		contentPanel.add(CategoryLabel);
		
		Category = new JComboBox();
		Category.setModel(new DefaultComboBoxModel<String>(new String[] {"Employee Salary", "Extraordinary", "Provider", "Supply", "Bill Invoice"}));
		Category.setBounds(107, 194, 294, 27);
		contentPanel.add(Category);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void StartView(Movimiento m,Usuario activeUser) {
		if(m.isEntry()) {
			rdbtnIngreso.setSelected(true);
			rdbtnIngreso.setEnabled(false);
			rdbtnGasto.setSelected(false);
			rdbtnGasto.setEnabled(false);
			lblDestination.setText("Procedencia");
		}else {
			rdbtnIngreso.setSelected(false);
			rdbtnIngreso.setEnabled(false);
			rdbtnGasto.setSelected(true);
			rdbtnGasto.setEnabled(false);
			lblDestination.setText("Destino");
		}
		selected = m;
		
		double amount = Math.abs(m.getCantidad());
		AmountTF.setText(""+amount);
		
		Date date  = m.getFecha();
		DayCB.setSelectedIndex(date.getDay()-1);
		MonthCB.setSelectedIndex(date.getMonth()-1);
		YearCB.setSelectedIndex(date.getYear()%2019);
		
		DescriptionTF.setText(m.getConcepto());
		
		DestinationTF.setModel(activeUser.getContactList());
		int idx = getContactIndex(m.getContacto());
		DestinationTF.setSelectedIndex(idx);
		
		idx = getCategoryIndex(m.getCategory());
		
		Category.setSelectedIndex(idx);
	}

	public void registrarControlador(ActionListener ctr) {
		okButton.addActionListener(ctr);
		okButton.setActionCommand("EditOk");
		
		cancelButton.addActionListener(ctr);
		cancelButton.setActionCommand("EditCancel");
	}

	public double getAmount() {
		if(rdbtnIngreso.isSelected()) {
			return Double.parseDouble(AmountTF.getText());
		}else {
			return -1 * Double.parseDouble(AmountTF.getText());
		}
	}
	
	public Date getDate() {
		return new Date(DayCB.getSelectedItem()+"/"+MonthCB.getSelectedItem()+"/"+YearCB.getSelectedItem());
	}
	
	public String getDescription() {
		return DescriptionTF.getText();
	}

	public String getDestination() {
		return DescriptionTF.getText();
	}
	
	public Integer getMovementID() {
		return selected.getIdentificador();
	}

	public String getCategory() {
		return (String) Category.getSelectedItem();
	}

	//Private methods
	private int getCategoryIndex(String category2) {
		int i = 0;
		
		switch(category2) {
			case "Employee Salary":
				i = 0;
			break;
			
			case "Extraordinary":
				i = 1;
			break;
			
			case "Provider":
				i = 2;
			break;
			
			case "Supply":
				i = 3;
			break;
			
			case "Bill Invoice":
				i = 4;
			break;
		}
		
		return i;
	}
	
	private int getContactIndex(String contacto) {
		ComboBoxModel<Contacto> list = DestinationTF.getModel();
				
		int i = 0;
		
		while(!list.getElementAt(i).getNombreCompleto().equals(contacto)) {
			i++;
		}
		
		
		return i;
	}
}
