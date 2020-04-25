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
		if(rowCoordinate < 1 || rowCoordinate > 8 || colCoordinate < 1 || colCoordinate > 8) {
			System.out.println("The Coordinates you entered are out of bounds");
			return false;
		}else if((rowCoordinate == this.row && colCoordinate == this.col + 1)||
				(rowCoordinate == this.row && colCoordinate == this.col - 1) ||
				(rowCoordinate == this.row + 1 && colCoordinate == this.col) ||
				(rowCoordinate == this.row - 1 && colCoordinate == this.col)) {
			System.out.println("That's an invalid move, you can only move diagonally");
			return false;
		}else if((rowCoordinate == this.row + 1 && colCoordinate == this.col - 1) ||
				(rowCoordinate == this.row + 1 && colCoordinate == this.col + 1)) {
			System.out.println("Invalid move, you cant move backwards as a regualr checker");
			return false;
		}else if(spotTaken(checkers, this.getRow(), this.getCol())) {
			System.out.println("That spot is taken");
			return false;
		}else {
			return true;
		}
	}
	
	
	//checks if a spot is already taken
	public boolean spotTaken(ArrayList<Checker> checkers, int rowCoordinate, int colCoordinate) {
		for(int i = 0; i < checkers.size(); i++) {
			if(checkers.get(i).getRow() == rowCoordinate &&
			   checkers.get(i).getCol() == colCoordinate) {
				return true;
			}
		}
		return false;
	}
	
	//checks if the checker jumped over an enemy checker
	public boolean ateEnemyChecker(ArrayList<Checker> checkers, Checker checker, int rowCoordinate, int colCoordinate) {
		if(rowCoordinate == checker.getRow() + 2 && colCoordinate == checker.getCol() + 2) {
			if(spotTaken(checkers, checker.getRow() + 1, checker.getCol() + 1) && checkers.get){
				
			}
		}
			(rowCoordinate == checker.getRow() + 2 && colCoordinate == checker.getCol() - 2) ||
			(rowCoordinate == checker.getRow() - 2 && colCoordinate == checker.getCol() + 2) ||
			(rowCoordinate == checker.getRow() - 2 && colCoordinate == checker.getCol() - 2)) {
	}
	
	
	public String toString(){
		if (color) {
			return "x";
		} else {
			return "o";
		}
	}
	
	public String printCoordinates() {
		return("(" + this.row + ", "+ this.col + ")");
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
