package vrg;

import java.awt.Color;
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
	public static final int distance = 40;
	public static int count = 8;
	public static int[][] coordinates = { { 19, 45 }, { 18, 46 }, { 20, 47 },
			{ 22, 42 }, { 20, 41 }, { 14, 40 }, { 12, 44 }, { 13, 45 } };
	public int[] demand = { 0, 1, 1, 1, 2, 2, 2, 1 };
	public int[] price = { 0, 4, 4, 4, 4, 4, 4, 4 };
	public static int[][] cars = { { 11, 39 }, { 15, 39 }, { 20, 39 } };
	public static ArrayList<VRGvertexes> vrgVertexes;
	public int nymberOfSpace = 0;

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

				paintCarcass(paramGraphics.create());
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

		graphComponent.addKeyListener(keyListener);
		graphComponent.addFocusListener(focusListener);
	}

	private void paintCarcass(Graphics paramGraphics) {
		paramGraphics.setColor(Color.BLACK);

		int numX = paramGraphics.getClipBounds().width / 10;
		int numY = paramGraphics.getClipBounds().height / 10;

		int offset = 5;
		int dx = 0, dy = 0;
		for (int i = 0; i < 10; i++) {
			dx += numX;
			dy += numY;
			paramGraphics.drawLine(0, dy, offset, dy);
			paramGraphics.drawString(String.valueOf(dy / distance), offset + 1,
					dy);

			paramGraphics.drawLine(dx, 0, dx, offset);
			paramGraphics.drawString(String.valueOf(dx / distance), dx,
					offset * 3);
		}

		paramGraphics.drawLine(0, 1, paramGraphics.getClipBounds().width, 1);

		paramGraphics.drawLine(1, 0, 1, paramGraphics.getClipBounds().height);
	}

	public void constuctGraph(mxGraph graph) {
		if (graph == null) {
			return;
		}
		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try {
			{
				VRGvertexes a = new VRGvertexes();

				a.objectVertex = graph
						.insertVertex(parent, null, StrUtils.LABEL_BASE,
								(VRG.coordinates.get(0).x) * distance,
								VRG.coordinates.get(0).y * distance,
								baseLength, baseLength,
								"shape=ellipse;perimeter=trianglePerimeter");// x,y,width,height
				a.demand = 0;
				a.price = 0;
				a.vertexCoords = new VRGvertexes.VertexCoords(
						VRG.coordinates.get(0));
				vrgVertexes.add(a);
			}

			addCars(graph);

			for (int i = 1; i < VRG.coordinates.size(); i++) {
				VRGvertexes vertex = new VRGvertexes();

				vertex.objectVertex = graph.insertVertex(parent, null,
						StrUtils.LABEL_VERTEX + i, VRG.coordinates.get(i).x
								* distance,
						VRG.coordinates.get(i).y * distance, length, length);// x,y,width,height//StrUtils.GRAPH_PARAM_3

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
		for (int i = 0; i < VRG.carsCoordinates.size(); i++) {
			graph.insertVertex(graph.getDefaultParent(), null,
					StrUtils.LABEL_CARS + i, VRG.carsCoordinates.get(i).x
							* distance,
					VRG.carsCoordinates.get(i).y * distance, 3 * length, length);// x,y,width,height
		}
	}

	private void updateEdges(mxGraph graph) {
		removeAllEdges(graph);

		Double distance;
		if (graph == null) {
			return;
		}
		if (VRG.routes == null || VRG.routes.size() == 0) {
			VRG.generateRoutes();
		}

		Object parent = graph.getDefaultParent();

		int k;
		ArrayList<Integer> tmp;

		do {
			if (nymberOfSpace > VRG.routes.size() / 3) {
				VRG.generateRoutes();
			}
			k = VRG.random(1, vrgVertexes.size());
			tmp = VRG.routes.get(k - 1);
		} while (tmp.size() < 2);

		int in = 0;
		tmp.remove(0);
		for (int index : tmp) {
			distance = VRGvertexes.getDistance(
					vrgVertexes.get(in).vertexCoords,
					vrgVertexes.get(index).vertexCoords);

			graph.insertEdge(parent, null, distance.toString().substring(0, 3),
					vrgVertexes.get(in).objectVertex,
					vrgVertexes.get(index).objectVertex);// StrUtils.GRAPH_PARAM_2);
			in = index;
		}
	}

	private void removeAllEdges(mxGraph graph) {
		graph.getModel().beginUpdate();
		try {
			for (VRGvertexes v : vrgVertexes) {
				// Object[] edges = graph.getEdges(v.objectVertex);
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
				nymberOfSpace++;
				updateEdges(graphComponent.getGraph());
			}
		}

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
			GraphFrame.this.dispose();
		}

		@Override
		public void focusGained(FocusEvent arg0) {

		}
	};

	public static void main(String[] args) {
		VRG.main(new String[] { "" });
	}
}
