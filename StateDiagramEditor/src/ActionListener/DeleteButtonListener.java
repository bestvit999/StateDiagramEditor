package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.Controller;

public class DeleteButtonListener implements ActionListener {

	private Controller c;

	public DeleteButtonListener(Controller c) {
		this.c = c;
	}

	public void actionPerformed(ActionEvent e) {
		c.deleteBtnClicked(e);
	}

}
