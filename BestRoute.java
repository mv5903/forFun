package forFun;

import java.util.ArrayList;
import java.util.Scanner;

public class BestRoute {
	static Scanner kbd = new Scanner(System.in);
	static ArrayList<Point> points = new ArrayList<Point>();
	public static void main(String[] args) {
		askForPoints();
	}
	
	public static void askForPoints() {
		System.out.println("Enter a point. Type stop when done.");
		String x, y;
		while (true) {
			x = kbd.nextLine();
			if (x.equalsIgnoreCase("stop")) {
				break;
			} 
			y = kbd.nextLine();
			if (y.equalsIgnoreCase("stop")) {
				break;
			}
			points.add(new Point(Double.parseDouble(x), Double.parseDouble(y)));
			System.out.println("Enter another point:");
		}
		System.out.println("Calculating best route...");
		System.out.println("The best route is " + bestRoute());
	}
	
	public static String bestRoute() {
		ArrayList<Point> indeces = new ArrayList<Point>();
		double min = points.get(0).distanceBetween(points.get(1)), start, end;
		for (int i = 0; i < points.size() - 1; i++) {
			for (int j = i + 1; j < points.size(); j++) {
				if (points.get(i).distanceBetween(points.get(j)) < min) {
					min = points.get(i).distanceBetween(points.get(j));
				}
			}
			
		}
		
		
		return "";
	}
}
