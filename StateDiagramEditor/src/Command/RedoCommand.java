package Command;

import AbstractFactory_and_Prototype.StateDiagram;
import Memento.DiagramStatus;

public class RedoCommand implements Command{

	private StateDiagram sd;
	private DiagramStatus ds;
	
	//constructor
	public RedoCommand(StateDiagram sd,DiagramStatus ds) {

		this.sd = sd;
		this.ds = ds;
	}

	@Override
	public void execute() {
		
		//call the StateDigram to do setMemento() & addCurrentVersion()
		System.out.println("redo execute");
		this.sd.restore(ds.getMemento(sd.getCurrentVersion()+1));
		sd.addCurrentVersion();
		
	}

}

