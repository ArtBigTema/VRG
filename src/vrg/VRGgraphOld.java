package vrg;

import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Map;

import ru.amse.smyshlyaev.grapheditor.graph.Edge;

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
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxEdgeStyle;
import com.mxgraph.view.mxGraph;

@SuppressWarnings("serial")
public class VRGgraphOld extends javax.swing.JFrame {
	public mxGraphComponent graphComponent;
	public final int length = 20;
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

		showInitMessage();

		graphComponent.addKeyListener(keyListener);
		graphComponent.addFocusListener(focusListener);

		// graphComponent.setBackgroundImage(VRGUtils.getImageForGraph());//FIXME
		repaint();
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
		int coef = 8;
		if (!b) {
			coef = 5;
		}
		Point point;
		Point x, y;
		y = routess.maxY();// FIXME
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
				// a.setSection(new Section(routess.getDepo()));
				vrgVertexes.add(a);
			}

			if (!withTimeWindow) {
				addCars(graph);
			}
			for (Route arr : routess.getRoutes()) {
				for (Section s : arr.getRoute()) {
					VRGvertexes vertex = new VRGvertexes();
					// Null is id
					vertex.setStartCell(graph.insertVertex(parent, null, s.getStart().toString(), translateX + s.getStart().x
							* distance, translateY + s.getStart().y * distance, length, length));// x,y,width,height//StrUtils.GRAPH_PARAM_3
					vertex.setEndCell(graph.insertVertex(parent, null, s.getEnd().toString(), translateX + s.getEnd().x
							* distance, translateY + s.getEnd().y * distance, length, length));// x,y,width,height//StrUtils.GRAPH_PARAM_3
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
		mxCell v1 = (mxCell) graph.insertVertex(parent, null, "Depo", -20, -20, 50, 50, "");
		mxGeometry geo = graph.getModel().getGeometry(v1);
		// The size of the rectangle when the minus sign is clicked
		geo.setAlternateBounds(new mxRectangle(20, 20, 100, 50));

		mxGeometry geo1 = new mxGeometry(0.5, 1.0, PORT_DIAMETER, PORT_DIAMETER);
		geo1.setOffset(new mxPoint(-PORT_RADIUS, -PORT_RADIUS));
		geo1.setRelative(true);

		mxCell port1 = new mxCell(null, geo1, "shape=ellipse;perimter=ellipsePerimeter");
		port1.setVertex(true);

		mxGeometry geo3 = new mxGeometry(1.0, 1.0, PORT_DIAMETER, PORT_DIAMETER);
		geo3.setOffset(new mxPoint(-PORT_RADIUS, -PORT_RADIUS));
		geo3.setRelative(true);

		mxCell port3 = new mxCell(null, geo3, "shape=ellipse;perimter=ellipsePerimeter");
		port3.setVertex(true);

		mxGeometry geo2 = new mxGeometry(1.0, 0.5, PORT_DIAMETER, PORT_DIAMETER);
		geo2.setOffset(new mxPoint(-PORT_RADIUS, -PORT_RADIUS));
		geo2.setRelative(true);

		mxCell port2 = new mxCell(null, geo2, "shape=ellipse;perimter=ellipsePerimeter");
		port2.setVertex(true);
		v1.setConnectable(false);

		graph.addCell(port1, v1);
		graph.addCell(port2, v1);
		graph.addCell(port3, v1);

		Object v2 = graph.insertVertex(parent, null, "World!", 240, 150, 80, 30);
		graph.insertEdge(parent, null, "Edge", port2, v2);
		Object v3 = graph.insertVertex(parent, null, "World!", 240, 250, 80, 30);
		graph.insertEdge(parent, null, "Edge", port1, v3);
		Object v4 = graph.insertVertex(parent, null, "World!", 140, 250, 80, 30);
		graph.insertEdge(parent, null, "Edge", port3, v4);
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

		Map<String, Object> style = graph.getStylesheet().getDefaultEdgeStyle();
		style.put(mxConstants.STYLE_EDGE, mxEdgeStyle.Loop);// FIXME сделай своё

		String distance = "";
		for (VRGvertexes v : vrgVertexes) {
			if (v.getSection() == null) {
				continue;
			}
			distance = " " + v.getSection().getStrInnerDistance() + " ";

			graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "green", new Object[] { v.startObjectVertex });
			graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "red", new Object[] { v.endObjectVertex });

			graph.insertEdge(parent, null, distance, v.cellStart, v.cellEnd);// VRGUtils.GRAPH_PARAM_4);

		}
		for (Route arr : routess.getRoutes()) {
			for (Section s : arr.getRoute()) {
				// VRGvertexes vertex = new VRGvertexes();
				// graph.insertEdge(parent, null, s.getStrInnerDistance(),
				// vrgVertexes.get(in).objectVertex,
				// vrgVertexes.get(index).objectVertex);//
				// VRGUtils.GRAPH_PARAM_4);

			}
		}
		graph.setAutoSizeCells(true);

		Object[] edges = graph.getEdgesBetween(vrgVertexes.get(0).objectVertex, vrgVertexes.get(0).objectVertex);

		for (Object edge : edges) {
			graph.getModel().remove(edge);
		}
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
