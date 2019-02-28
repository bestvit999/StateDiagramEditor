package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.Controller;

public class BlueStateButtonListener implements ActionListener {

	private Controller c;

	public BlueStateButtonListener(Controller c) {
		this.c = c;
	}

	public void actionPerformed(ActionEvent e) {
		c.blueStateBtnClicked(e);
	}
}
