package googlemapid.sample;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import googlemapid.sample.VRP.Addreses.Addres;
import googlemapid.sample.VRPUtils.Point;
import googlemapid.sample.VRPUtils.RouteWithDelayAndDistance;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;

import vrg.VRGUtils;

public class VRP implements Runnable {
	public VRP() {
		addreses = new Addreses();

		VRPframe frame = new VRPframe();
		frame.setVisible(true);
	}

	private static DecimalFormat df = new DecimalFormat("#.####");

	private static final String FIRST_ADDRES = "Россия, Самара, улица академика Павлова, 1";
	private static final String[] ADDRESES = new String[] {
			"Россия, Самара, улица академика Павлова, 1",// FIXME
			"Россия, Самара, улица Революционная, 50", "Россия, Самара, улица Стара Загора, 41",
			"Россия, Самара, улица Самарская, 150", "Россия, Самара, проспект Кирова, 145" };
	private static final Integer[] CARS_WEIGHT = { 2, 2, 2 };
	private static boolean isShortRoute = false;
	private static boolean isWalking = true;
	private static final Double EARTH_RADIUS = 6371 * 1000d;

	private static Addreses addreses;
	private static ArrayList<Integer> carsWeight = new ArrayList<Integer>();
	public static ArrayList<ArrayList<Double>> lengthOfRoutes = new ArrayList<ArrayList<Double>>();
	public static ArrayList<ArrayList<Addres>> routess = new ArrayList<ArrayList<Addres>>();
	public static ArrayList<Pair> cars = new ArrayList<Pair>();
	public static ArrayList<ArrayList<Double>> distances = new ArrayList<ArrayList<Double>>();
	public static ArrayList<ArrayList<Double>> delays = new ArrayList<ArrayList<Double>>();

	public static boolean isVal() {
		return (addreses != null) && (addreses.getSize() > 0);
	}

	public static void setShortRoute(boolean b) {
		isShortRoute = b;
	}

	public static void setWalking(boolean b) {
		isWalking = b;
		isShortRoute = true;
		if (b) {
			err("Walking");
		} else {
			err("Driving");
		}
	}

	public static String[] getTableAddress() {
		return addreses.toArray();
	}

	public static ArrayList<String> getArrayAddress() {
		return addreses.getStrAddreses();
	}

	public static ArrayList<String> getArrayFullAddress() {
		return addreses.getStrFullAddreses();
	}

	public static void setArrayAddress(ArrayList<String> s) {
		if (VRPUtils.checkInternetConnection()) {
			err("internet is enable");
			addreses.addNewAddresesWithCoord(s);
		} else {
			err("internet is disable");
			addreses.addNewAddresesWithStandartCoord(s);
		}
	}

	public static void setArrayAddress(Enumeration<String> s) {
		setArrayAddress(Collections.list(s));
	}

	public static void generateAllStandart() {
		clearAll();
		addreses = new Addreses();
		cars = new ArrayList<Pair>();
		addreses.addNewAddreses(new ArrayList<String>(Arrays.asList(ADDRESES)));
		carsWeight = new ArrayList<Integer>(Arrays.asList(CARS_WEIGHT));
		lengthOfRoutes = new ArrayList<ArrayList<Double>>();
	}

	public static void setCountCars(int k) {
		if (carsWeight.size() < 1)
			while (k-- != 0) {
				carsWeight.add(k + 1);
			}
	}

	public static void clearAll() {
		if (isVal()) {
			addreses.clearAll();
			addreses.setFirstAddres(FIRST_ADDRES);
			carsWeight.clear();
			lengthOfRoutes.clear();
			cars.clear();
		}
	}

	public static void addAddres(String s) {
		addreses.addNewAddreses(s);
	}

	public static String getFirstAddres() {
		return addreses.getFirstAddres();
	}

	public static String getFirstAddresStrLatLng() {
		return addreses.getFirstAddresStrLatLng();
	}

	public static String getStandartFirstAddres() {
		return FIRST_ADDRES;
	}

	public static void setFirstAddres(String s) {
		addreses.setFirstAddres(s);
	}

	public static void generateLengthRoutesAndTable() {
		for (int i = 0; i < addreses.getSize(); i++) {
			ArrayList<Double> tmp = new ArrayList<Double>();

			for (int j = 0; j < addreses.getSize(); j++) {
				Double d = getDistance(i, j);

				if (d == 0.0D)
					tmp.add(Double.NaN);
				else
					tmp.add(d);
			}
			lengthOfRoutes.add(new ArrayList<Double>(tmp));
		}
	}

	private static Double getDistance(int i, int j) {
		if (isShortRoute) {
			return getDistance(addreses.get(i).getPoint(), addreses.get(j).getPoint());
		} else {
			return distances.get(i).get(j);
		}
	}

	private static Double getDelay(int i, int j) {
		if (isShortRoute) {
			return getDistance(addreses.get(i).getPoint(), addreses.get(j).getPoint());
		} else {
			return delays.get(i).get(j);
		}
	}

	private static double deg2rad(final double degree) {
		return degree * (Math.PI / 180);
	}

	private static Double getDistance(final Point subwayStationPoint, final Point addressPoint) {
		final double EARTH_RADIUS = 6371d; // Радиус Земли

		// Рассчитываем расстояние между точками
		final double dlng = deg2rad(subwayStationPoint.getLng() - addressPoint.getLng());
		final double dlat = deg2rad(subwayStationPoint.getLat() - addressPoint.getLat());
		final double a = sin(dlat / 2) * sin(dlat / 2) + cos(deg2rad(addressPoint.getLat()))
				* cos(deg2rad(subwayStationPoint.getLat())) * sin(dlng / 2) * sin(dlng / 2);
		final double c = 2 * atan2(sqrt(a), sqrt(1 - a));

		return c * EARTH_RADIUS;
	}

	private static void show() {
		p("From Kiev to London");
		System.out.println("distance: " + getDistance(new Point(50.270006, 30.300000), new Point(51.300826, 0.007300)) + " km");
		p("");
		// ArrayList<Integer> pathKomiv =
		// VRPUtils.getPathsKomiv(addreses.getStrAddreses());
		// p(pathKomiv);
		// ArrayList<RouteWithDelayAndDistance> pathMatrix =
		// VRPUtils.getDistanceMatrixForDriving(addreses.getStrAddreses());
		// pp(pathMatrix);
	}

	public static void constructResults() {
		addreses = new Addreses();
		// a.setAddresLngLat(VRPUtils.getLatLngForAddres(a.getAddres()));
		if (VRPUtils.checkInternetConnection()) {
			err("internet is enable");
			addreses.addNewAddresesWithCoord(new ArrayList<String>(Arrays.asList(ADDRESES)));
		} else {
			err("internet is disable");
			addreses.addNewAddresesWithStandartCoord(new ArrayList<String>(Arrays.asList(ADDRESES)));
		}
		// addreses.setFirstAddresIn(FIRST_ADDRES);

		constrRes();
	}

	public static void constrRes() {
		// addreses.addNewAddreses()
		if (isShortRoute) {
			addreses.addNewAddreses(addreses.getFirstAddress(), 0);
			generateLengthRoutesAndTable();
		} else {
			createTableOfDistanceAndDelays();
			addreses.addNewAddreses(addreses.getFirstAddress(), 0);
			if (!checkDelDis()) {
				addreses.remove(0);
				return;
			}
		}
		solve();
		addreses.remove(0);
	}

	private static void clearDelDis() {
		if (checkDelDis()) {
			delays.clear();
			distances.clear();
		}
	}

	private static boolean checkDelDis() {
		return (distances != null && distances.size() > 0 && delays != null && delays.size() > 0);
	}

	public static void createTableOfDistanceAndDelays() {
		clearDelDis();
		distances = new ArrayList<ArrayList<Double>>();
		delays = new ArrayList<ArrayList<Double>>();

		int i = 0;
		ArrayList<Double> tmpDis = new ArrayList<Double>();
		ArrayList<Double> tmpDel = new ArrayList<Double>();
		ArrayList<RouteWithDelayAndDistance> routs = VRPUtils.getDistanceMatrix(addreses.getStrAddreses(), isWalking);
		if (routs == null) {
			VRGUtils.showErrorMess(null);
			return;
		}
		pp(routs);

		for (RouteWithDelayAndDistance r : routs) {
			tmpDis.add(r.distance);
			tmpDel.add(r.delay);
			i++;
			if (i == ((int) Math.sqrt(routs.size()))) {
				i = 0;
				distances.add(new ArrayList<Double>(tmpDis));
				delays.add(new ArrayList<Double>(tmpDel));
				tmpDel.clear();
				tmpDis.clear();
			}
		}
		p(distances);
		p("");
		p(delays);
	}

	public static void main(String[] args) {
		new VRP();

		// generateAllStandart();
		show();// FIXME
		// constrRes();
		// constructResults();
	}

	private static void solve() {// for taxi optimDistance
		int k = carsWeight.size();

		ArrayList<Integer> oldPath = new ArrayList<Integer>();
		routess = new ArrayList<ArrayList<Addres>>();

		cars = new ArrayList<Pair>();

		oldPath.add(0);
		p(routess);
		addreses.get(0).b = true;

		for (int i = 0; i < k; i++) {
			routess.add(new ArrayList<Addres>());
			routess.get(i).add(addreses.get(0));
			cars.add(new Pair(0, 0, i));

			cars.get(i).setLastPoint(addreses.get(0).getPoint());

			int index = getOptimPlace(cars.get(i));
			Point optimPlace = addreses.get(index).getPoint();

			p(optimPlace.toString());

			routess.get(i).add(addreses.get(index));
			addreses.get(index).b = true;

			double endDD = cars.get(i).delay + getDelay(addreses.indexOf(cars.get(i).getLastPoint()), index);
			// getDistance(cars.get(i).getLastPoint(), optimPlace);

			p("конечное время " + endDD);

			Pair p = new Pair(endDD, index, cars.get(i).num);
			p.setLastPoint(optimPlace);
			p.addDistance(getDistance(addreses.indexOf(cars.get(i).getLastPoint()), index));
			cars.get(i).setNewValue(p);

			p(cars.get(i).toStr());
			oldPath.add(p.place);
		}

		p("");
		p(routess);
		p("passedPath");
		p("");
		pr(cars);
		p("");

		ArrayList<Point> remnant = getRemnantPlace(getDifferenceBetweenSets(getAllIndexes(), oldPath));
		p(remnant + " осталось");
		p("");

		for (int i = 0; i < remnant.size(); i++) {
			Point t = remnant.get(i);
			p(addreses.indexOf(t) + " " + t.toString());

			int indexOfCar = getOptimPlace(cars, t);
			if (indexOfCar == -1) {
				err("optim place not found");
				continue;
			}
			p(cars.get(indexOfCar).getLastPoint().toString() + " - " + t.toString());

			double endDD = cars.get(indexOfCar).delay
					+ getDelay(addreses.indexOf(cars.get(indexOfCar).getLastPoint()), addreses.indexOf(t));
			p("конечное время " + df.format(endDD));

			Pair p = new Pair(endDD, addreses.indexOf(t), cars.get(indexOfCar).num);
			p.setLastPoint(t);
			p.addDistance(getDistance(addreses.indexOf(cars.get(indexOfCar).getLastPoint()), addreses.indexOf(t)));
			cars.get(indexOfCar).setNewValue(p);

			routess.get(indexOfCar).add(addreses.get(addreses.indexOf(t)));
			addreses.get(addreses.indexOf(t)).b = true;

			p(p.toStr());
			oldPath.add(p.place);
		}

		p("");
		p(routess);
		p(oldPath);
		p("");
		pr(cars);
		p("");
	}

	private static ArrayList<Integer> getAllIndexes() {
		ArrayList<Integer> allIndexes = new ArrayList<Integer>();
		for (int i = 0; i < addreses.getSize(); i++) {
			allIndexes.add(i);
		}
		return allIndexes;
	}

	public static boolean checkResult() {
		double d = 0.0;
		for (Pair p : cars) {
			d += p.distance;
		}
		return d > 1;
	}

	public static String getRoutes(int j) {
		return cars.get(j).getPath().toString();
	}

	public static String getFullRoute(int j) {
		return cars.get(j).toStr() + "\n" + getPath(cars.get(j).path) + "\n\n" + getDelDis(j);
	}

	private static String getDelDis(int j) {
		return VRPUtils.getDelay(cars.get(j).delay) + ",  " + VRPUtils.getDistance(cars.get(j).distance);
	}

	private static String getPath(ArrayList<Integer> path) {
		addreses.addNewAddreses(getFirstAddres(), 0);
		StringBuilder sb = new StringBuilder();
		for (int i : path) {
			sb.append("\n" + i + ": " + addreses.getStrAddreses(i));
		}
		addreses.remove(0);
		return sb.toString();
	}

	public static String getDelay(int j) {
		return VRGUtils.df.format(cars.get(j).delay);
	}

	public static String getDistance(int j) {
		return VRGUtils.df.format(cars.get(j).distance);
	}

	public static int getRoutessSize() {
		if (routess == null && routess.size() < 1 && cars == null && cars.size() < 1 && checkResult()) {
			return 0;
		}
		return routess.size();
	}

	private static int getOptimPlace(ArrayList<Pair> c, Point point) {
		ArrayList<Double> recom = new ArrayList<Double>();
		for (Pair p : c) {
			recom.add(getDistance(addreses.indexOf(point), addreses.indexOf(p.getLastPoint())));
		}
		int index = recom.indexOf(Collections.min(recom));

		return index;// without depo
	}

	private static void pr(ArrayList<Pair> c) {
		for (Pair p : c) {
			p(p.toStr());
		}
	}

	public static ArrayList<String> getArrayLatLngAddress() {
		return addreses.getStrLatLng();
	}

	private static ArrayList<Point> getRemnantPlace(ArrayList<Integer> pathNew) {
		ArrayList<Point> result = new ArrayList<Point>();
		for (int i : pathNew) {
			if (!addreses.get(i).isPassed()) {
				result.add(addreses.get(i).getPoint());
			}
		}
		return result;
	}

	public static ArrayList<Integer> getDifferenceBetweenSets(ArrayList<Integer> path, ArrayList<Integer> removed) {
		ArrayList<Integer> diff = new ArrayList<Integer>(path);
		diff.removeAll(removed);
		return diff;
	}

	private static int getOptimPlace(Pair car) {
		p("Ищем место для машины");
		p(car.toStr());
		// ArrayList<Addres> addr = new ArrayList<Addres>();

		ArrayList<Double> recom = new ArrayList<Double>();
		for (int i = 1; i < addreses.getSize(); i++) {
			Addres a = addreses.get(i);

			double distance = 0;

			if (!a.b) {
				distance = getDistance(i, addreses.indexOf(car.getLastPoint()));// getDistance(a.getPoint(),
																				// car.getLastPoint());
				// depo to short

				/*
				 * int optimIndex = getDistanceTo(i); // Addres optim =
				 * addreses.get(optimIndex);
				 * 
				 * // Double dis = getDistance(a.getPoint(), optim.getPoint());
				 * // short to subshort // Double sub =
				 * getDistance(car.getLastPoint(), optim.getPoint()); //
				 * subshort to depo
				 * 
				 * p(""); // p("depo to short " + df.format(distance)); //
				 * p("short to subshort " + df.format(dis)); //
				 * p("subshort to depo " + df.format(sub));
				 * 
				 * // p("difference " + df.format(distance + dis - sub)); p("");
				 * 
				 * // if (distance + dis - sub > 1 || optim.b) { p("no see"); //
				 * recom.add(distance); // } else { err("see"); // optim.b =
				 * true; // addr.add(optim); // recom.add(6371.0); // }
				 */
				recom.add(distance);
			} else {
				recom.add(EARTH_RADIUS);
			}
		}
		// for (Addres a : addr) {
		// a.b = addreses.get(addreses.indexOf(a.getPoint())).b;
		// }

		int index = recom.indexOf(Collections.min(recom));
		return index + 1;
	}

	/*
	 * private static int getDistanceTo(int j) { ArrayList<Double> recom = new
	 * ArrayList<Double>(); for (int i = 1; i < addreses.getSize(); i++) { Point
	 * t = addreses.get(i).getPoint(); Point tt = addreses.get(j).getPoint();
	 * 
	 * double distance = 0;
	 * 
	 * if (!addreses.get(i).b && !t.equals(tt)) { distance = getDistance(t, tt);
	 * if (distance != 0.0) { recom.add(distance); } else { recom.add(6371.0); }
	 * } else { recom.add(6371.0); } } if (Collections.min(recom) == 6371.0) {
	 * return j; } return recom.indexOf(Collections.min(recom)) + 1; }
	 */

	private static void p(Object o) {
		System.out.println(o.toString());
	}

	private static void err(Object o) {
		System.err.println(o.toString());
	}

	public static String getStringDifferenceBetweenSets() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (Pair p : cars)
			arr.addAll(p.path);

		arr = getDifferenceBetweenSets(getAllIndexes(), arr);
		if (arr.size() == 0) {
			return "";
		}
		return VRGUtils.SPACE + VRGUtils.SLASH + VRGUtils.SPACE + arr.toString();
	}

	private static void p(ArrayList<ArrayList<Double>> arrr) {
		for (ArrayList<Double> arr : arrr) {
			for (Double d : arr) {
				if (df.format(d).length() < 2) {
					System.out.print("NaN" + "\t");
				} else
					System.out.print(df.format(d) + "\t");
			}
			System.out.println("");
		}
	}

	private static void pp(ArrayList<RouteWithDelayAndDistance> arrr) {
		for (RouteWithDelayAndDistance d : arrr) {
			p(d.start + "-" + d.end + " " + df.format(d.delay) + d.del + " " + df.format(d.distance) + d.dis);
		}
		p("");
	}

	public static class Pair {
		double delay;
		int num;
		int place;
		double distance;
		ArrayList<Integer> path;
		Point lastPlace;
		Point lastPoint;

		public Pair(double d, int n) {
			path = new ArrayList<Integer>();
			delay = d;
			num = n;
			place = 0;
			distance = 0;
		}

		public String getPath() {
			return path.toString();
		}

		public Pair(double d, int i, int n) {
			path = new ArrayList<Integer>();
			delay = d;
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
			setLastPoint(p.lastPoint);
		}

		public void setLastPoint(Point t) {
			lastPoint = t;
		}

		public Point getLastPlace() {
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

		public boolean equalsPlace(Point t) {
			return (this.lastPlace.equals(t) || lastPoint.equals(t));
		}

		public String toStr() {
			return "Num: " + num + ", Общее время в пути: " + df.format(delay) + ",  Весь путь: " + path.toString()
					+ " Длина пути: " + df.format(distance);
		}

		@Override
		public String toString() {
			return "Num: " + num + ", delay: " + df.format(delay) + ", " + path.toString() + ", dis: " + df.format(distance)
					+ "\n";
		}
	}

	public static class Addreses {
		public static class Addres {

			private String addresStr = "";
			private double lat = 0d;
			private double lng = 0d;
			private int n = "Россия, Самара, улица академика Павлова, 1".length() * 3 / 2;
			public boolean b = false;

			public Addres(String s) {
				addresStr = s;
			}

			public boolean isPassed() {
				return b;
			}

			public void setAddres(String s) {
				addresStr = s;
			}

			public String getAddres() {
				return addresStr;
			}

			public String getFullAddres() {
				return addresStr + getSpaces(addresStr) + getStrLatLng();
			}

			public void setAddresLatLng(double la, double ln) {
				lat = la;
				lng = ln;
			}

			public void setAddresLngLat(Point p) {
				lat = p.getLat();
				lng = p.getLng();
			}

			public Double getLat() {
				return lat;
			}

			public Double getLng() {
				return lng;
			}

			public Point getPoint() {
				return new Point(getLat(), getLng());
			}

			public String getStrLatLng() {
				return df.format(getLat()).replace(",",".") + "," + df.format(getLng()).replace(",",".");
			}

			public String getSpaces(String s) {
				int k = n - s.length();
				StringBuilder sb = new StringBuilder();
				while ((k--) > 0) {
					sb.append("_");
				}
				return sb.toString();
			}

			@Override
			public String toString() {
				return b + " Адрес: " + getAddres() + " , Lat: " + df.format(getLat()) + " , Lng: " + df.format(getLng())
						+ "\n";
			}
		}

		public static final double[][] COORDS = { { 0, 0 }, { 53.211659, 50.171854 }, { 53.22770999999999, 50.2056929 },
				{ 53.1752116, 50.2134544 }, { 53.21913499999999, 50.26514299999999 }, { 0, 0 }, { 1, 3 }, { 4, 1 }, { 4, 3 },
				{ 4, 5 } };

		private ArrayList<Addres> addreses;
		private Addres firstAddres;

		public Addreses() {
			addreses = new ArrayList<Addres>();
			firstAddres = new Addres("Россия, Самара, улица академика Павлова, 1");
			firstAddres.setAddresLatLng(53.2238529802915, 50.17439398029149);// FIXME
		}

		public void remove(int i) {
			addreses.remove(0);
		}

		public boolean pointIsEnabled() {
			int i = 1;
			for (Addres a : addreses) {
				if (a.isPassed()) {
					i++;
				}
			}
			return (i >= getSize());
		}

		public int indexOf(Point t) {
			for (Addres a : addreses) {
				if (a.getPoint().equals(t)) {
					return addreses.indexOf(a);
				}
			}
			return -1;
		}

		public void setFirstAddresIn(String s) {
			addreses.add(0, new Addres(s));
		}

		public Addres get(int i) {
			return addreses.get(i);
		}

		public int getSize() {
			return addreses.size();
		}

		public String[] toArray() {
			return addreses.toArray(new String[getSize()]);
		}

		public void clearAll() {
			addreses.clear();
		}

		public void addNewAddreses(ArrayList<String> ss) {
			if (ss == null || ss.size() < 1) {
				return;
			}
			addreses = new ArrayList<Addres>();
			setFirstAddres(ss.remove(0));// FIXME
			for (String s : ss) {
				addreses.add(new Addres(s));
			}
		}

		public void addNewAddresesWithCoord(ArrayList<String> ss) {
			addNewAddreses(ss);
			for (Addres a : addreses) {
				a.setAddresLngLat(VRPUtils.getLatLngForAddres(a.getAddres()));
			}
		}

		public void addNewAddresesWithStandartCoord(ArrayList<String> ss) {
			addNewAddreses(ss);
			for (Addres a : addreses) {
				a.setAddresLngLat(new Point(1 * COORDS[addreses.indexOf(a) + 1][0], 1 * COORDS[addreses.indexOf(a) + 1][1]));
			}
		}

		public void addNewAddreses(String s, int index) {
			addreses.add(index, new Addres(s));
		}

		public void addNewAddreses(Addres s, int index) {
			addreses.add(index, s);
		}

		public void addNewAddreses(String s) {
			addreses.add(new Addres(s));
		}

		public void setNewAddreses(String s, int index) {
			addreses.set(index, new Addres(s));
		}

		public ArrayList<String> getStrAddreses() {
			ArrayList<String> s = new ArrayList<String>();
			s.add(getFirstAddres());// FIXME
			for (Addres a : addreses) {
				s.add(a.getAddres());
			}
			return s;
		}

		public ArrayList<String> getStrFullAddreses() {
			ArrayList<String> s = new ArrayList<String>();
			s.add(getFirstFullAddres());// FIXME
			for (Addres a : addreses) {
				s.add(a.getFullAddres());
			}
			return s;
		}

		public String getStrAddreses(int index) {
			return addreses.get(index).getAddres();
		}

		public ArrayList<String> getStrLatLng() {
			ArrayList<String> result = new ArrayList<String>();
			result.add(getFirstAddresStrLatLng());
			for (Addres a : addreses) {
				result.add(a.getStrLatLng());
			}
			return result;
		}

		public String getStrLatLng(int index) {
			return addreses.get(index).getStrLatLng();
		}

		public String getFirstAddres() {
			return firstAddres.getAddres();
		}

		public Addres getFirstAddress() {
			return firstAddres;
		}

		public String getFirstFullAddres() {
			return firstAddres.getFullAddres();
		}

		public String getFirstAddresStrLatLng() {
			return firstAddres.getStrLatLng();
		}

		public void setFirstAddres(String s) {
			firstAddres = new Addres(s);
			firstAddres.setAddresLatLng(53.2238529802915, 50.17439398029149);
		}

		@Override
		public String toString() {
			return "Начальный адрес: " + getFirstAddres() + "\n " + addreses.toString();
		}
	}

	@Override
	public void run() {
		main(null);
	}
}