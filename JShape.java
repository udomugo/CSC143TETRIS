import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * A J-Shape piece in the Tetris Game.
 * 
 * This piece is made up of 4 squares in the following configuration:
 * 
 *    Sq <br>
 *    Sq <br>
 * Sq Sq <br>
 * 
 * The game piece "floats above" the Grid. The (col, row) coordinates are the
 * location of the middle Square on the side within the Grid
 * 
 * @author CSC 143
 */
public class JShape extends AbstractPiece {

	/**
	 * Creates a J-Shape piece. See class description for actual location of r
	 * and c
	 * 
	 * @param r
	 *            row location for this piece
	 * @param c
	 *            column location for this piece
	 * @param g
	 *            the grid for this game piece
	 * 
	 */
	public JShape(int c, int r, Grid g) {
		
		super(g);

		// Create the squares
		square[0] = new Square(g, r - 1, c, Color.pink, true);
		square[1] = new Square(g, r, c, Color.pink, true);
		square[2] = new Square(g, r + 1, c, Color.pink, true);
		square[3] = new Square(g, r + 1, c - 1, Color.pink, true);
	}
}
