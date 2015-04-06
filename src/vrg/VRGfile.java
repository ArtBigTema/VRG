package vrg;

import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import vrg.VRGUtils.Point;
import vrg.VRGwithTimeWindow.PointT;

public class VRGfile {
	public static String coordsFileName = "";

	public static JFileChooser getFileChooser(String ext) {
		JFileChooser chooser = new JFileChooser(new File("").getAbsolutePath());
		FileFilter ff = new FileNameExtensionFilter(VRGUtils.TXT_FILES, ext, "adb");
		chooser.setFileFilter(ff);
		return chooser;
	}

	public static File openFile(Component parent, String ext) {
		JFileChooser chooser = getFileChooser(ext);
		File file = chooser.getSelectedFile();
		int result = chooser.showOpenDialog(parent);
		if (result == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
		}
		if (file == null) {
			VRGUtils.showErrorMess(parent, VRGUtils.MSG_ERR_TITLE, VRGUtils.MSG_ERR_FNF);
			return null;
		}
		return file;
	}

	public static ArrayList<VRGvertexes> readFromFile(File f) {
		if (f == null) {
			return null;
		}
		ArrayList<VRGvertexes> result = null;
		try {
			Scanner scanner = new Scanner(f);

			int n = VRGUtils.getIntFromText(scanner.nextLine());
			result = new ArrayList<VRGvertexes>(n);

			for (int i = 0; i < n; i++) {
				result.add(new VRGvertexes(scanner.nextLine().split("\\s+")));
			}
			scanner.close();
		} catch (FileNotFoundException ex) {
			return null;
		}
		return result;
	}

	public static ArrayList<VRGvertexes> readFromFile(Component parent) {
		return readFromFile(openFile(parent, "txt"));
	}

	public static ArrayList<Integer> readCarsFromFile() {
		ArrayList<Integer> res = new ArrayList<Integer>();
		File file = new File("resources/cars.json");

		String json = "";
		if (file != null && file.exists()) {
			try {
				json = FileUtils.readFileToString(file, "UTF-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			VRGUtils.showErrorMess(null, VRGUtils.MSG_ERR_TITLE, VRGUtils.MSG_ERR_FNF);
			return null;
		}

		JSONParser parser = new JSONParser();

		Object obj = null;
		try {
			obj = parser.parse(json);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONObject jsonObj = (JSONObject) obj;

		int n = getInt(jsonObj.get("count"));
		for (int i = 0; i < n; i++) {
			res.add((int) (10 + Math.random() * 10));// FIXME
		}
		return res;
	}

	public static ArrayList<PointT> readCoordFromFile(java.awt.Component parent) {
		// File file = new File("resources/coords.json");
		File file = openFile(parent, "json");

		String json = "";
		if (file != null && file.exists()) {
			try {
				json = FileUtils.readFileToString(file, "UTF-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}

		JSONParser parser = new JSONParser();

		Object obj = null;
		try {
			obj = parser.parse(json);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONObject jsonObj = (JSONObject) obj;

		int n = getInt(jsonObj.get("count"));

		JSONArray jstart = (JSONArray) jsonObj.get("CoordinatesStart");
		JSONArray jend = (JSONArray) jsonObj.get("CoordinatesEnd");
		JSONArray dels = (JSONArray) jsonObj.get("Delay");
		JSONArray ts = (JSONArray) jsonObj.get("TimeStart");
		JSONArray te = (JSONArray) jsonObj.get("TimeEnd");

		ArrayList<PointT> res = new ArrayList<PointT>();
		for (int i = 0; i < n; i++) {
			JSONArray js = (JSONArray) jstart.get(i);
			JSONArray je = (JSONArray) jend.get(i);

			Point ps = new Point(getInt(js.get(0)), getInt(js.get(1)));
			Point pe = new Point(getInt(je.get(0)), getInt(je.get(1)));

			PointT t = new PointT(ps);
			t.setEndPlace(pe);
			t.setDelay(getInt(dels.get(i)));
			t.setTimeWindow(getInt(ts.get(i)), getInt(te.get(i)));

			res.add(t);
		}

		return res;
	}

	public static int getInt(Object o) {
		return Integer.valueOf(String.valueOf(o.toString()));
	}

	public static File file = VRGUtils.getFile(new File(VRGUtils.LABEL_VRG), ".txt");
	public static FileWriter out;

	public static void openFile() {
		try {
			out = new FileWriter(file);
			writeDate();
		} catch (IOException e) {
			VRGUtils.showErrorMess(null, VRGUtils.MSG_ERR_TITLE, e.toString());
		}
	}

	public static void writeDate() {
		write(Calendar.getInstance().getTime().toString());
		write("ms: " + System.currentTimeMillis());
		write("\n");
	}

	public static void closeFile() {
		writeDate();
		try {
			out.flush();
			out.close();
			// openFileInDesktop();
		} catch (IOException e) {
			VRGUtils.showErrorMess(null, VRGUtils.MSG_ERR_TITLE, e.toString());
		}
	}

	public static void openFileInDesktop() {
		Desktop desk = Desktop.getDesktop();
		try {
			desk.open(file);
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
