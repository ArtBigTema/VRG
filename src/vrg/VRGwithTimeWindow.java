package vrg;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;

import vrg.VRGUtils.Point;
import vrg.VRGwithTimeWindow.PointT.C.OptimalPoint;

public class VRGwithTimeWindow implements VRGgeneratorListener {
	private static DecimalFormat df = new DecimalFormat("#.#");// FIXME
	private static final PrintWriter out = new PrintWriter(System.out);
	private static final boolean showGraph = true;
	private static final boolean forTaxi = true;
	private static boolean newForEach = false;
	private static VRGgraphOld frame;

	public static final int MAX_TIME = 100;
	public static final int[][] COORDS = { { 0, 0 }, { 0, 3 }, { 4, 0 }, { 4, 3 }, { 4, 5 }, { 8, 3 }, { 0, 6 }, { 8, 6 },
			{ 2, 6 }, { 8, 0 } };
	public static final int[][] COORDS_END = { { 0, 0 }, { 1, 3 }, { 7, 4 }, { 7, 7 }, { 7, 1 }, { 2, 3 }, { 3, 2 }, { 5, 6 },
			{ 4, 6 }, { 8, 4 } };
	public static final int[] CARS_WEIGHT = { 3, 3, 5 };
	public static final int[][] TIMES = { { 0, MAX_TIME }, { 1, 5 }, { 3, 7 }, { 5, 7 }, { 9, 20 }, { 15, 19 }, { 2, 3 },
			{ 20, 30 }, { 1, 1 }, { 15, 18 } };

	public static ArrayList<Integer> carsWeight = new ArrayList<Integer>();
	public static ArrayList<PointT> coordinates = new ArrayList<PointT>();
	public static VRGroutes routess;

	public static ArrayList<ArrayList<Double>> lengthOfRoutes = new ArrayList<ArrayList<Double>>();
	public static ArrayList<Integer> allIndexes = new ArrayList<Integer>();
	public static ArrayList<ArrayList<Integer>> routes = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<PointT> passedPath;
	public static PointT.C.OptimalPoint optimalPoint = OptimalPoint.OptimalDistance;
	public static Queue<PointT> newPointT = new LinkedList<PointT>();
	public static ArrayList<Pair> cars;

	@Override
	public void generated(PointT t) {
		newPointT.add(t);

		if (newForEach) {
			coordinates.add(t);
			openPlace();
			sortX();
			generateLengthRoutesAndTable();
			solve();

			updateGraph();
		}
	}

	@Override
	public void started() {
		newForEach = !newForEach;// FIXME
		optimalPoint = OptimalPoint.OptimalTime;
	}

	@Override
	public void stoped(int count) {
		p("new points");
		for (PointT t : newPointT) {
			p(t.toSt());
		}
		if (newForEach) {
			addNewPoints();// at last time
			updateGraph();
		}
		// System.exit(0);//FIXME
	}

	private void addNewPoints() {
		coordinates.addAll(newPointT);
		openPlace();
		sortX();
		pp(coordinates);
		generateLengthRoutesAndTable();

		solve();
		showGraphIfNeed();
		newPointT.clear();
	}

	@Override
	public void generated(Point t) {
		// FIXME
	}

	public VRGwithTimeWindow() {
		VRGStaticData data = new VRGStaticData();
		data.setListener(this);

		printWithEnd();
		generateAllStandart();
		sortX();

		pp(coordinates);

		data.setMaxPoint(getMaxXPoint(), getMaxYPoint());
		data.setMinPoint(getMinXPoint(), getMinYPoint());

		generateLengthRoutesAndTable();

		print();

		optimalPoint = OptimalPoint.OptimalTime;
		solve();// constructRoutes();//FIXME
		out.flush();

		showGraphIfNeed();

		data.startTimer();// FIXME
	}

	public static boolean isValid() {
		return (coordinates != null) && (coordinates.size() > 0) && (carsWeight != null) && (carsWeight.size() > 0);
	}

	public static void clearAll() {
		clearRoutess();
		carsWeight.clear();
		coordinates.clear();
		lengthOfRoutes.clear();
		allIndexes.clear();
		routes.clear();
		// table.clear();
		optimalPoint = OptimalPoint.OptimalTime;
		generateRoutess();
	}

	private static void clearRoutess() {
		if (routess != null) {
			routess.clear();
		}
		openPlace();
	}

	private static void generateRoutess() {
		routess = new VRGroutes();
	}

	public static VRGroutes getRoutess() {
		return routess;
	}

	public static void generateAllStandart() {
		clearAll();
		for (int i = 0; i < COORDS.length; i++) {
			PointT p = new PointT(COORDS[i][0], COORDS[i][1]);
			p.setTimeWindow(TIMES[i][0], TIMES[i][1]);
			p.setDelay(1);
			if (forTaxi) {
				p.setEndPlace(COORDS_END[i][0], COORDS_END[i][1]);
			}
			coordinates.add(p);
		}
		coordinates.get(0).setDelay(0);// FIXME
		for (int i = 0; i < CARS_WEIGHT.length; i++) {
			carsWeight.add(CARS_WEIGHT[i]);
		}
	}

	public static int getCountCoords() {
		return coordinates.size();
	}

	public static void generateAll(int n) {
		clearAll();
		generateCoordinates(n);
		generateCars(n);
		sort();
		generateLengthRoutesAndTable();
		print();
	}

	public static void generateNewRows(int n) {
		generateCoordinates(n);
		sort();
		generateLengthRoutesAndTable();
		print();
	}

	public static void generateCoordinates(int n) {
		int x = VRGUtils.windowWidth;
		int y = VRGUtils.windowHeight;
		Point min = new Point(x / 20, y / 20);
		Point max = new Point(x, y);
		if (coordinates != null && coordinates.size() > 0) {
			min.x = Collections.min(coordinates, new PointT.C.Cx()).x;
			min.y = Collections.min(coordinates, new PointT.C.Cy()).y;
			max.x = Collections.max(coordinates, new PointT.C.Cx()).x;
			max.y = Collections.max(coordinates, new PointT.C.Cy()).y;
		}

		coordinates.add(new PointT(random(min.x, max.x), random(min.y, max.y)));
		for (int i = 0; i < n; i++) {
			PointT p = new PointT(random(min.x, max.x), random(min.y, max.y));
			p.setDelay(random(1, 5));
			p.setTimeWindow(random(1, MAX_TIME / 2), random(MAX_TIME / 2, MAX_TIME));
			if (forTaxi) {
				p.setEndPlace(random(min.x, max.x), random(min.y, max.y));
			}
			coordinates.add(p);
		}
	}

	public static void generateCars(int n) {
		for (int i = 0; i < n; i++) {
			carsWeight.add(random(1, n));
		}
	}

	public static int[][] getRoutes() {
		if (routes == null || routes.size() == 0) {
			sortX();// FIXME
			generateLengthRoutesAndTable();
			solve();// constructRoutes();
		}
		int n = routes.size();
		int[][] paths = new int[n][n];
		for (int i = 0; i < n; i++) {
			paths[i] = new int[lengthOfRoutes.get(i).size()];
			for (int j = 0; j < routes.get(i).size(); j++) {
				paths[i][j] = routes.get(i).get(j);
			}
		}
		return paths;
	}

	public static ArrayList<ArrayList<Integer>> getRoutesAll() {
		if (routes == null || routes.size() == 0) {
			optimalPoint = OptimalPoint.OptimalDistance;
			generateLengthRoutesAndTable();
			solve();
		}
		return routes;
	}

	public static Double getDelayOfRoutes(int j) {
		if (j >= cars.size() || j < 0) {
			err("See getLengthOfRoutes");
		}
		return cars.get(j).delay;
	}

	public static Double getLengthOfRoutes(int j) {
		if (j >= cars.size() || j < 0) {
			err("See getLengthOfRoutes");
		}
		return cars.get(j).distance;
	}

	public static ArrayList<Integer> getRoutes(int j) {
		if (routes == null || routes.size() == 0) {
			generateLengthRoutesAndTable();

			print();

			optimalPoint = OptimalPoint.OptimalTime;
			solve();
		}
		return routes.get(j);
	}

	public static int getCountCars() {
		return carsWeight.size();
	}

	public static void err(Object o) {
		System.err.println(o.toString());
	}

	public static void generateLengthRoutesAndTable() {
		for (int i = 0; i < coordinates.size(); i++) {
			openPlace();
			allIndexes.add(i);
			ArrayList<Double> tmp = new ArrayList<Double>();
			Queue<Double> q = new LinkedList<Double>();

			for (int j = 0; j < coordinates.size(); j++) {
				Double d = getDistance(coordinates.get(i), coordinates.get(j));

				if (d == 0.0D)
					tmp.add(Double.NaN);
				else
					tmp.add(Double.parseDouble(df.format(d).replace(",", ".")));
			}
			for (int j = i; j < coordinates.size(); j++) {
				q.add(tmp.get(j));
			}
			lengthOfRoutes.add(new ArrayList<Double>(tmp));
		}
	}

	public static void main(String[] args) {
		new VRGwithTimeWindow();
	}

	public static void readFromFile(java.awt.Component parent) {
		clearAll();
		coordinates = VRGfile.readCoordFromFile(parent);
		carsWeight = VRGfile.readCarsFromFile();
		if (coordinates == null || carsWeight == null) {
			return;
		}
		sort();
	}

	private static void printWithEnd() {
		for (int i = 0; i < COORDS.length; i++) {
			p(i + " " + COORDS[i][0] + " " + COORDS[i][1] + " - " + COORDS_END[i][0] + " " + COORDS_END[i][1] + ", "
					+ TIMES[i][0] + " " + TIMES[i][1]);
		}
	}

	private static void sort() {
		Collections.sort(coordinates, new PointT.C.Cx());
		p("��������������� ���������� �� �");
		pp(coordinates);
		Collections.sort(coordinates, new PointT.C.Cf());
		p("��������������� ���������� �� fi");
		pp(coordinates);
		Collections.sort(coordinates, new PointT.C.Cy());
		p("��������������� ���������� �� Y");
		pp(coordinates);
		Collections.sort(coordinates, new PointT.C.Cr());
		p("��������������� ���������� �� r");
		pp(coordinates);
		sortX();
	}

	private static void sortX() {
		Collections.sort(coordinates, new PointT.C.Cx());
	}

	private static void showGraphIfNeed() {
		if (showGraph) {
			showGraph();
		}
	}

	private void updateGraph() {
		if (frame != null && !newForEach) {
			frame.updateRoutess(routess);
		} else {
			frame = new VRGgraphOld(routess);
		}
	}

	public static void showGraph() {// FIXME
		frame = new VRGgraphOld(routess);
		frame.withTimeWindow = true;
		frame.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent paramFocusEvent) {
				// System.exit(0);
			}

			@Override
			public void focusGained(FocusEvent paramFocusEvent) {

			}
		});
	}

	public static ArrayList<Point> getCoords() {
		ArrayList<Point> coord = new ArrayList<Point>();
		for (Point p : coordinates) {
			coord.add(p);
		}
		return coord;
	}

	public static PointT getCoords(int index) {
		return coordinates.get(index);
	}

	public static Integer getDelays(int index) {
		return (int) coordinates.get(index).getDelay();
	}

	public static String getStartEnd(int index) {
		PointT p = coordinates.get(index);
		return p.start + VRGUtils.ARROW + p.end;
	}

	public static Integer getCars(int index) {
		return carsWeight.get(index);
	}

	public static String generateOptim(boolean isOptim) {// button an solve
		if (isOptim) {
			optimalPoint = OptimalPoint.OptimalDistance;
			return generateRoutesRand();
		} else {
			optimalPoint = OptimalPoint.OptimalTime;
			return generateRoutesRand();
		}
	}

	public static String generateRoutesRand() {// button an solve
		String s = randomSort();
		generateLengthRoutesAndTable();
		openPlace();
		routes.clear();
		try {
			solve();// constructRoutes();// solve
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public static void constructSolution() {// button best solve
		sortX();
		generateLengthRoutesAndTable();
		openPlace();
		routes.clear();
		try {
			optimalPoint = OptimalPoint.OptimalDistance;
			solve();// constructRoutes();// solve
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String randomSort() {
		int k = random(0, 4);
		Comparator<PointT> c = null;
		switch (k) {
		case 0:
			c = new PointT.C.Cx();
			break;
		case 1:
			c = new PointT.C.Cy();
			break;
		case 2:
			c = new PointT.C.Cr();
			break;
		case 3:
			c = new PointT.C.Cf();
			break;
		default:
			c = new PointT.C.Cx();
			break;
		}
		Collections.sort(coordinates, c);
		p("��������������� ���������� �� ������� " + c.toString());
		pp(coordinates);
		return c.toString();
	}

	private static void print() {
		p("");
		p("������� ���� �����");
		p(lengthOfRoutes);
		p("");
	}

	public static void p(Object o) {
		out.println(o.toString());
		out.flush();
	}

	public static void pp(List<PointT> pp) {
		for (PointT p : pp) {
			p(pp.indexOf(p) + " " + p.toS());
		}
	}

	public static void p(LinkedList<Queue<Double>> oo) {
		for (Queue<Double> o : oo) {
			p(o.toString());
		}
	}

	public static void p(ArrayList<ArrayList<Double>> oo) {
		for (ArrayList<Double> o : oo) {
			p(o.toString());
		}
	}

	public static void p(Point o) {
		out.print("(" + o.x + ", " + o.y + "), ");
		out.flush();
	}

	public static double getDistance(Point p1, Point p2) {
		return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
	}

	public static Double getMin(ArrayList<Double> tmp) {
		Double x = Collections.min(tmp);
		return x;
	}

	public static Double getMin(Queue<Double> tmp) {
		Double x = Collections.min(tmp);
		return x;
	}

	private static void solve() {// for taxi optimDistance
		clearRoutess();
		int k = getCountCars();

		ArrayList<Integer> oldPath = new ArrayList<Integer>();
		passedPath = new ArrayList<PointT>();
		cars = new ArrayList<Pair>();

		setStartPlaceForCars();
		closeUnnecessaryPlace();

		oldPath.add(0);
		passedPath.add(coordinates.get(0));
		routess.addDepo();
		p(routess);

		for (int i = 0; i < k; i++) {
			PointT optimPlace = getOptimPlace(cars.get(i));
			p(optimPlace.toStr());
			// routess.addSectionForIndex(i, optimPlace.getPoint(),
			// optimPlace.getEndPlace());
			routess.addSectionForIndex(i, optimPlace);

			double endDD = cars.get(i).delay + getDistance(cars.get(i).getLastPoint(), optimPlace) + optimPlace.getDelay();

			p("�������� ����� " + endDD);

			Pair p = new Pair(endDD, coordinates.indexOf(optimPlace), cars.get(i).num);
			p.setLastPlace(optimPlace);
			p.addDistance(getDistance(cars.get(i).getLastPoint(), optimPlace) + optimPlace.getDistance());
			cars.get(i).setNewValue(p);

			p(cars.get(i).toStr());
			oldPath.add(p.place);
			passedPath.add(optimPlace.close());
		}

		p(routess);
		p("passedPath");
		pp(passedPath);
		p("");
		pp(coordinates);
		p("");
		pr(cars);

		ArrayList<PointT> remnant = getRemnantPlace(getDifferenceBetweenSets(allIndexes, oldPath));
		p(remnant + " ��������");
		p("");

		for (int i = 0; i < remnant.size(); i++) {
			PointT t = remnant.get(i);
			p(coordinates.indexOf(t) + " " + t.toStr());

			PointT optimPlace = getOptimPlace(cars, t);
			if (optimPlace == null) {
				err("optim place not found");
				continue;
			}
			p("���������� �� " + optimalPoint.toString());
			p(optimPlace.getEndPlace().toString() + " - " + optimPlace.toStr());

			int carIndex = getNumOfCarsWithPlace(cars, optimPlace) - 1;// car.num
			double waiting = t.start - cars.get(carIndex).delay - getDistance(cars.get(carIndex).getLastPoint(), t);
			if (waiting < 0) {
				waiting = 0.0;
			}
			if (t.end < cars.get(carIndex).delay + getDistance(cars.get(carIndex).getLastPoint(), t)) {
				err("it's late");
			}
			p("�������� ��������� " + df.format(waiting));
			// routess.addSectionForIndex(carIndex, t.getPoint(),
			// t.getEndPlace());
			routess.addSectionForIndex(carIndex, t);

			double endDD = cars.get(carIndex).delay + getDistance(cars.get(carIndex).getLastPoint(), t) + waiting
					+ t.getDelay();
			p("�������� ����� " + df.format(endDD));

			Pair p = new Pair(endDD, coordinates.indexOf(t), cars.get(carIndex).num);
			p.setLastPlace(t);
			p.addDistance(getDistance(cars.get(carIndex).getLastPoint(), t) + t.getDistance());
			cars.get(carIndex).setNewValue(p);

			p(p.toStr());
			p(cars.get(carIndex).toStr());
			oldPath.add(p.place);
			passedPath.add(t.close());
		}

		p("passedPath");
		pp(passedPath);
		p("");
		pp(coordinates);
		p("");
		pr(cars);

		for (Pair p : cars) {
			routes.add(p.path);
		}

		p(routess);
		passedPath.clear();
		allIndexes.clear();
	}

	private static PointT getOptimPlace(ArrayList<Pair> cs, PointT t) {
		switch (optimalPoint) {
		case OptimalDistance:
			return getOptimPlaceForDistance(cs, t);
		case OptimalTime:
			return getOptimPlaceForTime(cs, t);
		default:
			return getOptimPlaceForDistance(cs, t);
		}

	}

	private static PointT getOptimPlaceForTime(ArrayList<Pair> cs, PointT t) {
		ArrayList<Double> endTimes = new ArrayList<Double>();
		for (Pair car : cs) {
			endTimes.add(car.delay + getDistance(car.getLastPoint(), t));
		}
		p(endTimes); // 8.5 10 8
		double min = MAX_TIME;
		int in = -1;
		for (Double time : endTimes) {
			if (time > t.end) {
				err("see getOptimPlaceForTime");// FIXME
				continue;
			}
			if (min > Math.abs(t.start - time)) {// FIXME if (==) minDistance
				min = Math.abs(t.start - time);
				in = endTimes.indexOf(time);
			}
		}
		p(in + " =index, min= " + min);
		if (in == -1) {
			err("see getOptimPlaceForTime");
			return null;
		}
		return cs.get(in).lastPlace;
	}

	private static PointT getOptimPlaceForDistance(ArrayList<Pair> cs, PointT t) {
		ArrayList<Double> endTimes = new ArrayList<Double>();
		for (Pair car : cs) {
			if (car.delay + getDistance(car.getLastPoint(), t) > t.end) {
				err("see getOptimPlaceForDistance");
				endTimes.add(MAX_TIME * 1.0);
			} else {
				endTimes.add(getDistance(car.getLastPoint(), t));
			}
		}
		p(endTimes);
		double min = getMin(endTimes);
		int in = endTimes.indexOf(min);

		if (min == MAX_TIME) {
			err("see getOptimPlaceForDistance not found optim ");
		}

		p(in + " =index, min= " + min);
		return cs.get(in).lastPlace;
	}

	private static ArrayList<PointT> getRemnantPlace(ArrayList<Integer> pathNew) {
		ArrayList<PointT> result = new ArrayList<PointT>();
		for (int i : pathNew) {
			if (!coordinates.get(i).isPassed()) {
				result.add(coordinates.get(i));
			}
		}
		p("���������� ����������");
		pp(result);
		Collections.sort(result, new PointT.C.Cstart());
		p("��������������� ���������� �� ������");
		pp(result);
		return result;
	}

	private static PointT getOptimPlace(Pair car) {
		p("���� ����� ��� ������");
		p(car.toStr());

		ArrayList<PointT> recom = new ArrayList<PointT>();
		for (int i = 1; i < coordinates.size(); i++) {
			PointT t = coordinates.get(i);
			int start = 0, end = 0;
			double distance = 0;

			if (!t.isPassed()) {
				start = t.start;
				end = t.end;
				distance = getDistance(t, car.getLastPoint());

				p("from " + car.place + " to " + i + ", start= " + start + ",\tend= " + end + ", distance= " + distance);
				if (distance > end) {
					p("�� ������");// FIXME close place
					t.close();
				} else {
					if (distance < start) {
						p("������ � ���");
					} else {
						p("������");
					}
					recom.add(t);
				}
			}
		}
		p("");
		pp(recom);
		p("");

		int tmpStart = MAX_TIME;
		PointT optim = null;
		for (PointT t : recom) {
			if (tmpStart >= t.start) {// t.start+distance
				if (tmpStart == t.start) {
					double distance1 = getDistance(t, car.getLastPlace());
					// table.get(coordinates.indexOf(t)).get(car.place);
					double distance2 = getDistance(optim, car.getLastPlace());
					// table.get(coordinates.indexOf(optim)).get(car.place);
					if (distance1 < distance2) {
						tmpStart = t.start;
						optim = t;
					}
				} else {
					tmpStart = t.start;
					optim = t;
				}
			}
		}
		if (optim == null) {
			err("see getOptimPlace from depo");
		} else {
			p(optim);
		}

		return optim;
	}

	public static void pr(ArrayList<Pair> pp) {
		for (Pair p : pp) {
			p(p.toStr());
		}
	}

	private static void setStartPlaceForCars() {
		for (int i = 0; i < getCountCars(); i++) {
			cars.add(new Pair(0, 0, i + 1));
			cars.get(i).setLastPlace(coordinates.get(0));
		}
		closePlace(0);
		p("close depo");
		pp(coordinates);
		routess.setCountRoutess(getCountCars());
	}

	private static void closePlace(int i) {
		coordinates.get(i).close();
	}

	private static void openPlace() {
		for (PointT t : coordinates) {
			t.open();
		}
	}

	private static void closeUnnecessaryPlace() {
		for (int i = 1; i < coordinates.size(); i++) {
			PointT t = coordinates.get(i);
			int start = 0, end = 0;
			double distance = 0;
			start = t.start;
			end = t.end;
			distance = getDistance(t, coordinates.get(0));

			p("from " + 0 + " to " + i + ", start= " + start + ",\tend= " + end + ", distance= " + distance);
			if (distance > end) {
				p("���������, �� ����� �� ������");
				t.close();
			}
		}
		p("");
		pp(coordinates);
		p("");
	}

	public static int getNumOfCarsWithPlace(ArrayList<Pair> s, int place) {
		for (Pair p : s) {
			if (p.equalsPlace(place)) {
				return p.num;
			}
		}
		return 0;
	}

	public static int getNumOfCarsWithPlace(ArrayList<Pair> s, PointT place) {
		for (Pair p : s) {
			if (p.equalsPlace(place)) {
				return p.num;
			}
		}
		return 0;
	}

	public static ArrayList<Integer> getDifferenceBetweenSets(ArrayList<Integer> path, ArrayList<Integer> removed) {
		ArrayList<Integer> diff = new ArrayList<Integer>(path);
		diff.removeAll(removed);
		return diff;
	}

	public static int random(int start, int end) {
		Random rand = new Random();
		if (start >= end)
			return (int) (start + rand.nextInt(start - end + 1));// FIXME
		else
			return (int) (start + rand.nextInt(end - start + 1));
	}

	public static int random(int start, int end, int oldValue) {
		int result = 0;

		do {
			result = random(start, end);
		} while (result == oldValue);

		return result;
	}

	private int getMinYPoint() {
		return Collections.min(coordinates, new VRGwithTimeWindow.PointT.C.Cy()).y;
	}

	private int getMinXPoint() {
		return Collections.min(coordinates, new VRGwithTimeWindow.PointT.C.Cx()).x;
	}

	private int getMaxYPoint() {
		return Collections.max(coordinates, new VRGwithTimeWindow.PointT.C.Cy()).y;
	}

	private int getMaxXPoint() {
		return Collections.max(coordinates, new VRGwithTimeWindow.PointT.C.Cx()).x;
	}

	public static class Pair {
		double delay;
		int num;
		int place;
		double distance;
		ArrayList<Integer> path;
		PointT lastPlace;
		Point lastPoint;

		public Pair(double d, int n) {
			path = new ArrayList<Integer>();
			delay = d;
			num = n;
			place = 0;
			distance = 0;
		}

		public Pair(double d, int i, int n) {
			path = new ArrayList<Integer>();
			if (i != 0) {// FIXME
				delay = d;
			} else {
				delay = 0;
			}
			num = n;
			place = i;
			path.add(i);
			distance = 0;
		}

		public void setNewValue(Pair p) {
			if (num != p.num) {
				err("see car setNewValue ");
			}
			path.add(p.place);
			delay = p.delay;
			num = p.num;
			place = p.place;
			distance += p.distance;
			setLastPlace(p.lastPlace);
		}

		public void setLastPlace(PointT t) {
			lastPlace = t;
			setLastPoint(t.getEndPlace());
		}

		public void setLastPoint(Point t) {
			lastPoint = t;
		}

		public PointT getLastPlace() {
			return lastPlace;
		}

		public Point getLastPoint() {
			if (lastPoint == null) {
				err("see getLastPoint in Pair");
			}
			return lastPoint;
		}

		public void addDistance(double d) {
			distance += d;
		}

		public boolean equalsPlace(int in) {
			return place == in;
		}

		public boolean equalsPlace(PointT t) {
			return this.lastPlace.equals(t);
		}

		public String toStr() {
			return "Num: " + num + ", ����� ����� � ����: " + df.format(delay) + ",  ���� ����: " + path.toString()
					+ " ����� ����: " + df.format(distance);
		}

		@Override
		public String toString() {
			return "Num: " + num + ", delay: " + delay + ",  lastPlace: " + place;
		}
	}

	public static class PointT extends Point {
		public int x;
		public int y;
		public double r;
		public double f;
		public int start;
		public int end;
		public int delay;
		public boolean flag;
		public Point endPlace; // PointT
		public double dis;

		public PointT(int xx, int yy) {
			super(xx, yy);
			x = xx;
			y = yy;
			r = Math.sqrt(x * x + y * y);
			f = Math.toDegrees(Math.atan2(yy, xx));
			start = 0;
			end = Byte.MAX_VALUE;
			delay = 1;
			flag = false;
			dis = 0;
		}

		public PointT(Point t) {
			this(t.x, t.y);
		}

		public double getDistance() {
			return dis;
		}

		public boolean containEndPlace() {
			return endPlace != null;
		}

		public double getDelay() {
			return delay + dis;
		}

		public boolean isPassed() {
			return flag;
		}

		public void setTimeWindow(int s, int e) {
			if (e > s) {
				start = s;
				end = e;
			} else {
				start = e;
				end = s;
			}
		}

		public void setDelay(int d) {
			delay = d;
		}

		public PointT close() {
			flag = true;
			return this;
		}

		public void open() {
			flag = false;
		}

		public Point getPoint() {
			return new Point(x, y);
		}

		public void setEndPlace(int xx, int yy) {
			setEndPlace(new Point(xx, yy));
		}

		public void setEndPlace(Point t) {
			endPlace = t;// FIXME
			delay = 0;// for taxi
			dis = VRGwithTimeWindow.getDistance(this, endPlace);
		}

		public Point getEndPlace() {
			if (endPlace == null) {
				return getPoint();
			}
			return endPlace;
		}

		public String toSt() {
			return flag + " (" + x + ", " + y + ") to " + endPlace.toString() + " " + start + " to " + end + ", distance "
					+ df.format(dis);
		}

		public String toS() {
			if (endPlace == null) {
				return flag + " " + x + ", " + y + "\t" + start + " - " + end + ", end ";
			} else {
				return "(" + x + "; " + y + ") " + VRGUtils.ARROW + " " + endPlace.toString();
			}
		}

		public String toStr() {
			return "���������� " + flag + " ���������: (" + x + ", " + y + ")," + "\t" + "��������: (" + df.format(r) + ", "
					+ df.format(f) + VRGUtils.DEEGRES + ")," + "\t" + "����: (" + start + ", " + end
					+ "),\t�������� � ������: " + delay;
		}

		@Override
		public String toString() {
			return "(" + x + "; " + y + "),  " + "\t" + "(" + df.format(r) + "; " + df.format(f) + VRGUtils.DEEGRES + ")"
					+ "\t";
		}

		public static class C {
			public static enum OptimalPoint {
				OptimalTime, OptimalDistance
			}

			public static class Cx implements Comparator<PointT> {

				@Override
				public int compare(PointT p, PointT pp) {
					if (p.x > pp.x)
						return 1;
					if (p.x == pp.x)
						if (p.y >= pp.y)
							return 1;
					return -1;
				}

				@Override
				public String toString() {
					return "������������� �� X";
				}
			}

			public static class Cy implements Comparator<PointT> {

				@Override
				public int compare(PointT p, PointT pp) {
					if (p.y > pp.y)
						return 1;
					if (p.y == pp.y)
						if (p.x >= pp.x)
							return 1;
					return -1;
				}

				@Override
				public String toString() {
					return "������������� �� Y";
				}
			}

			public static class Cstart implements Comparator<PointT> {

				@Override
				public int compare(PointT p, PointT pp) {
					if (p.start > pp.start)
						return 1;
					if (p.start == pp.start)
						if (p.end >= pp.end)
							return 1;
					return -1;
				}

				@Override
				public String toString() {
					return "������������� �� Start";
				}
			}

			public static class Cr implements Comparator<PointT> {

				@Override
				public int compare(PointT p, PointT pp) {
					if (p.r > pp.r)
						return 1;
					else
						return -1;
				}

				@Override
				public String toString() {
					return "������������� �� R";
				}
			}

			public static class Cf implements Comparator<PointT> {

				@Override
				public int compare(PointT p, PointT pp) {
					if (p.f > pp.f)
						return 1;
					if (p.f == pp.f)
						if (p.r > pp.r)
							return 1;
					return -1;
				}

				@Override
				public String toString() {
					return "������������� �� f";
				}
			}
		}
	}
}
