import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
//import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public abstract class AbstractPiece implements Piece{
	
	private static int x = 0;
	private static int y = 0;
	private static int lengthX = 0;
	private static int lengthY = 0;
	
	private static Point[] points;
	
	protected boolean ableToMove;
	
	protected Square[] square;
	
	// number of squares in one Tetris game piece
	private static final int PIECE_COUNT = 4;
	
	// Made up of PIECE_COUNT squares
	protected Grid grid; // the board this piece is on // DISABLED TO SEE IF PERFROMACE WOULD IMPROVE
	
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
	public AbstractPiece(Grid g) {
		grid = g; // DISABLED TO SEE IF PERFROMACE WOULD IMPROVE
		square = new Square[PIECE_COUNT];
		ableToMove = true;
	}
	
	/**
	 * Draws the piece on the given Graphics context
	 */
	public void draw(Graphics g) {
		//square = this.getSquares();
		for (int i = 0; i < PIECE_COUNT; i++) {
			square[i].draw(g);
		}
	}
	
//	public Square[] getSquare(Piece piece) {
//		return piece.getSquares();
//	}
	
	/**
	 * Moves the piece if possible Freeze the piece if it cannot move down
	 * anymore
	 * 
	 * @param direction
	 *            the direction to move
	 */
	public void move(Direction direction) {
		if (canMove(direction)) {
			for (int i = 0; i < PIECE_COUNT; i++)
				square[i].move(direction);
		}
		// if we couldn't move, see if because we're at the bottom
		else if (direction == Direction.DOWN) {
			ableToMove = false;
		}
	}
	
	/**
	 * Returns the (row,col) grid coordinates occupied by this Piece
	 * 
	 * @return an Array of (row,col) Points
	 */
	public Point[] getLocations() {
		Point[] points = new Point[PIECE_COUNT];
		for (int i = 0; i < PIECE_COUNT; i++) {
			points[i] = new Point(square[i].getCol(), square[i].getRow());
		}
		return points;
	}
	
	/**
	 * Accepts a Point[] array and sets columns and rows the Square objects in square[] array
	 * @param points
	 */
	public void setLocations(Point[] points) {
		//points = new Point[PIECE_COUNT];
		for (int i = 0; i < PIECE_COUNT; i++) {
			square[i].setCol((int)points[i].getX());
			square[i].setRow((int)points[i].getY());
			//points[i] = new Point(square[i].getCol(), square[i].getRow());
		}
		//return points;
	}
	
	/**
	 * Return the color of this piece
	 */
	public Color getColor() {
		// all squares of this piece have the same color
		return square[0].getColor();
	}

	/**
	 * Returns if this piece can move in the given direction
	 * 
	 */
	public boolean canMove(Direction direction) {
		if (!ableToMove)
			return false;

		// Each square must be able to move in that direction
		boolean answer = true;
		for (int i = 0; i < PIECE_COUNT; i++) {
			answer = answer && square[i].canMove(direction);
		}

		return answer;
	}
	
	/**
	 * Drops the shape to the lowest row the shape can fit on top of.
	 */
	public void dropPiece() {
		this.setLocations(calcDropPiece(this.getLocations(), grid));
	}
	
	/**
	 * Calculates the rotation coordinates
	 * @param p
	 * @return
	 */
	public void rotate() {
		
//		points = p;
		x = square[1].getCol();
		y = square[1].getRow();
		
		int row = 0;
		int col = 0;
		
		if ( x - 1 >= 0 && x + 1 <= 9 ) {
			
			for ( Square coord : square) {
				
				row = (int) coord.getRow() - y;
				col = (int) coord.getCol() - x;
				
				if (col == 0 && row == -1) {
					coord.setCol(coord.getCol() + 1);
					coord.setRow(coord.getRow() + 1);
				} else if (col == 1 && row == -1) {
					coord.setCol(coord.getCol() + 0);
					coord.setRow(coord.getRow() +  2);
				} else if (col == 1 && row == 0) {
					coord.setCol(coord.getCol() - 1);
					coord.setRow(coord.getRow() +  1);
				} else if (col == 1 && row == 1) {
					//if ( x - 2 >= 0) {
						coord.setCol(coord.getCol() - 2);
						coord.setRow(coord.getRow() +  0);
					//}
				} else if (col == 0 && row == 1) {
					coord.setCol(coord.getCol() - 1);
					coord.setRow(coord.getRow() -  1);
				} else if (col == -1 && row == 1) {
					coord.setCol(coord.getCol() + 0);
					coord.setRow(coord.getRow() - 2);
				} else if (col == -1 && row == 0) {
					coord.setCol(coord.getCol() + 1);
					coord.setRow(coord.getRow() - 1);
				} else if (col == -1 && row == -1) {
					//if (x + 2 <=9) {
						coord.setCol(coord.getCol() + 2);
						coord.setRow(coord.getRow() + 0);
					//}
					
				}
			}
		}
	}
	
	
	public Point[] calcDropPiece(Point[] p, Grid grid) {
		
		//Grid grid = g;
		int rowAbove = 0; 
		int travelDist = 0;
		//ArrayList<Integer> memYvalue = new ArrayList<Integer>();
		TreeSet<Integer> lowestYvalues = new TreeSet<Integer>();
		
		// measuring the distance from the shape to the lowest row
		for ( Point coord : p) {
			for ( int row = (int)coord.getY(); row < Grid.HEIGHT; row++ ) {
				if(grid.isSet(row, (int) coord.getX())) {
					rowAbove = row - 1;
					lowestYvalues.add( rowAbove - (int)coord.getY());
					break;
				} else if(row == Grid.HEIGHT - 1) {
					rowAbove = Grid.HEIGHT - 1;
					lowestYvalues.add( rowAbove - (int)coord.getY());
					break;
				}
			}	
		}
		
		// selecting the drop row value
		travelDist = lowestYvalues.pollFirst();
		
		// setting the new shape coordinates
		for ( Point coord : p) {
			coord.setLocation((int)coord.getX(), 
					travelDist + (int)coord.getY());
		}
		// returning the new location
		return p;
	}
	
	/**
	 * Gets the total shape width
	 * 
	 * @return
	 */
	public int getXperimeter() {
		//Point[] p = piece.getLocations();
		Set<Integer> xValue = new HashSet<Integer>();
		for ( int i = 0; i < points.length; i++) {
			xValue.add((int)points[i].getX());
		}
		return xValue.size();
	}
	
	/**
	 * Gets the total shape Height
	 * 
	 * @return
	 */
	public int getYperimeter() {
		//Point[] p = piece.getLocations();
		Set<Integer> yValue = new HashSet<Integer>();
		for ( int i = 0; i < points.length; i++) {
			yValue.add((int)points[i].getY());
		}
		return yValue.size();
	}
	
	public int getShapeGreatestY() {
		//Point[] p = piece.getLocations();
		TreeSet<Integer> yValue = new TreeSet<Integer>();
		for ( int i = 0; i < points.length; i++) {
			yValue.add((int)points[i].getY());
		}
		return yValue.pollLast();
	}
	
	public int getShapeLowestX() {
		//Point[] p = piece.getLocations();
		TreeSet<Integer> xValue = new TreeSet<Integer>();
		for ( int i = 0; i < points.length; i++) {
			xValue.add((int)points[i].getX());
		}
		return xValue.pollFirst();
	}	
}
