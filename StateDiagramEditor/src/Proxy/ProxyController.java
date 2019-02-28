package Proxy;

import javax.swing.JDialog;

import AbstractFactory_and_Prototype.AbstractFactory;
import AbstractFactory_and_Prototype.StateDiagram;
import AbstractFactory_and_Prototype.Uml1Factory;
import AbstractFactory_and_Prototype.Uml2Factory;
import Controller.Controller;
import GUI.LogginView;

//Proxy in Proxy Pattern
public class ProxyController implements IController {

	private Controller c = new Controller();
	private LogginView dialog;
	private final String account = "OOSE";
	private final String password = "PASS";
	//Verify the user's privilege, if valid then set "validation" to true.
	private boolean validation = false;
	private String selectVerion;

	public ProxyController() {
		dialog = new LogginView(account, password, this);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	
	//for Abstract Factory
	public void setSelectVerion(String selectVerion) {
		this.selectVerion = selectVerion;
	}

	
	@Override
	public void startUp() {
		// [validation == true] Delegate to real Controller to start up the all system when user successfully log in.
		if (isValid()) {
			System.out.println("ProxyController : Successfully loggin.");
			// Delegate to realObject to startUp.
			c.setSelectVerion(selectVerion);
			c.startUp();
		} else {
		// [validation == false] stay in LogginView.
			System.out.println("Proxy Controller : You have no privilege to access the StateDiagramEditor.");
		}
	}

	public void setValidation(boolean validation) {
		this.validation = validation;
	}
	
	public boolean isValid() {
		return validation;
	}
}
