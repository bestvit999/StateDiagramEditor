package Command;

import AbstractFactory_and_Prototype.StateDiagram;
import Memento.DiagramStatus;

public class UndoCommand implements Command{

	private StateDiagram sd;
	private DiagramStatus ds;
	
	public UndoCommand(StateDiagram sd,DiagramStatus ds) {
		
		this.sd = sd;
		this.ds = ds;
		
	}

	@Override
	public void execute() {
		
		System.out.println("undo execute");
		this.sd.restore(ds.getMemento(sd.getCurrentVersion()-1));
		sd.minusCurrentVersion();
		
	}
	
}
