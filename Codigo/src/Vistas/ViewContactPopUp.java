
package Vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ViewContactPopUp extends JDialog {
	
	private final JPanel contentPanel = new JPanel(); //Contenedor
	private JButton okButton;
	
	private JLabel NameLabel;
	private JLabel IDLabel;
	private JLabel PhoneLabel;
	private JLabel RelationLabel;

	
	public ViewContactPopUp() {
		setBounds(100, 100, 460, 263);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel NameTxt = new JLabel("Name");
		NameTxt.setBounds(24, 25, 61, 16);
		contentPanel.add(NameTxt);
		
		JLabel Identificatortxt = new JLabel("Identificator");
		Identificatortxt.setBounds(24, 63, 78, 16);
		contentPanel.add(Identificatortxt);
		
		JLabel Phone = new JLabel("Phone");
		Phone.setBounds(24, 101, 78, 16);
		contentPanel.add(Phone);
		
		JLabel lblRelationship = new JLabel("Relationship");
		lblRelationship.setBounds(24, 142, 78, 16);
		contentPanel.add(lblRelationship);
		
		NameLabel = new JLabel("");
		NameLabel.setBounds(137, 25, 297, 16);
		contentPanel.add(NameLabel);
		
		IDLabel = new JLabel("");
		IDLabel.setBounds(137, 63, 297, 16);
		contentPanel.add(IDLabel);
		
		PhoneLabel = new JLabel("");
		PhoneLabel.setBounds(137, 101, 297, 16);
		contentPanel.add(PhoneLabel);
		
		RelationLabel = new JLabel("");
		RelationLabel.setBounds(137, 142, 297, 16);
		
		contentPanel.add(RelationLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Close");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	public void registraControlador(ActionListener ctr) {
		okButton.addActionListener(ctr);
		okButton.setActionCommand("Close");
		
	}
	
	public void setID(String t) {
		IDLabel.setText(t);
	}
	
	public void setName(String t) {
		NameLabel.setText(t);
	}
	
	public void setPhone(String t) {
		PhoneLabel.setText(t);;
	}
	
	public void setRelation(String t) {
		RelationLabel.setText(t);
	}

}
