package vrg;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import ru.amse.smyshlyaev.grapheditor.graph.Graph;
import ru.amse.smyshlyaev.grapheditor.io.GraphXMLFileReader;
import ru.amse.smyshlyaev.grapheditor.io.GraphXMLFileWriter;
import ru.amse.smyshlyaev.grapheditor.ui.filechooser.FileChooser;

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
	public static final String BTN_TXT_SAVE = "Сохранить";// "Save";//Generate
	public static final String BTN_TXT_OPEN = "Открыть";// "Open";
	public static final String FIELD_TXT_NUMBER_OF_ROWS = "Введите количество узлов";// "Enter count";
	public static final String TXT_COORDS_DEMAND_PRICE = "Координаты, спрос и цены клиентов";//
	public static final String TXT_GAMERS_AUTO = "Игроки (Авто)";// "Gamers";
	public static final String BTN_TXT_BEST_SOLUTION = "Лучшее решение";// "Best solution";
	public static final String BTN_TXT_SEARCH_SOLUTION = "Поиск решения";// "Search solution";
	public static final String BTN_TXT_ANOTHER_SOLUTION = "Другое решение";// "Another solution";
	public static final String TXT_PLAYER_NUMBER = "Игрок, i";// "Player, i";
	public static final String TXT_ROUTE_NUMBER = "Маршрут, r[i]";// "Route, r[i]";
	public static final String TXT_LENGTH_OF_ROUTE = "Длина маршрута, r[i]";// "Length of the route, r[i]";
	public static final String TXT_LOAD_VEHICLE = "Загрузка ТС, Sum(d(x[j]))";// "Load the vehicle, Sum(d(x[j]))";
	public static final String TXT_PROFIT_LABEL = "Прибыль, k[i](h[i])";// "Profit, k[i](h[i])";
	public static final String BTN_TXT_GENERATE = "Генерировать игроков";// "Generate";
	public static final String TXT_ROUTE = "r[i]";
	public static final String TXT_PROFIT = "k[i](r[i])";
	public static final String TXT_VERTEX = "x[j]/x[j]";
	public static final String TAB_TXT_GRAPH = "Граф";// "Graph";
	public static final String TAB_TXT_RESULT = "Результат";// "Result";
	public static final String TAB_TXT_ENTER_COORDS = "Ввод координат/авто";// "Coordinate input";
	public static final String FIELD_TXT_NUMBERS_OF_PLAYERS = "Введите кол-во игроков";// "Enter the number of players";
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

	public static final String MSG_INIT = "Здесь будет отображаться Ваш граф" + "\n"
			+ "Чтобы сгенерировать другой маршрут нажмите пробел (spase)" + "\n"
			+ "Чтобы сохранить текущее окно как картинку, нажмите CTRL" + "\n"
			+ "Чтобы сохранить весь экран как картинку, нажмите ALT";
	public static final String MSG_ERR_FILE_ISNT_CREATED = "Не возможно создать файл!";// "File is not created!";
	public static final String MSG_ERR_TITLE = "Error";
	public static final String MSG_ERR_BODY_TC = "Перейдите по вкладке граф";// "Click graph tab";
	public static final String MSG_ATTENTION = "Attention";
	public static final String MSG_ERR_FNF = "Файл не найден";// "File not found";
	public static final String MSG_BODY_ATTENTION = "Можете перейдите по вкладке граф,\n " + "чтобы посмотреть визуализацию";// "Click graph tab";
	public static final String MSG_ERR_BODY_NULL = "Заполните начальные данные";// "Enter main values";
	public static final String LABEL_WEIGHT = "Масса: ";// "WEIGHT: ";
	public static final String TXT_ANALYS = "Анализ на чувствительность";// "Analys";
	public static final String MSG_ERR_ADD_VERTEX = "Добавьте несколько вершин";// "Add more vertexes";
	public static final String TXT_IS_ALL = "Всего";// "All: ";
	public static final String MSG_ERR_ROUTES = "Возможные пути исчерпаны \n " + "Сгенерировать другие?";// "Generate routes?";//yes,
	public static final String FIELD_INT_TIMEWINDOW = "Введите максимальное время в пути";
	public static final String TXT_GENERATE_STANDARD_DATA = "Стандартные данные";// "Standard data";
	public static final String TXT_BUTT_TIME_WINDOW = "Временные окна";// "Time window";
	public static final String MSG_ERR_TIMEWINDOW = "Время не может быть отрицательным или равным нулю";

	public static final String SYMBOLS_ON = "☑";
	public static final String SYMBOLS_OFF = "☐";
	public static final String TXT_GRAPH = "V";
	public static final String LABEL_BASE = "A ";// "Base";
	public static final String LABEL_VERTEX = "X ";

	public static final String LABEL_VRG = "VRG Folder";
	public static final int DELAY = 100;
	public static final int START = 10;
	public static int DISTANCE = 1;
	public static int radius = 10;
	public static final int MAX_SIZE = 1000;// FIXME
	public static int windowWidth = 20;
	public static int windowHeight = 20;

	public static final String MENU_FILE = "Файл";// "File";
	public static final String MENU_EDIT = "Изменить";// "Edit";
	public static final String MENU_OPEN_LOG = "Открыть LOG";// "Edit";
	public static final String MENU_NEW = "Новый";// "New";
	public static final String MENU_EXPORT = "Экспорт таблиц";// "Export table";
	public static final String MENU_TABLE = "Таблица ";// "Table";
	public static final String MENU_COORDS_D_P = "Таблица координат, спроса, цены";// "Table of coordinates, demand, prices";
	public static final String MENU_CARS = "Таблица машин, загрузка ТС";// "Table cars, loading vehicle";
	public static final String MENU_COSTS = "Таблица транспортных затрат";// "Table of transportation costs";
	public static final String MENU_ROUTES = "Таблица маршрутов";// "The routing table";
	public static final String MENU_RESULT = "Таблица результатов";// "results table";
	public static final String MENU_EXPORT_ALL = "Экспортировать все таблицы";// "Export all tables";
	public static final String MENU_IMPORT = "Импортировать файлы";// "Importing files";
	public static final String MENU_COORDS = "Файл с координатами";// "File with the coordinates";
	public static final String MENU_CLOSE = "Закрыть программу";// "Close the program";
	public static final String MENU_EXPORT_GRAPH = "XML граф";
	public static final String MENU_IMPORT_GRAPH = "Файл с графом";

	public static final String TXT_FILES = "Файлы текста";// "Text files";
	public static final String ENCODING = "cp1251";

	public static DecimalFormat df = new DecimalFormat("#.####");

	public static int getIntFromDialog(String text) {
		int k = 0;
		try {
			k = Integer.parseInt(JOptionPane.showInputDialog(text));
		} catch (NumberFormatException e) {
			return 0;
		}
		return k;
	}

	public static int getIntFromDialog(javax.swing.JFrame frame, String title, int text) {
		int k = 0;
		try {
			k = Integer.parseInt(String.valueOf(JOptionPane.showInputDialog(frame, title, null, JOptionPane.QUESTION_MESSAGE,
					null, null, text)));
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
		return VRGUtils.df.format(Double.parseDouble(s));
	}

	public static Double getDouble(Object o) {
		String s = String.valueOf(o);
		return Double.valueOf(s.replace(",", "."));
	}

	public static void showMessage(java.awt.Component frame, String title, String body, int type) {
		JOptionPane.showMessageDialog(frame, body, title, type);
	}

	public static void showErrorMess(java.awt.Component frame, String title, String body) {
		showMessage(frame, title, body, JOptionPane.ERROR_MESSAGE);
	}

	public static void showInfoMess(java.awt.Component frame, String title, String body) {
		showMessage(frame, title, body, JOptionPane.QUESTION_MESSAGE);
	}

	public static boolean showInputDialog(java.awt.Component frame, String title, String body) {
		return (JOptionPane.showConfirmDialog(frame, body, title, JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION);
	}

	public static void showInitMessage(javax.swing.JFrame frame, String text) {
		showInfoMess(frame, "", text);
	}

	public static void saveWindowSize(int w, int h) {
		windowWidth = w;
		windowHeight = h;
		VRGgraphComponent.reSize(w, h);
		VRGgraphOld.reSize(w, h);
	}

	public static void paintCarcass(Graphics graphics) {// FIXME
		graphics.setColor(Color.BLACK);

		int numX = graphics.getClipBounds().width / 10;
		int numY = graphics.getClipBounds().height / 10;

		int offset = 5;
		int dx = 0, dy = 0;
		for (int i = 0; i < MAX_SIZE / 10; i++) {
			dx += numX;
			dy += numY;
			graphics.drawLine(0, dy, offset, dy);
			// graphics.drawString(String.valueOf(dy / DISTANCE), offset + 1,
			// dy);

			graphics.drawLine(dx, 0, dx, offset);
			// graphics.drawString(String.valueOf(dx / DISTANCE), dx - numX / 5,
			// offset * 4);
		}
		// paramGraphics.drawLine(0, 1, MAX_SIZE, 1);
		// paramGraphics.drawLine(1, 0, 1, MAX_SIZE);
	}

	public static void takeScreenCapture(javax.swing.JFrame frame) {
		frame.setExtendedState(6);

		java.util.Timer timer = new java.util.Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				VRGUtils.takeScreenCapture();
				this.cancel();
			}
		}, VRGUtils.DELAY, VRGUtils.START);
	}

	public static void takeScreenCapture() {
		BufferedImage image;
		try {
			image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

			ImageIO.write(image, "png", getFile(new File(VRGUtils.LABEL_VRG)));

		} catch (Exception e) {
			VRGUtils.showErrorMess(null, VRGUtils.MSG_ERR_TITLE, VRGUtils.MSG_ERR_FILE_ISNT_CREATED);
		}
	}

	public static void takeScreenShotOfWindow(Component component) {
		try {
			BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = image.createGraphics();
			component.paint(graphics2D);

			File directory = new File(VRGUtils.LABEL_VRG);

			ImageIO.write(image, "jpeg", getFile(directory));
		} catch (Exception e) {
			VRGUtils.showErrorMess(component, VRGUtils.MSG_ERR_TITLE, VRGUtils.MSG_ERR_FILE_ISNT_CREATED);
		}
	}

	public static File getFile(File directory) {
		return getFile(directory, ".jpg");
	}

	public static File getFile(File directory, String extension) {
		File filename = null;

		if (directory.exists() && directory.isDirectory()) {
			filename = new File(directory.getName() + "/ScreenShots " + (directory.list().length + 1) + extension);
		} else {
			directory.mkdir();
			filename = new File(directory.getName() + "/ScreenShots " + (directory.list().length + 1) + extension);
		}
		return filename;
	}

	private static boolean openGraph(VRGgraphComponent component) {
		boolean result = false;
		File file = FileChooser.choose(component, BTN_TXT_OPEN);

		if (file == null) {
			return result;
		}
		Graph graph = GraphXMLFileReader.readGraph(file);

		if (graph == null) {
			return result;
		}
		component.setGraph(graph);
		component.repaint();
		return result;
	}

	private static boolean saveGraph(VRGgraphComponent component) {
		boolean result = false;
		File file = FileChooser.choose(component, BTN_TXT_SAVE);
		if (file == null) {
			return result;
		}
		try {
			GraphXMLFileWriter.writeGraph(file, component.getGraph());
			result = true;
		} catch (Exception e) {
			showErrorMess(component, MSG_ERR_TITLE, e.toString());
		}
		return result;
	}

	public static boolean IOGraph(VRGgraphComponent component, boolean isSave) {
		if (isSave) {
			return saveGraph(component);
		} else {
			return openGraph(component);
		}
	}

	public static class Point {
		int x;
		int y;

		public Point(int xx, int yy) {
			x = xx;
			y = yy;
		}
	}
}
