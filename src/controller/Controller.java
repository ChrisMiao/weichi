package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import model.Board;
import model.Coordinate;
import model.IPlayer;

public class Controller {
    IPlayer player1;
    IPlayer player2;
    Board board;
    // player1 = 1, player2 = -1
    int playerNum;
    Coordinate currentCoordinate;
    // Value is layer
    Map<Coordinate, Integer> visitedMap = new HashMap<>();
    boolean isSurrounding;

    private void checkSurrouding(int[][] plainBoard, int thisColor) {
        System.out.println("------------------------START-------------");
        for (int i = 0; i < plainBoard.length; i++) {
            for (int j = 0; j < plainBoard[i].length; j++) {
                String s = plainBoard[i][j] == Integer.MAX_VALUE ? "--" : String.valueOf(plainBoard[i][j]);
                System.out.print(s + "\t");
            }
            System.out.println("");
        }

        for (int i = 0; i < plainBoard.length; i++) {
            bfs(plainBoard, 0, i, thisColor);
            bfs(plainBoard, plainBoard.length - 1, i, thisColor);
            bfs(plainBoard, i, 0, thisColor);
            bfs(plainBoard, i, plainBoard.length - 1, thisColor);
        }
        System.out.println(">>>NEW>>>>");
        for (int i = 0; i < plainBoard.length; i++) {
            for (int j = 0; j < plainBoard[i].length; j++) {
                String s = plainBoard[i][j] == Integer.MAX_VALUE ? "--" : String.valueOf(plainBoard[i][j]);
                System.out.print(s + "\t");
            }
            System.out.println("");
        }
        System.out.println("------------------------END-------------");

    }

    private void bfs(int[][] plainBoard, int x, int y, int thisColor) {
        if (plainBoard[x][y] == Integer.MAX_VALUE) return;
        Queue<Coordinate> q = new LinkedList<>();
        q.add(new Coordinate(x, y, 0));

        while (!q.isEmpty()) {
            Coordinate current = q.poll();
            int currentx = current.x;
            int currenty = current.y;

            if (plainBoard[currentx][currenty] == 1) {
                System.out.println(current.previous);
            }

            int currentVal = plainBoard[currentx][currenty];
            if (currentVal != thisColor && currentVal != Integer.MAX_VALUE) {
                plainBoard[currentx][currenty] = Integer.MAX_VALUE;

                Coordinate top = new Coordinate(currentx - 1, currenty, 0);
                Coordinate bottom = new Coordinate(currentx + 1, currenty, 0);
                Coordinate left = new Coordinate(currentx, currenty - 1, 0);
                Coordinate right = new Coordinate(currentx, currenty + 1, 0);

                Coordinate rightBottom = new Coordinate(currentx + 1, currenty + 1, 0);
                Coordinate leftTop = new Coordinate(currentx - 1, currenty - 1, 0);
                Coordinate leftBottom = new Coordinate(currentx + 1, currenty - 1, 0);
                Coordinate rightTop = new Coordinate(currentx - 1, currenty + 1, 0);

                if (board.isInsideBoundary(top)) {
                    top.crdColor = plainBoard[top.x][top.y];
                    if (plainBoard[top.x][top.y] == thisColor) {
                        top.previous = current;
                        q.add(top);
                    }
                }
                else {
                    top = null;
                }

                if (board.isInsideBoundary(bottom)) {
                    bottom.crdColor = plainBoard[bottom.x][bottom.y];
                    if (plainBoard[bottom.x][bottom.y] == thisColor) {
                        bottom.previous = current;
                        q.add(bottom);
                    }
                }
                else {
                    bottom = null;
                }

                if (board.isInsideBoundary(left)) {
                    left.crdColor = plainBoard[left.x][left.y];
                    if (plainBoard[left.x][left.y] == thisColor) {
                        left.previous = current;
                        q.add(left);
                    }
                }
                else {
                    left = null;
                }

                if (board.isInsideBoundary(right)) {
                    right.crdColor = plainBoard[right.x][right.y];
                    if (plainBoard[right.x][right.y] == thisColor) {
                        right.previous = current;
                        q.add(right);
                    }
                }
                else {
                    right = null;
                }

                boolean isQualified = board.isInsideBoundary(rightBottom);
                if (right != null && right.crdColor != thisColor && right.crdColor != Integer.MAX_VALUE) {
                    if (bottom != null && bottom.crdColor != thisColor && bottom.crdColor != Integer.MAX_VALUE) {
                        isQualified = false;
                    }
                }
                if (isQualified) {
                    rightBottom.previous = current;
                    q.add(rightBottom);
                }

                isQualified = board.isInsideBoundary(rightTop);
                if (right != null && right.crdColor != thisColor && right.crdColor != Integer.MAX_VALUE) {
                    if (top != null && top.crdColor != thisColor && top.crdColor != Integer.MAX_VALUE) {
                        isQualified = false;
                    }
                }
                if (isQualified) {
                    rightTop.previous = current;
                    q.add(rightTop);
                }

                isQualified = board.isInsideBoundary(leftTop);
                if (left != null && left.crdColor != thisColor && left.crdColor != Integer.MAX_VALUE) {
                    if (top != null && top.crdColor != thisColor && top.crdColor != Integer.MAX_VALUE) {
                        isQualified = false;
                    }
                }
                if (isQualified) {
                    leftTop.previous = current;
                    q.add(leftTop);
                }

                isQualified = board.isInsideBoundary(leftBottom);
                if (left != null && left.crdColor != thisColor && left.crdColor != Integer.MAX_VALUE) {
                    if (bottom != null && bottom.crdColor != thisColor && bottom.crdColor != Integer.MAX_VALUE) {
                        isQualified = false;
                    }
                }
                if (isQualified) {
                    leftBottom.previous = current;
                    q.add(leftBottom);
                }
            }
        }
    }

    public static boolean flag = true;
    public static IPlayer player;

    public void play(int x, int y) {
        IPlayer currentPlayer = player;
        if (flag) {
            currentPlayer = playerNum == 1 ? player1 : player2;
            player = player2;
            flag = false;
        }
        currentCoordinate = currentPlayer.play(x, y);

        if (board.isAvailable(currentCoordinate)) {
            board.setCoordinate(currentCoordinate);
            playerNum *= -1;
        }

        // Logic Rules
        int[][] plainBoard = board.getPlainBoard();
        checkSurrouding(plainBoard, currentPlayer.getColor());
        squash(plainBoard, currentCoordinate.crdColor);

    }

    private boolean needSquash(int[][] plainBoard) {
        for (int i = 0; i < plainBoard.length; i++) {
            for (int j = 0; j < plainBoard.length; j++) {
                if (plainBoard[i][j] == -1) return false;
            }
        }
        return true;
    }

    private void squash(int[][] plainBoard, int crdColor) {
        if (needSquash(plainBoard)) {
            for (int i = 0; i < plainBoard.length; i++) {
                for (int j = 0; j < plainBoard[i].length; j++) {
                    if (plainBoard[i][j] != crdColor && plainBoard[i][j] != -1) {
                        board.remove(new Coordinate(i, j, 0));
                    }
                }
            }
        }
    }

    public Controller(IPlayer player1, IPlayer player2, Board board) {
        this.playerNum = 1;
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;

        this.player1.setDimension(board.getDimension());
        this.player2.setDimension(board.getDimension());
        this.player1.setColor(0);
        this.player2.setColor(1);
    }

}
