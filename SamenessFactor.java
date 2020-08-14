package forFun;

import java.util.Scanner;

public class SamenessFactor {
	public static String str, str1, str2;
	public static Scanner s = new Scanner(System.in);
	public static int asf = 0;
	public static void main(String[] args) {
		System.out.println("Enter two strings.");
		str1 = s.nextLine();
		str2 = s.nextLine();
		align();
		delete();
		
	}
	
	
	public static void align() {
		str = str1 + " " + str2;
	}
	public static void delete() {
		int i;
		if (str1.length() < str2.length()) { //finds shorter one
			i = str1.length();
		} else {
			i = str2.length();
		}
		while (i > -1) {
			if (str1.charAt(i) == str2.charAt(i)) {
				
			}
		}
		
	}
	public static void proceed() {
		
	}
}
