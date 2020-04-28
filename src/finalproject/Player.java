package finalproject;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Player {
	public String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};
	public boolean color; //true = x false = o..
	Map<String,Integer> letterToInt;
	
	public Player(boolean color) {
		this.color = color;
		setLetterToInt();
	}

	public Player() {
		this.color = true;
	}
	
	public void setLetterToInt() {
		this.letterToInt = new HashMap<String,Integer>();
		for(int i = 1; i <= 8; i++){
			letterToInt.put(this.letters[i-1],i);
		}
	}
	

	
	public String toString() {
		if(this.color) {
			return "Player 1";
		}else {
			return "Player 2";
		}
	}
	

	
	
	//testing code(static method)
	public static void main(String[] args) {
		Player pTrue = new Player(true);
		Player pFalse = new Player(false);
		System.out.println(pTrue);
		System.out.println();
		System.out.println(pFalse);
		
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



	/*Given the array with ALL checkers currently in use, searches for a checker with specified x,y coordinates
	 * If found, returns the checker, if not returns null
	 */
	public Checker findChecker(int xCoordinate, int yCoordinate, ArrayList<Checker> checkersInUse) {
		for(int i = 0; i < checkersInUse.size(); i++) {
			if(xCoordinate == checkersInUse.get(i).getX() && yCoordinate == checkersInUse.get(i).getY()) {
				return checkersInUse.get(i);
			}
		}
		return null;
	}
	
	
	
	//Tells if the checker is a checker of the opponents or not: returns true if it is, false if it's not
	public boolean isEnemyColor(Checker checker) {
		if(checker.getColor() == this.color) {
			return false;
		}
		return true;
	}
	

	//tells if all pieces of a player is gone: returns false if there are pieces still left, false if all gone
	public boolean allPiecesGone(ArrayList<Checker> checkersInUse) {
		for(Checker c : checkersInUse) {
			if(c.getColor() == this.color) {
				return false;
			}
		}
		return true;
	}
	
	
}

