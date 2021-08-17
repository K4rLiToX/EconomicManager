package Vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ED.Date;
import Principal.Contacto;
import Principal.Usuario;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class NewEventPopUp extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField DescriptionTF;
	private JComboBox<String> DayBox;
	private JComboBox<String> YearBox;
	private JComboBox<String> MonthBox;
	private JComboBox<Contacto> ContactBox;


	
	public NewEventPopUp(Usuario activeUser) {
		setBounds(100, 100, 397, 187);
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
		lblYear.setBounds(257, 6, 61, 16);
		contentPanel.add(lblYear);
		
		DayBox = new JComboBox<>();
		DayBox.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "", ""}));
		DayBox.setMaximumRowCount(31);
		DayBox.setBounds(40, 2, 67, 27);
		contentPanel.add(DayBox);
		
		MonthBox = new JComboBox<>();
		MonthBox.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		MonthBox.setMaximumRowCount(12);
		MonthBox.setBounds(168, 2, 84, 27);
		contentPanel.add(MonthBox);
		
		YearBox = new JComboBox<>();
		YearBox.setModel(new DefaultComboBoxModel<String>(new String[] {"2019", "2020", "2021", "2022"}));
		YearBox.setBounds(294, 2, 84, 27);
		contentPanel.add(YearBox);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(6, 52, 101, 16);
		contentPanel.add(lblDescription);
		
		DescriptionTF = new JTextField();
		DescriptionTF.setBounds(112, 47, 279, 26);
		contentPanel.add(DescriptionTF);
		DescriptionTF.setColumns(10);
		
		JLabel lblContactWith = new JLabel("Contact  With ");
		lblContactWith.setBounds(6, 80, 101, 16);
		contentPanel.add(lblContactWith);
		
		ContactBox = new JComboBox<>();
		ContactBox.setModel(activeUser.getContactList());
		ContactBox.setBounds(112, 80, 279, 27);
		contentPanel.add(ContactBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int day = Integer.parseInt((String) DayBox.getSelectedItem());
						int month = Integer.parseInt((String) MonthBox.getSelectedItem());
						int year = Integer.parseInt((String) YearBox.getSelectedItem());
						Date fecha = new Date(day,month,year);
						String Description  = DescriptionTF.getText();
						Contacto c = (Contacto) ContactBox.getSelectedItem();
						String contacto = c.getNombreCompleto();
						
						activeUser.addEvent(fecha, Description, contacto);
						close();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						close();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}



	protected void close() {
		DescriptionTF.setText("");
		this.setVisible(false);		
	}
}
