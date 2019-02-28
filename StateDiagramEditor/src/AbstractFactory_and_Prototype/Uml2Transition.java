package AbstractFactory_and_Prototype;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;

import javax.swing.JComponent;

public class Uml2Transition extends JComponent implements Transition {
	private String name;
	private DiagramElement startPoint; // point one
	private DiagramElement endPoint; // point two
	private Point n1;
	private Point n2;
	private Rectangle b = new Rectangle(); // set boundary
	private boolean selected = false;
	private static int barb = 20; // barb length
	private static double phi = Math.PI / 6; // pi/6 =30 degree

	public Uml2Transition(String name, DiagramElement p1, DiagramElement p2) {

		this.name = name;
		this.startPoint = p1;
		this.endPoint = p2;
		setBoundary(b);
	}

	public void updateDiagramElementPoint(String name,DiagramElement p1, DiagramElement p2) {
		this.name = name;
		this.startPoint = p1;
		this.endPoint = p2;
	}
	
	public void printStruct(String preStr) {

		System.out.println(" " + preStr + " - " + "Transition component : " + getName());
	}

	public boolean intersect(Point p) {

		return true;
	}

	public void draw(Graphics g) {
		

		/*
		 * Point n1 = startPoint.getLocation(); Point n2 = endPoint.getLocation();
		 */

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		double theta;
		// get the point to calculate the theda
		theta = Math.atan2(n1.y - n2.y, n1.x - n2.x);

		g2.setPaint(Color.RED);
		g2.setStroke(new BasicStroke(2));
		
		// when state drag, control the transition
		if (n1.x > n2.x) {
			g2.draw(new Line2D.Double(n2.x + 30, n2.y, n1.x - 30, n1.y));
			drawArrow(g2, theta, n1.x - 30, n1.y);
			// send the theta and a point to draw an arrow
		} else if (n2.x > n1.x) {

			g2.draw(new Line2D.Double(n2.x - 30, n2.y, n1.x + 30, n1.y));
			drawArrow(g2, theta, n1.x + 30, n1.y);

		} else if ((n1.y < n2.y) && (n2.x > n1.x)) {

			g2.draw(new Line2D.Double(n2.x, n2.y - 30, n1.x, n1.y + 30));
			drawArrow(g2, theta, n1.x, n1.y);

		} else if ((n1.y < n2.y) && (n2.x < n1.x)) {
			g2.draw(new Line2D.Double(n2.x, n2.y - 30, n1.x + 30, n1.y + 30));
			drawArrow(g2, theta, n1.x, n1.y);

		}

		int xm = (n1.x + n2.x) / 2;
		int ym = (n1.y + n2.y) / 2;
		g2.drawString(name, xm, ym);// draw the string on middle point

		if (selected) {
			g.setColor(Color.darkGray);
			g.drawRect(b.x, b.y, b.width, b.height);
		}

		System.out.println("draw line");

	}

	public void setBoundary(Rectangle b) {
		System.out.println(".0.0.0.0.0.0.0"+startPoint);
		this.n1 = startPoint.getLocation();
		this.n2 = endPoint.getLocation();
	}

	public String getName() {
		return this.name;
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public void setSelected(boolean b) {
		this.selected = true;
	}

	@Override
	public Rectangle getRect() {
		return null;
	}

	// the function for draw arrow line
	public void drawArrow(Graphics2D g2, double theta, double x0, double y0) {
		// arrow's bottom point
		double x = x0 - barb * Math.cos(theta + phi);
		double y = y0 - barb * Math.sin(theta + phi);
		g2.draw(new Line2D.Double(x0, y0, x, y));
		x = x0 - barb * Math.cos(theta - phi);
		y = y0 - barb * Math.sin(theta - phi);
		g2.draw(new Line2D.Double(x0, y0, x, y));
	}

	@Override
	public void updatePosition(Point p) {
	}

	@Override
	public void setName(String name) {
	}

	public DiagramElement getStartPoint() {
		return startPoint;
	}

	public DiagramElement getEndPoint() {
		return endPoint;
	}

	@Override
	public boolean contains(Point point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Point getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	public Transition Clone() {
		Transition clonedTransition = null;
		try {
			clonedTransition = (Transition) super.clone(); // specialised clone
			clonedTransition.setName(getName());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clonedTransition;
	}
}
