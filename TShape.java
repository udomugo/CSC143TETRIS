import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * An L-Shape piece in the Tetris Game.
 * 
 * This piece is made up of 4 squares in the following configuration:
 * 
 * Sq <br>
 * Sq Sq<br>
 * Sq  <br>
 * 
 * The game piece "floats above" the Grid. The (col, row) coordinates are the
 * location of the middle Square on the side within the Grid
 * 
 * @author CSC 143
 */
public class TShape extends AbstractPiece {
	
	//private boolean ableToMove; // can this piece move

	//private Square[] square; // the squares that make up this piece

	// Made up of PIECE_COUNT squares
	//private Grid grid; // the board this piece is on // DISABLED TO SEE IF PERFROMACE WOULD IMPROVE

	// number of squares in one Tetris game piece
	//private static final int PIECE_COUNT = 4;

	/**
	 * Creates an L-Shape piece. See class description for actual location of r
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
	public TShape(int r, int c, Grid g) {
		super(g);
		//grid = g; // DISABLED TO SEE IF PERFROMACE WOULD IMPROVE
		//square = new Square[PIECE_COUNT];
		//ableToMove = true;

		// Create the squares
		square[0] = new Square(g, r - 1, c, Color.lightGray, true);
		square[1] = new Square(g, r, c, Color.lightGray, true);
		square[2] = new Square(g, r + 1, c, Color.lightGray, true);
		square[3] = new Square(g, r, c + 1, Color.lightGray, true);
	}

	/**
	 * Draws the piece on the given Graphics context
	 */
//	public void draw(Graphics g) {
//		for (int i = 0; i < PIECE_COUNT; i++) {
//			square[i].draw(g);
//		}
//	}

	/**
	 * Moves the piece if possible Freeze the piece if it cannot move down
	 * anymore
	 * 
	 * @param direction
	 *            the direction to move
	 */
//	public void move(Direction direction) {
//		if (canMove(direction)) {
//			for (int i = 0; i < PIECE_COUNT; i++)
//				square[i].move(direction);
//		}
//		// if we couldn't move, see if because we're at the bottom
//		else if (direction == Direction.DOWN) {
//			ableToMove = false;
//		}
//	}

	/**
	 * Returns the (row,col) grid coordinates occupied by this Piece
	 * 
	 * @return an Array of (row,col) Points
	 */
//	public Point[] getLocations() {
//		Point[] points = new Point[PIECE_COUNT];
//		for (int i = 0; i < PIECE_COUNT; i++) {
//			points[i] = new Point(square[i].getCol(), square[i].getRow());
//		}
//		return points;
//	}
	
	/**
	 * Accepts a Point[] array and sets columns and rows the Square objects in square[] array
	 * @param points
	 */
//	public void setLocations(Point[] points) {
//		//points = new Point[PIECE_COUNT];
//		for (int i = 0; i < PIECE_COUNT; i++) {
//			square[i].setCol((int)points[i].getX());
//			square[i].setRow((int)points[i].getY());
//			//points[i] = new Point(square[i].getCol(), square[i].getRow());
//		}
//		//return points;
//	}
	
	/**
	 * Rotates the shape
	 */
//	public void rotate() {
//		this.setLocations(super.calcRotate(this.getLocations()));
//	}
	
	/**
	 * Drops the shape to the lowest row the shape can fit on top of.
	 */
//	public void dropPiece() {
//		this.setLocations(super.calcDropPiece(this.getLocations(), grid));
//	}

	/**
	 * Return the color of this piece
	 */
//	public Color getColor() {
//		// all squares of this piece have the same color
//		return square[0].getColor();
//	}

	/**
	 * Returns if this piece can move in the given direction
	 * 
	 */
//	public boolean canMove(Direction direction) {
//		if (!ableToMove)
//			return false;
//
//		// Each square must be able to move in that direction
//		boolean answer = true;
//		for (int i = 0; i < PIECE_COUNT; i++) {
//			answer = answer && square[i].canMove(direction);
//		}
//
//		return answer;
//	}
}
