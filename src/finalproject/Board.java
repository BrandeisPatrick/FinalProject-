package finalproject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Board {

	public static String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};

	public Square[][] board;
	public ArrayList<Checker> checkersInUse;
	public Player player1;
	public Player player2;

	public Board() {
		this.checkersInUse = new ArrayList<Checker>();
		setBoard();
		setPlayer();
	}

	//create board and give each Square coordinates
	public void setBoard() {
		this.board = new Square[8][8];
		for(int i = 1; i <= 8; i++){  	  // vertical
			for(int j = 1; j <= 8; j++){  // horizontal
				this.board[i][j].setCoordinates(j,i);
			}
		}
	}

	//print header method
	public void printHeader(){
		System.out.println("This is checker game for two people;");
		System.out.println("Player 1 (red) will move first;");
		System.out.println("Player 2 (black) will move after Player 1;");
		System.out.println("red checkers will be represented as (X)");
		System.out.println("black checkers will be represented as (O)");
	}

	//distribute the players
	public void setPlayer() {
		System.out.println("Would you like to be Player 1 ? (please reply yes or no)");
		Scanner sc = new Scanner(System.in);
		String str  = sc.next();
		while(!str.equals("yes") && !str.equals("no")){
			System.out.println("Please give a valid answer.");
			str  = sc.next();
		}
		this.player1 = new Player(str.equals("yes"));
		this.player2 = new Player(!str.equals("yes"));
	}

	public void fillBoard(){
		Iterator<Checker> itr  = this.checkersInUse.iterator();
		while(itr.hasNext()){
			Checker checker = itr.next();
			int x = checker.x;
			int y = checker.y;
			board[x][y].setChecker(checker);
		}
	}

	public void eachTurn(){

	}

	public void tick(){
		fillBoard();
	}

	//Inspired from Koolgee0's code to print A B E C E D on one side of the board
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("\n  1 2 3 4 5 6 7 8\n");
		for (int i = 0; i < 8; i++) {
			output.append(letters[i]).append(" ");
			for (int j = 0; j < 8; j++) {
				if (board[i][j].getChecker() != null) {
					output.append(board[i][j].getChecker().toString());
				} else {
					output.append(board[i][j].getColor()).append(" ");
				}
			}
		}
		output.append("\n");
		return output.toString();
	}
/**
	public void printCheckersInUse() {
		System.out.print("[(" + checkersInUse.get(0).printCoordinates());
		for(int i = 1; i < checkersInUse.size(); i++) {
			System.out.print(", " + checkersInUse.get(i).printCoordinates());
		}
		System.out.println("]");
	}
 */
	
	//finds a checker with certain coordinates if it exists
	public Checker findChecker(int xCoordinate, int yCoordinate) {
		for(int i = 0; i < this.checkersInUse.size(); i++) {
			if(xCoordinate == checkersInUse.get(i).getX() && yCoordinate == checkersInUse.get(i).getX()) {
				return checkersInUse.get(i);
			}
		}
		return null;
	}
	
	
	public static void main(String[] args){
		Board board = new Board();
		System.out.print(board);
	}


}
