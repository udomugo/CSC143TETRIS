/**
 * This version of Tetris have the following function implemented:
 * 	- Left and Right Arrows will move the current piece in the 
 * 		desired direction on the next update cycle.
 *
 *  - Down Arrow advances the current piece further downward than the
 * 		timer driven downward advance.
 * 
 *  - Spacebar drops current piece to lowest possible row in the
 *  	play field within one animation (timer) cycle.
 *  
 *  - clearRows() method has been implemented and will clear a 
 *  	solid row of colored squares and advance up the rows
 *  	from the bottom moving the play field down one row.
 *  
 *  - Implemented a JUnit test library to project and created a couple of basic tests.
 * 
 */

/**
 * Create and control the game Tetris.
 * 
 * @author CSC 143
 *
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


@SuppressWarnings("serial")
public class Tetris extends JPanel {

	private Game game;

	/**
	 * Sets up the parts for the Tetris game, display and user control
	 */
	public Tetris() {
		game = new Game(this); 
		JFrame f = new JFrame("The Tetris Game");
		f.add(this);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400, 550);
		f.setVisible(true);
		EventController ec = new EventController(game);
		f.addKeyListener(ec);
		setBackground(Color.YELLOW);
	}

	/**
	 * Updates the display
	 */
	public void update() {
		repaint();
	}

	/**
	 * Paint the current state of the game
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.draw(g);
		if (game.isGameOver()) {
			g.setFont(new Font("Palatino", Font.BOLD, 40));
			g.setColor(Color.BLACK);
			g.drawString("GAME OVER", 80, 300);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Tetris();
			}
		});
	}

}
