package vrg;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;

import vrg.VRGUtils.Point;

public class VRGwithTimeWindow {
	private static DecimalFormat df = new DecimalFormat("#.#");
	private static final PrintWriter out = new PrintWriter(System.out);
	private static final boolean showGraph = false;// XXX

	// public static final int[][] COORDS = { { 5, 3 }, { 8, 1 }, { 8, 5 }, {
	// 11, 1 }, { 8, 6 }, { 2, 1 }, { 2, 3 }, { 1, 3 } };
	public static final int[][] COORDS = { { 0, 0 }, { 0, 3 }, { 4, 0 }, { 4, 3 }, { 8, 3 }, { 0, 6 }, { 8, 6 }, { 2, 6 },
			{ 8, 0 }, { 4, 5 } };
	public static final int[] CARS_WEIGHT = { 3, 3, 5 };

	public static ArrayList<Integer> cars = new ArrayList<Integer>();
	public static ArrayList<PointT> coordinates = new ArrayList<PointT>();
	public static ArrayList<ArrayList<Double>> lengthOfRoutes = new ArrayList<ArrayList<Double>>();
	public static ArrayList<Integer> allIndexes = new ArrayList<Integer>();
	public static ArrayList<ArrayList<Integer>> routes = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<ArrayList<Double>> table = new ArrayList<ArrayList<Double>>();

	public static ArrayList<Pair> cities;

	public VRGwithTimeWindow() {
		main(null);
	}

	public static boolean isValid() {
		return (coordinates != null) && (coordinates.size() > 0) && (cars != null) && (cars.size() > 0);
	}

	public static void clearAll() {
		cars.clear();
		coordinates.clear();
		lengthOfRoutes.clear();
		allIndexes.clear();
		routes.clear();
		table.clear();
	}

	public static void generateAllStandart() {
		clearAll();
		for (int i = 0; i < COORDS.length; i++) {
			coordinates.add(new PointT(COORDS[i][0], COORDS[i][1]));
		}
		for (int i = 0; i < CARS_WEIGHT.length; i++) {
			cars.add(CARS_WEIGHT[i]);
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
		generateLengthRoutesAndTable();// XXX
		print();
	}

	public static void generateNewRows(int n) {
		generateCoordinates(n);
		generateCars(n);
		sort();
		generateLengthRoutesAndTable();// XXX
		print();
	}

	public static void generateCoordinates(int n) {
		int x = VRGUtils.windowWidth;
		int y = VRGUtils.windowHeight;
		coordinates.add(new PointT(random(x / 20, x / 2), random(y / 20, y / 2)));
		for (int i = 0; i < n; i++) {
			PointT p = new PointT(random(y / 20, y), random(y / 20, y));
			p.setDelay(random(1, 5));
			p.setTimeWindow(1, coordinates.size() + n);
			coordinates.add(p);
		}
	}

	public static void generateCars(int n) {
		for (int i = 0; i < n; i++) {
			cars.add(random(1, n));
		}
	}

	public static int[][] getRoutes() {
		if (routes == null || routes.size() == 0) {
			main(null);
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
			main(null);
		}
		return routes;
	}

	public static Double getDelayOfRoutes(int j) {
		if (j >= cities.size() || j < 0) {
			err("See getLengthOfRoutes");
		}
		return cities.get(j).delay;
	}

	public static Double getLengthOfRoutes(int j) {
		if (j >= cities.size() || j < 0) {
			err("See getLengthOfRoutes");
		}
		return cities.get(j).length;
	}

	public static ArrayList<Integer> getRoutes(int j) {
		if (routes == null || routes.size() == 0) {
			main(null);
		}
		return routes.get(j);
	}

	public static int getCountCars() {
		return cars.size();
	}

	public static void err(Object o) {
		System.err.println(o.toString());
	}

	public static void generateLengthRoutesAndTable() {
		for (int i = 0; i < coordinates.size(); i++) {
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

			for (int j = i; j < coordinates.size(); j++) {
				tmp.set(j, Double.NaN);
			}
			table.add(tmp);
		}
	}

	public static void main(String[] args) {
		generateAllStandart();

		sort();

		generateLengthRoutesAndTable();// XXX

		print();

		try {
			solve(lengthOfRoutes);// XXX
			out.flush();
		} catch (Exception e) {
			p(e);
		}

		showGraphIfNeed();
	}

	private static void sort() {
		Collections.sort(coordinates, new PointT.C.Cx());
		p("Отсортированные координаты по Х");
		pp(coordinates);
		Collections.sort(coordinates, new PointT.C.Cy());
		p("Отсортированные координаты по Y");
		pp(coordinates);
		Collections.sort(coordinates, new PointT.C.Cf());
		p("Отсортированные координаты по fi");
		pp(coordinates);
		Collections.sort(coordinates, new PointT.C.Cr());
		p("Отсортированные координаты по r");
		pp(coordinates);
		sortX();
	}

	private static void sortX() {
		Collections.sort(coordinates, new PointT.C.Cx());
	}

	private static void showGraphIfNeed() {// FIXME
		if (showGraph) {
			showGraph();
		}
	}

	public static void showGraph() {// FIXME
		VRGgraphOld frame = new VRGgraphOld(getCoords(), routes);
		frame.isTimeWindow = true;
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
		return coordinates.get(index).delay;
	}

	public static String getStartEnd(int index) {
		PointT p = coordinates.get(index);
		return p.start + VRGUtils.ARROW + p.end;
	}

	public static Integer getCars(int index) {
		return cars.get(index);
	}

	public static void generateRoutesRand() {
		// randomSort();
		sortX();// FIXME
		generateLengthRoutesAndTable();// XXX
		try {
			solve(lengthOfRoutes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void randomSort() {
		int k = random(0, 4);
		switch (k) {
		case 0:
			Collections.sort(coordinates, new PointT.C.Cx());
			break;
		case 1:
			Collections.sort(coordinates, new PointT.C.Cy());
			break;
		case 2:
			Collections.sort(coordinates, new PointT.C.Cr());
			break;
		case 3:
			Collections.sort(coordinates, new PointT.C.Cf());
			break;
		default:
			Collections.sort(coordinates, new PointT.C.Cx());
			break;
		}
		p("Отсортированные координаты по рандому " + k);
		pp(coordinates);
	}

	private static void print() {
		p("");
		p("таблица всех путей");
		p(lengthOfRoutes);
		p("");
		p("таблица оставшихся путей");
		p(table);
		p("");
	}

	public static void p(Object o) {
		out.println(o.toString());
		out.flush();
	}

	public static void pp(ArrayList<PointT> pp) {
		for (PointT p : pp) {
			p(p.toStr());
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

	private static void solve(ArrayList<ArrayList<Double>> l) throws Exception {
		int k = cars.size();

		ArrayList<Integer> oldPath = new ArrayList<Integer>();
		cities = new ArrayList<Pair>();

		for (int i = 0; i < k; i++) {
			cities.add(new Pair(0, 0, i + 1));
		}

		Queue<Double> qq = new LinkedList<Double>(lengthOfRoutes.get(0));
		ArrayList<Double> q = new ArrayList<Double>(qq);
		oldPath.add(0);
		for (int i = 0; i < k; i++) {
			double s = 1; // FIXME

			double m = getMin(qq); // getMin(arr); // 0; // readInt();

			double end = Math.max(cities.get(i).delay, s) + m;
			Pair p = new Pair(end, q.indexOf(m), cities.get(i).num);
			p.addLength(m);
			cities.get(i).setNewValue(p);

			p(p);
			oldPath.add(p.place);

			qq.remove(m);
		}
		p(oldPath + " пройдено");
		p("");

		ArrayList<Integer> pathNew = getDifferenceBetweenSets(allIndexes, oldPath);
		LinkedList<Queue<Double>> tr = new LinkedList<Queue<Double>>();

		for (int i = 0; i < lengthOfRoutes.size(); i++) {
			tr.add(new LinkedList<Double>(table.get(i)));// lengthOfRoutes
		}

		p(pathNew + " осталось");
		p("");
		int currPlace = 0;

		while (pathNew.size() != 0) {
			currPlace = pathNew.get(0);
			pathNew.remove(0);
			qq = tr.get(currPlace);
			q = new ArrayList<Double>(qq);

			double m = getMin(q);
			double s = 1; // FIXME задержка в городе
			int num = getNumOfCarsWithPlace(cities, q.indexOf(m)) - 1;
			Pair p = null;
			double end = 0;

			try {
				end = Math.max(cities.get(num).delay, s) + m;
				p = new Pair(end + s, currPlace, cities.get(num).num);
				p.addLength(m);
			} catch (Exception e) {
				p(e);
				e.printStackTrace();
			}
			cities.get(num).setNewValue(p);

			p(qq + " смотрим " + q.indexOf(m) + " из " + currPlace);
			p(p + " оптимальное место");
			oldPath.add(p.place);
			p("");
		}

		p(oldPath + " всё пройдено ");
		p("");
		for (Pair p : cities) {
			p(p.toStr());
			routes.add(p.path);
		}
		allIndexes.clear();
	}

	public static int getNumOfCarsWithPlace(ArrayList<Pair> s, int place) {
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
			return (int) (start + rand.nextInt(end - start));
	}

	public static int random(int start, int end, int oldValue) {
		int result = 0;

		do {
			result = random(start, end);
		} while (result == oldValue);

		return result;
	}

	public static class Pair {
		double delay;
		int num;
		int place;
		double length;
		ArrayList<Integer> path;

		public Pair(double d, int n) {
			path = new ArrayList<Integer>();
			delay = d;
			num = n;
			place = 0;
			length = 0;
		}

		public Pair(double d, int i, int n) {
			path = new ArrayList<Integer>();
			if (i != 0) {
				delay = d;
			} else {
				delay = 0;
			}
			num = n;
			place = i;
			path.add(i);
			length = 0;
		}

		public void setNewValue(Pair p) {
			path.add(p.place);
			delay = p.delay;
			num = p.num;
			place = p.place;
			length += p.length;
		}

		public void addLength(double l) {
			length += l;
		}

		public boolean equalsPlace(int in) {
			return place == in;
		}

		public String toStr() {
			return "Num: " + num + ", Общее время в пути: " + delay + ",  Весь путь: " + path.toString() + " Длина пути: "
					+ length;
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
		}

		public void setTimeWindow(int s, int e) {
			start = s;
			end = e;
		}

		public void setDelay(int d) {
			delay = d;
		}

		public void close() {
			flag = true;
		}

		public Point getPoint() {
			return new Point(x, y);
		}

		public String toStr() {
			return "Активность " + flag + " Декартовы: (" + x + ", " + y + ")," + "\t" + "Полярные: (" + df.format(r) + ", "
					+ df.format(f) + VRGUtils.DEEGRES + ")," + "\t" + "Окна: (" + start + ", " + end
					+ "),\tЗадержка в городе: " + delay;// FIXME
		}

		@Override
		public String toString() {
			return "(" + x + "; " + y + "),  " + "\t" + "(" + df.format(r) + "; " + df.format(f) + VRGUtils.DEEGRES + ")";
		}

		public static class C {
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
			}

			public static class Cstart implements Comparator<PointT> {

				@Override
				public int compare(PointT p, PointT pp) {
					if (p.start > pp.start)
						return 1;
					if (p.start == pp.start)
						if (p.end <= pp.end)
							return 1;
					return -1;
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
			}
		}
	}

}
