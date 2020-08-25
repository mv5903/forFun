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
	public static double distance;
	public static Point shortestPoints;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Continue entering points. When finished, type \"stop\" at any time.");
		ArrayList<Point> p = new ArrayList<Point>();
		while (true) {
			System.out.println("X-Value: ");
			String tempX = s.nextLine();
			if (tempX.equals("stop") || tempX.equals("STOP")) {
				System.out.println("Calculating answer...");
				break;
			}
			System.out.println("Y-Value: ");
			String tempY = s.nextLine();
			if (tempY.equals("stop") || tempY.equals("STOP")) {
				System.out.println("Ignoring previous x input. Calculating answer...");
				break;
			}
			p.add(new Point(Double.parseDouble(tempX), (Double.parseDouble(tempY))));
		}
		calculate(p);
		if (p.size() > 2) {
			System.out.println("The shortest distance is between points " + p.get((int)(shortestPoints.getX())) 
			+ " and " + p.get((int)(shortestPoints.getY()))
			+ ". \nDistance: " + p.get((int)(shortestPoints.getX())).distanceBetween(p.get((int)(shortestPoints.getY()))));
		} else if (p.size() == 2) {
			System.out.println("Distance between these two points: " + p.get(0).distanceBetween(p.get(1)));
		} else {
			System.out.println("An unknown error occurred either because you didn't input enough points or you inputted a"
					+ " foreign character.");
		}
		s.close();
	}
	public static double calculate(ArrayList<Point> p) {
		if (p.size() == 2) {
			return p.get(0).distanceBetween(p.get(1));
		}
		double min = p.get(0).distanceBetween(p.get(1));
		double tempDistance;
		shortestPoints = new Point(0, 1);
		for (int i = 0; i < p.size(); i++) {
			for (int j = 0; j < p.size(); j++) {
				tempDistance = p.get(i).distanceBetween(p.get(j));
				if (tempDistance < min && tempDistance != 0) {
					min = tempDistance;
					shortestPoints.setX(i);
					shortestPoints.setY(j);
				}
			}
		}
		distance = min;
		return min;
	}
}
