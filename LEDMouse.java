package forFun;

public class LEDMouse extends Mouse{
	public void lights(String color) {
		System.out.println("Lit up the color " + color);
	}
	public boolean isBlinking() {
		return true;
	}
}
