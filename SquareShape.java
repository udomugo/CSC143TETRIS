import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * A Square-Shape piece in the Tetris Game.
 * 
 * This piece is made up of 4 squares in the following configuration:
 * 
 * Sq Sq<br>
 * Sq Sq <br>
 * 
 * 
 * The game piece "floats above" the Grid. The (col, row) coordinates are the
 * location of the middle Square on the side within the Grid
 * 
 * @author CSC 143
 */
public class SquareShape extends AbstractPiece {

	/**
	 * Creates a Square-Shape piece. See class description for actual location of r
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
	public SquareShape(int c, int r, Grid g) {
		
		super(g);

		// Create the squares
		square[0] = new Square(g, r, c - 1, Color.cyan, true);
		square[1] = new Square(g, r, c, Color.cyan, true);
		square[2] = new Square(g, r + 1, c - 1, Color.cyan, true);
		square[3] = new Square(g, r + 1, c, Color.cyan, true);
	}
	
	/**
	 * 
	 * Method prevents the SquareShape Object from executing the rotate() method from the AbstractPiece Class
	 * 
	 * @Override
	 */
	public void rotate() {}
}