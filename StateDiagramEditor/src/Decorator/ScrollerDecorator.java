package Decorator;

import java.awt.Component;

import javax.swing.JScrollPane;

//ConcreteDecorator in Decorator Pattern
public class ScrollerDecorator extends Decorator{
	
	public ScrollerDecorator(Components component) {
		super(component);
	}
	
	//the most important method in Decorator.
	public Component showTextBox() {
		//invoke addiction operation
		setScrollBar();
		//then invoke the original operation
		return super.showTextBox();		
	}
	
	// addiction operation
	public void setScrollBar() {
		JScrollPane TextAreaWithScrollBar = new JScrollPane(TextBox.getInstance().getTextArea(),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		TextBox.getInstance().setTextArea(TextAreaWithScrollBar);
	}
}
