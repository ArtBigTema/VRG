package vrg;

import java.awt.Point;

public class VRGvertexes {

	public static class VertexCoords {
		private Point coords;

		public VertexCoords(Point p) {
			this.coords = p;
		}

		public VertexCoords(int x, int y) {
			this.coords = new Point(x, y);
		}

		public Point getPoint() {
			return this.coords;
		}

		@Override
		public String toString() {
			return VRGUtils.TXT_COORDS + VRGUtils.SPACE + VRGUtils.OPENEDBKT + coords.x + VRGUtils.SEMICOLON + coords.y
					+ VRGUtils.CLOSEDBKT;
		}

	}

	public int demand = 0;
	public int price = 0;
	public Object objectVertex;
	public VertexCoords vertexCoords;
	public Object edges;

	public static double getDistance(VertexCoords p1, VertexCoords p2) {
		return Math.sqrt((p2.getPoint().x - p1.getPoint().x) * (p2.getPoint().x - p1.getPoint().x)
				+ (p2.getPoint().y - p1.getPoint().y) * (p2.getPoint().y - p1.getPoint().y));
	}

	public VRGvertexes() {
		objectVertex = new Object();
	}

	public VRGvertexes(int x, int y, int d, int p) {
		objectVertex = new Object();
		vertexCoords = new VertexCoords(x, y);
		price = p;
		demand = d;
	}

	public VRGvertexes(String[] array) {
		this(VRGUtils.getIntFromText(array[0]), VRGUtils.getIntFromText(array[1]), VRGUtils.getIntFromText(array[2]), VRGUtils
				.getIntFromText(array[3]));
	}

	@Override
	public String toString() {
		return vertexCoords.toString() + VRGUtils.SPACE + VRGUtils.TXT_DEMAND + VRGUtils.EQ + demand + VRGUtils.COMMA
				+ VRGUtils.SPACE + VRGUtils.TXT_PRICE + VRGUtils.EQ + price;
	}
}
