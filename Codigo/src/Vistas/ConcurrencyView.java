/**
 * @Author Isma
 **/
package Vistas;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import Principal.Divisas;

@SuppressWarnings("serial")
public class ConcurrencyView extends JPanel{
	//Components 
	private JButton Calcular;
	private JComboBox<String> from;
	private JComboBox<String> to;
	private JTextPane fromM;
	private JTextPane toM;
	private JTextPane DivisaEntrada;
	private JTextPane DivisaSalida;
	private JTextPane Importe;
	private JTextPane Resultado;
	private JTextPane RatesPane;
	
	public ConcurrencyView() {
		//Ajustes de Ventana
		this.setFocusTraversalKeysEnabled(false);
		this.setFocusable(false);
		this.setBackground(SystemColor.window);
		this.setLayout(null);
		

		Calcular = new JButton("Exchange");
		Calcular.setBounds(150, 105, 101, 22);
		this.add(Calcular);
		
		from = new JComboBox<String>();
		from.addItem("EUR");
		from.addItem("USD");
		from.addItem("GBP");
		from.addItem("CAD");
		from.setBounds(10, 31, 119, 20);
		this.add(from);
		
		to = new JComboBox<String>();
		to.addItem("USD");
		to.addItem("EUR");
		to.addItem("GBP");
		to.addItem("CAD");
		to.setBounds(10, 74, 119, 20);
		this.add(to);
		
		fromM = new JTextPane();
		fromM.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		fromM.setBounds(150, 31, 101, 20);
		this.add(fromM);
		
		toM = new JTextPane();
		toM.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		toM.setBounds(150, 74, 101, 20);
		toM.setEditable(false);
		this.add(toM);
		
		DivisaEntrada = new JTextPane();
		DivisaEntrada.setBackground(SystemColor.window);
		DivisaEntrada.setEditable(false);
		DivisaEntrada.setText("Original Currency:");
		DivisaEntrada.setBounds(10, 11, 119, 20);
		this.add(DivisaEntrada);
		
		DivisaSalida = new JTextPane();
		DivisaSalida.setBackground(SystemColor.window);
		DivisaSalida.setEditable(false);
		DivisaSalida.setText("Destination Currency:");
		DivisaSalida.setBounds(10, 54, 119, 20);
		this.add(DivisaSalida);
		
		Importe = new JTextPane();
		Importe.setBackground(SystemColor.window);
		Importe.setEditable(false);
		Importe.setText("Amount to exchange:");
		Importe.setBounds(150, 11, 126, 20);
		this.add(Importe);
		
		Resultado = new JTextPane();
		Resultado.setBackground(SystemColor.window);
		Resultado.setEditable(false);
		Resultado.setText("Exchange Result: ");
		Resultado.setBounds(150, 54, 84, 20);
		this.add(Resultado);
		
		RatesPane = new JTextPane();
		RatesPane.setBackground(SystemColor.window);
		RatesPane.setBounds(348, 11, 200, 283);
		add(RatesPane);
	}

	public void registrarControladorDivisas(ActionListener ctr) {
		Calcular.addActionListener(ctr);
		Calcular.setActionCommand("Calcular");
	}
	
	//Servicios
	public String divisaOrigen() {
		return (String) from.getSelectedItem();
	}
	
	public String divisaDestino() {
		return (String) to.getSelectedItem();
	}
	
	public double divisaCantidad() {
		return Double.parseDouble(fromM.getText());
	}
	
	public void divisaDeSalida(double cant) {
		toM.setText(Double.toString(cant));
	}
	
	public void SetRates() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Exchange Rates - Related To €\n");
		for(String s : Divisas.getRates()) {
			sb.append(s+"\n");
		}
		
		RatesPane.setText(sb.toString());
	}
}
