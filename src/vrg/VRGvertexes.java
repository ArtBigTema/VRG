package vrg;

import java.awt.Point;

public class VRGvertexes {

	public static class VertexCoords {
		private Point coords;

		public VertexCoords(Point p) {
			this.coords = p;
		}

		public VertexCoords(int x, int y) {
			this.coords.x = x;
			this.coords.y = y;
		}

		public Point getPoint() {
			return this.coords;
		}

		@Override
		public String toString() {
			return StrUtils.TXT_COORDS + StrUtils.SPACE + StrUtils.OPENEDBKT
					+ coords.x + StrUtils.SEMICOLON + coords.y
					+ StrUtils.CLOSEDBKT;
		}

	}

	public int demand = 0;
	public int price = 0;
	public Object objectVertex;
	public VertexCoords vertexCoords;

	public static double getDistance(VertexCoords p1, VertexCoords p2) {
		return Math.sqrt((p2.getPoint().x - p1.getPoint().x)
				* (p2.getPoint().x - p1.getPoint().x)
				+ (p2.getPoint().y - p1.getPoint().y)
				* (p2.getPoint().y - p1.getPoint().y));
	}

	public static double getDistance(Point p1, Point p2) {
		return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y)
				* (p2.y - p1.y));
	}

	public static String getDistanceText(Object p1, Object p2) {
		Double distanse = 0D;
		if (p1 instanceof Point) {
			distanse = getDistance(Point.class.cast(p1), Point.class.cast(p2));
		}
		if (p1 instanceof VertexCoords) {
			distanse = getDistance(VertexCoords.class.cast(p1),
					VertexCoords.class.cast(p2));
		}
		if (distanse.toString().length() < 4) {
			return distanse.toString();
		} else {
			return distanse.toString().substring(0, 4);
		}
	}

	public VRGvertexes() {
		objectVertex = new Object();
	}

	@Override
	public String toString() {
		return vertexCoords.toString() + StrUtils.SPACE + StrUtils.TXT_DEMAND
				+ StrUtils.EQ + demand + "," + StrUtils.SPACE
				+ StrUtils.TXT_PRICE + StrUtils.EQ + price;
	}
}
