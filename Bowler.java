package forFun;

public class Bowler {
	private String name;
	private int highestScore;
	private int currentScore;
	
	public Bowler(String name) {
		this.name = name;
		highestScore = 300;
		currentScore = 0;
	}
	
	public int getHighestScore() {
		return highestScore;
	}
	
	public int getCurrentScore() {
		return currentScore;
	}
	
	public String getName() {
		return name;
	}
	
	public void setHighestScore(int highestScore) {
		this.highestScore = highestScore;
	}
	
	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}
	
	public String toString() {
		return name + ": " + highestScore + " potential, " + currentScore + " current.";
	}
}
