package vrg;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.mxgraph.canvas.mxICanvas;
import com.mxgraph.canvas.mxImageCanvas;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.view.mxInteractiveCanvas;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;

@SuppressWarnings("serial")
public class GraphFrame extends JFrame {
	public mxGraph graph;
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

	public GraphFrame() {
		// GraphFrame frame = new GraphFrame();
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
		mxGraphComponent graphComponent = new mxGraphComponent(graph) {

			@Override
			public void paint(Graphics paramGraphics) {
				super.paint(paramGraphics);

				paintCarcass(paramGraphics.create());

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
					paramGraphics.drawString(String.valueOf(dy / distance),
							offset + 1, dy);

					paramGraphics.drawLine(dx, 0, dx, offset);
					paramGraphics.drawString(String.valueOf(dx / distance), dx,
							offset * 3);
				}

				paramGraphics.drawLine(0, 1,
						paramGraphics.getClipBounds().width, 1);

				paramGraphics.drawLine(1, 0, 1,
						paramGraphics.getClipBounds().height);
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
	}

	public void createGraph() {
		graph = new mxGraph() {
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
								VRG.coordinates.get(0).x * distance,
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
			Double distance;
			{
				distance = VRGvertexes.getDistance(
						vrgVertexes.get(0).vertexCoords,
						vrgVertexes.get(1).vertexCoords);

				graph.insertEdge(parent, null,
						distance.toString().substring(0, 3),
						vrgVertexes.get(0).objectVertex,
						vrgVertexes.get(1).objectVertex, StrUtils.GRAPH_PARAM_2);

				distance = VRGvertexes.getDistance(
						vrgVertexes.get(1).vertexCoords,
						vrgVertexes.get(2).vertexCoords);
				graph.insertEdge(parent, null,
						distance.toString().substring(0, 3),
						vrgVertexes.get(1).objectVertex,
						vrgVertexes.get(2).objectVertex, StrUtils.GRAPH_PARAM_2);

				distance = VRGvertexes.getDistance(
						vrgVertexes.get(2).vertexCoords,
						vrgVertexes.get(3).vertexCoords);
				graph.insertEdge(parent, null,
						distance.toString().substring(0, 3),
						vrgVertexes.get(2).objectVertex,
						vrgVertexes.get(3).objectVertex, StrUtils.GRAPH_PARAM_2);

				distance = VRGvertexes.getDistance(
						vrgVertexes.get(3).vertexCoords,
						vrgVertexes.get(0).vertexCoords);
				graph.insertEdge(parent, null,
						distance.toString().substring(0, 3),
						vrgVertexes.get(3).objectVertex,
						vrgVertexes.get(0).objectVertex, StrUtils.GRAPH_PARAM_2);
			}
		} finally {
			graph.getModel().endUpdate();

		}
	}

	/*
	 * private void createCarcas(mxGraph graph) { mxRectangle rect =
	 * graph.getGraphBounds();
	 * 
	 * double numX = rect.getWidth() / 10; double numY = rect.getHeight() / 10;
	 * graph.insertVertex(graph.getDefaultParent(), null, ".", 1, 100, length,
	 * length); graph.insertVertex(graph.getDefaultParent(), null, ".", 100, 1,
	 * length, length); int offset = 5; int dx = 0, dy = 0; for (int i = 0; i <
	 * 10; i++) { dx += numX; dy += numY;
	 * graph.insertVertex(graph.getDefaultParent(), null, "-", 1, dy, length,
	 * length); graph.insertVertex(graph.getDefaultParent(), null, "|", dx, 1,
	 * length, length);
	 * 
	 * }
	 * 
	 * }
	 */

	private void addCars(mxGraph graph) {
		for (int i = 0; i < VRG.carsCoordinates.size(); i++) {
			graph.insertVertex(graph.getDefaultParent(), null,
					StrUtils.LABEL_CARS + i, VRG.carsCoordinates.get(i).x
							* distance,
					VRG.carsCoordinates.get(i).y * distance, 3 * length, length);// x,y,width,height
		}
	}

	public static void main(String[] args) {

	}
}
