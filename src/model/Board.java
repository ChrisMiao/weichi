package model;

public class Board {
    Coordinate[][] board;
    int dimension;

    private Board(int n) {
        dimension = n;
    }

    public Board getBoard() {
        if (board == null) board = new Coordinate[dimension][dimension];
        return this;
    }

    public boolean isAvailable(Coordinate coordinate) {
        int x = coordinate.x;
        int y = coordinate.y;
        return board[x][y] == null;
    }

    public int getDimension() {
        return dimension;
    }

    public void setCoordinate(Coordinate coordinate) {
        if (coordinate == null) {
            throw new RuntimeException("This coordinate is null!!!");
        }
        int x = coordinate.x;
        int y = coordinate.y;
        if (board[x][y] != null) {
            throw new RuntimeException("The spot is invalid");
        } else {
            board[x][y] = coordinate;
        }
    }

    public Coordinate getCoordinate(int x, int y) {
        return board[x][y];
    }

    public static class BoardFactory {
        Board board;

        public static Board getBoard(int n) {
            Board board = new Board(n);
            return board.getBoard();
        }
    }
}
