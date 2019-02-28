package Decorator;

import java.awt.Component;

//Decorator in Decorator Pattern
public class Decorator implements Components{

	//aggregate the Components interface to keep a reference.
	protected Components component;
	
	
	public Decorator(Components component) {
		this.component = component;
	}
	
	//invoke the original operation in component.
	@Override
	public Component showTextBox() {
		return component.showTextBox();
	}
}
