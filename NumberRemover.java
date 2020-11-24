package forFun;

import java.io.File;
import java.util.Scanner;

/**
 * Removes numbers from beginning of a line of code in a text file.
 * @author matt
 *
 */
public class NumberRemover {
	
	// Paste code in your downloads folder, and name it this:
	static String fileName = "toDecode.txt";
	
	public static void main(String[] args) throws Exception {
		String userFolder = System.getProperty("user.home");
		File toDecode = new File(userFolder + "/Downloads/" + fileName);
		Scanner reader = new Scanner(toDecode);
		while (reader.hasNextLine()) {
			String currentLine = reader.nextLine();
			String toPrint = currentLine.substring(currentLine.indexOf('.') + 1);
			System.out.println(toPrint);
		}
		reader.close();
	}
}
