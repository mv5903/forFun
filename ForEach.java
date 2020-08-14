package forFun;

public class ForEach {

	public static void main(String[] args) {
		int[] n = {5, 2, 67, 4, 22};
		int total = 0;
		for (int x : n) {
			total+=x;
		}
		System.out.println(total);
	}

}
