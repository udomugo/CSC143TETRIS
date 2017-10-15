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

	private Shape piece; // the current piece that is dropping

	private boolean isOver; // has the game finished?
	
	private Random dice = new Random();

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
		updatePiece();
		display.update();
		grid.checkRows();
	}
	
	
//	public boolean findTargetRow() {
//		
//		Point[] p = piece.getLocations();
//		//int startRow = (int) p[0].getY();
//		int startRow = getShapeGreatestY();
//		int col = getShapeLowestX();
//		int targetRow = 0;
//		int wide = getXperimeter();
//		//int tall = getYperimeter();
//		int rowAbove = 0; 
//		ArrayList<Integer> memYvalue = new ArrayList<Integer>();
//		TreeSet<Integer> lowestYvalues = new TreeSet<Integer>();
//		
//		
//		
//		return false;
//	}
	
	/**
	 * Drops the current piece to the lowest possible position
	 * 
	 * 
	 */
	public void dropPiece() {
		if (piece != null) {
			
			piece.dropPiece();
			
//			Point[] p = piece.getLocations();
//			//int startRow = (int) p[0].getY();
//			int startRow = getShapeGreatestY();
//			int col = getShapeLowestX();
//			int targetRow = 0;
//			int wide = getXperimeter();
//			//int tall = getYperimeter();
//			int rowAbove = 0; 
//			int travelDist = 0;
//			ArrayList<Integer> memYvalue = new ArrayList<Integer>();
//			TreeSet<Integer> lowestYvalues = new TreeSet<Integer>();
//			
//			
//			//lowestYvalues.clear();
//			for ( Point coord : p) {
//				
//				
//					for ( int row = (int)coord.getY(); row < Grid.HEIGHT; row++ ) {
//						if(grid.isSet(row, (int) coord.getX())) {
//							rowAbove = row - 1;
//							lowestYvalues.add( rowAbove - (int)coord.getY());
//							break;
//						} else if(row == Grid.HEIGHT - 1) {
//							rowAbove = Grid.HEIGHT - 1;
//							lowestYvalues.add( rowAbove - (int)coord.getY());
//							break;
//						}
//					}
//					
//			}
//			
//			travelDist = lowestYvalues.pollFirst();
//			
//			for ( Point coord : p) {
//				coord.setLocation((int)coord.getX(), 
//						travelDist + (int)coord.getY());
//			}
			
			//piece.setLocations(p);
			
	 		updatePiece();
			display.update();
			grid.checkRows();
		}
	}
	
//	/**
//	 * Gets the total shape width
//	 * 
//	 * @return
//	 */
//	public int getXperimeter() {
//		Point[] p = piece.getLocations();
//		Set<Integer> xValue = new HashSet<Integer>();
//		for ( int i = 0; i < p.length; i++) {
//			xValue.add((int)p[i].getX());
//		}
//		return xValue.size();
//	}
//	
//	/**
//	 * Gets the total shape Height
//	 * 
//	 * @return
//	 */
//	public int getYperimeter() {
//		Point[] p = piece.getLocations();
//		Set<Integer> yValue = new HashSet<Integer>();
//		for ( int i = 0; i < p.length; i++) {
//			yValue.add((int)p[i].getY());
//		}
//		return yValue.size();
//	}
//	
//	public int getShapeGreatestY() {
//		Point[] p = piece.getLocations();
//		TreeSet<Integer> yValue = new TreeSet<Integer>();
//		for ( int i = 0; i < p.length; i++) {
//			yValue.add((int)p[i].getY());
//		}
//		return yValue.pollLast();
//	}
//	
//	public int getShapeLowestX() {
//		Point[] p = piece.getLocations();
//		TreeSet<Integer> xValue = new TreeSet<Integer>();
//		for ( int i = 0; i < p.length; i++) {
//			xValue.add((int)p[i].getX());
//		}
//		return xValue.pollFirst();
//	}


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
			int rand = dice.nextInt(2);
			switch(rand) {
				case 0: {
					piece = new LShape(1, Grid.WIDTH / 2 - 1, grid);
					break;
				}
				case 1: {
					piece = new TShape(1, Grid.WIDTH / 2 - 1, grid);
					break;
				}
				//case 2: piece = new LShape(1, Grid.WIDTH / 2 - 1, grid);
				//case 3: piece = new TShape(1, Grid.WIDTH / 2 - 1, grid);
				//case 4: piece = new LShape(1, Grid.WIDTH / 2 - 1, grid);
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
