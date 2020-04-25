package finalproject;

import java.util.ArrayList;

public class KingChecker extends Checker {


	public KingChecker(int row, int col, boolean color, int number) {
		super(row,col,color, number);

	}
	
	@Override
		public boolean canMove(int rowCoordinate, int colCoordinate, ArrayList<Checker> checkers) { //coordinates player is trying to move to
			if(rowCoordinate < 1 || rowCoordinate > 8 || colCoordinate < 1 || colCoordinate > 8) {
				System.out.println("The Coordinates you entered are out of bounds");
				return false;
			}else if((rowCoordinate == this.row && colCoordinate == this.col + 1)||
					(rowCoordinate == this.row && colCoordinate == this.col - 1) ||
					(rowCoordinate == this.row + 1 && colCoordinate == this.col) ||
					(rowCoordinate == this.row - 1 && colCoordinate == this.col)) {
				System.out.println("That's an invalid move, you can only move diagonally");
				return false;
			}else if(spotTaken(checkers, this)) {
				System.out.println("That spot is taken");
				return false;
			}else{
				return true;
			}
		}
	
	public String toString(){
		if (color) {
			return "X";
		} else {
			return "O";
		}
	}
	

}
