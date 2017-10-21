import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public interface Piece {
	
	abstract void draw(Graphics g);
	
	abstract void move(Direction direction);
	
	abstract boolean canMove(Direction direction);
	
	abstract Point[] getLocations();
	
	abstract void rotate();
	
	abstract void dropPiece();
	
	abstract Color getColor();
}
