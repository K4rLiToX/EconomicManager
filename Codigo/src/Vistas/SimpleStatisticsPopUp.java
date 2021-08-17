package Vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import Herramientas.SimpleStatCalculator;
import Principal.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class SimpleStatisticsPopUp extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField actualMonthBalanceTF;
	private JTextField actualYearBalanceTF;
	private JTextField MeanExpendituresTF;
	private JTextField MeanEntriesTF;
	private JPanel CategoryMeanPanel;

	
	public SimpleStatisticsPopUp(Usuario activeUser) {
		setBounds(100, 100, 701, 416);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblActualMonthBalance = new JLabel("Actual Month Balance");
			lblActualMonthBalance.setBounds(6, 18, 151, 16);
			contentPanel.add(lblActualMonthBalance);
		}
		{
			JLabel lblActualYearBalance = new JLabel("Actual Year Balance");
			lblActualYearBalance.setBounds(6, 46, 151, 16);
			contentPanel.add(lblActualYearBalance);
		}
		{
			JLabel lblMeanMonthExpenditures = new JLabel("Mean Month Expenditures");
			lblMeanMonthExpenditures.setBounds(6, 73, 169, 16);
			contentPanel.add(lblMeanMonthExpenditures);
		}
		{
			JLabel lblMeanMonthExpe = new JLabel("Mean Month Expenditures (Divided By Category): ");
			lblMeanMonthExpe.setBounds(6, 101, 318, 16);
			contentPanel.add(lblMeanMonthExpe);
		}
		{
			JLabel lblMeanMonthEntries = new JLabel("Mean Month Entries");
			lblMeanMonthEntries.setBounds(6, 318, 169, 16);
			contentPanel.add(lblMeanMonthEntries);
		}
		
		actualMonthBalanceTF = new JTextField();
		actualMonthBalanceTF.setEditable(false);
		actualMonthBalanceTF.setBorder(null);
		actualMonthBalanceTF.setBackground(SystemColor.window);
		actualMonthBalanceTF.setBounds(324, 13, 130, 26);
		contentPanel.add(actualMonthBalanceTF);
		actualMonthBalanceTF.setColumns(10);
		
		actualYearBalanceTF = new JTextField();
		actualYearBalanceTF.setBorder(null);
		actualYearBalanceTF.setBackground(SystemColor.window);
		actualYearBalanceTF.setEditable(false);
		actualYearBalanceTF.setBounds(324, 41, 130, 26);
		contentPanel.add(actualYearBalanceTF);
		actualYearBalanceTF.setColumns(10);
		
		MeanExpendituresTF = new JTextField();
		MeanExpendituresTF.setBorder(null);
		MeanExpendituresTF.setBackground(SystemColor.window);
		MeanExpendituresTF.setBounds(324, 68, 130, 26);
		contentPanel.add(MeanExpendituresTF);
		MeanExpendituresTF.setColumns(10);
		
		MeanEntriesTF = new JTextField();
		MeanEntriesTF.setBorder(null);
		MeanEntriesTF.setBackground(SystemColor.window);
		MeanEntriesTF.setBounds(324, 313, 130, 26);
		contentPanel.add(MeanEntriesTF);
		MeanEntriesTF.setColumns(10);
		
		JPanel panel = new JPanel();
		contentPanel.add(panel);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
	
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		this.SetView(activeUser);
	}

	private void SetView(Usuario activeUser) {
		SimpleStatCalculator ssc = new SimpleStatCalculator(activeUser);
		
		ssc.actualMonthBalace(actualMonthBalanceTF);
		ssc.actualYearBalance(actualYearBalanceTF);
		ssc.MeanMonthExpenditures(MeanExpendituresTF);
		CategoryMeanPanel = ssc.MeanByCategory();
		CategoryMeanPanel.setBounds(6, 129, 689, 186);
		contentPanel.add(CategoryMeanPanel);
		
		ssc.MeanEntries(MeanEntriesTF);
		
		
		
		
		
		
		
	}

	protected void closeWindow() {
		this.setVisible(false);
	}
}
