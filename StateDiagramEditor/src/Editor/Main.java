package Editor;

import AbstractFactory_and_Prototype.AbstractFactory;
import AbstractFactory_and_Prototype.StateDiagram;
import AbstractFactory_and_Prototype.Uml1Factory;
import AbstractFactory_and_Prototype.Uml1StateDiagram;
import Controller.Controller;
import GUI.*;
import Memento.DiagramStatus;
import Proxy.ProxyController;
import State.ControllerState;
import State.Init;

public class Main {
	public static void main(String[] args) {
		try {
			new ProxyController();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
