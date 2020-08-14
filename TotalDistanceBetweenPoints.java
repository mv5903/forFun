package forFun;

import java.util.ArrayList;
import java.util.Scanner;

public class TotalDistanceBetweenPoints {
	static Scanner kbd = new Scanner(System.in);

	public static void main(String[] args) {
		ArrayList<Point> points = new ArrayList<Point>();
		String currentX = "", currentY = "";
		System.out.println("Continue entering points. When finished, type \"stop\".");
		while (true) {
			System.out.println("Enter an X-Value: ");
			currentX = kbd.nextLine();
			if (currentX.equalsIgnoreCase("stop")) {
				break;
			}
			System.out.println("Enter a Y-Value:");
			currentY = kbd.nextLine();
			if (currentY.equalsIgnoreCase("stop")) {
				break;
			}
			points.add(new Point(Integer.parseInt(currentX), Integer.parseInt(currentY)));
		}
		if (confirmPoints(points).equalsIgnoreCase("Y")) {
			System.out.println("Should the distance be [1] calculated consecutively (1-2, 2-3) or [2] between every possible combination (1-2, 1-3, etc)?");
			if (kbd.nextInt() == 1) {
				System.out.println(consec(points));
			} else {
				System.out.println(every(points));
			}
		}

	}
	
	public static String confirmPoints(ArrayList<Point> p) {
		String output = "You entered ";
		for (int i = 0; i < p.size(); i++) {
			output+="(" + p.get(i).getX() + ", " + p.get(i).getY() + ")";
			if (i != p.size() - 1) {
				output+="; ";
			}
		}
		output+="\nIs this correct? (Y/N)";
		System.out.println(output);
		return kbd.nextLine();
	}
	
	public static String consec(ArrayList<Point> p) {
		double distance = 0;
		for (int i = 0; i < p.size()-1; i++) {
			distance+=p.get(i).distanceBetween(p.get(i+1));
		}
		
		return "Distance (consec): " + distance;
	}
	
	public static String every(ArrayList<Point> p) {
		double distance = 0;
		for (int i = 0; i < p.size(); i++) {
			for (int j = 0; j < p.size(); j++) {
				distance+=p.get(i).distanceBetween(p.get(j));
			}
		}
		
		return "Distance (every): " + distance;
	}

}
