package vrg;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;
import vrg.VRGUtils.Point;


public class VRGwithTimeWindow {
	private static DecimalFormat f = new DecimalFormat("#.#");
	private static final PrintWriter out = new PrintWriter(System.out);
	private static final boolean showGraph = true;// FIXME

	// public static final int[][] COORDS = { { 5, 3 }, { 8, 1 }, { 8, 5 }, {
	// 11, 1 }, { 8, 6 }, { 2, 1 }, { 2, 3 }, { 1, 3 } };
	public static final int[][] COORDS = { { 0, 0 }, { 0, 3 }, { 4, 0 }, { 4, 3 }, { 8, 3 }, { 0, 6 }, { 8, 6 }, { 2, 6 },
			{ 8, 0 }, { 4, 5 } };
	public static final int[] CARS_WEIGHT = { 3, 3, 5 };

	public static ArrayList<Integer> cars = new ArrayList<Integer>();
	public static ArrayList<PointT> coordinates = new ArrayList<PointT>();
	public static ArrayList<ArrayList<Double>> lengthOfRoutes = new ArrayList<ArrayList<Double>>();
	public static ArrayList<Integer> oldPath = new ArrayList<Integer>();
	public static ArrayList<Integer> allIndexes = new ArrayList<Integer>();
	public static ArrayList<ArrayList<Integer>> routes = new ArrayList<ArrayList<Integer>>();

	public static ArrayList<ArrayList<Double>> table = new ArrayList<ArrayList<Double>>();

	// public static ArrayList<Double> NAN = new ArrayList<Double>();

	public static void main(String[] args) {
		for (int i = 0; i < COORDS.length; i++) {
			coordinates.add(new PointT(COORDS[i][0], COORDS[i][1]));
		}
		for (int i = 0; i < CARS_WEIGHT.length; i++) {
			cars.add(CARS_WEIGHT[i]);
		}

		Collections.sort(coordinates, new Comparator<Point>() {
			@Override
			public int compare(Point p, Point pp) {
				if (p.x > pp.x)
					return 1;
				if (p.x == pp.x)
					if (p.y >= pp.y)
						return 1;
				return -1;
			}
		});

		for (int i = 0; i < coordinates.size(); i++) {
			// NAN.add(Double.NaN);
			allIndexes.add(i);
			ArrayList<Double> tmp = new ArrayList<Double>();
			Queue<Double> q = new LinkedList<Double>();

			for (int j = 0; j < coordinates.size(); j++) {
				Double d = getDistance(coordinates.get(i), coordinates.get(j));

				if (d == 0.0D)
					tmp.add(Double.NaN);
				else
					tmp.add(Double.parseDouble(f.format(d).replace(",", ".")));
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
		// sort(coordinates, lengthOfRoutes.get(0));
		print();

		try {
			solve(lengthOfRoutes);
			out.flush();
		} catch (Exception e) {
			p(e);
		}

		showGraphIfNeed();
	}

	private static void showGraphIfNeed() {
		if (showGraph) {
			ArrayList<Point> coord = new ArrayList<Point>();
			for (Point p : coordinates) {
				coord.add(p);
			}
			VRGgraphOld frame = new VRGgraphOld(coord, routes);
			frame.isTimeWindow = true;
		}
	}

	private static void print() {
		p("Отсортированные координаты по Х");
		for (int i = 0; i < coordinates.size(); i++) {
			p(coordinates.get(i));
		}
		p("");
		p("таблица всех путей");
		p(lengthOfRoutes);
		p("");
		p("таблица оставшихся путей");
		p(table);
		p("");
		// p(NAN);
	}

	public static void p(Object o) {
		out.println(o.toString());
		out.flush();
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
		Double x = Collections.min(tmp, new Comparator<Double>() {
			@Override
			public int compare(Double paramInt1, Double paramInt2) {
				return Double.compare(paramInt1, paramInt2);
			}
		});
		return x;
	}

	public static Double getMin(Queue<Double> tmp) {
		Double x = Collections.min(tmp, new Comparator<Double>() {
			@Override
			public int compare(Double paramInt1, Double paramInt2) {
				return Double.compare(paramInt1, paramInt2);
			}
		});
		return x;
	}

	private static void solve(ArrayList<ArrayList<Double>> l) throws Exception {
		int k = cars.size();

		ArrayList<Pair> cities = new ArrayList<Pair>();

		for (int i = 0; i < k; i++) {
			cities.add(new Pair(0, 0, i + 1));
		}

		Queue<Double> qq = new LinkedList<Double>(lengthOfRoutes.get(0));
		ArrayList<Double> q = new ArrayList<Double>(qq);
		oldPath.add(0);
		for (int i = 0; i < k; i++) {
			double s = 1; // readInt();

			double m = getMin(qq); // getMin(arr); // 0; // readInt();

			double end = Math.max(cities.get(i).delay, s) + m;
			Pair p = new Pair(end, q.indexOf(m), cities.get(i).num);
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

			double end = Math.max(cities.get(num).delay, s) + m;
			Pair p = new Pair(end + s, currPlace, cities.get(num).num);
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
		// Collections.sort(diff);

		return diff;
	}

	public static class Pair {
		double delay;
		int num;
		int place;
		ArrayList<Integer> path;

		public Pair(double d, int n) {
			path = new ArrayList<Integer>();
			delay = d;
			num = n;
			place = 0;
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
		}

		public void setNewValue(Pair p) {
			path.add(p.place);
			delay = p.delay;
			num = p.num;
			place = p.place;
		}

		public boolean equalsPlace(int in) {
			return place == in;
		}

		public String toStr() {
			return "Num: " + num + ", Общее время в пути: " + delay + ",  Весь путь: " + path.toString();
		}

		@Override
		public String toString() {
			return "Num: " + num + ", delay: " + delay + ",  lastPlace: " + place;
		}
	}

	public static class PointT extends Point {
		int x;
		int y;
		int start;
		int end;

		public PointT(int xx, int yy) {
			super(xx, yy);
			x = xx;
			y = yy;
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + "), ";
		}
	}

}
