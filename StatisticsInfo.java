package forFun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * Finds all common statistics information given a user-entered sample. 
 * Commonly found as "1-Var Stats" on most graphing calculators.
 * @author matt
 *
 */
public class StatisticsInfo {
	
	static ArrayList<Double> data = new ArrayList<Double>(); //all data goes here
	static double[] result = new double[11]; //mean, min, q1, median, q3, max, iqr, range, sd, sum, sum squared in this order
	static ArrayList<ArrayList<Double>> outliers = new ArrayList<ArrayList<Double>>(); //outliers, extreme outliers
	static boolean isSample;
	static Scanner kbd = new Scanner(System.in);
	
	public static void main(String[] args) {
		//main method can get called again, so we must clear all lists
		if (data.size() != 0) {
			data.clear();
			outliers.get(1).clear();
			outliers.get(0).clear();
			outliers.clear();
		}
		outliers.add(new ArrayList<Double>());
		outliers.add(new ArrayList<Double>());
		inputNumbers();
	}
	/**
	 * Collects data and data type (sample/population) from user
	 */
	public static void inputNumbers() {
		String input = "";
		while (true) {
			System.out.println("Enter a data point. Type \"stop\" to stop.");
			input = kbd.nextLine();
			if (input.equalsIgnoreCase("stop")) {
				break;
			}
			data.add(Double.parseDouble(input));
		}
		
		System.out.println("Is this a sample or population? ");
		input = kbd.nextLine();
		System.out.println("\nOriginal data: " + data);
		isSample = input.equalsIgnoreCase("sample") ? true : false;
		Collections.sort(data);
		calculate();
		displayResults();
		kbd.close();
	}

	/**
	 * Displays the "1-Var Stats" with print format
	 */
	public static void displayResults() {
		//If the size of an outlier ArrayList is 0, then since there is no outliers:
		Object[] outs = new Object[2];
		for (int i = 0; i < outs.length; i++) {
			outs[i] = outliers.get(i).size() == 0 ? "None" : outliers.get(i);
		}
		
		String sdType = isSample ? "Sample" : "Population";
		System.out.println("Sorted data: " + data + "\n");
		System.out.printf("Sum of data: %.2f\t Sum of data squared: %.2f\t Mean: %.2f\t Min: %.2f\t Q1: %.2f\t Median: %.2f\t Q3: %.2f\t Max: %.2f\n"
				+ "IQR: %.2f\tRange: %.2f\t Standard Deviation (%s): %.2f\t Outliers: %s\t Extreme Outliers: %s\n"
				, result[9], result[10], result[0], result[1], result[2], result[3], result[4], result[5], result[6], result[7]
						, sdType, result[8], outs[0], outs[1]);
		System.out.println("\nRun again? Yes/No: ");
		String input = kbd.nextLine();
		/*
		 * I know you could make a constructor to call inputNumbers() again, but I wanted to try
		 * calling main again :)
		 */
		if (input.equalsIgnoreCase("yes")) main(null); else System.exit(0); 
		
	}

	/**
	 * Computes all data and places into {@link #result} according to the comment at the start.
	 */
	public static void calculate() {
		//mean
		double total = 0;
		for (Double d: data) {
			total+=d;
		}
		result[9] = total;
		result[10] = Math.pow(total, 2);
		result[0] = total/data.size();
		//min
		result[1] = data.get(0);
		//q1 & q3
		int medianIndex;
		if (data.size() % 2 == 1) {
			medianIndex = (data.size() - 1) / 2;
			if (medianIndex % 2 == 0) { // 1 in notes
				result[2] = (data.get(medianIndex / 2) + data.get(medianIndex / 2 - 1)) / 2.0;
				result[4] = (data.get(medianIndex + (medianIndex / 2)) + (data.get(medianIndex + (medianIndex / 2 + 1)))) / 2;
			} else { // 2 in notes
				result[2] = data.get((medianIndex - 1) / 2);
				result[4] = data.get((medianIndex) + (medianIndex - ((medianIndex - 1) / 2)));
			}
		} else {
			if (data.size() % 4 == 0) { // 3 in notes
				result[2] = (data.get(data.size() * 1 / 4) + data.get(data.size() * 1 / 4 - 1)) / 2;
				result[4] = (data.get(data.size() * 3 / 4) + data.get(data.size() * 3 / 4 - 1)) / 2;
			} else {
				result[2] = data.get(((data.size() / 2 - 1) / 2));
				result[4] = data.get((data.size() / 2) + ((data.size() / 2 - 1) - ((data.size() / 2 - 1) / 2)));
			}
		}
		//median
		if (data.size() % 2 == 0) {
			result[3] = ((data.get(data.size() / 2)) + (data.get(data.size() / 2 - 1))) / 2;
		} else {
			result[3] = data.get((data.size() - 1) / 2);
		}
		//max
		result[5] = data.get(data.size()-1);
		//iqr
		result[6] = result[4] - result[2];
		//range
		result[7] = result[5] - result[1];
		//standard dev
		double sum = 0;
		for (Double d: data) {
			sum+= Math.pow((d - result[0]), 2); 
		}
		if (isSample) {
			result[8] = Math.sqrt(sum / (data.size()  - 1));
		} else {
			result[8] = Math.sqrt(sum / data.size());
		}
		//outliers
		findOutliers();
	}
	/**
	 * Used to find the outliers and extreme outliers.
	 */
	public static void findOutliers() {
		double maxOutlier, minOutlier, maxOutlierExtreme, minOutlierExtreme;

		maxOutlierExtreme = (result[6] * 3) + result[4];
		minOutlierExtreme = result[2] - (result[6] * 3);
		maxOutlier = (result[6] * 1.5) + result[4];
		minOutlier = result[2] - (result[6] * 1.5);

		for (Double d: data) {
			if ((d >= maxOutlier && d < maxOutlierExtreme) || (d <= minOutlier && d > minOutlierExtreme)) {
				outliers.get(0).add(d);
			} else if (d >= maxOutlierExtreme || d <= minOutlierExtreme) {
				outliers.get(1).add(d);
			}
			
		}
		
	}
	
}
