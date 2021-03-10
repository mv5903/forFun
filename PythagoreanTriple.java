package forFun;

import java.util.ArrayList;

public class PythagoreanTriple {
	public static void main(String[] args) {
		final int UP_TO = 1000;
		long startTime = System.nanoTime();
		ArrayList<Integer> distances = new ArrayList<Integer>();
		for (int i = 1; i <= UP_TO; i++) {
			for (int j = 1; j <= UP_TO; j++) {
				Point p = new Point(i, j);
				Double distance = distanceFromOrigin(p);
				if (distance.doubleValue() > UP_TO) {
					break;
				}
				Integer intValue = distance.intValue();
				if (!distances.contains(intValue) && intValue.intValue() == distance.doubleValue()) {
					distances.add((int) distanceFromOrigin(p));
					System.out.println(i + ", " + j + ", " + (int)(distanceFromOrigin(p)));
				}
			}
		}
		System.out.println("Runtime: " + ((System.nanoTime() - startTime) / 1000000.0) + " ms.");
		System.out.println("Number of pythagorean triples: " + distances.size());
	}
	
	public static double distanceFromOrigin(Point p) {
		return Math.sqrt(Math.pow(p.getX(), 2) + Math.pow(p.getY(), 2));
	}
}
