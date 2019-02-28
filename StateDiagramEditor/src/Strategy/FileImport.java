package Strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

import AbstractFactory_and_Prototype.DiagramElement;
import AbstractFactory_and_Prototype.StateDiagram;
import AbstractFactory_and_Prototype.Uml1StateDiagram;
import Controller.Controller;

public class FileImport {

	private ObjectInputStream input;

	// template method
	public void openDocument(StateDiagram sd, Controller c) {

		File fileName = getFileName(c);
		loadFile(fileName);
		loadElementsFromFile(sd);
		closeFile();

	}

	public File getFileName(Controller c) {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setCurrentDirectory(new File("/Users/stevevitali/Desktop"));

		int result = fileChooser.showOpenDialog(c);

		File fileName = fileChooser.getSelectedFile();

		return fileName;

	}

	public void loadFile(File fileName) {

		try {
			input = new ObjectInputStream(new FileInputStream(fileName));
		} catch (IOException ioException) {
			System.err.println("Error loading file: " + fileName);
			return;
		}

	}

	public void loadElementsFromFile(StateDiagram sd) {

		try {
			sd.getComponent().clear();
			while (true) {
				sd.addComponent((DiagramElement) input.readObject());
			}
		} catch (IOException exception) {
			return;
		} catch (ClassNotFoundException classNotFoundException) {
			System.err.println("Unable to create object.");
		}

	}

	public void closeFile() {

		try {
			if (input != null)
				input.close();
		} catch (IOException exception) {
			System.err.println("Error closing file");
			System.exit(1);
		}

	}

}
