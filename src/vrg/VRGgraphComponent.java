package vrg;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ru.amse.smyshlyaev.grapheditor.graph.Edge;
import ru.amse.smyshlyaev.grapheditor.graph.Graph;
import ru.amse.smyshlyaev.grapheditor.graph.Vertex;
import ru.amse.smyshlyaev.grapheditor.ui.JGraphComponent;
import vrg.VRGUtils.Point;
import vrg.VRGwithTimeWindow.Pair;

@SuppressWarnings("serial")
public class VRGgraphComponent extends JGraphComponent implements VRGframe.onSpacePressed {
	public Graph graph;
	public boolean isCompleted = false;
	public boolean withTimeWindow = false;

	public VRGgraphComponent(Graph graph, int width, int height) {
		super(graph, width, height);
		setUp();
	}

	public static int length = 20;
	public static int baseLength = 15;
	public static int distance = VRGUtils.DISTANCE;
	public static int zoom = 1;
	public static int height = 100;
	public static int width = 100;
	public int numberOfSpace = 0;
	public int radius = 40;
	public int translateX = 0;
	public int translateY = 0;

	public static ArrayList<Point> coordinates = new ArrayList<Point>();
	public static ArrayList<ArrayList<Integer>> routes = new ArrayList<ArrayList<Integer>>();

	ArrayList<VRGvertexes> vrgVertexes;

	public void setListener(VRGframe o) {
		VRGframe.onSpacePressed listener = this;
		o.setListener(listener);
	}

	public void setUp() {
		vrgVertexes = new ArrayList<VRGvertexes>();
		graph = new Graph();
	}

	public static void setZoom(int z) {
		zoom = z;
		distance = z;
	}

	public void init(VRGframe o, boolean withTimeWindow) {
		setListener(o);
		setData(withTimeWindow);
		graph = new Graph();
		vrgVertexes = new ArrayList<VRGvertexes>();
		constuctGraph(graph);
		this.setGraph(graph);
		isCompleted = true;
		// Graph.unmodifiableGraph(graph);
	}

	private void setData(boolean withTimeWindow) {
		if (withTimeWindow) {
			coordinates = new ArrayList<Point>(VRGwithTimeWindow.getCoords());
			routes = new ArrayList<ArrayList<Integer>>(VRGwithTimeWindow.getRoutesAll());
		} else {
			coordinates = new ArrayList<Point>(VRG.coordinates);
			routes = new ArrayList<ArrayList<Integer>>(VRG.routes);
		}
		this.withTimeWindow = withTimeWindow;
	}

	public void constuctGraph(Graph graph) {
		if (graph == null && !VRG.isValid()) {
			return;
		}
		vrgVertexes.clear();
		setZoomIfNeed(withTimeWindow);// FIXME

		Vertex vertexA = new Vertex(translateX + coordinates.get(0).x * distance, translateY + coordinates.get(0).y * distance,
				VRGUtils.LABEL_BASE);
		graph.addVertex(vertexA);

		VRGvertexes a = new VRGvertexes();
		a.objectVertex = vertexA;
		a.demand = 0;
		a.price = 0;
		a.vertexCoords = new VRGvertexes.VertexCoords(coordinates.get(0));
		vrgVertexes.add(a);

		if (!withTimeWindow) {
			addCars(graph);// XXX
		}

		for (int i = 1; i < coordinates.size(); i++) {
			Vertex vertex = new Vertex(translateX + coordinates.get(i).x * distance, translateY + coordinates.get(i).y
					* distance, VRGUtils.LABEL_VERTEX + i);
			graph.addVertex(vertex);

			VRGvertexes vertexes = new VRGvertexes();
			if (!withTimeWindow) {
				vertexes.demand = VRG.demand.get(i);
				vertexes.price = VRG.price.get(i);
			}
			vertexes.vertexCoords = new VRGvertexes.VertexCoords(coordinates.get(i));
			vertexes.objectVertex = vertex;

			vrgVertexes.add(vertexes);
		}
		updateEdges(graph);
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

		translateX = 20;
		translateY = 20;
	}

	public void constructVertexes() {
		VRGvertexes a = new VRGvertexes();
		a.demand = 0;
		a.price = 0;
		a.vertexCoords = new VRGvertexes.VertexCoords(coordinates.get(0));
		vrgVertexes.add(a);

		for (int i = 1; i < coordinates.size(); i++) {
			VRGvertexes vertex = new VRGvertexes();
			if (!withTimeWindow) {
				vertex.demand = VRG.demand.get(i);
				vertex.price = VRG.price.get(i);
			}
			vertex.vertexCoords = new VRGvertexes.VertexCoords(coordinates.get(i));
			vrgVertexes.add(vertex);
		}
	}

	private void addCars(Graph graph) {
		for (int i = 1; i < VRG.carsCoordinates.size(); i++) {
			Vertex vertex = new Vertex(50, VRG.carsCoordinates.get(i).y / 2, VRGUtils.LABEL_CARS + i);
			graph.addVertex(vertex);
		}
	}

	private void updateEdges(Graph graph) {
		removeAllEdges(graph);

		Double distance = 0D;
		if (graph == null) {
			return;
		}

		if ((numberOfSpace + 1) > routes.size()) {
			if (VRGUtils.showInputDialog(this, VRGUtils.MSG_ATTENTION, VRGUtils.MSG_ERR_ROUTES)) {
				VRG.constructSolution();// FIXME
			}
			VRGframe.isNeedToUpdate = true;
			numberOfSpace = 0;
		}

		for (ArrayList<Integer> tmp : routes) {
			int in = 0;
			for (int index : tmp) {
				distance = VRGvertexes.getDistance(vrgVertexes.get(in).vertexCoords, vrgVertexes.get(index).vertexCoords);
				Vertex vertex1 = null;
				Vertex vertex2 = null;
				if (vrgVertexes.get(in).objectVertex instanceof Vertex && vrgVertexes.get(index).objectVertex instanceof Vertex) {
					vertex1 = Vertex.class.cast(vrgVertexes.get(in).objectVertex);
					vertex2 = Vertex.class.cast(vrgVertexes.get(index).objectVertex);
				} else {
					continue;
				}

				Edge edge = new Edge(vertex1, vertex2, VRGUtils.get(distance));
				graph.addEdge(edge);
				vrgVertexes.get(in).edges = edge;
				in = index;
			}
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

	public static void reSize(int w, int h) {
		width = w;
		height = h;
	}

	@Override
	public void paint(Graphics paramGraphics) {
		super.paint(paramGraphics);
		VRGUtils.paintCarcass(paramGraphics.create());
		if (isCompleted) {
			paramGraphics.drawOval(translateX + coordinates.get(0).x * distance - VRGUtils.radius,
					translateY + coordinates.get(0).y * distance - VRGUtils.radius, VRGUtils.radius, VRGUtils.radius);
			// x-radius, y-radius, radius*2, radius*2
		}
	}

	@Override
	public void spacePressed() {
		VRG.generateEdges();// FIXME
		numberOfSpace++;
		updateEdges(graph);
	}

}
