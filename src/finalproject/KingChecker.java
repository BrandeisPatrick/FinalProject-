package finalproject;

import java.util.ArrayList;

public class KingChecker extends Checker {


	public KingChecker(int x, int y, boolean color, int number) {
		super(x,y,color, number);

	}
	
	@Override
	//checks if the checker can move to that spot
		public boolean canMove(int xCoordinate, int yCoordinate, ArrayList<Checker> checkers) { //coordinates player is trying to move to
			if(xCoordinate < 1 || xCoordinate > 8 || yCoordinate < 1 || yCoordinate > 8) {
				System.out.println("The Coordinates you entered are out of bounds");
				return false;
			}else if((xCoordinate == this.x && yCoordinate == this.y + 1)||
					(xCoordinate == this.x && yCoordinate == this.y - 1) ||
					(xCoordinate == this.x + 1 && yCoordinate == this.y) ||
					(xCoordinate == this.x - 1 && yCoordinate == this.y)) {
				System.out.println("That's an invalid move, you can only move diagonally");
				return false;
			}else if(spotTaken(checkers, this.getX(), this.getY())) {
				System.out.println("That spot is taken");
				return false;
			}else {
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
