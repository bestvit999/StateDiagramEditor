package GUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Decorator.Components;
import Decorator.ScrollerDecorator;
import Decorator.TextBox;

public class View extends JFrame {

	public JFrame frame;

	private JPanel contentPanel;
	private JButton saveMemento;
	private JPanel canvas;
	private JButton stateButton_black;
	private JButton stateButton_red;
	private JButton stateButton_blue;
	private JButton transButton;
	private JButton undoButton;
	private JButton redoButton;
	private JButton deleteButton;
	private JMenuBar tool_bar;
	private JMenu file;
	private JMenuItem importfile;
	private JMenuItem exportfile;
	private JMenuItem exportpic;
	private JSeparator separator;
	private JMenu edit;
	private JMenuItem undo;
	private JMenuItem redo;
	private JMenu textmenu;
	private JMenuItem fontSizeMenuItem;
	private JToolBar toolBar;
	private JButton openfileButton;
	private JButton exportfileButton;
	private JButton exportpicButton;
	private JButton fontSizeButton;
	private Component textArea;

	public View() {

		this.frame = new JFrame("StateDiagramEditor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setTitle("StateDiagramEditor");
		frame.setBounds(100, 100, 700, 700);

		tool_bar = new JMenuBar();
		frame.setJMenuBar(tool_bar);

		file = new JMenu("File");
		tool_bar.add(file);

		importfile = new JMenuItem("Import File");
		importfile.setIcon(new ImageIcon("img/open.png"));
		file.add(importfile);

		separator = new JSeparator();
		file.add(separator);

		exportfile = new JMenuItem("Export File");
		exportfile.setIcon(new ImageIcon("img/save.png"));
		file.add(exportfile);

		exportpic = new JMenuItem("Export Pic");
		exportpic.setIcon(new ImageIcon("img/picture.png"));
		file.add(exportpic);

		edit = new JMenu("Edit");
		tool_bar.add(edit);

		undo = new JMenuItem("Undo");
		undo.setIcon(new ImageIcon("img/undo.png"));
		edit.add(undo);

		redo = new JMenuItem("Redo");
		redo.setIcon(new ImageIcon("img/redo.png"));
		edit.add(redo);

		textmenu = new JMenu("Text");
		tool_bar.add(textmenu);

		fontSizeMenuItem = new JMenuItem("Font and Size");
		fontSizeMenuItem.setIcon(new ImageIcon("img/text.png"));

		textmenu.add(fontSizeMenuItem);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));

		canvas = new JPanel();
		canvas.setBorder(null);
		contentPanel.add(canvas, BorderLayout.CENTER);

		// DECORATOR Pattern : first, set the general textArea
		Components textBox = TextBox.getInstance();
		// second, add the ScrollBar to the general textArea
		Components textBoxWithScrollBar = new ScrollerDecorator(textBox);
		// final, show the TextArea
		contentPanel.add(textBoxWithScrollBar.showTextBox(), BorderLayout.SOUTH);

		textArea = TextBox.getInstance().getTextArea();
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		contentPanel.add(toolBar, BorderLayout.NORTH);

		openfileButton = new JButton("");
		openfileButton.setIcon(new ImageIcon("img/open.png"));
		toolBar.add(openfileButton);

		exportfileButton = new JButton("");
		exportfileButton.setIcon(new ImageIcon("img/export.png"));
		toolBar.add(exportfileButton);

		exportpicButton = new JButton("");
		exportpicButton.setIcon(new ImageIcon("img/picture.png"));
		toolBar.add(exportpicButton);

		fontSizeButton = new JButton("");
		fontSizeButton.setIcon(new ImageIcon("img/text.png"));
		toolBar.add(fontSizeButton);

		undoButton = new JButton("");
		toolBar.add(undoButton);
		undoButton.setIcon(new ImageIcon("img/undo.png"));

		redoButton = new JButton("");
		toolBar.add(redoButton);
		redoButton.setIcon(new ImageIcon("img/redo.png"));

		deleteButton = new JButton("");
		toolBar.add(deleteButton);
		deleteButton.setIcon(new ImageIcon("img/delete.png"));

		stateButton_black = new JButton("");
		toolBar.add(stateButton_black);
		stateButton_black.setIcon(new ImageIcon("img/blackcircle.png"));

		stateButton_blue = new JButton("");
		stateButton_blue.setIcon(new ImageIcon("img/bluecircle.png"));
		toolBar.add(stateButton_blue);

		stateButton_red = new JButton("");
		stateButton_red.setIcon(new ImageIcon("img/redcircle.png"));
		toolBar.add(stateButton_red);

		transButton = new JButton("");
		toolBar.add(transButton);
		transButton.setIcon(new ImageIcon("img/arrow.png"));
	}

	public void addExportPicListener(ActionListener listenForCalcButton) {
		exportpic.addActionListener(listenForCalcButton);
		exportpicButton.addActionListener(listenForCalcButton);
	}

	public void addExportFileListener(ActionListener listenForCalcButton) {
		exportfile.addActionListener(listenForCalcButton);
		exportfileButton.addActionListener(listenForCalcButton);
	}

	public void addImportFileListener(ActionListener listenForCalcButton) {
		importfile.addActionListener(listenForCalcButton);
		openfileButton.addActionListener(listenForCalcButton);
	}

	public void addundoListener(ActionListener listenForCalcButton) {
		undo.addActionListener(listenForCalcButton);
		undoButton.addActionListener(listenForCalcButton);
	}

	public void addredoListener(ActionListener listenForCalcButton) {
		redo.addActionListener(listenForCalcButton);
		redoButton.addActionListener(listenForCalcButton);
	}

	public void addBlackStateListener(ActionListener listenForCalcButton) {

		stateButton_black.addActionListener(listenForCalcButton);
	}

	public void addBlueStateListener(ActionListener listenForCalcButton) {

		stateButton_blue.addActionListener(listenForCalcButton);
	}

	public void addRedStateListener(ActionListener listenForCalcButton) {

		stateButton_red.addActionListener(listenForCalcButton);
	}

	public void addTransListener(ActionListener listenForCalcButton) {

		transButton.addActionListener(listenForCalcButton);
	}

	public void addDeleteListener(ActionListener listenForCalcButton) {
		deleteButton.addActionListener(listenForCalcButton);
	}

	public void addFontSizeListener(ActionListener listenForCalcButton) {

		fontSizeMenuItem.addActionListener(listenForCalcButton);
		fontSizeButton.addActionListener(listenForCalcButton);
	}

	public void addSaveMementoListener(ActionListener listenForCalcButton) {

		saveMemento.addActionListener(listenForCalcButton);
	}

	public void addMouseClickedListener(MouseListener listenForMouse) {

		canvas.addMouseListener(listenForMouse);

	}

	public void addMouseDraggedListener(MouseListener listenForMouse) {

		canvas.addMouseListener(listenForMouse);

	}

	public void addMousePressedListener(MouseListener listenForMouse) {

		canvas.addMouseListener(listenForMouse);

	}

	public void addMouseReleasedListener(MouseListener listenForMouse) {

		canvas.addMouseListener(listenForMouse);

	}

	public JPanel getCanvas() {
		return canvas;
	}

	public JButton getUndoButton() {
		return undoButton;
	}

	public JButton getRedoButton() {
		return redoButton;
	}

	public Component getTextArea() {
		return textArea;
	}

	public JMenuItem getFontSizeMenuItem() {
		return fontSizeMenuItem;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}
}
