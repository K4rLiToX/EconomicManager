package Vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Principal.Movimiento;
import Principal.Usuario;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;

import javax.swing.JList;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JSeparator;
import java.awt.Choice;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import java.awt.Font;

@SuppressWarnings("serial")
public class VistaAcciones extends JFrame {
	//Contenedor Exterior
	private JTabbedPane contentPane;
	
	//Pestaña Usuario - Alfa
	private JPanel UserPane;		//Contenedor
	private JLabel UserImage;		//Imagen Decorativa
	private JLabel UserNametxt;		//Campo para Mostrar Nombre de Usuario
	private JButton LogOutButton;	//Boton para Cerrar Sesion
	private JButton BorrarButton;	//Boton para Borrar Cuenta de Usuario
	
	
	//Pestaña Movimientos - Alfa
	private JPanel MovementsPane;	//Contenedor
	private JList<Movimiento> MovementsList;	//Lista de Movimientos
	private JButton btnNewTransactions;			//Insertar Nuevo Movimiento
	private JButton btnDeleteTransaction;		//Borrar Movimiento
	private JButton EditButton;
	private JButton ViewButton;
	private JLabel lblBalanceMensual;
	private JTextField BalanceTF;
	
	//Pesta�a Calendario - Beta
	private CalendarView CalendarPane;
	
	
	
	
	public VistaAcciones() {
		//Alfa
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 700);
		
		contentPane = new JTabbedPane();
		setContentPane(contentPane);
		
		//Usuario
		UserPane = new JPanel();
		
		contentPane.addTab("User", UserPane);
		UserPane.setLayout(null);
		
		UserImage = new JLabel("");
		UserImage.setBounds(286, 5, 256, 256);
		UserImage.setIcon(new ImageIcon(VistaAcciones.class.getResource("/Imagenes/User.png")));
		UserPane.add(UserImage);
		
		UserNametxt = new JLabel("");
		UserNametxt.setBackground(SystemColor.windowBorder);
		UserNametxt.setBounds(286, 295, 256, 16);
		UserPane.add(UserNametxt);
		
		LogOutButton = new JButton("Log out");
		LogOutButton.setBounds(349, 347, 117, 29);
		UserPane.add(LogOutButton);
		
		BorrarButton = new JButton("Delete User Account");
		BorrarButton.setBounds(309, 397, 192, 29);
		UserPane.add(BorrarButton);
		
		
		
		//Movimientos
		MovementsPane = new JPanel();
		MovementsPane.setFocusTraversalKeysEnabled(false);
		MovementsPane.setFocusable(false);
		MovementsPane.setBackground(SystemColor.window);
		contentPane.addTab("Transactions", MovementsPane);
		MovementsPane.setLayout(null);
		
		MovementsList = new JList<>();
		MovementsList.setVisibleRowCount(300);
		MovementsList.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Transactions", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		MovementsList.setBounds(22, 34, 410, 567);
		MovementsPane.add(MovementsList);
		
		btnNewTransactions = new JButton("New Transaction");
		btnNewTransactions.setBounds(460, 34, 156, 29);
		MovementsPane.add(btnNewTransactions);
		
		btnDeleteTransaction = new JButton("Delete Transaction");
		btnDeleteTransaction.setBounds(460, 73, 156, 29);
		MovementsPane.add(btnDeleteTransaction);
		
		EditButton = new JButton("Edit Transaction");
		EditButton.setBounds(460, 114, 156, 29);
		MovementsPane.add(EditButton);
		
		lblBalanceMensual = new JLabel("Monthly Account Balance");
		lblBalanceMensual.setForeground(Color.BLACK);
		lblBalanceMensual.setBounds(470, 165, 158, 16);
		MovementsPane.add(lblBalanceMensual);
		
		BalanceTF = new JTextField();
		BalanceTF.setBorder(null);
		BalanceTF.setBackground(SystemColor.window);
		BalanceTF.setEditable(false);
		BalanceTF.setBounds(693, 161, 130, 26);
		MovementsPane.add(BalanceTF);
		BalanceTF.setColumns(10);
		
		ViewButton = new JButton("View Transaction Details");
		ViewButton.setBounds(628, 34, 195, 29);
		MovementsPane.add(ViewButton);
		
		//Calendario
		CalendarPanel = new JPanel();
		CalendarPanel.setBackground(new Color(240, 240, 240));
		contentPane.addTab("Calendar", CalendarPanel);
		CalendarPanel.setLayout(null);
		
		JList list = new JList();
		list.setBounds(21, 21, 797, 257);
		CalendarPanel.add(list);
		
		JButton btnAddEvent = new JButton("Delete Event\r\n");
		btnAddEvent.setBounds(563, 342, 216, 35);
		CalendarPanel.add(btnAddEvent);
		
		textField = new JTextField();
		textField.setBounds(21, 343, 186, 32);
		CalendarPanel.add(textField);
		textField.setColumns(10);
		
		Choice choice = new Choice();
		choice.setBounds(21, 434, 72, 32);
		CalendarPanel.add(choice);
		
		Choice choice_1 = new Choice();
		choice_1.setBounds(135, 434, 72, 32);
		CalendarPanel.add(choice_1);
		
		Choice choice_2 = new Choice();
		choice_2.setBounds(244, 434, 72, 32);
		CalendarPanel.add(choice_2);
		
		JTextArea txtrDay = new JTextArea();
		txtrDay.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtrDay.setBackground(SystemColor.menu);
		txtrDay.setText("Day");
		txtrDay.setEditable(false);
		txtrDay.setBounds(21, 396, 72, 22);
		CalendarPanel.add(txtrDay);
		
		JTextArea txtrMonth = new JTextArea();
		txtrMonth.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtrMonth.setBackground(SystemColor.menu);
		txtrMonth.setText("Month");
		txtrMonth.setEditable(false);
		txtrMonth.setBounds(135, 396, 72, 22);
		CalendarPanel.add(txtrMonth);
		
		JTextArea txtrYear = new JTextArea();
		txtrYear.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtrYear.setBackground(SystemColor.menu);
		txtrYear.setText("Year");
		txtrYear.setEditable(false);
		txtrYear.setBounds(244, 396, 72, 22);
		CalendarPanel.add(txtrYear);
		
		JTextArea txtrIntroduceEvent = new JTextArea();
		txtrIntroduceEvent.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtrIntroduceEvent.setTabSize(10);
		txtrIntroduceEvent.setText("Introduce Event");
		txtrIntroduceEvent.setBackground(SystemColor.menu);
		txtrIntroduceEvent.setEditable(false);
		txtrIntroduceEvent.setBounds(21, 310, 178, 27);
		CalendarPanel.add(txtrIntroduceEvent);
		
		JRadioButton rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setBounds(21, 540, 81, 35);
		CalendarPanel.add(rdbtnYes);
		
		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBounds(135, 540, 81, 35);
		CalendarPanel.add(rdbtnNo);
		
		JTextArea txtrDoYouWant = new JTextArea();
		txtrDoYouWant.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtrDoYouWant.setText("Do you want to be warned?");
		txtrDoYouWant.setTabSize(10);
		txtrDoYouWant.setEditable(false);
		txtrDoYouWant.setBackground(SystemColor.menu);
		txtrDoYouWant.setBounds(21, 496, 228, 27);
		CalendarPanel.add(txtrDoYouWant);
		
		JButton btnCreateNewEvent = new JButton("Create New Event");
		btnCreateNewEvent.setBounds(254, 540, 209, 35);
		CalendarPanel.add(btnCreateNewEvent);
		
		JButton btnModifyEvent = new JButton("Modify Event");
		btnModifyEvent.setBounds(563, 434, 216, 35);
		CalendarPanel.add(btnModifyEvent);
		
		
	}
	
	public void RegistrarControladorUsuario(ActionListener ctr) {
		//Alfa
		LogOutButton.addActionListener(ctr);
		LogOutButton.setActionCommand("Cerrar Sesion");
		
		BorrarButton.addActionListener(ctr);
		BorrarButton.setActionCommand("Borrar Usuario");
	}
	
	public void RegistrarControladorMovimientos(ActionListener ctr) {	
		btnNewTransactions.addActionListener(ctr);
		btnNewTransactions.setActionCommand("New Transaction");
		
		btnDeleteTransaction.addActionListener(ctr);
		btnDeleteTransaction.setActionCommand("Delete Transaction");
		
		EditButton.addActionListener(ctr);
		EditButton.setActionCommand("Edit Transaction");
		
		ViewButton.addActionListener(ctr);
		ViewButton.setActionCommand("View Transaction");
	}
	
	
	
	//Servicios - Usuario - Alfa
	public void SetUserId(String name) {
		UserNametxt.setText(name);
	}
	
	//Servicios - Movimientos - Alfa
	public void SetMovements(DefaultListModel<Movimiento> lista) {
		MovementsList.setModel(lista);
	}

	public Movimiento getSelectedMovement() {
		return MovementsList.getSelectedValue();
	}
	
	public void setBalance(Usuario activeUser) {
		double balance = activeUser.getMonthlyBalance();
		
		if(balance >= 0) {
			BalanceTF.setForeground(Color.GREEN);
		}else {
			BalanceTF.setForeground(Color.RED);
		}
		
		BalanceTF.setText(""+balance);

	}
	}
