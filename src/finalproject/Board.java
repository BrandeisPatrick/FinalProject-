package finalproject;
import java.util.ArrayList;
import java.util.Iterator;

public class Board {

	public static char RED = '\u25A0';
	public static char BLACK = '\u25A1'; //Inspired from Koolgee0's code https://github.com/Koolgee0/CheckerGame.git
	public static String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};

	public Square[][] board;
	public ArrayList<Checker> checkersInUse;


	public Board() {
		this.board = new Square[8][8];
		this.checkersInUse = new ArrayList<Checker>();
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

	public void printCheckersInUse() {
		System.out.print("[(" + checkersInUse.get(0).printCoordinates());
		for(int i = 1; i < checkersInUse.size(); i++) {
			System.out.print(", " + checkersInUse.get(i).printCoordinates());
		}
		System.out.println("]");
	}
	
	public static void main(String[] args){
		Board board = new Board();
		System.out.print(board);
	}


}
