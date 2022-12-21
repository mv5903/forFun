package game2048;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class BoardSpace extends JButton {
	private int value;
	private Color color;
	
	public BoardSpace() {
		super("0");
		this.value = 0;
		this.color = Constants.BLANK_TILE_COLOR;
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		this.setOpaque(true);
		this.setForeground(Color.BLACK);
		this.setBorderPainted(true);
		setNumber(0);
	}
	
	public void setValue(int value) {
		this.value = value;
		this.setText(value == 0 ? "" : value + "");
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setColor(Color color) {
		this.color = color;
		this.setBackground(color);
		if (color.equals(Color.DARK_GRAY)) {
			this.setForeground(Color.WHITE);
		} else {
			this.setForeground(Color.BLACK);
		}
	}

	
	public void setNumber(int value) {
		setValue(value);
		setColor(Constants.getTileColor(value));
	}
}
