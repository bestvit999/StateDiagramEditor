package Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import AbstractFactory_and_Prototype.AbstractFactory;
import AbstractFactory_and_Prototype.DiagramElement;
import AbstractFactory_and_Prototype.StateDiagram;
import AbstractFactory_and_Prototype.Uml1Factory;
import AbstractFactory_and_Prototype.Uml2Factory;
import ActionListener.*;
import Decorator.TextBox;
import GUI.View;
import Mediator.FontDialog;
import Memento.*;
import Proxy.IController;
import State.*;
import Strategy.Export;
import Strategy.FileExport;
import Strategy.FileImport;
import Strategy.PictureExport;


/*  Real Object in Proxy Pattern  */
public class Controller extends JPanel implements IController {

	private View v;
	private DiagramStatus diagramStatus;
	private StateDiagram sd;
	private ControllerState cs;
	private Export fe;
	private FileImport fi = new FileImport();
	private String selectVerion;

	public View getView() {
		return v;
	}

	public Controller() {}

	// when this method be called, the draw panel will paint
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		System.out.println("Controller : paintComponent() method is working");
		for (DiagramElement d : sd.getComponent()) {
			d.draw(g);
		}
		// print message to check
		sd.printStruct("");
		// diagramStatus.printDiagramState();
	}

	@Override
	public Dimension getPreferredSize() {
		System.out.println("Controller : dimesion is work");
		return new Dimension(this.v.getCanvas().getWidth(), this.v.getCanvas().getHeight());
		// return new Dimension();
	}

	// black_stateBtnClicked
	public void blackStateBtnClicked(ActionEvent e) {
		System.out.println("Controller : state Button was clicked, delegate to ControllerState");
		cs = cs.blackStateBtnClicked(e, this);
	}

	// black_stateBtnClicked
	public void blueStateBtnClicked(ActionEvent e) {
		System.out.println("Controller : state Button was clicked, delegate to ControllerState");
		cs = cs.blueStateBtnClicked(e, this);
	}

	// black_stateBtnClicked
	public void redStateBtnClicked(ActionEvent e) {
		System.out.println("Controller : state Button was clicked, delegate to ControllerState");
		cs = cs.redStateBtnClicked(e, this);
	}

	// transition button
	public void transBtnClicked(ActionEvent e) {
		System.out.println("Controller : trans Button was clicked, delegate to ControllerState");
		cs = cs.transBtnClicked(e, this);
	}

	public void deleteBtnClicked(ActionEvent e) {
		System.out.println("Controller : delete Button was clicked, delegate to ControllerState");
		cs = cs.deleteBtnClicked(e, this);
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println("Controller : mouse clicked, delegate to ControllerState");
		cs = cs.mouseClicked(e, sd, this, diagramStatus);
		e.getComponent().repaint();
	}

	public void mouseDragged(MouseEvent e) {
		System.out.println("Controller : mouse Dragged, delegate to ControllerState");
		cs = cs.mouseDragged(e, sd, this);
		e.getComponent().repaint();

	}

	public void mousePressed(MouseEvent e) {
		System.out.println("Controller : mouse Pressed, delegate to ControllerState");
		cs = cs.mousePressed(e, sd, this, diagramStatus);
		e.getComponent().repaint();

	}

	public void mouseReleased(MouseEvent e) {
		System.out.println("Controller : mouse released, delegate to ControllerState");
		cs = cs.mouseReleased(e, sd, this, diagramStatus);
		System.out.println("how many components:" + sd.getComponent().size());
		e.getComponent().repaint();

	}

	public void undoBtnClicked(ActionEvent e) {
		System.out.println("undo was clicked");

		// control the range
		if (1 <= sd.getCurrentVersion()) {
			this.v.getRedoButton().setEnabled(true);
			diagramStatus.undoExecute(sd);
			repaint();
		} else {
			this.v.getUndoButton().setEnabled(false);
			System.out.println("command can't execute");
		}
		repaint();
	}

	public void redoBtnClicked(ActionEvent e) {

		System.out.println("redo was clicked");

		// control the range
		if (sd.getCurrentVersion() < (sd.getVersion())) {
			this.v.getUndoButton().setEnabled(true);
			diagramStatus.redoExecute(sd);
			repaint();
		} else {
			this.v.getRedoButton().setEnabled(false);
			System.out.println("command can't execute");
		}

		repaint();
	}

	public void importFileBtnClicked() {
		// ImportFile
		fi.openDocument(sd, this);
		repaint();

	}

	public void exportFileBtnClicked() {
		// ExportFile
		fe = new FileExport(sd);
		sd.setExportStrategy(fe);
		sd.exportDiagram();

	}

	public void exportPicBtnClicked() {
		// ExportPicture
		fe = new PictureExport(sd);
		sd.setExportStrategy(fe);
		sd.exportDiagram();

	}

	public void fontSizeButtonClicked(ActionEvent e) {
		//this.v.getTextArea().setFont(FontDialog.showDialog(this.v, this.v.getTextArea().getFont()));
		TextBox.getInstance().getTextArea().setFont(FontDialog.showDialog(this.v, this.v.getTextArea().getFont()));
	}
	
	public void setSelectVerion(String selectVerion) {
		this.selectVerion = selectVerion;
	}
	
	public String getSelectVersion() {
		return selectVerion;
	}
	
	
	// the Real Operation in Proxy Pattern
	// Build the whole StateDiagramEditor system.
	@Override
	public void startUp() {
		System.out.println("Controller : Startup.... please wait.");
		View v = new View();
		// set init staus
		ControllerState cs = Init.getInstance();
		DiagramStatus ds = new DiagramStatus();
		if (selectVerion.equals("UML Version 1.")) {
			System.out.println("Controller : setting the system with UML Version 1.");
			StateDiagram sd;
			AbstractFactory uml1Factory = new Uml1Factory("StateDiagram");
			sd = uml1Factory.createStateDiagram("StateDiagram");
			this.sd = sd;
		}else if (selectVerion.equals("UML Version 2.")) {
			System.out.println("Controller : setting the system with UML Version 2.");
			StateDiagram sd;
			AbstractFactory uml2Factory = new Uml2Factory("StateDiagram");
			sd = uml2Factory.createStateDiagram("StateDiagram");
			this.sd = sd;
		}
		
		this.v = v;
		this.cs = cs;
		this.diagramStatus = ds;

		v.frame.setVisible(true);
		
		addAllActionListener();
		diagramStatus.setMemento(sd.saveMemento()); // initial memento
	}

	public void addAllActionListener() {
		// actionListener
		this.v.getCanvas().add(this);
		this.v.addBlackStateListener(new BlackStateButtonListener(this));
		this.v.addBlueStateListener(new BlueStateButtonListener(this));
		this.v.addRedStateListener(new RedStateButtonListener(this));
		this.v.addTransListener(new TransButtonListener(this));
		this.v.addDeleteListener(new DeleteButtonListener(this));
		this.v.addMouseClickedListener(new Movement(this));
		this.v.getCanvas().addMouseMotionListener(new Movement(this));
		// redo & undo addListener
		this.v.addundoListener(new UndoButtonListener(this));
		this.v.addredoListener(new RedoButtonListener(this));
		// font & size listener
		this.v.addFontSizeListener(new FontSizeListener(this));
		this.v.addExportFileListener(new ExportFileListener(this));
		this.v.addImportFileListener(new ImportFileListener(this));
		this.v.addExportPicListener(new ExportPicListener(this));
		
	}

}
