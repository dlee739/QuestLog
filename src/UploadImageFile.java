import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UploadImageFile {

	// Declare Variable
	JFileChooser fileChooser;
	FileNameExtensionFilter filter;

	public UploadImageFile() {
		filter = new FileNameExtensionFilter("JPEG file", new String[] {"jpg", "jpeg"});
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(filter);
		
		
	}
	
	public String readImageDirectory() {
		String directory;
		
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			directory = fileChooser.getSelectedFile().getAbsolutePath();
		} else {
			return null;
		}
		return directory;
	}

	public File getFile() throws Exception {

		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

			// get the file
			File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			return file;

		} else {
			return null;
		}
	}

}