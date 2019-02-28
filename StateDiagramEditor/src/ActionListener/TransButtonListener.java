package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.Controller;

public class TransButtonListener implements ActionListener {

	private Controller c;

	public TransButtonListener(Controller c) {

		this.c = c;

	}

	public void actionPerformed(ActionEvent e) {

		c.transBtnClicked(e);

	}

}
