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
	public static Queue<Queue<Double>> tr = new LinkedList<Queue<Double>>();
	public static ArrayList<Integer> oldPath = new ArrayList<Integer>();
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

			for (int j = 0; j < i; j++) {
				tmp.set(j, Double.NaN);
			}
			table.add(tmp);
		}
		print();

		try {
			solve(lengthOfRoutes);
			out.flush();
		} catch (Exception e) {
			p(e);
		}
	}

	private static void print() {
		for (int i = 0; i < coordinates.size(); i++) {
			p(coordinates.get(i));
		}
		p("");
		p(tr);
		p("");
		p(lengthOfRoutes);
		p("");
		p(table);
		p("");
		// p(NAN);
	}

	public static void p(Object o) {
		out.println(o.toString());
		out.flush();
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

		PriorityQueue<Pair> servers = new PriorityQueue<Pair>(10, new Comparator<Pair>() {
			public int compare(Pair p1, Pair p2) {
				return Double.compare(p1.delay, p2.delay);
			}
		});

		for (int i = 0; i < k; i++) {
			servers.add(new Pair(0, 0, i + 1));
		}

		Queue<Double> qq = tr.peek();// tr.poll();
		ArrayList<Double> q = new ArrayList<Double>(qq);
		oldPath.add(0);
		for (int i = 0; i < k; i++) {
			double s = 0; // readInt();

			double m = getMin(qq); // getMin(arr); // 0; // readInt();

			double end = Math.max(servers.peek().delay, s) + m;
			Pair p = new Pair(end, q.indexOf(m), servers.poll().num);
			servers.add(p);

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
			table.set(j, NAN);// можно lengthOfRoutes
		}
		p(table);

	}

	public static class Pair {
		double delay;
		int num;
		int index;

		public Pair(double d, int n) {
			delay = d;
			num = n;
		}

		public Pair(double d, int i, int n) {
			delay = d;
			num = n;
			index = i;
		}

		@Override
		public String toString() {
			return "Num: " + num + ", delay: " + delay + ",  index: " + index;
		}
	}
}
