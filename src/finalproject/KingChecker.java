package finalproject;

import java.util.ArrayList;

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
	
	//override: returns false every time because it's a king so it can move backward or forward
	public boolean moveBackwards (int targetY, int originalY) {
		return false;
	}
	
				
	

}
