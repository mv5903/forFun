package game2048;

public class Main {
	private static Board board;

	public static void main(String[] args) {
		Constants.initColors();
		board = new Board();
		board.showBoard();
		board.newGame();
	}

}
