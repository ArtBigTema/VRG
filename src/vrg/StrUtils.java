package vrg;

import javax.swing.JOptionPane;

public class StrUtils {
	public static final String OPENEDBKT = "(";
	public static final String CLOSEDBKT = ")";
	public static final String SEMICOLON = ";";
	public static final String X = "x";
	public static final String SPACE = " ";
	public static final String FONT_TAHOMA = "Tahoma";
	public static final String TXT_DISTANCES = "Расстояния между вершинами (транспортные затраты)";// "Distances between vertices";
	public static final String TXT_TYPE_OF_GAME = "Можете выбрать путь согласованной игры или выбрать автоматическую";// "You can choose the way of a coherent game or choose to automatically";
	public static final String TXT_EXAMPLE = " Пример: 0, 3, 4, 7, 0.";// "Example: 0, 3, 4, 7, 0";
	public static final String TXT_TRANSPORTS_COSTS = "Транспортные затраты";// "Transport Costs";
	public static final String TXT_SAVE_COUNT = "Сохранить";// "Save";
	public static final String TXT_ENTER_COUNT_ROWS = "Введите количество узлов";//"Enter count";
	public static final String TXT_COORDS_DEMAND_PRICE = "Координаты, спрос и цены клиентов";//

	public static int getIntFromDialog(String text) {
		int k = 0;
		try {
			k = Integer.parseInt(JOptionPane.showInputDialog(text));
		} catch (NumberFormatException e) {
			k = 0;
		}
		return k;
	}

	public static int getIntFromText(String text) {
		int k = 0;
		try {
			k = Integer.parseInt(text);
		} catch (NumberFormatException e) {
			k = 0;
		}
		return k;
	}
	
	public static int getIntFromObject(Object object) {
		int k = 0;
		try {
			k = Integer.parseInt(String.valueOf(object));
		} catch (Exception e) {
			k = 0;
		}
		return k;
	}
}
