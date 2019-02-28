package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.Controller;

public class RedStateButtonListener implements ActionListener {

	private Controller c;

	public RedStateButtonListener(Controller c) {
		this.c = c;
	}

	public void actionPerformed(ActionEvent e) {
		c.redStateBtnClicked(e);
	}
}
