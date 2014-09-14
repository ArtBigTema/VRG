package vrg;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class VRGTableExporter {

	public static void exportTableToXLS(JTable[] tables) {
		try {
			File filename = null;
			String name = "";

			if (tables.length == 1) {
				name = tables[0].getName();
			} else {
				name = VRGUtils.TAB_TXT_RESULT;
				tables[0].setName("");
			}

			filename = createFile(name);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filename), "cp1251"));

			for (JTable table : tables) {
				writeFile(out, table);
			}

			flushAndOpenFile(out, filename);

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex.toString(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private static File createFile(String name) {
		File directory = new File(VRGUtils.LABEL_VRG);
		if (directory.exists() && directory.isDirectory()) {
			return new File(directory.getName() + "/" + name
					+ +(directory.list().length + 1) + ".xls");
		} else {
			directory.mkdir();
			return new File(directory.getName() + "/" + name
					+ +(directory.list().length + 1) + ".xls");
		}
	}

	private static void flushAndOpenFile(BufferedWriter out, File filename)
			throws IOException {
		out.flush();
		out.close();
		Desktop desk = Desktop.getDesktop();
		desk.open(filename);
		// filename.deleteOnExit();
	}

	private static void writeFile(BufferedWriter out, JTable table) {
		try {
			out.write("<html>"
					+ "<head>"
					+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1251\" />"
					+ "<title>"
					+ table.getName()
					+ "</title>"
					+ "</head>"
					+ "<body>"
					+ "<table border=\"1\" cellspacing=\"0\" cellpadding=\"0\">"
					+ "<tr>");

			for (int i = 0; i < table.getColumnCount(); i++) {
				out.write("<td bgcolor=\"#EEEECC\" align=\"center\" width=\""
						+ table.getColumnModel().getColumn(i).getWidth()
						+ "\"><B>" + table.getColumnName(i) + "</B></td>");
			}

			out.write("</tr>");

			for (int i = 0; i < table.getRowCount(); i++) {
				out.write("<tr>");

				for (int j = 0; j < table.getColumnCount(); j++) {
					if (table.getValueAt(i, j) != null)
						out.write("<td>" + table.getValueAt(i, j).toString()
								+ " </td>");
					else
						out.write("<td> </td>");
				}

				out.write("</tr>");
			}

			out.write("</table></body></html>");

			out.write("<html>"
					+ "<head>"
					+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1251\" />"
					+ "<title>"
					+ "\t"
					+ "</title>"
					+ "</head>"
					+ "<body>"
					+ "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"
					+ "<tr>");
			out.write("</table></body></html>");
		} catch (IOException ex) {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
			}
			JOptionPane.showMessageDialog(null, ex.toString(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}