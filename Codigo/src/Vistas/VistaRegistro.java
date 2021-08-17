/**
 * @Author Pedro
 */

package Vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.SystemColor;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class VistaRegistro extends JFrame {
	
	//Contenedor Externo
	private JPanel contentPane;
	
	//Campos Fijos - Alfa
	private JLabel txtTitulo;
	private JLabel txtUsuario;
	private JLabel lblIntroduzcaSuContrasea;
	
	//Campos Fijos - Version 1.3
	private JLabel lblChooseEnterpriseLogo;
	
	//Campos Editables - Alfa
	private JLabel ConsoleTF; //Consola para mostrar info
	private JPasswordField passwordField;
	private JTextField UsuarioTF;
	
	//Botones - Alfa
	private JButton RegistrarmeButton;
	private JButton BackButton;
	
	//Botones - Version 1.3
	private JButton SelectPathButton;
	private JLabel lblCompanyName;
	
	//Campos de Texto - Company Info
	private JTextField CNameTF;
	private JTextField CPhoneTF;
	private JTextField CEMailTF;
	private JTextField CAdressTF;

	//Constructor
	public VistaRegistro() {
		//Ajustes de Ventana
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 515);
		contentPane = new JPanel();
		contentPane.setForeground(SystemColor.controlHighlight);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Componentes - Version ≤ 1.2
		txtTitulo = new JLabel("Register Wizard");
		txtTitulo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtTitulo.setBounds(25, 30, 419, 26);
		contentPane.add(txtTitulo);
		
		txtUsuario = new JLabel("Insert Username");
		txtUsuario.setBounds(25, 108, 218, 16);
		contentPane.add(txtUsuario);
		
		lblIntroduzcaSuContrasea = new JLabel("Insert Password");
		lblIntroduzcaSuContrasea.setBounds(25, 136, 218, 16);
		contentPane.add(lblIntroduzcaSuContrasea);
		
		UsuarioTF = new JTextField();
		UsuarioTF.setBounds(255, 103, 189, 26);
		contentPane.add(UsuarioTF);
		UsuarioTF.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(255, 131, 189, 26);
		contentPane.add(passwordField);
		
		RegistrarmeButton = new JButton("Finish Register");
		RegistrarmeButton.setBounds(132, 392, 148, 29);
		contentPane.add(RegistrarmeButton);
		
		ConsoleTF = new JLabel();
		ConsoleTF.setForeground(Color.RED);
		ConsoleTF.setBorder(new CompoundBorder());
		ConsoleTF.setBackground(SystemColor.window);
		ConsoleTF.setBounds(111, 348, 210, 26);
		contentPane.add(ConsoleTF);
		
		BackButton = new JButton("");
		ImageIcon back = new ImageIcon(VistaRegistro.class.getResource("/Imagenes/back-icon.png"));
		Image backim = back.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		back.setImage(backim);
		BackButton.setIcon(back);
		BackButton.setBounds(25, 419, 83, 53);
		contentPane.add(BackButton);
		
		lblChooseEnterpriseLogo = new JLabel("Choose Company Logo");
		lblChooseEnterpriseLogo.setBounds(25, 320, 156, 16);
		contentPane.add(lblChooseEnterpriseLogo);
		
		SelectPathButton = new JButton("select");
		SelectPathButton.setBounds(255, 315, 117, 29);
		contentPane.add(SelectPathButton);
		
		lblCompanyName = new JLabel("Name");
		lblCompanyName.setBounds(25, 206, 156, 16);
		contentPane.add(lblCompanyName);
		
		CNameTF = new JTextField();
		CNameTF.setBounds(255, 201, 189, 26);
		contentPane.add(CNameTF);
		CNameTF.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(25, 68, 419, 12);
		contentPane.add(separator);
		
		JLabel lblAppInformation = new JLabel("App Information");
		lblAppInformation.setBounds(164, 80, 103, 16);
		contentPane.add(lblAppInformation);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(25, 164, 419, 12);
		contentPane.add(separator_1);
		
		JLabel lblYourCompanyInformation = new JLabel("Your Company Information");
		lblYourCompanyInformation.setBounds(132, 173, 189, 16);
		contentPane.add(lblYourCompanyInformation);
		
		JLabel lblPhoneNumberfax = new JLabel("Phone Number/Fax");
		lblPhoneNumberfax.setBounds(25, 234, 156, 16);
		contentPane.add(lblPhoneNumberfax);
		
		CPhoneTF = new JTextField();
		CPhoneTF.setBounds(255, 229, 189, 26);
		contentPane.add(CPhoneTF);
		CPhoneTF.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(25, 262, 61, 16);
		contentPane.add(lblEmail);
		
		CEMailTF = new JTextField();
		CEMailTF.setBounds(255, 257, 189, 26);
		contentPane.add(CEMailTF);
		CEMailTF.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(25, 290, 61, 16);
		contentPane.add(lblAddress);
		
		CAdressTF = new JTextField();
		CAdressTF.setBounds(255, 285, 189, 26);
		contentPane.add(CAdressTF);
		CAdressTF.setColumns(10);
		
	}
	
	//Registro del Controlador
	public void registrarControlador(ActionListener ctr) {
		//Alfa
		RegistrarmeButton.addActionListener(ctr);
		RegistrarmeButton.setActionCommand("Registro Terminado");
		
		BackButton.addActionListener(ctr);
		BackButton.setActionCommand("Volver LogIn");
		
		//Version 1.3
		SelectPathButton.addActionListener(ctr);
		SelectPathButton.setActionCommand("Open File Chooser");
	}

	//Servicios - Alfa
	public String getUsuario() {
		return UsuarioTF.getText();
	}
	
	@SuppressWarnings("deprecation")
	public String getPassword() {
		return passwordField.getText();
	}
	
	public void printSomething(String text) {
		ConsoleTF.setText(text);
	}

	public void limpiarVista() {
		UsuarioTF.setText("");
		passwordField.setText("");
		ConsoleTF.setText("");
		CNameTF.setText("");
		CPhoneTF.setText("");
		CEMailTF.setText("");
		CAdressTF.setText("");

	}

	
	//Servicios - Undefined
	public String getCName() {
		return CNameTF.getText();
	}

	public String getCPhone() {
		return CPhoneTF.getText();
	}

	public String getCEmail() {
		return CEMailTF.getText();
	}

	public String getCAddress() {
		return CAdressTF.getText();
	}
	
	
	
	
}
