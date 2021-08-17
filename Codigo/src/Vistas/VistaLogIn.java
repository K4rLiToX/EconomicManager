/**
 * @Author Pedro
 */

package Vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class VistaLogIn extends JFrame {
	//Contenedor Intermedio
	private JPanel contentPane;
	
	//Texto Fijo 
	private JLabel txtBienvenida;
	private JLabel txtUsuario;
	private JLabel txtContraseña;

	//Texto Editable
	private JTextField UsuarioTF;
	private JPasswordField passwordField;
	private JLabel ConsoleTF; //Salida para mostrar informacion
	
	//Botones
	private JButton LogInButton;
	private JButton RegistrarmeButton;

	//Constructor
	public VistaLogIn() {
		setForeground(SystemColor.window);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 377, 313);
		contentPane = new JPanel();
		contentPane.setForeground(SystemColor.controlHighlight);
		contentPane.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtBienvenida = new JLabel("Welcome to G7 - Financial Assistant");
		txtBienvenida.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 20));
		txtBienvenida.setBounds(6, 36, 367, 27);
		contentPane.add(txtBienvenida);
		
		txtUsuario = new JLabel("User");
		txtUsuario.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtUsuario.setBounds(41, 90, 61, 16);
		contentPane.add(txtUsuario);
		
		txtContraseña = new JLabel("Password");
		txtContraseña.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtContraseña.setBounds(41, 130, 100, 16);
		contentPane.add(txtContraseña);
		
		UsuarioTF = new JTextField();
		UsuarioTF.setBounds(145, 88, 130, 23);
		contentPane.add(UsuarioTF);
		UsuarioTF.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(145, 128, 130, 22);
		contentPane.add(passwordField);
		
		LogInButton = new JButton("Log in");
		LogInButton.setBounds(145, 162, 130, 29);
		contentPane.add(LogInButton);
		
		RegistrarmeButton = new JButton("Sign Up");
		RegistrarmeButton.setBounds(238, 244, 117, 29);
		contentPane.add(RegistrarmeButton);
		
		ConsoleTF = new JLabel();
		ConsoleTF.setForeground(SystemColor.controlHighlight);
		ConsoleTF.setBorder(null);
		ConsoleTF.setBackground(SystemColor.window);
		ConsoleTF.setBounds(41, 203, 312, 26);
		contentPane.add(ConsoleTF);
	}

	//Registro del Controlador
	public void RegistrarControladorUsuario(ActionListener ctr) {
		//Botón IniciarSesión 
		LogInButton.addActionListener(ctr);
		LogInButton.setActionCommand("Iniciar Sesion");
		
		//Boton Registrarme
		RegistrarmeButton.addActionListener(ctr);
		RegistrarmeButton.setActionCommand("Cambiar a Registro");
		
		passwordField.addActionListener(ctr);
		passwordField.setActionCommand("Iniciar Sesion");
	}

	//Servicios
	public String getUserText() {
		return UsuarioTF.getText();
	}
	
	@SuppressWarnings("deprecation")
	public String getPassword() {
		return passwordField.getText();
	}

	public void printSomething(String text) {
		ConsoleTF.setText(text);
	}
	
	public void LimpiarVista() {
		UsuarioTF.setText("");
		passwordField.setText("");
		ConsoleTF.setText("");
	}
	
	public void CleanPassword() {
		passwordField.setText("");
	}
	
	public void setColorNormal() {
		ConsoleTF.setForeground(Color.black);
	}

	public void setColorRed() {
		ConsoleTF.setForeground(Color.red);
	}
}
