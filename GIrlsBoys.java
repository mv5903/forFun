package forFun;

import java.util.Random;

public class GIrlsBoys {
	public static Random r = new Random();

	public static void main(String[] args) {
		String guy;
		String girl;
		// int count = 0;
		// count++;
		guy = chooseGuy();
		girl = chooseGirl();
		System.out.println(guy + " & " + girl);

	}

	public static String chooseGirl() {
		String[] girls = { "ma-an", "katrina", "jess", "dara", "nyah", "naia", "samantha", "rubileen", "liz", "ayanna",
				"helen", "danaya", "yasmeen", "rina", "lindsey", "sophia", "izzy", "krina", "zoe", "bri", "angelika",
				"mahfuza", "dorothy", "emily", "tania", "maddie", "katie", "natalia", "devarshi", "nubia", "sofia" };

		return girls[r.nextInt(girls.length)];
	}

	public static String chooseGuy() {
		String[] guys = { "areet", "santi", "raj", "christian", "chris", "trent", "matt", "prit", "dareen", "neet",
				"wei-chi", "koleby", "ved", "rithvik", "sachit", "aditya", "dustin", "tim", "ishaan", "nick",
				"kunjan" };
		return guys[r.nextInt(guys.length)];

	}
}
