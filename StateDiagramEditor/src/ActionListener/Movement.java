package ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Controller.Controller;

public class Movement implements MouseListener, MouseMotionListener {

	private Controller c;

	public Movement(Controller c) {
		this.c = c;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		c.mouseClicked(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		c.mouseDragged(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		c.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		c.mouseReleased(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
