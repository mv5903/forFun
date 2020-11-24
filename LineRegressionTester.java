package forFun;

import java.util.ArrayList;

public class LineRegressionTester {

	public static void main(String[] args) {
		ArrayList<Integer> x = new ArrayList<Integer>();
		ArrayList<Integer> y = new ArrayList<Integer>();
		x.add(5);
		x.add(7);
		x.add(8);
		y.add(12);
		y.add(12);
		y.add(10);

		LinearRegression l = new LinearRegression(x, y);
		System.out.println(l);
	}

}
