package Vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class DeleteAccountPopUp extends JDialog {

	private final JPanel contentPanel = new JPanel();	//Contenedor
	private JLabel lblNewLabel;
	private JButton okButton;
	private JButton cancelButton;

	
	public DeleteAccountPopUp() {
		//Ajustes de ventana
		this.pack();
		setBounds(100, 100, 643, 190);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblNewLabel = new JLabel("You're about to delete your account Forever, Please Confirm your decission");
			lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblNewLabel.setBounds(6, 26, 631, 60);
			contentPanel.add(lblNewLabel);
		}
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
	
	
	public void registrarControlador(ActionListener ctr) {
		okButton.addActionListener(ctr);
		okButton.setActionCommand("OK");
		
		cancelButton.addActionListener(ctr);
		cancelButton.setActionCommand("Cancel");
	}
}
