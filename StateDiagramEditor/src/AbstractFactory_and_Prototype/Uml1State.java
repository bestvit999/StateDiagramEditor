package AbstractFactory_and_Prototype;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/*  Refined Abstraction in Bridge Pattern  */
public class Uml1State extends State{
	private String name;
	private Point p;
	private final int r = 35;
	private Rectangle b = new Rectangle();
	private boolean selected = false;
	public Uml1State(String name, Point p) {

		this.name = name;
		this.p = p;
		setBoundary(b);

	}

	public void printStruct(String preStr) {

		System.out.println(" " + preStr + " - " + "State component : " + getName());
	}

	public void draw(Graphics g) {
		//Bridge Pattern : Invoke the Implementor to implement the ColorChoose method.
		g.setColor(impl.getColor());
		g.drawOval(this.getRect().x, this.getRect().y, this.getRect().width, this.getRect().height);
		g.drawString(this.getName(), this.getRect().x + 20, this.getRect().y + 35);

		// if the state be selected, display a selection
		if (this.isSelected()) {
			g.setColor(Color.darkGray);
			g.drawRect(this.getRect().x, this.getRect().y, this.getRect().width, this.getRect().height);
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
	
	public  State Clone() {
		State clonedState = null;
        try
        {
        	clonedState = (State) super.clone(); //specialised clone
        	clonedState.setName(getName());
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return clonedState;
	}
}
