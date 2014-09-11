package vrg;

import java.util.ArrayList;

import javax.swing.JFrame;


public class VRG {
	public static final int LENGTH = 20;
	public static final int DISTANCE = 30;
	public static final int count = 8;
	public static final int[][] CORDINATES = { { 19, 45 }, { 18, 46 }, { 20, 47 },
			{ 22, 42 }, { 20, 41 }, { 14, 40 }, { 12, 44 }, { 13, 45 } };
	public static final int[] DEMAND = { 0, 1, 1, 1, 2, 2, 2, 1 };
	public static final int[] PRICE = { 0, 4, 4, 4, 4, 4, 4, 4 };
	public static final int[][] CARS = { { 11, 39 }, { 15, 39 }, { 20, 39 } };
	public static int countCars = 0;
	public static ArrayList<Integer> cars = new ArrayList<Integer>();

	public VRG() {

	}

	public static void main(String[] args) {
	VRGframe frame = new VRGframe();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
