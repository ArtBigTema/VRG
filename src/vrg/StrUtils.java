package vrg;

import javax.swing.JOptionPane;

public class StrUtils {
	public static final String OPENEDBKT = "(";
	public static final String CLOSEDBKT = ")";
	public static final String SEMICOLON = ";";
	public static final String COMMA = ",";
	public static final String X = "x";
	public static final String SPACE = " ";
	public static final String FONT_TAHOMA = "Tahoma";
	public static final String TXT_DISTANCES = "���������� ����� ��������� (������������ �������)";// "Distances between vertices";
	public static final String TXT_TYPE_OF_GAME = "������ ������� ���� ������������� ���� ��� ������� ��������������";// "You can choose the way of a coherent game or choose to automatically";
	public static final String TXT_EXAMPLE = " ������: 0, 3, 4, 7, 0.";// "Example: 0, 3, 4, 7, 0";
	public static final String TXT_TRANSPORTS_COSTS = "������������ �������";// "Transport Costs";
	public static final String BTN_TXT_SAVE_COUNT = "���������";// "Save";//Generate
	public static final String FIELD_TXT_NUMBER_OF_ROWS = "������� ���������� �����";// "Enter count";
	public static final String TXT_COORDS_DEMAND_PRICE = "����������, ����� � ���� ��������";//
	public static final String TXT_GAMERS_AUTO = "������ (����)";// "Gamers";
	public static final String BTN_TXT_BEST_SOLUTION = "������ �������";// "Best solution";
	public static final String BTN_TXT_ANOTHER_SOLUTION = "������ �������";// "Another solution";
	public static final String TXT_PLAYER_NUMBER = "�����, i";// "Player, i";
	public static final String TXT_ROUTE_NUMBER = "�������, r[i]";// "Route, r[i]";
	public static final String TXT_LENGTH_OF_ROUTE = "����� ��������, r[i]";// "Length of the route, r[i]";
	public static final String TXT_LOAD_VEHICLE = "�������� ��, Sum(d(x[j]))";// "Load the vehicle, Sum(d(x[j]))";
	public static final String TXT_PROFIT_LABEL = "�������, k[i](h[i])";// "Profit, k[i](h[i])";
	public static final String BTN_TXT_GENERATE = "������������";// "Generate";
	public static final String TXT_ROUTE = "r[i]";
	public static final String TXT_PROFIT = "k[i](r[i])";
	public static final String TXT_VERTEX = "x[j]/x[j]";
	public static final String TAB_TXT_GRAPH = "����";// "Graph";
	public static final String TAB_TXT_RESULT = "���������";// "Result";
	public static final String TAB_TXT_ENTER_COORDS = "���� ���������/����";// "Coordinate input";
	public static final String FIELD_TXT_NUMBERS_OF_PLAYERS = "������� ���������� �������";// "Enter the number of players";
	public static final String TXT_VERTEX_LABEL = "�������, x[j]";// "Vertex, x[j]";
	public static final String TXT_COORDS = "����������";// "Coordinates";
	public static final String TXT_DEMAND = "d(x[j])";
	public static final String TXT_PRICE = "p(x[j])";
	public static final String BTN_TXT_DELETE_VERTEX = "�������� ��";// "Clear ALL";"Delete vertex";
	public static final String BTN_TXT_GENERATE_DATA = "������������ ������";// "Generate data";
	public static final String BTN_TXT_ADD_VERTEX = "�������� �������";// "Add vertex";
	public static final String TXT_PLAYER_LABEL = "����� �������: ";// "Total players: ";
	public static final String LABEL_BASE = "A ";// "Base";
	public static final String LABEL_VERTEX = "X ";
	public static final String LABEL_CARS = "Cars ";

	public static final String GRAPH_PARAM_1 = "edgeStyle=elbowEdgeStyle;elbow=horizontal;"
			+ "exitX=0.5;exitY=1;exitPerimeter=1;entryX=0;entryY=0;entryPerimeter=1;";
	public static final String GRAPH_PARAM_2 = "edgeStyle=elbowEdgeStyle;elbow=vertical;orthogonal=1;"
			+ "entryX=0;entryY=1;entryPerimeter=0;";
	public static final String GRAPH_PARAM_3 = "shape=and;fillColor=#ff0000;gradientColor=#ffffff;shadow=1";
	public static final String MSG_ERR_TITLE = "Error";
	public static final String MSG_ERR_BODY_TC = "��������� �� ������� ����";// "Click graph tab";
	public static final String MSG_ERR_ATTENTION = "Attention";
	public static final String MSG_ERR_BODY_ATTENTION = "������ ��������� �� ������� ����,\n ����� ���������� ������������";// "Click graph tab";
	public static final String EQ = "=";
	public static final String MSG_ERR_BODY_NULL = "��������� ��������� ������";// "Enter main values";
	public static final String LABEL_WEIGHT = "�����: ";// "WEIGHT: ";
	public static final String TXT_ANALYS = "������ �� ����������������";// "Analys";
	public static final String MSG_ERR_ADD_VERTEX = "�������� ��������� ������";// "Add more vertexes";
	public static final String TXT_IS_ALL = "�����";// "All: ";

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
