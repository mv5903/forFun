package forFun;

import java.util.Arrays;

public class ExcelImage {
	public static void main(String[] args) {
		System.out.println(isPowerOfThree(243));
	}
	
	public static boolean isPowerOfThree(int n) {
		System.out.println((Math.log10(n) / Math.log10(3)));
		return (int) (Math.log10(n) / Math.log10(3)) == (Math.log10(n) / Math.log10(3));
	}
}
