package finalproject;
import java.util.ArrayList;

public class Checker {
	
	public int x;
	public int y;
	public boolean color; // true = x; false = o
	public int number;

	public Checker(int x,int y, boolean color, int number) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.number = number;
	}


	
	public void Move(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
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
		}else if((xCoordinate == this.x + 1 && yCoordinate == this.y - 1) ||
				(xCoordinate == this.x + 1 && yCoordinate == this.y + 1)) {
			System.out.println("Invalid move, you cant move backwards as a regualar checker");
			return false;
		}else if(spotTaken(checkers, this.getX(), this.getY())) {
			System.out.println("That spot is taken");
			return false;
		}else if ((ateEnemyChecker() == false) &&
				 ((Math.abs(xCoordinate - this.x) > 1) || (Math.abs(yCoordinate - this.y) > 1)) ){
			System.out.println("Invalid move, you can't move more than one spot without eating an enemy checker");
			return false;
		}else {
			return true;
		}
	}
	
	
	//checks if a spot is already taken
	public boolean spotTaken(ArrayList<Checker> checkers, int xCoordinate, int yCoordinate) {
		for(int i = 0; i < checkers.size(); i++) {
			if(checkers.get(i).getX() == xCoordinate &&
			   checkers.get(i).getY() == yCoordinate) {
				return true;
			}
		}
		return false;
	}
	
	//checks if the checker jumped over an enemy checker
	public boolean ateEnemyChecker(ArrayList<Checker> checkers, Checker checker, int xCoordinate, int yCoordinate) {
		if(xCoordinate == checker.getX() + 2 && yCoordinate == checker.getY() + 2) {
			if(spotTaken(checkers, checker.getX() + 1, checker.getY() + 1) && checkers.get){
				
			}
		}
			(xCoordinate == checker.getX() + 2 && yCoordinate == checker.getY() - 2) ||
			(xCoordinate == checker.getX() - 2 && yCoordinate == checker.getY() + 2) ||
			(xCoordinate == checker.getX() - 2 && yCoordinate == checker.getY() - 2)) {
	}
	
	
	public String toString(){
		if (color) {
			return "x";
		} else {
			return "o";
		}
	}
	
	public String printCoordinates() {
		return("(" + this.x + ", "+ this.y + ")");
	}
	
	public int getX() {
		return this.y;
	}
	
	public int getY() {
		return this.y;
	}
	
	public boolean getColor() {
		return this.color;
	}
	
	public int getNumber() {
		return this.number;
	}
}
