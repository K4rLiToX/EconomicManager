
package Vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class NewContactPopUp extends JDialog {
	
	private final JPanel contentPanel = new JPanel(); //Contenedor
	private JTextField NameTF;
	private JTextField IdentificatorTF;
	private JLabel lblDestination;
	private JButton okButton;
	private JButton cancelButton;
	private JTextField PhoneTF;
	private JTextField RelationTF;

	
	public NewContactPopUp() {
		setBounds(100, 100, 460, 263);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel NameTxt = new JLabel("Name");
		NameTxt.setBounds(24, 25, 61, 16);
		contentPanel.add(NameTxt);
		
		NameTF = new JTextField();
		NameTF.setBounds(107, 20, 294, 26);
		contentPanel.add(NameTF);
		NameTF.setColumns(10);
		
		JLabel Identificatortxt = new JLabel("Identificator");
		Identificatortxt.setBounds(24, 63, 78, 16);
		contentPanel.add(Identificatortxt);
		
		IdentificatorTF = new JTextField();
		IdentificatorTF.setBounds(107, 58, 294, 26);
		contentPanel.add(IdentificatorTF);
		IdentificatorTF.setColumns(10);
		
		lblDestination = new JLabel("");
		lblDestination.setBounds(24, 157, 78, 16);
		contentPanel.add(lblDestination);
		
		JLabel Phone = new JLabel("Phone");
		Phone.setBounds(24, 101, 78, 16);
		contentPanel.add(Phone);
		
		JLabel lblRelationship = new JLabel("Relationship");
		lblRelationship.setBounds(24, 142, 78, 16);
		contentPanel.add(lblRelationship);
		
		PhoneTF = new JTextField();
		PhoneTF.setColumns(10);
		PhoneTF.setBounds(107, 96, 294, 26);
		contentPanel.add(PhoneTF);
		
		RelationTF = new JTextField();
		RelationTF.setColumns(10);
		RelationTF.setBounds(107, 137, 294, 26);
		contentPanel.add(RelationTF);
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
		
	}
	
	public String getID() {
		return IdentificatorTF.getText();
	}
	
	public String getName() {
		return NameTF.getText();
	}
	
	public String getPhone() {
		return PhoneTF.getText();
	}
	
	public String getRelation() {
		return RelationTF.getText();
	}
	
	public void Clean() {
		NameTF.setText("");
		IdentificatorTF.setText("");
		PhoneTF.setText("");
		RelationTF.setText("");
	}
	
}
