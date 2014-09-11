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

	public VRGvertexes(){
		objectVertex = new Object();
	}

}
