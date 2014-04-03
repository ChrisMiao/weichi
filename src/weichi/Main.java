package weichi;

import controller.Controller;
import model.Board;
import model.Board.BoardFactory;
import model.Coordinate;
import model.HumanPlayer;
import model.IPlayer;
import processing.core.*;

public class Main extends PApplet {
    private static final long serialVersionUID = 1L;
    private final int n = 18;

    private Board board = BoardFactory.getBoard(n);
    private Controller controller;
    private IPlayer player1;
    private IPlayer player2;

    public void setup() {
        size(600, 600);
        background(227, 173, 108);
        player1 = new HumanPlayer();
        player2 = new HumanPlayer();
        controller = new Controller(player1, player2, board);
    }

    int distance;

    // Main Invocation Point
    public void mousePressed() {
        int x = (mouseX - 10) / distance;
        int y = (mouseY - 10) / distance;

        controller.play(x, y);
    }

    public void draw() {
        distance = (width - 21) / n;
        strokeWeight(3);
        stroke(0);
        noFill();
        rect(0, 0, width - 1, height - 1);
        strokeWeight(1);
        rect(10, 10, distance * 18, distance * 18);
        for (int x = 10 + distance; x < width - 15; x += distance) {
            line(x, 10, x, distance * 18 + 10);
        }
        for (int y = 10 + distance; y < height - 15; y += distance) {
            line(10, y, distance * 18 + 10, y);
        }
        for (int x = 10 + 3 * distance; x < width; x += 6 * distance) {
            for (int y = 10 + 3 * distance; y < height; y += 6 * distance) {
                fill(0);
                ellipse(x, y, 4, 4);
            }
        }
        noStroke();
        // Render chess, stupid keep polling
        for (int i = 0; i < board.getDimension(); i++) {
            for (int j = 0; j < board.getDimension(); j++) {
                Coordinate coordinate = board.getCoordinate(i, j);
                if (coordinate != null) {
                    // Judge color
                    int crdColor = (coordinate.crdColor == 0) ? 0 : 255;
                    fill(crdColor);
                    ellipse(coordinate.x * distance + 10, coordinate.y * distance + 10, distance, distance);
                }
            }
        }
    }
}