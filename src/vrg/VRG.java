package vrg;

import javax.swing.JFrame;

import com.mxgraph.view.mxGraph;

@SuppressWarnings("serial")
public class VRG {
	public static VRGframe frame;
	public static mxGraph graph;
	public static final int length = 20;
	public static final int distance = 30;
	public static int count = 8;
	public static int[][] coordinates = { { 19, 45 }, { 18, 46 }, { 20, 47 },
			{ 22, 42 }, { 20, 41 }, { 14, 40 }, { 12, 44 }, { 13, 45 } };
	public int[] demand = { 0, 1, 1, 1, 2, 2, 2, 1 };
	public int[] price = { 0, 4, 4, 4, 4, 4, 4, 4 };
	public static int[][] cars = { { 11, 39 }, { 15, 39 }, { 20, 39 } };

	public VRG() {

	}

	public static void main(String[] args) {
		frame = new VRGframe();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
