package finalproject;
import java.util.ArrayList;

public class Board {

	public char[][] board;
	public ArrayList<Checker> checkersInUse;


	public Board() {
		this.board = new char[9][9];
		this.checkersInUse = new ArrayList<Checker>();
		setHeader();
	}

	public void setChecker(Checker checker) {
		checkersInUse.add(checker);
	}

	public void setHeader(){
		int decimalCode = 69;
		for(int i = 1; i <= 8; i++){
			this.board[0][i] = (char) decimalCode;
			decimalCode++;
		}

		decimalCode = 49;
		for(int i = 1; i <= 8; i++){
			this.board[i][0] = (char) decimalCode;
			decimalCode++;
		}
	}


	public String toString() {
		String output = "";
		for (int i = 0; i <= 8; i++) {
			for (int j = 0; j <= 8; j++) {
				if(this.board[i][j] == '\u0000'){
					output += "_" + "    ";
				}else {
					output += this.board[i][j] +  "    ";
				}
			}
			output += "\n\n" ;
		}
		return output;
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
