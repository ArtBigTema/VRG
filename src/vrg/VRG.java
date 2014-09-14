package vrg;

import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;

public class VRG {
	public static final int[][] COORDINATES = { { 19, 45 }, { 18, 46 },
			{ 20, 47 }, { 22, 42 }, { 20, 41 }, { 14, 40 }, { 12, 44 },
			{ 13, 45 } };
	public static final int[] DEMAND = { 0, 1, 1, 1, 2, 2, 2, 1 };
	public static final int[] PRICE = { 0, 4, 4, 4, 4, 4, 4, 4 };
	public static final int[] CARS_WEIGHT = { 2, 2, 3 };
	public static final int[][] CARS = { { 11, 39 }, { 15, 39 }, { 20, 39 } };
	public static final int[][] ROUTES = { { 0, 1, 2, 0 }, { 0, 3, 4, 0 },
			{ 0, 5, 6, 7, 0 } };

	public static int countCoords = 8;
	public static int countCars = 3;
	public static final int ZOOM = 5;

	public static ArrayList<Integer> cars = new ArrayList<Integer>();
	public static ArrayList<Integer> price = new ArrayList<Integer>();
	public static ArrayList<Integer> demand = new ArrayList<Integer>();
	public static ArrayList<Point> coordinates = new ArrayList<Point>();
	public static ArrayList<Point> carsCoordinates = new ArrayList<Point>();
	public static ArrayList<ArrayList<Integer>> routes = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<ArrayList<Double>> lengthOfRoutes = new ArrayList<ArrayList<Double>>();

	public static boolean isValid() {
		return (coordinates != null) && (coordinates.size() > 0)
				&& (cars != null) && (cars.size() > 0);
	}

	public static void generateAllStandart() {
		clearAll();

		for (int i = 0; i < countCoords; i++) {
			coordinates.add(new Point(COORDINATES[i][0], COORDINATES[i][1]));
			price.add(PRICE[i]);
			demand.add(DEMAND[i]);
		}

		for (int i = 0; i < countCars; i++) {
			cars.add(CARS_WEIGHT[i]);

			ArrayList<Integer> tmp = new ArrayList<Integer>();
			for (int j = 0; j < ROUTES[i].length; j++) {
				tmp.add(ROUTES[i][j]);
			}
			routes.add(tmp);
		}
		cars.add(CARS_WEIGHT[countCars - 1]);

		fillCarsCoords();
		createTableOfRoutes();
	}

	public static void generateAll(int n) {
		clearAll();
		generateCoordinates(n);
		generateCars(n);
		generateDemand(n);
		generatePrice(n);
		generateGraphRoutes();
	}

	public static void clearAll() {
		countCars = CARS.length;
		countCoords = COORDINATES.length;
		coordinates.clear();
		price.clear();
		demand.clear();
		cars.clear();
		lengthOfRoutes.clear();
		routes.clear();
		carsCoordinates.clear();
	}

	public static void generateCoordinates(int n) {
		coordinates.clear();
		coordinates.add(new Point(random(n / 2, n), random(n / 2, n)));
		for (int i = 1; i < n; i++) {
			coordinates.add(new Point(random(1, n), random(1, n)));
		}
	}

	public static void generatePrice(int n) {
		price.clear();
		for (int i = 0; i < n; i++) {
			price.add(random(1, n));
		}
	}

	public static void generateDemand(int n) {
		demand.clear();
		demand.add(0);
		for (int i = 1; i < n; i++) {
			demand.add(random(1, n));
		}
	}

	public static void generateCars(int n) {
		cars.clear();

		for (int i = 0; i < n; i++) {
			cars.add(random(1, n));
		}
		if (coordinates != null && coordinates.size() > 0) {
			fillCarsCoords();
		}
	}

	public static void createTableOfRoutes() {
		lengthOfRoutes.clear();

		for (int i = 0; i < coordinates.size(); i++) {
			ArrayList<Double> tmp = new ArrayList<Double>();

			for (int j = 0; j < coordinates.size(); j++) {
				String s = getDistanceText(coordinates.get(i),
						coordinates.get(j));
				tmp.add(Double.valueOf(s));
			}

			lengthOfRoutes.add(tmp);
		}
	}

	public static int getRoutesWeight(int k) {
		ArrayList<Integer> indexes = routes.get(k);
		int result = 0;

		for (int i : indexes) {
			result += demand.get(i);
		}

		return result;
	}

	public static Double getBenefit(int k) {
		Double result = 0D;
		ArrayList<Integer> tmp = routes.get(k);

		int lastVertex = tmp.get(tmp.size() - 2);
		result = -lengthOfRoutes.get(lastVertex).get(0);

		for (int i = 1; i < tmp.size() - 1; i++) {
			result += price.get(tmp.get(i)) * demand.get(tmp.get(i));
			result -= lengthOfRoutes.get(tmp.get(i - 1)).get(tmp.get(i));
		}
		return result;
	}

	public static ArrayList<Integer> getCoordsIndexes() {
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for (int i = 0; i < coordinates.size() - 1; i++) {
			indexes.add(i + 1);
		}

		Collections.shuffle(indexes);
		return indexes;
	}

	public static void generateRoutes() {// FIXME
		routes.clear();

		for (int i = 0; i < countCars; i++) {

			ArrayList<Integer> tmp = new ArrayList<Integer>();
			ArrayList<Integer> indexes = getCoordsIndexes();

			if (indexes == null || coordinates == null
					|| coordinates.size() < 2) {
				return;
			}
			tmp.add(0);
			int m = random(Math.max(indexes.size() / 3, 1), indexes.size());
			for (int j = 0; j <= m; j++) {
				tmp.add(indexes.get(j));
			}
			tmp.add(0);

			routes.add(tmp);
		}
	}

	public static void generateGraphRoutes() {
		routes.clear();

		ArrayList<Integer> tmp = new ArrayList<Integer>();

		Queue<Integer> indexQ = new LinkedList<Integer>();
		indexQ.addAll(getCoordsIndexes());

		if (indexQ == null || coordinates == null || coordinates.size() < 2
				|| indexQ.size() < 2) {
			return;
		}
		for (int i = 0; i < countCars; i++) {
			tmp = new ArrayList<Integer>();
			tmp.add(0);
			int m = random(1, Math.max(indexQ.size() / 2, 0));// FIXME
			for (int j = 0; j < Math.min(indexQ.size(), m); j++) {
				tmp.add(indexQ.poll());
			}
			tmp.add(0);

			routes.add(tmp);
		}
	}

	public static int[][] getRoutes() {
		int n = routes.size();
		int[][] paths = new int[n][n];
		for (int i = 0; i < n; i++) {
			paths[i] = new int[routes.get(i).size()];
			for (int j = 0; j < routes.get(i).size(); j++) {
				paths[i][j] = routes.get(i).get(j);
			}
		}
		return paths;
	}

	public static Double getLengthOfRoutes(int k) {
		int n = routes.size();
		Double result = 0D;

		if (n < k) {
			return result;
		}

		ArrayList<Integer> tmp = routes.get(k - 1);
		int index = 0;

		for (int i : tmp) {
			result += lengthOfRoutes.get(index).get(i);
			index = i;
		}

		return result;
	}

	private static void fillCarsCoords() {
		carsCoordinates.clear();
		Point max = Collections.max(coordinates, new Comparator<Point>() {
			@Override
			public int compare(Point paramInt1, Point paramInt2) {
				return Integer.compare(paramInt1.x, paramInt2.x);
			}
		});

		for (int i = 0; i < cars.size(); i++) {
			carsCoordinates.add(new Point(max.x + 4, i));
		}
	}

	public static String getDistanceText(Object p1, Object p2) {
		Double distanse = 0D;
		if (p1 instanceof Point) {
			distanse = getDistance(Point.class.cast(p1), Point.class.cast(p2));
		}

		if (distanse.toString().length() < 5) {
			return distanse.toString();
		} else {
			return distanse.toString().substring(0, 5);
		}
	}

	public static double getDistance(Point p1, Point p2) {
		return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y)
				* (p2.y - p1.y));
	}

	public static int random(int start, int end) {
		return (int) (start + Math.round((end - start) * Math.random()));
	}

	public static String getDifferenceBetweenSets() {
		ArrayList<Integer> allIndexes = new ArrayList<Integer>();

		for (ArrayList<Integer> tmp : routes) {
			allIndexes.addAll(new ArrayList<Integer>(tmp));
		}

		ArrayList<Integer> indexes = getCoordsIndexes();
		indexes.add(0);

		ArrayList<Integer> diff = new ArrayList<Integer>(indexes);

		diff.removeAll(allIndexes);
		Collections.sort(diff);

		return diff.toString();
	}

	public static void generateCoordTableAtIndex(int row, int column) {
		switch (column) {
		case 1: {
			coordinates.set(row,
					new Point(random(1, countCoords), random(1, countCoords)));
			break;
		}
		case 2: {
			demand.set(row, random(1, countCoords));
			break;
		}
		case 3: {
			price.set(row, random(1, countCoords));
			break;
		}
		}
	}

	public static void generateCarsTableAtIndex(int row, int column) {
		cars.set(column, random(1, countCars));
	}

	public static String getStringDifferenceBetweenSets() {
		String result = getDifferenceBetweenSets();
		if (result.length() < 3) {
			result = "";
		} else {
			result = VRGUtils.SPACE + VRGUtils.SLASH + VRGUtils.SPACE + result;
		}
		return result;
	}

	public static void readTableFromFile(Component parent) {
		ArrayList<VRGvertexes> vertexes = VRGfile.readFromFile(parent);
		clearAll();
		countCoords = vertexes.size();

		for (int i = 0; i < countCoords; i++) {
			coordinates.add(vertexes.get(i).vertexCoords.getPoint());
			price.add(vertexes.get(i).price);
			demand.add(vertexes.get(i).demand);
		}

		for (int i = 0; i < countCars; i++) {
			cars.add(CARS_WEIGHT[i]);

			ArrayList<Integer> tmp = new ArrayList<Integer>();
			for (int j = 0; j < ROUTES[i].length; j++) {
				tmp.add(ROUTES[i][j]);
			}
			routes.add(tmp);
		}
		cars.add(CARS_WEIGHT[countCars - 1]);

		fillCarsCoords();
		createTableOfRoutes();
	}

	public static void main(String[] args) {
		VRGframe frame = new VRGframe();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
