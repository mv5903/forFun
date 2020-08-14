package forFun;

import java.util.ArrayList;
import java.util.Scanner;

public class Bowling {
	public static Scanner kbd = new Scanner(System.in);
	public static ArrayList<Bowler> bowlers = new ArrayList<Bowler>();
	public static int firstShot, secondShot;
	public static void main(String[] args) {
		new Bowling();
	}
	
	public Bowling() {
		getPlayerNames();
		startGame();
	}
	
	public static void getPlayerNames() {
		System.out.println("Enter player names. Type \"stop\" at any time to halt name entry.");
		String tempName = kbd.nextLine();
		while (!tempName.equalsIgnoreCase("stop")) {
			bowlers.add(new Bowler(tempName));
			System.out.println("Enter another player's name: ");
			tempName = kbd.nextLine();
		}
	}
	
	public static void startGame() {
		for (int i = 1; i <= 9; i++) {
			for (int j = 0; j < bowlers.size(); j++) {
				firstRoll(j, i);
				secondRoll(j, i);
			}
			updateScores();
		}
		for (int i = 0; i < bowlers.size(); i++) { 
			tenthFrame(i);
		}
	}
	
	public static void firstRoll(int bowlerNumber, int frame) {
		System.out.println("Frame: " + frame + 
				"\nCurrent Score: " + bowlers.get(bowlerNumber).getCurrentScore() + 
				"\nHighest Score Possible: " + bowlers.get(bowlerNumber).getHighestScore() + 
				"\n" + bowlers.get(bowlerNumber).getName() + ", enter your first shot.");
		firstShot = kbd.nextInt();
	}
	
	public static void secondRoll(int bowlerNumber, int frame) {
		System.out.println(bowlers.get(bowlerNumber).getName() + ", enter your second shot");
		secondShot = kbd.nextInt();
	}
	
	public static void updateScores() {
		
	}
	
	public static void tenthFrame(int bowlerNumber) {
		
	}
}
