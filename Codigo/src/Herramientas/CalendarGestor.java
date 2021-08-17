package Herramientas;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;
import javax.swing.JTextField;


import ED.Date;
import Principal.Usuario;
import Principal.Evento;
import Vistas.CalendarView;

public class CalendarGestor  implements MouseListener{
	Usuario activeUser;
	CalendarView cv;
	static String LastFecha;
	static String LastFecha2;
	
	public CalendarGestor (Usuario activeUser, CalendarView calendarView) {
		this.activeUser = activeUser;
		cv = calendarView;
	}

	public void setName(JTextField TF) {
		
		String strDateFormat = "MM/yyyy";
		SimpleDateFormat obj = new SimpleDateFormat(strDateFormat);
		
		String full_Date = obj.format(Date.getSysDate());
		
		int month = Integer.parseInt(full_Date.substring(0, 2));
		int year = Integer.parseInt(full_Date.substring(3));
		
		TF.setText(month+"/"+year);
		LastFecha = month+"/"+year;
	}

	public static void nextMonthLabel(JTextField TF) {
		String Actual = TF.getText();
		
		StringTokenizer st = new StringTokenizer(Actual,"/");
		
		int mes  = Integer.parseInt(st.nextToken());
		int year = Integer.parseInt(st.nextToken());
		
		mes++;
		if(mes == 13) {
			mes = 1;
			year++;
		}
		
		TF.setText(mes+"/"+year);
		LastFecha = mes+"/"+year;
	}
	
	public static void previousMonthLabel(JTextField TF) {
		String Actual = TF.getText();
		
		StringTokenizer st = new StringTokenizer(Actual,"/");
		
		int mes  = Integer.parseInt(st.nextToken());
		int year = Integer.parseInt(st.nextToken());
		
		mes--;
		if(mes == 0) {
			mes = 12;
			year--;
		}
		
		TF.setText(mes+"/"+year);
		LastFecha= mes+"/"+year;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int column = cv.getTable().getSelectedColumn();
		int row = cv.getTable().getSelectedRow();
		Date fecha;
		try {
			int dia = (int) cv.getTable().getValueAt(row, column);
			 fecha = new Date(dia+"/"+LastFecha);
		}catch(ClassCastException ex) {
			String dia = (String) cv.getTable().getValueAt(row, column);
			 fecha = new Date(dia+"/"+LastFecha);
		}
		
		
		
		LastFecha2 = fecha.toString();
		
		
		DefaultListModel<Evento> list = activeUser.getEventsByDate(fecha);		
		cv.setEventList(list);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int column = cv.getTable().getSelectedColumn();
		int row = cv.getTable().getSelectedRow();
		Date fecha;
		try {
			int dia = (int) cv.getTable().getValueAt(row, column);
			 fecha = new Date(dia+"/"+LastFecha);
		}catch(ClassCastException ex) {
			String dia = (String) cv.getTable().getValueAt(row, column);
			 fecha = new Date(dia+"/"+LastFecha);
		}
		
		
		
		LastFecha2 = fecha.toString();
		
		
		DefaultListModel<Evento> list = activeUser.getEventsByDate(fecha);		
		cv.setEventList(list);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static Date getFecha() {
		return new Date(LastFecha2);
	}
}
