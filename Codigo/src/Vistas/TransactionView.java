/**
 * @Author Pedro
 */

package Vistas;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Principal.Movimiento;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class TransactionView extends JPanel {
	//Components - Version ≤ 1.2
	private JList<Movimiento> MovementsList;	//Lista de Movimientos
	private JButton btnNewTransactions;			//Insertar Nuevo Movimiento
	private JButton btnDeleteTransaction;		//Borrar Movimiento
	private JButton EditButton;					//Editar un Movimiento
	private JButton ViewButton;					//Ver detalles de un movimiento
	
	private JLabel statisticsLabel;				//etiqueta
	private JButton btnExportTable;				//Boton para exportar los Movimientos a tabla en PDF
	private JButton btnExportGraphics;			//Boton parra exportar los Movimientos a grafica en PDF
	private JLabel FilterTransactionsLabel;		//etiqueta
	private JButton btnGenerateBill;			//Boton para Generar Factura en PDF
	private JComboBox<String> MainFilterCB;		//Filtro Principal
	private JTextField FilterTextTF;			//Detalles del Filtro
	private JButton Filterbtn;					//Boton de aplicar Filtro
	private JButton btnDisplayLastMonths;       //Boton Mostrar gráfica resumen de los ultimos meses
	private JButton btnDisplaySummary;			//Boton Mostrar Estadisticas Descriptivas
	private JButton btnDisplayAdvanced;
	
	public TransactionView() {
		//Version ≤ 1.2
		
		//Ajustes de la ventana
		this.setFocusTraversalKeysEnabled(false);
		this.setFocusable(false);
		this.setBackground(SystemColor.window);
		this.setLayout(null);
		
		//Lista de Movimientos
		MovementsList = new JList<>();
		MovementsList.setVisibleRowCount(300);
		MovementsList.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Transactions", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		MovementsList.setBounds(22, 114, 410, 453);
		this.add(MovementsList);
		
		//Boton Añadir Transaccion
		btnNewTransactions = new JButton("New Transaction");
		btnNewTransactions.setBounds(460, 34, 156, 29);
		this.add(btnNewTransactions);
		
		//Boton Borrar Transaccion
		btnDeleteTransaction = new JButton("Delete Transaction");
		btnDeleteTransaction.setBounds(460, 73, 156, 29);
		this.add(btnDeleteTransaction);
		
		//Boton Editar Transaccion
		EditButton = new JButton("Edit Transaction");
		EditButton.setBounds(628, 73, 195, 29);
		this.add(EditButton);
		
		//boton Visualizar Transaccion
		ViewButton = new JButton("View Transaction Details");
		ViewButton.setBounds(628, 34, 195, 29);
		this.add(ViewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(460, 234, 363, 12);
		add(separator);
		
		statisticsLabel = new JLabel("Statistics");
		statisticsLabel.setBounds(595, 251, 61, 16);
		add(statisticsLabel);
		
		btnExportTable = new JButton("Export Filtered Transactions as a Table");
		btnExportTable.setBounds(460, 114, 353, 26);
		add(btnExportTable);
		
		btnExportGraphics = new JButton("Export Filtered Transactions as a Graphic ");
		btnExportGraphics.setBounds(460, 152, 353, 29);
		add(btnExportGraphics);
		
		FilterTransactionsLabel = new JLabel("Filter Transactions");
		FilterTransactionsLabel.setBounds(22, 39, 124, 16);
		add(FilterTransactionsLabel);
		
		
		btnGenerateBill = new JButton("Generate Bill for the selected Movement");
		btnGenerateBill.setBounds(460, 193, 353, 29);
		add(btnGenerateBill);
		
		btnDisplayLastMonths = new JButton("Display Last Months Balance");
		btnDisplayLastMonths.setBounds(460, 279, 363, 29);
		add(btnDisplayLastMonths);
		
		btnDisplaySummary = new JButton("Display Transactions Summary");
		btnDisplaySummary.setBounds(460, 320, 363, 29);
		add(btnDisplaySummary);
		
		btnDisplayAdvanced = new JButton("Display Advance Statistics");
		btnDisplayAdvanced.setBounds(460, 361, 363, 29);
		add(btnDisplayAdvanced);
		
		MainFilterCB = new JComboBox<String>();
		MainFilterCB.setModel(new DefaultComboBoxModel<String>(new String[] {"Show Everything", "Date", "Description", "Related Contact", "Category"}));
		MainFilterCB.setMaximumRowCount(5);
		MainFilterCB.setBounds(158, 35, 274, 27);
		add(MainFilterCB);
		
		FilterTextTF = new JTextField();
		FilterTextTF.setBounds(22, 73, 257, 26);
		add(FilterTextTF);
		FilterTextTF.setColumns(10);
		
		Filterbtn = new JButton("Enable Filter");
		Filterbtn.setBounds(315, 73, 117, 29);
		add(Filterbtn);
	}

	public void RegistrarControladorMovimientos(ActionListener ctr) {	
		//Version ≤ 1.2
		
		btnNewTransactions.addActionListener(ctr);
		btnNewTransactions.setActionCommand("New Transaction");
		
		btnDeleteTransaction.addActionListener(ctr);
		btnDeleteTransaction.setActionCommand("Delete Transaction");
		
		EditButton.addActionListener(ctr);
		EditButton.setActionCommand("Edit Transaction");
		
		ViewButton.addActionListener(ctr);
		ViewButton.setActionCommand("View Transaction");
		
		Filterbtn.addActionListener(ctr);
		Filterbtn.setActionCommand("Go Filter");
		
		btnGenerateBill.addActionListener(ctr);
		btnGenerateBill.setActionCommand("Generate Bill");
		
		btnExportTable.addActionListener(ctr);
		btnExportTable.setActionCommand("Export Excel");
		
		btnExportGraphics.addActionListener(ctr);
		btnExportGraphics.setActionCommand("Export Graphics");
		
		btnDisplayLastMonths.addActionListener(ctr);
		btnDisplayLastMonths.setActionCommand("Display Last Months");
		
		btnDisplaySummary.addActionListener(ctr);
		btnDisplaySummary.setActionCommand("Display Descriptive Summary");
		
		btnDisplayAdvanced.addActionListener(ctr);
		btnDisplayAdvanced.setActionCommand("Display Inference Stats");
	}
	
	//Servicios - Version ≤ 1.2
	public void SetMovements(DefaultListModel<Movimiento> lista) {
		MovementsList.setModel(lista);
	}

	public Movimiento getSelectedMovement() {
		return MovementsList.getSelectedValue();
	}
	
	public String getMainFilter() {
		return (String) MainFilterCB.getSelectedItem();
	}
	
	public String getSubFilter() {
		return FilterTextTF.getText();
	}

	public void clearFilters() {
		FilterTextTF.setText("");
	}

	public Object[] getExcelInfo() {
		Object[] info = new Object[3];
		
		info[0] = MainFilterCB.getSelectedItem();
		info[1] = FilterTextTF.getText();
		info[2] = MovementsList.getModel();
		
		return info;
	}

}
