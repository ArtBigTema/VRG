package vrg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.PriorityQueue;

public class VRGwithTimeWindow {
	private static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	private static final PrintWriter out = new PrintWriter(System.out);

	public static void main(String[] args) {
		try {
			solve();
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void solve() throws Exception {
		int n = readInt();
		int k = readInt();

		PriorityQueue<Long> servers = new PriorityQueue<Long>();
		for (int i = 0; i < k; i++) {
			servers.add(0L);
		}
		for (int i = 0; i < n; i++) {
			int s = readInt();
			int m = readInt();
			long end = Math.max(servers.poll(), s) + m;
			servers.add(end);
			out.println(end);
		}
	}

	private static int readInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}
