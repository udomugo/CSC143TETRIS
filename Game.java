import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
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

	private Piece piece; // the current piece that is dropping

	private boolean isOver; // has the game finished?
	
	private Random dice = new Random();
	
	// Tracking the random numbers that generate shapes
	public static int[] shapeGenOutput = new int[8];

	/**
	 * Creates a Tetris game
	 * 
	 * @param Tetris
	 *            the display
	 */
	public Game(Tetris display) {
		grid = new Grid();
		this.display = display;
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
		updatePiece();
		display.update();
		grid.checkRows();
	}
	/**
	 * Drops the current piece to the lowest possible position
	 * 
	 * 
	 */
	public void dropPiece() {
		if (piece != null) {
			
			piece.dropPiece();
			
	 		updatePiece();
			display.update();
			grid.checkRows();
		}
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
			int rand = 0;
			while (rand == 0) {
				rand = (int)(dice.nextInt(24)/3.14159);
				// Tracking the count of random numbers generated
				shapeGenOutput[rand]++;
				//System.out.println(rand);  // print out each number generated
			}
			switch(rand) {
				case 1: {
					piece = new LShape(Grid.WIDTH / 2 - 1, 1, grid);
					break;
				}
				case 2: {
					piece = new TShape(Grid.WIDTH / 2 - 1, 1, grid);
					break;
				}
				case 3: {
					piece = new SShape(Grid.WIDTH / 2 - 1, 1, grid);
					break;
				}
				case 4: {
					piece = new SquareShape(Grid.WIDTH / 2 - 1, 1, grid);
					break;
				}
				case 5: {
					piece = new ZShape(Grid.WIDTH / 2 - 1, 1, grid);
					break;
				}
				case 6: {
					piece = new JShape(Grid.WIDTH / 2 - 1, 1, grid);
					break;
				}
				case 7: {
					piece = new BarShape(Grid.WIDTH / 2 - 1, 1, grid);
					break;
				}
			}
			
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
