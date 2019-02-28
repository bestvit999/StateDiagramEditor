package Bridge;

import java.awt.Color;

//ConcreteImplementor in Bridge Pattern
public class BlueImplementor implements Implementor{

	@Override
	public Color getColor() {
		System.out.println("BlueImplementor : return Blue color.");
		return Color.BLUE;
	}
	
	
}
