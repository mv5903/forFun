package forFun;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class BoxPlot {

	public static double min, max, q1, q3, iqr, median, range, mean, sd;
	public static ArrayList<Double> data = new ArrayList<Double>();

	public static void main(String[] args) {
		Scanner kbd = new Scanner(System.in);
		System.out.println("Enter data, seperated by enter presses. All data must be > 0. When done, type \"stop\".");
		String temp = kbd.nextLine();
		while (!temp.equalsIgnoreCase("stop")) {
			data.add(Double.parseDouble(temp));
			temp = kbd.nextLine();
		}
		showWaitTimer();
		System.out.println("You entered: " + data + "\n"
				+ "-------------------------------------------------------------------------------------------------------------------");
		data = sort(data);
		findAllImportantData();
		showPlotAndData();
		kbd.close();
	}

	public static void showPlotAndData() {

		DecimalFormat d = new DecimalFormat("#.###");
		int countBy = (int) (max / 10.0);
		int temp = (int) (min);
		ArrayList<Integer> ranges = new ArrayList<Integer>();
		for (int i = 0; i < 16; i++) {
			System.out.print(temp + "-" + (temp + countBy) + "\t");
			ranges.add(temp);
			temp += countBy;
		}

		System.out.println("\n");
		// Start of Line 1
		System.out.print("|");
		for (int i = 0; i < ranges.size() - 1; i++) {
			if (max >= ranges.get(i) && max < ranges.get(i + 1)) {
				System.out.print("|");
			} else {
				System.out.print("\t");
			}
		}
		System.out.println();
		// Start of Line 2
		System.out.print("|");
		for (int i = 0; i < ranges.size() - 1; i++) {
			if (q1 >= ranges.get(i) && q1 < ranges.get(i + 1)) {
				System.out.print("|--\t");
			} else if (median >= ranges.get(i) && median < ranges.get(i + 1)) {
				System.out.print("-|-\t");
			} else if (q3 >= ranges.get(i) && q3 < ranges.get(i + 1)) {
				System.out.print("--|\t");
			} else if (max >= ranges.get(i) && max < ranges.get(i + 1)) {
				System.out.print("|\t");
			}
			else {
				System.out.print("\t");
			}
		}
		System.out.println();
		//Start of Line 3
		System.out.print("|");
		for (int i = 0; i < ranges.size() - 1; i++) {
			if (q1 >= ranges.get(i) && q1 < ranges.get(i + 1)) {
				System.out.print("|\t");
			} else if (median >= ranges.get(i) && median < ranges.get(i + 1)) {
				System.out.print(" |\t");
			} else if (q3 >= ranges.get(i) && q3 < ranges.get(i + 1)) {
				System.out.print("  |\t");
			} else if (max >= ranges.get(i) && max < ranges.get(i + 1)) {
				System.out.print("|\t");
			}
			else {
				System.out.print("\t");
			}
		}
		System.out.println();
		//Start of Line 4
		System.out.print("|");
		for (int i = 0; i < ranges.size() - 1; i++) {
			if (q1 >= ranges.get(i) && q1 < ranges.get(i + 1)) {
				System.out.print("|--\t");
			} else if (median >= ranges.get(i) && median < ranges.get(i + 1)) {
				System.out.print("-|-\t");
			} else if (q3 >= ranges.get(i) && q3 < ranges.get(i + 1)) {
				System.out.print("--|\t");
			} else if (max >= ranges.get(i) && max < ranges.get(i + 1)) {
				System.out.print("|\t");
			}
			else {
				System.out.print("\t");
			}
		}
		System.out.println();
		//Start of Line 5
		System.out.print("|");
		for (int i = 0; i < ranges.size() - 1; i++) {
			if (max >= ranges.get(i) && max < ranges.get(i + 1)) {
				System.out.print("|");
			} else {
				System.out.print("\t");
			}
		}
		System.out.println();
		//End of diagram
		System.out.println(
				"\n-------------------------------------------------------------------------------------------------------------------");
		System.out.println("Median: " + d.format(median) + "\tRange: " + d.format(range) + "\tMean: " + d.format(mean)
				+ "\tMinimum: " + min + "\tMaximum: " + max + "\tQ1: " + d.format(q1) + "\tQ3: " + d.format(q3)
				+ "\tIQR: " + d.format(iqr) + "\tStandard Deviation: " + d.format(sd));
	}

	public static ArrayList<Double> sort(ArrayList<Double> data) {
		double[] nums = new double[data.size()];
		for (int i = 0; i < data.size(); i++) {
			nums[i] = data.get(i);
		}
		int mI = 0;
		double min = nums[0];
		double temp;
		for (int i = 0; i < nums.length - 1; i++) {
			min = nums[i];
			mI = i;
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[j] < min) {
					min = nums[j];
					mI = j;
				}
			}
			temp = nums[i];
			nums[i] = min;
			nums[mI] = temp;
		}
		ArrayList<Double> toReturn = new ArrayList<Double>();
		for (int i = 0; i < nums.length; i++) {
			toReturn.add(nums[i]);
		}
		return toReturn;
	}

	public static void findAllImportantData() {
		findMean();
		findMedian();
		findMinAndMax();
		findQ1AndQ3();
		findIQR();
		findRange();
		findStandardDeviation();
	}

	public static void findMean() {
		double sum = 0;
		for (double d : data) {
			sum += d;
		}
		mean = sum / data.size();
	}

	public static void findStandardDeviation() {
		double numerator = 0;
		for (Double d : data) {
			numerator += Math.pow(d - mean, 2);
		}
		sd = Math.sqrt(numerator / (data.size() - 1));
	}

	public static void findRange() {
		range = data.get(data.size() - 1) - data.get(0);
	}

	public static void findMedian() {
		if (data.size() % 2 == 0) {
			median = ((data.get(data.size() / 2)) + (data.get(data.size() / 2 - 1))) / 2;
		} else {
			median = data.get((data.size() - 1) / 2);
		}
	}

	public static void findMinAndMax() {
		double minimum = data.get(0), maximum = data.get(0);
		for (double d : data) {
			if (d < minimum) {
				minimum = d;
			}
			if (d > maximum) {
				maximum = d;
			}
		}
		min = minimum;
		max = maximum;
	}

	public static void findQ1AndQ3() {
		int medianIndex;
		if (data.size() % 2 == 1) {
			medianIndex = (data.size() - 1) / 2;
			if (medianIndex % 2 == 0) { // 1 in notes
				q1 = (data.get(medianIndex / 2) + data.get(medianIndex / 2 - 1)) / 2.0;
				q3 = (data.get(medianIndex + (medianIndex / 2)) + (data.get(medianIndex + (medianIndex / 2 + 1)))) / 2;
			} else { // 2 in notes
				q1 = data.get((medianIndex - 1) / 2);
				q3 = data.get((medianIndex) + (medianIndex - ((medianIndex - 1) / 2)));
			}
		} else {
			if (data.size() % 4 == 0) { // 3 in notes
				q1 = (data.get(data.size() * 1 / 4) + data.get(data.size() * 1 / 4 - 1)) / 2;
				q3 = (data.get(data.size() * 3 / 4) + data.get(data.size() * 3 / 4 - 1)) / 2;
			} else {
				q1 = data.get(((data.size() / 2 - 1) / 2));
				q3 = data.get((data.size() / 2) + ((data.size() / 2 - 1) - ((data.size() / 2 - 1) / 2)));
			}
		}
	}

	public static void findIQR() {
		iqr = q3 - q1;
	}

	public static void showWaitTimer() {
		int temp = 0;
		String[] symbols = { "(-)", "(\\)", "(|)", "(/)", "(-)" };
		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < 50; i++) {
				System.out.println();
			}
			System.out.println(symbols[temp]);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			temp++;
		}
		for (int i = 0; i < 10; i++) {
			System.out.println();
		}
	}
}
