package controller;

import java.util.ArrayList;

import model.Board;
import model.Coordinate;
import model.IPlayer;

public class Controller {
    IPlayer player1;
    IPlayer player2;
    Board board;
    int playerNum;

    public void play(int x, int y) {
        IPlayer currentPlayer;
        if (playerNum == 0) {
            currentPlayer = player1;
            playerNum = 1;
        } else {
            currentPlayer = player2;
            playerNum = 0;
        }

        Coordinate coordinate = currentPlayer.play(x, y);
        if (board.isAvailable(coordinate)) {
            board.setCoordinate(coordinate);
        }
        // TODO: Logic Rules
    }

    public boolean isWin() {
        // TODO: isWin
        return false;
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
