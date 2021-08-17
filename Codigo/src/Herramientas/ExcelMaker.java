package Herramientas;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JFileChooser;
import javax.swing.ListModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.*;

import Excepciones.EmptyInfoException;
import Principal.Usuario;
import Principal.Company;
import Principal.Movimiento;

public class ExcelMaker {
	//Tipos de letra
	static Font helvetica_bold = FontFactory.getFont(FontFactory.HELVETICA, 21, Font.BOLD);
	static Font times_italic	= FontFactory.getFont(FontFactory.TIMES_BOLDITALIC,14);
	static Font times_normal	= FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL);
	
	
	@SuppressWarnings("unchecked")
	public static void generateExcelFile(Object[] info,Usuario activeUser) throws EmptyInfoException{
		//Campos Externos 
		String MainFilter = (String) info[0];
		String FilterTxt = (String) info[1];
		if(FilterTxt.length() == 0) FilterTxt = "All Registered Transactions";
		ListModel<Movimiento> movements = (ListModel<Movimiento>) info[2];
		
		//Comprobar Datos Validos 
		if(movements.getSize() == 0) throw new EmptyInfoException();
		
		//Seleccionar Destino del Fichero excel
		JFileChooser chooser = new JFileChooser();
		
		chooser.setSelectedFile(new File(FilterTxt+"_table.pdf"));
		int returnVal = chooser.showSaveDialog(null);
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			String Ruta = chooser.getSelectedFile().toString();
			File Table = new File(Ruta);
			Table.setWritable(true);
		
		
			try {
				FileOutputStream file = new FileOutputStream(Table);
				Document doc = new Document();
				PdfWriter.getInstance(doc, file);
				doc.open();
				
			
				
				//Agregar Cabecera
				String Top_Header = activeUser.getCompany().getName();
		
				if(new File("BD/"+activeUser.getUserName()+".jpg").exists()) {
					Image logo = Image.getInstance("BD/"+activeUser.getUserName()+".jpg");
					logo.scaleAbsolute(100, 100);
					logo.setAlignment(Element.ALIGN_RIGHT);
					doc.add(logo);
				}
	
				Paragraph p1 = new Paragraph(Top_Header+"\n\n",helvetica_bold);
				doc.add(p1);
				doc.add(new LineSeparator());
				
				//Añadir Informacion de la Compañia del Usuario
				String Company_Info = getCompanyHeader(activeUser.getCompany());
				Paragraph p2 = new Paragraph(Company_Info+"\n",times_italic);
				p2.setAlignment(Element.ALIGN_JUSTIFIED);
				doc.add(p2);
				doc.add(new LineSeparator());
				
				//Añadir Tipo de Movimientos Seleccionados
				String Filters_Name = getFilterLine(MainFilter,FilterTxt);
				Paragraph p3 = new Paragraph(Filters_Name + "\n",times_italic);
				doc.add(p3);
				
				//Añadir Tabla
				PdfPTable excel = new PdfPTable(6);
				excel.setWidthPercentage(100);
				excel.setHorizontalAlignment(Element.ALIGN_LEFT);
				float [] widths = {30,100,210,130,130,70};
				excel.setTotalWidth(widths);
				
				excel.addCell(addTitle("Id"));
				excel.addCell(addTitle("Date"));
				excel.addCell(addTitle("Concept"));
				excel.addCell(addTitle("Related Contact"));
				excel.addCell(addTitle("Category"));
				excel.addCell(addTitle("Amount"));
				
				for(int i = 0;i<movements.getSize();i++) {
					Movimiento m = movements.getElementAt(i);
					
					
					excel.addCell(addDataCell(""+m.getIdentificador()));
					excel.addCell(addDataCell(""+m.getFecha()));
					excel.addCell(addDataCell(m.getConcepto()));
					excel.addCell(addDataCell(m.getContacto()));
					excel.addCell(addDataCell(m.getCategory()));
					excel.addCell(addDataCell(""+m.getCantidad()));
				}
				
				doc.add(excel);
				
				
				
				//Set Document Uneditable
				Table.setWritable(false);
				doc.close();
				
			}catch(DocumentException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private static PdfPCell addDataCell(String string) {
		// create cell
        PdfPCell cell = new PdfPCell(new Phrase(string,times_normal));
 
        // set style
        cell.setPaddingTop(0f);
        cell.setPaddingBottom(7f);   
        
        return cell;
	}

	private static PdfPCell addTitle(String string) {
		// create cell
        PdfPCell cell = new PdfPCell(new Phrase(string,times_normal));
 
        // set style
        cell.setBackgroundColor(new BaseColor(0,121,182));
        cell.setPaddingTop(0f);
        cell.setPaddingBottom(7f);        
        return cell;
	}

	private static String getFilterLine(String mainFilter, String filterTxt) {
		if(mainFilter.equals("Showing All Transactions")) {
			return mainFilter+":\n ";
		}else {
			return "Transations by "+mainFilter+": "+filterTxt+"\n";
		}
	
	}

	private static String getCompanyHeader(Company company) {
		return "Phone Number/Fax: "+company.getPhoneNumber()
				+"\nContact Email: "+company.getEmail()+"\nPhysical Address: "+company.getAddress()+"\n";
	}
}
