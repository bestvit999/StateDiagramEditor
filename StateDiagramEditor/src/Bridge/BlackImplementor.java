package Bridge;

import java.awt.Color;

//ConcreteImplementor in Bridge Pattern
public class BlackImplementor implements Implementor{

	@Override
	public Color getColor() {
		System.out.println("BlackImplementor : return Black color.");
		return Color.BLACK;
	}
	
}
