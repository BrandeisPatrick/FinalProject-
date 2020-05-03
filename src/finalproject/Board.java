package finalproject;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is basically the board for the checker game. 
 * String[] letters is the array that allows for conversion between the board coordinates and x, y coordinates
 * Square[][] board is what the simulation prints to build the board on the console
 * ArrayList<Checker> checkersInUse is all the checkers currently on the board at a given time
 * 
 * The board sets up all checkers at the start of the game. It also has a number of methods for 
 * finding and removing checkers on the board, and checking if spots are filled on the board.
 */

public class Board {
	public String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};

	public Square[][] board;
	public ArrayList<Checker> checkersInUse;

	public Board() {
		this.checkersInUse = new ArrayList<Checker>();
		setBoard();

	}

	/**
	 * This method initialize the Board as 8X8 Squares and assign each individual Square coordinates
	 * It set 12 checkers for each player
	 * It calls the fillBoard method.
	 */
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

	/**
	 * This method sets up the checkers on the board.
	 * @param color is the identity for Player X (true) or Player O (false)
	 * @param checkersInUse is the ArrayList contains all the checkers
	 * @param checkerNum  is the number of Checkers will be added
	 */
	public void setCheckers(boolean color, ArrayList<Checker> checkersInUse, int checkerNum) {
		if(color == true) { //player x
			color = true;
			for(int i = 1; i <= 3; i++) { //iterates through three rows
				if(i%2 != 0) {
					for(int j = 1; j<= 7; j+=2) {
						checkersInUse.add(new Checker( j, i,true, checkerNum +1));
						checkerNum ++;
					}
				}else {
					for(int j = 2; j<= 8; j+=2) {
						checkersInUse.add(new Checker( j, i,  true, checkerNum +1));
						checkerNum ++;
					}
				}
			}
		}else{
			color = false;
			for(int i = 6; i <= 8; i++) {
				if(i%2 != 0) {
					for(int j = 1; j<=7; j+=2) {
						checkersInUse.add(new Checker(j, i, false, checkerNum +1));
						checkerNum ++;
					}
				}else {
					for(int j = 2; j<=8; j+=2) {
						checkersInUse.add(new Checker(j, i, false, checkerNum +1));
						checkerNum ++;
					}
				}
			}
			
		}
	}

	/**
	 *  This method assign(put) each checker to the Square which has the same coordinate as each Checker
	 *  This method is often called
	 *  After one player's move have been processed and the all the Checkers' (in ArraryList checkerInUse) coordinates have been updated,
	 */
	public void fillBoard(){
		Iterator<Checker> itr  = this.checkersInUse.iterator();
		while(itr.hasNext()){
			Checker checker = itr.next();
			int x = checker.x;
			int y = checker.y;
			//System.out.println("x = " + x + " y = " + y + " | " );
			board[y-1][x-1].setChecker(checker);
		}
	}

	/**
	 *  This method create a new board consist of 8X8 Squares
	 *  It is called after one player's move, before the fillBoard().
	 */
	public void cleanBoard(){
		for(int i = 1; i <= 8; i++){  	  // vertical
			for(int j = 1; j <= 8; j++){  // horizontal
				this.board[i-1][j-1] = new Square();
				this.board[i-1][j-1].setCoordinates(j,i);
			}
		}
	}

	/**
	 * This method prints the Board including Squares and Checkers
	 * @return String representation of the Board.
	 */
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("\n   1 2  3 4  5 6  7 8\n");
		for (int i = 0; i < 8; i++) {
			output.append(letters[i]).append("  ");
			for (int j = 0; j < 8; j++) {
				if (board[i][j].getChecker() != null) {
					output.append(board[i][j].getChecker().toString()).append(" ");
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
	/**
	 * This method finds a checker with certain coordinates if it exists, given coordinates
	 * @param xCoordinate  coordinates of the checker
	 * @param yCoordinate
	 * @return				the checker
	 */
	public Checker findChecker(int xCoordinate, int yCoordinate) {
		for(int i = 0; i < this.checkersInUse.size(); i++) {
			if(xCoordinate == checkersInUse.get(i).getX() && yCoordinate == checkersInUse.get(i).getY()) {
				return checkersInUse.get(i);
			} 
		}
		return null;
	}


	/**
	 * This methods finds a checker in ArraryList<E></> checkerInUse
	 * @param checker	input Checker (with Coordinates info)
	 * @return			a checker in ArraryList<E></> checkerInUse which has the same coordinates as the input Checker
	 */
		public Checker findChecker(Checker checker) {
			for(int i = 0; i < this.checkersInUse.size(); i++) {
				if(checker.getX() == checkersInUse.get(i).getX() && checker.getY() == checkersInUse.get(i).getY()) {
					return checkersInUse.get(i);
				}
			}
			return null;
		}

	/**
	 * This methods checks if a spot is already taken or it's open
	 * @param xCoordinate 	Coordinates of the Spot
	 * @param yCoordinate
	 * @return	is a Boolean if this spot is taken
	 */
	public boolean spotOpen(int xCoordinate, int yCoordinate) {
		for(int i = 0; i < this.checkersInUse.size(); i++) {
			if(this.checkersInUse.get(i).getX() == xCoordinate &&
				this.checkersInUse.get(i).getY() == yCoordinate) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Removes enemy checker that has been eaten from the ArrayList checkerInUse
	 * @param enemyX
	 * @param enemyY	The coordinate of the enemy checker
	 */
	public void removeChecker(int enemyX, int enemyY) {
		for(int i = 0; i < this.checkersInUse.size(); i++) {
			if(checkersInUse.get(i).getX() == enemyX && checkersInUse.get(i).getY() == enemyY) {
				this.checkersInUse.remove(checkersInUse.get(i));
			}
		}
	}
				

	/**
	 * This method checks if the Checker has reached the opposite side ( of the board)
	 * Adds a new KingChecker and removes (regular) Checker if it has
	 */
	public void checkForKing() {
		for(int i = 0; i < this.checkersInUse.size(); i++) {
			if(!this.checkersInUse.get(i).toString().equals("X") || !this.checkersInUse.get(i).toString().equals("O")) {
				if(this.checkersInUse.get(i).getColor() == true) {
					if (this.checkersInUse.get(i).getY() == 8) {
						KingChecker newChecker = new KingChecker(this.checkersInUse.get(i).getX(), this.checkersInUse.get(i).getY(), true, this.checkersInUse.get(i).getNumber());
						this.checkersInUse.add(newChecker);
						this.checkersInUse.remove(this.checkersInUse.get(i));
					}
				}else {
					if(this.checkersInUse.get(i).getY() == 1) {
						KingChecker newChecker = new KingChecker(this.checkersInUse.get(i).getX(), this.checkersInUse.get(i).getY(), false, this.checkersInUse.get(i).getNumber());
						this.checkersInUse.add(newChecker);
						this.checkersInUse.remove(this.checkersInUse.get(i));
					}
				}
			}
		}
	}


	/**
	 * Testing Class
	 * @param args
	 */
	public static void main(String[] args){
		Board board = new Board();
		System.out.print(board);
	}

	/**
	 * Testing Class
	 */
	public void printCheckerCoord(){
		StringBuilder builder = new StringBuilder();
		for (Checker checker : this.checkersInUse){
			builder.append("x = " + checker.x + " y = " + checker.y + " | " );
		}
		System.out.println(builder);
	}
}
