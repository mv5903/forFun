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

public class SystemLogger {

	public static void main(String[] args) throws IOException {
		Scanner kbd = new Scanner(System.in);
		File systemLogger;
		FileWriter writer;
		String path;
		boolean exceptionOccured = false;
		while (true) {
			System.out.println(
					"Enter an existing directory of where to put the System text file (example: C:\\users\\matt\\documents): ");
			path = kbd.next();
			try {
				exceptionOccured = false;
				systemLogger = new File(path + "\\systemLogger.txt");
				writer = new FileWriter(systemLogger);
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

		systemLogger = new File(path + "\\systemLogger.txt");
		if (systemLogger.exists()) {
			systemLogger.delete();
		}
		writer = new FileWriter(systemLogger);
		Map<String, String> systemProperties = System.getenv();
		Date currentDate = new Date();
		writer.write("File last updated on " + currentDate.toString() + "\n\n\nSystem Information: \n\n");
		for (Map.Entry<String, String> e : systemProperties.entrySet()) {
			writer.write(e.getKey() + ": " + e.getValue() + "\n");
		}
		writer.write("\n\nRuntime Information: \n\n");
		Runtime.getRuntime();
		String[] runtimeInfo = {"Available processors (cores): " + Runtime.getRuntime().availableProcessors(), "Free memory: " + Runtime.getRuntime().freeMemory() + " B (" + String.format("%.3f", toGB(Runtime.getRuntime().freeMemory())) + " GB)", "Maximum Memory: " + Runtime.getRuntime().maxMemory() + " B (" + String.format("%.3f", toGB(Runtime.getRuntime().maxMemory())) + " GB)", "Total Memory: " + Runtime.getRuntime().totalMemory() + " B (" + String.format("%.3f", toGB(Runtime.getRuntime().totalMemory())) + " GB)", "Version: " + Runtime.version()};
		for (String s: runtimeInfo) {
			writer.write(s + "\n");
		}
		writer.write("\n\nFile Roots: \n\n");
		File[] roots = File.listRoots();
		for (File root: roots) {
			writer.write("File system root: " + root.getAbsolutePath() + "\n");
		    writer.write(String.format("Total space: %d B (%.3f GB)\n", root.getTotalSpace(), toGB(root.getTotalSpace())));
		    writer.write(String.format("Free space: %d B, (%.3f GB)\n", root.getFreeSpace(), toGB(root.getFreeSpace())));
		    writer.write(String.format("Used space: %d B, (%.3f GB)\n", root.getTotalSpace()-root.getFreeSpace(), toGB(root.getTotalSpace()-root.getFreeSpace())) + "\n");
		}
		writer.write("\nOS Info:\n\n");
		OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
		String[] osInfo = {"Name of Operating System: " + os.getName(), "Architecture Type: " + os.getArch(), "Available Processors: " + os.getAvailableProcessors(), "System Load Average: " + os.getSystemLoadAverage()};
		for (String s: osInfo) {
			writer.write(s + "\n");
		}
		System.out.println("Done!");
		kbd.close();
		writer.close();
	}
	
	public static double toGB(long bytes) {
		return bytes / 1024.0 / 1024.0 / 1024.0;
	}

}
