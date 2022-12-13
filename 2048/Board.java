package main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends JPanel implements KeyListener {
	private BoardSpace[][] board;
	private static JFrame frame;
	
	public Board() {
		super(new GridLayout(Constants.ROWS, Constants.COLS));
		this.setPreferredSize(new Dimension(Constants.WINDOW_SIZE, Constants.WINDOW_SIZE));
		board = new BoardSpace[Constants.ROWS][Constants.COLS];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = new BoardSpace();
				this.add(board[i][j]);
			}
		}
		frame = new JFrame("2048");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.pack();
		frame.setFocusable(true);
		frame.addKeyListener(this);
	}
	
	public void showBoard() {
		frame.setVisible(true);
	}
	
	public void newGame() {
		// Choose two random indices of the board (make sure they aren't the same!)
		Point a, b;
		do {
			a = new Point((int) (Math.random() * Constants.COLS), (int) (Math.random() * Constants.ROWS));			
			b = new Point((int) (Math.random() * Constants.COLS), (int) (Math.random() * Constants.ROWS));
		} while (a.x == b.x && a.y == b.y);
		// Choose either two 2's, or 2 & 4:
		boolean secondIsFour = Math.random() < 0.5;
		// Create new BoardSpaces and add to board
		board[a.x][a.y].setNumber(2);
		board[b.x][b.y].setNumber(secondIsFour ? 4 : 2);
		
	}
 	
	public void moveLeft() {
		System.out.println("Moved Left");
	}
	
	public void moveRight() {
		System.out.println("Moved Right");
		// Same applies to each row
		for (int i = 0; i < Constants.ROWS; i++) {
			BoardSpace[] row = getRow(i);
			// Anywhere that there are two consecutive numbers, combine and replace with zeros
			for (int num = row.length - 1; num >= 0; num--) {
				int numToLookFor = row[num].getValue();
				for (int prevs = num - 1; prevs >= 0; prevs--) {
					int lookingAt = row[prevs].getValue();
					if (numToLookFor == 0) break;
					// If we find a different number, break
					if (numToLookFor != lookingAt) {
						break;
					}
					// If we find the same number (other than 0), combine together
					if (numToLookFor == lookingAt) {
						
					}
				}
			}
		}
	}
	
	public void moveUp() {
		System.out.println("Moved Up");
	}
	
	public void moveDown() {
		System.out.println("Moved Down");
	}
	
	private BoardSpace[] getRow(int row) {
		return this.board[row];
	}
	
	private int getRowNonzeros(int row) {
		int countnonzero = 0;
		for (BoardSpace space: getRow(row)) {
			if (space.getValue() != 0) {
				countnonzero++;
			}
		}
		return countnonzero;
	}
	
	private BoardSpace[] getColumn(int column) {
		BoardSpace[] col = new BoardSpace[Constants.ROWS];
		for (int row = 0; row < Constants.ROWS; row++) {
			col[row] = this.board[row][column];
		}
		return col;
	}
	
	private int getColumnNonzeros(int column) {
		int countnonzero = 0;
		for (BoardSpace space: getColumn(column)) {
			if (space.getValue() != 0) {
				countnonzero++;
			}
		}
		return countnonzero;
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			moveDown();
			return;
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			moveUp();
			return;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			moveLeft();
			return;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			moveRight();
			return;
		}
		System.err.printf("Warning: Unimplemented key pressed: %d ['%c']\n", e.getKeyCode(), e.getKeyChar());
	}

	// Unused Listener Methods
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
}
