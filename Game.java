import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Manages the game Tetris. Keeps track of the current piece and the grid.
 * Updates the display whenever the state of the game has changed.
 * 
 * @author CSC 143
 */
public class Game {

	private Grid grid; // the grid that makes up the Tetris board

	private Tetris display; // the visual for the Tetris game

	private LShape piece; // the current piece that is dropping

	private boolean isOver; // has the game finished?

	/**
	 * Creates a Tetris game
	 * 
	 * @param Tetris
	 *            the display
	 */
	public Game(Tetris display) {
		grid = new Grid();
		this.display = display;
		piece = new LShape(1, Grid.WIDTH / 2 - 1, grid);
		isOver = false;
	}

	/**
	 * Draws the current state of the game
	 * 
	 * @param g
	 *            the Graphics context on which to draw
	 */
	public void draw(Graphics g) {
		grid.draw(g);
		if (piece != null) {
			piece.draw(g);
		}
	}

	/**
	 * Moves the piece in the given direction
	 * 
	 * @param the
	 *            direction to move
	 */
	public void movePiece(Direction direction) {
		if (piece != null) {
			piece.move(direction);
		}
		
		updatePiece();
		display.update();
		grid.checkRows();
	}
	
	public void rotatePiece() {
		if (piece != null) {
			piece.rotate();
		}
	}
	
	/**
	 * Drops the current piece to the lowest possible position
	 * 
	 * 
	 */
	public void dropPiece() {
		if (piece != null) {		
		
		Point[] p = piece.getLocations();
		int startRow = (int) p[0].getY();
		int col = (int) p[0].getX();
		int targetRow = 0;
		int wide = getXperimeter();
		int tall = getYperimeter();
		TreeSet<Integer> bottomValues = new TreeSet<Integer>();
		
		
		for ( int i = col; i < wide + col; i++) {
			for ( int row = startRow + tall; row < Grid.HEIGHT; row++ ) {
				if(grid.isSet(row, i)) {
					bottomValues.add(row - 1);
					break;
				} else if(row == Grid.HEIGHT - 1) {
					bottomValues.add(row);
					break;
				}
			}
		}
		int lowestValue =  bottomValues.pollFirst();
		targetRow = lowestValue - startRow - 2;
		
 		piece.setRow(targetRow);
		updatePiece();
		display.update();
		grid.checkRows();
		}
	}
	
	/**
	 * Gets the total shape width
	 * 
	 * @return
	 */
	public int getXperimeter() {
		Point[] p = piece.getLocations();
		Set<Integer> xValue = new HashSet<Integer>();
		for ( int i = 0; i < p.length; i++) {
			xValue.add((int)p[i].getX());
		}
		return xValue.size();
	}
	
	/**
	 * Gets the total shape Height
	 * 
	 * @return
	 */
	public int getYperimeter() {
		Point[] p = piece.getLocations();
		Set<Integer> yValue = new HashSet<Integer>();
		for ( int i = 0; i < p.length; i++) {
			yValue.add((int)p[i].getY());
		}
		return yValue.size();
	}

	/**
	 * Returns true if the game is over
	 */
	public boolean isGameOver() {
		// game is over if the piece occupies the same space as some non-empty
		// part of the grid. Usually happens when a new piece is made
		if (piece == null) {
			return false;
		}

		// check if game is already over
		if (isOver) {
			return true;
		}

		// check every part of the piece
		Point[] p = piece.getLocations();
		for (int i = 0; i < p.length; i++) {
			if (grid.isSet((int) p[i].getY(), (int) p[i].getX())) {
				isOver = true;
				return true;
			}
		}
		return false;
	}

	/** Updates the piece */
	private void updatePiece() {
		if (piece == null) {
			// CREATE A NEW PIECE HERE
			piece = new LShape(1, Grid.WIDTH / 2 - 1, grid);
		}

		// set Grid positions corresponding to frozen piece
		// and then release the piece
		else if (!piece.canMove(Direction.DOWN)) {
			Point[] p = piece.getLocations();
			Color c = piece.getColor();
			for (int i = 0; i < p.length; i++) {
				grid.set((int) p[i].getY(), (int) p[i].getX(), c);
			}
			piece = null;
		} else if(piece.canMove(Direction.DOWN)) {
			
		}

	}

}
