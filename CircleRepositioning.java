package forFun;

import java.util.Arrays;
import java.util.Scanner;

public class CircleRepositioning {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int[] x = new int[4];
		int[] y = new int[4];
		System.out.println("Enter the origin point x, then y: ");
		int originx = s.nextInt();
		int originy = s.nextInt();
		for (int i = 0; i < 4; i++) {
			System.out.println("Enter an X-Coordinate: ");
			x[i] = s.nextInt();
			System.out.println("Enter a Y-Coordinate: ");
			y[i] = s.nextInt();
		}
		for (int i = 0; i < 4; i++) {
			x[i] = -1 * originx + x[i];
			y[i] = -1 * originy + y[i];
		}
		System.out.println("X: " + Arrays.toString(x));
		System.out.println("Y: " + Arrays.toString(y));
		s.close();
		
		

	}

}
