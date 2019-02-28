package State;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import AbstractFactory_and_Prototype.*;
import Controller.Controller;
import Memento.DiagramStatus;

//if you press the transition button, change to this state 

public class AddTransition implements ControllerState {
	// singleton
	private static AddTransition instance = null;
	private DiagramElement p1;
	private DiagramElement p2;
	private Point p;
	private int count = 0;

	private AddTransition() {
	}

	public static synchronized AddTransition getInstance() {
		if (instance == null) {
			return new AddTransition();
		}
		return instance;
	}

	@Override
	public ControllerState blackStateBtnClicked(ActionEvent e, Controller c) {
		System.out.println("AddTransition state : state button clicked, state change to AddState");
		// changing the cursor to crosshair
		c.getView().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		return AddBlackState.getInstance();
	}

	@Override
	public ControllerState blueStateBtnClicked(ActionEvent e, Controller c) {
		System.out.println("AddTransition state : state button clicked, state change to AddState");
		// changing the cursor to crosshair
		c.getView().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		return AddBlueState.getInstance();
	}

	@Override
	public ControllerState redStateBtnClicked(ActionEvent e, Controller c) {
		System.out.println("AddTransition state : state button clicked, state change to AddState");
		// changing the cursor to crosshair
		c.getView().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		return AddRedState.getInstance();
	}

	@Override
	public ControllerState transBtnClicked(ActionEvent e, Controller c) {
		System.out.println("AddTransition state : trans button clicked, state no change");
		// changing the cursor to crosshair
		c.getView().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		return this;
	}

	@Override
	public ControllerState deleteBtnClicked(ActionEvent e, Controller c) {
		System.out.println("AddTransition state : delete button clicked, state changing to TransSourceSelected");
		// changing the cursor to hand
		c.getView().getCanvas().setCursor(new Cursor(Cursor.HAND_CURSOR));
		return TransSourceSelected.getInstance();
	}

	@Override
	public ControllerState mouseClicked(MouseEvent e, StateDiagram sd, Controller c, DiagramStatus ds) {
		return this;
	}

	@Override
	public ControllerState mouseDragged(MouseEvent e, StateDiagram sd, Controller c) {
		return this;
	}

	@Override
	public ControllerState mousePressed(MouseEvent e, StateDiagram sd, Controller c, DiagramStatus ds) {

	
		count += 1;
		for (DiagramElement dc : sd.getComponent()) {
			if (dc.contains(e.getPoint())) {
				p1 = dc;
				System.out.println("trans source point :" + p1.getLocation());
				if (p2 == null) {
					p2 = dc;
					System.out.println("trans: source point :" + p2.getLocation());

				}
			}
		}

		return this;
	}

	@Override
	public ControllerState mouseReleased(MouseEvent e, StateDiagram sd, Controller c, DiagramStatus ds) {

		// when the count is 2, add a transition between two state
		if (count == 2) {

			System.out.println("trans mouse Released");

			// create a dialog to gather name for transition
			JFrame dialog = new JFrame();
			String name = JOptionPane.showInputDialog(dialog, "Enter the Transition's name: ", "Input Dialog",
					JOptionPane.PLAIN_MESSAGE);

			//Uml1Transition transition = new Uml1Transition(name, p1, p2);
				
			Transition transition;
			/*AbstractFactory Uml1Factory = new Uml1Factory("Transition");
			Uml1Factory.setInitTransition(p1, p2);
			transition = Uml1Factory.createTransition("Transition");
			transition.updateDiagramElementPoint(name,p1, p2);	
			*/			
			
			if(c.getSelectVersion().equals("UML Version 1.")) {
				AbstractFactory Uml1Factory = new Uml1Factory("Transition");
				Uml1Factory.setInitTransition(p1, p2);
				transition = Uml1Factory.createTransition("Transition");
				transition.updateDiagramElementPoint(name,p1, p2);
				//BRIDGE PATTERN : setting the abstraction's implementor.
				//In this case, we set diagramElement implementor to RedImplementor.		
				sd.add(transition);
			}else if(c.getSelectVersion().equals("UML Version 2.")) {
				AbstractFactory Uml2Factory = new Uml2Factory("Transition");
				Uml2Factory.setInitTransition(p1, p2);
				transition = Uml2Factory.createTransition("Transition");
				transition.updateDiagramElementPoint(name,p1, p2);
				//BRIDGE PATTERN : setting the abstraction's implementor.
				//In this case, we set diagramElement implementor to RedImplementor.		
				sd.add(transition);
			}
			
			// when state diagram add a component, the current stateDiagram
			// would be stored
			ds.setMemento(sd.saveMemento());
			// version count
			sd.addVersion();
			sd.addCurrentVersion();

			e.getComponent().repaint();

			count = 0;// reset the count

			return Init.getInstance();
		}

		// changing the cursor to default
		c.getView().getCanvas().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

		return this;
	}

}
