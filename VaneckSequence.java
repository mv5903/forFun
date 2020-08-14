package forFun;

import java.util.Scanner;

public class VaneckSequence {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("How long should the Vaneck Sequence continue? ");
		int indexes = s.nextInt();
		int current;
		int previous;
		int difference;
		String vaneck = "00";
		String check;
		for (int i = 1; i < indexes; i++) {
			current = Integer.parseInt(vaneck.substring(i)); // gets last number of string
			check = vaneck.substring(0, i); // numbers before current number
			if (check.contains(vaneck.substring(i))) { // number already exists
				previous = check.lastIndexOf(Integer.toString(current)); // last index of where it occurs
				difference = i - previous;
				vaneck += difference;
			} else {
				vaneck += "0";
			}
		}
		System.out.println(vaneck.substring(0, vaneck.length() - 1)); /*
																		 * prints up until last character since original
																		 * string starts with an additional number
																		 */
		s.close();

	}

}
