package Strategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import AbstractFactory_and_Prototype.DiagramElement;
import AbstractFactory_and_Prototype.StateDiagram;
import AbstractFactory_and_Prototype.Uml1StateDiagram;

public class FileExport extends Export {

	private ObjectOutputStream output;
	private StateDiagram sd;

	public FileExport(StateDiagram sd) {
		this.sd = sd;
	}

	public void saveDocument() {

		try {
			String FileName = super.pickLocation();
			FileName = FileName + ".oose";
			
			File file = new File(FileName);
			// 123123+ .oose= /folder/123123.oose = string;
			
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			bufferedWriter.close();
			// 123123.oose in directory, empty

			exportFile(file);
			writeSketchToFile(sd);
			closeFile();
		} catch (IOException exception) {
			System.err.println("Error saving to new file.");
		}

	}

	public void exportFile(File fileName) {

		try {
			output = new ObjectOutputStream(new FileOutputStream(fileName));
		} catch (IOException ioException) {
			System.err.println("Error loading file: " + fileName);
			return;
		}

	}

	public void writeSketchToFile(StateDiagram sd) {
		// write the component in file
		try {
			for (int i = 0; i < sd.getComponent().size(); i++) {
				DiagramElement elem = sd.getComponent().get(i);
				output.writeObject(elem);
			}
		} catch (IOException exception) {
			System.err.println("Error writing to file.");
			return;
		}
	}

	public void closeFile() {

		try {
			if (output != null)
				output.close();
		} catch (IOException exception) {
			System.err.println("Error closing file");
			System.exit(1);
		}

	}

}
