package Memento;

import AbstractFactory_and_Prototype.StateDiagram;

public class Memento {

	private StateDiagram sd;

	public Memento(StateDiagram sd) {

		this.sd = sd;
		System.out.println("Memento : new memento");
//		printMemento();
	}

	// get memento
	public StateDiagram getState() {

		return sd;
	}


}
