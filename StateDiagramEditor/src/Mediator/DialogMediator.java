package Mediator;

import java.awt.Font;

public class DialogMediator extends Mediator {

	// the most important method
	@Override
	public void componentsChanged(String str, Object... objects) {

		if (str.equals("Button.setFalseVisible")) {
			this.setFalseVisible((FontDialog) objects[0]);
		} else if (str.equals("Button.setTrueVisible")) {
			this.setTrueVisible((FontDialog) objects[0]);
		} else if (str.equals("List.changeCurrentFont")) {
			this.changeCurrentFont((FontDialog) objects[0]);
		} else if (str.equals("List.changeCurrentStyle")) {
			this.changeCurrentStyle((FontDialog) objects[0]);
		} else if (str.equals("ComboBox.changeCurrentSize")) {
			this.changeCurrentSize((FontDialog) objects[0]);
		}
	}

	private void setTrueVisible(FontDialog dialog) {

		System.out.println("DialogMediator : Using the mediator to set visible to False");
		dialog.setVisible(true);
	}

	private void setFalseVisible(FontDialog dialog) {

		System.out.println("DialogMediator : Using the mediator to set visible to False");
		dialog.setVisible(false);
	}

	/* Change Current Font / Style / Size */
	private void changeCurrentFont(FontDialog dialog) {
		/* debugging */
		/*
		 * Font newFont = new Font((String) dialog.getFontlist().getSelectedValue(),
		 * dialog.getCurrentFont().getStyle(), (int)
		 * dialog.getCurrentFont().getSize2D()); newFont =
		 * newFont.deriveFont(dialog.getCurrentFont().getSize2D());
		 * this.changeDialogCurrentFont(newFont, dialog);
		 */
		System.out.println("DialogMediator : Using the mediator to change Current Font");
	}

	private void changeCurrentStyle(FontDialog dialog) {
		/* debugging */
		/*
		 * Font newFont = new
		 * Font(dialog.getCurrentFont().getName(),dialog.getFontStyleFromListIndex(
		 * dialog.getStylelist().getSelectedIndex()),(int)dialog.getCurrentFont().
		 * getSize2D());
		 * 
		 * newFont = newFont.deriveFont(dialog.getCurrentFont().getSize2D());
		 * this.changeDialogCurrentFont(newFont, dialog);
		 */
		System.out.println("DialogMediator : Using the mediator to change Current Style");

	}

	private void changeCurrentSize(FontDialog dialog) {
		System.out.println("DialogMediator : Using the mediator to change Current Size");

		Font newFont = new Font(dialog.getCurrentFont().getName(), dialog.getCurrentFont().getStyle(),
				(int) Float.parseFloat(dialog.getFontsizelist().getEditor().getItem().toString()));
		newFont = newFont.deriveFont(Float.parseFloat(dialog.getFontsizelist().getEditor().getItem().toString()));

		this.changeDialogCurrentFont(newFont, dialog);

	}

	// set dialog current font
	private void changeDialogCurrentFont(Font font, FontDialog dialog) {
		dialog.getPreviewLabel().setFont(font);
		dialog.setCurrentFont(font);
	}

	// get dialog current Style to change the previewLabel
	private int getFontStyleFromListIndex(int index) {
		if (index == 1)
			return Font.BOLD;
		else if (index == 2)
			return Font.ITALIC;
		else if (index == Font.PLAIN)
			return Font.PLAIN;
		else
			return Font.PLAIN;
	}

}
