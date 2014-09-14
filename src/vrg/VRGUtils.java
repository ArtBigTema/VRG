package vrg;

import javax.swing.JOptionPane;

public class VRGUtils {
	public static final String OPENEDBKT = "(";
	public static final String CLOSEDBKT = ")";
	public static final String SEMICOLON = ";";
	public static final String COMMA = ",";
	public static final String X = "x";
	public static final String EQ = "=";
	public static final String SPACE = " ";
	public static final String SLASH = "/";
	public static final String FONT_TAHOMA = "Tahoma";
	public static final String TXT_DISTANCES = "Расстояния между вершинами (транспортные затраты)";// "Distances between vertices";
	public static final String TXT_TYPE_OF_GAME = "Можете выбрать путь согласованной игры или выбрать автоматическую";// "You can choose the way of a coherent game or choose to automatically";
	public static final String TXT_EXAMPLE = " Пример: 0, 3, 4, 7, 0.";// "Example: 0, 3, 4, 7, 0";
	public static final String TXT_ROUTES = "Маршруты";// "Routes";
	public static final String TXT_TRANSPORTS_COSTS = "Транспортные затраты";// "Transport Costs";
	public static final String BTN_TXT_SAVE_COUNT = "Сохранить";// "Save";//Generate
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
	public static final String BTN_TXT_DELETE_VERTEX = "Очистить всё";// "Clear ALL";"Delete vertex";
	public static final String BTN_TXT_GENERATE_DATA = "Генерировать данные";// "Generate data";
	public static final String BTN_TXT_ADD_VERTEX = "Добавить вершины";// "Add vertex";
	public static final String TXT_PLAYER_LABEL = "Всего игроков: ";// "Total players: ";
	public static final String LABEL_CARS = "Cars ";

	public static final String GRAPH_PARAM_1 = "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
			+ "exitX=0.5;exitY=1;exitPerimeter=1;entryX=0;entryY=0;entryPerimeter=1;";
	public static final String GRAPH_PARAM_2 = "edgeStyle=elbowEdgeStyle;elbow=vertical;orthogonal=1;"
			+ "entryX=0;entryY=1;entryPerimeter=0;";
	public static final String GRAPH_PARAM_3 = "shape=and;fillColor=#ff0000;gradientColor=#ffffff;shadow=1";

	public static final String MSG_INIT = "Здесь будет отображаться Ваш граф"
			+ "\n"
			+ "Чтобы сгенерировать другой маршрут нажмите пробел (spase)"
			+ "\n" + "Чтобы сохранить текущее окно как картинку, нажмите CTRL"
			+ "\n" + "Чтобы сохранить весь экран как картинку, нажмите ALT";
	public static final String MSG_ERR_FILE_ISNT_CREATED = "Не возможно создать файл!";// "File is not created!";
	public static final String MSG_ERR_TITLE = "Error";
	public static final String MSG_ERR_BODY_TC = "Перейдите по вкладке граф";// "Click graph tab";
	public static final String MSG_ERR_ATTENTION = "Attention";
	public static final String MSG_ERR_BODY_ATTENTION = "Можете перейдите по вкладке граф,\n "
			+ "чтобы посмотреть визуализацию";// "Click graph tab";
	public static final String MSG_ERR_BODY_NULL = "Заполните начальные данные";// "Enter main values";
	public static final String LABEL_WEIGHT = "Масса: ";// "WEIGHT: ";
	public static final String TXT_ANALYS = "Анализ на чувствительность";// "Analys";
	public static final String MSG_ERR_ADD_VERTEX = "Добавьте несколько вершин";// "Add more vertexes";
	public static final String TXT_IS_ALL = "Всего";// "All: ";
	public static final String MSG_ERR_ROUTES = "Возможные пути исчерпаны \n "
			+ "Сгенерировать другие?";// "Generate routes?";
	public static final String TXT_GENERATE_STANDARD_DATA = "Стандартные данные";// "Standard data";

	public static final String SYMBOLS_ON = "☑";
	public static final String SYMBOLS_OFF = "☐";
	public static final String TXT_GRAPH = "V";
	public static final String LABEL_BASE = "A ";// "Base";
	public static final String LABEL_VERTEX = "X ";

	public static final String LABEL_VRG = "VRG";
	public static final int DELAY = 400;
	public static final int START = 10;

	public static int getIntFromDialog(String text) {
		int k = 0;
		try {
			k = Integer.parseInt(JOptionPane.showInputDialog(text));
		} catch (NumberFormatException e) {
			return 0;
		}
		return k;
	}

	public static int getIntFromDialog(javax.swing.JFrame frame, String title,
			int text) {
		int k = 0;
		try {
			k = Integer.parseInt(String.valueOf(JOptionPane.showInputDialog(
					frame, title, null, JOptionPane.QUESTION_MESSAGE, null,
					null, text)));
		} catch (NumberFormatException e) {
			return 0;
		}
		return k;
	}

	public static int getIntFromText(String text) {
		int k = 0;
		try {
			k = Integer.parseInt(text);
		} catch (NumberFormatException e) {
			return 0;
		}
		return k;
	}

	public static int getIntFromObject(Object object) {
		int k = 0;
		try {
			k = Integer.parseInt(String.valueOf(object));
		} catch (Exception e) {
			return 0;
		}
		return k;
	}

	public static String get(Object o) {
		String s = String.valueOf(o);
		if (s.length() > 5) {
			s = s.substring(0, 5);
		}
		return s.replace(".", ",");
	}

	public static Double getDouble(Object o) {
		String s = String.valueOf(o);
		return Double.valueOf(s.replace(",", "."));
	}

	public static void showErrorMess(javax.swing.JFrame frame, String title,
			String body) {
		JOptionPane.showMessageDialog(frame, body, title,
				JOptionPane.ERROR_MESSAGE);
	}

	public static boolean showInputDialog(javax.swing.JFrame frame,
			String title, String body) {
		return (JOptionPane.showConfirmDialog(frame, body, title,
				JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION);
	}

	public static void showInitMessage(javax.swing.JFrame frame, String text) {
		JOptionPane.showMessageDialog(frame, text);
	}
}
