package Strategy;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import AbstractFactory_and_Prototype.StateDiagram;

public class DiagramPanel extends JPanel {

	private StateDiagram sd;

	public DiagramPanel(StateDiagram sd) {
		this.sd = sd;
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, Color.white, null, null));
		setBounds(150, 50, 700, 500);
		setBackground(Color.white);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// draw component at panel
		sd.draw(g);
	}

	public JPanel getPanel() {
		return this;
	}
}

