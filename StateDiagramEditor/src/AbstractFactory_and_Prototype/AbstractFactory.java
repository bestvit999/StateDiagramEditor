package AbstractFactory_and_Prototype;

import java.awt.Point;

public interface AbstractFactory {
	public abstract State createState(String type);

	public abstract Transition createTransition(String type);

	public abstract StateDiagram createStateDiagram(String type);

	public abstract void setInitTransition(DiagramElement p1, DiagramElement p2);
}
