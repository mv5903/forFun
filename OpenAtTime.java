package forFun;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

public class OpenAtTime {
	
	final static String zoomLink = "https://www.google.com/";
	
	public static void main(String[] args) {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {			
				e.printStackTrace();
			}
			LocalDateTime time = LocalDateTime.now();
			if (isWeekday(time.getDayOfWeek().toString())) {
				if (time.getHour() == 7 && time.getMinute() == 15 && time.getSecond() >= 0 && time.getSecond() <= 5) {
					openLink();
					try {
						Thread.sleep(10000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			System.out.printf("[%d:%d:%d] - Application is still running.\n", time.getHour(), time.getMinute(), time.getSecond());
		}
	}
	
	public static boolean isWeekday(String weekday) {
		String[] days = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"};
		return Arrays.asList(days).contains(weekday);
	}
	
	public static void openLink() {
		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec("rundll32 url.dll,FileProtocolHandler " + zoomLink);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
