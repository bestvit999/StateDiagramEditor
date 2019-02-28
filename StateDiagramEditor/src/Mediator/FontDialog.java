package Mediator;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Window;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.ComponentOrientation;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import static javax.swing.JOptionPane.*;
import javax.swing.event.ListSelectionListener;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.AbstractAction;

public class FontDialog extends JDialog {
	// Font Dialog Component
	FontDialog fontDialog;
	JPanel topPanel, bottomPanel;
	JList fontlist, stylelist;
	JComboBox fontsizelist;
	Font currentFont, originalFont;
	JLabel previewLabel;
	int dialogResult = CANCEL_OPTION;
	Action okAction, cancelAction;
	Window owner;
	// Mediator Colleague
	ComboBox sizeCB;
	List flist;
	List slist;
	Label label;
	TextArea ta;
	Button okBT;
	Button cancelBT;

	// constructor
	public FontDialog(Window owner, Font defaultFont) {
		super(owner, "Font dialog", ModalityType.APPLICATION_MODAL);
		setLocationRelativeTo(null);

		originalFont = defaultFont;

		createActions();

		// create the Mediator for fontDialog
		createMediator();

		createUI(defaultFont);

		setupUI(defaultFont);
		this.fontDialog = this;
		this.owner = owner;
		this.pack();
	}

	// create the Dialog Mediator
	private void createMediator() {

		// define the MEDIATOR
		Mediator mediator = new DialogMediator();

		// define the Colleague
		sizeCB = new ComboBox(mediator);
		flist = new List(mediator);
		slist = new List(mediator);
		label = new Label(mediator);
		ta = new TextArea(mediator);
		okBT = new Button(mediator);
		cancelBT = new Button(mediator);

	}

	// ok & cancle button action
	private void createActions() {
		JDialog parent = this;
		okAction = new AbstractAction("Ok") {
			public void actionPerformed(ActionEvent e) {
				dialogResult = OK_OPTION;

				// Using the mediator to set visible to False
				okBT.setFalseVisible(parent);
			}
		};

		cancelAction = new AbstractAction("Cancel") {
			public void actionPerformed(ActionEvent e) {
				dialogResult = CANCEL_OPTION;

				// Using the mediator to set visible to False
				cancelBT.setFalseVisible(parent);
			}
		};
	}

	// set the initial UI
	private void setupUI(Font font) {
		populateFontsList();

		fontlist.setSelectedValue(font.getName(), true);
		stylelist.setSelectedIndex(getFontStyleListIndexFromStyle(font.getStyle()));
		fontsizelist.getEditor().setItem(font.getSize2D() + "");
	}

	private int getFontStyleListIndexFromStyle(int style) {
		if (style == Font.BOLD)
			return 1;
		else if (style == Font.ITALIC)
			return 2;
		else if (style == Font.PLAIN)
			return 0;
		else
			return 0;
	}

	public int getFontStyleFromListIndex(int index) {
		if (index == 1)
			return Font.BOLD;
		else if (index == 2)
			return Font.ITALIC;
		else if (index == Font.PLAIN)
			return Font.PLAIN;
		else
			return Font.PLAIN;
	}

	private void changeCurrentFont(Font font) {
		previewLabel.setFont(font);
		currentFont = font;
	}

	private void changeCurrentFont(String fontName, int style, float size) {
		Font newFont = new Font(fontName, style, (int) size);
		newFont = newFont.deriveFont(size);

		changeCurrentFont(newFont);
	}

	public static Font showDialog(Window owner, Font defaultFont) {

		FontDialog fd = new FontDialog(owner, defaultFont);
		fd.setVisible(true);
		if (fd.getDialogResult() == CANCEL_OPTION)
			return fd.originalFont;
		else
			return fd.currentFont;
	}

	private void populateFontsList() {
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		fontlist.setListData(fonts);

	}

	private void createUI(Font defaultFont) {
		this.setLayout(new BorderLayout(5, 5));
		// ----------CREATE DIFFERENT PANELS----------
		JPanel mainPanel = new JPanel(new BorderLayout(5, 10));

		JPanel footerPanel = new JPanel();
		footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.LINE_AXIS));
		footerPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		JPanel selectionPanel = new JPanel();
		selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.LINE_AXIS));

		JPanel previewPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		previewPanel.setPreferredSize(new Dimension(100, 50));
		previewPanel.setBackground(Color.WHITE);

		JPanel leftpanel = new JPanel();
		leftpanel.setLayout(new BoxLayout(leftpanel, BoxLayout.PAGE_AXIS));

		JPanel centerpanel = new JPanel();
		centerpanel.setLayout(new BoxLayout(centerpanel, BoxLayout.PAGE_AXIS));
		// centerpanel.setPreferredSize(new Dimension(100,150));

		JPanel rightpanel = new JPanel();
		rightpanel.setLayout(new BoxLayout(rightpanel, BoxLayout.PAGE_AXIS));

		// ----------PREVIEW PANEL-------------
		previewLabel = new JLabel("Sample text");
		previewPanel.add(previewLabel);

		// ----------SELECTION PANEL-----------
		selectionPanel.add(Box.createRigidArea(new Dimension(5, 5)));
		selectionPanel.add(leftpanel);
		selectionPanel.add(Box.createRigidArea(new Dimension(5, 5)));
		selectionPanel.add(centerpanel);
		selectionPanel.add(Box.createRigidArea(new Dimension(5, 5)));
		selectionPanel.add(rightpanel);
		selectionPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		// ----------LEFT PANEL------------

		fontlist = new JList();
		fontlist.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				changeCurrentFont((String) fontlist.getSelectedValue(), currentFont.getStyle(),
						currentFont.getSize2D());

				// FontDialog : fontlist is changed, using the mediator to set the
				// PreviewLabel's textFont
				/* debugging */
				System.out.println("FontDialog : fontlist is changed, using the mediator to change the Current Font");
				flist.changeCurrentFont(fontDialog);

			}
		});

		JScrollPane fontsScrollPane = new JScrollPane(fontlist);
		fontsScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);

		leftpanel.add(new JLabel("Fonts:"));
		leftpanel.add(Box.createRigidArea(new Dimension(0, 5)));
		leftpanel.add(fontsScrollPane);

		// ----------CENTER PANEL----------------
		stylelist = new JList(new String[] { "Normal", "Bold", "Italics" });
		stylelist.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				changeCurrentFont(currentFont.getName(), getFontStyleFromListIndex(stylelist.getSelectedIndex()),
						currentFont.getSize2D());

				// FontDialog : stylelist is changed, using the mediator to set the
				// PreviewLabel's textStyle
				/* debugging */
				System.out.println("FontDialog : stylelist is changed, using the mediator to change the Current Style");
				slist.changeCurrentStyle(fontDialog);
			}
		});
		stylelist.setPreferredSize(new Dimension(100, 150));
		JScrollPane stylesScrollPane = new JScrollPane(stylelist);
		stylesScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		stylesScrollPane.setPreferredSize(new Dimension(100, 150));

		centerpanel.add(new JLabel("Styles:"));
		centerpanel.add(Box.createRigidArea(new Dimension(0, 5)));
		centerpanel.add(stylesScrollPane);

		// ----------RIGHT PANEL---------------
		fontsizelist = new JComboBox(new String[] { "1", "3", "10", "11", "12", "15", "20", "25", "30" });
		fontsizelist.setEditable(true);
		fontsizelist.setMaximumSize(new Dimension(100, 20));
		fontsizelist.setAlignmentX(Component.LEFT_ALIGNMENT);
		fontsizelist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// FontDialog : fontsizelist is changed, using the mediator to set
				// PreviewLabel's textSize
				System.out
						.println("FontDialog : fontsizelist is changed, using the mediator to change the Current Size");
				sizeCB.changeCurrentSize(fontDialog);

			}
		});

		rightpanel.add(new JLabel("Size:"));
		rightpanel.add(Box.createRigidArea(new Dimension(0, 5)));
		rightpanel.add(fontsizelist);
		rightpanel.add(Box.createVerticalGlue());

		// ----------MAIN PANEL-----------------
		mainPanel.add(selectionPanel, BorderLayout.CENTER);
		mainPanel.add(previewPanel, BorderLayout.PAGE_END);

		// ----------FOOTER PANEL---------------
		footerPanel.add(new JButton(cancelAction));
		footerPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		footerPanel.add(new JButton(okAction));
		footerPanel.add(Box.createRigidArea(new Dimension(5, 0)));

		// ----------ADD BOTH PANELS-------------
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(footerPanel, BorderLayout.PAGE_END);
		changeCurrentFont(defaultFont);
	}

	// Getter & Setter
	private int getDialogResult() {
		return dialogResult;
	}

	public Font getCurrentFont() {
		return currentFont;
	}

	public JLabel getPreviewLabel() {
		return previewLabel;
	}

	public void setCurrentFont(Font currentFont) {
		this.currentFont = currentFont;
	}

	public FontDialog getFontDialog() {
		return fontDialog;
	}

	public void setFontDialog(FontDialog fontDialog) {
		this.fontDialog = fontDialog;
	}

	public JPanel getTopPanel() {
		return topPanel;
	}

	public void setTopPanel(JPanel topPanel) {
		this.topPanel = topPanel;
	}

	public JPanel getBottomPanel() {
		return bottomPanel;
	}

	public void setBottomPanel(JPanel bottomPanel) {
		this.bottomPanel = bottomPanel;
	}

	public JList getFontlist() {
		return fontlist;
	}

	public void setFontlist(JList fontlist) {
		this.fontlist = fontlist;
	}

	public JList getStylelist() {
		return stylelist;
	}

	public void setStylelist(JList stylelist) {
		this.stylelist = stylelist;
	}

	public JComboBox getFontsizelist() {
		return fontsizelist;
	}

	public void setFontsizelist(JComboBox fontsizelist) {
		this.fontsizelist = fontsizelist;
	}

	public Font getOriginalFont() {
		return originalFont;
	}

	public void setOriginalFont(Font originalFont) {
		this.originalFont = originalFont;
	}

	public Action getOkAction() {
		return okAction;
	}

	public void setOkAction(Action okAction) {
		this.okAction = okAction;
	}

	public Action getCancelAction() {
		return cancelAction;
	}

	public void setCancelAction(Action cancelAction) {
		this.cancelAction = cancelAction;
	}

	public void setPreviewLabel(JLabel previewLabel) {
		this.previewLabel = previewLabel;
	}

	public void setDialogResult(int dialogResult) {
		this.dialogResult = dialogResult;
	}

}
