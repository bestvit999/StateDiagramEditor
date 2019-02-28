package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.Controller;

public class BlackStateButtonListener implements ActionListener {

	private Controller c;

	public BlackStateButtonListener(Controller c) {

		this.c = c;

	}

	public void actionPerformed(ActionEvent e) {

		c.blackStateBtnClicked(e);

	}
}
