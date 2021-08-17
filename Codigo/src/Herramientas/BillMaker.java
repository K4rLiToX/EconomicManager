package Herramientas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JFileChooser;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.*;

import Principal.Company;
import Principal.Contacto;
import Principal.Movimiento;
import Principal.Usuario;

public class BillMaker {

	public static int generateBill(Movimiento mov, Usuario activeUser) {
		JFileChooser chooser = new JFileChooser();
		
		chooser.setSelectedFile(new File(mov.getIdentificador()+".pdf"));
		int returnVal = chooser.showSaveDialog(null);
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			String Ruta = chooser.getSelectedFile().toString();
			File bill = new File(Ruta);
			
			try {
				FileOutputStream file =  new FileOutputStream(bill);
				Document doc = new Document(PageSize.A4,30,20,20,10);
				PdfWriter.getInstance(doc, file);
				doc.open();
				
				//Tipos de letra
				Font helvetica_bold = FontFactory.getFont(FontFactory.HELVETICA, 21, Font.BOLD);
				Font times_normal	= FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL);
				Font times_italic	= FontFactory.getFont(FontFactory.TIMES_BOLDITALIC,14);
				
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
				Paragraph p2 = new Paragraph("Issuer of the Invoidance: "+"\n",times_italic);
				Chunk c_ci = new  Chunk(Company_Info + "\n",times_normal);
				p2.setAlignment(Element.ALIGN_JUSTIFIED);
				p2.add(c_ci);
				doc.add(p2);
				doc.add(new LineSeparator());
				
				
				//Añadir Informacion del Cliente
				String Client_Info = getContactHeader(activeUser.getContactByName(mov.getContacto()));
				Paragraph p3 = new Paragraph("Recipient of the Invoidance: "+"\n",times_italic);
						c_ci = new  Chunk(Client_Info + "\n",times_normal);
				p3.setAlignment(Element.ALIGN_JUSTIFIED);
				p3.add(c_ci);
				doc.add(p3);
				doc.add(new LineSeparator());
				
				//Añadir Informacion del Cobro
				String Bill_Data = getData(mov);
				Paragraph p4 = new Paragraph("Invoidance ID: "+mov.getIdentificador()+"\n",times_italic);
				Chunk c_bd = new  Chunk(Bill_Data + "\n",times_normal);
				p4.setAlignment(Element.ALIGN_JUSTIFIED);
				p4.add(c_bd);
				doc.add(p4);
				doc.add(new LineSeparator());
				
				//Añadir Pie de Página
				for(int i = 0;i<11;i++) {
					Chunk c = new Chunk("\n");
					doc.add(c);
				}
				Paragraph p5 = new Paragraph("Page 1 of 1",times_italic);
				p5.setAlignment(Element.ALIGN_CENTER);
				doc.add(p5);
				
				//Set PDF Uneditable
				bill.setWritable(false);
				
				doc.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			return 0;
		}else {
			return -1;
		}
	}

	private static String getData(Movimiento mov) {
		return "Amount to Pay: "+Math.abs(mov.getCantidad())+ "\n In Concept: "+mov.getConcepto()+
				"\nDate: "+mov.getFecha()+"\n";
	}

	private static String getContactHeader(Contacto contact) {
		return contact.getRelacion()+": "+contact.getNombreCompleto()+"\nNIF/CIF: "+contact.getDNI()
			+"\nPhoneNumber: "+contact.getTelefono()+"\n\n";
	}

	private static String getCompanyHeader(Company company) {
		return "Phone Number/Fax: "+company.getPhoneNumber()
				+"\nContact Email: "+company.getEmail()+"\nPhysical Address: "+company.getAddress()+"\n";
	}

}
