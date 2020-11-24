package forFun;

import java.io.IOException;
import java.util.Scanner;
/**
 * Allows user to quickly run an application from their computer. Best used with a batch file that
 * runs this program.
 * @author matt
 *
 */
public class ExecutableChooser {

	public static void main(String[] args) {
		Scanner kbd = new Scanner(System.in);
		//Extend this downwards for the amount of exe's you want. Be sure to update filePath accordingly.
		System.out.println("What application would you like to run?\n"
				+ "1) Notepad\n"
				+ "2) Application 2\n"
				+ "3) Application 3\n");
		int choice = kbd.nextInt();
		runProgram(choice);
		kbd.close();
	}
	/**
	 * Runs the corresponding executable that corresponds with the given choice number.
	 * @param choice The application that will run according to the choice variable.
	 */
	public static void runProgram(int choice) {
		//Extend this with as many exe's as you would want.
		String[] filePath = {"C:\\Windows\\System32\\Notepad.exe",
				"C:\\Path\\To\\Application2",
				"C:\\Path\\To\\Application3"};
		try {
			Runtime r = Runtime.getRuntime();
			Process p = r.exec(filePath[choice-1]);
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Couldn't find the file specified");
		}
	}

}
