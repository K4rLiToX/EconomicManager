package Herramientas;

import java.awt.Color;
import java.awt.Paint;
import java.awt.SystemColor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.ListModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import ED.Date;
import Excepciones.EmptyInfoException;
import Principal.Company;
import Principal.Movimiento;
import Principal.Usuario;

public class GraphicDisplayer {
	// Tipos de letra
	static Font helvetica_bold = FontFactory.getFont(FontFactory.HELVETICA, 21, Font.BOLD);
	static Font times_italic = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 14);
	static Font times_normal = FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL);
	
	//Clase para colorear barritas
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

	
	//Funcion exportar como Grafica
	@SuppressWarnings("unchecked")
	public static void generateGraphicsPdf(Object[] graphicInfo, Usuario activeUser) throws EmptyInfoException {
		// Campos Externos
		String MainFilter = (String) graphicInfo[0];
		String FilterTxt = (String) graphicInfo[1];
		if (FilterTxt.length() == 0)
			FilterTxt = "All Registered Transactions";
		ListModel<Movimiento> movements = (ListModel<Movimiento>) graphicInfo[2];

		// Comprobar Datos Validos
		if (movements.getSize() == 0)
			throw new EmptyInfoException();

		// Seleccionar Destino del Fichero excel
		JFileChooser chooser = new JFileChooser();

		chooser.setSelectedFile(new File(FilterTxt + "_graphics.pdf"));
		int returnVal = chooser.showSaveDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String Ruta = chooser.getSelectedFile().toString();
			File graphic = new File(Ruta);
			graphic.setWritable(true);

			try {
				FileOutputStream file = new FileOutputStream(graphic);
				Document doc = new Document();
				PdfWriter.getInstance(doc, file);
				doc.open();

				// Agregar Cabecera
				String Top_Header = activeUser.getCompany().getName();

				if (new File("BD/" + activeUser.getUserName() + ".jpg").exists()) {
					Image logo = Image.getInstance("BD/" + activeUser.getUserName() + ".jpg");
					logo.scaleAbsolute(100, 100);
					logo.setAlignment(Element.ALIGN_RIGHT);
					doc.add(logo);
				}

				Paragraph p1 = new Paragraph(Top_Header + "\n\n", helvetica_bold);
				doc.add(p1);
				doc.add(new LineSeparator());

				// Añadir Informacion de la Compañia del Usuario
				String Company_Info = getCompanyHeader(activeUser.getCompany());
				Paragraph p2 = new Paragraph(Company_Info + "\n", times_italic);
				p2.setAlignment(Element.ALIGN_JUSTIFIED);
				doc.add(p2);
				doc.add(new LineSeparator());

				// Añadir Tipo de Movimientos Seleccionados
				String Filters_Name = getFilterLine(MainFilter, FilterTxt);
				Paragraph p3 = new Paragraph(Filters_Name + "\n", times_italic);
				doc.add(p3);
				
				//Generar Grafica			
				Rectangle r = doc.getPageSize();
				int width = (int)r.getWidth();
				int height = 75*movements.getSize();
				JFreeChart chart = generateChart(movements,Filters_Name);
				File chart_file = new File("temp.jpg");
				ChartUtilities.saveChartAsJPEG(chart_file, chart, width, height);
				
				Image im = Image.getInstance("temp.jpg");
				im.setAlignment(Element.ALIGN_JUSTIFIED);
				doc.add(im);
				
				
				
				
				
				//Set Uneditable & close
					
				graphic.setWritable(false);
				doc.close();
				chart_file.delete();
				
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private static JFreeChart generateChart(ListModel<Movimiento> movements, String filters_Name) {
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Paint[] colors = new Paint[movements.getSize()];
		
		for(int i = 0;i<movements.getSize();i++) {
			Movimiento m = movements.getElementAt(i);
			
			data.addValue(m.getCantidad(), "Esto khe es", m.getConcepto());
			
			if(m.getCantidad()>= 0) {
				colors[i] = Color.green;
			}else {
				colors[i] = Color.red;
			}
			
		}
		
		JFreeChart chart = ChartFactory.createBarChart(filters_Name,"", "Amount", data, PlotOrientation.HORIZONTAL,
										false, true, false);
		CategoryPlot p = chart.getCategoryPlot();
		p.setBackgroundPaint(Color.white);
		p.setRangeGridlinePaint(Color.black);
		
		//Pintar color de las barras
		p.setRenderer(new CustomRenderer(colors));
		return chart;
	}

	private static String getFilterLine(String mainFilter, String filterTxt) {
		if (mainFilter.equals("Showing All Transactions")) {
			return mainFilter + ":\n ";
		} else {
			return "Transations by " + mainFilter + ": " + filterTxt + "\n";
		}

	}

	private static String getCompanyHeader(Company company) {
		return "Phone Number/Fax: " + company.getPhoneNumber() + "\nContact Email: " + company.getEmail()
				+ "\nPhysical Address: " + company.getAddress() + "\n";
	}

	//Funcion Balance de los ultimos meses
	public static ChartPanel generateLastMonthsGraphics(Usuario activeUser) {
		JFreeChart chart = generateChart(activeUser.getMovementsList());
		ChartPanel panel = new ChartPanel(chart);
		
		return panel;
	}

	private static JFreeChart generateChart(DefaultListModel<Movimiento> movementsList) {
		
		//Data 
		Double[][] values = new Double[6][3];
		values = calculateMeans(movementsList);
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		
		//Colors
		Paint[] colors  = new  Paint[6];
		
		for(int i = 0;i<6;i++) {
			
			if(values[i][0] >= 0) {
				colors[i] = Color.green;
			}else {
				colors[i] = Color.red;
			}
			
			data.addValue(values[i][0], "",Date.showGraph(values[i][1],values[i][2]));

		}
		
		JFreeChart chart = ChartFactory.createBarChart("Last Months Balance Summary", "Date", " Total Balance", data,
				PlotOrientation.VERTICAL, false, true, false);
		
		//Draw chart Properties
		chart.setBackgroundPaint(SystemColor.window);
		CategoryPlot p = chart.getCategoryPlot();
		
		p.setRangeGridlinePaint(Color.black);
		p.setBackgroundPaint(SystemColor.window);
		p.setRenderer(new CustomRenderer(colors));
		
		return chart;
	}

	private static Double[][] calculateMeans(DefaultListModel<Movimiento> movementsList) {
		Double[][] values = new Double[6][3];
		
		//Get todays date
		String strDateFormat = "MM/yyyy";
		SimpleDateFormat obj = new SimpleDateFormat(strDateFormat);
		
		String full_Date = obj.format(Date.getSysDate());
		
		int month = Integer.parseInt(full_Date.substring(0, 2));
		int year = Integer.parseInt(full_Date.substring(3));
		
		//Calculate means from the last six months		
		for(int i = 5;i >= 0;i--) {
			values[i][0] = Calculate(month,year,movementsList);
			values[i][1] = Double.parseDouble(""+month);
			values[i][2] = Double.parseDouble(""+year);
			month--;
			if(month == 0) {
				month = 12;
				year--;
			}
		}
		
		return values;
	}

	private static Double Calculate(int month, int year,DefaultListModel<Movimiento> movementsList) {
		int sum = 0;
		int cont = 0;
		
		for(int i = 0;i<movementsList.getSize();i++) {
			Movimiento m = movementsList.get(i);
			
			if(m.getFecha().getMonth()==month) {
				if(m.getFecha().getYear()==year) {
					sum+= m.getCantidad();
					cont++;
				}
			}
		}
		
		if(cont == 0) {
			return 0.0;
		}else {
			return Double.parseDouble(""+sum);
		}
	}



	
}
