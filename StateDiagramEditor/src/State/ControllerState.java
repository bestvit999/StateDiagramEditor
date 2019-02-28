package State;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import AbstractFactory_and_Prototype.StateDiagram;
import Controller.Controller;
import Memento.*;

public interface ControllerState {
	public abstract ControllerState blackStateBtnClicked(ActionEvent e, Controller c);

	public abstract ControllerState blueStateBtnClicked(ActionEvent e, Controller c);

	public abstract ControllerState redStateBtnClicked(ActionEvent e, Controller c);

	public abstract ControllerState transBtnClicked(ActionEvent e, Controller c);

	public abstract ControllerState deleteBtnClicked(ActionEvent e, Controller c);

	public abstract ControllerState mouseClicked(MouseEvent e, StateDiagram sd, Controller c, DiagramStatus ds);

	public abstract ControllerState mouseDragged(MouseEvent e, StateDiagram sd, Controller c);

	public abstract ControllerState mousePressed(MouseEvent e, StateDiagram sd, Controller c, DiagramStatus ds);

	public abstract ControllerState mouseReleased(MouseEvent e, StateDiagram sd, Controller c, DiagramStatus ds);

}

