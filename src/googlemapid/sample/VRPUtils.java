package googlemapid.sample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

import vrg.VRGUtils;

public class VRPUtils extends AbstractSample {
	public static final String FONT_TAHOMA = "Tahoma";
	public static final String CHC_SAVE_ADRESS = "Сохранить начальный адрес адрес";
	public static final String CHC_TIME_HM = "Использовать часы/минуты";
	public static final String CHC_TIME_H = "Использовать часы";
	public static final String CHC_TIME_M = "Использовать минуты";
	public static final String CHC_SHORT_ROUTE = "Использовать прямые маршруты";
	public static final String LBL_ENTER_FIRST_ADRESS = "Введите начальный адрес";
	public static final String LBL_ENTER_COUNT_TC = "Введите количество ТС";
	public static final String LBL_ENTER_NEW_ADRESS = "Введите новый адрес";
	public static final String LBL_TABLE_WEIGHT = "Таблица загрузки ТС";
	public static final String BTN_DELETE_ADRESS = "Удалить адрес";
	public static final String BTN_GENER_ADRESS = "Сгенерировать адрес";
	public static final String BTN_ADD_ADRESS = "Добавить адрес";
	public static final String BTN_GENER_CARS = "Сгенерировать ТС";

	public static final String TAB_ENTER_DATA = "Ввод данных";
	public static final String TAB_ROUTING_COSTS = "Транспортные затраты";
	public static final String TAB_VISUALIZATION = "Визуализация";
	public static final String TAB_RESULTS = "Результаты";
	public static JDialog d;
	public static String[] colors = new String[] { "black", "red", "green", "blue", "gray", "orange", "yellow" };

	public static String downloadImage(String s, int w, int h, int zoom) {
		String destinationFile = "resources/image.jpg";
		String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" + s + "&zoom=" + zoom + "&size=" + w + "x"
				+ h + "&scale=2&language=ru";
		System.err.println("download");
		try {

			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
				os.flush();
			}

			is.close();
			os.close();
		} catch (IOException e) {
			closeMessage();
			e.printStackTrace();
			System.exit(1);
		}
		return destinationFile;
	}

	public static String downloadImageWithMarkers(String s, int w, int h, int zoom, ArrayList<String> ss) {
		String destinationFile = "resources/image.jpg";
		// http://maps.googleapis.com/maps/api/staticmap?center=Brooklyn+Bridge,New+York,NY&zoom=13&size=600x300
		// &maptype=roadmap
		// &markers=color:blue%7Clabel:S%7C40.702147,-74.015794
		// &markers=color:green%7Clabel:G%7C40.711614,-74.012318
		// &markers=color:red%7Ccolor:red%7Clabel:C%7C40.718217,-73.998284
		// String imageUrl =
		// "https://maps.googleapis.com/maps/api/staticmap?center=" + s +
		// "&zoom=" + zoom + "&size=" + w + "x"
		// + h + "&scale=2&language=ru&sensor=false";
		System.err.println("download");
		try {

			final String u = "https://maps.googleapis.com/maps/api/staticmap";
			final Map<String, String> params = Maps.newHashMap();
			params.put("center", s);
			params.put("sensor", "false");
			params.put("zoom", String.valueOf(zoom));
			params.put("language", "ru");
			params.put("size", String.valueOf(w) + "x" + String.valueOf(h));

			String markers = "";
			for (int i = 0; i < ss.size() - 1; i++) {// &markers=color:blue%7C40.702147,-74.015794&
				markers += "" + "color:" + colors[ss.indexOf(ss.get(i))] + "%7C" + ss.get(i) + "&markers=";
			}
			markers += "color:" + colors[ss.size() - 1] + "%7C" + ss.get(ss.size() - 1);
			params.put("markers", markers);

			final String baseUrl = u
					+ '?'
					+ encodeParams(params).replace("%26", "&").replace("%3D", "=").replace("%2C", ",").replace("%3A", ":")
							.replace("%257C", "%7C");
			System.err.println(baseUrl);

			URL url = new URL(baseUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
				os.flush();
			}

			is.close();
			os.close();
		} catch (IOException e) {
			closeMessage();
			e.printStackTrace();
			System.exit(1);
		}
		return destinationFile;
	}

	public static Icon getIcon() {
		return new ImageIcon("resources/image.jpg");
	}

	public static void downLoadImage(final JLabel l, final int sliderZoom) {
		d = getDialog(l);

		java.lang.Thread tNew = new java.lang.Thread(new Runnable() {
			@Override
			public void run() {
				ImageIcon i = null;
				try {
					i = new ImageIcon(ImageIO.read(new File(downloadImage(VRP.getFirstAddresStrLatLng(), l.getWidth(),
							l.getHeight(), sliderZoom))));
				} catch (IOException e) {
					VRGUtils.showErrorMess(l, e.getMessage());
				}
				l.setIcon(i);
				closeMess();
			}
		});
		tNew.start();
		d.setVisible(true);
	}

	public static void downLoadImageWithMarkers(final JLabel l, final int sliderZoom, final ArrayList<String> s) {
		d = getDialog(l);

		java.lang.Thread tNew = new java.lang.Thread(new Runnable() {
			@Override
			public void run() {
				ImageIcon i = null;
				try {
					i = new ImageIcon(ImageIO.read(new File(downloadImageWithMarkers(VRP.getFirstAddresStrLatLng(),
							l.getWidth(), l.getHeight(), sliderZoom, s))));
				} catch (IOException e) {
					VRGUtils.showErrorMess(l, e.getMessage());
				}
				l.setIcon(i);
				closeMess();
			}
		});
		tNew.start();
		d.setVisible(true);
	}

	private static JDialog getDialog(JComponent c) {
		return getDialog(c, VRGUtils.MSG_ATTENTION, "Подождите", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void showMess(java.awt.Component frame) {
		d = getDialog(frame, VRGUtils.MSG_ATTENTION, "Подождите", JOptionPane.INFORMATION_MESSAGE);

		java.lang.Thread tNew = new java.lang.Thread(new Runnable() {
			@Override
			public void run() {
				d.setVisible(true);
			}
		});
		tNew.start();
	}

	public static void closeMess() {

		java.util.Timer timer = new java.util.Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				closeMessage();
				this.cancel();
			}
		}, VRGUtils.DELAY, VRGUtils.DELAY);
	}

	public static void closeMessage() {
		if (d != null && d.isShowing()) {
			d.setVisible(false);
			d.dispose();
			d = null;
		}

	}

	public static boolean checkInternetConnection() {
		boolean result = false;
		HttpURLConnection urlc = null;
		try {
			URL url = new URL("http://ya.ru");
			urlc = (HttpURLConnection) url.openConnection();
			urlc.setRequestProperty("Connection", "close");
			urlc.setConnectTimeout(5000); // mTimeout is in seconds
			urlc.connect();

			result = (urlc.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			result = false;
		} finally {
			if (urlc != null) {
				try {
					urlc.disconnect();
				} catch (Exception e) {
					result = false;
				}
			}
		}
		return result;
	}

	public static void checkInternetConnectionWithErr(java.awt.Component frame) {
		if (!checkInternetConnection()) {
			VRGUtils.showErrorMess(frame, "Подключитесь к сети интернет");
		}
	}

	public static void showAutoCLoseMess(java.awt.Component frame, String title, int coef) {
		showAutoCloseMess(frame, title, "", JOptionPane.INFORMATION_MESSAGE, VRGUtils.START * coef);
	}

	public static void showAutoCLoseMess(java.awt.Component frame, String title, String body) {
		showAutoCloseMess(frame, title, body, JOptionPane.INFORMATION_MESSAGE, VRGUtils.START);
	}

	private static JDialog getDialog(java.awt.Component frame, String title, String body, int type) {
		final JOptionPane optionPane = new JOptionPane(body, type, JOptionPane.DEFAULT_OPTION, null, new Object[] {}, null);

		final JDialog dialog = new JDialog();
		dialog.setTitle(title);
		dialog.setModal(true);
		dialog.setLocationRelativeTo(frame);
		dialog.setLocation(dialog.getLocation().x / 2, dialog.getLocation().y / 2);
		dialog.setContentPane(optionPane);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.pack();

		return dialog;
	}

	private static void showAutoCloseMess(java.awt.Component frame, String title, String body, int type, int delay) {
		final JDialog dialog = getDialog(frame, title, body, type);

		java.util.Timer timer = new java.util.Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				dialog.dispose();
			}
		}, VRGUtils.MAX_SIZE, delay);

		dialog.setVisible(true);
	}

	public static String getDelay(Object value) {
		Double d = getDouble(value);

		int h = d.intValue() / 3600;
		int m = (d.intValue() % 3600) / 60;
		int s = (d.intValue() % 3600 % 60) % 60;
		return h + " часов " + m + " минут " + s + " секунд";
	}

	public static String getDistance(Object value) {
		Double d = getDouble(value);
		int h = d.intValue() / 1000;
		int m = d.intValue() % 1000;
		return h + " км " + m + " метров";
	}

	public static String getStrLatLng(String s) {
		try {
			return getLatLngForAddresExc(s).getStrLatLng();
		} catch (JSONException | IOException e) {
			closeMessage();
			return e.getMessage();
		}
	}

	public static Point getLatLngForAddres(String s) {
		try {
			return getLatLngForAddresExc(s);// s//FIXME
		} catch (JSONException | IOException e) {
			closeMessage();
			VRGUtils.showErrorMess(null, e.getMessage());
			return new Point(0, 0);
		}
	}

	public static ArrayList<RouteWithDelayAndDistance> getDistanceMatrix(ArrayList<String> s, boolean isWalking) {
		try {// driving, walking, bicycling
			return getDistanceMatrixAll(s, isWalking ? "walking" : "driving");
		} catch (JSONException | IOException e) {
			closeMessage();
			VRGUtils.showErrorMess(null, e.getMessage());
			return null;
		}
	}

	public static ArrayList<RouteWithDelayAndDistance> getDistanceMatrixForWalking(ArrayList<String> s) {
		try {// driving, walking, bicycling
			return getDistanceMatrixAll(s, "walking");
		} catch (JSONException | IOException e) {
			closeMessage();
			VRGUtils.showErrorMess(null, e.getMessage());
			return null;
		}
	}

	public static ArrayList<RouteWithDelayAndDistance> getDistanceMatrixForCycling(ArrayList<String> s) {
		try {// driving, walking, bicycling
			return getDistanceMatrixAll(s, "bicycling");
		} catch (JSONException | IOException e) {
			closeMessage();
			VRGUtils.showErrorMess(null, e.getMessage());
			return null;
		}
	}

	public static ArrayList<RouteWithDelayAndDistance> getDistanceMatrixForDriving(ArrayList<String> s) {
		try {// driving, walking, bicycling
			return getDistanceMatrixAll(s, "driving");
		} catch (JSONException | IOException e) {
			closeMessage();
			// VRGUtils.showErrorMess(null, e.getMessage());
			return null;
		}
	}

	private static ArrayList<RouteWithDelayAndDistance> getDistanceMatrixAll(ArrayList<String> s, String mode)
			throws JSONException, IOException {
		if (s == null || s.size() < 1) {
			return null;
		}
		ArrayList<RouteWithDelayAndDistance> res = new ArrayList<RouteWithDelayAndDistance>();
		final String[] ss = s.toArray(new String[s.size()]);

		// origins=Vancouver+BC|Seattle&destinations=San+Francisco|Victoria+BC
		final String baseUrl = "http://maps.googleapis.com/maps/api/distancematrix/json";
		final Map<String, String> params = Maps.newHashMap();
		params.put("language", "ru");// язык данных
		params.put("sensor", "false");

		params.put("mode", mode);
		params.put("origins", Joiner.on('|').join(ss));
		params.put("destinations", Joiner.on('|').join(ss));

		final String url = baseUrl + '?' + encodeParams(params);
		System.out.println(url);

		JSONObject response = readUrl(url);
		JSONArray location = response.getJSONArray("rows");

		for (int i = 0; i < location.length(); i++) {// ("elements")
			JSONObject arraysObject = location.getJSONObject(i);
			JSONArray arrays = arraysObject.getJSONArray("elements");

			for (int j = 0; j < arrays.length(); j++) {
				RouteWithDelayAndDistance r = new RouteWithDelayAndDistance(i, j);

				final JSONObject result = arrays.getJSONObject(j);

				final Object distance = result.getJSONObject("distance").get("value");

				final Object duration = result.getJSONObject("duration").get("value");

				r.setDelDis(duration, distance);
				res.add(r);
			}
		}
		System.out.println(res);
		return res;
	}

	public static ArrayList<Integer> getPathsKomiv(ArrayList<String> s) {
		try {
			return getPath(s);
		} catch (JSONException | IOException e) {
			VRGUtils.showErrorMess(null, e.getMessage());
			return null;
		}
	}

	public static int getInt(Object o) {
		return Integer.valueOf(String.valueOf(o.toString()));
	}

	public static Double getDouble(Object o) {
		return Double.valueOf(String.valueOf(o.toString()).replace(",", "."));
	}

	private static ArrayList<Integer> getPath(ArrayList<String> s) throws JSONException, IOException {
		if (s == null || s.size() < 1) {
			return null;
		}
		ArrayList<Integer> path = new ArrayList<Integer>();
		// origin=Adelaide,SA&destination=Adelaide,SA&waypoints=optimize:true|Barossa+Valley,SA|Clare,SA|Connawarra,SA|McLaren+Vale,SA&sensor=false
		final String baseUrl = "http://maps.googleapis.com/maps/api/directions/json";
		final Map<String, String> params = Maps.newHashMap();
		params.put("sensor", "false");
		params.put("language", "ru");// язык данных
		params.put("mode", "driving");
		// адрес, который нужно геокодировать
		params.put("origin", Joiner.on('|').join(new String[] { s.get(0) }));
		params.put("destination", Joiner.on('|').join(new String[] { s.get(0) }));
		s.remove(0);// FIRST ADDRES

		final String[] waypoints = s.toArray(new String[s.size()]);
		// в запросе адреса должны раделяться символом '|'
		params.put("waypoints=optimize:true|", Joiner.on('|').join(waypoints));

		final String url = baseUrl + '?' + encodeParams(params);
		System.out.println(url);

		JSONObject response = readUrl(url);
		final JSONArray arrays = response.getJSONArray("waypoint_order");
		int index = 0;
		if (index < arrays.length()) {
			path.add(Integer.parseInt(String.valueOf(arrays.getJSONObject(index++))));
		}
		System.out.println(path.toString());
		return path;
	}

	private static Point getLatLngForAddresExc(String s) throws JSONException, IOException {
		if (s == null || s.length() < 1) {
			return new Point(0, 0);
		}

		final String baseUrl = "http://maps.googleapis.com/maps/api/geocode/json";
		final Map<String, String> params = Maps.newHashMap();
		params.put("sensor", "false");
		// адрес, который нужно геокодировать
		params.put("address", s);
		final String url = baseUrl + '?' + encodeParams(params);
		System.out.println(url);
		JSONObject response;

		response = readUrl(url);
		JSONObject location = response.getJSONArray("results").getJSONObject(0);
		location = location.getJSONObject("geometry");
		location = location.getJSONObject("location");
		final double lng = location.getDouble("lng");// долгота
		final double lat = location.getDouble("lat");// широта

		System.out.println(lat + ", " + lng);
		return new Point(lat, lng);
	}

	private static JSONObject readUrl(String url) throws IOException, JSONException {
		return JsonReader.read(url);
	}

	public static class RouteWithDelayAndDistance {
		public int start;
		public int end;
		public double delay;
		public double distance;
		public String del = "";
		public String dis = "";

		public RouteWithDelayAndDistance(int s, int e) {
			start = s;
			end = e;
		}

		public void setDelDis(double de, double di) {
			distance = di;
			delay = de;
		}

		public void setDelDis(Object de, Object di) {
			delay = getDouble(de);
			distance = getDouble(di);
			distance = (distance == 0.0) ? Double.NaN : distance;
			dis = "м";
			del = "мин";
		}

		public void setDelDisSplit(String de, String di) {
			String[] dell = de.split(" ");
			String[] diss = di.split(" ");

			delay = getDouble(dell[0]);
			distance = getDouble(diss[0]);

			del = dell[1];
			dis = diss[1];
		}

		public void setDelDis(String de, String di) {
			dis = di;
			dis = de;
		}

		@Override
		public String toString() {
			return "RouteWithDelayAndDistance [start=" + start + ", end=" + end + ", delay=" + delay + del + ", distance="
					+ distance + dis + "\n";
		}
	}

	public static class Point {
		private double lat = 0d;
		private double lng = 0d;
		private String add = "";

		public Point(double la, double ln) {
			lat = la;
			lng = ln;
		}

		public Point(String s) {
			add = s;
			getLatLngForAddres(s);// FIXME
		}

		public double getLat() {
			return lat;
		}

		public double getLng() {
			return lng;
		}

		public String getStrLatLng() {
			return getLat() + "," + getLng();
		}

		public boolean equals(Point p) {
			return (p.lat == this.lat) && (p.lng == this.lng);
		}

		@Override
		public String toString() {
			return "Point [lat=" + lat + ", lng=" + lng + ", add=" + add + "]";
		}
	}
}