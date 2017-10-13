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
}