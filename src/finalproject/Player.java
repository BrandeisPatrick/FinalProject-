package finalproject;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	public boolean color; //true = x false = o..
	ArrayList<Checker> checkers;
	
	public Player(boolean color) {
		int checkerNum = 0;
		this.checkers = new ArrayList<Checker>();
		setCheckers(color, checkers, checkerNum);
	}
	
	public void setCheckers(boolean color, ArrayList<Checker> checkers, int checkerNum) {
		if(color == true) { //player x
			this.color = true;
			for(int i = 1; i < 4; i++) { //iterates through three rows
				if(i%2 != 0) {
					for(int j = 1; j<= 7; j+=2) {
						checkers.add(new Checker(i-1, j, true, checkerNum +1));
						checkerNum ++;
					}
				}else {
					for(int j = 0; j<= 6; j+=2) {
						checkers.add(new Checker(i-1, j, true, checkerNum +1));
						checkerNum ++;
					}
				}
			}
		}else{
			this.color = false;
			for(int i = 5; i <= 7; i++) {
				if(i%2 == 0) {
					for(int j = 0; j<=6; j+=2) {
						checkers.add(new Checker(i,j, true, checkerNum +1));
						checkerNum ++;
					}
				}else {
					for(int j = 1; j<=7; j+=2) {
						checkers.add(new Checker(i,j, true, checkerNum +1));
						checkerNum ++;
					}
				}
			}
			
		}
	}
	

	
	public void printPlayer() {
		for(Checker c : checkers) {
			System.out.println( "(" + c.getX() + "," + c.getY() + ")");
		}
	}
	
	//Checks if the Checker has reached the opposite side
	//Adds a new KingChecker and removes Checker if it has
	public void checkForKing() {
		if (color == true) {
			for(Checker c : checkers) {
				if (c.getX() == 8) {
					KingChecker newChecker = new KingChecker(c.getX(), c.getY(), true, c.getNumber());
					checkers.add(newChecker);
					checkers.remove(c);
				}
			}
		}
		else {
			for(Checker c : checkers) {
				if(c.getX() == 1) {
					KingChecker newChecker = new KingChecker(c.getX(), c.getY(), false, c.getNumber());
					checkers.add(newChecker);
					checkers.remove(c);
				}
			}
		}
	}
	
	
	//testing code(static method)
	public static void main(String[] args) {
		Player pTrue = new Player(true);
		Player pFalse = new Player(false);
		pTrue.printPlayer();
		System.out.println();
		pFalse.printPlayer();
		
	}
	
	public void tick(Scanner console) {

		
	}
	
}

