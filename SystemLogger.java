package forFun;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
/**
 * Logs all system information to one text file of the users choosing, based on a particular path.
 * @author matt
 *
 */
public class SystemLogger {
	//Throw IOException because files could end up not working
	public static void main(String[] args) throws IOException {
		Scanner kbd = new Scanner(System.in);
		File systemLogFile;
		FileWriter writer;
		String path;
		//Helps determine when the file is valid or not
		boolean exceptionOccured = false;
		while (true) {
			System.out.println(
					"Enter an existing directory of where to put the System text file (example: C:\\users\\matt\\documents): ");
			path = kbd.next();
			try {
				exceptionOccured = false;
				systemLogFile = new File(path + "\\systemLogger.txt");
				writer = new FileWriter(systemLogFile);
			} catch (FileNotFoundException f) {
				exceptionOccured = true;
				System.out.println(
						"You either inputted an incorrect path or you are writing to an unwritable directory. Please enter another valid path.");
			}
			if (!exceptionOccured) {
				System.out.printf("Ok, putting systemLogger.txt in %s... ", path);
				break;
			}
		}
		//Create the file:
		systemLogFile = new File(path + "\\systemLogger.txt");
		if (systemLogFile.exists()) {
			systemLogFile.delete();
		}
		//Create the FileWriter object which will write to the text file given via the file systemLogger:
		writer = new FileWriter(systemLogFile);
		//System properties are stored in a Map, so get them accordingly:
		Map<String, String> systemProperties = System.getenv();
		Date currentDate = new Date();
		writer.write("File last updated on " + currentDate.toString() + "\n\n\nSystem Information: \n\n");
		//Write each key and value to the text file
		for (Map.Entry<String, String> e : systemProperties.entrySet()) {
			writer.write(e.getKey() + ": " + e.getValue() + "\n");
		}
		writer.write("\n\nRuntime Information: \n\n");
		Runtime.getRuntime();
		//Runtime Properties are not accessible via a map, so store them each in an array accordingly:
		String[] runtimeInfo = {"Available processors (cores): " + Runtime.getRuntime().availableProcessors(), "Free memory: " + Runtime.getRuntime().freeMemory() + " B (" + String.format("%.3f", toGB(Runtime.getRuntime().freeMemory())) + " GB)", "Maximum Memory: " + Runtime.getRuntime().maxMemory() + " B (" + String.format("%.3f", toGB(Runtime.getRuntime().maxMemory())) + " GB)", "Total Memory: " + Runtime.getRuntime().totalMemory() + " B (" + String.format("%.3f", toGB(Runtime.getRuntime().totalMemory())) + " GB)", "Version: " + Runtime.version()};
		//Write each element of the runtimeInfo String array to the systemLogFile:
		for (String s: runtimeInfo) {
			writer.write(s + "\n");
		}
		writer.write("\n\nFile Roots: \n\n");
		//File Roots are slightly easier, stored nicely in an array:
		File[] roots = File.listRoots();
		//For each is necessary in order to access each file root (C drive, D drive, etc.):
		for (File root: roots) {
			writer.write("File system root: " + root.getAbsolutePath() + "\n");
		    writer.write(String.format("Total space: %d B (%.3f GB)\n", root.getTotalSpace(), toGB(root.getTotalSpace())));
		    writer.write(String.format("Free space: %d B, (%.3f GB)\n", root.getFreeSpace(), toGB(root.getFreeSpace())));
		    writer.write(String.format("Used space: %d B, (%.3f GB)\n", root.getTotalSpace()-root.getFreeSpace(), toGB(root.getTotalSpace()-root.getFreeSpace())) + "\n");
		}
		writer.write("\nOS Info:\n\n");
		//OperatingSystemMXBean is a package responsible for certain cpu and system information:
		OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
		String[] osInfo = {"Name of Operating System: " + os.getName(), "Architecture Type: " + os.getArch(), "Available Processors: " + os.getAvailableProcessors(), "System Load Average: " + os.getSystemLoadAverage()};
		for (String s: osInfo) {
			writer.write(s + "\n");
		}
		System.out.println("Done!");
		//Need to close the Scanner and FileWriter when done:
		kbd.close();
		writer.close();
	}
	/**
	 * Converts a long number in bytes to a floating point value in Gigabytes (GB)
	 * @param bytes Number in bytes
	 * @return bytes converted to Gigabytes (GB)
	 */
	public static double toGB(long bytes) {
		return bytes / 1024.0 / 1024.0 / 1024.0;
	}

}
