package model;

public class HumanPlayer implements IPlayer {
    private int clr;

    @Override
    public Coordinate play(int x, int y) {
        Coordinate coordinate = new Coordinate(x, y, clr);
        return coordinate;
    }

    @Override
    public void setColor(int i) {
        this.clr = i;
    }

    @Override
    public void setDimension(int n) {
    }

}
