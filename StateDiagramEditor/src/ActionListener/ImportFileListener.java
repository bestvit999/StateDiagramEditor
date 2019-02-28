package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.Controller;

public class ImportFileListener implements ActionListener {

	private Controller c;

	public ImportFileListener(Controller c) {

		this.c = c;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		c.importFileBtnClicked();

	}

}
