package forFun;


public class ZoomInviteToHTML {
	public static void main(String[] args) {
		String s = "dfkjl!.d,?sk";
		System.out.println(puncRemoved(s));
	}
	
	public static String puncRemoved(String s) {
		char[] puncs = {' ', '.', '!', '?', ','};
		String puncRemoved = "";
		boolean isAight = true;
		for (char c: s.toCharArray()) {
			for (char puncMark: puncs) {
				if (c == puncMark) {
					isAight=false;
				}
			}
			if (isAight) {
				puncRemoved+=c;
			}
		}
		return puncRemoved;
	}
}
