package vrg;

import vrg.VRGUtils.Point;
import java.util.*;

public class VRG {
	/*
	 * public static final int[][] COORDINATES = { { 19, 45 }, { 18, 46 }, { 20,
	 * 47 }, { 22, 42 }, { 20, 41 }, { 14, 40 }, { 12, 44 }, { 13, 45 } };
	 */

	public static final int[][] COORDS = { { 4, 3 }, { 5, 2 }, { 3, 1 }, { 1, 6 }, { 3, 7 }, { 9, 8 }, { 11, 4 }, { 10, 3 } };
	public static final int[][] TIMES = { { 1, 3 }, { 2, 5 }, { 1, 5 }, { 1, 6 }, { 3, 7 }, { 4, 8 }, { 5, 11 }, { 10, 12 } };
	public static final int[] DEMAND = { 0, 1, 1, 1, 2, 2, 2, 1 };
	public static final int[] PRICE = { 0, 4, 4, 4, 4, 4, 4, 4 };
	public static final int[] CARS_WEIGHT = { 3, 3, 5 };
	public static final int[][] CARS = { { 11, 39 }, { 15, 39 }, { 20, 39 } };
	public static final int[][] ROUTES = { { 0, 1, 2, 0 }, { 0, 3, 4, 0 }, { 0, 5, 6, 7, 0 } };

	public static int countCoords = 8;
	public static int countCars = 3;
	public static final int ZOOM = 5;
	public static int numberProfits = 0;
	public static boolean withTimeWindow = false;// FIXME
	public static int timeWindow = 0;
	public static ArrayList<Integer> indexInWindow = new ArrayList<Integer>();

	public static ArrayList<Integer> cars = new ArrayList<Integer>();
	public static ArrayList<Integer> price = new ArrayList<Integer>();
	public static ArrayList<Integer> demand = new ArrayList<Integer>();
	public static ArrayList<Point> coordinates = new ArrayList<Point>();
	public static ArrayList<Point> carsCoordinates = new ArrayList<Point>();
	public static ArrayList<ArrayList<Integer>> routes = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<ArrayList<Double>> lengthOfRoutes = new ArrayList<ArrayList<Double>>();
	public static ArrayList<Double> benefits = new ArrayList<Double>();

	public static boolean isValid() {
		return (coordinates != null) && (coordinates.size() > 0) && (cars != null) && (cars.size() > 0);
	}

	public static void generateAllStandart() {
		clearAll();

		if (withTimeWindow) {
			generateAllStandartWithTimeWindow();
			return;
		}

		for (int i = 0; i < countCoords; i++) {
			coordinates.add(new Point(COORDS[i][0], COORDS[i][1]));
			price.add(PRICE[i]);
			demand.add(DEMAND[i]);
		}

		cars.add(CARS_WEIGHT[0]);
		for (int i = 0; i < countCars; i++) {
			cars.add(CARS_WEIGHT[i]);

			ArrayList<Integer> tmp = new ArrayList<Integer>();
			for (int j = 0; j < ROUTES[i].length; j++) {
				tmp.add(ROUTES[i][j]);
			}
			routes.add(tmp);
		}

		fillCarsCoords();
		createTableOfRoutes();
	}

	public static void generateAll(int n) {
		clearAll();
		// countCoords = n;
		generateCoordinates(n);
		generateCars(n);
		generateDemand(n);
		generatePrice(n);
		createTableOfRoutes();
		generateGraphRoutes();
	}

	public static void clearAll() {
		withTimeWindow = false;
		countCars = CARS.length;
		countCoords = COORDS.length;
		coordinates.clear();
		price.clear();
		demand.clear();
		cars.clear();
		lengthOfRoutes.clear();
		routes.clear();
		carsCoordinates.clear();
		benefits.clear();
	}

	public static ArrayList<Integer> generateAllStandartWithTimeWindow() {
		coordinates.clear();
		indexInWindow.clear();
		routes.clear();

		ArrayList<Integer> indexes = new ArrayList<Integer>();

		Point depo = new Point(COORDS[0][0], COORDS[0][1]);
		coordinates.add(depo);

		for (int i = 1; i < countCoords; i++) {
			Point vertex = new Point(COORDS[i][0], COORDS[i][1]);
			if (checkVertex(vertex, depo)) {
				indexInWindow.add(i);
				coordinates.add(vertex);
				price.add(PRICE[i]);
				demand.add(DEMAND[i]);
			} else {
				indexes.add(i);
			}
		}
		countCoords -= indexes.size();
		generateRoutesWithTimeWindow(new ArrayList<Integer>(indexInWindow));
		return indexes;

	}

	private static void generateRoutesWithTimeWindow(ArrayList<Integer> in) {
		routes.clear();
		generateGraphRoutes();

		cars.add(CARS_WEIGHT[0]);
		for (int i = 0; i < countCars; i++) {
			cars.add(CARS_WEIGHT[i]);

			ArrayList<Integer> tmp = new ArrayList<Integer>();
			for (int j = 0; j < random(0, in.size()); j++) {
				int k = random(0, in.size());
				tmp.add(in.get(k));
				in.remove(k);
			}
			routes.add(tmp);
		}

		fillCarsCoords();
		createTableOfRoutes();
	}

	private static boolean checkVertex(Point v, Point d) {
		return Math.sqrt((v.x - d.x) * (v.x - d.x) + (v.y - d.y) * (v.y - d.y)) <= timeWindow;
	}

	public static void generateCoordinates(int n) {
		coordinates.clear();
		int x = VRGUtils.windowWidth;
		int y = VRGUtils.windowHeight;
		coordinates.add(new Point(random(x / 20, x / 2), random(y / 20, y / 2)));
		for (int i = 1; i < n; i++) {
			coordinates.add(new Point(random(y / 20, y), random(y / 20, y)));
		}
		// generatePrice(random(x / 20, x));
		// generateDemand(random(y / 5, y));
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
				String s = getDistanceText(coordinates.get(i), coordinates.get(j));
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

		Collections.shuffle(indexes);// FIXME XXX
		return indexes;
	}

	public static void generateGraphRoutes() {
		routes.clear();

		ArrayList<Integer> tmp = new ArrayList<Integer>();

		Queue<Integer> indexQ = new LinkedList<Integer>();
		indexQ.addAll(getCoordsIndexes());

		if (indexQ == null || coordinates == null || coordinates.size() < 2 || indexQ.size() < 2) {
			return;
		}
		for (int i = 0; i < countCars; i++) {
			tmp = new ArrayList<Integer>();
			tmp.add(0);
			int m = random(1, Math.max(indexQ.size() / 2, 0));
			for (int j = 0; j < Math.min(indexQ.size(), m); j++) {
				tmp.add(indexQ.poll());
			}
			Collections.sort(tmp);
			tmp.add(0);
			routes.add(tmp);
		}
	}

	public static Boolean searchOptimalSolutions(Double oldProfits) {
		generateOptimalRoutes(2);
		// generateAllRoutes(5);
		Double allProfits = getSumDoubleArr(benefits);
		if (allProfits.intValue() == oldProfits.intValue()) {
			numberProfits += countCoords / 3;
		} else {
			numberProfits = 0;
		}
		if ((allProfits.intValue() == oldProfits.intValue()) && (oldProfits.intValue() == 0)) {
			numberProfits += countCoords / 4;
		}
		return numberProfits > countCoords;
	}

	public static void generateOptimalRoutes() {
		generateOptimalRoutes(1);
	}

	public static void generateOptimalRoutes(int accuracy) {
		routes.clear();
		benefits.clear();
		if (lengthOfRoutes.size() < 1) {
			generateGraphRoutes();
		}

		ArrayList<Double> localBenefits = new ArrayList<Double>();

		ArrayList<ArrayList<Integer>> pathMaxDel = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> pathMax = new ArrayList<ArrayList<Integer>>();

		for (int i = 1; i < cars.size(); i++) {

			ArrayList<ArrayList<Integer>> allRoutes = getRoutes(cars.get(i), demand, accuracy);
			ArrayList<ArrayList<Integer>> copy = new ArrayList<ArrayList<Integer>>(allRoutes);
			for (ArrayList<Integer> tmp : pathMax) {
				deleteArray(copy, tmp);
			}

			ArrayList<Integer> paths = new ArrayList<Integer>();

			for (ArrayList<Integer> arr : copy) {
				Double result = 0D;
				if (arr.size() < 1) {
					continue;
				}

				int index = 0;
				for (int j = 1; j < arr.size() - 1; j++) {
					Double sum = 0D;
					index = arr.get(j);
					sum += price.get(index) * demand.get(index);
					sum -= lengthOfRoutes.get(index).get(arr.get(j - 1));
					result += sum;
					paths.add(index);
				}
				result -= lengthOfRoutes.get(0).get(index);

				if (result > 0) {
					benefits.add(result);
					pathMaxDel.add(new ArrayList<Integer>(paths));
				}
				paths.clear();
			}

			if (benefits.size() < 1) {
				localBenefits.add(0D);
				pathMax.add(new ArrayList<Integer>());
				continue;
			}
			Double max = Collections.max(benefits);
			int in = benefits.indexOf(max);

			localBenefits.add(max);
			pathMax.add(pathMaxDel.get(in));
			deleteArray(routes, pathMaxDel.get(in));

			pathMaxDel.clear();
			benefits.clear();
		}
		createOptimumRoutesAndBenefits(pathMax, localBenefits);
	}

	public static void generateAllRoutes(int accuracy) {
		routes.clear();
		benefits.clear();
		if (lengthOfRoutes.size() < 1) {
			generateGraphRoutes();
		}

		ArrayList<ArrayList<ArrayList<Integer>>> pathMax = new ArrayList<ArrayList<ArrayList<Integer>>>();
		ArrayList<ArrayList<ArrayList<Integer>>> pathAll = new ArrayList<ArrayList<ArrayList<Integer>>>();
		ArrayList<Double> benefitsRoutes = new ArrayList<Double>();

		for (int i = 1; i < cars.size(); i++) {
			ArrayList<ArrayList<Integer>> allRoutes = getRoutes(cars.get(i), demand, accuracy, true);
			pathAll.add(allRoutes);
		}

		ArrayList<ArrayList<ArrayList<Integer>>> paths = constructRoutes(pathAll);
		VRGfile.write(paths.size());
		VRGfile.write(paths.toString());

		for (ArrayList<ArrayList<Integer>> allRoutes : paths) {
			for (ArrayList<Integer> arrr : allRoutes) {
				ArrayList<Integer> arr = new ArrayList<Integer>(arrr);
				Double result = 0D;
				if (arr.size() < 1) {
					continue;
				}
				arr.add(0, 0);
				arr.add(0);

				int index = 0;
				for (int j = 1; j < arr.size() - 1; j++) {
					Double sum = 0D;
					index = arr.get(j);
					sum += price.get(index) * demand.get(index);
					sum -= lengthOfRoutes.get(index).get(arr.get(j - 1));
					result += sum;
				}
				result -= lengthOfRoutes.get(0).get(index);

				benefits.add(VRGUtils.getDouble(result));
			}
			if (getSumDoubleArr(benefits) > 0D) {
				VRGfile.write(allRoutes.toString());
				benefitsRoutes.add(getSumDoubleArr(benefits));
				pathMax.add(allRoutes);
			}
			benefits.clear();
		}
		if (benefitsRoutes.size() < 1) {
			benefitsRoutes.add(0D);
			pathMax.add(new ArrayList<ArrayList<Integer>>());
		}
		Double max = Collections.max(benefitsRoutes);
		int in = benefitsRoutes.indexOf(max);

		VRGfile.write(pathMax.get(in).toString());
		createOptimumRoutesAndBenefits(pathMax.get(in));
	}

	private static void createOptimumRoutesAndBenefits(ArrayList<ArrayList<Integer>> arrayList) {
		routes.clear();
		benefits.clear();

		for (ArrayList<Integer> arrr : arrayList) {
			ArrayList<Integer> arr = new ArrayList<Integer>(arrr);
			Double result = 0D;
			arr.add(0, 0);
			arr.add(0);

			int index = 0;
			for (int j = 1; j < arr.size() - 1; j++) {
				Double sum = 0D;
				index = arr.get(j);
				sum += price.get(index) * demand.get(index);
				sum -= lengthOfRoutes.get(index).get(arr.get(j - 1));
				result += sum;
			}
			result -= lengthOfRoutes.get(0).get(index);

			routes.add(arr);
			benefits.add(VRGUtils.getDouble(result));
		}
	}

	private static ArrayList<ArrayList<ArrayList<Integer>>> constructRoutes(ArrayList<ArrayList<ArrayList<Integer>>> pathAll) {

		Set<ArrayList<ArrayList<Integer>>> allroutes = new HashSet<ArrayList<ArrayList<Integer>>>();
		ArrayList<ArrayList<Integer>> firstLine = new ArrayList<ArrayList<Integer>>(pathAll.get(0));
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		ArrayList<Integer> indexes0 = new ArrayList<Integer>();

		for (int l = 0; l < firstLine.size(); l++) {
			ArrayList<ArrayList<ArrayList<Integer>>> paths = new ArrayList<ArrayList<ArrayList<Integer>>>();
			ArrayList<ArrayList<Integer>> arrValue = new ArrayList<ArrayList<Integer>>();
			arrValue.add(firstLine.get(l));

			indexes.clear();
			indexes0.clear();
			int n = 1;
			for (int i = 1; i < pathAll.size(); i++) {
				indexes.add(pathAll.get(i).size() - 1);
				n = getSumIntArr(indexes);
				indexes0.add(0);
			}
			indexes0.add(0);

			int m = 0;
			while (m != n) {
				ArrayList<ArrayList<Integer>> arr2dim = new ArrayList<ArrayList<Integer>>(arrValue);

				for (int i = 1; i < pathAll.size(); i++) {
					arr2dim.add(pathAll.get(i).get(indexes0.get(i - 1)));
				}
				paths.add(new ArrayList<ArrayList<Integer>>(arr2dim));

				m = incrementIndex(indexes0, indexes, 0);
			}
			getNotContainsValue(paths);
			allroutes.addAll(paths);
		}

		return new ArrayList<ArrayList<ArrayList<Integer>>>(allroutes);
	}

	private static int incrementIndex(ArrayList<Integer> indexes0, ArrayList<Integer> indexesAll, int j) {
		if (j >= indexesAll.size() || getSumIntArr(indexes0) == getSumIntArr(indexesAll)) {
			VRGfile.write("eerrror");
			return getSumIntArr(indexes0);
		}
		if (getSumIntArr(indexes0) != getSumIntArr(indexesAll))
			if ((indexes0.get(j) - indexesAll.get(j)) == 0) {
				indexes0.set(j, 0);
				incrementIndex(indexes0, indexesAll, j + 1);
			} else {
				indexes0.set(j, indexes0.get(j) + 1);
			}
		return getSumIntArr(indexes0);
	}

	private static void getNotContainsValue(ArrayList<ArrayList<ArrayList<Integer>>> paths) {// FIXME
		for (int i = paths.size() - 1; i >= 0; i--) {
			ArrayList<ArrayList<Integer>> tmp = paths.get(i);
			ArrayList<Integer> all = new ArrayList<Integer>();
			for (int j = 1; j < tmp.size(); j++) {
				ArrayList<Integer> tmpp = tmp.get(j);
				for (int k = j - 1; k >= 0; k--) {
					all.addAll(tmp.get(k));
				}
				tmp.set(j, getDifferenceBetweenSets(tmpp, all));
				all.clear();
				if (tmp.size() < 2) {
					tmp.add(new ArrayList<Integer>());
				}
				if (tmp.size() > 3) {
					tmp.remove(0);
				}
				if (tmp.size() < 3) {
					tmp.add(new ArrayList<Integer>());
				}
			}
		}
	}

	public static void generateEdges() {
		if (getSumDoubleArr(benefits).intValue() == 0) {
			generateGraphRoutes();
		} else {
			generateOptimalRoutes();
		}
	}

	private static void deleteArray(ArrayList<ArrayList<Integer>> copy, ArrayList<Integer> paths) {
		for (int j = copy.size() - 1; j >= 0; j--) {
			for (int p : paths) {
				int in = copy.get(j).indexOf(p);
				if (in != -1) {
					copy.set(j, new ArrayList<Integer>());
					break;
				}
			}
		}
	}

	private static ArrayList<ArrayList<Integer>> getRandomIndexes(int costSize, int accuracy) {
		Set<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();
		int k = 0;
		int count = 0;
		int n = (int) Math.pow(20, accuracy);// FIXME

		for (int j = 0; j < n; j++) {
			ArrayList<Integer> values = new ArrayList<Integer>();
			ArrayList<Integer> valuesShort = new ArrayList<Integer>();

			for (int i = 0; i < costSize - 1; i++) {
				values.add(i + 1);
			}
			Collections.shuffle(values);
			Collections.shuffle(values);

			for (int i = 0; i < random(1, values.size()); i++) {
				valuesShort.add(values.get(i));
			}
			Collections.sort(valuesShort);
			k = set.size();
			set.add(valuesShort);
			if (k != set.size()) {
				count = 0;
			} else {
				count++;
			}
			if (count > countCoords) {
				break;
			}
		}
		return new ArrayList<ArrayList<Integer>>(set);
	}

	private static ArrayList<ArrayList<Integer>> getRoutes(int max, ArrayList<Integer> costs, int accuracy) {
		return getRoutes(max, costs, accuracy, false);
	}

	private static ArrayList<ArrayList<Integer>> getRoutes(int max, ArrayList<Integer> costs, int accuracy, boolean b) {

		ArrayList<ArrayList<Integer>> allRoutes = getRandomIndexes(costs.size(), accuracy);
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

		for (ArrayList<Integer> arr : allRoutes) {
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			for (int i : arr) {
				tmp.add(costs.get(i));
			}

			if (getSumIntArr(tmp) <= max) {
				tmp = new ArrayList<Integer>(arr);
				if (!b) {
					tmp.add(0, 0);
					tmp.add(0);
				}
				result.add(tmp);
			}
		}
		return result;
	}

	private static void createOptimumRoutesAndBenefits(ArrayList<ArrayList<Integer>> pathMax, ArrayList<Double> benefitss) {
		routes.clear();
		benefits.clear();
		for (ArrayList<Integer> tmp : pathMax) {
			tmp.add(0, 0);
			tmp.add(0);
			routes.add(tmp);
		}
		benefits = new ArrayList<Double>(benefitss);
	}

	public static void constructSolution() {
		ArrayList<Double> benefitsRoutes = new ArrayList<Double>();

		ArrayList<ArrayList<ArrayList<Integer>>> pathMax = new ArrayList<ArrayList<ArrayList<Integer>>>();
		for (int i = 1; i < 5; i++) {
			generateAllRoutes(i);
			pathMax.add(routes);
			benefitsRoutes.add(getSumDoubleArr(benefits));
		}
		Double max = Collections.max(benefitsRoutes);
		int in = benefitsRoutes.indexOf(max);

		// createOptimumRoutesAndBenefits(pathMax.get(in));
		routes = new ArrayList<ArrayList<Integer>>(pathMax.get(in));
		VRGfile.write(pathMax.get(in).toString());
	}

	private static int getSumIntArr(ArrayList<Integer> path) {
		int result = 0;
		for (int i : path) {
			result += i;
		}
		return result;
	}

	private static Double getSumDoubleArr(ArrayList<Double> path) {
		Double result = 0D;
		for (double i : path) {
			result += i;
		}
		return result;
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
		Point max = getMaxMinX(true);

		for (int i = 0; i < cars.size(); i++) {
			carsCoordinates.add(new Point(max.x + VRGUtils.windowWidth / 20, i * (VRGUtils.windowHeight / cars.size())));
		}
	}

	public static String getDistanceText(Object p1, Object p2) {
		Double distanse = 0D;
		if (p1 instanceof Point) {
			distanse = getDistance(Point.class.cast(p1), Point.class.cast(p2));
		}
		return VRGUtils.df.format(distanse).replace(",", ".");
	}

	public static double getDistance(Point p1, Point p2) {
		return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
	}

	public static int random(int start, int end) {
		Random rand = new Random();
		if (start >= end)
			return (int) (start + rand.nextInt(start - end + 1));// FIXME
		else
			return (int) (start + rand.nextInt(end - start));
	}

	public static int random(int start, int end, int oldValue) {
		int result = 0;

		do {
			result = random(start, end);
		} while (result == oldValue);

		return result;
	}

	public static ArrayList<Integer> getDifferenceBetweenSets(ArrayList<Integer> path, ArrayList<Integer> first) {
		ArrayList<Integer> diff = new ArrayList<Integer>(path);

		diff.removeAll(first);
		Collections.sort(diff);

		return diff;
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
			coordinates.set(row, new Point(random(1, VRGUtils.windowWidth), random(1, VRGUtils.windowHeight)));
			break;
		}
		case 2: {
			demand.set(row, random(1, countCoords, demand.get(row)));
			break;
		}
		case 3: {
			price.set(row, random(1, countCoords, price.get(row)));
			break;
		}
		}
	}

	public static void generateCarsTableAtIndex(int column) {
		if (column == 0) {
			Collections.fill(cars, 35);
		} else
			cars.set(column, random(1, countCars * 2, cars.get(column)));
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

	public static Point getMaxCoords() {
		Point result;

		Point maxX = getMaxMinX(true);
		Point maxY = getMaxMinY(true);
		result = new Point(maxX.x, maxY.y);
		return result;
	}

	public static Point getMinCoords() {
		Point result;

		Point minX = getMaxMinX(false);
		Point minY = getMaxMinY(false);
		result = new Point(minX.x, minY.y);
		return result;
	}

	private static Point getMaxMinX(boolean isMax) {
		Point x;
		if (isMax) {
			x = Collections.max(coordinates, new Comparator<Point>() {
				@Override
				public int compare(Point paramInt1, Point paramInt2) {
					return Integer.compare(paramInt1.x, paramInt2.x);
				}
			});
		} else {
			x = Collections.min(coordinates, new Comparator<Point>() {
				@Override
				public int compare(Point paramInt1, Point paramInt2) {
					return Integer.compare(paramInt1.x, paramInt2.x);
				}
			});
		}
		return x;
	}

	private static Point getMaxMinY(boolean isMax) {
		Point y;
		if (isMax) {
			y = Collections.max(coordinates, new Comparator<Point>() {
				@Override
				public int compare(Point paramInt1, Point paramInt2) {
					return Integer.compare(paramInt1.y, paramInt2.y);
				}
			});
		} else {
			y = Collections.min(coordinates, new Comparator<Point>() {
				@Override
				public int compare(Point paramInt1, Point paramInt2) {
					return Integer.compare(paramInt1.y, paramInt2.y);
				}
			});
		}
		return y;
	}

	public static void readTableFromFile(java.awt.Component parent) {
		ArrayList<VRGvertexes> vertexes = VRGfile.readFromFile(parent);
		clearAll();
		if (vertexes == null) {
			countCoords = 0;
			countCars = 1;
			return;
		}
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

	public static void nonKoopGener(int accuracy) {
		routes.clear();
		benefits.clear();
		if (lengthOfRoutes.size() < 1) {
			generateGraphRoutes();
		}

		ArrayList<Double> localBenefits = new ArrayList<Double>();

		ArrayList<ArrayList<Integer>> pathMaxDel = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> pathMax = new ArrayList<ArrayList<Integer>>();

		for (int i = 1; i < cars.size(); i++) {

			ArrayList<ArrayList<Integer>> allRoutes = getRoutes(cars.get(i), demand, accuracy);
			ArrayList<ArrayList<Integer>> copy = new ArrayList<ArrayList<Integer>>(allRoutes);
			for (ArrayList<Integer> tmp : pathMax) {
				deleteArray(copy, tmp);
			}

			ArrayList<Integer> paths = new ArrayList<Integer>();

			for (ArrayList<Integer> arr : copy) {
				Double result = 0D;
				if (arr.size() < 1) {
					continue;
				}

				int index = 0;
				for (int j = 1; j < arr.size() - 1; j++) {
					Double sum = 0D;
					index = arr.get(j);
					sum += price.get(index) * demand.get(index);
					sum -= lengthOfRoutes.get(index).get(arr.get(j - 1));
					result += sum;
					paths.add(index);
				}
				result -= lengthOfRoutes.get(0).get(index);

				if (result > 0) {
					benefits.add(result);
					pathMaxDel.add(new ArrayList<Integer>(paths));
				}
				paths.clear();
			}

			if (benefits.size() < 1) {
				localBenefits.add(0D);
				pathMax.add(new ArrayList<Integer>());
				continue;
			}
			Double max = Collections.max(benefits);
			int in = benefits.indexOf(max);

			localBenefits.add(max);
			pathMax.add(pathMaxDel.get(in));
			deleteArray(routes, pathMaxDel.get(in));

			pathMaxDel.clear();
			benefits.clear();
		}
		createOptimumRoutesAndBenefits(pathMax, localBenefits);
	}

	public static void nGener(int accuracy) {
		if (accuracy == 1) {
			nonKoopGener(1);
			return;
		}

		/*
		 * int n = readInt(); int k = readInt();
		 * 
		 * PriorityQueue<Long> servers = new PriorityQueue<Long>(); for (int i =
		 * 0; i < k; i++) {servers.add(0L);} for (int i = 0; i < n; i++) { int s
		 * = readInt(); int m = readInt(); long end = Math.max(servers.poll(),
		 * s) + m; servers.add(end); out.println(end); }
		 */
	}

	public static void main(String[] args) {
		VRGframe frame = new VRGframe();
		frame.setVisible(true);
	}
}
