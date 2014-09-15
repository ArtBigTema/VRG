package vrg;

import ru.amse.smyshlyaev.grapheditor.graph.Edge;
import ru.amse.smyshlyaev.grapheditor.graph.Graph;
import ru.amse.smyshlyaev.grapheditor.graph.Vertex;

public class VRGgraph {
	Graph graph;

	public static int length = 20;
	public static int baseLength = 15;
	public static int distance = VRGUtils.DISTANCE;
	public int height = 100;
	public int width = 100;

	public void setUp() {
		graph = new Graph();
	}

	public void testAddVertex() {
		Vertex vertex = new Vertex(2 * distance, 3 * distance);
		graph.addVertex(vertex);
	}

	public void testRemoveVertex() {
		Vertex vertex = new Vertex(0, 0);

		graph.addVertex(vertex);
		graph.removeVertex(vertex);
	}

	public void testAddEdge() {
		Vertex vertex1 = new Vertex(1 * distance, 5 * distance);
		Vertex vertex2 = new Vertex(4 * distance, 2 * distance);
		Edge edge = new Edge(vertex1, vertex2);

		graph.addVertex(vertex1);
		graph.addVertex(vertex2);
		graph.addEdge(edge);
	}

	public void testRemoveEdge() {
		Vertex vertex1 = new Vertex(0, 0);
		Vertex vertex2 = new Vertex(0, 0);
		Edge edge = new Edge(vertex1, vertex2);

		graph.addVertex(vertex1);
		graph.addVertex(vertex2);
		graph.addEdge(edge);
		graph.removeEdge(edge);

	}

	public void main(String[] args) {
		new VRGgraph();
	}

	public VRGgraph() {
		setUp();
		testAddVertex();
		testAddEdge();
	}

	public Graph getGraph() {
		return graph;
	}

	public void reSize(int w, int h) {
		this.width = w;
		this.height = h;

		rePaint();
	}

	private void rePaint() {

	}

}
