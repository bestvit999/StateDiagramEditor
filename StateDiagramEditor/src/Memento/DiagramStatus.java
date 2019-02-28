package Memento;

import java.util.ArrayList;

import AbstractFactory_and_Prototype.StateDiagram;
import AbstractFactory_and_Prototype.Uml1StateDiagram;

public class DiagramStatus {
	// determine a collection to store the mementos
	private ArrayList<Memento> sdmList = new ArrayList<Memento>();

	// set a memento to ArrayList
	public void setMemento(Memento m) {

		sdmList.add(m);
		System.out.println("DiagramStatus : add memento to caretaker");
		System.out.println("caretaker :" + sdmList.size());
//		printDiagramState();
	}

	// get the version of memento form
	public Memento getMemento(int index) {
		return sdmList.get(index);
	}


	public void undoExecute(StateDiagram sd) {

		// call the StateDigram to do restore() & minusCurrentVersion()
		System.out.println("undo execute");
		sd.restore(this.getMemento(sd.getCurrentVersion() - 1));// back to previous step
		sd.minusCurrentVersion();
	}

	public void redoExecute(StateDiagram sd) {

		// call the StateDigram to do restore() & addCurrentVersion()
		System.out.println("redo execute");
		sd.restore(this.getMemento(sd.getCurrentVersion() + 1));// go to the next step
		sd.addCurrentVersion();

	}

}
