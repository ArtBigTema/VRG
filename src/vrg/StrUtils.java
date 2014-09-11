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
	public static final String BTN_TXT_SAVE_COUNT = "Сохранить";// "Save";
	public static final String FIELD_TXT_NUMBER_OF_ROWS = "Введите количество узлов";// "Enter count";
	public static final String TXT_COORDS_DEMAND_PRICE = "Координаты, спрос и цены клиентов";//
	public static final String TXT_GAMERS_AUTO = "Игроки (Авто)";// "Gamers";
	public static final String BTN_TXT_BEST_SOLUTION = "Лучшее решение";// "Best solution";
	public static final String BTN_TXT_ANOTHER_SOLUTION = "Другое решение";// "Another solution";
	public static final String TXT_PLAYER_NUMBER = "Игрок, i";// "Player, i";
	public static final String TXT_ROUTE_NUMBER = "Маршрут, r[i]";// "Route, r[i]";
	public static final String TXT_LENGTH_OF_ROUTE = "Длина маршрута, r[i]";// "Length of the route, r[i]";
	public static final String TXT_LOAD_VEHICLE = "Загрузка ТС, Sum(d(x[j]))";// "Load the vehicle, Sum(d(x[j]))";
	public static final String TXT_PROFIT_LABEL = "Прибыль, k[i](h[i])";// "Profit, k[i](h[i])";
	public static final String BTN_TXT_GENERATE = "Генерировать";// "Generate";
	public static final String TXT_ROUTE = "r[i]";
	public static final String TXT_PROFIT = "k[i](r[i])";
	public static final String TXT_VERTEX = "x[j]/x[j]";
	public static final String TAB_TXT_GRAPH = "Граф";// "Graph";
	public static final String TAB_TXT_RESULT = "Результат";// "Result";
	public static final String TAB_TXT_ENTER_COORDS = "Ввод координат/авто";// "Coordinate input";
	public static final String FIELD_TXT_NUMBERS_OF_PLAYERS = "Введите количество игроков";// "Enter the number of players";
	public static final String TXT_VERTEX_LABEL = "Вершина, x[j]";// "Vertex, x[j]";
	public static final String TXT_COORDS = "Координаты";// "Coordinates";
	public static final String TXT_DEMAND = "d(x[j])";
	public static final String TXT_PRICE = "p(x[j])";
	public static final String BTN_TXT_DELETE_VERTEX = "Удалить вершины";// "Delete vertex";
	public static final String BTN_TXT_GENERATE_DATA = "Генерировать данные";// "Generate data";
	public static final String BTN_TXT_ADD_VERTEX = "Добавить вершины";// "Add vertex";
	public static final String TXT_PLAYER_LABEL = "Всего игроков: ";// "Total players: ";
	public static final String LABEL_BASE = "A ";// "Base";
	public static final String LABEL_VERTEX = "X ";

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
