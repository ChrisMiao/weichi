package model;

public interface IPlayer {
    public Coordinate play(int x, int y);

    // 0 - black, 1 - white
    public void setColor(int i);
    public int getColor();
    public void setDimension(int n);
}
