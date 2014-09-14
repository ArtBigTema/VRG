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

	public static void exportTableToXLS(JTable table) {
		File directory = new File(VRGUtils.LABEL_VRG);
		File filename = null;
		try {
			if (directory.exists() && directory.isDirectory()) {
				filename = new File(directory.getName() + "/" + table.getName()
						+ +(directory.list().length + 1) + ".xls");
			} else {
				directory.mkdir();
				filename = new File(directory.getName() + "/" + table.getName()
						+ +(directory.list().length + 1) + ".xls");
			}

			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filename), "cp1251"));

			writeFile(out, table);

			out.flush();
			out.close();
			Desktop desk = Desktop.getDesktop();
			desk.open(filename);
			// filename.deleteOnExit();

		} catch (IOException ex) {
			filename.deleteOnExit();
			JOptionPane.showMessageDialog(null, ex.toString(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void writeFile(BufferedWriter out, JTable table)
			throws IOException {
		try {
			out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">"
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
		} catch (IOException ex) {
			out.flush();
			out.close();
			JOptionPane.showMessageDialog(null, ex.toString(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}