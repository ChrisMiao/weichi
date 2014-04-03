package weichi;

import java.util.ArrayList; 
import model.Board;
import model.Board.BoardFactory;
import model.Coordinate;
import processing.core.*;

public class Main extends PApplet {
	private static final long serialVersionUID = 1L;
	private final int n = 18;

	public void setup() {
		size(600, 600);
		background(227, 173, 108);
	}

	Board board = BoardFactory.getBoard(n);
	ArrayList<Coordinate> positions = new ArrayList<Coordinate>();

	int distance;
	final int black = 0, white = 255;
	int colorcode = black;
	Coordinate p;

	public void mousePressed() {
		p = new Coordinate((mouseX - 10) / distance, (mouseY - 10) / distance,
				colorcode);
		boolean b = board.isAvailable((mouseX - 10) / distance, (mouseY - 10)
				/ distance);

		if (b) {
			if (colorcode == black) {
				colorcode = white;
				board.setCoordinate(p);
				positions.add(p);
			} else {
				colorcode = black;
				board.setCoordinate(p);
				positions.add(p);
			}
		}
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
		for (int i = 0; i < positions.size(); i++) {
			fill(positions.get(i).crdColor);
			ellipse(positions.get(i).x * distance + 10, positions.get(i).y
					* distance + 10, distance, distance);
		}
	}
}