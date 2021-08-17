/**
 * @Author Pedro
 */

package Vistas;

import java.awt.Color;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Principal.Company;
import Principal.Usuario;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class UserView extends JPanel {
	private JLabel UserImage;		//Imagen Decorativa
	private JLabel UserNametxt;		//Campo para Mostrar Nombre de Usuario
	private JButton LogOutButton;	//Boton para Cerrar Sesion
	private JButton BorrarButton;	//Boton para Borrar Cuenta de Usuario
	private JButton btnChangeImage;
	private JLabel atLabel;
	private JTextField CNameTF;
	private JTextField CPhoneTF;
	private JLabel lblPhoneNumberfax;
	private JTextField CEMailTF;
	private JLabel lblEmail;
	private JLabel lblAddress;
	private JTextField CAddressTF;
	private JButton btnEditCompanyInformation;
	
	
	public UserView() {
		setLayout(null);
		
		
		UserImage = new JLabel("");
		UserImage.setBackground(SystemColor.window);
		UserImage.setBounds(247, 51, 256, 256);
		this.add(UserImage);
		
		UserNametxt = new JLabel("");
		UserNametxt.setBackground(SystemColor.windowBorder);
		UserNametxt.setBounds(23, 356, 233, 29);
		this.add(UserNametxt);
		
		LogOutButton = new JButton("Log Out");
		LogOutButton.setBounds(325, 553, 92, 29);
		this.add(LogOutButton);
		
		BorrarButton = new JButton("Delete User Account");
		BorrarButton.setBounds(290, 594, 172, 29);
		this.add(BorrarButton);
		
		btnChangeImage = new JButton("Change Image");
		btnChangeImage.setBounds(515, 278, 133, 29);
		this.add(btnChangeImage);
		
		atLabel = new JLabel("At");
		atLabel.setBounds(177, 362, 61, 16);
		add(atLabel);
		
		CNameTF = new JTextField();
		CNameTF.setBorder(null);
		CNameTF.setEditable(false);
		CNameTF.setBackground(SystemColor.window);
		CNameTF.setBounds(208, 357, 570, 26);
		add(CNameTF);
		CNameTF.setColumns(10);
		
		CPhoneTF = new JTextField();
		CPhoneTF.setBorder(null);
		CPhoneTF.setBackground(SystemColor.window);
		CPhoneTF.setBounds(208, 397, 233, 26);
		add(CPhoneTF);
		CPhoneTF.setColumns(10);
		
		lblPhoneNumberfax = new JLabel("Phone Number/Fax");
		lblPhoneNumberfax.setBounds(23, 402, 149, 16);
		add(lblPhoneNumberfax);
		
		CEMailTF = new JTextField();
		CEMailTF.setBorder(null);
		CEMailTF.setBackground(SystemColor.window);
		CEMailTF.setBounds(208, 435, 322, 26);
		add(CEMailTF);
		CEMailTF.setColumns(10);
		
		lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(23, 440, 61, 16);
		add(lblEmail);
		
		lblAddress = new JLabel("Address");
		lblAddress.setBounds(23, 477, 61, 16);
		add(lblAddress);
		
		CAddressTF = new JTextField();
		CAddressTF.setBorder(null);
		CAddressTF.setBackground(SystemColor.window);
		CAddressTF.setBounds(208, 472, 570, 26);
		add(CAddressTF);
		CAddressTF.setColumns(10);
		
		btnEditCompanyInformation = new JButton("Edit Company Information");
		btnEditCompanyInformation.setBounds(23, 278, 212, 29);
		add(btnEditCompanyInformation);
	}
	
	public void RegistrarControladorUsuario(ActionListener ctr) {
		//Alfa
		LogOutButton.addActionListener(ctr);
		LogOutButton.setActionCommand("Cerrar Sesion");
		
		BorrarButton.addActionListener(ctr);
		BorrarButton.setActionCommand("Borrar Usuario");
		
		//Version 1.3
		btnChangeImage.addActionListener(ctr);
		btnChangeImage.setActionCommand("Change Image");
		
		//Undefined
		btnEditCompanyInformation.addActionListener(ctr);
		btnEditCompanyInformation.setActionCommand("Edit Info");
	}
	
	//Services - Version ≤ Updated Undefined
	public void SetView(Usuario activeUser) {
		UserNametxt.setText(activeUser.getUserName());
		CNameTF.setText(activeUser.getCompany().getName());
		CPhoneTF.setText(activeUser.getCompany().getPhoneNumber());
		CEMailTF.setText(activeUser.getCompany().getEmail());
		CAddressTF.setText(activeUser.getCompany().getAddress());		
	}
	
	//Services - Version 1.3
	public void SetUserImage(Usuario activeUser) {
		String name = activeUser.getUserName();
		
		File dataBaseImage = new File("BD/"+name+".jpg");
		
		if(dataBaseImage.exists()) {
			ImageIcon II = new ImageIcon(dataBaseImage.getPath());
			Image im = II.getImage();
			im = im.getScaledInstance(256, 256, Image.SCALE_SMOOTH);
			II.setImage(im);
			
			this.UserImage.setIcon(II);
		}else {
			UserImage.setIcon(new ImageIcon(UserView.class.getResource("/Imagenes/User.png")));	
		}
	}

	public void SetFieldsEditable() {
		CNameTF.setEditable(true);
		CNameTF.setBackground(Color.WHITE);
		CPhoneTF.setEditable(true);
		CPhoneTF.setBackground(Color.WHITE);
		CEMailTF.setEditable(true);
		CEMailTF.setBackground(Color.WHITE);
		CAddressTF.setEditable(true);
		CAddressTF.setBackground(Color.WHITE);
	
	}
	
	public void ReadAndSetFields(Usuario activeUser) {
		String Name = CNameTF.getText();
		String Phone = CPhoneTF.getText();
		String EMail = CEMailTF.getText();
		String Addres = CAddressTF.getText();
		
		Company c = activeUser.getCompany();
		c.setName(Name);
		c.setPhoneNumber(Phone);
		c.setEmail(EMail);
		c.setAddress(Addres);
	}


	public void setFieldsUnEditable() {
		CNameTF.setEditable(false);
		CNameTF.setBackground(SystemColor.window);
		CPhoneTF.setEditable(false);
		CPhoneTF.setBackground(SystemColor.window);
		CEMailTF.setEditable(false);
		CEMailTF.setBackground(SystemColor.window);
		CAddressTF.setEditable(false);
		CAddressTF.setBackground(SystemColor.window);		
	}
}
