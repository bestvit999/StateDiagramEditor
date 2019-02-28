package AbstractFactory_and_Prototype;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import Bridge.Implementor;


/*  Refined Abstraction in Bridge Pattern  */
public class Uml2State extends State {
	private String name;
	private Point p;
	private final int r = 35;
	private Rectangle b = new Rectangle();
	private boolean selected = false;
	public Uml2State(String name, Point p) {

		this.name = name;
		this.p = p;

		setBoundary(b);

	}

	public void printStruct(String preStr) {

		System.out.println(" " + preStr + " - " + "State component : "
				+ getName());
	}

	public void draw(Graphics g) {

		Graphics2D g1 = (Graphics2D) g;
		
		//Bridge Pattern : Invoke the Implementor to implement the ColorChoose method.
		g1.setColor(impl.getColor());
		g1.setStroke(new BasicStroke(2));
		g1.drawRoundRect(this.getRect().x, this.getRect().y, this.getRect().width, this.getRect().height,50,50);
		g1.drawString(this.getName(), this.getRect().x + 20, this.getRect().y + 35);

		// if the state be selected, display a selection
		if (this.isSelected()) {
			g1.setColor(Color.darkGray);
			g1.drawRect(this.getRect().x, this.getRect().y,
					this.getRect().width, this.getRect().height);
		}

	}

	public boolean intersect(Point p) {

		return true;
	}

	private void setBoundary(Rectangle b) {
		// set the selection boundary
		b.setBounds(p.x - r, p.y - r, 2 * r, 2 * r);
	}

	@Override
	public void updatePosition(Point p) {
		this.p.x = p.x;
		this.p.y = p.y;
		this.setBoundary(this.b);
	}

	public Point getLocation() {
		return p;
	}

	@Override
	public boolean contains(Point p) {
		return b.contains(p);
	}

	/**
	 * Return true if this node is selected.
	 */
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getName() {

		return this.name;
	}

	public Rectangle getRect() {

		return b;
	}

	@Override
	public void setName(String name) {
		this.name = name;

	}

	public Point getPoint() {
		return p;
	}
	
	public State Clone() {
		State clonedState = null;
		try {
			clonedState = (State) super.clone(); // specialised clone
			clonedState.setName(getName());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clonedState;
	}
}
