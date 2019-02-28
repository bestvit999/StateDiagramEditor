package AbstractFactory_and_Prototype;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JComponent;

import Memento.Memento;
import Strategy.Export;

public class Uml1StateDiagram extends JComponent implements StateDiagram{
	private ArrayList<DiagramElement> components = new ArrayList<DiagramElement>();
	private String name;
	private static int version = 0; // it present how many memento version
	private static int currentVersion = 0;// a stop for redo & undo
	private Export export;

	public Uml1StateDiagram(String name) {

		this.name = name;

	}

	public void setExportStrategy(Export export) {
		this.export = export;

	}

	public void exportDiagram() {
		this.export.saveDocument();
	}

	public void printStruct(String preStr) {
		// print the structure for state diagram
		System.out.println(preStr + " + " + "StateDiagram component :" + this.getName());

		if (this.components != null) {
			preStr += " ";
			for (DiagramElement c : components) {
				c.printStruct(preStr);
			}
		}
	}

	public void add(DiagramElement de) {

		this.components.add(de);

	}

	public void remove(DiagramElement component) {
		this.components.remove(component);
	}

	public void draw(Graphics g) {
		for (DiagramElement e : components) {
			e.draw(g);
		}
	}

	public ArrayList<DiagramElement> getComponent() {
		return components;
	}

	// the function which lets StateDiagram set clone Arraylist
	public void setComponent(ArrayList<DiagramElement> deList) {

		this.components = deList;
	}

	public String getName() {

		return this.name;
	}

	@Override
	public boolean contains(Point point) {
		return false;
	}

	@Override
	public void setSelected(boolean b) {
	}

	@Override
	public void updatePosition(Point p) {
	}

	@Override
	public Rectangle getRect() {
		return null;
	}

	@Override
	public boolean isSelected() {
		return false;
	}

	@Override
	public Point getLocation() {
		return null;
	}

	// get the StateDiagram history in Memento
	public void restore(Memento stateDiagramMemento) {
		components = (ArrayList<DiagramElement>) stateDiagramMemento.getState().getComponent();
	}

	// create a new Memento with a new StateDiagram
	public Memento saveMemento() {
		System.out.println("store the stateDiagram history to memento");

		// create new StateDiagram to store clone copy
		// create new ArrayList to store clone copy
		Uml1StateDiagram sd = new Uml1StateDiagram(name);
		ArrayList<DiagramElement> adcs = new ArrayList<DiagramElement>();
		adcs = (ArrayList<DiagramElement>) this.components.clone(); // clone
		sd.setComponent(adcs);
		// new StateDiagram set clone copy

		return new Memento(sd);
		// create a memento to store clone StateDiagram
	}

	public void addVersion() {

		version += 1;
		System.out.println("version:" + version);
	}

	public int getVersion() {
		return version;
	}

	public void addCurrentVersion() {
		currentVersion += 1;
	}

	public void minusCurrentVersion() {
		currentVersion -= 1;
	}

	public int getCurrentVersion() {

		return currentVersion;

	}

	@Override
	public void setName(String name) {
	}

	// add component from open file
	public void addComponent(DiagramElement deList) {

		this.components.add(deList);
	}
	
	public  StateDiagram Clone() {
		StateDiagram clonedStateDiagram = null;
        try
        {
        	clonedStateDiagram = (StateDiagram) super.clone(); //specialised clone
        	clonedStateDiagram.setName(getName());
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return clonedStateDiagram;
	}
}
