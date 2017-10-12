import java.awt.Point;

public abstract class Shape {
	
	Square[] square;
	
	public boolean rotate(Shape piece) {
		System.out.println(piece.getClass().toString());
		String evalClass = piece.getClass().toString();
		System.out.println(evalClass);
		return evalClass.equals("class LShape");
	}
	
	
}
