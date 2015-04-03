package vrg;

import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import vrg.VRGUtils.Point;
import vrg.VRGroutes.Route;
import vrg.VRGroutes.Route.Section;

import com.mxgraph.canvas.mxICanvas;
import com.mxgraph.canvas.mxImageCanvas;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.view.mxInteractiveCanvas;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxEdgeStyle;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

@SuppressWarnings("serial")
public class VRGgraphOld extends javax.swing.JFrame {
	public mxGraphComponent graphComponent;
	public final int length = 40;
	public final int baseLength = 15;
	public boolean isCompleted = false;
	public boolean withTimeWindow;
	// public ArrayList<Point> coordinates = new ArrayList<Point>();
	// public ArrayList<ArrayList<Integer>> routes = new
	// ArrayList<ArrayList<Integer>>();
	public static VRGroutes routess;

	public static int distance = VRGUtils.DISTANCE;
	public static int zoom = 1;
	public static int height = 100;
	public static int width = 100;
	public int radius = 40;
	public int translateX = 0;
	public int translateY = 0;

	public static ArrayList<VRGvertexes> vrgVertexes;
	public int numberOfSpace = 0;

	private void showInitMessage() {
		VRGUtils.showInitMessage(this, VRGUtils.MSG_INIT);
	}

	public VRGgraphOld(VRGroutes r) {
		// coordinates = new ArrayList<Point>(p);
		routess = new VRGroutes(r);
		withTimeWindow = true;
		setUp();
	}

	public VRGgraphOld() {
		// coordinates = new ArrayList<Point>(VRG.coordinates);
		routess = new VRGroutes(VRG.routes, VRG.coordinates);
		withTimeWindow = false;
		setUp();
	}

	private void setUp() {

		vrgVertexes = new ArrayList<VRGvertexes>();
		mxGraph graph = new mxGraph() {
			public void drawState(mxICanvas canvas, mxCellState state, boolean drawLabel) {
				String label = (drawLabel) ? state.getLabel() : "";

				if (getModel().isVertex(state.getCell()) && canvas instanceof mxImageCanvas
						&& ((mxImageCanvas) canvas).getGraphicsCanvas() instanceof SwingCanvas) {
					((SwingCanvas) ((mxImageCanvas) canvas).getGraphicsCanvas()).drawVertex(state, label);
				} else if (getModel().isVertex(state.getCell()) && canvas instanceof SwingCanvas) {
					((SwingCanvas) canvas).drawVertex(state, label);
				} else {
					super.drawState(canvas, state, drawLabel);
				}
			}
		};

		this.constuctGraph(graph);
		graphComponent = new mxGraphComponent(graph) {

			@Override
			public void paint(Graphics paramGraphics) {
				super.paint(paramGraphics);
				VRGUtils.paintCarcass(paramGraphics.create());

			}

			public mxInteractiveCanvas createCanvas() {
				mxInteractiveCanvas canvas = super.createCanvas();
				return canvas;
			}
		};

		this.getContentPane().add(graphComponent);
		this.setSize(600, 450);
		this.setVisible(true);

		// graphComponent.setBackgroundImage(VRGUtils.getImageForGraph());
		repaint();
		// showInitMessage();FIXME

		graphComponent.addKeyListener(keyListener);
		graphComponent.addFocusListener(focusListener);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (isCompleted) {
			// g.drawOval(translateX + (coordinates.get(0).x) * distance -
			// VRGUtils.radius, translateY + (coordinates.get(0).y)
			// * distance - VRGUtils.radius, VRGUtils.radius, VRGUtils.radius);
			// x-radius, y-radius, radius*2, radius*2
		}
		// g.drawImage(VRGUtils.getImageForGraph().getImage(), 0, 0,
		// g.getClipBounds().width, g.getClipBounds().height, null);
	}

	public static void reSize(int w, int h) {
		width = w;
		height = h;
	}

	private void setZoomIfNeed(boolean b) {
		int coef = 15;
		if (!b) {
			coef = 5;
		}
		Point point;
		Point x, y;
		y = routess.maxY();
		x = routess.maxX();

		point = new Point(x.x, y.y);
		distance = (point.x + point.y) * coef / 2;

		translateX = 2 * coef;
		translateY = 2 * coef;
	}

	public void constuctGraph(mxGraph graph) {
		if (graph == null && !VRG.isValid()) {
			return;
		}
		setZoomIfNeed(withTimeWindow);

		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try {
			{
				VRGvertexes a = new VRGvertexes();

				a.objectVertex = graph.insertVertex(parent, null, VRGUtils.LABEL_BASE, translateX + routess.getDepo().x
						* distance, translateY + routess.getDepo().y * distance, baseLength, baseLength,
						"shape=ellipse;perimeter=trianglePerimeter");// x,y,width,height
				a.setEndCell(a.objectVertex);
				a.setStartCell(a.objectVertex);
				// a.setSection(new Section(new Point(0, 0)));
				// a.setSection(new Section(routess.getDepo()));
				// vrgVertexes.add(a);
			}

			if (!withTimeWindow) {
				addCars(graph);
			}
			for (Route arr : routess.getRoutes()) {
				for (Section s : arr.getRoute()) {
					VRGvertexes vertex = new VRGvertexes();
					// Null is id
					vertex.setStartCell(graph.insertVertex(parent, null, s.getStart().toString(), translateX + s.getStart().x
							* distance, translateY + s.getStart().y * distance, length, length / 2));// x,y,width,height//StrUtils.GRAPH_PARAM_3
					vertex.setEndCell(graph.insertVertex(parent, null, s.getEnd().toString(), translateX + s.getEnd().x
							* distance, translateY + s.getEnd().y * distance, length, length / 2));// x,y,width,height//StrUtils.GRAPH_PARAM_3
					vertex.setSection(s);

					if (!withTimeWindow) {
						// vertex.demand = VRG.demand.get(i);
						// vertex.price = VRG.price.get(i);
					}
					vertex.vertexCoords = new VRGvertexes.VertexCoords(s.getStart(), s.getEnd());

					vrgVertexes.add(vertex);
				}
			}
			updateEdges(graph);
		} finally {
			isCompleted = true;
			graph.getModel().endUpdate();
		}
	}

	private void addCars(mxGraph graph) {
		for (int i = 1; i < VRG.carsCoordinates.size(); i++) {
			graph.insertVertex(graph.getDefaultParent(), null, VRGUtils.LABEL_CARS + i, 50, VRG.carsCoordinates.get(i).y / 2,
					2 * length, length);
		}
	}

	private void addDepo(mxGraph graph) {
		Object parent = graph.getDefaultParent();

		final int PORT_DIAMETER = 20;
		final int PORT_RADIUS = PORT_DIAMETER / 2;
		mxCell depo = (mxCell) graph.insertVertex(parent, null, "Depo", 0, 0, 50, 50, "");
		depo.setConnectable(false);

		mxGeometry geo = graph.getModel().getGeometry(depo);
		// The size of the rectangle when the minus sign is clicked
		geo.setAlternateBounds(new mxRectangle(00, 00, 50, 50));

		float[][][] coords = new float[][][] { { { 1, 1 } }, { { 0.5f, 1 }, { 1, 0.5f } },
				{ { 0.5f, 1 }, { 1, 1 }, { 1, 0.5f } } };
		ArrayList<mxCell> ports = new ArrayList<mxCell>();

		int size = routess.getCountRoutess();
		for (int i = 0; i < size; i++) {
			float x = coords[size - 1][i][0];
			float y = coords[size - 1][i][1];
			mxGeometry g = new mxGeometry(x, y, PORT_DIAMETER, PORT_DIAMETER);
			g.setOffset(new mxPoint(-PORT_RADIUS, -PORT_RADIUS));
			g.setRelative(true);

			mxCell port = new mxCell(null, g, "shape=rectangle;perimter=rectanglePerimeter");
			port.setVertex(true);
			ports.add(port);

			graph.addCell(port, depo);
		}

		for (int i = 0; i < size; i++) {
			Object v = (graph.insertVertex(parent, null, getFirstRoute(i).toString(), translateX + getFirstRoute(i).x
					* distance, translateY + getFirstRoute(i).y * distance, length, length / 2, "FILLCOLOR=green;"));
			graph.insertEdge(parent, null, VRGroutes.getStrDistance(new Point(0, 0), getFirstRoute(i)), ports.get(i), v,
					"strokeColor=green;edgeStyle=Loop;dashed=1;entryX=0;entryPerimeter=1;exitPerimeter=0;");
		}
	}

	private Point getFirstRoute(int i) {
		return routess.getRoutes().get(i).getRoute().get(1).getStart();
	}

	private void updateEdges(mxGraph graph) {
		removeAllEdges(graph);

		if (graph == null) {
			return;
		}
		if (routess == null || routess.getCountRoutess() == 0) {
			generateRoutes();
		}

		Object parent = graph.getDefaultParent();
		addDepo(graph);

		if ((numberOfSpace + 1) > routess.getCountRoutess()) {
			VRGframe.isNeedToUpdate = false;
			if (VRGUtils.showInputDialog(this, VRGUtils.MSG_ATTENTION, VRGUtils.MSG_ERR_ROUTES_OPTIM)) {
				generateRoutes(true);
			}
			VRGframe.isNeedToUpdate = true;
			numberOfSpace = 0;
		}

		setStyle(graph);

		String GRAPH_PARAM_2 = "strokeColor=red;edgeStyle=EntityRelation;elbow=vertical;entryX=0;entryY=1;entryPerimeter=0;exitPerimeter=1;";
		String GRAPH_PARAM_4 = "strokeColor=green;edgeStyle=Loop;dashed=1;entryX=0;entryPerimeter=1;exitPerimeter=0;";

		String distance = "";
		Point tmp = new Point(0, 0);
		for (VRGvertexes v : vrgVertexes) {
			if (v.getSection() == null || v.vertexCoords.eq(tmp)) {
				continue;
			}
			distance = " " + v.getSection().getStrInnerDistance() + " ";

			graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "green", new Object[] { v.startObjectVertex });
			graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "red", new Object[] { v.endObjectVertex });

			graph.insertEdge(parent, null, distance, v.cellStart, v.cellEnd, GRAPH_PARAM_2);
		}
		int in = 0;
		int index = 0;
		for (Route arr : routess.getRoutes()) {
			for (Section s : arr.getRoute()) {
				in = getVertex(s.getStart(), s.getEnd());
				if (vrgVertexes.get(in).vertexCoords.eq(tmp)) {
					index = in;
					continue;
				}
				graph.insertEdge(
						parent,
						null,
						VRGroutes.getStrDistance(vrgVertexes.get(index).getSection().getEnd(), vrgVertexes.get(in).getSection()
								.getStart()), vrgVertexes.get(index).endObjectVertex, vrgVertexes.get(in).startObjectVertex,
						GRAPH_PARAM_4);
				index = in;
			}
		}
		graph.setAutoSizeCells(true);

		Object[] edges = graph.getEdgesBetween(vrgVertexes.get(0).objectVertex, vrgVertexes.get(0).objectVertex);

		for (Object edge : edges) {
			graph.getModel().remove(edge);
		}
	}

	private void setStyle(mxGraph graph) {
		Map<String, Object> style = graph.getStylesheet().getDefaultEdgeStyle();
		// styl.put(mxConstants.STYLE_EDGE, mxEdgeStyle.Loop);// FIXME сделай
		style.put(mxConstants.STYLE_EDGE, mxEdgeStyle.EntityRelation);//
		style.put(mxConstants.STYLE_STROKEWIDTH, 2);
		style.put(mxConstants.STYLE_FONTSIZE, 12);
		style.put(mxConstants.STYLE_FONTCOLOR, "black");
		style.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_OVAL);
		style.put(mxConstants.STYLE_LABEL_BACKGROUNDCOLOR, "#ffffff");
		style.put(mxConstants.STYLE_LABEL_BORDERCOLOR, "#000000");
		style.put(mxConstants.STYLE_LABEL_POSITION, mxConstants.ALIGN_TOP);
	}

	private int getVertex(Point start, Point end) {
		for (VRGvertexes v : vrgVertexes) {
			if (v.equalsPoint(start, end))
				return vrgVertexes.indexOf(v);
		}
		return 0;
	}

	private void generateRoutes(boolean isOptim) {
		if (isOptim) {
			numberOfSpace = 0;
		}
		String mess = "Координаты отсортированы";
		if (withTimeWindow) {
			mess = VRGwithTimeWindow.generateOptim(isOptim);
			routess = VRGwithTimeWindow.getRoutess();
		} else {
			VRG.generateEdges();
			routess = new VRGroutes(VRG.routes, VRG.coordinates);
		}
		VRGUtils.showAutoCLoseMess(VRGgraphOld.this, VRGUtils.MSG_TITLE_GENER, mess);
		removeAllEdges(graphComponent.getGraph());
	}

	private void generateRoutes() {
		generateRoutes(false);
	}

	private void removeAllEdges(mxGraph graph) {
		graph.getModel().beginUpdate();
		try {
			for (VRGvertexes v : vrgVertexes) {
				if (v.objectVertex == null) {
					continue;// FIXME to start/end
				}
				Object[] edges = graph.getEdges((mxCell) v.objectVertex);
				if (edges == null || edges.length == 0) {
					continue;
				}
				for (Object edge : edges) {
					graph.getModel().remove(edge);
				}
			}

		} finally {
			graph.getModel().endUpdate();
		}
	}

	public KeyListener keyListener = new KeyListener() {
		@Override
		public void keyReleased(KeyEvent paramKeyEvent) {
			if (paramKeyEvent.getKeyCode() == (KeyEvent.VK_SPACE)) {
				numberOfSpace++;
				generateRoutes();
				updateEdges(graphComponent.getGraph());
			}

			if (paramKeyEvent.getKeyCode() == KeyEvent.VK_ALT) {
				VRGUtils.takeScreenShotOfWindow(VRGgraphOld.this);
			}
			if (paramKeyEvent.getKeyCode() == KeyEvent.VK_CONTROL) {
				VRGUtils.takeScreenCapture(VRGgraphOld.this);
			}
			if (paramKeyEvent.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
				graphComponent.setBackgroundImage(null);
				repaint();
			}
		};

		@Override
		public void keyTyped(KeyEvent paramKeyEvent) {

		}

		@Override
		public void keyPressed(KeyEvent paramKeyEvent) {

		}
	};
	FocusListener focusListener = new FocusListener() {

		@Override
		public void focusLost(FocusEvent arg0) {
			if (VRGframe.isNeedToUpdate) {
				VRGgraphOld.this.dispose();
			}
		}

		@Override
		public void focusGained(FocusEvent arg0) {

		}
	};
}
