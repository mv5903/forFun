package forFun;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConvertBetweenNumberSystems {
	static Map<String, Integer> nums = new HashMap<String, Integer>();
	public static void main(String[] args) {
		initMap();
		Scanner kbd = new Scanner(System.in);
		System.out.println("Bases up to 36 are supported.");
		System.out.println("What base will you be converting from?");
		int startBase = kbd.nextInt();
		System.out.println("What base are you converting to?");
		int endBase = kbd.nextInt();
		System.out.println("What number are you converting?");
		String number = kbd.next();
		System.out.printf("%s converted from base %d to base %d is %s.", number, startBase, endBase, result(startBase, endBase, number.toUpperCase()));
		kbd.close();
	}
	
	public static String result(int startBase, int endBase, String number) {
		String basedTen = convertToBaseTen(startBase, number);
		return convertToFinal(endBase, basedTen);
	}
	
	public static String convertToBaseTen(int start, String number) {
		long total = 0;
		for (int i = 0; i < number.length(); i++) {
			int toMultiply = nums.get(Character.toString(number.charAt(i)));
			int subTotal = (int) (Math.pow(start, i)) * toMultiply;
			total+=subTotal;
		}
		return Long.toString(total);
	}
	
	public static String convertToFinal(int endBase, String basedTen) {
		if (endBase == 10) {
			return basedTen;
		}
		long wholeQuotient =  Long.parseLong(basedTen);
		String reversedAnswer = "";
		double actualQuotient;
		do {
			actualQuotient = (double) wholeQuotient / (double) endBase;
			String toString = Double.toString(actualQuotient);
			wholeQuotient = Long.parseLong(toString.substring(0, toString.indexOf(".")));
			System.out.println(wholeQuotient);
			String temp = toString.substring((toString.indexOf(".")));
			double decimalPortion = Double.parseDouble("0" + temp);
			int remainder = (int) (Math.round(decimalPortion * endBase));
			for (Map.Entry<String, Integer> entry: nums.entrySet()) {
				if (entry.getValue() == remainder) {
					reversedAnswer+=entry.getKey();
				}
			}
		} while (endBase < actualQuotient);
		StringBuilder correctAnswer = new StringBuilder(reversedAnswer);
		String finalAnswer = correctAnswer.reverse().toString().substring(1);
		return finalAnswer;
	}
	
	public static void initMap() {
		//Numbers
		for (int i = 0; i <= 9; i++) {
			nums.put(Integer.toString(i), i);
		}
		//Letters
		for (int i = 65; i <= 90; i++) {
			nums.put(Character.toString((char)(i)), (i-55));
		}
	}
}
