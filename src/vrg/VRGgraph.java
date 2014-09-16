package vrg;

import java.util.ArrayList;

import ru.amse.smyshlyaev.grapheditor.graph.Edge;
import ru.amse.smyshlyaev.grapheditor.graph.Graph;
import ru.amse.smyshlyaev.grapheditor.graph.Vertex;

public class VRGgraph implements VRGframe.onSpacePressed {
	Graph graph;

	public static int length = 20;
	public static int baseLength = 15;
	public static int distance = VRGUtils.DISTANCE;
	public static int zoom = 1;
	public int height = 100;
	public int width = 100;
	public int numberOfSpace = 0;
	public int radius = 40;

	ArrayList<VRGvertexes> vrgVertexes;

	public void setListener(Object o) {
		VRGframe.onSpacePressed listener = this;
		((VRGframe) o).setListener(listener);
	}

	public void setUp() {
		vrgVertexes = new ArrayList<VRGvertexes>();
		graph = new Graph();
	}

	public static void setZoom(int z) {
		zoom = z;
		distance = z;
	}

	public VRGgraph() {
		setUp();
	}

	public VRGgraph(Object o) {
		setListener(o);
		graph = new Graph();
		vrgVertexes = new ArrayList<VRGvertexes>();
		constuctGraph(graph);
		// Graph.unmodifiableGraph(graph);
	}

	public void constuctGraph(Graph graph) {
		if (graph == null && !VRG.isValid()) {
			return;
		}
		vrgVertexes.clear();

		Vertex vertexA = new Vertex(VRG.coordinates.get(0).x * distance,
				VRG.coordinates.get(0).y * distance, VRGUtils.LABEL_BASE);
		graph.addVertex(vertexA);

		VRGvertexes a = new VRGvertexes();
		a.objectVertex = vertexA;
		a.demand = 0;
		a.price = 0;
		a.vertexCoords = new VRGvertexes.VertexCoords(VRG.coordinates.get(0));
		vrgVertexes.add(a);

		addCars(graph);

		for (int i = 1; i < VRG.coordinates.size(); i++) {
			Vertex vertex = new Vertex(VRG.coordinates.get(i).x * distance,
					VRG.coordinates.get(i).y * distance, VRGUtils.LABEL_VERTEX
							+ i);
			graph.addVertex(vertex);

			VRGvertexes vertexes = new VRGvertexes();
			vertexes.demand = VRG.demand.get(i);
			vertexes.price = VRG.price.get(i);
			vertexes.vertexCoords = new VRGvertexes.VertexCoords(
					VRG.coordinates.get(i));
			vertexes.objectVertex = vertex;

			vrgVertexes.add(vertexes);
		}
		updateEdges(graph);
	}

	public void constructVertexes() {
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

	private void addCars(Graph graph) {
		for (int i = 1; i < VRG.carsCoordinates.size(); i++) {
			Vertex vertex = new Vertex(radius, radius + i
					+ VRG.carsCoordinates.get(i).y * radius,
					VRGUtils.LABEL_CARS + i);
			graph.addVertex(vertex);
		}
	}

	private void updateEdges(Graph graph) {
		removeAllEdges(graph);

		Double distance;
		if (graph == null) {
			return;
		}
		if (VRG.routes == null || VRG.routes.size() == 0) {
			VRG.generateGraphRoutes();
		}

		if ((numberOfSpace + 1) > VRG.routes.size()) {// FIXME
			VRGframe.isNeedToUpdate = false;
			if (VRGUtils.showInputDialog(null, VRGUtils.MSG_ATTENTION,
					VRGUtils.MSG_ERR_ROUTES)) {
				VRG.generateGraphRoutes();
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

			Vertex vertex1 = null;
			Vertex vertex2 = null;
			if (vrgVertexes.get(in).objectVertex instanceof Vertex
					&& vrgVertexes.get(index).objectVertex instanceof Vertex) {
				vertex1 = Vertex.class.cast(vrgVertexes.get(in).objectVertex);
				vertex2 = Vertex.class
						.cast(vrgVertexes.get(index).objectVertex);
			} else {
				continue;
			}

			Edge edge = new Edge(vertex1, vertex2, VRGUtils.get(distance));
			graph.addEdge(edge);
			vrgVertexes.get(in).edges = edge;
			in = index;
		}
	}

	private void removeAllEdges(Graph graph) {
		for (VRGvertexes v : vrgVertexes) {
			if (v.edges instanceof Edge) {
				graph.removeEdge(Edge.class.cast(v.edges));
			}
		}
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

	@Override
	public void spacePressed() {
		numberOfSpace++;
		updateEdges(graph);
	}
}
