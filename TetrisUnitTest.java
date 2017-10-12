import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class TetrisUnitTest {

	@Test
	public void testCheckRows() {
		// Create a grid with a full row at the bottom
		// and two squares above the full bottom row
		Grid g = new Grid();
		// full bottom row
		for (int col = 0; col < Grid.WIDTH; col++) {
			g.set(Grid.HEIGHT - 1, col, Color.GREEN);
		}
		// add two squares above the bottom row
		g.set(Grid.HEIGHT - 2, 3, Color.RED);
		g.set(Grid.HEIGHT - 3, 3, Color.RED);
		// remove the full row
		g.checkRows();
		// check that the grid has been updated correctly
		for (int row = 0; row < Grid.HEIGHT; row++) {
			for (int col = 0; col < Grid.WIDTH; col++) {
				// check the square at (row,col)
				// must have: (18,3) and (19,3) set
				// and all of the others not set
				if ((row == 18 || row == 19) && col == 3) {
					assertTrue(g.isSet(row, col));

				} else {
					assertFalse(g.isSet(row, col));
				}
			}
		}
	}
	
	@Test
	public void testCheckRowsBigger() {
		// Creates a grid to test a full row on the bottom and then checker board fills
		//	several rows above
		Grid myGrid = new Grid();
		
		// Setting the bottom row to all be non EMPTY Square Objects
		for ( int col = 0; col < Grid.WIDTH; col++ )
			myGrid.set(Grid.HEIGHT -1, col, Color.ORANGE);
		
		
		// Setting the second to bottom row to alternate non EMPTY Square
		//	Objects starting with index 0
		for ( int col = 0; col < Grid.WIDTH; col += 2 )
			myGrid.set(Grid.HEIGHT - 2, col, Color.ORANGE);
		
		
		// Setting the third to the bottom row to alternate non EMPTY Square
		//	Objects starting with index 1
		for ( int col = 1; col < Grid.WIDTH; col += 2 )
			myGrid.set(Grid.HEIGHT - 3, col, Color.BLUE);
		
		// Setting the fourth and fifth from the bottom row to alternate non EMPTY Square
		//	Objects starting with index 0		
		for ( int row = Grid.HEIGHT - 4; row > Grid.HEIGHT - 6; row--) {
			for ( int col = 0; col < Grid.WIDTH; col += 2 )
				myGrid.set(row, col, Color.ORANGE);
		}
		
		// Setting the fourth and fifth from the bottom row to alternate non EMPTY Square
		//	Objects starting with index 0		
		for ( int row = Grid.HEIGHT - 6; row > Grid.HEIGHT - 8; row--) {
			for ( int col = 1; col < Grid.WIDTH; col += 2 )
				myGrid.set(row, col, Color.BLUE);
		}
		
		// Calling checkRows on test Grid object
		myGrid.checkRows();
		
		// Checking that the lowest row has been removed and the following rows have
		// have migrated down correctly.
		for (int row = 0; row < Grid.HEIGHT; row++) {
			for (int col = 0; col < Grid.WIDTH; col++) {
				if ( row == 19 && (col == 0 || col == 2 || col == 4 || col == 6 || col == 8)) {
					assertTrue(myGrid.isSet(row, col));

				} else if ( row == 18 && (col == 1 || col == 3 || col == 5 || col == 7 || col == 9)) {
					assertTrue(myGrid.isSet(row, col));

				} else if ( (row == 17 || row == 16) && (col == 0 || col == 2 || col == 4 || col == 6 || col == 8)) {
					assertTrue(myGrid.isSet(row, col));

				} else if ( (row == 15 || row == 14) && (col == 1 || col == 3 || col == 5 || col == 7 || col == 9)) {
					assertTrue(myGrid.isSet(row, col));

				} else {
					assertFalse(myGrid.isSet(row, col));
				}
			}
		}
	}
	
	@Test
	public void testLShapeMoveDirection() {
		
		// Creates a grid to test shape object movement
		Grid toMoveOn = new Grid();
		
		// Creating LShape object
		LShape shapeL = new LShape(4, 4, toMoveOn);
		
		// Testing moving piece to the left
		shapeL.move(Direction.LEFT);
		assertTrue( 3 == (int)shapeL.getLocations()[1].getX());

		// Testing moving piece to the right
		shapeL.move(Direction.RIGHT);
		assertTrue( 4 == (int)shapeL.getLocations()[1].getX());
		
		// Testing moving piece to the right
		shapeL.move(Direction.RIGHT);
		assertTrue( 5 == (int)shapeL.getLocations()[1].getX());
		
		// Testing moving piece to the down
		shapeL.move(Direction.DOWN);
		assertTrue( 5 == (int)shapeL.getLocations()[1].getY());
	}
	
	@Test
	public void testShapeGetClass() {
		// Creates a grid to test shape object movement
		Grid toMoveOn = new Grid();
		
		// Creating LShape object
		Shape shapeL = new LShape(4, 4, toMoveOn);
		//Shape piece = new LShape();
		shapeL.rotate(shapeL);
		assertTrue(shapeL.rotate(shapeL));
	}
}