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
	public static final int[][] CORDINATES = { { 19, 45 }, { 18, 46 },
			{ 20, 47 }, { 22, 42 }, { 20, 41 }, { 14, 40 }, { 12, 44 },
			{ 13, 45 } };
	public static final int[] DEMAND = { 0, 1, 1, 1, 2, 2, 2, 1 };
	public static final int[] PRICE = { 0, 4, 4, 4, 4, 4, 4, 4 };
	public static final int[][] CARS = { { 11, 39 }, { 15, 39 }, { 20, 39 } };

	public static int countCars = 0;

	public static ArrayList<Integer> cars = new ArrayList<Integer>();
	public static ArrayList<Integer> price = new ArrayList<Integer>();
	public static ArrayList<Integer> demand = new ArrayList<Integer>();
	public static ArrayList<Point> coordinates = new ArrayList<Point>();
	public static ArrayList<Point> carsCoordinates = new ArrayList<Point>();

	public static void generateCoordinates(int n) {
		coordinates.clear();
		coordinates.add(new Point(random(n / 2, n), random(n / 2, n)));
		for (int i = 1; i < n; i++) {// First is base == a
			coordinates.add(new Point(random(1, n), random(1, n)));
		}
	}

	public static void generatePrice(int n) {
		price.clear();
		for (int i = 0; i < n; i++) {
			price.add(random(1, n));// ==4
		}
	}

	public static void generateCars(int n) {
		cars.clear();
		for (int i = 0; i < n; i++) {
			cars.add(random(1, n));// 1..2
		}
		fillCarsCoords();
	}

	private static void fillCarsCoords() {
		carsCoordinates.clear();
		Point min = Collections.min(coordinates, new Comparator<Point>() {
			@Override
			public int compare(Point paramInt1, Point paramInt2) {
				return Integer.compare(paramInt1.y, paramInt2.y);
			}
		});

		for (int i = 0; i < cars.size(); i++) {
			carsCoordinates.add(new Point(coordinates.get(0).x + 4, min.y
					+ (i + 1)));
		}
	}

	public static void generateDemand(int n) {
		demand.clear();
		for (int i = 0; i < n; i++) {
			demand.add(random(1, n));
		}
	}

	public static int random(int start, int end) {
		return start + (int) (end * Math.random());
	}

	public static void main(String[] args) {
		VRGframe frame = new VRGframe();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
