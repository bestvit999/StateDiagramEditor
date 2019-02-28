package AbstractFactory_and_Prototype;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;

import javax.swing.JComponent;

/*  Leaf Component  */
public interface Transition extends DiagramElement {

	public abstract boolean intersect(Point p);

	public abstract void setBoundary(Rectangle b);

	// the function for draw arrow line
	public abstract void drawArrow(Graphics2D g2, double theta, double x0, double y0);

	public abstract DiagramElement getStartPoint();

	public abstract DiagramElement getEndPoint();

	public abstract Transition Clone();

	public abstract void updateDiagramElementPoint(String name,DiagramElement p1, DiagramElement p2);

}
