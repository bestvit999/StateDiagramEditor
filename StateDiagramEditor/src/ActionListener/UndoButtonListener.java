package ActionListener;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.Controller;

public class UndoButtonListener implements ActionListener {

	private Controller c;

	public UndoButtonListener(Controller c) {

		this.c = c;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		c.undoBtnClicked(e);
		// changing the cursor to default
		c.getView().getCanvas().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

	}

}
