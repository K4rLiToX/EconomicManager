/**
 * @Author Pedro
 */

package Vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Principal.Movimiento;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ViewMovementPopUp extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField AmountTF;
	private JTextField DateTF;
	private JTextField DescriptionTF;
	private JTextField ContactTF;
	private JLabel Typetxt;
	private JLabel Contactotxt;
	private JButton DoneButton;
	private JLabel lblCategory;
	private JTextField CategoryTF;

	public ViewMovementPopUp() {
		setBounds(100, 100, 450, 249);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		Typetxt = new JLabel("");
		Typetxt.setBounds(179, 6, 86, 16);
		contentPanel.add(Typetxt);
		
		JLabel lblCantidad = new JLabel("Amount");
		lblCantidad.setBounds(6, 58, 61, 16);
		contentPanel.add(lblCantidad);
		
		JLabel lblFecha = new JLabel("Date");
		lblFecha.setBounds(230, 58, 61, 16);
		contentPanel.add(lblFecha);
		
		JLabel lblDescripcin = new JLabel("Description");
		lblDescripcin.setBounds(6, 91, 75, 16);
		contentPanel.add(lblDescripcin);
		
		Contactotxt = new JLabel("Destino/Procedencia");
		Contactotxt.setBounds(6, 119, 75, 26);
		contentPanel.add(Contactotxt);
		
		AmountTF = new JTextField();
		AmountTF.setBorder(null);
		AmountTF.setEditable(false);
		AmountTF.setBackground(SystemColor.window);
		AmountTF.setBounds(88, 53, 130, 26);
		contentPanel.add(AmountTF);
		AmountTF.setColumns(10);
		
		DateTF = new JTextField();
		DateTF.setBackground(SystemColor.window);
		DateTF.setBorder(null);
		DateTF.setBounds(275, 53, 130, 26);
		contentPanel.add(DateTF);
		DateTF.setColumns(10);
		
		DescriptionTF = new JTextField();
		DescriptionTF.setBorder(null);
		DescriptionTF.setBackground(SystemColor.window);
		DescriptionTF.setBounds(89, 86, 315, 26);
		contentPanel.add(DescriptionTF);
		DescriptionTF.setColumns(10);
		
		ContactTF = new JTextField();
		ContactTF.setBackground(SystemColor.window);
		ContactTF.setBorder(null);
		ContactTF.setBounds(90, 119, 315, 26);
		contentPanel.add(ContactTF);
		ContactTF.setColumns(10);
		
		lblCategory = new JLabel("Category");
		lblCategory.setBounds(6, 157, 61, 16);
		contentPanel.add(lblCategory);
		
		CategoryTF = new JTextField();
		CategoryTF.setBorder(null);
		CategoryTF.setBackground(SystemColor.window);
		CategoryTF.setEditable(false);
		CategoryTF.setBounds(90, 152, 175, 26);
		contentPanel.add(CategoryTF);
		CategoryTF.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				DoneButton = new JButton("Done");
				DoneButton.setActionCommand("OK");
				buttonPane.add(DoneButton);
				getRootPane().setDefaultButton(DoneButton);
			}
		}
	}
	
	
	public void SetView(Movimiento m) {
		if(m.isEntry()) {
			Typetxt.setText("Entry");
			Contactotxt.setText("Origin");
		}else {
			Typetxt.setText("Expenditure");
			Contactotxt.setText("Destination");
		}
		
		AmountTF.setText(""+Math.abs(m.getCantidad()));
		DateTF.setText(m.getFecha().toString());
		DescriptionTF.setText(m.getConcepto());
		ContactTF.setText(m.getContacto());
		CategoryTF.setText(m.getCategory());
		
		
	}
	
	public void registrarControlador(ActionListener ctr) {
		DoneButton.addActionListener(ctr);
		DoneButton.setActionCommand("Done");
	}
}
