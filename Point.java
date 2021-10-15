package forFun;
/**
 * Point class used for several programs in this package.
 * @author matt
 *
 */
public class Point {

	private double x, y;
	private int number;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(double x, double y, int number) {
		this.x = x;
		this.y = y;
		this.number = number;
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public int getNumber() {
		return number;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	public double distanceBetween(Point p) {
		double horizontal = Math.abs(p.getX()-this.getX());
		double vertical = Math.abs(p.getY()-this.getY());
		return Math.sqrt(Math.pow(horizontal, 2) + Math.pow(vertical, 2));
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
