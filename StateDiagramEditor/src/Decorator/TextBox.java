package Decorator;

import java.awt.Component;
import javax.swing.JTextArea;

//ConcreteComponent in Decorator Pattern
public class TextBox implements Components{
//Using Singleton Pattern, let TextBox is only one.
	private static TextBox instance ;
	private Component textArea;
	
	private TextBox() {
		this.textArea = new JTextArea("NOTE: type some description about this State Diagram ...",10,20);
		textArea.setVisible(true);
	}
	
	public static TextBox getInstance() {
		if(instance == null) {
			instance = new TextBox();			
		}
		return instance;
	}
	
	//the real operation is to returning a textArea to gui. 
	@Override
	public Component showTextBox() {
		return textArea;
	}
	
	public Component getTextArea() {
		return textArea;
	}
	
	public void setTextArea(Component textArea) {
		this.textArea = textArea;
	}
	
}
