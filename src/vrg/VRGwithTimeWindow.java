package vrg;

import java.awt.Point;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class VRGwithTimeWindow {
	private static NumberFormat f = new DecimalFormat("#0.0");
	private static final PrintWriter out = new PrintWriter(System.out);

	// public static final int[][] COORDS = { { 5, 3 }, { 8, 1 }, { 8, 5 }, {
	// 11, 1 }, { 8, 6 }, { 2, 1 }, { 2, 3 }, { 1, 3 } };
	public static final int[][] COORDS = { { 0, 0 }, { 0, 3 }, { 4, 0 }, { 4, 3 }, { 8, 3 }, { 0, 6 }, { 8, 6 } };
	public static final int[] CARS_WEIGHT = { 3, 3, 5 };

	public static ArrayList<Integer> cars = new ArrayList<Integer>();
	public static ArrayList<Point> coordinates = new ArrayList<Point>();
	public static ArrayList<ArrayList<Double>> lengthOfRoutes = new ArrayList<ArrayList<Double>>();
	public static LinkedList<Queue<Double>> tr = new LinkedList<Queue<Double>>();
	public static ArrayList<Integer> oldPath = new ArrayList<Integer>();
	public static ArrayList<Integer> allIndexes = new ArrayList<Integer>();

	public static ArrayList<ArrayList<Double>> table = new ArrayList<ArrayList<Double>>();
	public static ArrayList<Double> NAN = new ArrayList<Double>();

	public static void main(String[] args) {
		// Locale.setDefault(Locale.ENGLISH);
		// Point depo = new Point(COORDS[0][0], COORDS[0][1]);
		for (int i = 0; i < COORDS.length; i++) {
			coordinates.add(new Point(COORDS[i][0], COORDS[i][1]));
		}
		for (int i = 0; i < CARS_WEIGHT.length; i++) {
			cars.add(CARS_WEIGHT[i]);
		}

		Collections.sort(coordinates, new Comparator<Point>() {
			@Override
			public int compare(Point paramInt1, Point paramInt2) {
				return Integer.compare(paramInt1.x, paramInt2.x);
			}
		});

		for (int i = 0; i < coordinates.size(); i++) {
			NAN.add(Double.NaN);
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
			tr.add(q);
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
	}

	private static void print() {
		p("Отсортированные координаты по Х");
		for (int i = 0; i < coordinates.size(); i++) {
			p(coordinates.get(i));
		}
		p("");
		p("");
		p("tr");
		p(tr);
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

		// int n = coordinates.size();// getMin(arr).intValue();// readInt();
		int k = cars.size();// readInt();

		ArrayList<Pair> cities = new ArrayList<Pair>();

		for (int i = 0; i < k; i++) {
			cities.add(new Pair(0, 0, i + 1));
		}

		Queue<Double> qq = tr.peek();// tr.poll();
		ArrayList<Double> q = new ArrayList<Double>(qq);
		oldPath.add(0);
		for (int i = 0; i < k; i++) {
			double s = 1; // readInt();

			double m = getMin(qq); // getMin(arr); // 0; // readInt();

			double end = Math.max(cities.get(i).delay, s) + m;
			Pair p = new Pair(end, q.indexOf(m), cities.get(i).num);
			cities.get(i).setNewValue(p);

			p(p);
			oldPath.add(p.index);

			qq.remove(m);
			tr.poll();
		}
		p(oldPath + " пройдено");
		p("");
		// First iteration
		// нужно удалить 0 вую строку и столбец!

		for (int j : oldPath) {
			for (int i = 0; i < table.get(j).size(); i++) {
				table.get(j).set(i, Double.NaN);// можно lengthOfRoutes
				// lengthOfRoutes.get(i).set(j, Double.NaN);
			}
		}
		p("таблица оставшихся путей");
		p(table);
		p("");

		Queue<Integer> pathNew = getDifferenceBetweenSets(allIndexes, oldPath);
		tr.clear();
		for (int i = 0; i < lengthOfRoutes.size(); i++) {
			tr.add(new LinkedList<Double>(table.get(i)));// lengthOfRoutes
		}
		p(pathNew + " осталось");
		p("");

		while (pathNew.size() != 0) {
			qq = tr.get(pathNew.poll());
			q = new ArrayList<Double>(qq);
			p(qq + " смотрим");

			double m = getMin(q);
			double s = 1; // readInt();//FIXME
			int num = getIndexFromPair(cities, q.indexOf(m)) - 1;

			double end = Math.max(cities.get(num).delay, s) + m;
			Pair p = new Pair(end + s, q.indexOf(m) + 1, cities.get(num).num);
			cities.get(num).setNewValue(p);

			p(p + " оптимальное место");
			oldPath.add(p.index);
			p("");
		}

		p(oldPath + " всё пройдено ");
		p("");
		for (Pair p : cities) {
			p(p.toStr());
		}
	}

	public static int getIndexFromPair(ArrayList<Pair> s, int index) {
		for (Pair p : s) {
			if (p.equalsIndex(index)) {
				return p.num;
			}
		}
		return 0;
	}

	public static Queue<Integer> getDifferenceBetweenSets(ArrayList<Integer> path, ArrayList<Integer> removed) {
		ArrayList<Integer> diff = new ArrayList<Integer>(path);

		diff.removeAll(removed);
		Collections.sort(diff);

		return new LinkedList<Integer>(diff);
	}

	public static class Pair {
		double delay;
		int num;
		int index;
		ArrayList<Integer> path;

		public Pair(double d, int n) {
			path = new ArrayList<Integer>();
			delay = d;
			num = n;
			index = 0;
		}

		public Pair(double d, int i, int n) {
			path = new ArrayList<Integer>();
			if (i != 0) {
				delay = d;
			} else {
				delay = 0;
			}
			num = n;
			index = i;
			path.add(i);
		}

		public void setNewValue(Pair p) {
			path.add(p.index);
			delay = p.delay;
			num = p.num;
			index = p.index;
		}

		public boolean equalsIndex(int in) {
			return index == in;
		}

		public String toStr() {
			return "Num: " + num + ", Общее время в пути: " + delay + ",  Весь путь: " + path.toString();
		}

		@Override
		public String toString() {
			return "Num: " + num + ", delay: " + delay + ",  lastPlace: " + index;
		}
	}
}
