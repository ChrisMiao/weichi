package model;

public class Coordinate {
    public int x;
    public int y;
    // 0 -> player 1, 1 -> player 2
    public int crdColor;
    
    public Coordinate previous;

    public Coordinate(int x, int y, int clr) {
        this.x = x;
        this.y = y;
        this.crdColor = clr;
    }

    public String toString() {
        return "x = " + x + " : y = " + y + " : crdColor = " + crdColor + "|";
    }

}
