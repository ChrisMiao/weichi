package controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.Board;
import model.Coordinate;
import model.IPlayer;

public class Controller {
    IPlayer player1;
    IPlayer player2;
    Board board;
    int playerNum;
    Coordinate currentCoordinate;
    // Value is layer
    Map<Coordinate, Integer> visitedMap = new HashMap<>();
    boolean isSurrounding;

    public void play(int x, int y) {
        IPlayer currentPlayer;
        if (playerNum == 0) {
            currentPlayer = player1;
            playerNum = 1;
        } else {
            currentPlayer = player2;
            playerNum = 0;
        }

        currentCoordinate = currentPlayer.play(x, y);
        if (board.isAvailable(currentCoordinate)) {
            board.setCoordinate(currentCoordinate);
        }

        // Logic Rules
        visitedMap.clear();
        isSurrounding = false;
        isSurrounding(currentCoordinate, currentCoordinate, 0);

        if (isSurrounding) {
            Set<Coordinate> needRemoveCoordinate = new HashSet<>();
            for (Coordinate iter : visitedMap.keySet()) {
                needRemoveCoordinate.addAll(getPathRight(iter, new HashSet<Coordinate>()));
                needRemoveCoordinate.addAll(getPathLeft(iter, new HashSet<Coordinate>()));
                needRemoveCoordinate.addAll(getPathUp(iter, new HashSet<Coordinate>()));
                needRemoveCoordinate.addAll(getPathDown(iter, new HashSet<Coordinate>()));

            }

            for (Coordinate iter : needRemoveCoordinate) {
                board.remove(iter);
            }
        }
    }

    private Set<Coordinate> getPathUp(Coordinate iterCoordinate, Set<Coordinate> path) {

        int x = iterCoordinate.x;
        int y = iterCoordinate.y;

        Coordinate mockCoordinate = new Coordinate(x - 1, y, iterCoordinate.crdColor);

        while (board.isInsideBoundary(mockCoordinate)) {
            Coordinate next = board.getCoordinate(mockCoordinate.x, mockCoordinate.y);
            if (next == null) {
                return getPathUp(next, path);
            } else if (next.crdColor == iterCoordinate.crdColor) {
                return path;
            } else if (next.crdColor != iterCoordinate.crdColor) {
                path.add(next);
                return getPathUp(next, path);
            }
        }

        return new HashSet<Coordinate>();
    }

    private Set<Coordinate> getPathDown(Coordinate iterCoordinate, Set<Coordinate> path) {

        int x = iterCoordinate.x;
        int y = iterCoordinate.y;

        Coordinate mockCoordinate = new Coordinate(x + 1, y, iterCoordinate.crdColor);

        while (board.isInsideBoundary(mockCoordinate)) {
            Coordinate next = board.getCoordinate(mockCoordinate.x, mockCoordinate.y);
            if (next == null) {
                return getPathDown(next, path);
            } else if (next.crdColor == iterCoordinate.crdColor) {
                return path;
            } else if (next.crdColor != iterCoordinate.crdColor) {
                path.add(next);
                return getPathDown(next, path);
            }
        }

        return new HashSet<Coordinate>();
    }

    private Set<Coordinate> getPathLeft(Coordinate iterCoordinate, Set<Coordinate> path) {

        int x = iterCoordinate.x;
        int y = iterCoordinate.y;

        Coordinate mockCoordinate = new Coordinate(x, y - 1, iterCoordinate.crdColor);

        while (board.isInsideBoundary(mockCoordinate)) {
            Coordinate next = board.getCoordinate(mockCoordinate.x, mockCoordinate.y);
            if (next == null) {
                return getPathLeft(next, path);
            } else if (next.crdColor == iterCoordinate.crdColor) {
                return path;
            } else if (next.crdColor != iterCoordinate.crdColor) {
                path.add(next);
                return getPathLeft(next, path);
            }
        }

        return new HashSet<Coordinate>();
    }

    private Set<Coordinate> getPathRight(Coordinate iterCoordinate, Set<Coordinate> path) {

        int x = iterCoordinate.x;
        int y = iterCoordinate.y;

        Coordinate mockCoordinate = new Coordinate(x, y + 1, iterCoordinate.crdColor);

        while (board.isInsideBoundary(mockCoordinate)) {
            Coordinate next = board.getCoordinate(mockCoordinate.x, mockCoordinate.y);
            if (next == null) {
                return getPathRight(next, path);
            } else if (next.crdColor == iterCoordinate.crdColor) {
                return path;
            } else if (next.crdColor != iterCoordinate.crdColor) {
                path.add(next);
                return getPathRight(next, path);
            }
        }

        return new HashSet<Coordinate>();
    }

    private void isSurrounding(Coordinate coordinate, Coordinate previous, int layer) {
        if (visitedMap.containsKey(coordinate) && layer == visitedMap.get(coordinate) + 1
                && previous != currentCoordinate) {
            isSurrounding = true;
        }

        if (visitedMap.containsKey(coordinate)) return;
        visitedMap.put(coordinate, layer);

        int color = coordinate.crdColor;
        int x = coordinate.x;
        int y = coordinate.y;
        Coordinate left = board.getCoordinate(x - 1, y);
        Coordinate right = board.getCoordinate(x + 1, y);
        Coordinate top = board.getCoordinate(x, y - 1);
        Coordinate bottom = board.getCoordinate(x, y + 1);
        Coordinate leftTop = board.getCoordinate(x - 1, y - 1);
        Coordinate leftBottom = board.getCoordinate(x - 1, y + 1);
        Coordinate rightTop = board.getCoordinate(x + 1, y - 1);
        Coordinate rightBottom = board.getCoordinate(x + 1, y + 1);

        boolean hasNext = false;
        if (left != null && left.crdColor == color && !visitedMap.containsKey(left)) {
            isSurrounding(left, coordinate, layer + 1);
            hasNext = true;
        }
        if (right != null && right.crdColor == color && !visitedMap.containsKey(right)) {
            isSurrounding(right, coordinate, layer + 1);
            hasNext = true;
        }
        if (top != null && top.crdColor == color && !visitedMap.containsKey(top)) {
            isSurrounding(top, coordinate, layer + 1);
            hasNext = true;
        }
        if (bottom != null && bottom.crdColor == color && !visitedMap.containsKey(bottom)) {
            isSurrounding(bottom, coordinate, layer + 1);
            hasNext = true;
        }
        if (leftTop != null && leftTop.crdColor == color && !visitedMap.containsKey(leftTop)) {
            isSurrounding(leftTop, coordinate, layer + 1);
            hasNext = true;
        }
        if (leftBottom != null && leftBottom.crdColor == color && !visitedMap.containsKey(leftBottom)) {
            isSurrounding(leftBottom, coordinate, layer + 1);
            hasNext = true;
        }
        if (rightTop != null && rightTop.crdColor == color && !visitedMap.containsKey(rightTop)) {
            isSurrounding(rightTop, coordinate, layer + 1);
            hasNext = true;
        }
        if (rightBottom != null && rightBottom.crdColor == color && !visitedMap.containsKey(rightBottom)) {
            isSurrounding(rightBottom, coordinate, layer + 1);
            hasNext = true;
        }

        if (!hasNext) {
            visitedMap.clear();
            isSurrounding = false;
        }
    }


    public Controller(IPlayer player1, IPlayer player2, Board board) {
        this.playerNum = 0;
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;

        this.player1.setDimension(board.getDimension());
        this.player2.setDimension(board.getDimension());
        this.player1.setColor(0);
        this.player2.setColor(1);
    }

}
