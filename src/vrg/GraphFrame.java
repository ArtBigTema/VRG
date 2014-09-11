package vrg;

import javax.swing.JFrame;

import com.mxgraph.canvas.mxICanvas;
import com.mxgraph.canvas.mxImageCanvas;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;

@SuppressWarnings("serial")
public class GraphFrame extends JFrame{
	public  mxGraph graph;
	public static final int length = 20;
	public static final int distance = 30;
	public static int count = 8;
	public static int[][] coordinates = { { 19, 45 }, { 18, 46 }, { 20, 47 },
			{ 22, 42 }, { 20, 41 }, { 14, 40 }, { 12, 44 }, { 13, 45 } };
	public int[] demand = { 0, 1, 1, 1, 2, 2, 2, 1 };
	public int[] price = { 0, 4, 4, 4, 4, 4, 4, 4 };
	public static int[][] cars = { { 11, 39 }, { 15, 39 }, { 20, 39 } };

	public GraphFrame() {
		// GraphFrame frame = new GraphFrame();
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
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
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
			Object a = graph.insertVertex(parent, null, "A", coordinates[0][0]
					* distance, coordinates[0][1] * distance, length, length);// x,y,width,height
			{
				graph.insertVertex(parent, null, "Cars, 3", cars[0][0]
						* distance, cars[0][1] * distance, 3 * length, length);// x,y,width,height
				graph.insertVertex(parent, null, "Cars, 3", cars[1][0]
						* distance, cars[1][1] * distance, 3 * length, length);// x,y,width,height
				graph.insertVertex(parent, null, "Cars, 5", cars[2][0]
						* distance, cars[2][1] * distance, 3 * length, length);// x,y,width,height
			}

			/*
			 * for(int i = 1; i<count;i++){ graph.insertVertex(parent, null,
			 * "X"+i, coordinates[i][0] * length, coordinates[i][1] * length,
			 * d*length, d*length);// x,y,width,height }
			 */

			if (a instanceof mxCell) {
				// ((mxCell)a).getValue()
			}

			Object v1 = graph.insertVertex(parent, null, "X1", 18 * distance,
					46 * distance, 1 * length, 1 * length);
			Object v2 = graph.insertVertex(parent, null, "X2", 20 * distance,
					47 * distance, 1 * length, 1 * length);
			Object v3 = graph.insertVertex(parent, null, "X3", 22 * distance,
					42 * distance, 1 * length, 1 * length);
			Object v4 = graph.insertVertex(parent, null, "X4", 20 * distance,
					41 * distance, 2 * length, 2 * length);
			Object v5 = graph.insertVertex(parent, null, "X5", 14 * distance,
					40 * distance, 2 * length, 2 * length);
			Object v6 = graph.insertVertex(parent, null, "X6", 12 * distance,
					44 * distance, 2 * length, 2 * length);
			Object v7 = graph.insertVertex(parent, null, "X7", 13 * distance,
					45 * distance, 1 * length, 1 * length);
			{
				graph.insertEdge(parent, null, "", a, v1);
				graph.insertEdge(parent, null, "", v1, v2);
				graph.insertEdge(parent, null, "", v2, a);
			}
			{
				graph.insertEdge(parent, null, "", a, v3);
				graph.insertEdge(parent, null, "", v3, v4);
				graph.insertEdge(parent, null, "", v4, a);
			}
			{
				graph.insertEdge(parent, null, "", a, v5);
				graph.insertEdge(parent, null, "", v5, v6);
				graph.insertEdge(parent, null, "", v6, v7);
				graph.insertEdge(parent, null, "", v7, a);
			}

			graph.insertVertex(parent, null, "", 50 * distance, 50 * distance,
					0 * length, 0 * length);
		} finally {
			graph.getModel().endUpdate();
		}

	}
	public static void main(String[] args) {
		
	}
}
