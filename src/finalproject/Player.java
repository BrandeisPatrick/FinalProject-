package finalproject;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Player {
	public String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};

	public boolean color; //true = x false = o..
	ArrayList<Checker> checkers;
	Map<String,Integer> letterToInt;
	
	public Player(boolean color) {
		int checkerNum = 0;
		this.color = color;
		this.checkers = new ArrayList<Checker>();
		setCheckers(color, checkers, checkerNum);
		setLetterToInt();
	}

	public Player() {
		this.color = true;
		this.checkers = new ArrayList<Checker>();
	}
	
	public void setLetterToInt() {
		this.letterToInt = new HashMap<String,Integer>();
		for(int i = 1; i <= 8; i++){
			letterToInt.put(this.letters[i-1],i);
		}
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
	

	
	
	//testing code(static method)
	public static void main(String[] args) {
		Player pTrue = new Player(true);
		Player pFalse = new Player(false);
		pTrue.printPlayer();
		System.out.println();
		pFalse.printPlayer();
		
	}

	public void tick(ArrayList<Checker> checkersInUse, Board board){  //do I need to take care of defensive programming
		Scanner userinput = new Scanner(System.in);
		System.out.println("Your move ");
		String select = userinput.next();
		String destination = userinput.next();
		while(select.length()!= 2 || destination.length() != 2){
			System.out.println("defensive programming, your input is invalid");
			System.out.println("Your move ");
			select = userinput.next();
			destination = userinput.next();
		}
		int iy = letterToInt.get(select.substring(0,1));  // expect: E would be mapped to 1
		int ix = Integer.parseInt(select.substring(1));

		int fy = letterToInt.get(destination.substring(0,1));
		int fx = Integer.parseInt(destination.substring(1));


		
		while(findChecker(ix, iy, checkersInUse) == null || !findChecker(ix, iy, checkersInUse).canMove(board, fx, fy, checkersInUse)){  //can be simplified
			//there is no Checker or the move is mistaken.
			//needs to enter an new move.
			System.out.println("Your move ");
			select = userinput.next();
			destination = userinput.next();
			while(select.length()!= 2 || destination.length() != 2){
				System.out.println("defensive programming, your input is invalid");
				System.out.println("Your move ");
				select = userinput.next();
				destination = userinput.next();
			}
			iy = letterToInt.get(select.substring(0,1));  // expect: E would be mapped to 1
			ix = Integer.parseInt(select.substring(1));

			fy = letterToInt.get(destination.substring(0,1));
			fx = Integer.parseInt(destination.substring(1));
		}
		findChecker(ix, iy, checkersInUse).move(fx,fy);
	}




	public Checker findChecker(int xCoordinate, int yCoordinate, ArrayList<Checker> checkersInUse) {
		for(int i = 0; i < checkersInUse.size(); i++) {
			if(xCoordinate == checkersInUse.get(i).getX() && yCoordinate == checkersInUse.get(i).getY()) {
				return checkersInUse.get(i);
			}
		}
		return null;
	}
	
	
	
	//Returns the boolean of which color the checker is: basically saying what player is it
	public boolean isEnemyColor(Checker checker) {
		if(checker.getColor() == this.color) {
			return false;
		}
		return true;
	}
	

	public boolean isMyChecker(int xCoordinate, int yCoordinate, ArrayList<Checker> checkersInUse) {
		Checker c = findChecker(xCoordinate, yCoordinate, checkersInUse);
		if(c.getColor() == this.color) {
			return true;
		}
		else {
			return false;
			
		}
	}
	
	
}

