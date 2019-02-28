package Strategy;

import java.io.File;

import javax.swing.JFileChooser;

public abstract class Export {

	FileExport fileexport;

	public void setExport(FileExport fee) {
		fileexport = fee;
	}

	private File file;

	public String pickLocation() {

		JFileChooser chooseDirec = new JFileChooser();
		chooseDirec.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooseDirec.showSaveDialog(null);

		file = chooseDirec.getSelectedFile();
		return file.toString();
	}

	public abstract void saveDocument();

}


