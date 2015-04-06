package vrg;

import vrg.VRGUtils.Point;
import vrg.VRGwithTimeWindow.PointT;

public interface VRGgeneratorListener {
	public void generated(PointT t);

	public void generated(Point t);

	public void started();

	public void stoped(int count);
}
