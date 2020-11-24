package forFun;

import java.util.Scanner;

public class PlugInForX {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter an x value: ");
		double x = s.nextDouble();
		double equation = (Math.pow(x, 2) + (6 * x) + 2)/(x + 1);
		System.out.println("Y: " + equation);
	}
}
