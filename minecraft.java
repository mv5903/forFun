package forFun;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
/**
 * Generates random strings of a variable length. Used in Minecraft for the
 * April fools update where writing this contents would teleport you to a 
 * randomly generated dimension.
 * @author matt
 *
 */
public class Minecraft {
	public static void main(String[] args) {
		String toCopy = "";
		for (int i = 0; i < (int)(Math.random()*100); i++) {
			int c = (int)(Math.random() * 26) + 'a';
			toCopy+=(char)(c);
			
		}
	
		StringSelection stringSelection = new StringSelection(toCopy);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);	
		clipboard.getContents(new String());
		System.out.println(toCopy);
	}
}
