package googlemapid.sample;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

@SuppressWarnings({ "serial", "rawtypes" })
class CellRenderer extends JLabel implements ListCellRenderer {

	private static final Color HIGHLIGHT_COLOR = Color.gray;

	public CellRenderer() {
		setOpaque(true);
		setIconTextGap(15);
		setFont(new java.awt.Font("Consolas", 0, 14));
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

		setText(String.valueOf(value));

		if (isSelected) {
			setBackground(HIGHLIGHT_COLOR);
			setForeground(Color.white);
		} else {
			setBackground(Color.white);
			setForeground(Color.black);
		}
		return this;
	}
}