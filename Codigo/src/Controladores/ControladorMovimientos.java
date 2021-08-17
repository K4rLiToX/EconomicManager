/**
 * @Author Pedro
 */

package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import org.jfree.chart.ChartPanel;

import Excepciones.EmptyInfoException;
import Herramientas.BillMaker;
import Herramientas.ExcelMaker;
import Herramientas.GraphicDisplayer;
import Principal.Movimiento;
import Principal.Usuario;
import Vistas.AdvancedStatisticsPopUp;
import Vistas.BalanceGraphicsPopUp;
import Vistas.CommonErrorPopUp;
import Vistas.EditMovementPopUp;
import Vistas.NewMovementPopUp;
import Vistas.SimpleStatisticsPopUp;
import Vistas.ViewMovementPopUp;
import Vistas.VistaAcciones;

public class ControladorMovimientos implements ActionListener {
	//Vistas
	VistaAcciones va;
	
	//Usuario
	Usuario activeUser;
	
	//VistasInternas
		NewMovementPopUp nmpu = new NewMovementPopUp();
		EditMovementPopUp empu = new EditMovementPopUp();
		ViewMovementPopUp vmpu = new ViewMovementPopUp();
	
	//Controladores Internos
		NewMovementPopUpControler nmpuc;
		EditMovementPopUpControler empuc;
		ViewMovementPopUpControler vmpuc;
		
	//Variables Aux
	String LastFilter = "";
	
	public ControladorMovimientos(VistaAcciones v2, Usuario au) {
		activeUser = au;
		va = v2;
		nmpuc = new NewMovementPopUpControler(va,nmpu,activeUser);
		empuc = new EditMovementPopUpControler(va,empu,activeUser);
		vmpuc = new ViewMovementPopUpControler(vmpu);
		
		nmpu.registraControlador(nmpuc);
		empu.registrarControlador(empuc);
		vmpu.registrarControlador(vmpuc);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		switch (command) {
			case "New Transaction":
				nmpu.setContactsModel(activeUser.getContactList());
				nmpu.setVisible(true);
		
			break;
			
			case "Delete Transaction":
				Movimiento m = va.getSelectedMovement();
				Integer movID = m.getIdentificador();
				
				activeUser.DeleteTransaction(movID);
				
				va.SetMovements(activeUser.getMovementsList());
			break;
			
			case "Edit Transaction":
				empu.StartView(va.getSelectedMovement(),activeUser);
				empu.setVisible(true);
			break;
			
			case "View Transaction":
				vmpu.setVisible(true);
				vmpu.SetView(va.getSelectedMovement());
			break;
			
			case "Go Filter":
				String mainFilter = va.getMainFilter();
				String subFilter = va.getSubFilter();
				va.SetMovements(activeUser.filterby(mainFilter,subFilter));
				LastFilter = subFilter;
				va.clearFilters();
			break;
			
			case "Generate Bill":
				if(va.getSelectedMovement() == null) {
					JDialog errorMsg = new CommonErrorPopUp("No Transaction Selected, Please choose One");
					errorMsg.setVisible(true);
				}else {
					Movimiento mov = va.getSelectedMovement();
					
					int  err = BillMaker.generateBill(mov,activeUser);
					
					if(err == 0) {
						JDialog errorMsg = new CommonErrorPopUp("Bill Successfully Generated");
						errorMsg.setLocationRelativeTo(va);
						errorMsg.setVisible(true);
					}else {
						JDialog errorMsg = new CommonErrorPopUp("Bill Generation Aborted");
						errorMsg.setLocationRelativeTo(va);
						errorMsg.setVisible(true);
					}
				}
			break;
			
			case "Export Excel":
				Object[] ExcelInfo = va.getExcelInfo();
				ExcelInfo[1] = LastFilter;
				try {
					ExcelMaker.generateExcelFile(ExcelInfo,activeUser);
					JDialog successMsg = new CommonErrorPopUp("Document Successfully Generated");
					successMsg.setLocationRelativeTo(va);
					successMsg.setVisible(true);
				}catch(EmptyInfoException eie) {
					JDialog errorMsg = new CommonErrorPopUp("There`s no Movements To Export");
					errorMsg.setLocationRelativeTo(va);
					errorMsg.setVisible(true);
				}
			break;
			
			case "Export Graphics":
				Object[] GraphicInfo = va.getExcelInfo();
				GraphicInfo[1] = LastFilter;
				try {
					GraphicDisplayer.generateGraphicsPdf(GraphicInfo,activeUser);
					JDialog successMsg = new CommonErrorPopUp("Document Successfully Generated");
					successMsg.setLocationRelativeTo(va);
					successMsg.setVisible(true);
				}catch(EmptyInfoException eie) {
					JDialog errorMsg = new CommonErrorPopUp("There`s no Movements To Export");
					errorMsg.setLocationRelativeTo(va);
					errorMsg.setVisible(true);
				}
				
			break;
			
			case "Display Last Months":
				ChartPanel pane = GraphicDisplayer.generateLastMonthsGraphics(activeUser);
				BalanceGraphicsPopUp info = new BalanceGraphicsPopUp(pane);
				info.setLocationRelativeTo(va);
				info.setVisible(true);
			break;
			
			case "Display Descriptive Summary":
				SimpleStatisticsPopUp sspu = new SimpleStatisticsPopUp(activeUser);
				sspu.setLocationRelativeTo(va);
				sspu.setVisible(true);
			break;
			
			case "Display Inference Stats":
				AdvancedStatisticsPopUp aspu = new AdvancedStatisticsPopUp(activeUser);
				aspu.setLocationRelativeTo(va);
				aspu.setVisible(true);
			break;
				
		}
	}
	
}
