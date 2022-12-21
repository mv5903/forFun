package game2048;

import java.util.HashMap;
import java.awt.Color;
import static java.awt.Color.*;

public class Constants {
	public static int ROWS = 4;
	public static int COLS = 4;
	public static int BOARD_SIZE = 8;
	public static int WINDOW_SIZE = 800;
	
	public static Color BLANK_TILE_COLOR = Color.LIGHT_GRAY;
	
	// Space Colors
	public static HashMap<Integer, Color> spaceColors;
	private static Color[] colors = {
		new Color(238, 228, 218),
		new Color(237, 224, 200),
		new Color(242, 177, 121),
		new Color(245, 149, 99),
		new Color(246, 124, 95),
		new Color(246, 94, 59),
		new Color(237, 207, 114),
		new Color(237, 204, 97),
		new Color(237, 200, 80),
		new Color(237, 197, 63),
		new Color(237, 194, 46)
	};
	
	public static void initColors() {
		spaceColors = new HashMap<>();
		for (int i = 2, j = 0; i <= 2048; i *= 2, j++) {
			spaceColors.put(i, colors[j]);
		}
	}
	
	public static Color getTileColor(int number) {
		if (!spaceColors.containsKey(number)) {
			return BLANK_TILE_COLOR;
		}
		return spaceColors.get(number);
	}
}
