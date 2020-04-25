package finalproject;
import java.util.ArrayList;

public class Checker {
	
	public int row;
	public int col;
	public boolean color; // true = x; false = o
	public int number;

	public Checker(int row,int col, boolean color, int number) {
		this.row = col;
		this.row = col;
		this.color = color;
		this.number = number;
	}


	
	public void Move(int row,int col) {
		this.row = row;
		this.col = col;
	}
	
	//checks if the checker can move to that spot
	public boolean canMove(int rowCoordinate, int colCoordinate, ArrayList<Checker> checkers) { //coordinates player is trying to move to
		if((rowCoordinate == this.row && colCoordinate == this.col + 1) ||
		   (rowCoordinate == this.row && colCoordinate == this.col - 1) ||
		   (rowCoordinate == this.row + 1 && colCoordinate == this.col) ||
		   (rowCoordinate == this.row - 1 && colCoordinate == this.col)) {
			System.out.println("That's an invalid move");
			return false;
		}else if(spotTaken(checkers, this)) {
			System.out.println("That spot is taken");
			return false;
		}else{
			return true;
		}
	}
	
	//checks if a spot is already taken
	public boolean spotTaken(ArrayList<Checker> checkers, Checker checker) {
		for(int i = 0; i < checkers.size(); i++) {
			if(checkers.get(i).getRow() == checker.getRow() &&
			   checkers.get(i).getCol() == checker.getCol()) {
				return true;
			}
		}
		return false;
	}
	
	
	public String toString(){
		if (color) {
			return "x";
		} else {
			return "o";
		}
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public boolean getColor() {
		return this.color;
	}
}
