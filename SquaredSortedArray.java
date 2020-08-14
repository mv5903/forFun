package forFun;

import java.util.Arrays;

public class SquaredSortedArray {

	public static void main(String[] args) {
		int[] n = new int[20];
		for (int i = 0; i < n.length; i++) {
			n[i] = (int) (Math.random()*20) -10;
		}
		System.out.println(Arrays.toString(n));
		System.out.println(Arrays.toString(squaredSort(n)));
	}

		public static int[] squaredSort(int[] n) {
			for (int i = 0; i < n.length; i++) {
				n[i] = (int) (Math.pow(n[i], 2));
			}
			System.out.println(Arrays.toString(n));
			int minI = 0;
			int min = n[0];
			int temp;
			
			for (int i = 0; i < n.length - 1; i++) {
				min = n[i];
				minI = i;
				
				for (int j = i+1; j < n.length; j++) {
					if (n[j] < min) {
						min = n[j];
						minI = j;
					}
				}
				
				temp = n[minI];
				n[minI] = n[i];
				n[i] = temp;
			}
			
			return n;
		}
}
