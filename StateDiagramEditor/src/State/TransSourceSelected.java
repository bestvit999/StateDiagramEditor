package State;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import AbstractFactory_and_Prototype.DiagramElement;
import AbstractFactory_and_Prototype.StateDiagram;
import Controller.Controller;
import Memento.DiagramStatus;

//if you press the delete button, change to this state 
public class TransSourceSelected implements ControllerState {
	// singleton
	private static TransSourceSelected instance = null;

	private TransSourceSelected() {
	}

	public static synchronized TransSourceSelected getInstance() {
		if (instance == null) {
			return new TransSourceSelected();
		}
		return instance;
	}

	@Override
	public ControllerState blackStateBtnClicked(ActionEvent e, Controller c) {
		System.out.println("TransSourceSelected state : state button clicked, state change to AddState");
		// changing the cursor to crosshair
		c.getView().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		return AddBlackState.getInstance();
	}

	@Override
	public ControllerState blueStateBtnClicked(ActionEvent e, Controller c) {
		System.out.println("TransSourceSelected state : state button clicked, state change to AddState");
		// changing the cursor to crosshair
		c.getView().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		return AddBlueState.getInstance();
	}

	@Override
	public ControllerState redStateBtnClicked(ActionEvent e, Controller c) {
		System.out.println("TransSourceSelected state : state button clicked, state change to AddState");
		// changing the cursor to crosshair
		c.getView().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		return AddRedState.getInstance();
	}

	@Override
	public ControllerState transBtnClicked(ActionEvent e, Controller c) {
		System.out.println("TransSourceSelected state : state button clicked, state change to AddState");
		// changing the cursor to crosshair
		c.getView().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		return AddTransition.getInstance();
	}

	@Override
	public ControllerState deleteBtnClicked(ActionEvent e, Controller c) {
		System.out.println("TransSourceSelected state : delete button clicked, state no change");
		c.getView().getCanvas().setCursor(new Cursor(Cursor.HAND_CURSOR));
		return this;
	}

	@Override
	public ControllerState mouseClicked(MouseEvent e, StateDiagram sd, Controller c, DiagramStatus ds) {
		System.out.println("TransSourceSelected state : mouse Pressed, state no change, Delete the Component");
		for (DiagramElement d : sd.getComponent()) {
			if (d.contains(e.getPoint())) {
				sd.remove(d);
				// when state diagram delete a component, the current stateDiagram would be
				// stored
				ds.setMemento(sd.saveMemento());
				// version count
				sd.addVersion();
				sd.addCurrentVersion();
			}
		}

		return Init.getInstance();
	}

	@Override
	public ControllerState mouseDragged(MouseEvent e, StateDiagram sd, Controller c) {
		return Init.getInstance();
	}

	@Override
	public ControllerState mousePressed(MouseEvent e, StateDiagram sd, Controller c, DiagramStatus ds) {
		return this;
	}

	@Override
	public ControllerState mouseReleased(MouseEvent e, StateDiagram sd, Controller c, DiagramStatus ds) {
		// changing the cursor to default
		c.getView().getCanvas().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		return this;
	}

}
