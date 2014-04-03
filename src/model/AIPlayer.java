package model;

public class AIPlayer implements IPlayer {
    private int n;
    private int clr;

    @Override
    public Coordinate play() {
        // TODO: Random generate coordinate in 0 - n-1.
        int x = (int) Math.random();
        int y = 0;
        Coordinate coordinate = new Coordinate(x, y, clr);
        return coordinate;
    }

    /**
     * 
     * @param n size of the board
     */
    public AIPlayer(int n) {
        this.n = n;
    }

}
