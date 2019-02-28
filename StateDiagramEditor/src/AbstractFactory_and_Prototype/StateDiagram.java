package AbstractFactory_and_Prototype;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;

import AbstractFactory_and_Prototype.DiagramElement;
import Memento.Memento;
import Strategy.Export;

/*  Composite Component  &  Originator for Memento  */
public interface StateDiagram extends DiagramElement {

	public abstract void setExportStrategy(Export export);

	public abstract void exportDiagram();

	public abstract void add(DiagramElement de);

	public abstract void remove(DiagramElement component);
	
	// get object from StateDiagram's ArrayList
	public abstract ArrayList<DiagramElement> getComponent();

	// the function which lets StateDiagram set clone Arraylist
	public abstract void setComponent(ArrayList<DiagramElement> deList);

	// get the StateDiagram history in Memento
	public abstract void restore(Memento stateDiagramMemento);

	// create a new Memento with a new StateDiagram
	public abstract Memento saveMemento();

	public abstract void addVersion();

	public abstract int getVersion();

	public abstract void addCurrentVersion();

	public abstract void minusCurrentVersion();

	public abstract int getCurrentVersion();

	// add component from open file
	public abstract void addComponent(DiagramElement deList);

	public abstract StateDiagram Clone();
}
