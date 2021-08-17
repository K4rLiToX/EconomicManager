package Herramientas;

import java.awt.Color;
import java.awt.Paint;
import java.awt.SystemColor;
import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import ED.Date;
import Principal.Movimiento;
import Principal.Usuario;
import Principal.Contacto;

public class SimpleStatCalculator {
	//Clase para colorear Barritas	
	@SuppressWarnings("serial")
	static class CustomRenderer extends BarRenderer {

        /** The colors. */
        private Paint[] colors;

        /**
         * Creates a new renderer.
         *
         * @param colors  the colors.
         */
        public CustomRenderer(final Paint[] colors) {
            this.colors = colors;
        }

        /**
         * Returns the paint for an item.  Overrides the default behaviour inherited from
         * AbstractSeriesRenderer.
         *
         * @param row  the series.
         * @param column  the category.
         *
         * @return The item color.
         */
        public Paint getItemPaint(final int row, final int column) {
            return this.colors[column % this.colors.length];
        }
    }
	
	//Data
	DefaultListModel<Movimiento> transactions;
	Map<String,Double> contacts = new HashMap<>();
	
	
	public SimpleStatCalculator(Usuario activeUser) {
		transactions  = activeUser.getMovementsList();
		DefaultComboBoxModel<Contacto> c = activeUser.getContactList();
		
		for(int i = 0;i<c.getSize();i++) {
			Contacto cl = c.getElementAt(i);
			contacts.put(cl.getNombreCompleto(), 0.0);
		}
		
	}

	public void actualMonthBalace(JTextField TF) {
		String strDateFormat = "MM/yyyy";
		SimpleDateFormat obj = new SimpleDateFormat(strDateFormat);
		
		String full_Date = obj.format(Date.getSysDate());
		
		int month = Integer.parseInt(full_Date.substring(0, 2));
		int year = Integer.parseInt(full_Date.substring(3));
		
		double balance = 0;
		
		for(int i = 0;i<transactions.getSize();i++) {
			Date fecha = transactions.get(i).getFecha();
			
			if(fecha.getMonth() == month && fecha.getYear() == year) {
				balance += transactions.get(i).getCantidad();
			}
		}
		
		if(balance >= 0) {
			TF.setForeground(new Color(0,128,0));
		}else {
			TF.setForeground(Color.red);
		}
		
		TF.setText(""+balance);
		
	}

	
	public void actualYearBalance(JTextField TF) {
		String strDateFormat = "yyyy";
		SimpleDateFormat obj = new SimpleDateFormat(strDateFormat);
		
		String full_Date = obj.format(Date.getSysDate());
		
		int year = Integer.parseInt(full_Date.substring(0));
		
		double balance = 0;
		
		for(int i = 0;i<transactions.getSize();i++) {
			Date fecha = transactions.get(i).getFecha();
			
			if(fecha.getYear() == year) {
				balance += transactions.get(i).getCantidad();
			}
		}
		
		if(balance >= 0) {
			TF.setForeground(new Color(0,128,0));
		}else {
			TF.setForeground(Color.red);
		}
		
		TF.setText(""+balance);
	}


	public void MeanMonthExpenditures(JTextField TF) {
		SortedSet<Movimiento> trans = new TreeSet<>();
		float mean;
		
		for(int i = 0;i<transactions.getSize();i++) {
			trans.add(transactions.get(i));			
		}
		
		String strDateFormat = "MM/yyyy";
		SimpleDateFormat obj = new SimpleDateFormat(strDateFormat);
		
		String full_Date = obj.format(Date.getSysDate());
		
		int month = Integer.parseInt(full_Date.substring(0, 2));
		int year = Integer.parseInt(full_Date.substring(3));
		
		
		
		int numMeses = 1;
		int sum = 0;
		
		int M_i = trans.first().getFecha().getMonth();
		int Y_i = trans.first().getFecha().getYear();
		
		while(M_i != month || Y_i != year) {
			numMeses++;
			M_i++;
			if(M_i == 13) {
				M_i = 1;
				Y_i++;
			}
		}
		
		for(Movimiento m : trans) {
			if(m.getCantidad()<0) {
				sum+= m.getCantidad();
			}
		}
		
		if(numMeses == 0) {
			mean = 0;
		}else {
			mean = sum/numMeses;
		}
		
		TF.setForeground(Color.red);
		TF.setText(""+Math.abs(mean));	
	}

	
	public ChartPanel MeanByCategory() {
		SortedSet<Movimiento> trans = new TreeSet<>();
		String[] names = new String[4];
		double[] values = new double[4];
		Paint[] colors = new Paint[4];
		
		for(int i = 0;i<4;i++) {
			values[i] = 0;
		}
		
		names[0] = "Salaries";
		names[1] = "Extraordinary";
		names[2] = "Providers";
		names[3] = "Supplies";
		
		colors[0] = Color.blue;
		colors[1] = Color.red;
		colors[2] = Color.magenta;
		colors[3] = Color.yellow;
		
		
		//Ordenar Transacciones por fecha
		for(int i = 0;i<transactions.getSize();i++) {
			trans.add(transactions.get(i));			
		}
		
		//Obtener numero de meses
		String strDateFormat = "MM/yyyy";
		SimpleDateFormat obj = new SimpleDateFormat(strDateFormat);
		
		String full_Date = obj.format(Date.getSysDate());
		
		int month = Integer.parseInt(full_Date.substring(0, 2));
		int year = Integer.parseInt(full_Date.substring(3));
		
		
		
		int numMeses = 1;
		
		int M_i = trans.first().getFecha().getMonth();
		int Y_i = trans.first().getFecha().getYear();
		
		while(M_i != month || Y_i != year) {
			numMeses++;
			M_i++;
			if(M_i == 13) {
				M_i = 1;
				Y_i++;
			}
		}
		
		//Calcular Totales por Categoria
		
		for(Movimiento m: trans) {
			switch(m.getCategory()) {
				case "Employee Salary":
					values[0]= m.getCantidad();
				break;
				
				case "Extraordinary":
					values[1] =m.getCantidad();
				break;
				
				case "Provider":
					values[2]= m.getCantidad();
				break;
				
				case "Supply":
					values[3]= m.getCantidad();
				break;
				
				default:
				break;
			}
		}
		
		//Calcular Medias Por Categorias
		for(int i = 0;i<4;i++) {
			values[i] = values[i]/numMeses;
		}
		
		
		
		//Dibujar Grafica
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		
		for(int i = 0;i<4;i++) {
			data.addValue(Math.abs(values[i]), (Double) values[i], names[i]);;
		}
		
		JFreeChart graphic = ChartFactory.createBarChart("","","",data,
							PlotOrientation.HORIZONTAL,false,false,false);
		
		
		graphic.setBackgroundPaint(SystemColor.window);
		ChartPanel panel = new ChartPanel(graphic);	
		
		CategoryPlot plot = graphic.getCategoryPlot();
		
		plot.setRenderer(new CustomRenderer(colors));
		
		return panel;
	}

	
	public void MeanEntries(JTextField TF) {
		SortedSet<Movimiento> trans = new TreeSet<>();
		float mean;
		
		for(int i = 0;i<transactions.getSize();i++) {
			trans.add(transactions.get(i));			
		}
		
		String strDateFormat = "MM/yyyy";
		SimpleDateFormat obj = new SimpleDateFormat(strDateFormat);
		
		String full_Date = obj.format(Date.getSysDate());
		
		int month = Integer.parseInt(full_Date.substring(0, 2));
		int year = Integer.parseInt(full_Date.substring(3));
		
		
		
		int numMeses = 1;
		int sum = 0;
		
		int M_i = trans.first().getFecha().getMonth();
		int Y_i = trans.first().getFecha().getYear();
		
		while(M_i != month || Y_i != year) {
			numMeses++;
			M_i++;
			if(M_i == 13) {
				M_i = 1;
				Y_i++;
			}
		}
		
		for(Movimiento m : trans) {
			if(m.getCantidad()>0) {
				sum+= m.getCantidad();
			}
		}
		
		if(numMeses == 0) {
			mean = 0;
		}else {
			mean = sum/numMeses;
		}
		
		TF.setForeground(new Color(0,128,0));
		TF.setText(""+Math.abs(mean));		
	}

	
	
}
