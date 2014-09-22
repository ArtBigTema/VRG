package vrg;

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class VRGfile {
	public static String coordsFileName = "";

	public static JFileChooser getFileChooser() {
		JFileChooser chooser = new JFileChooser();
		FileFilter ff = new FileNameExtensionFilter(VRGUtils.TXT_FILES, "txt",
				"adb");
		chooser.setFileFilter(ff);
		return chooser;
	}

	public static File openFile(Component parent) {
		JFileChooser chooser = getFileChooser();
		File file = chooser.getSelectedFile();
		int result = chooser.showOpenDialog(parent);
		if (result == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
		}
		return file;
	}

	@SuppressWarnings("resource")
	public static ArrayList<VRGvertexes> readFromFile(File f) {
		ArrayList<VRGvertexes> result = null;
		try {
			Scanner scanner = new Scanner(f);

			int n = VRGUtils.getIntFromText(scanner.nextLine());
			result = new ArrayList<VRGvertexes>(n);

			for (int i = 0; i < n; i++) {
				result.add(new VRGvertexes(scanner.nextLine().split("\\s+")));
			}

		} catch (FileNotFoundException ex) {
			VRGUtils.showErrorMess(null, VRGUtils.MSG_ERR_TITLE,
					"File not found");
		}
		return result;
	}

	public static ArrayList<VRGvertexes> readFromFile(Component parent) {
		return readFromFile(openFile(parent));
	}

	public static File file = VRGUtils.getFile(new File(VRGUtils.LABEL_VRG),
			".txt");
	public static FileWriter out;

	public static void openFile() {
		try {
			out = new FileWriter(file);
		} catch (IOException e) {
			VRGUtils.showErrorMess(null, VRGUtils.MSG_ERR_TITLE, e.toString());
		}
	}

	public static void closeFile() {
		try {
			out.flush();
			out.close();
			// Desktop desk = Desktop.getDesktop();
			// desk.open(file);
		} catch (IOException e) {
			VRGUtils.showErrorMess(null, VRGUtils.MSG_ERR_TITLE, e.toString());
		}
	}

	public static void write(Object s) {
		try {
			String n = "\n";
			out.append(n);
			out.append(String.valueOf(s));
			out.flush();
		} catch (IOException e) {
			VRGUtils.showErrorMess(null, VRGUtils.MSG_ERR_TITLE, e.toString());
		}
	}
}
