package Bridge;

import java.awt.Color;

//ConcreteImplementor in Bridge Pattern
public class RedImplementor implements Implementor{

	@Override
	public Color getColor() {
		System.out.println("RedImplementor : return Red color.");
		return Color.RED;
	}
}
