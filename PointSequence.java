package forFun;

import java.util.ArrayList;

public class PointSequence {
	private ArrayList<Point> points;
	private Point shortestPoint;
	
	public PointSequence() {
		points = new ArrayList<Point>();
	}
	
	public void addPoint(Point p) {
		points.add(p);
	}
	
	public void removePoint(Point p) {
		points.remove(points.indexOf(p));
	}
	
	public void removePoint(int index) {
		points.remove(index);
	}
	
	public double shortestDistance() {
		if (points.size() == 2) {
			return points.get(0).distanceBetween(points.get(1));
		}
		double min = points.get(0).distanceBetween(points.get(1));
		double tempDistance;
		shortestPoint = new Point(0, 1);
		for (int i = 0; i < points.size(); i++) {
			for (int j = 0; j < points.size(); j++) {
				tempDistance = points.get(i).distanceBetween(points.get(j));
				if (tempDistance < min && tempDistance != 0) {
					min = tempDistance;
					shortestPoint.setX(i);
					shortestPoint.setY(j);
				}
			}
		}
		return min;
	}
}
