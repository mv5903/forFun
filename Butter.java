package forFun;

public class Butter {

	public static void main(String[] args) {
		final int offset = -3;
		for (int i = 0; i < 100; i++) {
			System.out.print("-");
		}
		System.out.println();
		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 100; i++) {
				boolean b = (((j >= 2 && j <= 6) && i == offset + 20)
						|| ((j == 2 || j == 4 || j == 6) && (i == offset + 22 || i == offset + 24))
						|| ((j == 3 || j == 5) && i == offset + 26)),
						u = (((j >= 2 && j <= 4) && i == offset + 30)
								|| (j == 5 && (i == offset + 31 || i == offset + 35))
								|| (j == 6 && (i == offset + 32 || i == offset + 33 || i == offset + 34))
								|| ((j >= 2 && j <= 4) && i == offset + 36)),
						t = ((j == 2 && (i == offset + 40 || i == offset + 42))
								|| ((j >= 2 && j <= 6) && i == offset + 44)
								|| (j == 2 && (i == offset + 46 || i == offset + 48))
								|| (j == 2 && (i == offset + 52 || i == offset + 54))
								|| ((j >= 2 && j <= 6) && i == offset + 56)
								|| (j == 2 && (i == offset + 58 || i == offset + 60))),
						e = (((j >= 2 && j <= 6) && i == offset + 64) || ((j == 2 || j == 4 || j == 6)
								&& (i == offset + 66 || i == offset + 68 || i == offset + 70 || i == offset + 72))),
						r = (((j >= 2 && j <= 6) && i == offset + 76) || ((j == 2 || j == 4) && i == offset + 78)
								|| ((j == 2 || j == 4 || j == 5) && i == offset + 80)
								|| ((j == 3 || j == 6) && i == offset + 82));
				if (i == 0 || i == 99) {
					System.out.print("|");
				} else if (b) {
					System.out.print("B");
				} else if (u) {
					System.out.print("U");
				} else if (t) {
					System.out.print("T");
				} else if (e) {
					System.out.print("E");
				} else if (r) {
					System.out.print("R");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}

		for (int i = 0; i < 100; i++) {
			System.out.print("-");
		}

	}

}
