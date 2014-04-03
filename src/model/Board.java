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
        if (!isInsideBoundary(coordinate)) return false;
        int x = coordinate.x;
        int y = coordinate.y;
        return board[x][y] == null;
    }

    public boolean isInsideBoundary(Coordinate coordinate) {
        int x = coordinate.x;
        int y = coordinate.y;
        if (x < 0 || x >= dimension) return false;
        if (y < 0 || y >= dimension) return false;
        return true;
    }

    public void remove(Coordinate coordinate) {
        board[coordinate.x][coordinate.y] = null;
    }

    public int getDimension() {
        return dimension;
    }

    public void setCoordinate(Coordinate coordinate) {
        if (coordinate == null) { throw new RuntimeException("This coordinate is null!!!"); }
        int x = coordinate.x;
        int y = coordinate.y;
        if (board[x][y] != null) {
            throw new RuntimeException("The spot is invalid");
        }
        else {
            board[x][y] = coordinate;
        }
    }

    public Coordinate getCoordinate(int x, int y) {
        Coordinate mock = new Coordinate(x, y, 0);
        if (!isInsideBoundary(mock)) return null;
        return board[x][y];
    }

    public int[][] getPlainBoard() {
        int[][] plainBoard = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                plainBoard[i][j] = board[i][j] == null ? -1 : board[i][j].crdColor;
            }
        }

        return plainBoard;
    }

    public static class BoardFactory {
        Board board;

        public static Board getBoard(int n) {
            Board board = new Board(n);
            return board.getBoard();
        }
    }
}
