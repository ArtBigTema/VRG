package vrg;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import vrg.VRGUtils.Point;
import vrg.VRGroutes.Route.Section;
import vrg.VRGwithTimeWindow.PointT;

public class VRGroutes {
	private static DecimalFormat df = new DecimalFormat("#.#");// FIXME

	public class Route {
		public class Section {
			public Point start;
			public Point end;
			public PointT point;

			public Section(Point s, Point e) {
				start = s;
				end = e;
			}

			public Section(Point s) {
				start = s;
				end = new Point(s.x, s.y);// FIXME
				setPointT(new PointT(s.x, s.y));
				point.setEndPlace(s.x, s.y);
			}

			public Section(PointT s) {
				start = s.getPoint();
				end = s.getEndPlace();
				setPointT(s);
			}

			public Point getStart() {
				return start;
			}

			public Point getEnd() {
				return end;
			}

			public boolean isOnePoint() {
				return ((start.x == end.x) && (start.y == end.y));
			}

			public void setPointT(PointT p) {
				point = p;
			}

			public double getInnerDistance() {
				return getDistance(start, end);
			}

			public String getStrInnerDistance() {
				return df.format(getInnerDistance());
			}

			@Override
			public String toString() {
				return start.toString() + " - " + end.toString() + ", ";
			}
		}

		public ArrayList<Section> route;
		public int index; // num of car

		public Route() {
			route = new ArrayList<Section>();
		}

		public Route(int i) {
			this();
			setIndex(i);
		}

		public ArrayList<Section> getRoute() {
			return route;
		}

		public void addSection(Section s) {
			route.add(s);
		}

		public void addSection(Point s, Point e) {
			route.add(new Section(s, e));
		}

		public void addSection(Point s) {
			route.add(new Section(s));
		}

		public void addSection(PointT p) {
			route.add(new Section(p));
		}

		public void setIndex(int i) {
			index = i;
		}

		public int getIndex() {
			return index;
		}

		@Override
		public String toString() {
			return getIndex() + " " + route.toString() + "";
		}
	}

	public ArrayList<Route> routes;
	public Point depo;
	public ArrayList<Point> noActivePoint;

	public VRGroutes() {
		routes = new ArrayList<Route>();
		noActivePoint = new ArrayList<Point>();
	}

	public VRGroutes(VRGroutes r) {
		routes = new ArrayList<Route>(r.getRoutes());
		noActivePoint = new ArrayList<Point>(r.getNoActivePoint());
		setDepo(r.getDepo());
	}

	public VRGroutes(ArrayList<ArrayList<Integer>> r, ArrayList<Point> coordinates) {
		this();
		setCountRoutess(r.size());
		Route tmp;
		for (ArrayList<Integer> arr : r) {
			tmp = new Route();
			for (int i : arr) {
				tmp.addSection(coordinates.get(i));
			}
			setRoute(r.indexOf(arr), tmp);
		}
	}

	public ArrayList<Route> getRoutes() {
		return routes;
	}

	public int getCountRoutess() {
		return routes.size();
	}

	public void setCountRoutess(int count) {
		for (int i = 0; i < count; i++) {
			routes.add(new Route(i));
		}
	}

	public void addRoute(Route r) {
		routes.add(r);
	}

	public void setRoute(int i, Route r) {
		routes.set(i, r);
	}

	public void clear() {
		this.routes.clear();
	}

	public void addSectionForIndex(int i, Point s, Point e) {
		routes.get(i).addSection(s, e);
	}

	public void addSectionForIndex(int i, PointT p) {
		routes.get(i).addSection(p);
	}

	public void addDepo() {
		for (int i = 0; i < getCountRoutess(); i++) {
			routes.get(i).addSection(new Point(0, 0), new Point(0, 0));
			// routes.get(i).addSection(new Point(0, 0));
		}
		setDepo(new Point(0, 0));
	}

	public Point getDepo() {
		return depo;
	}

	public void setDepo(Point t) {
		depo = t;
	}

	public Point maxX() {
		ArrayList<PointT> max = new ArrayList<PointT>();
		for (Route r : routes) {
			max.add(Collections.max(r.getRoute(), new C.Cx()).point);
		}

		return Collections.max(max, new VRGwithTimeWindow.PointT.C.Cx());
	}

	public Point maxY() {
		ArrayList<PointT> max = new ArrayList<PointT>();
		for (Route r : routes) {
			max.add(Collections.max(r.getRoute(), new C.Cy()).point);
		}

		return Collections.max(max, new VRGwithTimeWindow.PointT.C.Cy());
	}

	public static double getDistance(Point p1, Point p2) {
		return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
	}

	public static String getStrDistance(Point p1, Point p2) {
		return df.format(getDistance(p1, p2));
	}

	public void addNoActivePoint(Point p) {
		noActivePoint.add(p);
	}

	public void checkNoActPoint(ArrayList<PointT> arr) {
		for (PointT p : arr) {
			for (Point t : noActivePoint) {
				if (p.getPoint().equals(t)) {
					noActivePoint.remove(p);
				}
			}
		}
	}

	public void addNoActivePoint(ArrayList<Point> p) {
		noActivePoint.addAll(p);
	}

	public ArrayList<Point> getNoActivePoint() {
		return noActivePoint;
	}

	@Override
	public String toString() {
		String s = "";
		for (Route r : this.routes) {
			s += r.toString() + "\n";
		}
		return s;
	}

	public static class C {
		public static class Cx implements Comparator<Section> {

			@Override
			public int compare(Section p, Section pp) {
				if (p.start.x > pp.start.x)
					return 1;
				if (p.start.x == pp.start.x)
					if (p.start.y >= pp.start.y)
						return 1;
				return -1;
			}

			@Override
			public String toString() {
				return "Отсортировано по X";
			}
		}

		public static class Cy implements Comparator<Section> {

			@Override
			public int compare(Section p, Section pp) {
				if (p.start.y > pp.start.y)
					return 1;
				if (p.start.y == pp.start.y)
					if (p.start.x >= pp.start.x)
						return 1;
				return -1;
			}

			@Override
			public String toString() {
				return "Отсортировано по Y";
			}
		}
	}
}
