package Herramientas;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import ED.Date;
import Principal.Movimiento;
import Principal.Usuario;

import java.awt.Color;
import java.awt.Paint;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AdvancedStatCalculator {
	//Fields
	private  Map<String,SortedSet<Movimiento>> transactions = new HashMap<>();
	
	public AdvancedStatCalculator(Usuario activeUser) {
		for(Movimiento m: activeUser.getIteradorMovimientos()) {
			if(transactions.containsKey(m.getCategory())) {
				SortedSet<Movimiento> values = transactions.get(m.getCategory());
				
				values.add(m);
			}else {
				SortedSet<Movimiento> values = new TreeSet<>();
				values.add(m);
				transactions.put(m.getCategory(), values);
			}
		}
	}

	public  ChartPanel NextMonthPrediction(JTextField TF) {
		//Tomar mes y año del Sistema
		Date Limite = CalcularTiempo();
		
		//Coger Datos de los Ultimos 3 Meses
		Iterator<String> it = transactions.keySet().iterator();
		while(it.hasNext()) {
			String category = it.next();
			
			SortedSet<Movimiento> Transacciones = transactions.get(category);
			Movimiento aux = new Movimiento(0.0,Limite,null,null,null,null);
			Transacciones = Transacciones.tailSet(aux);
			transactions.replace(category, Transacciones);
		}
		
		//Calcular Media de Gasto por Categorias
		Map<String,List<Double>> Medias = new HashMap<>();
		it = transactions.keySet().iterator();
		
		while(it.hasNext()) {
			String Category = it.next();
			Medias.put(Category, new ArrayList<>());
			
			double count = 0;
			int num = 0;
			int mes = Limite.getMonth();
			int year = Limite.getYear();
			
			for(int i = 0;i<3;i++) {
				List<Double> valores = Medias.get(Category);
				
				for(Movimiento m:transactions.get(Category)) {
					if(m.getFecha().getMonth() == mes && m.getFecha().getYear() == year) {
						count+= m.getCantidad();
						num++;
					}
				}
				if(num == 0) valores.add(i,0.0);
				else valores.add(i,count/num);
				mes++;
				if(mes == 13) {
					mes = 1;
					year++;
				}
				
				num = 0;
				count = 0;
			}
		}
		
		
		
			//Aplicar Parametros de Ponderacion y Sumar
			it = transactions.keySet().iterator();
			double Prediccion = 0;
			
			
			while(it.hasNext()) {
				String Category = it.next();
				double[] ponderaciones = getParametros(Category);
				List<Double> valores = Medias.get(Category);
				
				for(int i = 0;i<3;i++) {
					double total = ponderaciones[i]*valores.get(i);
					Prediccion+= total;
				}
			}
			
			
			if(Prediccion >= 0) {
				TF.setForeground(new Color(0,128,0));
			}else {
				TF.setForeground(Color.red);
			}
			
			TF.setText(String.format("%.2f", Prediccion));
			
			//Dibujar Grafica de Regresion 
			Paint[] colors = new Paint[4];
			colors[0] = Color.blue;
			colors[1] = Color.blue;
			colors[2] = Color.blue;
			colors[3] = Color.magenta;
			
			DefaultCategoryDataset data = new DefaultCategoryDataset();
			
			CompleteDataSet(data,Medias,Prediccion);
			
			JFreeChart reg_chart = ChartFactory.createLineChart("Regression Chart", "Last 3 Months", "Pondered Balance", data,
									PlotOrientation.VERTICAL,false,false,false);
			
			CategoryPlot p = reg_chart.getCategoryPlot();
			
			p.setRenderer(new CustomRenderer(colors));
			
			ChartPanel panel = new ChartPanel(reg_chart);
		
		
			return panel;

		}
	
	private void CompleteDataSet(DefaultCategoryDataset data, Map<String, List<Double>> Medias, double prediccion) {
		String[] meses  = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		
		
		
		Date Time = CalcularTiempo();
		int mes = Time.getMonth();
		int Year = Time.getYear();
		String fecha = meses[mes-1]+"/"+Year;
		
		for(int i = 0;i<3;i++) {
			double Balance = 0;
			
			Iterator<String> it = transactions.keySet().iterator();
			
			while(it.hasNext()) {
				String Category = it.next();
				List<Double> valores = Medias.get(Category);
				
				for(int j = 0;j<3;j++) {
					Balance += valores.get(i);
				}
			}
			
			data.addValue(Balance," ",fecha);
			
			mes++;
			if(mes == 13) {
				mes = 1;
				Year++;
			}
			fecha = meses[mes-1]+"/"+Year;
		}
		
		data.addValue(prediccion, " ", fecha);
	}

	private double[] getParametros(String category) {
		switch (category){
			case "Extraordinary":
				double[] r = {0.01,0.02,0.07};
				return r;
			
			case "Supply":
				double[] s = {0.33,0.33,0.34};
				return s;
			
			case "Employee Salaries": 
				double[] t = {0.34,0.33,0.33};
				return t;
			
			case "Provider":
				double[] u = {0.1,0.2,0.5};
				return u;
			default:
				double[] w = {0.05,0.3,0.5};
				return w;
			
 		}
	}

	private  Date CalcularTiempo() {
		String strDateFormat = "MM/yyyy";
		SimpleDateFormat obj = new SimpleDateFormat(strDateFormat);
		
		String full_Date = obj.format(Date.getSysDate());
		
		int month = Integer.parseInt(full_Date.substring(0, 2));
		int year = Integer.parseInt(full_Date.substring(3));
		
		for(int i = 0;i<2;i++) {
			month--;
			
			if(month == 0) {
				month = 12;
				year--;
			}
		}
		return new Date(1+"/"+month+"/"+year);
	}

		//Clase para colorear Barritas	
		@SuppressWarnings("serial")
	private class CustomRenderer extends LineAndShapeRenderer {

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
}
