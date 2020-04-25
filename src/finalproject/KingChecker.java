package finalproject;

import java.util.ArrayList;

public class KingChecker extends Checker {


	public KingChecker(int x, int y, boolean color, int number) {
		super(x,y,color, number);

	}
	
	@Override
		public boolean canMove(int xCoordinate, int yCoordinate, ArrayList<Checker> checkers) { //coordinates player is trying to move to
			
		}
	
	public String toString(){
		if (color) {
			return "X";
		} else {
			return "O";
		}
	}
	

}
