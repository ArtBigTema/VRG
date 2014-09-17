package vrg;

import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.mxgraph.canvas.mxICanvas;
import com.mxgraph.canvas.mxImageCanvas;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.view.mxInteractiveCanvas;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;

@SuppressWarnings("serial")
public class GraphFrame extends JFrame {
	public mxGraphComponent graphComponent;
	public static final int length = 20;
	public static final int baseLength = 15;

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

	public GraphFrame() {
		vrgVertexes = new ArrayList<VRGvertexes>();

		mxGraph graph = new mxGraph() {
			public void drawState(mxICanvas canvas, mxCellState state,
					boolean drawLabel) {
				String label = (drawLabel) ? state.getLabel() : "";

				if (getModel().isVertex(state.getCell())
						&& canvas instanceof mxImageCanvas
						&& ((mxImageCanvas) canvas).getGraphicsCanvas() instanceof SwingCanvas) {
					((SwingCanvas) ((mxImageCanvas) canvas).getGraphicsCanvas())
							.drawVertex(state, label);
				} else if (getModel().isVertex(state.getCell())
						&& canvas instanceof SwingCanvas) {
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

			// Sets global image base path
			public mxInteractiveCanvas createCanvas() {
				mxInteractiveCanvas canvas = super.createCanvas();
				return canvas;
			}
		};

		this.getContentPane().add(graphComponent);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 450);
		this.setVisible(true);

		showInitMessage();

		graphComponent.addKeyListener(keyListener);
		graphComponent.addFocusListener(focusListener);
	}

	public static void reSize(int w, int h) {
		width = w;
		height = h;
	}

	private void setZoomIfNeed() {
		java.awt.Point point = VRG.getMaxCoords();
		if ((point.x < width / 3) || (point.y < height / 4)) {
			distance = (width + height) / 50;
		} else {
			distance = 1;
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

	public void constuctGraph(mxGraph graph) {
		if (graph == null && !VRG.isValid()) {
			return;
		}
		setZoomIfNeed();
		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try {
			{
				VRGvertexes a = new VRGvertexes();

				a.objectVertex = graph.insertVertex(parent, null,
						VRGUtils.LABEL_BASE,
						translateX + (VRG.coordinates.get(0).x) * distance,
						translateY + VRG.coordinates.get(0).y * distance,
						baseLength, baseLength,
						"shape=ellipse;perimeter=trianglePerimeter");// x,y,width,height
				a.demand = 0;
				a.price = 0;
				a.vertexCoords = new VRGvertexes.VertexCoords(
						VRG.coordinates.get(0));
				vrgVertexes.add(a);
			}

			// addCars(graph);//FIXME

			for (int i = 1; i < VRG.coordinates.size(); i++) {
				VRGvertexes vertex = new VRGvertexes();

				vertex.objectVertex = graph.insertVertex(parent, null,
						VRGUtils.LABEL_VERTEX + i,
						translateX + VRG.coordinates.get(i).x * distance,
						translateY + VRG.coordinates.get(i).y * distance,
						length, length);// x,y,width,height//StrUtils.GRAPH_PARAM_3

				vertex.demand = VRG.demand.get(i);
				vertex.price = VRG.price.get(i);
				vertex.vertexCoords = new VRGvertexes.VertexCoords(
						VRG.coordinates.get(i));

				vrgVertexes.add(vertex);
			}
			updateEdges(graph);
		} finally {
			graph.getModel().endUpdate();
		}
	}

	public static void constructVertexes() {
		vrgVertexes = new ArrayList<VRGvertexes>();
		VRGvertexes a = new VRGvertexes();
		a.demand = 0;
		a.price = 0;
		a.vertexCoords = new VRGvertexes.VertexCoords(VRG.coordinates.get(0));
		vrgVertexes.add(a);

		for (int i = 1; i < VRG.coordinates.size(); i++) {
			VRGvertexes vertex = new VRGvertexes();
			vertex.demand = VRG.demand.get(i);
			vertex.price = VRG.price.get(i);
			vertex.vertexCoords = new VRGvertexes.VertexCoords(
					VRG.coordinates.get(i));
			vrgVertexes.add(vertex);
		}
	}

	private void addCars(mxGraph graph) {
		for (int i = 1; i < VRG.carsCoordinates.size(); i++) {
			graph.insertVertex(graph.getDefaultParent(), null,
					VRGUtils.LABEL_CARS + i, VRG.carsCoordinates.get(i).x
							* distance, distance + VRG.carsCoordinates.get(i).y
							* distance, 3 * length, length);// x,y,width,height
		}
	}

	private void updateEdges(mxGraph graph) {
		removeAllEdges(graph);

		Double distance;
		if (graph == null) {
			return;
		}
		if (VRG.routes == null || VRG.routes.size() == 0) {
			VRG.generateEdges();
		}

		Object parent = graph.getDefaultParent();

		if ((numberOfSpace + 1) > VRG.routes.size()) {
			VRGframe.isNeedToUpdate = false;
			if (VRGUtils.showInputDialog(this, VRGUtils.MSG_ATTENTION,
					VRGUtils.MSG_ERR_ROUTES)) {
				VRG.generateEdges();
			}
			VRGframe.isNeedToUpdate = true;
			numberOfSpace = 0;
		}

		ArrayList<Integer> tmp = VRG.routes.get(numberOfSpace);

		int in = 0;
		for (int index : tmp) {
			distance = VRGvertexes.getDistance(
					vrgVertexes.get(in).vertexCoords,
					vrgVertexes.get(index).vertexCoords);

			graph.insertEdge(parent, null, distance.toString().substring(0, 3),
					vrgVertexes.get(in).objectVertex,
					vrgVertexes.get(index).objectVertex, VRGUtils.GRAPH_PARAM_2);
			in = index;
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
			if (paramKeyEvent.getKeyCode() == (KeyEvent.VK_SPACE)
					&& VRG.routes != null && VRG.routes.size() > 0) {
				numberOfSpace++;
				updateEdges(graphComponent.getGraph());
			}

			if (paramKeyEvent.getKeyCode() == KeyEvent.VK_ALT) {
				VRGUtils.takeScreenShotOfWindow(GraphFrame.this);
			}
			if (paramKeyEvent.getKeyCode() == KeyEvent.VK_CONTROL) {
				VRGUtils.takeScreenCapture(GraphFrame.this);
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
				GraphFrame.this.dispose();
			}
		}

		@Override
		public void focusGained(FocusEvent arg0) {

		}
	};

	public static void main(String[] args) {
		VRG.main(new String[] { "" });
	}
}
