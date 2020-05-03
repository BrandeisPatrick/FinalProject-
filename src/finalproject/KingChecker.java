package finalproject;
import java.util.ArrayList;

/**
 * KingChecker is a normal checker except with the special feature that it can move forwards and 
 * backwards. It is designated with capital X and O instead of lower case
 */

public class KingChecker extends Checker {


	public KingChecker(int x, int y, boolean color, int number) {
		super(x,y,color, number);
	}
	
	
	public String toString(){
		if (color) {
			return "X";
		} else {
			return "O";
		}
	}


	/**
	 * @Override
	 * returns false every time because it's a king so it can move backward or forward.
	 * @param targetY
	 * @param originalY
	 * @return
	 */
	public boolean moveBackwards (int targetY, int originalY) {
		return false;
	}
	
				
	

}
