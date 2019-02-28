package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.Controller;

public class FontSizeListener implements ActionListener {

	private Controller c;

	public FontSizeListener(Controller c) {
		this.c = c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		c.fontSizeButtonClicked(e);
	}

}
