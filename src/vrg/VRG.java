package vrg;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;

public class VRG {
	public static final int LENGTH = 20;
	public static final int DISTANCE = 30;
	public static final int COUNT = 8;
	public static final Integer[][] CORDINATES = { { 19, 45 }, { 18, 46 },
			{ 20, 47 }, { 22, 42 }, { 20, 41 }, { 14, 40 }, { 12, 44 },
			{ 13, 45 } };
	public static final Integer[] DEMAND = { 0, 1, 1, 1, 2, 2, 2, 1 };
	public static final Integer[] PRICE = { 0, 4, 4, 4, 4, 4, 4, 4 };
	public static final Integer[] CARS_WEIGHT = { 2, 2, 3 };
	public static final Integer[][] CARS = { { 11, 39 }, { 15, 39 }, { 20, 39 } };

	public static int countCars = 3;
	public static final int ZOOM = 5;

	public static ArrayList<Integer> cars = new ArrayList<Integer>();
	public static ArrayList<Integer> price = new ArrayList<Integer>();
	public static ArrayList<Integer> demand = new ArrayList<Integer>();
	public static ArrayList<Point> coordinates = new ArrayList<Point>();
	public static ArrayList<Point> carsCoordinates = new ArrayList<Point>();
	public static ArrayList<ArrayList<Integer>> routes = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<ArrayList<Double>> lengthOfRoutes = new ArrayList<ArrayList<Double>>();

	public static void generateAllStandart() {
		clearAll();

		Collections.addAll(price, PRICE);
		Collections.addAll(demand, DEMAND);
		Collections.addAll(cars, CARS_WEIGHT);

		for (int i = 0; i < COUNT; i++) {
			coordinates.add(new Point(CORDINATES[i][0], CORDINATES[i][1]));
			price.add(PRICE[i]);
			demand.add(DEMAND[i]);
		}

		fillCarsCoords();
		createTableOfRoutes();
		generateRoutes();
	}

	public static void generateAll(int n) {
		generateCoordinates(n);
		generateCars(n);
		generateDemand(n);
		generatePrice(n);
		generateRoutes();
	}

	public static void clearAll() {
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
				String s = VRGvertexes.getDistanceText(coordinates.get(i),
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

	public static void generateRoutes() {
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

	public static int random(int start, int end) {
		return start + (int) ((end - start) * Math.random());
	}

	public static void main(String[] args) {
		VRGframe frame = new VRGframe();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
