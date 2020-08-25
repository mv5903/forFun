package forFun;

import java.util.Scanner;

public class ExponentialEquationFinderTwoPoints {
	static Point a, b;
	public static void main(String[] args) {
		Scanner kbd = new Scanner(System.in);
		System.out.println("Enter X1: ");
		double x = kbd.nextDouble();
		System.out.println("Enter Y1: ");
		double y = kbd.nextDouble();
		a = new Point(x, y);
		System.out.println("Enter X2: ");
		x = kbd.nextDouble();
		System.out.println("Enter Y2: ");
		y = kbd.nextDouble();
		b = new Point(x, y);
		solve();
	}
	
	public static void solve() {
		//Keep point a as greater y-value no matter what is entered.
		if (a.getY() < b.getY()) { //swap points
			Point temp = a;
			a = b;
			b = temp;
		}
		
		double y = a.getY() / b.getY();
		double bExp = a.getX() - b.getX();
		double equationB = Math.pow(y, 1/bExp);
		double equationA = a.getY() / (Math.pow(equationB, a.getX()));
		System.out.printf("Equation: y = %.2f(%.2f)^x", equationA, equationB);
	}

}
