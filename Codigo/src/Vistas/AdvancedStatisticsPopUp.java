package Vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;

import Herramientas.AdvancedStatCalculator;
import Principal.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AdvancedStatisticsPopUp extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField NextMonthValue;
	private ChartPanel RegressionChart;
	
	
	public AdvancedStatisticsPopUp(Usuario activeUser) {
		setBounds(100, 100, 669, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNextMonthBalance = new JLabel("Next Month Balance Prediction");
		lblNextMonthBalance.setBounds(6, 6, 199, 16);
		contentPanel.add(lblNextMonthBalance);
		
		NextMonthValue = new JTextField();
		NextMonthValue.setHorizontalAlignment(SwingConstants.CENTER);
		NextMonthValue.setEditable(false);
		NextMonthValue.setBorder(null);
		NextMonthValue.setBackground(SystemColor.window);
		NextMonthValue.setBounds(259, 1, 224, 26);
		contentPanel.add(NextMonthValue);
		NextMonthValue.setColumns(10);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeView();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
			
			
		this.startView(activeUser);
		
	}
	
	private void startView(Usuario activeUser) {
		
		AdvancedStatCalculator asc = new AdvancedStatCalculator(activeUser);
		RegressionChart =  asc.NextMonthPrediction(NextMonthValue);
		RegressionChart.setBounds(6, 34, 660, 300);
		contentPanel.add(RegressionChart);

	}

	protected void closeView() {
		this.setVisible(false);
	}
}
