package vrg;

import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import vrg.VRGUtils.Point;

import com.mxgraph.canvas.mxICanvas;
import com.mxgraph.canvas.mxImageCanvas;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.view.mxInteractiveCanvas;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;

@SuppressWarnings("serial")
public class VRGgraphOld extends javax.swing.JFrame {
	public mxGraphComponent graphComponent;
	public static final int length = 20;
	public static final int baseLength = 15;
	public static boolean isCompleted = false;
	public boolean isTimeWindow;// FIXME
	public static ArrayList<Point> coordinates = new ArrayList<Point>();
	public static ArrayList<ArrayList<Integer>> routes = new ArrayList<ArrayList<Integer>>();

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

	public VRGgraphOld(ArrayList<Point> p, ArrayList<ArrayList<Integer>> r) {
		coordinates = new ArrayList<Point>(p);
		routes = new ArrayList<ArrayList<Integer>>(r);
		isTimeWindow = true;
		setUp();
	}

	public VRGgraphOld() {
		coordinates = new ArrayList<Point>(VRG.coordinates);
		routes = new ArrayList<ArrayList<Integer>>(VRG.routes);
		isTimeWindow = false;
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
	}

	@Override
	public void paint(Graphics paramGraphics) {
		super.paint(paramGraphics);
		if (isCompleted) {
			paramGraphics.drawOval(translateX + (coordinates.get(0).x) * distance - VRGUtils.radius,
					translateY + (coordinates.get(0).y) * distance - VRGUtils.radius, VRGUtils.radius, VRGUtils.radius);
			// x-radius, y-radius, radius*2, radius*2
		}
	}

	public static void reSize(int w, int h) {
		width = w;
		height = h;
	}

	private void setZoomIfNeed() {
		VRGUtils.Point point = VRG.getMaxCoords();
		if ((point.x < width / 3) || (point.y < height / 4)) {
			distance = (width + height) / 50;
		} else {
			distance = 1;
			VRGUtils.DISTANCE = distance;
		}
		point = VRG.getMinCoords();
		if ((point.x < width / 10) || (point.y < height / 10)) {
			translateX = -point.x * distance + 2 * radius;
			translateY = -point.y * distance + 2 * radius;
		} else {
			translateX = 0;
			translateY = 0;
		}
	}

	private void setZoomIfNeed(boolean b) {
		if (!b) {
			setZoomIfNeed();
			return;
		}
		Point point;
		Point x, y;
		y = Collections.max(coordinates, new Comparator<Point>() {
			@Override
			public int compare(Point paramInt1, Point paramInt2) {
				return Integer.compare(paramInt1.y, paramInt2.y);
			}
		});
		x = Collections.max(coordinates, new Comparator<Point>() {
			@Override
			public int compare(Point paramInt1, Point paramInt2) {
				return Integer.compare(paramInt1.x, paramInt2.x);
			}
		});

		point = new Point(x.x, y.y);

		distance = (point.x + point.y) * 4;

		translateX = 10;
		translateY = 10;
	}

	public void constuctGraph(mxGraph graph) {
		if (graph == null && !VRG.isValid()) {
			return;
		}
		setZoomIfNeed(isTimeWindow);

		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try {
			{
				VRGvertexes a = new VRGvertexes();

				a.objectVertex = graph.insertVertex(parent, null, VRGUtils.LABEL_BASE, translateX + coordinates.get(0).x
						* distance, translateY + coordinates.get(0).y * distance, baseLength, baseLength,
						"shape=ellipse;perimeter=trianglePerimeter");// x,y,width,height
				a.demand = 0;
				a.price = 0;
				a.vertexCoords = new VRGvertexes.VertexCoords(coordinates.get(0));
				vrgVertexes.add(a);
			}

			if (!isTimeWindow) {
				addCars(graph);
			}

			for (int i = 1; i < coordinates.size(); i++) {
				VRGvertexes vertex = new VRGvertexes();

				vertex.objectVertex = graph.insertVertex(parent, null, VRGUtils.LABEL_VERTEX + i,
						translateX + coordinates.get(i).x * distance, translateY + coordinates.get(i).y * distance, length,
						length);// x,y,width,height//StrUtils.GRAPH_PARAM_3

				if (!isTimeWindow) {
					vertex.demand = VRG.demand.get(i);
					vertex.price = VRG.price.get(i);
				}
				vertex.vertexCoords = new VRGvertexes.VertexCoords(coordinates.get(i));

				vrgVertexes.add(vertex);
			}
			updateEdges(graph);
		} finally {
			isCompleted = true;
			graph.getModel().endUpdate();
		}
	}

	public void constructVertexes() {
		vrgVertexes = new ArrayList<VRGvertexes>();
		VRGvertexes a = new VRGvertexes();
		a.demand = 0;
		a.price = 0;
		a.vertexCoords = new VRGvertexes.VertexCoords(coordinates.get(0));
		vrgVertexes.add(a);

		for (int i = 1; i < coordinates.size(); i++) {
			VRGvertexes vertex = new VRGvertexes();
			if (!isTimeWindow) {
				vertex.demand = VRG.demand.get(i);
				vertex.price = VRG.price.get(i);
			}
			vertex.vertexCoords = new VRGvertexes.VertexCoords(coordinates.get(i));
			vrgVertexes.add(vertex);
		}
	}

	private void addCars(mxGraph graph) {
		for (int i = 1; i < VRG.carsCoordinates.size(); i++) {
			graph.insertVertex(graph.getDefaultParent(), null, VRGUtils.LABEL_CARS + i, 50, VRG.carsCoordinates.get(i).y / 2,
					2 * length, length);
		}
	}

	private void updateEdges(mxGraph graph) {
		removeAllEdges(graph);

		Double distance;
		if (graph == null) {
			return;
		}
		if (routes == null || routes.size() == 0) {
			VRG.generateEdges();
			routes = new ArrayList<ArrayList<Integer>>(VRG.routes);
		}

		Object parent = graph.getDefaultParent();

		if ((numberOfSpace + 1) > routes.size()) {
			VRGframe.isNeedToUpdate = false;
			if (VRGUtils.showInputDialog(this, VRGUtils.MSG_ATTENTION, VRGUtils.MSG_ERR_ROUTES)) {
				VRG.generateEdges();
			}
			VRGframe.isNeedToUpdate = true;
			numberOfSpace = 0;
		}

		for (ArrayList<Integer> tmp : routes) {
			int in = 0;
			for (int index : tmp) {
				distance = VRGvertexes.getDistance(vrgVertexes.get(in).vertexCoords, vrgVertexes.get(index).vertexCoords);

				graph.insertEdge(parent, null, distance.toString().substring(0, 3), vrgVertexes.get(in).objectVertex,
						vrgVertexes.get(index).objectVertex);// VRGUtils.GRAPH_PARAM_4);
				in = index;
			}
		}
	}

	private void removeAllEdges(mxGraph graph) {
		graph.getModel().beginUpdate();
		try {
			for (VRGvertexes v : vrgVertexes) {
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
			if (paramKeyEvent.getKeyCode() == (KeyEvent.VK_SPACE) && VRG.routes != null && VRG.routes.size() > 0) {
				numberOfSpace++;
				updateEdges(graphComponent.getGraph());
			}

			if (paramKeyEvent.getKeyCode() == KeyEvent.VK_ALT) {
				VRGUtils.takeScreenShotOfWindow(VRGgraphOld.this);
			}
			if (paramKeyEvent.getKeyCode() == KeyEvent.VK_CONTROL) {
				VRGUtils.takeScreenCapture(VRGgraphOld.this);
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
