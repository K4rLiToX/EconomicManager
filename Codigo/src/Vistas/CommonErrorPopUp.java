package Vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CommonErrorPopUp extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField ErrorTF;

	
	public CommonErrorPopUp(String Text) {
		setBounds(100, 100, 450, 170);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			ErrorTF = new JTextField();
			ErrorTF.setEditable(false);
			ErrorTF.setText(Text);
			ErrorTF.setBorder(null);
			ErrorTF.setBackground(SystemColor.window);
			ErrorTF.setBounds(6, 42, 438, 40);
			contentPanel.add(ErrorTF);
			ErrorTF.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cerrarVista();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}


	protected void cerrarVista() {
		this.setVisible(false);
	}

}
