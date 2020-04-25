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
			System.out.println( "(" + c.getRow() + "," + c.getCol() + ")");
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

