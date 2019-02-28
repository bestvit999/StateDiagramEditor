package State;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import AbstractFactory_and_Prototype.AbstractFactory;
import AbstractFactory_and_Prototype.DiagramElement;
import AbstractFactory_and_Prototype.State;
import AbstractFactory_and_Prototype.StateDiagram;
import AbstractFactory_and_Prototype.Uml1Factory;
import AbstractFactory_and_Prototype.Uml1State;
import AbstractFactory_and_Prototype.Uml1StateDiagram;
import AbstractFactory_and_Prototype.Uml2Factory;
import Bridge.BlackImplementor;
import Bridge.BlueImplementor;
import Controller.Controller;
import Memento.DiagramStatus;

//if you press the state button, change to this state 

public class AddBlackState implements ControllerState {
	// singleton
	private static AddBlackState instance = null;

	private AddBlackState() {
	}

	public static synchronized AddBlackState getInstance() {
		if (instance == null) {
			return new AddBlackState();
		}
		return instance;
	}

	@Override
	public ControllerState blackStateBtnClicked(ActionEvent e, Controller c) {
		System.out.println("AddBlackState state : state button clicked, state not change");
		// changing the cursor to crosshair
		c.getView().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		return this;
	}

	@Override
	public ControllerState blueStateBtnClicked(ActionEvent e, Controller c) {
		System.out.println("AddBlackState state : state button clicked, state not change");
		// changing the cursor to crosshair
		c.getView().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		return AddBlueState.getInstance();
	}

	@Override
	public ControllerState redStateBtnClicked(ActionEvent e, Controller c) {
		System.out.println("AddBlackState state : state button clicked, state not change");
		// changing the cursor to crosshair
		c.getView().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		return AddRedState.getInstance();
	}

	@Override
	public ControllerState transBtnClicked(ActionEvent e, Controller c) {
		System.out.println("AddBlackState state : transition button clicked, state changing to AddTransition");
		// changing the cursor to crosshair
		c.getView().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		return AddTransition.getInstance();
	}

	@Override
	public ControllerState deleteBtnClicked(ActionEvent e, Controller c) {
		System.out.println("AddBlackState state : delete button clicked, state changing to TransSourceSelected");
		c.getView().getCanvas().setCursor(new Cursor(Cursor.HAND_CURSOR));
		return TransSourceSelected.getInstance();
	}

	@Override
	public ControllerState mouseClicked(MouseEvent e, StateDiagram sd, Controller c, DiagramStatus ds) {
		System.out.println(
				"AddBlackState state : mouse Clicked, Create a state at [X=" + e.getX() + ",Y=" + e.getY() + "]");

		// add a State component to stateDiagram
		State state;
		
		if(c.getSelectVersion().equals("UML Version 1.")) {
			AbstractFactory Uml1Factory = new Uml1Factory("State");
			state = Uml1Factory.createState("State");
			state.updatePosition(e.getPoint());
			// BRIDGE PATTERN : setting the abstraction's implementor.
			// In this case, we set diagramElement implementor to BlackImplementor.
			state.setImpl(new BlackImplementor());
			sd.add(state);
		}else if(c.getSelectVersion().equals("UML Version 2.")) {
			AbstractFactory Uml2Factory = new Uml2Factory("State");
			state = Uml2Factory.createState("State");
			state.updatePosition(e.getPoint());
			// BRIDGE PATTERN : setting the abstraction's implementor.
			// In this case, we set diagramElement implementor to BlackImplementor.
			state.setImpl(new BlackImplementor());
			sd.add(state);
		}

		
		// changing cursor to default
		c.getView().getCanvas().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		// when state diagram add a component, the current stateDiagram would be
		// stored
		ds.setMemento(sd.saveMemento());
		// version count + 1
		sd.addVersion();
		sd.addCurrentVersion();

		return Init.getInstance();
	}

	@Override
	public ControllerState mouseDragged(MouseEvent e, StateDiagram sd, Controller c) {
		return Init.getInstance();
	}

	@Override
	public ControllerState mousePressed(MouseEvent e, StateDiagram de, Controller c, DiagramStatus ds) {
		System.out.println("AddBlackState state : mouse pressed, state not change");
		// changing cursor to crosshair
		c.getView().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		return this;
	}

	@Override
	public ControllerState mouseReleased(MouseEvent e, StateDiagram sd, Controller c, DiagramStatus ds) {
		System.out.println("AddBlackState state : mouse Released, state not change");
		// changing cursor to default
		c.getView().getCanvas().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		return this;
	}

}
