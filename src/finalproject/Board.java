package finalproject;
import java.util.ArrayList;
import java.util.Iterator;


public class Board {
	public String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};

	public Square[][] board;
	public ArrayList<Checker> checkersInUse;

	public Board() {
		this.checkersInUse = new ArrayList<Checker>();
		setBoard();

	}

	//create board and give each Square coordinates
	public void setBoard() {
		this.board = new Square[8][8];
		for(int i = 1; i <= 8; i++){  	  // vertical
			for(int j = 1; j <= 8; j++){  // horizontal
				this.board[i-1][j-1] = new Square();
				this.board[i-1][j-1].setCoordinates(j,i);
			}
		}
		setCheckers(true, checkersInUse, 12);
		setCheckers(false, checkersInUse, 12);
		fillBoard();
	}

	public void setCheckers(boolean color, ArrayList<Checker> checkers, int checkerNum) {
		if(color == true) { //player x
			color = true;
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
			color = false;
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
	
	public void fillBoard(){
		Iterator<Checker> itr  = this.checkersInUse.iterator();
		while(itr.hasNext()){
			Checker checker = itr.next();
			int x = checker.x;
			int y = checker.y;
			board[x][y].setChecker(checker);
		}
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
					output.append(board[i][j].toString()).append(" ");
				}
			}
			output.append("\n");
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
	//finds a checker with certain coordinates if it exists, given coordinates
	public Checker findChecker(int xCoordinate, int yCoordinate) {
		for(int i = 0; i < this.checkersInUse.size(); i++) {
			if(xCoordinate == checkersInUse.get(i).getX() && yCoordinate == checkersInUse.get(i).getY()) {
				return checkersInUse.get(i);
			}
		}
		return null;
	}
	
	//finds a checker with certain coordinates if it exists, given a checker
		public Checker findChecker(Checker checker) {
			for(int i = 0; i < this.checkersInUse.size(); i++) {
				if(checker.getX() == checkersInUse.get(i).getX() && checker.getY() == checkersInUse.get(i).getY()) {
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
