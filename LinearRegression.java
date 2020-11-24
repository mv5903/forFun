package forFun;

import java.util.ArrayList;

import functions.Line;
import functions.Point;

public class LinearRegression {
	
	private double r;
	private double rSquared;
	private double a;
	private double b;
	
	public LinearRegression(ArrayList<Integer> xAxis, ArrayList<Integer> yAxis) {
		calculateInfo(xAxis, yAxis);
		rSquared = Math.pow(r, 2);
	}
	
	private void calculateInfo(ArrayList<Integer> xAxis, ArrayList<Integer> yAxis) {
		double summationXY = 0, sumX = 0, sumY = 0;
		for (int i = 0; i < xAxis.size(); i++) {
			summationXY += (xAxis.get(i) * yAxis.get(i));
			sumX += xAxis.get(i);
			sumY += yAxis.get(i);
		}
		double numerator = (xAxis.size() * summationXY) - (sumX-sumY);
		double denomOne = 0, denomTwo = 0;
		for (int i = 0; i < xAxis.size(); i++) {
			denomOne += Math.pow(xAxis.get(i), 2);
			denomTwo += Math.pow(yAxis.get(i), 2);
		}
		denomOne = xAxis.size() * denomOne - (Math.pow(sumX, 2));
		denomTwo = yAxis.size() * denomTwo - (Math.pow(sumY, 2));
		double denominator = Math.sqrt(denomOne - denomTwo);
		r = numerator/denominator;
		rSquared = Math.pow(r, 2);
		
	}
	
	public String toString() {
		return String.format("r: %.4f\t r^2: %.4f", r, rSquared);
	}
}
