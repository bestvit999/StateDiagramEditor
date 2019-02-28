package State;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import AbstractFactory_and_Prototype.DiagramElement;
import AbstractFactory_and_Prototype.StateDiagram;
import Controller.Controller;
import Memento.DiagramStatus;

//initial state

public class Init implements ControllerState {
	// singleton
	private static Init instance = null;

	private Init() {
	}

	public static synchronized Init getInstance() {
		if (instance == null) {
			instance = new Init();
		}
		return instance;
	}

	@Override
	public ControllerState blackStateBtnClicked(ActionEvent e, Controller c) {
		System.out.println("Init state : Now is preparing to draw a state, Changing the pointer to crosshair.");
		System.out.println("Init state : state is Changing to AddState");

		// changing the cursor to crosshair
		c.getView().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		return AddBlackState.getInstance();
	}

	@Override
	public ControllerState blueStateBtnClicked(ActionEvent e, Controller c) {
		System.out.println("Init state : Now is preparing to draw a state, Changing the pointer to crosshair.");
		System.out.println("Init state : state is Changing to AddState");

		// changing the cursor to crosshair
		c.getView().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		return AddBlueState.getInstance();
	}

	@Override
	public ControllerState redStateBtnClicked(ActionEvent e, Controller c) {
		System.out.println("Init state : Now is preparing to draw a state, Changing the pointer to crosshair.");
		System.out.println("Init state : state is Changing to AddState");

		// changing the cursor to crosshair
		c.getView().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		return AddRedState.getInstance();
	}

	@Override
	public ControllerState transBtnClicked(ActionEvent e, Controller c) {
		System.out.println("Init state : Now is preparing to draw a transition, Changing the pointer to crosshair.");
		System.out.println("Init state : state is Changing to AddTransition");

		// changing the cursor to crosshair
		c.getView().getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		return AddTransition.getInstance();

	}

	@Override
	public ControllerState deleteBtnClicked(ActionEvent e, Controller c) {
		System.out.println("Init state : state is Changing to TransSourceSelected");
		c.getView().getCanvas().setCursor(new Cursor(Cursor.HAND_CURSOR));
		return TransSourceSelected.getInstance();

	}

	@Override
	public ControllerState mouseClicked(MouseEvent e, StateDiagram sd, Controller c, DiagramStatus ds) {
		System.out.println("init state : mouse clicked, state not change");

		for (DiagramElement dc : sd.getComponent()) {
			// if pointer inside the component's range, then pop up a dialog to edit State
			// name
			if (dc.contains(e.getPoint())) {

				// changing the cursor to default
				c.getView().getCanvas().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

				JFrame dialog = new JFrame();
				String name = JOptionPane.showInputDialog(dialog, "Enter the State's name: ", "Input Dialog",
						JOptionPane.PLAIN_MESSAGE);

				if (name == null) {
					dc.setName("name");
				} else {
					dc.setName(name);
				}

			}
		}

		return this;
	}

	@Override
	public ControllerState mouseDragged(MouseEvent e, StateDiagram sd, Controller c) {
		System.out.println("init state : mouse Pressed, state not change");

		// when state be dragged, update the position for state
		for (DiagramElement d : sd.getComponent()) {
			// pointer need to inside the state, and state is be selected
			// pre-condition : to avoid dragged all the component at same time
			if (d.contains(e.getPoint()) && d.isSelected() == true) {
				// changing the cursor to Move
				c.getView().getCanvas().setCursor(new Cursor(Cursor.MOVE_CURSOR));
				d.updatePosition(e.getPoint());
				e.getComponent().repaint();
			}
		}

		return this;
	}

	@Override
	public ControllerState mousePressed(MouseEvent e, StateDiagram sd, Controller c, DiagramStatus ds) {
		System.out.println("init state : mouse Pressed, state not change");
		for (DiagramElement d : sd.getComponent()) {
			// if state be pressed, display the being selected component
			if (d.contains(e.getPoint())) {
				// changing the cursor to hand_mode
				c.getView().getCanvas().setCursor(new Cursor(Cursor.HAND_CURSOR));
				d.setSelected(true);
			} else {
				// changing the cursor to default
				c.getView().getCanvas().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				d.setSelected(false);
			}
		}
		return this;
	}

	@Override
	public ControllerState mouseReleased(MouseEvent e, StateDiagram sd, Controller c, DiagramStatus ds) {
		System.out.println("init state : mouse Released, state not change");
		// changing the cursor to default
		c.getView().getCanvas().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		return this;
	}

}
