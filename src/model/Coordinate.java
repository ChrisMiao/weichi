package model;

public class Coordinate {
	public int x;
	public int y;
	public int crdColor;

	public Coordinate(int x, int y, int clr) {
		this.x = x;
		this.y = y;
		this.crdColor = clr;
	}

	public String toString() {
		return "x = " + x + "y = " + y + "crdColor = " + crdColor;
	}
	
}
