package vrg;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import vrg.VRGUtils.Point;
import vrg.VRGwithTimeWindow.PointT;

public class VRGStaticData {
	private int DELAY = 10000, START = 10000;

	// private PointT currPointT;
	// private Point currPoint;
	private Timer timer;
	private Point max;
	private Point min;
	private long stop = 5;

	int count = 0;

	public VRGgeneratorListener listener;

	public void setListener(VRGwithTimeWindow lis) {
		this.listener = lis;
	}

	public VRGStaticData() {
		timer = new Timer();
	}

	public void startTimer() {
		if (timer == null) {
			return;
		}
		if (listener != null) {
			listener.started();
		}

		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (decTime()) {
					genertate();
				}
			}

			private boolean decTime() {
				stop--;
				if (stop < 0) {
					return stopTimer();
				}
				return true;
			}
		}, DELAY, START);
	}

	public boolean stopTimer() {
		timer.cancel();
		timer = null;
		if (listener != null) {
			listener.stoped(count);
		}
		generatePoint();
		return false;
	}

	private void genertate() {
		if (listener != null) {
			listener.generated(generatePointT());
			// listener.generated(generatePoint());
		}
	}

	private PointT generatePointT() {
		count++;
		PointT t = new PointT(random(min.x, max.x), random(min.y, max.y));
		t.setDelay(0);
		t.setEndPlace(random(min.x, max.x), random(min.y, max.y));
		t.setTimeWindow(random(min.x, max.x), random(min.x, max.x));
		return t;
	}

	private Point generatePoint() {
		count++;
		return new Point(random(min.x, max.x), random(min.y, max.y));
	}

	public void setMaxPoint(int x, int y) {
		max = new Point(x, y);
	}

	public void setMinPoint(int x, int y) {
		min = new Point(x, y);
	}

	public static int random(int start, int end) {
		Random rand = new Random();
		if (start >= end)
			return (int) (start + rand.nextInt(start - end + 1));// FIXME
		else
			return (int) (start + rand.nextInt(end - start + 1));
	}
}
