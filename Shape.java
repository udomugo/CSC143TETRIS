import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public abstract class Shape {
	
	private static int x = 0;
	private static int y = 0;
	private static int lengthX = 0;
	private static int lengthY = 0;
	
	private static Point[] points;
	
	public void rotate(Point[] p) {
		
		points = p;
		x = (int) points[2].getX();
		y = (int) points[2].getY();
		lengthX = getXperimeter(points);
		lengthY = getYperimeter(points);
		int[] xCoord1 = new int[4];
		int[] xCoord2 = new int[4];
		int[] yCoord1 = new int[4];
		int[] yCoord2 = new int[4];
		
		if (lengthX > lengthY) {
			for ( Point coord : points) {
				int i = 0;
				if (x == coord.getX() || x > coord.getX()) {
					xCoord1[i] = (int) coord.getX();
				} else if (x == coord.getX() || x < coord.getX()) {
					xCoord2[i] = (int) coord.getX();
				}
				i++;
			}
		} else if (lengthX < lengthY) {
			for ( Point coord : points) {
				int i = 0;
				if (y == coord.getY() || y > coord.getY()) {
					yCoord1[i] = (int) coord.getY();
				} else if (y == coord.getY () || y < coord.getY()) {
					yCoord2[i] = (int) coord.getY();
				}
				i++;
			}
		}
	}
	
//	private Square[] square;
//	
//	public boolean rotate(Shape piece) {
//		System.out.println(piece.getClass().toString());
//		String evalClass = piece.getClass().toString();
//		System.out.println(evalClass);
//		return evalClass.equals("class LShape");
//		//piece = (LShape) piece;
//		this.square = ((LShape) piece).getSquare();
//	}
//	
//	public abstract Square[] getSquare();
//	
//	public void dropPiece() {
//		if (piece != null) {		
//		
//		Point[] p = piece.getLocations();
//		int startRow = (int) p[0].getY();
//		int col = (int) p[0].getX();
//		int targetRow = 0;
//		int wide = getXperimeter();
//		int tall = getYperimeter();
//		TreeSet<Integer> bottomValues = new TreeSet<Integer>();
//		
//		
//		for ( int i = col; i < wide + col; i++) {
//			for ( int row = startRow + tall; row < Grid.HEIGHT; row++ ) {
//				if(grid.isSet(row, i)) {
//					bottomValues.add(row - 1);
//					break;
//				} else if(row == Grid.HEIGHT - 1) {
//					bottomValues.add(row);
//					break;
//				}
//			}
//		}
//		int lowestValue =  bottomValues.pollFirst();
//		targetRow = lowestValue - startRow - 2;
//		
// 		piece.setRow(targetRow);
//		updatePiece();
//		display.update();
//		grid.checkRows();
//		}
//	}
	
	/**
	 * Gets the total shape width
	 * 
	 * @return
	 */
	public int getXperimeter(Point[] p) {
		//Point[] p = piece.getLocations();
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
	public int getYperimeter(Point[] p) {
		//Point[] p = piece.getLocations();
		Set<Integer> yValue = new HashSet<Integer>();
		for ( int i = 0; i < p.length; i++) {
			yValue.add((int)p[i].getY());
		}
		return yValue.size();
	}
	
	public Point[] getPoints() {
		return this.points;
	}
}
