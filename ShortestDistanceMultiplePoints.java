package forFun;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Finds the shortest distance between any number of points. It will
 * give the two points with the shortest distance.
 * @author matt
 *
 */
public class ShortestDistanceMultiplePoints {
	public static void main(String[] args) {
		Scanner kbd = new Scanner(System.in);
		ArrayList<Point> points = new ArrayList<Point>();
		System.out.println("Shortest distance between infinite points. Use stop to stop.");
		int counter = 1;
		while (true) {
			System.out.print("Enter X" + counter + ": ");
			String xStr = kbd.next();
			if (xStr.equalsIgnoreCase("stop")) break;
			double x = Double.parseDouble(xStr);
			System.out.print("Enter Y" + counter + ": ");
			String yStr = kbd.next();
			if (yStr.equalsIgnoreCase("stop")) break;
			double y = Double.parseDouble(yStr);
			points.add(new Point(x, y, counter));
			counter++;
		}
		if (points.size() < 2) {
			System.err.println("Not enough points! Exiting.");
			System.exit(0);
		}
		double shortestDistance = points.get(0).distanceBetween(points.get(1));
		Point a = points.get(0), b = points.get(1);
		for (int i = 0; i < points.size(); i++) {
			for (int j = 0; j < points.size(); j++) {
				if (i == j) continue;
				double tempDistance = points.get(i).distanceBetween(points.get(j));
				if (tempDistance < shortestDistance) {
					shortestDistance = tempDistance;
					a = points.get(i);
					b = points.get(j);
				}
			}
		}
		System.out.printf("\nThe shortest distance between two points is %.3f, and those two points are (%d)%s and (%d)%s.", shortestDistance, a.getNumber(), a, b.getNumber(), b);
		kbd.close();
	}
}
