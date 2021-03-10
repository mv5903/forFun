package forFun;

import java.math.BigInteger;

public class BigNumberTest {
	public static void main(String[] args) {
		
		long startTime = System.nanoTime();
		
		byte[] num = {9, 9, 9, 9, 9, 9, 9, 9, 9};
		
		BigInteger a = new BigInteger(num);
		BigInteger b = new BigInteger(num);
		
		
		System.out.println(a.pow(999999));
		
		System.out.println("Took: " + time(System.nanoTime() - startTime));
	}
	
	public static String time(long ns) {
		long ms = ns / 1000000;
		return Long.toString(ms) + " ms.";
	}
 }
