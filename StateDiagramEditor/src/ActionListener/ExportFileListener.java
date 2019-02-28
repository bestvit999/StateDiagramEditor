package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.Controller;

public class ExportFileListener implements ActionListener {

	private Controller c;

	public ExportFileListener(Controller c) {

		this.c = c;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		c.exportFileBtnClicked();

	}

}
