package AbstractFactory_and_Prototype;

import java.awt.Graphics;

import Bridge.Implementor;

/*  Abstraction in Bridge Pattern  */
public abstract class State implements DiagramElement {
	
	//Bridge : define the Implementor.
	Implementor impl;
	
	public void setImpl(Implementor impl) {
		this.impl = impl;
	};
	
	//Bridge : define the abstract operation
	//in this case, we have a draw method to draw a state.
	public abstract void draw(Graphics g);
	public abstract State Clone();

}
