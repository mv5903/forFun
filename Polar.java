package forFun;

import java.text.DecimalFormat;

public class Polar {

	public static void main(String[] args) {
		
		DecimalFormat d = new DecimalFormat("0.00");
		
		int degrees = 0;
		double radians = Math.toRadians(degrees);
		double equation = 2 + Math.sin(radians);
		
		for (int i = 0; i < 17; i++) {
			System.out.print(degrees + " degrees: \t" + d.format(equation));
			
			if (degrees == 0 || (degrees % 90 == 0)){ //0, 90, 180, 270, 360
				degrees+=30;
			} else if (degrees / 30 == 1 || degrees / 30 == 4 || degrees / 30 == 7 || degrees / 30 == 10) {
				degrees+=15;
			} else {
				degrees+=30;
			}
			radians = Math.toRadians(degrees);
			equation =  2 + Math.sin(radians);
			System.out.println();
		}
		

	}

}
