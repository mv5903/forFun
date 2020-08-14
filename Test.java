package forFun;

public class Test {
	public static String[][] n;
	public static void main(String[] args) {
		n = new String[][]{ { "Hi", "there", "AP", "CS" }, { "students!", "Do", "You", "Understand" },
				{ "2d", "arrays", "yet", ":)" } };
		someMethod(n);
		for (int i = 0; i < n.length; i++) {
			for (int j = 0; j < n[0].length; j++) {
				System.out.print(n[i][j] + "      \t\t");
			}
			System.out.println();
		}
	}

	public static void someMethod(String[][] strArr) {
		String[][] result = new String[strArr.length][strArr[0].length];
		for (int row = 0; row < strArr.length; row++) {
			for (int col = 0; col < strArr[0].length; col++) {
				if (strArr[row][col].length() > 3) {
					int len = strArr[row][col].length() / 2;
					result[row][col] = strArr[row][col].substring(len);
				} else
					result[row][col] = strArr[row][col];
			}
		}
		n = result;
	}
}
