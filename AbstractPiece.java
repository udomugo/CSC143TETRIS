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
	//private static int lengthX = 0;
	//private static int lengthY = 0;
	
	//private static Point[] points;
	
	protected boolean ableToMove;
	
	protected Square[] square;
	
	// number of squares in one Tetris game piece
	private static final int PIECE_COUNT = 4;
	
	// Made up of PIECE_COUNT squares
	protected Grid grid; // the board this piece is on // DISABLED TO SEE IF PERFROMACE WOULD IMPROVE
	
	/**
	 * 
	 * @param g
	 *            the grid for this game piece
	 * 
	 */
	protected AbstractPiece(Grid g) {
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
	 * Returns the (col, row) grid coordinates occupied by this Piece
	 * 
	 * @return an Array of (col, row) Points
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
//	private void setLocations(Point[] points) {
//		for (int i = 0; i < PIECE_COUNT; i++) {
//			square[i].setCol((int)points[i].getX());
//			square[i].setRow((int)points[i].getY());
//		}
//	}
	
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
	 * Calculates the rotation coordinates
	 * @param p
	 * @return
	 */
	public void rotate() {
		
		x = square[1].getCol();
		y = square[1].getRow();
		
		boolean isBarShape = false;
		int barrierDistanceLeft = 1;
		int barrierDistanceRight = 1;
		int row = 0;
		int col = 0;
		int barY = 0;
		int barX = 0;
		
		if (this.getXperimeter() == 4 || this.getYperimeter() == 4) {
			isBarShape = true;
			if (this.getYperimeter() == 4) {
				barY = y - square[3].getRow();
				if ( barY == 2) {
					barrierDistanceRight = 2;
				} else {
					barrierDistanceLeft = 2;
				}
			} else {
				barX = x - square[3].getCol();
				if (barX == 2) {
					barrierDistanceLeft = 2;
				} else {
					barrierDistanceRight = 2;
				}
			}
		}
		if ( x - barrierDistanceLeft >= 0 && x + barrierDistanceRight <= Grid.WIDTH - 1 ) {
			for ( Square coord : square) {
				
				row = coord.getRow() - y;
				col = coord.getCol() - x;
				
				if (col == 0 && row == -1) {
					coord.setCol(coord.getCol() + 1);
					coord.setRow(coord.getRow() + 1);
				} else if (col == 1 && row == -1) {
					coord.setCol(coord.getCol() + 0);
					coord.setRow(coord.getRow() + 2);
				} else if (col == 1 && row == 0) {
					coord.setCol(coord.getCol() - 1);
					coord.setRow(coord.getRow() + 1);
				} else if (col == 1 && row == 1) {
					coord.setCol(coord.getCol() - 2);
					coord.setRow(coord.getRow() + 0);
				} else if (col == 0 && row == 1) {
					coord.setCol(coord.getCol() - 1);
					coord.setRow(coord.getRow() - 1);
				} else if (col == -1 && row == 1) {
					coord.setCol(coord.getCol() + 0);
					coord.setRow(coord.getRow() - 2);
				} else if (col == -1 && row == 0) {
					coord.setCol(coord.getCol() + 1);
					coord.setRow(coord.getRow() - 1);
				} else if (col == -1 && row == -1) {
					coord.setCol(coord.getCol() + 2);
					coord.setRow(coord.getRow() + 0);
				}
				
				if (isBarShape) {
					if (col == 0 && row == -2) {
						coord.setCol(coord.getCol() + 2);
						coord.setRow(coord.getRow() + 2);
					} else if (col == 1 && row == -2) {
						coord.setCol(coord.getCol() + 1);
						coord.setRow(coord.getRow() + 3);
					} else if (col == 2 && row == -2) {
						coord.setCol(coord.getCol() + 0);
						coord.setRow(coord.getRow() + 4);
					} else if (col == 2 && row == -1) {
						coord.setCol(coord.getCol() - 1);
						coord.setRow(coord.getRow() + 3);
					} else if (col == 2 && row == 0) {
						coord.setCol(coord.getCol() - 2);
						coord.setRow(coord.getRow() + 2);
					} else if (col == 2 && row == 1) {
						coord.setCol(coord.getCol() - 3);
						coord.setRow(coord.getRow() - 1);
					} else if (col == 2 && row == 2) {
						coord.setCol(coord.getCol() - 4);
						coord.setRow(coord.getRow() + 0);
					} else if (col == 1 && row == 2) {
						coord.setCol(coord.getCol() - 3);
						coord.setRow(coord.getRow() - 1);
					} else if (col == 0 && row == 2) {
						coord.setCol(coord.getCol() - 2);
						coord.setRow(coord.getRow() - 2);
					} else if (col == -1 && row == 2) {
						coord.setCol(coord.getCol() - 1);
						coord.setRow(coord.getRow() - 3);
					} else if (col == -2 && row == 2) {
						coord.setCol(coord.getCol() + 0);
						coord.setRow(coord.getRow() - 4);
					} else if (col == -2 && row == 1) {
						coord.setCol(coord.getCol() + 3);
						coord.setRow(coord.getRow() - 3);
					} else if (col == -2 && row == 0) {
						coord.setCol(coord.getCol() + 2);
						coord.setRow(coord.getRow() - 2);
					} else if (col == -2 && row == -1) {
						coord.setCol(coord.getCol() + 3);
						coord.setRow(coord.getRow() - 1);
					} else if (col == -2 && row == -2) {
						coord.setCol(coord.getCol() + 4);
						coord.setRow(coord.getRow() + 0);
					} else if (col == -1 && row == -2) {
						coord.setCol(coord.getCol() + 3);
						coord.setRow(coord.getRow() + 1);
					}
				}
			}
		}
	}
	
	/**
	 * Drops the shape to the lowest row the shape can fit on top of.
	 */
	public void dropPiece() {
		
		int rowAbove = 0; 
		int travelDist = 0;
		
		TreeSet<Integer> lowestYvalues = new TreeSet<Integer>();
		
		// measuring the distance from the shape to the lowest row
		for ( Square coord : square) {
			for ( int row = coord.getRow(); row < Grid.HEIGHT; row++ ) {
				if(grid.isSet(row, coord.getCol())) {
					rowAbove = row - 1;
					lowestYvalues.add( rowAbove - coord.getRow());
					break;
				} else if(row == Grid.HEIGHT - 1) {
					rowAbove = Grid.HEIGHT - 1;
					lowestYvalues.add( rowAbove - coord.getRow());
					break;
				}
			}	
		}
		
		// selecting the drop row value
		travelDist = lowestYvalues.pollFirst();
		
		// setting the new shape coordinates
		for ( Square coord : square) {
			coord.setRow(coord.getRow() + travelDist);
		}
	}
	
	/**
	 * Gets the total shape width
	 * 
	 * @return
	 */
	private int getXperimeter() {
		//Point[] p = piece.getLocations();
		Set<Integer> xValue = new HashSet<Integer>();
		for ( int i = 0; i < square.length; i++) {
			xValue.add(square[i].getCol());
		}
		return xValue.size();
	}
	
	/**
	 * Gets the total shape Height
	 * 
	 * @return
	 */
	private int getYperimeter() {
		//Point[] p = piece.getLocations();
		Set<Integer> yValue = new HashSet<Integer>();
		for ( int i = 0; i < square.length; i++) {
			yValue.add(square[i].getRow());
		}
		return yValue.size();
	}
}
