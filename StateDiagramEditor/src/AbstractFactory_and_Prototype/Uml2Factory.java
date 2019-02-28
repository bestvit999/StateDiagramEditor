package AbstractFactory_and_Prototype;

import java.awt.Point;
import java.util.Hashtable;

public class Uml2Factory implements AbstractFactory{
	private Point point = new Point(100, 100);
	private DiagramElement startPoint; // point one
	private DiagramElement endPoint;// point two
	private boolean transitionOnce = false;
	private boolean stateOnce = false;
	private static Hashtable<String, State> StateMap = new Hashtable<String, State>();
	private static Hashtable<String, Transition> TransitionMap = new Hashtable<String, Transition>();
	private static Hashtable<String, StateDiagram> StateDiagramMap = new Hashtable<String, StateDiagram>();

	public Uml2Factory(String name) {
		if (stateOnce == false) {
		loadCache(name);
		stateOnce = true;
		}
	}

	public State createState(String type) {
		return StateMap.get(type).Clone();
	}

	public Transition createTransition(String type) {
		return TransitionMap.get(type).Clone();
	}

	public StateDiagram createStateDiagram(String type) {
		return StateDiagramMap.get(type).Clone();
	}

	public void loadCache(String name) {

		if (name == "State") {
			State Uml2State = new Uml2State(name, point);
			StateMap.put("State", Uml2State);

		} else if (name == "StateDiagram") {
			StateDiagram Uml2StateDiagram = new Uml2StateDiagram(name);
			StateDiagramMap.put("StateDiagram", Uml2StateDiagram);
		}
	}

	public void loadCacheTransition(String name) {

		Transition Uml2Transition = new Uml2Transition(name, startPoint, endPoint);
		TransitionMap.put("Transition", Uml2Transition);

	}

	public void setInitTransition(DiagramElement p1, DiagramElement p2) {
		this.startPoint = p1;
		this.endPoint = p2;
		if (transitionOnce == false) {
			loadCacheTransition("Transition");
			transitionOnce = true;
			}
	}
}
