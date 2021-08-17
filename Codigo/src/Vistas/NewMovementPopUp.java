/**
 * @Author Pedro
 */

package Vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ED.Date;
import Principal.Contacto;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class NewMovementPopUp extends JDialog {
	
	private final JPanel contentPanel = new JPanel(); //Contenedor
	private JTextField AmountTF;
	private JTextField DescriptionTF;
	private JComboBox<Contacto> DestinationTF;
	private JLabel lblDestination;
	private JButton okButton;
	private JButton cancelButton;
	private JRadioButton rdbtnIngreso;
	private JRadioButton rdbtnGasto;
	private JComboBox<Integer> DayBox;
	private JComboBox<Integer> MonthBox;
	private JComboBox<Integer> YearBox;
	private JLabel lblCategory;
	private JComboBox<String> CategoryCB;

	
	public NewMovementPopUp() {
		setBounds(100, 100, 534, 290);
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
		
		DayBox = new JComboBox<>();
		DayBox.setModel(new DefaultComboBoxModel<>(new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31}));
		DayBox.setMaximumRowCount(31);
		DayBox.setBounds(107, 73, 69, 27);
		contentPanel.add(DayBox);
		
		MonthBox = new JComboBox<>();
		MonthBox.setModel(new DefaultComboBoxModel<>(new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12}));
		MonthBox.setMaximumRowCount(12);
		MonthBox.setBounds(188, 73, 62, 27);
		contentPanel.add(MonthBox);
		
		YearBox = new JComboBox<>();
		YearBox.setModel(new DefaultComboBoxModel<>(new Integer[] {2019,2020,2021,2022}));
		YearBox.setMaximumRowCount(4);
		YearBox.setBounds(262, 73, 98, 27);
		contentPanel.add(YearBox);
		
		lblCategory = new JLabel("Category");
		lblCategory.setBounds(24, 194, 61, 16);
		contentPanel.add(lblCategory);
		
		CategoryCB = new JComboBox<String>();
		
		CategoryCB.setBounds(107, 190, 294, 27);
		contentPanel.add(CategoryCB);
		CategoryCB.setModel(new DefaultComboBoxModel<String>(new String[] {"Employee Salary", "Extraordinary", "Provider", "Supply", "Bill Invoice"}));
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

	public void registraControlador(ActionListener ctr) {
		okButton.addActionListener(ctr);
		okButton.setActionCommand("NewOk");
		
		cancelButton.addActionListener(ctr);
		cancelButton.setActionCommand("NewCancel");
		
		rdbtnIngreso.addActionListener(ctr);
		rdbtnIngreso.setActionCommand("Ingreso");
		
		rdbtnGasto.addActionListener(ctr);
		rdbtnGasto.setActionCommand("Gasto");
		
	}
	
	public Double getAmount() {
		if(rdbtnGasto.isSelected()) {
			return -1 * Double.parseDouble(AmountTF.getText());
		}else {
			return Double.parseDouble(AmountTF.getText());
		}
	}
	
	public Date getDate() {
		Date date = new Date(DayBox.getSelectedItem()+"/"+MonthBox.getSelectedItem()+"/"+YearBox.getSelectedItem());
		
		return date;
	}
	
	public String getDescription() {
		return DescriptionTF.getText();
	}
	
	public Contacto getDestination() {
		return (Contacto)DestinationTF.getSelectedItem();
	}
	
	public String getSign() {
		if(rdbtnGasto.isSelected()) {
			return "Gasto";
		}else {
			return "Ingreso";
		}
	}

	public void setSelected(String button) {
		if(button.equals("Ingreso")) {
			rdbtnIngreso.setSelected(true);
			rdbtnGasto.setSelected(false);
			lblDestination.setText("Procedencia");
		}else {
			rdbtnIngreso.setSelected(false);
			rdbtnGasto.setSelected(true);
			lblDestination.setText("Destination");
		}
	}
	
	public void Clean() {
		AmountTF.setText("");
		DescriptionTF.setText("");
	}

	public String getCategory() {
		return (String) CategoryCB.getSelectedItem();
	}

	public void setContactsModel(DefaultComboBoxModel<Contacto> ContactList) {
		ContactList.addElement(new Contacto("0", "Otro...", " ", " "));
		DestinationTF.setModel(ContactList);
	}
}
