package forFun;


//import java.util.Arrays;

public class usingarrays {
	public static void main(String[] args) {

		
		
		int[] newJeet = {};
		int num;
		int length = 0;
		int[] jeet = {1, 4, 6, 4, 8, 1, 2, 1};
		for (int i = 0; i < jeet.length; i++) {
			num = jeet[i];
			for (int j = 0; j < newJeet.length; j++) {
				if (num != newJeet[j]) {
					newJeet[length] = num;
					length++;
				} 
			}
		}

		
		for (int i = 0;  i < newJeet.length; i++) {
			System.out.println(newJeet[i]);
		}
		
		
		
		
	}
}
