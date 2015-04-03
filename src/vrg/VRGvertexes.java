package vrg;

import java.text.DecimalFormat;

import com.mxgraph.model.mxCell;

import vrg.VRGUtils.Point;
import vrg.VRGroutes.Route.Section;

public class VRGvertexes {
	private static DecimalFormat df = new DecimalFormat("#.#");// FIXME

	public static class VertexCoords {
		private Point coord;
		private Point endCoord;

		public VertexCoords(Point p) {
			this.coord = p;
		}

		public VertexCoords(Point s, Point e) {
			this.coord = s;
			this.endCoord = e;
		}

		public VertexCoords(int x, int y) {
			this.coord = new Point(x, y);
		}

		public void setEndCoord(int x, int y) {
			setEndCoord(new Point(x, y));
		}

		public void setEndCoord(Point p) {
			endCoord = p;
		}

		public void setStartCoord(int x, int y) {
			setStartCoord(new Point(x, y));
		}

		public void setStartCoord(Point p) {
			coord = p;
		}

		public Point getEndCoord() {
			return endCoord;
		}

		public boolean isContainsEndPoint() {
			return endCoord != null;
		}

		public Point getStartCoord() {
			return this.coord;
		}
		
		public boolean eq(Point p){
			return (p.x == this.coord.x) && (p.y == this.coord.y);
		}

		@Override
		public String toString() {
			return VRGUtils.TXT_COORDS + VRGUtils.SPACE + VRGUtils.OPENEDBKT + coord.x + VRGUtils.SEMICOLON + coord.y
					+ VRGUtils.CLOSEDBKT;
		}

	}

	public int demand = 0;
	public int price = 0;
	public Object objectVertex;
	public Object startObjectVertex;
	public Object endObjectVertex;
	public VertexCoords vertexCoords;
	public Object edges;
	public Section section;
	public mxCell cell;
	public mxCell cellStart;
	public mxCell cellEnd;

	public static double getDistance(VertexCoords p1, VertexCoords p2) {
		return Math.sqrt((p2.getStartCoord().x - p1.getStartCoord().x) * (p2.getStartCoord().x - p1.getStartCoord().x)
				+ (p2.getStartCoord().y - p1.getStartCoord().y) * (p2.getStartCoord().y - p1.getStartCoord().y));
	}

	public void setStartCell(Object o) {
		startObjectVertex = o;
		cellStart = (mxCell) o;
	}

	public void setEndCell(Object o) {
		endObjectVertex = o;
		cellEnd = (mxCell) o;
	}

	public static String getStrDistance(VertexCoords p1, VertexCoords p2) {
		return df.format(getDistance(p1, p2));
	}

	public VRGvertexes() {
		// objectVertex = new Object();
	}

	public VRGvertexes(int x, int y, int d, int p) {
		// objectVertex = new Object();
		vertexCoords = new VertexCoords(x, y);
		price = p;
		demand = d;
	}

	public void setSection(Section s) {
		section = s;
	}

	public Section getSection() {
		return section;
	}

	public boolean equalsPoint(Point ps, Point pe) {
		return vertexCoords.getStartCoord().equals(ps) && vertexCoords.getEndCoord().equals(pe);
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
